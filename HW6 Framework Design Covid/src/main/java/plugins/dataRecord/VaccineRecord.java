package plugins.dataRecord;

public class VaccineRecord {
    private GroupBy groupBy;
    private long distributed;
    private long distributed_Johnson;
    private long distributed_Pfizer;
    private long distributed_Moderna;
    private long administered;
    private long administered_Johnson;
    private long administered_Pfizer;
    private long administered_Moderna;

    /**
     * the constructor of VaccineRecord
     * @param groupBy groupBy class with data and state inside
     * @param distributed the number of vaccines that have been distributed in US
     * @param distributed_Johnson the number of J&J vaccines that have been distributed in US
     * @param distributed_Pfizer the number of Pfizer vaccines that have been distributed in US
     * @param distributed_Moderna the number of Moderna vaccines that have been distributed in US
     * @param administered the number of vaccines that have been administered in US
     * @param administered_Johnson the number of J&J vaccines that have been administered in US
     * @param administered_Pfizer the number of Pfizer vaccines that have been administered in US
     * @param administered_Moderna the number of Moderna vaccines that have been administered in US
     */
    public VaccineRecord(GroupBy groupBy, long distributed, long distributed_Johnson, long distributed_Pfizer, long distributed_Moderna, long administered, long administered_Johnson, long administered_Pfizer, long administered_Moderna ){
        this.groupBy = groupBy;
        this.distributed = distributed;
        this.distributed_Johnson = distributed_Johnson;
        this.distributed_Pfizer = distributed_Pfizer;
        this.distributed_Moderna = distributed_Moderna;
        this.administered = administered;
        this.administered_Johnson = administered_Johnson;
        this.administered_Pfizer = administered_Pfizer;
        this.administered_Moderna = administered_Moderna;
    }

    /**
     * get the GroupBy class with data and state in the class
     * @return the GroupBy class with data and state in the class
     */
    public GroupBy getGroupBy(){
        return this.groupBy;
    }

    /**
     * get the total number of vaccines that have been distributed in US
     * @return the number of vaccines that have been distributed
     */
    public long getDistributed(){
        return this.distributed;
    }

    /**
     * get the number of J&J that have been distributed
     * @return the number of vaccine J&J that has been distributed
     */
    public long getDistributed_Johnson(){
        return this.distributed_Johnson;
    }

    /**
     * get the number of vaccine Pfizer that have been distributed
     * @return the number of vaccine Pfizer that has been distributed
     */
    public long getDistributed_Pfizer(){
        return this.distributed_Pfizer;
    }

    /**
     * get the number of vaccine Moderna that have been distributed
     * @return he number of vaccine Moderna that has been distributed
     */
    public long getDistributed_Moderna(){
        return this.distributed_Moderna;
    }

    /**
     * get the total number of vaccines that have been administered in US
     * @return total number of vaccines that have been administered in US
     */
    public long getAdministered(){
        return this.administered;
    }

    /**
     * get the number of J&J that have been administered in US
     * @return the number of J&J that have been administered in US
     */
    public long getAdministered_Johnson(){
        return this.administered_Johnson;
    }

    /**
     * get the number of vaccine Pfizer that have been administered
     * @return the number of vaccine Pfizer that have been administered
     */
    public long getAdministered_Pfizer(){
        return this.administered_Pfizer;
    }

    /**
     * get the number of vaccine Moderna that have been administered
     * @return the number of vaccine Moderna that have been administered
     */
    public long getAdministered_Moderna(){
        return this.administered_Moderna;
    }

    @Override
    public String toString(){
        return "VaccineRecord{" +
                "GroupBy=" + groupBy +
                ", distributed=" + distributed +
                ", distributed_Johnson=" + distributed_Johnson +
                ", distributed_Pfizer=" + distributed_Pfizer +
                ", distributed_Moderna=" + distributed_Moderna +
                ", administered=" + administered +
                ", administered_Johnson=" + administered_Johnson +
                ", administered_Pfizer=" + administered_Pfizer +
                ", administered_Moderna" + administered_Moderna +
                "}";
    }



}
