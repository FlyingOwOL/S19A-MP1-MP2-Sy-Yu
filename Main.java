import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Account> activeAccounts = new ArrayList<>();
    public static ArrayList<Account> deactivatedAccounts = new ArrayList<>();
    public static ArrayList<Calendar> publicCalendars = new ArrayList<>();

    public static void addToPublicCalendars(Calendar calendar) {
        if (calendar != null && calendar.isPubliclyAvailable() && !publicCalendars.contains(calendar)) {
            publicCalendars.add(calendar);
        }
    }

    private static void createAccount(Scanner userInput) {
        Account newAccount = MainMethods.createAccount(userInput, activeAccounts);
        if (newAccount != null) {
            activeAccounts.add(newAccount);
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account creation failed.");
        }
    }

 public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    int menuChoice = -1;

    do {
        MainMethods.displayMenu();

        if (userInput.hasNextInt()) { // ✅ Check if input is an integer
            menuChoice = userInput.nextInt();

            if (menuChoice == 1) {
                createAccount(userInput);
            } else if (menuChoice == 2) {
                if (activeAccounts.size() > 0) {
                    Account loggedInAccount = MainMethods.login(userInput, activeAccounts);
                    if (loggedInAccount != null) {
                        System.out.println("Welcome, " + loggedInAccount.getAccountName() + "!");
                        UserMenu.userMenu(userInput, loggedInAccount);
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                } else {
                    System.out.println("No accounts available. Please create an account first.");
                }
            } else if (menuChoice != 0) {
                System.out.println("Invalid choice. Please try again.");
            }

        } else {
            // If input is not an integer, display error and discard it
            System.out.println("Invalid input. Please enter a number.");
            userInput.nextLine(); // Consume the invalid input
            menuChoice = -1; // Stay in the loop
        }

    } while (menuChoice != 0);

    System.out.println("Thank you for using the Calendar Application!");
    userInput.close();
}
}
