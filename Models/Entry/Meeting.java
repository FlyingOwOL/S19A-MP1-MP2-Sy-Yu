package Models.Entry;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Meeting class represents a scheduled meeting entry in the calendar system.
 * A meeting includes details such as its modality (online or face-to-face), venue,
 * meeting link (if online), and its date and time information.
 * 
 * This class extends the EntryModel base class and adds attributes relevant to meetings.
 */
public class Meeting extends EntryModel {
    private String modality;     // The type of meeting: "online", "face-to-face", or "hybrid"
    private String venue;        // The venue 
    private String link;         // The online meeting link
    private LocalDate startDate; // The starting date of the meeting
    private LocalDate endDate;   // The ending date of the meeting
    private LocalTime startTime; // The starting time of the meeting
    private LocalTime endTime;   // The ending time of the meeting

    /**
     * Constructs a new Meeting instance with the given title and modality.
     *
     * @param title    the title of the meeting
     * @param modality the type of meeting (e.g., "online", "face-to-face")
     */
    public Meeting(String title, String modality) {
        super(title);
        this.modality = modality;
        this.venue = "";
        this.link = "";
        // Initialize dates to current date to avoid null
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    /**
     * Gets the modality of the meeting.
     * 
     * @return the meeting modality
     */
    public String getModality() {
        return this.modality;
    }

    /**
     * Gets the venue of the meeting.
     * 
     * @return the meeting venue
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Gets the online link of the meeting.
     * 
     * @return the meeting link
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Gets the start date of the meeting.
     * 
     * @return the start date
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the meeting.
     * 
     * @return the end date
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the start time of the meeting.
     * 
     * @return the start time
     */
    public LocalTime getStartDateTime() {
        return this.startTime;
    }

    /**
     * Gets the end time of the meeting.
     * 
     * @return the end time
     */
    public LocalTime getEndDateTime() {
        return this.endTime;
    }

    /**
     * Sets the modality of the meeting.
     * 
     * @param modality the new modality
     */
    public void setModality(String modality) {
        this.modality = modality;
    }

    /**
     * Sets the venue of the meeting.
     * 
     * @param venue the venue
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * Sets the link for the online meeting.
     * 
     * @param link the meeting link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Sets the start date of the meeting.
     * 
     * @param startDate the start date as a string in "M/d/yyyy" format
     */
    public void setStartDate (String startDate){
        try {
            this.startDate = parseDate(startDate.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid start date format", startDate, 0);
        }
    }

    /**
     * Sets the end date of the meeting.
     * 
     * @param endDate the end date as a string in "M/d/yyyy" format
     */
    public void setEndDate (String endDate){
        try {
            this.endDate = parseDate(endDate.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid end date format", endDate, 0);
        }
    }

    /**
     * Sets the start time of the meeting using a string in "h:mm a" format (e.g., "3:30 PM").
     * 
     * @param startTime the start time as a string
     */
    public void setStartTime(String startTime) {
        try {
            this.startTime = parseTime(startTime.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid start time format", startTime, 0);
        }
    }

    /**
     * Sets the end time of the meeting using a string in "h:mm a" format (e.g., "3:30 PM").
     * 
     * @param endTime the end time as a string
     */
    public void setEndTime(String endTime) {
        try {
            this.endTime = parseTime(endTime.trim());
        } catch (Exception e) {
            throw new DateTimeParseException("Invalid end time format", endTime, 0);
        }
    }

    /**
     * Returns a string representation of the meeting entry.
     * 
     * @return a string containing the title, modality, venue, link, start date, end date, start time, and end time
     */
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
    
    /**
     * Parses a date string in various formats and returns a LocalDate object.
     * 
     * @param dateInput the date string to parse
     * @return LocalDate object representing the parsed date
     * @throws DateTimeParseException if the date cannot be parsed
     */
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