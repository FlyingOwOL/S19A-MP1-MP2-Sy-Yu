package Models.Entry;

public class Task extends EntryModel{
    private String priority;
    private String status;
    private String createdBy;
    private String finishedBy;

    public Task(String title, String priority, String status,String createdBy){
        super(title);
        this.priority = priority;
        this.status = status;
        this.createdBy = createdBy;
        this.finishedBy = "";
    }

    //getters
    public String getPriority() {
        return this.priority;
    }
    public String getStatus() {
        return this.status;
    }
    public String getFinishedBy(){
        return this.finishedBy;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }

    //setters
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * 
     */
    public void finishTask (String finishedBy){
        this.status = "done";
        this.finishedBy = finishedBy;
    }
    /*
     * 
     */
    public boolean isValidStatus(String status){
        boolean isValid = false;
        switch(status){
            case "high":
                isValid = true;
                break;
            case "medium":
                isValid = true;
                break;
            case "low":
                isValid = true;
                break;
        }
        return isValid;
    }
}
