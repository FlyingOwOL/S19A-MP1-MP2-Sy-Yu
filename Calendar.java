import java.util.ArrayList;

public class Calendar {
    private String calendarName;
    private int calendarYear;
    private int numberOfMonths;
    private boolean isPubliclyAvailable;
    private ArrayList<Entry> calendarEntry;
    private Month [] months;

    public Calendar (String calendarName, boolean isPubliclyAvailable, int calendarYear){
        this.calendarName = calendarName;
        this.isPubliclyAvailable = isPubliclyAvailable;
        this.calendarYear = calendarYear;
        this.numberOfMonths = FixedValues.NO_OF_MONTHS;
        this.calendarEntry = new ArrayList<>();
        this.months = new Month[FixedValues.NO_OF_MONTHS];
    }


    public String getCalendarName() {
        return calendarName;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public Month getMonth (String name){
        return null;
    }

    public boolean getAvailability() {
        return isPubliclyAvailable;
    }

    public boolean addEntry (Entry entry) {
        return false;
    }

    public boolean deleteEntry (Entry entry) {
        return false;
    }

    public boolean editEntry (int entryID, Entry newEntry) {
        return false;
    }

}
