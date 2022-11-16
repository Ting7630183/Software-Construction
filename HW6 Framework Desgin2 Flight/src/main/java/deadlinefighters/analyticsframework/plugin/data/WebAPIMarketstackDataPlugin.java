package deadlinefighters.analyticsframework.plugin.data;

import deadlinefighters.analyticsframework.framework.core.DataPlugin;
import deadlinefighters.analyticsframework.framework.core.Framework;
import deadlinefighters.analyticsframework.framework.model.StockQuote;
import deadlinefighters.analyticsframework.framework.model.StockQuotesResult;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class WebAPIMarketstackDataPlugin implements DataPlugin {
    private static final String NAME = "Web API - Market Stack";
    private static final String NULL_API_KEY_MESSAGE = "API key is empty";
    private static final String BASE_URL = "http://api.marketstack.com/v1/eod";
    private static String key;
    private static HttpClient client = HttpClient.newHttpClient();
    private static String errorMessage = null;

    @Override
    public void openConnection(String arg) throws IOException {
        if (StringUtils.isBlank(arg)) {
            throw new IOException(NULL_API_KEY_MESSAGE);
        }
        key = arg;
    }

    @Override
    public CompletableFuture<StockQuotesResult> getStockQuotes(List<String> symbols) {
        CompletableFuture<StockQuotesResult> stockQuotesResultCompletableFuture
            = CompletableFuture.supplyAsync(new Supplier<StockQuotesResult>() {
            @Override
            public StockQuotesResult get() {
                List<StockQuote> stockQuotes = new ArrayList<>();
                errorMessage = null;
                for (String symbol : symbols) {
                    HttpRequest request = HttpRequest.newBuilder(
                            URI.create(BASE_URL + "?access_key=" + key + "&symbols=" + symbol))
                        .header("Authorization", "Key " + key)
                        .header("Content-Type", "application/json")
                        .build();
                    HttpResponse<String> response = null;
                    try {
                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<StockQuote> parsedData = parseData(response.body(), symbol);
                    if (parsedData != null) {
                        stockQuotes.addAll(parsedData);
                    }
                }
                if (errorMessage != null) {
                    errorMessage = "Missing symbols " + errorMessage;
                }
                return new StockQuotesResult(stockQuotes, errorMessage);
            }
        });
        return stockQuotesResultCompletableFuture;
    }

    @Override
    public void closeConnection() throws IOException {

    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void onRegister(Framework framework) {
    }

    private List<StockQuote> parseData(String response, String symbol) {
        if (!response.contains("data")) {
            if (errorMessage == null) {
                errorMessage = symbol;
            } else {
                errorMessage += " " + symbol;
            }
            return null;
        }
        List<StockQuote> stockQuotes = new ArrayList<>();
        JSONObject json = new JSONObject(response);
        JSONArray array = json.getJSONArray("data");
        int len = array.length();
        for (int i = 0; i < len; i++) {
            JSONObject rawStcokQuote = array.getJSONObject(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            StockQuote stockQuote = new StockQuote(rawStcokQuote.getString("symbol"),
                LocalDate.parse(rawStcokQuote.getString("date").substring(0, 10), formatter),
                rawStcokQuote.getDouble("open"),
                rawStcokQuote.getDouble("close"),
                rawStcokQuote.getDouble("high"),
                rawStcokQuote.getDouble("low")
            );
            stockQuotes.add(stockQuote);
        }
        return stockQuotes;
    }
}
