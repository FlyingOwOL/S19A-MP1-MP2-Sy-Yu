package Controllers;

import Models.Account.AccountModel;
import Views.AccountLoginPage;
import Views.AccountPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private AccountLoginPage accountLoginPage;
    public LoginController() {
        this.accountLoginPage = new AccountLoginPage();
        this.accountLoginPage.addLoginButtonListener(new LoginListener());
        this.accountLoginPage.addCreateAccountButtonListener(new LoginListener());
    }
    class LoginListener implements ActionListener {
        String enteredName;
        String enteredPassword;        
        public void actionPerformed(ActionEvent e){
            enteredName = accountLoginPage.getUsername();
            enteredPassword = accountLoginPage.getPassword(); 
            if (enteredName.isEmpty() ||
                enteredPassword.isEmpty()) { //we can't have a user without a password
                System.out.println("Please enter username and password.");
            } else {
                if (e.getSource() == accountLoginPage.getLoginButton()){
                    AccountModel foundAccount = MainController.getAccountByName(enteredName);
                    if (foundAccount == null) {
                        // Show error message if account not found
                        System.out.println("Account not found");
                    } else {
                        AccountPage newAccountPage = new AccountPage(foundAccount);
                        foundAccount.setAccountPage(newAccountPage);
                        MainController.setupFeatureControllers(foundAccount.getAccountPage());

                        accountLoginPage.dispose(); // Close the login page
                    }

                } else if (e.getSource() == accountLoginPage.getCreateAccountButton()) {
                    boolean isTaken = MainController.accountExists(enteredName);
                    if (!isTaken) {
                        AccountModel newAccount = new AccountModel(enteredName, enteredPassword);
                        AccountPage newAccountPage = new AccountPage(newAccount);
                        newAccount.setAccountPage(newAccountPage);
                        MainController.setupFeatureControllers(newAccount.getAccountPage());
                        MainController.accounts.add(newAccount);
                         
                        accountLoginPage.dispose(); // Close the login page

                        System.out.println("Account created successfully.");
                    } else {
                        System.out.println("Account name is already taken.");
                    }
                }
            }
        }
    }
}
