package Models.Entry;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        this.startDate = parseDate(startDate);
    }
    public void setEndDate(String endDate) {
        this.endDate = parseDate(endDate);
    }
    public void setStartTime(String startDate) {
        this.startTime = parseTime(startDate);
    }
    public void setEndTime(String endDate) {
        this.endTime = parseTime(endDate);
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
    private LocalTime parseTime (String timeInput){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return LocalTime.parse(timeInput, formatter);
    }
    private LocalDate parseDate (String dateInput){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(dateInput, formatter);
    }
}
