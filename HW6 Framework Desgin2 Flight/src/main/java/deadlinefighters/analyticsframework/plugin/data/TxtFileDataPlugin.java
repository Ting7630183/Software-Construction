package deadlinefighters.analyticsframework.plugin.data;

import deadlinefighters.analyticsframework.framework.core.DataPlugin;
import deadlinefighters.analyticsframework.framework.core.Framework;
import deadlinefighters.analyticsframework.framework.model.StockQuote;
import deadlinefighters.analyticsframework.framework.model.StockQuotesResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;

/**
 * Implementation of a *.txt local file data plugin.
 * The txt file should be organized as :
 *      first line => header with column titles
 *      rest => values
 * The header columns should have at least :
 *      index, date, open, high, low, close
 * Date should be formatted as yyyy-MM-dd
 *
 * Example file– src/resources/daily_uniqlo.txt
 */
public class TxtFileDataPlugin implements DataPlugin {
    private static final String PLUGIN_NAME = "TXT local file";
    private static final String[] TARGET_COL_NAMES = {"index", "date",
        "open", "high", "low", "close"};
    private Framework framework;
    private BufferedReader bReader;

    /**
     * Establish a connection with the data source, this is called by the
     * framework before getStockQuotes is called.
     *
     * @param arg argument required to initialize the data plugin
     *            (e.g. local file path, web url or database url)
     * @throws IllegalArgumentException when fail to parse the arg
     *                                  or IOException when fail to establish a connection
     */
    @Override
    public void openConnection(String arg) throws IOException {
        FileReader r = new FileReader(arg, StandardCharsets.UTF_8);
        bReader = new BufferedReader(r);
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
            @Override
            public StockQuotesResult get() {
                List<StockQuote> stockQuotes = new ArrayList<>();
                String errorMessage = null;
                Set<String> symbolsSet = new HashSet<>(symbols);
                Set<String> symbolsVisited = new HashSet<>();

                try {
                    String header = bReader.readLine();
                    String[] nextRecord0 = header.trim().split("\\s");
                    String[] nextRecord = new String[nextRecord0.length];
                    for (int i = 0; i < nextRecord0.length; i++) {
                        String str = nextRecord0[i].replaceAll("[^a-zA-Z]", "");
                        nextRecord[i] = str;
                    }
                    int[] colIndex = getColIndexByHeader(nextRecord);

                    for (String line; (line = bReader.readLine()) != null; ) {
                        nextRecord = line.split(" ");

                        String symbol = nextRecord[colIndex[0]];
                        if (!symbolsSet.contains(symbol)) continue;
                        symbolsVisited.add(symbol);

                        LocalDate date = LocalDate.parse(nextRecord[colIndex[1]]);
                        double open = Double.parseDouble(nextRecord[colIndex[2]]);
                        double high = Double.parseDouble(nextRecord[colIndex[3]]);
                        double low = Double.parseDouble(nextRecord[colIndex[4]]);
                        double close = Double.parseDouble(nextRecord[colIndex[5]]);

                        stockQuotes.add(new StockQuote(symbol, date, open, close, high, low));
                    }
                } catch (IOException e) {
                    throw new CompletionException(e);
                }
                symbolsSet.removeAll(symbolsVisited);
                if (symbolsSet.size() != 0) {
                    StringBuilder missingSymbols = new StringBuilder();
                    for (String noSymbol : symbolsSet) {
                        missingSymbols.append(" ").append(noSymbol);
                    }
                    errorMessage = "Missing symbols" + missingSymbols;
                }

                return new StockQuotesResult(stockQuotes, errorMessage);
            }
        });
        return stockQuotesResultCompletableFuture;
    }

    protected int[] getColIndexByHeader(String[] headers) {
        int[] colIndex = new int[TARGET_COL_NAMES.length];
        List<String> headerList = Arrays.asList(headers);
        for (int i = 0; i < TARGET_COL_NAMES.length; i++) {
            colIndex[i] = headerList.indexOf(TARGET_COL_NAMES[i]);
        }
        return colIndex;
    }

    /**
     * Close the connection with the data source, this is called by the
     * framework after getStockQuotes is called.
     *
     * @throws IOException when fail to close a connection
     */
    @Override
    public void closeConnection() throws IOException {
        bReader.close();
    }

    /**
     * Get the name of the plugin to show on the GUI
     *
     * @return name of the plugin
     */
    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    /**
     * Called (only once) when the plugin is first registered with the
     * framework, giving the plugin a chance to perform any set-up during
     * plugin registration.
     *
     * @param framework The {@link Framework} instance with which the plugin
     *                  was registered.
     */
    @Override
    public void onRegister(Framework framework) {
        this.framework = framework;
    }
}

