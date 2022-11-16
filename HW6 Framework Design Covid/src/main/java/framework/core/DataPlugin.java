package framework.core;

import plugins.dataRecord.DataRecord;
import plugins.dataRecord.GroupBy;

import java.io.IOException;
import java.util.Map;

public interface DataPlugin {

    /**
     * get the name of the data plugin
     * @return the name of the data plugin
     */
    public String getPluginName();

    /**
     * read the data from the file in filePath and then store the data in corresponding dataRecord class
     * @param filePath the path name of the file
     */
    public void read(String filePath) throws IOException, InterruptedException;

    /**
     * get the data from the plugin
     * @return the data from the plugin, the data is put into a map
     */
    public Map<GroupBy, DataRecord> getDate();

    /**
     * get the earliest data in the plugin in the format of Integer
     * @return the earliest data in the plugin in the format of Integer
     */
    public int getEarliestDate();


    /**
     * get the latest data in the plugin in the format of Integer YYYYMM
     * @return the latest data in the plugin in the format of Integer YYYYMM
     */
    public int getLatestDate();

    /**
     * get the path name of the file in the plugin
     * @return the path name of the file in the plugin, can be URl or local file path
     */
    public String getPathName();

    /**
     *Called (only once) when the plugin is first register to framework, giving the chance of
     * the plugin to perform any initial set-up before extracting data from the plugin(if necessary)
     * @param framework The{@link Framework} instance with which the plugin was registered
     */
    public void register(Framework framework);


}
