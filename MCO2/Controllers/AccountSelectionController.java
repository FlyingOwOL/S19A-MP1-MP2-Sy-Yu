package Controllers;

import Controllers.Listeners_Controllers.AccountSelectionListener;
import Views.AccountPage;

/**
 * Controller responsible for managing account selection interactions
 * on the AccountPage view.
 * 
 * It attaches the AccountSelectionListener to handle user input related
 * to selecting different accounts.
 */
public class AccountSelectionController {
    private AccountPage accountPage;    // The page where account selection is managed
    
    /**
     * Constructs the AccountSelectionController and sets up the account selection listener.
     * 
     * @param accountPage The view representing the user's account page.
     */
    public AccountSelectionController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.accountPage.changeAccountSelection(new AccountSelectionListener(accountPage));
    }
}