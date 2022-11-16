package deadlinefighters.analyticsframework.plugin.data;
import deadlinefighters.analyticsframework.framework.core.DataPlugin;
import deadlinefighters.analyticsframework.framework.core.Framework;
import deadlinefighters.analyticsframework.framework.model.StockQuote;
import deadlinefighters.analyticsframework.framework.model.StockQuotesResult;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Import data from Json file
 *
 * It should have the following columns:
 * symbol,date,open,high,low,close
 *
 * Symbol, date and close are required columns
 *
 * Date format: yyyy-MM-dd
 *
 * Example: src/main/resources/bitcoin.json (note: volume is ignored)
 */
public class JsonFileDataPlugin implements DataPlugin {

    private static final String NAME = "Json local file";
    private static final String[] TARGET_COL = {"symbol", "date",
        "open", "high", "low", "close"};
    private Framework framework;
    private static FileReader f;

    public JsonFileDataPlugin() {
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void onRegister(Framework f) {
        framework = f;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    /**
     * Establish a connection with the data source, this is called by the
     * framework before getStockQuotes is called.
     *
     * @param arg argument required to initialize the data plugin
     *            (e.g. local file path, web url or database url)
     * @throws FileNotFoundException when fail to parse the arg
     *                                  or IOException when fail to establish a connection
     */
    @Override
    public void openConnection(String arg) throws FileNotFoundException {
        f = new FileReader(arg);
    }

    /**
     * Read from the data source with only selected symbols
     * and parse the data into stock object.
     * <p>
     * If exception was thrown during the execution, the framework should catch
     * ExecutionException and handle it.
     * <p>
     * If symbols are missing in the data source, error message will be written
     * in the result.
     *
     * @param symbols a list of selected symbols
     * @return a promise of stock quotes result
     */
    @Override
    public CompletableFuture<StockQuotesResult> getStockQuotes(List<String> symbols) {
        CompletableFuture<StockQuotesResult> stockQuotesResultCompletableFuture
            = CompletableFuture.supplyAsync(new Supplier<StockQuotesResult>() {
            //            @Override
            public StockQuotesResult get() {
                List<StockQuote> stockQuotes = new ArrayList<>();
                boolean hasError = false;
                String errorMessage = null;
                Set<String> symbolsSet = new HashSet<>(symbols);
                Set<String> symbolsVisited = new HashSet<>();

                String[] nextRecord;
                int[] colIndex;
                try {
                    JSONParser JsonParser = new JSONParser();
                    JSONArray JsonArray = (JSONArray) JsonParser.parse(f);

                    for (Object o : JsonArray) {
                        JSONObject object = (JSONObject) o;
                        String symbol = (String) object.get("symbol");
                        if (!symbolsSet.contains(symbol)) continue;
                        symbolsVisited.add(symbol);

                        LocalDate date = LocalDate.parse((CharSequence) object.get("date"));

                        Object lowObject = object.get("low");
                        double low = new Double(lowObject.toString());

                        Object highObject = object.get("high");
                        double high = new Double(highObject.toString());

                        Object openObject = object.get("open");
                        double open = new Double(openObject.toString());

                        Object closeObject = object.get("close");
                        double close = new Double(closeObject.toString());
                        stockQuotes.add(new StockQuote(symbol, date, open,
                            close, high, low));
                    }

                }catch(IOException | ParseException e){
                    System.out.println("IO error");
                }

                // check symbols that haven't seen
                symbolsSet.removeAll(symbolsVisited);
                if (symbolsSet.size() != 0) {
                    hasError = true;
                    StringBuilder missingSymbol = new StringBuilder();
                    for (String noSymbol : symbolsSet) {
                        missingSymbol.append(" ").append(noSymbol);
                    }
                    errorMessage = "Missing symbols" + missingSymbol;
                }

                return new StockQuotesResult(stockQuotes, errorMessage);
            }
        });

        return stockQuotesResultCompletableFuture;
    }

    /**
     * Close the connection with the data source, this is called by the
     * framework after getStockQuotes is called.
     *
     * @throws IOException when fail to close a connection
     */
    @Override
    public void closeConnection() throws IOException {
        f.close();
    }

}
