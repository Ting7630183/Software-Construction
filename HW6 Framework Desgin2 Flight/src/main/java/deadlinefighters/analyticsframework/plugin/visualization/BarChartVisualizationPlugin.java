package deadlinefighters.analyticsframework.plugin.visualization;

import com.fasterxml.jackson.core.JsonProcessingException;
import deadlinefighters.analyticsframework.framework.core.Framework;
import deadlinefighters.analyticsframework.framework.core.VisualizationPlugin;
import deadlinefighters.analyticsframework.framework.model.AggregatedStockQuotes;

import java.util.List;

public class BarChartVisualizationPlugin implements VisualizationPlugin {
    private Framework framework;
    private static final String NAME = "Bar chart";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void onRegister(Framework framework) {
        this.framework = framework;
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
                  name: 'High',
                  type: 'bar'
                };
                var trace2 = {
                  x: [],
                  y: [],
                  name: 'Low',
                  type: 'bar'
                };
                aggregated[0].monthly.forEach(q => {
                    if (q.date <= "2017-01-01") {
                        return;
                    }
                    trace1.x.push(q.date);
                    trace2.x.push(q.date);
                    trace1.y.push(q.high);
                    trace2.y.push(q.low);
                });

                var data = [trace1, trace2];

                var layout = {barmode: 'group'};
                 var layout = {
                    title: 'Monthly Highest and Lowest Stock Price - ' + aggregated[0].symbol
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
