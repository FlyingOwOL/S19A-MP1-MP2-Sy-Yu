package Controllers.Listeners_Controllers;

import Controllers.MainController;
import Models.Account.AccountModel;
import Views.AccountLoginPage;
import Views.AccountPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
    private AccountLoginPage accountLoginPage;

    public LoginListener(AccountLoginPage accountLoginPage) {
        this.accountLoginPage = accountLoginPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredName = accountLoginPage.getUsername();
        String enteredPassword = accountLoginPage.getPassword();

        if (enteredName.isEmpty() || enteredPassword.isEmpty()) {
            System.out.println("Please enter username and password.");
            return;
        }

        if (e.getSource() == accountLoginPage.getLoginButton()) {
            AccountModel foundAccount = MainController.getAccountByName(enteredName);
            if (foundAccount == null) {
                System.out.println("Account not found");
            } else {
                if (foundAccount.checkAuthority(enteredPassword)){
                    AccountPage newAccountPage = new AccountPage(foundAccount);
                    foundAccount.setAccountPage(newAccountPage);
                    MainController.setupFeatureControllers(newAccountPage);
                    accountLoginPage.dispose();                    
                } else {
                    System.out.println("Incorrect password. Please try again.");
                }

            }

        } else if (e.getSource() == accountLoginPage.getCreateAccountButton()) {
            boolean isTaken = MainController.accountExists(enteredName);
            if (!isTaken) {
                AccountModel newAccount = new AccountModel(enteredName, enteredPassword);
                AccountPage newAccountPage = new AccountPage(newAccount);
                newAccount.setAccountPage(newAccountPage);
                MainController.setupFeatureControllers(newAccountPage);
                MainController.accounts.add(newAccount);

                accountLoginPage.dispose();
                System.out.println("Account created successfully.");
            } else {
                System.out.println("Account name is already taken.");
            }
        }
    }
}
