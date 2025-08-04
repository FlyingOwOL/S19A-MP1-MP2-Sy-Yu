package Controllers;

// Models import
import Models.Account.AccountModel;
import Models.Calendar.*;
import Views.AccountPage;
import java.util.ArrayList;

/**
 * This class serves as the main controller for the application,
 * managing the overall flow and interactions between models and views.
 */
public class MainController {
    // Static lists to hold accounts and public calendars
    public static ArrayList<AccountModel> accounts = new ArrayList<>();
    public static ArrayList<CalendarParentModel> publicCalendars = new ArrayList<>(); // includes Normal and Family calendars only

    /**
     * Main controller for the application.
     * Initializes the login controller to start the application.
     */
    public MainController() {
        new LoginController();
    }

    /**
     * Adds a new account to the list of accounts.
     * 
     * @param account The AccountModel to be added.
     */
    public static boolean accountExists(String name) {
        boolean isTaken = false;
        for (AccountModel account : accounts) {
            if (account.getName().toUpperCase().equals(name.toUpperCase())) { //disregards case sensitivity
                isTaken = true;
            }
        }
        return isTaken;
    }

    /**
     * Returns the AccountModel associated with the given name.
     * If no account is found, returns null.
     *
     * @param name the name of the account to find
     * @return the AccountModel if found; null otherwise
     */
    public static AccountModel getAccountByName(String name) {
        AccountModel foundAccount = null;
        for (AccountModel account : accounts) {
            if (account.getName().equals(name)) {
                foundAccount = account;
            }
        }
        return foundAccount; // Return null if no account found
    }

    /**
     * Sets up the feature controllers for the AccountPage.
     * Initializes controllers for entries, calendar display, date navigation, and account selection.
     *
     * @param accountPage the AccountPage view to be managed by these controllers
     */
    public static void setupFeatureControllers(AccountPage accountPage) {
        // NOTE: EntriesPopUpsController is no longer needed since we use direct connection in AccountPage
        // new EntriesPopUpsController(accountPage); // REMOVED - now using direct connection
        new CalendarDisplayController(accountPage);
        new CalendarDateController(accountPage);
        new AccountSelectionController(accountPage);
        // Other feature controllers
    }
}