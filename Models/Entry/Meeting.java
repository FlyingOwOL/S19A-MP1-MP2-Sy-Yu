package Models.Entry;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        try {
            this.startDate = parseDate(startDate.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid start date format", startDate, 0);
        }
    }
    public void setEndDate (String endDate){
        try {
            this.endDate = parseDate(endDate.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid end date format", endDate, 0);
        }
    }
    public void setStartTime(String startTime) {
        try {
            this.startTime = parseTime(startTime.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid start time format", startTime, 0);
        }
    }
    public void setEndTime(String endTime) {
        try {
            this.endTime = parseTime(endTime.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid end time format", endTime, 0);
        }
    }

    private LocalTime parseTime (String timeInput){
        if (timeInput == null || timeInput.trim().isEmpty()) {
            return LocalTime.now();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            return LocalTime.parse(timeInput.trim(), formatter);
        } catch (DateTimeParseException e) {
            // Try alternative format
            try {
                DateTimeFormatter altFormatter = DateTimeFormatter.ofPattern("H:mm");
                return LocalTime.parse(timeInput.trim(), altFormatter);
            } catch (DateTimeParseException e2) {
                throw new DateTimeParseException("Cannot parse time: " + timeInput, timeInput, 0);
            }
        }
    }
    
    private LocalDate parseDate (String dateInput){
        if (dateInput == null || dateInput.trim().isEmpty()) {
            return LocalDate.now();
        }
        
        String cleanInput = dateInput.trim();
        
        // Try multiple patterns
        String[] patterns = {"M/d/yyyy", "MM/dd/yyyy", "d/M/yyyy", "dd/MM/yyyy"};
        
        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(cleanInput, formatter);
            } catch (DateTimeParseException e) {
                continue; // Try next pattern
            }
        }
        
        throw new DateTimeParseException("Cannot parse date: " + dateInput + ". Use format like 7/25/2025", dateInput, 0);
    }
}
