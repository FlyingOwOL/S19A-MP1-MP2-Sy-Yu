import java.util.ArrayList;

public class Account {

    private String accountName;
    private String accountPassword;
    private boolean isActive;
    private ArrayList<Calendar> calendars;

   
    public Account(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.isActive = true;
        this.calendars = new ArrayList<>();

        Calendar defaultCalendar = new Calendar(accountName, false, this);
        calendars.add(defaultCalendar);
    }

    // Getters
    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public boolean isActive() {
        return isActive;
    }

    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }

    public Calendar getDefaultCalendar() {
    return calendars.get(0);
    }


    /**
     * Marks the account as inactive (used when deleting an account).
     */
    public void deactivateAccount() {
        isActive = false;
    }

    /**
     * Authenticates the account using the provided password.
     */
    public boolean authenticate(String password) {
        return this.accountPassword.equals(password);
    }

    /**
     * Adds a calendar to this account's list if not already present.
     */
    public boolean addCalendar(Calendar calendar) {
        if (calendar != null && !calendars.contains(calendar)) {
            calendars.add(calendar);
            return true;
        }
        return false;
    }

    /**
     * Removes a calendar from this account's list if the account is the owner
     * and the calendar is not the default private calendar.
     */
    public boolean removeCalendar(Calendar calendar) {
        if (calendar != null && calendar.getOwner() == this && !calendar.getName().equals(accountName)) {
            calendars.remove(calendar);
            return true;
        }
        return false;
    }
}
