package Controllers;

import Controllers.Listeners_Controllers.PopUpSelectionListener;
import Views.AccountPage;

/**
 * Controller responsible for handling the selection of entry pop-ups,
 * such as those for adding tasks, events, or journals.
 * 
 * It connects the corresponding listener to the AccountPage view
 * to manage user actions related to entry creation or selection.
 */
public class EntriesPopUpsController {
    private AccountPage accountPage;    // The page where entry pop-ups are displayed

    /**
     * Constructs an EntriesPopUpsController and registers the listener
     * for entry pop-up selections.
     * 
     * @param accountPage The AccountPage view where pop-ups can be triggered.
     */
    public EntriesPopUpsController(AccountPage accountPage) {
        this.accountPage = accountPage;

        // Attach listener to handle entry pop-up interactions
        this.accountPage.selectPopUps(new PopUpSelectionListener(accountPage));
    }
}