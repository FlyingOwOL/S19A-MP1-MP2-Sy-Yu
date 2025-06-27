import java.util.ArrayList;

/**
 * This represents a user account in the calendar application.
 * Each account has a username, password, an activity status, and a list of calendars.
 */
public class Account {

    private String accountName;             // The username of the account (must be unique and case-insensitive)
    private String accountPassword;         // The password for the account
    private boolean isActive;               // This tracks whether the account is active or deactivated
    private ArrayList<Calendar> calendars;  // Stores the list of calendars belonging to this account

   
    /**
     * This constructs a new Account with a default private calendar.
     * @param accountName The username for the account.
     * @param accountPassword The password for the account.
     */
    public Account(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.isActive = true;
        this.calendars = new ArrayList<>();

        // This automatically creates and adds the default private calendar to the account.
        Calendar defaultCalendar = new Calendar(accountName, false, this);
        calendars.add(defaultCalendar);
    }

    
    /**
     * This gets the username of this account.
     * @return The account name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * This gets the password of this account.
     * @return The account password.
     */
    public String getAccountPassword() {
        return accountPassword;
    }

    /**
     * This gets the list of calendars associated with this account.
     * @return ArrayList of the user's calendars.
     */
    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }

    /**
     * This gets the default private calendar of the account.
     * @return The default calendar (first calendar in the list).
     */
    public Calendar getDefaultCalendar() {
    return calendars.get(0);
    }

    /**
     * This checks if the account is active.
     * @return True if the account is active; false if deactivated.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * This method deactivates the account.
     * It's called when the user chooses to delete or deactivate their account.
     */
    public void deactivateAccount() {
        isActive = false;
    }

    /**
     * This authenticates the user by comparing the provided password with the stored password.
     * @param password The password input from the user.
     * @return True if the password matches; false otherwise.
     */
    public boolean authenticate(String password) {
        return this.accountPassword.equals(password);
    }

    /**
     * This adds a calendar to this account's list if it's not already present.
     * @param calendar The calendar to add.
     * @return True if the calendar was added successfully; false if the calendar is null or already exists.
     */
    public boolean addCalendar(Calendar calendar) {
        if (calendar != null && !calendars.contains(calendar)) {
            calendars.add(calendar);
            return true;
        }
        return false;
    }

    /**
     * Removes a calendar from this account's list.
     * The calendar must not be the default calendar and the current user must be its owner.
     * @param calendar The calendar to remove.
     * @return True if successfully removed; false if conditions are not met.
     */
    public boolean removeCalendar(Calendar calendar) {
        if (calendar != null && calendar.getOwner() == this && !calendar.getName().equals(accountName)) {
            calendars.remove(calendar);
            return true;
        }
        return false;
    }
}
