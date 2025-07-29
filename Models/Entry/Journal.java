package Models.Entry;

public class Journal extends EntryModel{
    private String details;
    private String month;

    public Journal(String title, String details, String month){
        super(title);
        this.details = details;
        this.month = month;
    }

    //getter
    public String getDetails(){
        return this.details;
    }
    public String getMonth(){
        return this.month;
    }
}
