package Models.Entry;


import java.time.LocalDate;
import java.time.LocalTime;

public class EventEntry extends EntryModel{
    private String venue;
    private String organizer;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public EventEntry(String title, String venue, String owner){
        super(title);
        this.organizer = owner;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    //getters
    public String getVenue() {
        return this.venue;
    }
    public String getOrganizer() {
        return this.organizer;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public LocalTime getEndTime(){
        return this.endTime;
    }
    public LocalTime getStartTime(){
        return this.startTime;
    }

    //setters
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }
    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }
    public void setStartTime(String startDate) {
        this.startTime = LocalTime.parse(startDate);
    }
    public void setEndTime(String endDate) {
        this.endTime = LocalTime.parse(endDate);
    }

    /*
     * 
     */
    public boolean isValidStartDate(String startDate){
        boolean isValid = false;
        return isValid;
    }
    /*
     * 
     */
    public boolean isValidEndDate(String endDate){
        boolean isValid = false;
        return isValid;
    }
}
