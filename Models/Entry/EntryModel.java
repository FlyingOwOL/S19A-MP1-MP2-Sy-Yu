package Models.Entry;               

import java.time.LocalDate;         

/**
 * Represents a general calendar entry with a title, optional details, and a date.
 * 
 * This is the base class for other specific types of entries like Tasks, Events, Meetings, and Journals.
 * Each entry must have a title and date, while details are optional.
 */
public class EntryModel {
    private String title;           // The title of the entry
    private String details;         // Optional details about the entry
    private LocalDate date;         // The date of the entry

    /**
     * Constructs a new EntryModel with the specified title.
     * Initializes details as an empty string and the date as the current date.
     *
     * @param title the title of the entry
     */
    public EntryModel(String title){
        this.title = title;
        this.details = "";
        this.date = LocalDate.now();
    }

    /**
     * Returns the title of the entry.
     * 
     * @return the title of the entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the details of the entry.
     * 
     * @return the details of the entry
     */
    public String getDetails() {
        return details;
    }

    /**
     * Returns the date of the entry.
     * 
     * @return the date of the entry
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the title of the entry.
     * 
     * @param title the new title of the entry
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the details of the entry.
     * 
     * @param details the new details of the entry
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Sets the date of the entry.
     * 
     * @param date the new date of the entry
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns a string representation of the entry.
     * 
     * @return a formatted string with title, details, and date
     */
    @Override
    public String toString() {
        return "Title: " + title + "\nDetails: " + details + "\nDate: " + date;
    }
}