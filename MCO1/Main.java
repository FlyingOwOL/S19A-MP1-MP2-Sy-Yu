import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // This list stores all active accounts.
    public ArrayList<Account> activeAccounts = new ArrayList<>();

    // This list stores all deactivated accounts.
    public ArrayList<Account> deactivatedAccounts = new ArrayList<>();

    // This list stores all public calendars from all users.
    public ArrayList<Calendar> publicCalendars = new ArrayList<>();

    // This tracks the currently logged in user.
    public Account currentLoggedInAccount = null;

    // These are the objects of other classes that will be shared across the application.
    private CalendarManager calendarManager = new CalendarManager();
    private EntryManager entryManager = new EntryManager(calendarManager);
    private MainMethods mainMethods = new MainMethods(); // Reused instance

    /**
     * This method adds a calendar to the publicCalendars list if it is public and not already added.
     * @param calendar The calendar to be added to the public list.
     */
    public void addToPublicCalendars(Calendar calendar) {
        if (calendar != null && calendar.isPubliclyAvailable() && !publicCalendars.contains(calendar)) {
            publicCalendars.add(calendar);
        }
    }

    /**
     * This getter returns the list of active accounts.
     */
    public ArrayList<Account> getActiveAccounts() {
        return activeAccounts;
    }

    /**
     * This getter returns the list of deactivated accounts.
     */
    public ArrayList<Account> getDeactivatedAccounts() {
        return deactivatedAccounts;
    }

    public void deactivateAccount(Account account) {
        account.deactivateAccount(); // This marks the account as inactive.
        activeAccounts.remove(account); // This removes the account from the active accounts list.
        deactivatedAccounts.add(account); // This adds the account to the deactivated accounts list.
    }

    /**
     * This getter returns the list of public calendars.
     */
    public ArrayList<Calendar> getPublicCalendars() {
        return publicCalendars;
    }

    /**
     * This method handles account creation and automatically opens the user menu after successful creation.
     * @param userInput Scanner to get user input.
     */
    private void createAccount(Scanner userInput) {
        Account newAccount = mainMethods.createAccount(userInput, activeAccounts);
        if (newAccount != null) {
            activeAccounts.add(newAccount);
            System.out.println("Account created successfully!\n");

            // Automatically go to the User Menu after creating an account.
            currentLoggedInAccount = newAccount;
            UserMenu userMenu = new UserMenu(calendarManager, entryManager, this);
            userMenu.userMenu(userInput, newAccount);
        } else {
            System.out.println("Account creation failed.\n");
        }
    }

    /**
     * This is the main application program.
     */
    public void runApp() {
        Scanner userInput = new Scanner(System.in);
        int menuChoice = -1;

        do {
            mainMethods.displayMenu(activeAccounts);

            if (userInput.hasNextInt()) {
                menuChoice = userInput.nextInt();
                userInput.nextLine(); 

                if (menuChoice == 1) {
                    createAccount(userInput);
                } else if (menuChoice == 2) {
                    if (activeAccounts.size() > 0) {
                        Account loggedInAccount = mainMethods.login(userInput, activeAccounts);
                        if (loggedInAccount != null) {
                            System.out.println("Welcome, " + loggedInAccount.getAccountName() + "!");
                            currentLoggedInAccount = loggedInAccount;

                            // Use shared managers
                            UserMenu userMenu = new UserMenu(calendarManager, entryManager, this);
                            userMenu.userMenu(userInput, loggedInAccount);
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                    } else {
                        System.out.println("No accounts available. Please create an account first.");
                    }
                } else if (menuChoice == 3) {
                    // This logs out the currently logged in user if there is one.
                    if (currentLoggedInAccount != null) {
                        System.out.println("User  " + currentLoggedInAccount.getAccountName() + " logged out successfully.\n");
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
                userInput.nextLine(); // clear bad input
                menuChoice = -1;
            }

        } while (menuChoice != 0);

        System.out.println("Thank you for using the Calendar Application!");
        userInput.close();
    }

    /**
     * This is the main method where the program starts.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Main app = new Main();       
        app.runApp();
    }
}
