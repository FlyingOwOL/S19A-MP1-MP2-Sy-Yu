import java.time.YearMonth;
import java.util.Scanner;

/**
 * This class handles the user menu operations such as calendar management,
 * entry management, and account deactivation.
 */
public class UserMenu {

    private boolean logoutFlag = false; //  Logout flag 

    private CalendarManager calendarManager;   // Reference to CalendarManager for calendar operations
    private EntryManager entryManager;         // Reference to EntryManager for entry operations
    private Main mainApp;                      // Reference to the Main app for shared state (e.g., account lists)

    /**
     * This constructor sets up the user menu with necessary managers and app state.
     * @param calendarManager Reference to calendar manager.
     * @param entryManager Reference to entry manager.
     * @param mainApp Reference to the main application (for shared lists).
     */
    public UserMenu(CalendarManager calendarManager, EntryManager entryManager, Main mainApp) {
        this.calendarManager = calendarManager;
        this.entryManager = entryManager;
        this.mainApp = mainApp;
    }

    /**
     * This method displays the user menu after a successful login and allows the user
     * to navigate calendar and entry options.
     * @param userInput This is the Scanner used to read user input.
     * @param loggedInAccount This is the currently logged-in account.
     */
    public void userMenu(Scanner userInput, Account loggedInAccount) {
        int userChoice;
        YearMonth currentMonth = YearMonth.now();
        Calendar calendar = loggedInAccount.getDefaultCalendar();
        logoutFlag = false; // Reset flag on new login

        // This loop continues to display the user menu until the user logs out.
        do {
            MonthlyDisplay monthlyDisplay = new MonthlyDisplay();
            monthlyDisplay.displayMonthView(calendar, currentMonth);
            System.out.println("\nLogged in as: " + loggedInAccount.getAccountName());
            System.out.println("User Menu:");
            System.out.println("[1] View Calendars");
            System.out.println("[2] Add Calendar");
            System.out.println("[3] Delete Calendar");
            System.out.println("[4] Add Entry to Calendar");
            System.out.println("[5] Edit Entry in Calendar");
            System.out.println("[6] Delete Entry from Calendar");
            System.out.println("[7] Deactivate Account");
            System.out.println("[0] Logout");
            System.out.print("Enter your choice: ");

            if (userInput.hasNextInt()) {
                userChoice = userInput.nextInt();
                userInput.nextLine();
            } else {
                userInput.nextLine(); 
                userChoice = -1; // Stay in the loop
            }

            // This switch handles the user’s menu choice.
            switch (userChoice) {
                // This calls the method to view calendars.
                case 1:
                    if (calendarManager.viewCalendars(userInput, loggedInAccount)) {
                        logoutFlag = true; // Logout requested from CalendarManager
                        userChoice = 0;
                    }
                    break;
                // This calls the method to add a new calendar.
                case 2:
                calendarManager.addCalendar(userInput, loggedInAccount, mainApp.getPublicCalendars());
                break;
                // This calls the method to delete a calendar.
                case 3:
                    if (calendarManager.deleteCalendar(userInput, loggedInAccount, mainApp.getPublicCalendars(), mainApp.getActiveAccounts())) {
                        logoutFlag = true; // Logout requested from CalendarManager
                        userChoice = 0;
                    }
                    break;
                // This calls the method to add an entry to a calendar.
                case 4:
                    entryManager.addEntry(userInput, loggedInAccount);
                    break;
                // This calls the method to edit an entry in a calendar.
                case 5:
                    entryManager.editEntry(userInput, loggedInAccount);
                    break;
                // This calls the method to delete an entry from a calendar.
                case 6:
                    entryManager.deleteEntry(userInput, loggedInAccount);
                    break;
                // This calls the method to deactivate the logged-in account.
                case 7:
                    deactivateAccount(loggedInAccount);
                    userChoice = 0;
                    break;
                // This logs out the user.
                case 0:
                    System.out.println("\nLogging out...\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }

        } while (userChoice != 0 && logoutFlag == false);
    }

    /**
     * This method deactivates the account, removes it from active accounts,
     * and moves it to the deactivated accounts list.
     * @param account This is the account to be deactivated.
     */
    public void deactivateAccount(Account account) {
        account.deactivateAccount(); // This marks the account as inactive.
        mainApp.getActiveAccounts().remove(account); // This removes the account from the active accounts list.
        mainApp.getDeactivatedAccounts().add(account); // This adds the account to the deactivated accounts list.
        System.out.println("\nAccount deactivated. You have been logged out.\n");
    }
}