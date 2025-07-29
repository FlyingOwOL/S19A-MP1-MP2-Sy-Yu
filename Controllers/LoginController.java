package Controllers;

import Controllers.Listeners_Controllers.LoginListener;
import Views.AccountLoginPage;

/**
 * Controller responsible for managing the login and account creation interactions
 * on the AccountLoginPage view.
 * 
 * It attaches the LoginListener to both the login and create account buttons
 * to handle user input and authentication-related actions.
 */
public class LoginController {
    private AccountLoginPage accountLoginPage;  // The page where users log in or create accounts

    /**
     * Constructs the LoginController and initializes the login page view.
     * 
     * Adds the LoginListener to both the login and create account buttons
     * to process user interactions on the login interface.
     */
    public LoginController() {
        this.accountLoginPage = new AccountLoginPage();

        LoginListener loginListener = new LoginListener(accountLoginPage);
        this.accountLoginPage.addLoginButtonListener(loginListener);
        this.accountLoginPage.addCreateAccountButtonListener(loginListener);
    }
}