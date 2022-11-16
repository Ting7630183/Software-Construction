package plugins.dataPlugins;

import framework.core.DataPlugin;
import framework.core.Framework;
import plugins.dataRecord.DataRecord;
import plugins.dataRecord.GroupBy;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import plugins.dataRecord.VaccineRecord;

/**
 * extract data from Json file and then store the data in vaccineRecord class, and then sum monthly date.
 * Convert the month-long vaccineRecord into a DataRecord, and then use a map to map
 * the groupBy(date and state) and dataRecord
 */
public class JsonPlugin implements DataPlugin{
    private Map<GroupBy, DataRecord> dataSet;
    private List<String> fieldNames;
    private String filePath;
    private static final String pluginName = "JsonPlugin";
    private Framework framework;
    private static final int fieldNumber = 8;
    private static final String pathName = "vaccination.json";
    private int earliestDate;
    private int latestDate;


    /**
     * constructor of JsonPlugin. Add all the fields into a list
     */
    public JsonPlugin(){
        this.dataSet = new HashMap<>();
        fieldNames = new ArrayList<>();
        fieldNames.add("distributed");
        fieldNames.add("distributed_Johnson");
        fieldNames.add("distributed_Pfizer");
        fieldNames.add("distributed_Moderna");
        fieldNames.add("administered");
        fieldNames.add("administered_Johnson");
        fieldNames.add("administered_Pfizer");
        fieldNames.add("administered_Moderna");
        earliestDate = Integer.MAX_VALUE;
        latestDate = Integer.MIN_VALUE;

    }

    @Override
    public String getPluginName() {
        return this.pluginName;
    }

    @Override
    public void read(String filePath) throws IOException, InterruptedException {

        JSONParser parser = new JSONParser();
        try{
            JSONArray JsonArray = (JSONArray) parser.parse(new FileReader(filePath));
            List<VaccineRecord> vaccineRecordsList = new ArrayList<>();
            for(Object o: JsonArray){
                JSONObject object = (JSONObject) o;
                long distributed = (long) object.get("Distributed");
                long distributed_Johnson = (long) object.get("Distributed_Janssen");
                long distributed_Pfizer = (long) object.get("Distributed_Pfizer");
                long distributed_Moderna = (long) object.get("Distributed_Moderna");
                long administered = (long) object.get("Administered");
                long administered_Johnson = (long) object.get("Administered_Janssen");
                long administered_Pfizer = (long) object.get("Administered_Pfizer");
                long administered_Moderna = (long) object.get("Administered_Moderna");
                String date = (String) object.get("Date");
                String state = (String) object.get("Location");

                //format the date into MM/DD/YYYY
                int indexOfFirstSlash = date.indexOf("/", 0);
                int indexOfSecondSlash =date.indexOf("/", 3);
                String month = date.substring(0, indexOfFirstSlash);
                String year = date.substring(indexOfSecondSlash+1);
                StringBuilder stringBuilder = new StringBuilder();
                if(month.length() == 1){
                    stringBuilder.append("0");
                }
                stringBuilder.append(month);
                stringBuilder.append("/");
                stringBuilder.append("01");
                stringBuilder.append("/");
                stringBuilder.append("20");
                stringBuilder.append(year);
                String formattedDate = stringBuilder.toString();
                System.out.println(formattedDate);

                //construct a list of vaccineRecord
                GroupBy groupBy = new GroupBy(formattedDate, state);
                VaccineRecord vaccineRecord = new VaccineRecord(groupBy, distributed, distributed_Johnson,
                                                                distributed_Pfizer, distributed_Moderna,
                                                                administered, administered_Johnson,
                                                                 administered_Pfizer, administered_Moderna);
                vaccineRecordsList.add(vaccineRecord);

                //update the earliest and latest date
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("20");
                stringBuilder1.append(year);
                if(month.length() == 1){
                    stringBuilder1.append("0");
                }
                stringBuilder1.append(month);
                String date1 = stringBuilder1.toString();
                int dateInInteger = Integer.parseInt(date1);
                if(dateInInteger < earliestDate){
                    earliestDate = dateInInteger;
                }

                if(dateInInteger > latestDate){
                    latestDate = dateInInteger;
                }
            }

            //stream the list of vaccineRecord and calculate the month sum of each field
            //then form a map to store the date into dataRecord
            dataSet = vaccineRecordsList.stream().collect(Collectors.groupingBy(VaccineRecord::getGroupBy, Collectors.reducing((sum, s)->
                    new VaccineRecord(s.getGroupBy(), sum.getDistributed()+ s.getDistributed(),
                            sum.getDistributed_Johnson() + s.getDistributed_Moderna(),
                            sum.getDistributed_Pfizer() + s.getDistributed_Pfizer(),
                            sum.getDistributed_Moderna() + s.getDistributed_Moderna(),
                            sum.getAdministered() + s.getAdministered(),
                            sum.getAdministered_Johnson() + s.getAdministered_Johnson(),
                            sum.getAdministered_Pfizer() + s.getAdministered_Pfizer(),
                            sum.getAdministered_Moderna() + s.getAdministered_Moderna()))))
                    .entrySet().stream().collect(Collectors.toMap(
                            entry->entry.getKey(),
                            entry->new DataRecord(entry.getValue().get())
                    ));
            for(Map.Entry<GroupBy, DataRecord> entry: dataSet.entrySet()){
                System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        return this.filePath;
    }

    @Override
    public void register(Framework framework) {
        this.framework = framework;
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        read("vaccination.json");
//    }
}
