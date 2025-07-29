package Models.Calendar;            

import Models.Account.AccountModel; 

/**
 * Represents a normal calendar in the calendar system.
 * 
 * A normal calendar is publicly available and can be accessed by anyone.
 * It extends CalendarParentModel to inherit common calendar functionalities.
 */
public class Normal extends CalendarParentModel {

    private final boolean isPubliclyAvailable = true;   // Normal calendar is publicly available

    /**
     * Constructs a new Normal calendar with the specified name and owner.
     * Initializes the name and owner using the superclass constructor.
     * 
     * @param name  the name of the normal calendar
     * @param owner the account that owns the normal calendar
     */
    public Normal(String name, AccountModel owner) {
        super(name, owner);
    }

    /**
     * Returns the availability status of the normal calendar.
     * 
     * @return true since normal calendars are publicly available
     */
    public boolean getAvailability() {
        return isPubliclyAvailable;
    }
}