import java.util.Scanner;

/**
 * This class handles the user menu operations such as calendar management,
 * entry management, and account deactivation.
 */
public class UserMenu {

     /**
     * This displays the user menu after a successful login and allows the user
     * to navigate calendar and entry options.
     * @param userInput This is the Scanner used to read user input.
     * @param loggedInAccount This is the currently logged-in account.
     */
    public static void userMenu(Scanner userInput, Account loggedInAccount) {
        int userChoice;

        // This loop continues to display the user menu until the user logs out.
        do {
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

            // This reads the user’s menu selection.
            userChoice = userInput.nextInt();
            userInput.nextLine(); 

             // This switch handles the user’s menu choice.
            switch (userChoice) {
                // This calls the method to view calendars.
                case 1:
                    CalendarManager.viewCalendars(userInput, loggedInAccount);
                    break;
                // This calls the method to add a new calendar.
                case 2:
                    CalendarManager.addCalendar(userInput, loggedInAccount);
                    break;
                // This calls the method to delete a calendar.
                case 3:
                    CalendarManager.deleteCalendar(userInput, loggedInAccount);
                    break;
                // This calls the method to add an entry to a calendar.
                case 4:
                    EntryManager.addEntry(userInput, loggedInAccount);
                    break;
                // This calls the method to edit an entry in a calendar.
                case 5:
                    EntryManager.editEntry(userInput, loggedInAccount);
                    break;
                // This calls the method to delete an entry from a calendar.
                case 6:
                    EntryManager.deleteEntry(userInput, loggedInAccount);
                    break;
                // This calls the method to deactivate the logged-in account.
                case 7:
                    deactivateAccount(loggedInAccount);
                    userChoice = 0;
                    break;
                // This logs out the user.   
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (userChoice != 0);
    }

    /**
     * This deactivates the account, removes it from active accounts,
     * and moves it to the deactivated accounts list.
     * @param account This is the account to be deactivated.
     */
    public static void deactivateAccount(Account account) {
        account.deactivateAccount();                // This marks the account as inactive.
        Main.activeAccounts.remove(account);        // This removes the account from the active accounts list.
        Main.deactivatedAccounts.add(account);      // This adds the account to the deactivated accounts list.
        System.out.println("Account deactivated. You have been logged out.");
    }
}
