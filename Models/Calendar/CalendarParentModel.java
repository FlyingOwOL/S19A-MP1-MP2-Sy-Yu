package Models.Calendar;

import Models.Account.AccountModel;
import Models.Entry.EntryModel;

import java.util.ArrayList;

/**
 * Represents a parent calendar model that holds entries such as events, tasks, etc.
 * Each calendar has a name, an owner (account), and a list of entries.
 * This class serves as a base for specialized calendars (e.g., Personal, Shared).
 */
public class CalendarParentModel {

    private String name;                                // The name of the calendar
    private AccountModel owner;                         // The owner of the calendar 
    private ArrayList<EntryModel> entries;              // A list of entries in the calendar

    /**
     * Constructs a new CalendarParentModel with the given name and owner.
     * Initializes an empty list of entries.
     *
     * @param name  the name of the calendar
     * @param owner the account that owns this calendar
     */
    public CalendarParentModel(String name, AccountModel owner) {
        this.entries = new ArrayList<EntryModel>();
        this.name = name;
        this.owner = owner;
    }

    /**
     * Returns the name of the calendar.
     *
     * @return the calendar's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the owner of the calendar.
     *
     * @return the account that owns the calendar
     */
    public AccountModel getOwner() {
        return owner;
    }

    /**
     * Returns the list of all entries in the calendar.
     *
     * @return an ArrayList of EntryModel objects
     */
    public ArrayList<EntryModel> getEntries() {
        return entries;
    }

    /**
     * Returns this calendar instance.
     * May be used by child classes for specific behavior.
     *
     * @return this CalendarParentModel instance
     */
    public CalendarParentModel getCalendar() {
        return this;
    }

    /**
     * Sets the name of the calendar.
     *
     * @param name the new name of the calendar
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a new entry to the calendar.
     *
     * @param entry the entry to be added
     */
    public void addEntry(EntryModel entry) {
        entries.add(entry);
    }

    /**
     * Replaces an existing entry in the calendar with a new one.
     * If the old entry is not found, no changes are made.
     *
     * @param newEntry the updated entry
     * @param oldEntry the entry to be replaced
     */
    public void editEntry(EntryModel newEntry, EntryModel oldEntry) {
        int index = entries.indexOf(oldEntry);
        if (index != -1) {
            entries.set(index, newEntry);
            System.out.println("Entry updated successfully.");
        } else {
            System.out.println("Entry not found.");
        }
    }

    /**
     * Removes a specified entry from the calendar.
     *
     * @param entry the entry to be removed
     */
    public void deleteEntry(EntryModel entry) {
        entries.remove(entry);
    }
}