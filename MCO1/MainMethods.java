import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the main system functions such as displaying menus,
 * creating accounts, and logging into accounts.
 */
public class MainMethods {

    /**
     * This method displays the main menu options to the user.
     * Options include account creation, login, and exiting the application.
     * Login will only be shown if there are existing accounts.
     * 
     * @param activeAccounts The list of active accounts used to determine whether to show login.
     */
    public void displayMenu(ArrayList<Account> activeAccounts) {
        System.out.println("Welcome to the Calendar Application!");
        System.out.println("Please choose an option:");
        System.out.println("1. Create a new account");

        // This displays the login option only if there are active accounts.
        if (activeAccounts.size() > 0)
            System.out.println("2. Login to account");
        System.out.println("0. Exit the application");
        System.out.print("Enter your choice: ");
    }

    /**
     * This method handles the process of creating a new user account.
     * The account name must be unique (case-insensitive).
     * @param userInput This is the Scanner used to read user input.
     * @param accountsList This is the list of existing active accounts.
     * @return A newly created Account object.
     */
    public Account createAccount(Scanner userInput, ArrayList<Account> accountsList) {
        Account newAccount = null;
        boolean isValid;

        String accountName;
        do {
            isValid = true; // Assume valid unless found duplicate
            System.out.println("Enter your account name: ");
            accountName = userInput.nextLine();

            for (Account account : accountsList) {
                if (account.getAccountName().equalsIgnoreCase(accountName)) {
                    System.out.println("Account name already exists. Please choose a different name.");
                    isValid = false; // Set flag to re-loop
                }
            }

        } while (!isValid);

        // This prompts the user to enter a password after providing a unique name.
        System.out.println("Enter your account password:");
        String accountPassword = userInput.nextLine();

        newAccount = new Account(accountName, accountPassword);
        return newAccount;
    }

    /**
     * This method handles the process of logging into an existing account.
     * It checks the provided username and password against active accounts.
     * @param userInput This is the Scanner used to read user input.
     * @param accountsList This is the list of existing active accounts.
     * @return The Account object if login is successful, or null if it fails.
     */
    public Account login(Scanner userInput, ArrayList<Account> accountsList) {
        System.out.println("Enter your account name: ");
        String accountName = userInput.nextLine();
        System.out.println("Enter your password: ");
        String accountPassword = userInput.nextLine();

        // This searches for the account by name (case-insensitive).
        for (Account account : accountsList) {
            if (account.getAccountName().equalsIgnoreCase(accountName)) {
                // This checks if the password is correct.
                if (account.authenticate(accountPassword)) {
                    // This verifies if the account is active.
                    if (account.isActive()) {
                        return account;
                    } else {
                        System.out.println("This account is deactivated.");
                        return null;
                    }
                } else {
                    System.out.println("Incorrect password.");
                    return null;
                }
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}