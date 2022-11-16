package plugins.dataRecord;

public class GroupBy {
    private String date;
    private String state;

    /**
     * constructor of groupBy class
     * @param date the date
     * @param state the state
     */
    public GroupBy(String date, String state){
        this.date = date;
        this.state = state;
    }

    /**
     * get the date from the class
     * @return the date
     */
    public String getDate(){
        return this.date;
    }

    /**
     * get the state from the class
     * @return the state
     */
    public String getState(){
        return this.state;
    }

    /**
     * get the hashCode of the class
     * @return int the hashCode of the class
     */
    @Override
    public int hashCode(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date);
        stringBuilder.append(state);
        return stringBuilder.toString().hashCode();
    }

    /**
     * compare two groupBy object
     * @param obj another groupBy object
     * @return true if two groupBy are the same, otherwise return false
     */
    @Override
    public boolean equals(Object obj){
        GroupBy other = (GroupBy) obj; // cast the obj to groupBy
        if(other.getDate().equals(this.date) && other.getState().equals(this.state)){
            return true;
        }
        return false;
    }

    /**
     * convert the class to String
     * @return the String of the class
     */
    @Override
    public String toString(){
        return "groupBy{" +
                "date = " + date +
                ",  state = " + state +
                "}";
    }


}
