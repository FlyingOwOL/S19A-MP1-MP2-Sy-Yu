package Models.Entry;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an event entry with additional attributes like venue, organizer,
 * start/end dates, and start/end times.
 * Extends the base EntryModel class.
 */
public class EventEntry extends EntryModel {
    private String venue;           // The venue where the event will be held
    private String organizer;       // The organizer or owner of the event
    private LocalDate startDate;    // The start date of the event    
    private LocalDate endDate;      // The end date of the event
    private LocalTime startTime;    // The start time of the event
    private LocalTime endTime;      // The end time of the event

    /**
     * Constructs an EventEntry with a given title, venue, and organizer.
     * Sets default start and end dates to the current date.
     *
     * @param title   The title of the event.
     * @param venue   The location where the event will be held.
     * @param owner   The organizer or owner of the event.
     */
    public EventEntry(String title, String venue, String owner) {
        super(title);
        this.venue = venue;
        this.organizer = owner;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    /**
     * Gets the venue of the event.
     *
     * @return The venue.
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Gets the organizer of the event.
     *
     * @return The organizer.
     */
    public String getOrganizer() {
        return this.organizer;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Sets the venue of the event.
     *
     * @param venue The venue to set.
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * Sets the organizer of the event.
     *
     * @param organizer The organizer to set.
     */
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    /**
     * Sets the start date of the event.
     *
     * @param startDate The start date as a string in ISO format (yyyy-MM-dd).
     */
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    /**
     * Sets the end date of the event.
     *
     * @param endDate The end date as a string in ISO format (yyyy-MM-dd).
     */
    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }

    /**
     * Sets the start time of the event.
     *
     * @param startTime The start time as a string in HH:mm format.
     */
    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
    }

    /**
     * Sets the end time of the event.
     *
     * @param endTime The end time as a string in HH:mm format.
     */
    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }

    /**
     * Checks if the provided start date string is valid.
     *
     * @param startDate The start date as a string.
     * @return true if valid; false otherwise.
     */
    public boolean isValidStartDate(String startDate) {
        boolean isValid = false;
        return isValid;
    }

    /**
     * Checks if the provided end date string is valid.
     *
     * @param endDate The end date as a string.
     * @return true if valid; false otherwise.
     */
    public boolean isValidEndDate(String endDate) {
        boolean isValid = false;
        return isValid;
    }
}
