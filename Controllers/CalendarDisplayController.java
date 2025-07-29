package Controllers;

import Views.AccountPage;
import Controllers.Listeners_Controllers.ChangeDisplayListener;

public class CalendarDisplayController {
    private final AccountPage accountPage;

    public CalendarDisplayController(AccountPage accountPage) {
        this.accountPage = accountPage;
        // Set the change display listener
        this.accountPage.changeCalendarDisplay(new ChangeDisplayListener(accountPage));
        
    }
}
