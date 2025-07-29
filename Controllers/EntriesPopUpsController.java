package Controllers;

import Views.AccountPage;
import Controllers.Listeners_Controllers.PopUpSelectionListener;

public class EntriesPopUpsController {
    private AccountPage accountPage;

    public EntriesPopUpsController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.accountPage.selectPopUps(new PopUpSelectionListener(accountPage));
    }
}
