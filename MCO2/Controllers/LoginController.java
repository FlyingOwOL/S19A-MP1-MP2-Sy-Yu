package Controllers;

import Controllers.Listeners_Controllers.LoginListener;
import Views.AccountLoginPage;

public class LoginController {
    private AccountLoginPage accountLoginPage;

    public LoginController() {
        this.accountLoginPage = new AccountLoginPage();

        LoginListener loginListener = new LoginListener(accountLoginPage);
        this.accountLoginPage.addLoginButtonListener(loginListener);
        this.accountLoginPage.addCreateAccountButtonListener(loginListener);
    }
}
