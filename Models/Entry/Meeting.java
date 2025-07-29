package Models.Entry;

import java.time.LocalDate;
import java.time.LocalTime;

public class Meeting extends EntryModel{
    private String modality;
    private String venue;
    private String link;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(String title, String modality){
        super(title);
        this.modality = modality;
        this.venue = "";
        this.link = "";
        // Initialize dates to current date to avoid null
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    //getters
    public String getModality() {
        return this.modality;
    }
    public String getVenue() {
        return this.venue;
    }
    public String getLink() {
        return this.link;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public LocalTime getStartDateTime(){
        return this.startTime;
    }
    public LocalTime getEndDateTime(){
        return this.endTime;
    }
    
    // Setters
    public void setModality(String modality) {
        this.modality = modality;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setStartDate (String startDate){
        this.startDate = LocalDate.parse(startDate);
        System.out.println("parsed Start:" + this.startDate);
    }
    public void setEndDate (String endDate){
        this.endDate = LocalDate.parse(endDate);
        System.out.println("parsed Start:" +this.endDate);
    }
    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
    }
    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }
}
