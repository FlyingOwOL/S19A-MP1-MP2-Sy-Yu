package Controllers;

import Views.AccountPage;
import Controllers.Listeners_Controllers.AccountSelectionListener;

public class AccountSelectionController {
    private AccountPage accountPage;
    
    public AccountSelectionController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.accountPage.changeAccountSelection(new AccountSelectionListener(accountPage));
    }
}
