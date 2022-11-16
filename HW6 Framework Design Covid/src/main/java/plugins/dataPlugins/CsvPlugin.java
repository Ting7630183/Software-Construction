package plugins.dataPlugins;

import framework.core.DataPlugin;
import framework.core.Framework;
import plugins.dataRecord.DataRecord;
import plugins.dataRecord.GroupBy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * extract data from Csv file and then store the data in HospitalRecord class, and then sum monthly date.
 * Convert the month-long HospitalRecord into a DataRecord, and then use a map to map
 * the groupBy(date and state) and dataRecord
 */
public class CsvPlugin implements DataPlugin {
    private Map<GroupBy, DataRecord> dataSet;
    private List<String> fieldNames;
    private Framework framework;
    private static final int fieldNumber = 4;
    private static final String pluginName = "CsvPlugin";
    private int earliestDate;
    private int latestDate;

    /**
     * constructor of CsvPlugin
     * add all the fields into the fieldNames list
     */
    public CsvPlugin() {
        dataSet = new HashMap<>();
        fieldNames = new ArrayList<>();
        fieldNames.add("hospitalized");
        fieldNames.add("inICU");
        fieldNames.add("onVentilator");
        fieldNames.add("recovered");
        earliestDate = Integer.MAX_VALUE;
        latestDate = Integer.MIN_VALUE;
    }

    @Override
    public String getPluginName() {
        return this.pluginName;
    }

    @Override
    public void read(String filePath) throws IOException, InterruptedException {

    }

    @Override
    public Map<GroupBy, DataRecord> getDate() {
        return this.dataSet;
    }

    @Override
    public int getEarliestDate() {
        return this.earliestDate;
    }

    @Override
    public int getLatestDate() {
        return this.latestDate;
    }

    @Override
    public String getPathName() {
        return null;
    }

    @Override
    public void register(Framework framework) {
        this.framework = framework;

    }
}
