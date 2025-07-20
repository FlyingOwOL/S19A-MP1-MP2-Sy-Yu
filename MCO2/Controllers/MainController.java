package Controllers;

// Models import
import Models.Account.AccountModel;
import Models.Calendar.*;
import Views.AccountPage;

// Views import

// Other imports
import java.util.ArrayList;

public class MainController {
    public static ArrayList<AccountModel> accounts = new ArrayList<>();
    public static ArrayList<CalendarParentModel> publicCalendars = new ArrayList<>(); // includes Normal and Family calendars only

    public MainController() {
        new LoginController();
    }

    public static boolean accountExists(String name) {
        boolean isTaken = false;
        for (AccountModel account : accounts) {
            if (account.getName().equals(name)) {
                isTaken = true;
            }
        }
        return isTaken;
    }

    public static AccountModel getAccountByName(String name) {
        AccountModel foundAccount = null;
        for (AccountModel account : accounts) {
            if (account.getName().equals(name)) {
                foundAccount = account;
            }
        }
        return foundAccount; // Return null if no account found
    }

    public static void setupFeatureControllers(AccountPage accountPage) {
            new EntriesPopUpsController(accountPage);
            new CalendarDisplayController(accountPage);
            new CalendarDateController(accountPage);
            new AccountSelectionController(accountPage);
            // Other feature controllers
    }
}
