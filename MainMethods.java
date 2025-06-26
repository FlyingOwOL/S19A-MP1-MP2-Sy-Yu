import java.util.ArrayList;
import java.util.Scanner;

public class MainMethods {

    public static void displayMenu() {
        System.out.println("Welcome to the Calendar Application!");
        System.out.println("Please choose an option:");
        System.out.println("1. Create a new account");
        if (Main.activeAccounts.size() > 0)
            System.out.println("2. Login to account");
        System.out.println("0. Exit the application");
        System.out.print("Enter your choice: ");
    }

    public static Account createAccount(Scanner userInput, ArrayList<Account> accountsList) {
        Account newAccount = null;
        boolean isValid;

        userInput.nextLine(); // Consume newline

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

        System.out.println("Enter your account password:");
        String accountPassword = userInput.nextLine();

        newAccount = new Account(accountName, accountPassword);
        return newAccount;
    }


     public static Account login(Scanner userInput, ArrayList<Account> accountsList) {
        userInput.nextLine(); // Consume newline
        System.out.println("Enter your account name: ");
        String accountName = userInput.nextLine();
        System.out.println("Enter your password: ");
        String accountPassword = userInput.nextLine();

        for (Account account : accountsList) {
            if (account.getAccountName().equalsIgnoreCase(accountName)) {
                if (account.authenticate(accountPassword)) {
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

