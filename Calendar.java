import java.util.ArrayList;

public class Calendar {
    private String calendarName;
    private boolean isPubliclyAvailable;
    private Account owner; // Track who created the calendar
    private ArrayList<Entry> calendarEntries;

    /**
     * Constructor: Initializes the calendar with a name, availability, and owner.
     */
    public Calendar(String calendarName, boolean isPubliclyAvailable, Account owner) {
        this.calendarName = calendarName;
        this.isPubliclyAvailable = isPubliclyAvailable;
        this.owner = owner;
        this.calendarEntries = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return calendarName;
    }

    public boolean isPubliclyAvailable() {
        return isPubliclyAvailable;
    }

    public Account getOwner() {
        return owner;
    }

    public ArrayList<Entry> getCalendarEntries() {
        return calendarEntries;
    }

    /**
     * Adds an entry to the calendar if it does not already exist.
     */
    public boolean addEntry(Entry entry) {
        if (entry != null && !calendarEntries.contains(entry)) {
            calendarEntries.add(entry);
            return true;
        }
        return false;
    }

    /**
     * Deletes an entry from the calendar.
     */
    public boolean deleteEntry(Entry entry) {
        if (entry != null && calendarEntries.contains(entry)) {
            calendarEntries.remove(entry);
            return true;
        }
        return false;
    }

    /**
     * Edits an existing entry in the calendar by matching entryID.
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
