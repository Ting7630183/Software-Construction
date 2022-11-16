package plugins.dataRecord;

public class HospitalRecord {
    private GroupBy groupBy;
    private long hospitalized;
    private long inICU;
    private long onVentilator;
    private long recovered;

    /**
     * constructor of the  HospitalRecord
     * @param groupBy the GroupBy class with date and state
     * @param hospitalized the number of patients in hospital in certain day and certain state
     * @param inICU the number of patients in ICU in certain day and certain state
     * @param onVentilator the number of patients on ventilator in certain day and certain state
     * @param recovered the number of patients who have recovered from Covid-19 in certain day and certain state
     */
    public HospitalRecord(GroupBy groupBy, long hospitalized, long inICU, long onVentilator, long recovered){
        this.groupBy = groupBy;
        this.hospitalized = hospitalized;
        this.inICU = inICU;
        this.onVentilator = onVentilator;
        this.recovered = recovered;
    }

    /**
     * get the GroupBy with date and state
     * @return the GroupBy with date and state
     */
    public GroupBy getGroupBy(){
        return this.groupBy;
    }

    /**
     * get the number of patients in hospital in certain day and certain state
     * @return the number of patients in hospital in certain day and certain state
     */
    public long getHospitalized(){
        return this.hospitalized;
    }

    /**
     * get the number of patients in ICU in certain day and certain state
     * @return the number of patients in ICU in certain day and certain state
     */
    public long getInICU(){
        return this.inICU;
    }

    /**
     * get the number of patients on ventilator in certain day and certain state
     * @return the number of patients on ventilator in certain day and certain state
     */
    public long getOnVentilator(){
        return this.onVentilator;
    }

    /**
     * get the number of patients who have recovered from Covid-19 in certain day and certain state
     * @return the number of patients who have recovered from Covid-19 in certain day and certain state
     */
    public long getRecovered(){
        return this.recovered;
    }

    @Override
    public String toString(){
        return "HospitalRecord{" +
                "groupBy=" + groupBy +
                ", hospitalized=" + hospitalized +
                ", inICU=" + inICU +
                ", onVentilator" + onVentilator +
                ", recovered" + recovered +
                "}";
    }

}
