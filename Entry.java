import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a calendar entry with essential information such as date, title,
 * time range, and additional details.
 */
public class Entry {

    private int entryID;
    private String title;
    private String details;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor to create an Entry.
     */
    public Entry(int entryID, String title, String details, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.entryID = entryID;
        this.title = title;
        this.details = details;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters

    public int getEntryID() {
        return entryID;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    // Setters

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public boolean setTitle(String title) {
        if (title != null) {
            this.title = title;
            return true;
        }
        return false;
    }

    public boolean setDetails(String details) {
        this.details = details;
        return true;
    }

    public boolean setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
            return true;
        }
        return false;
    }

    public boolean setStartTime(LocalTime startTime) {
        // Ensure startTime is not null and is before endTime if endTime is set
        if (startTime != null && (endTime == null || startTime.isBefore(endTime))) {
            this.startTime = startTime;
            return true;
        }
        return false;
    }

    public boolean setEndTime(LocalTime endTime) {
        // Ensure endTime is not null and is after startTime if startTime is set
        if (endTime != null && (startTime == null || endTime.isAfter(startTime))) {
            this.endTime = endTime;
            return true;
        }
        return false;
    }

    // Just a formatting method to display the entry information in a readable format
    @Override
    public String toString() {
        return String.format("Entry: %s [%s - %s]: %s", date, startTime, endTime, title);
    }
}
