package plugins.dataRecord;

public class CaseRecord {
    private GroupBy groupBy;
    private long newCases;
    private long deaths;

    /**
     * constructor of the CaseRecord
     * @param groupBy groupBy class with date and state inside
     * @param newCases the number of Covid-19 newCases on certain day and certain state
     * @param deaths the number of Covid-19 deaths on certain day and certain state
     */
    public CaseRecord(GroupBy groupBy, long newCases, long deaths){
        this.groupBy = groupBy;
        this.newCases = newCases;
        this.deaths = deaths;
    }

    /**
     * get the GroupBy with date and state
     * @return the GroupBy with date and state
     */
    public GroupBy getGroupBy(){
        return this.groupBy;
    }

    /**
     * get the number of Covid-19 newCases on certain day and certain state
     * @return the number of Covid-19 newCases on certain day and certain state
     */
    public long getNewCases(){
        return this.newCases;
    }

    /**
     * get the number of Covid-19 deaths on certain day and certain state
     * @return the number of Covid-19 deaths on certain day and certain state
     */
    public long getDeaths(){
        return this.deaths;
    }

    /**
     * get the String of the CaseRecord class
     * @return the String of the CaseRecord class
     */
    @Override
    public String toString(){
        return "CaseRecord{" +
                "groupBy=" + groupBy +
                ", newCases=" + newCases +
                ", deaths=" + deaths +
                "}";
    }
}
