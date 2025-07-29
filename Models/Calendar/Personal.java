package Models.Calendar;                

import Models.Account.AccountModel;     

/**
 * Represents a personal calendar in the calendar system.
 * 
 * A personal calendar is privately available and can only be accessed by the owner.
 * It extends CalendarParentModel to inherit common calendar functionalities.
 */
public class Personal extends CalendarParentModel {

    private final boolean isPubliclyAvailable = false;  // Indicates that the personal calendar is privately available

    /**
     * Constructs a new Personal calendar with the specified name and owner.
     * Initializes the name and owner using the superclass constructor.
     * 
     * @param name  the name of the personal calendar
     * @param owner the account that owns the personal calendar
     */ 
    public Personal(String name, AccountModel owner) {
        super(name, owner);
    }

    /**
     * Returns the availability status of the personal calendar.
     * 
     * @return false since personal calendars are privately available
     */
    public boolean getAvailability() {
        return isPubliclyAvailable;
    }
}