package Models.Entry;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an event entry in the calendar system.
 * It includes specific attributes such as venue, organizer, start and end dates, and start and end times.
 * It extends the EntryModel to inherit basic entry properties like title.
 */
public class EventEntry extends EntryModel {
    private String venue;               // Location where the event will be held
    private String organizer;           // Person organizing the event
    private LocalDate startDate;        // Start date of the event
    private LocalDate endDate;          // End date of the event
    private LocalTime startTime;        // Start time of the event
    private LocalTime endTime;          // End time of the event

    /**
     * Constructs an EventEntry with the given title, venue, and organizer.
     * Default start and end dates are set to the current date.
     * 
     * @param title     Title of the event
     * @param venue     Location of the event
     * @param owner     Organizer of the event
     */
    public EventEntry(String title, String venue, String owner) {
        super(title);
        this.venue = venue;
        this.organizer = owner;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    /**
     * Gets the details of the event in a formatted string.
     * 
     *  @return Venue of the event */
    public String getVenue() {
        return this.venue;
    }

    /** 
     * Gets the organizer of the event.
     * 
     * @return Organizer of the event */
    public String getOrganizer() {
        return this.organizer;
    }

    /** 
     * Gets the start date of the event.
     * 
     * @return Start date of the event */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the event.
     * 
     *  @return End date of the event */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the start time of the event.
     * 
     *  @return Start time of the event */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /** 
     * Gets the end time of the event.
     * 
     * @return End time of the event */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Sets the start date of the event using a string in "M/d/yyyy" format.
     * 
     * @param startDate The start date as a string
     */
    public void setStartDate(String startDate) {
        this.startDate = parseDate(startDate);
    }

    /**
     * Sets the end date of the event using a string in "M/d/yyyy" format.
     * 
     * @param endDate The end date as a string
     */
    public void setEndDate(String endDate) {
        this.endDate = parseDate(endDate);
    }

    /**
     * Sets the start time of the event using a string in "h:mm a" format (e.g., "3:30 PM").
     * 
     * @param startTime The start time as a string
     */
    public void setStartTime(String startTime) {
        this.startTime = parseTime(startTime);
    }

    /**
     * Sets the end time of the event using a string in "h:mm a" format (e.g., "4:45 PM").
     * 
     * @param endTime The end time as a string
     */
    public void setEndTime(String endTime) {
        this.endTime = parseTime(endTime);
    }

    /**
     * Checks if the provided start date is valid.
     * 
     * @param startDate The start date string to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidStartDate(String startDate) {
        boolean isValid = false;
        return isValid;
    }

    /**
     * Checks if the provided end date is valid.
     * 
     * @param endDate The end date string to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidEndDate(String endDate) {
        boolean isValid = false;
        return isValid;
    }

    /**
     * Converts a time string in the format "h:mm a" into a LocalTime object.
     * 
     * @param timeInput Time string (e.g., "3:30 PM")
     * @return LocalTime object
     */
    private LocalTime parseTime(String timeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return LocalTime.parse(timeInput, formatter);
    }

    /**
     * Converts a date string in the format "M/d/yyyy" into a LocalDate object.
     * 
     * @param dateInput Date string (e.g., "7/28/2025")
     * @return LocalDate object
     */
    private LocalDate parseDate(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(dateInput, formatter);
    }
}