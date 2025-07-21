package Controllers;

import Views.AccountPage;
import Controllers.Listeners_Controllers.ChangeDisplayListener;

public class CalendarDisplayController {
    private AccountPage accountPage;

    public CalendarDisplayController(AccountPage accountPage){
        this.accountPage = accountPage;

        this.accountPage.changeCalendarDisplay(new ChangeDisplayListener(accountPage));
    }
}
