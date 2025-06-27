import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // This list stores all active accounts.
    public static ArrayList<Account> activeAccounts = new ArrayList<>();

    // This list stores all deactivated accounts.
    public static ArrayList<Account> deactivatedAccounts = new ArrayList<>();

    // This list stores all public calendars from all users.
    public static ArrayList<Calendar> publicCalendars = new ArrayList<>();

    // This variable tracks the currently logged in user.
    public static Account currentLoggedInAccount = null;

    /**
     * This method adds a calendar to the publicCalendars list if it is public and not already added.
     * @param calendar The calendar to be added to the public list.
     */
    public static void addToPublicCalendars(Calendar calendar) {
        if (calendar != null && calendar.isPubliclyAvailable() && !publicCalendars.contains(calendar)) {
            publicCalendars.add(calendar);
        }
    }

    /**
     * This method handles account creation and automatically opens the user menu after successful creation.
     * @param userInput Scanner to get user input.
     */
    private static void createAccount(Scanner userInput) {
        Account newAccount = MainMethods.createAccount(userInput, activeAccounts);
        if (newAccount != null) {
            activeAccounts.add(newAccount);
            System.out.println("Account created successfully!\n");

            // Automatically go to the User Menu after creating an account.
            currentLoggedInAccount = newAccount;
            UserMenu.userMenu(userInput, newAccount);
        } else {
            System.out.println("Account creation failed.\n");
        }
    }

    /**
     * This is the main method where the program starts.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int menuChoice = -1;

        do {
            MainMethods.displayMenu();

            if (userInput.hasNextInt()) { // Check if input is an integer
                menuChoice = userInput.nextInt();

                if (menuChoice == 1) {
                    // This creates a new account and goes directly to the user menu.
                    createAccount(userInput);
                } else if (menuChoice == 2) {
                    // This logs in to an existing account if there are active accounts.
                    if (activeAccounts.size() > 0) {
                        Account loggedInAccount = MainMethods.login(userInput, activeAccounts);
                        if (loggedInAccount != null) {
                            System.out.println("Welcome, " + loggedInAccount.getAccountName() + "!");
                            currentLoggedInAccount = loggedInAccount;
                            UserMenu.userMenu(userInput, loggedInAccount);
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                    } else {
                        System.out.println("No accounts available. Please create an account first.");
                    }
                } else if (menuChoice == 3) {
                    // This logs out the currently logged in user if there is one.
                    if (currentLoggedInAccount != null) {
                        System.out.println("User " + currentLoggedInAccount.getAccountName() + " logged out successfully.\n");
                        currentLoggedInAccount = null;
                    } else {
                        System.out.println("No user is currently logged in.\n");
                    }
                } else if (menuChoice != 0) {
                    System.out.println("\nInvalid choice. Please try again.\n");
                }

            } else {
                // If input is not an integer, display error and discard it
                System.out.println("\nInvalid input. Please enter a number.\n");
                userInput.nextLine();
                menuChoice = -1; // This ensures the loop continues.
            }

        } while (menuChoice != 0);

        System.out.println("Thank you for using the Calendar Application!");
        userInput.close();
    }
}
