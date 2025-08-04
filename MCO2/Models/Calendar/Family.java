package Models.Calendar;                

import Models.Account.AccountModel;     

/**
 * Represents a family calendar in the calendar system.
 * 
 * A family calendar is publicly available and can be accessed by anyone with the access code.
 * It extends CalendarParentModel to inherit common calendar functionalities.
 */
public class Family extends CalendarParentModel {

    private final boolean isPubliclyAvailable = true;   // Indicates that the family calendar is publicly available
    private String accessCode;                          // The access code for the family calendar

    /**
     * Constructs a new Family calendar with the specified name, owner, and access code.
     * Initializes the name and owner using the superclass constructor.
     * 
     * @param name        the name of the family calendar
     * @param owner       the account that owns the family calendar
     * @param accessCode  the access code for the family calendar
     */
    public Family(String name, AccountModel owner, String accessCode) {
        super(name, owner);
        this.accessCode = accessCode;
    }

    /**
     * Returns the availability status of the family calendar.
     * 
     * @return true since family calendars are publicly available
     */
    public boolean getAvailability() {
        return isPubliclyAvailable;
    }

    /**
     * Returns the access code of the family calendar.
     * 
     * @return the access code of the calendar
     */
    public String getCode() {
        return accessCode;
    }

    /**
     * Sets a new access code for the family calendar.
     * 
     * @param newAccessCode the new access code to be set
     */
    public void setCode(String newAccessCode){
        this.accessCode = newAccessCode;
    }
}