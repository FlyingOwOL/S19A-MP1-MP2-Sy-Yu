import java.util.Scanner;

public class UserMenu {

    public static void userMenu(Scanner userInput, Account loggedInAccount) {
        int userChoice;

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

            userChoice = userInput.nextInt();
            userInput.nextLine(); // Consume newline

            switch (userChoice) {
                case 1:
                    CalendarManager.viewCalendars(userInput, loggedInAccount);
                    break;
                case 2:
                    CalendarManager.addCalendar(userInput, loggedInAccount);
                    break;
                case 3:
                    CalendarManager.deleteCalendar(userInput, loggedInAccount);
                    break;
                case 4:
                    EntryManager.addEntry(userInput, loggedInAccount);
                    break;
                case 5:
                    EntryManager.editEntry(userInput, loggedInAccount);
                    break;

                case 6:
                    EntryManager.deleteEntry(userInput, loggedInAccount);
                    break;

                case 7:
                    deactivateAccount(loggedInAccount);
                    userChoice = 0;
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (userChoice != 0);
    }

    public static void deactivateAccount(Account account) {
        account.deactivateAccount();
        Main.activeAccounts.remove(account);
        Main.deactivatedAccounts.add(account);
        System.out.println("Account deactivated. You have been logged out.");
    }
}
