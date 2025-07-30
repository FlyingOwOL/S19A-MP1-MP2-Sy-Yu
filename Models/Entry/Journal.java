package Models.Entry;

/**
 * Represents a journal entry in the calendar system.
 * 
 * Inherits from EntryModel and adds fields specific to journal entries:
 * a custom details field and the month in which the journal was written.
 */
public class Journal extends EntryModel {

    private String details; // Custom journal details, overrides the one in EntryModel
    private String month;   // Month the journal entry is associated with

    /**
     * Constructs a Journal entry with a title, details, and month.
     *
     * @param title   the title of the journal entry
     * @param details the content/details of the journal
     * @param month   the month the journal is associated with
     */
    public Journal(String title, String details, String month) {
        super(title);
        this.details = details;
        this.month = month;
    }

    /**
     * Returns the details of the journal entry.
     * 
     * @return the journal's details
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * Returns the month associated with the journal entry.
     * 
     * @return the month of the journal
     */
    public String getMonth() {
        return this.month;
    }
}