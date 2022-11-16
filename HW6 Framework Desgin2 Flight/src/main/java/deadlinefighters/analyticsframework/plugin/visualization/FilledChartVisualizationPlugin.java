package deadlinefighters.analyticsframework.plugin.visualization;

import com.fasterxml.jackson.core.JsonProcessingException;
import deadlinefighters.analyticsframework.framework.core.Framework;
import deadlinefighters.analyticsframework.framework.core.VisualizationPlugin;
import deadlinefighters.analyticsframework.framework.model.AggregatedStockQuotes;

import java.util.List;

public class FilledChartVisualizationPlugin implements VisualizationPlugin {
    private static final String NAME = "Filled chart";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void onRegister(Framework framework) {
    }

    @Override
    public boolean isSupported(int symbolCount) {
        return symbolCount == 1;
    }

    @Override
    public String render(List<AggregatedStockQuotes> data) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("<script>\n");
            sb.append(VisualizationHelper.quotesToJavascript(data));
            sb.append("""
                var trace1 = {
                    x: [],
                    y: [],
                    fill: 'tonexty',
                    type: 'scatter',
                    name: aggregated[0].symbol + ' Open'
                };
                var trace2 = {
                    type: 'scatter',
                    fill: 'tozeroy',
                    name: aggregated[0].symbol + ' Close',
                    x: [],
                    y: [],
                };
                aggregated[0].daily.forEach(q => {
                    if (q.date <= "2017-01-01") {
                        return;
                    }
                    trace1.x.push(q.date);
                    trace2.x.push(q.date);
                    trace1.y.push(q.open);
                    trace2.y.push(q.close);
                });
                var data = [trace1, trace2];
                var layout = {
                    title: 'Daily stock prices - ' + aggregated[0].symbol
                };
                Plotly.newPlot('myDiv', data, layout);
                """);
            sb.append("</script>\n");
            sb.append("<div id=\"myDiv\" />");
            return sb.toString();
        } catch (JsonProcessingException e) {
            return e.toString();
        }
    }
}
