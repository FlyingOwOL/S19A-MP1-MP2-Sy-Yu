package Controllers;

import Controllers.Listeners_Controllers.ChangeDisplayListener;
import Views.AccountPage;

/**
 * Controller class responsible for managing the calendar display view (e.g., switching
 * between Month or Week views).
 * 
 * This class registers the appropriate listener for handling user actions related
 * to changing the calendar's display format.
 */
public class CalendarDisplayController {


    private final AccountPage accountPage;    // The page where the calendar is displayed

    /**
     * Constructs the CalendarDisplayController and sets up the display change listener.
     * 
     * @param accountPage The view representing the user's account page.
     */
    public CalendarDisplayController(AccountPage accountPage) {
        this.accountPage = accountPage;
        // Set the change display listener
        this.accountPage.changeCalendarDisplay(new ChangeDisplayListener(accountPage));
        
    }
}