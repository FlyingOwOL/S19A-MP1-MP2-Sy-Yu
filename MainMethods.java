import java.util.Scanner;
import java.util.ArrayList;

public class MainMethods {
    public static void displayMenu(){
        System.out.println("Welcome to the Calendar Application!");
        System.out.println("Please choose an option:");
        System.out.println("1. create a new account");
        if (Main.activeAccounts.size() > 0)
            System.out.println("2. login to account");
        System.out.println("0. exit the application");
        System.out.print("Enter your choice: ");
    }


    public static Accounts createAccount(Scanner userInput, ArrayList<Accounts> accountsList){
        Accounts newAccount = null;
        boolean isValid = false;
        userInput.nextLine(); // Consume the newline character
        String accountName = "";
        do{
            System.out.println("Enter your account name: ");
            accountName = userInput.nextLine();
            for (Accounts account : accountsList){
                if (account.getAccountName().equalsIgnoreCase(accountName)){
                    System.out.println("Account name already exists. Please choose a different name.");
                } else {
                    isValid = true;
                }
            }
        } while (!isValid);
        System.out.println("Enter your account password:");
        String accountPassword = userInput.nextLine();
        newAccount = new Accounts(accountName, accountPassword);
        return newAccount;
    }
}
