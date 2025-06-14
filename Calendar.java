import java.util.ArrayList;

public class Calendar {
    private String calendarName;
    private int calendarYear;
    private int numberOfMonths;
    private boolean isPubliclyAvailable;
    private ArrayList<Entry> calendarEntry;
    private Month [] months;


    /**
     * Constructor to create a Calendar object.
     */
    public Calendar (String calendarName, boolean isPubliclyAvailable, int calendarYear){
        this.calendarName = calendarName;
        this.isPubliclyAvailable = isPubliclyAvailable;
        this.calendarYear = calendarYear;
        this.numberOfMonths = FixedValues.NO_OF_MONTHS;
        this.calendarEntry = new ArrayList<>();
        this.months = new Month[FixedValues.NO_OF_MONTHS];
    }

    // Getters
    public String getCalendarName() {
        return calendarName + " " + calendarYear;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public Month getMonth (String name){
        Month monthToReturn = null;
        for (Month month : months) {
            if (month != null &&
                month.getMonthName().equalsIgnoreCase(name)) {
                monthToReturn = month;
            }
        }
        return monthToReturn;
    }

    public boolean getAvailability() {
        return isPubliclyAvailable;
    }

    public boolean addEntry (Entry entry) {
        boolean isSuccessful = false;
        if (entry != null) {
            calendarEntry.add(entry);
            isSuccessful = true;
        }
        return isSuccessful;
    }

    public boolean deleteEntry (Entry entry) {
        boolean isSuccessful = false;
        if (entry != null && calendarEntry.contains(entry)) {
            calendarEntry.remove(entry);
            isSuccessful = true;
        }
        return isSuccessful;
    }

    public boolean editEntry (int entryID, Entry newEntry) {
        boolean isSuccessful = false;
        for (int i = 0; i < calendarEntry.size(); i++) {
            if (calendarEntry.get(i).getEntryID() == entryID) {
                calendarEntry.set(i, newEntry);
                isSuccessful = true;
                i = calendarEntry.size(); // Exit loop after finding the entry
            }
        }
        return isSuccessful;
    }
}
