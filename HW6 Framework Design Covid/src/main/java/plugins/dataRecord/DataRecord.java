package plugins.dataRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataRecord uses GroupBy as key to combine all the fields of CaseRecord,
 * HospitalRecord, VaccineRecord to a List for framework to use
 */
public class DataRecord {
    private GroupBy groupBy;
    private List<Long> fields;

    /**
     * constructor with no data coming in yet
     * @param groupBy the GroupBy class with data and state inside
     */
    public DataRecord(GroupBy groupBy){
        this.groupBy = groupBy;
        this.fields = new ArrayList<>();
    }

    /**
     * constructor of DataRecord taking caseRecord as input,
     * put all the fields in caseRecord into the list of fields in DataRecord
     * @param caseRecord caseRecord with fields: newCases, deaths
     */
    public DataRecord(CaseRecord caseRecord){
        this.groupBy = caseRecord.getGroupBy();
        fields = new ArrayList<>();
        fields.add(caseRecord.getNewCases());
        fields.add(caseRecord.getDeaths());
    }

    /**
     * constructor of DataRecord taking hospitalRecord as input,
     * put all the fields in hospitalRecord into the list of fields in DataRecord
     * @param hospitalRecord hospitalRecord with fields: hospitalized, inICU, onVentilator, recovered
     */
    public DataRecord(HospitalRecord hospitalRecord){
        this.groupBy = hospitalRecord.getGroupBy();
        fields = new ArrayList<>();
        fields.add(hospitalRecord.getHospitalized());
        fields.add(hospitalRecord.getInICU());
        fields.add(hospitalRecord.getOnVentilator());
        fields.add(hospitalRecord.getRecovered());
    }

    /**
     * constructor of DataRecord taking vaccineRecord as input,
     * put all the fields in vaccineRecord into the list of fields in DataRecord
     * @param vaccineRecord vaccineRecord with fields: distributed, distributed_Johnson,
     *                      distributed_Pfizer, distributed_Moderna, administered,
     *                      administered_Johnson, administered_Pfizer, administered_Moderna
     */
    public DataRecord(VaccineRecord vaccineRecord){
        this.groupBy = vaccineRecord.getGroupBy();
        fields = new ArrayList<>();
        fields.add(vaccineRecord.getDistributed());
        fields.add(vaccineRecord.getDistributed_Johnson());
        fields.add(vaccineRecord.getDistributed_Pfizer());
        fields.add(vaccineRecord.getDistributed_Moderna());
        fields.add(vaccineRecord.getAdministered());
        fields.add(vaccineRecord.getAdministered_Johnson());
        fields.add(vaccineRecord.getAdministered_Pfizer());
        fields.add(vaccineRecord.getAdministered_Moderna());
    }

    /**
     * get the GroupBy class with data and state in the DataRecord class
     * @return the GroupBy class with data and state in the DataRecord class
     */
    public GroupBy getGroupBy(){
        return this.groupBy;
    }

    /**
     * set the GroupBy in the DateRecord class
     * @param groupBy the new groupBy that is put into the DateRecord class
     */
    public void setGroupBy(GroupBy groupBy){
        this.groupBy = groupBy;
    }

    /**
     * get all fields in the DataRecord
     * @return all fields in the DataRecord
     */
    public List<Long> getFields(){
        return this.fields;
    }

    /**
     * put more fields into the DataRecord class
     * @param fields a list of fields that will be put into the DataRecord class
     */
    public void setFields(List<Long> fields){
        this.fields.addAll(fields);
    }

    @Override
    public String toString(){
        return "DataRecord{" +
                "GroupBy=" + groupBy +
                ", fields=" + Arrays.toString(fields.toArray()) + // convert list to array and then convert long to string
                "}";
    }





}
