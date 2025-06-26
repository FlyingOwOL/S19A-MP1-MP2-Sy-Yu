import java.time.LocalDate; /*  This import is necessary for handling dates in the Entry class. 
It is the current date (year, month, day). */
import java.time.LocalTime; /*  This import is necessary for handling time in the Entry class. 
It is the current time (hour, minute, second, nanosecond).*/

    /**
     * This class epresents a calendar entry containing essential details such as
     * ID, title, description, date, and time range.
     */
public class Entry {

    private int entryID;            // Unique id for this entry
    private String title;           // Title or name of the entry
    private String details;         // Additional details or description of the entry
    private LocalDate date;         // The specific date of the entry
    private LocalTime startTime;    // Start time of the entry
    private LocalTime endTime;      // End time of the entry

    /**
     * Constructor to initialize all fields of the Entry.
     * 
     * @param entryID    Unique ID of the entry.
     * @param title      Title of the entry.
     * @param details    Additional details about the entry.
     * @param date       The specific date of the entry.
     * @param startTime  Start time of the entry.
     * @param endTime    End time of the entry.
     */
    public Entry(int entryID, String title, String details, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.entryID = entryID;
        this.title = title;
        this.details = details;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Gets the entry's unique ID.
    public int getEntryID() {
        return entryID;
    }

    // Gets the entry's title.
    public String getTitle() {
        return title;
    }

    // Gets the entry's details or description.
    public String getDetails() {
        return details;
    }

    // Gets the entry's date.
    public LocalDate getDate() {
        return date;
    }

    // Gets the entry's start and end times.
    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the entry's unique ID.
     * @param entryID New ID to assign to the entry.
     */
    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    /**
     * Sets the entry's title. Only updates if the new title is not empty.
     * 
     * @param title New title for the entry.
     * @return true if successfully updated, false if input is null.
     */
    public boolean setTitle(String title) {
        if (title != null) {
            this.title = title;
            return true;
        }
        return false;
    }

    /**
     * Sets the entry's details. Always successful.
     * 
     * @param details New details for the entry.
     * @return true (always returns success)
     */
    public boolean setDetails(String details) {
        this.details = details;
        return true;
    }

    /**
     * Sets the entry's date. Only updates if the new date is not empty.
     * 
     *  @param date New date for the entry.
     *  @return true if successfully updated, false if input is null.
     */
    public boolean setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
            return true;
        }
        return false;
    }

    /**
     * This sets the entry's start time. 
     * The start time must be before the end time if the end time is already set.
     * 
     * @param startTime New start time.
     * @return true if successfully updated, false if invalid.
     */
    public boolean setStartTime(LocalTime startTime) {
        // Ensure startTime is not null and is before endTime if endTime is set
        if (startTime != null && (endTime == null || startTime.isBefore(endTime))) {
            this.startTime = startTime;
            return true;
        }
        return false;
    }

    /**
     * This sets the entry's end time. The end time must be after the start time if the start time is already set.
     * 
     * @param endTime New end time.
     * @return true if successfully updated, false if invalid.
     */
    public boolean setEndTime(LocalTime endTime) {
        // Ensure endTime is not null and is after startTime if startTime is set
        if (endTime != null && (startTime == null || endTime.isAfter(startTime))) {
            this.endTime = endTime;
            return true;
        }
        return false;
    }

    // This is just a formatting method to display the entry information in a readable format.
    @Override
    public String toString() {
        return String.format("Entry: %s [%s - %s]: %s", date, startTime, endTime, title);
    }
}
