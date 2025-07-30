package Models.Account;

import Models.Calendar.*;
import Views.AccountPage;

import java.util.ArrayList;

/**
 * Represents a user account within the calendar application.
 * Each account contains basic authentication info, a list of calendars,
 * a reference to the account page view, and an active status.
 */
public class AccountModel {

    private String name;                                // The username for the account owner 
    private String password;                            // The password of the account
    private ArrayList<CalendarParentModel> calendars;   // A list of calendars of the account
    private boolean activeStatus;                       // Indicates whether the account is currently active or deactivated
    private AccountPage accountPage;                    // A reference to the AccountPage GUI associated with this account

    /**
     * Constructs a new AccountModel with the specified name and password.
     * A default personal calendar is automatically created and added to the account.
     *
     * @param name     the name of the account owner
     * @param password the password of the account
     */
    public AccountModel(String name, String password) {
        this.name = name;
        this.password = password;
        this.calendars = new ArrayList<CalendarParentModel>();
        this.activeStatus = true;
        this.calendars.add(new Personal(this.name, this)); // personal calendar with same name and owner
    }

    /**
     * Returns the account owner's name.
     *
     * @return the account name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the account is currently active.
     *
     * @return true if the account is active, false otherwise
     */
    public boolean getActiveStatus() {
        return this.activeStatus;
    }

    /**
     * Retrieves the list of calendars owned by the account.
     *
     * @return an ArrayList of CalendarParentModel objects
     */
    public ArrayList<CalendarParentModel> getCalendars() {
        return this.calendars;
    }

    /**
     * Returns the AccountPage GUI associated with this account.
     *
     * @return the AccountPage instance
     */
    public AccountPage getAccountPage() {
        return this.accountPage;
    }

    /**
     * Sets the AccountPage view associated with this account.
     *
     * @param accountPage the AccountPage to associate
     */
    public void setAccountPage(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    /**
     * Checks if the provided password matches the account's password.
     *
     * @param password the password to verify
     * @return true if the password matches, false otherwise
     */
    public boolean checkAuthority(String password) {
        return this.password.equals(password);
    }

    /**
     * Deactivates the account by setting its status to inactive.
     */
    public void deactivateAccount() {
        this.activeStatus = false;
    }
}