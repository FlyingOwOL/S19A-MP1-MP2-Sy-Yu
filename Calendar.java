import java.util.ArrayList;

/**
 * This class represents a calendar that can contain multiple entries.
 * A calendar can be public or private and belongs to a specific account.
 */
public class Calendar {
    private String calendarName;                // This is the name of the calendar.        
    private boolean isPubliclyAvailable;        // This indicates whether the calendar is public or private.
    private Account owner;                      // This stores the owner of the calendar.
    private ArrayList<Entry> calendarEntries;   // This is the list of entries (appointments, tasks, events, etc.) in the calendar.

    /**
     * This constructor initializes the calendar with a name, availability status, and owner.
     * @param calendarName This is the name of the calendar.
     * @param isPubliclyAvailable This indicates if the calendar is public or private.
     * @param owner This is the account that created the calendar.
     */
    public Calendar(String calendarName, boolean isPubliclyAvailable, Account owner) {
        this.calendarName = calendarName;
        this.isPubliclyAvailable = isPubliclyAvailable;
        this.owner = owner;
        this.calendarEntries = new ArrayList<>();
    }

    /**
     * This gets the name of the calendar.
     * @return The calendar name.
     */
    public String getName() {
        return calendarName;
    }

    /**
     * This gets the owner of the calendar.
     * @return The account that owns the calendar.
     */
    public Account getOwner() {
        return owner;
    }

    /**
     * This gets the list of all entries in the calendar.
     * @return ArrayList of calendar entries.
     */
    public ArrayList<Entry> getCalendarEntries() {
        return calendarEntries;
    }

    /**
     * This gets the number of entries in the calendar.
     * @return The total number of entries.
     */
    public int getEntryCount() {
    return calendarEntries.size();
    }

    /**
     * This sets the public availability status of the calendar.
     * @param status True if the calendar should be public, false if private.
     */
    public void setPublicAvailability(boolean status) {
        this.isPubliclyAvailable = status;
    }

    /**
     * This checks if the calendar is publicly available.
     * @return True if the calendar is public, false if private.
     */
    public boolean isPubliclyAvailable() {
        return isPubliclyAvailable;
    }

    /**
     * This adds an entry to the calendar if it does not already exist.
     * @param entry The entry to be added.
     * @return True if the entry was added successfully, false otherwise.
     */
    public boolean addEntry(Entry entry) {
        if (entry != null && !calendarEntries.contains(entry)) {
            calendarEntries.add(entry);
            return true;
        }
        return false;
    }

    /**
     * This deletes an entry from the calendar if it exists.
     * @param entry The entry to be deleted.
     * @return True if the entry was successfully deleted, false otherwise.
     */
    public boolean deleteEntry(Entry entry) {
        if (entry != null && calendarEntries.contains(entry)) {
            calendarEntries.remove(entry);
            return true;
        }
        return false;
    }

    /**
     * This updates an existing entry in the calendar by searching for the entry ID.
     * @param entryID The ID of the entry to be edited.
     * @param newEntry The new entry to replace the old one.
     * @return True if the entry was successfully updated, false otherwise.
     */
    public boolean editEntry(int entryID, Entry newEntry) {
        for (int i = 0; i < calendarEntries.size(); i++) {
            if (calendarEntries.get(i).getEntryID() == entryID) {
                calendarEntries.set(i, newEntry);
                return true;
            }
        }
        return false;
    }
}
