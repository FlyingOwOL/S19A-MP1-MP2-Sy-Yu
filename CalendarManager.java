import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all calendar-related operations such as viewing, adding,
 * and deleting calendars.
 */
public class CalendarManager {

    /**
     * This method displays the list of calendars for the logged-in account and
     * allows the user to navigate into a selected calendar.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public static void viewCalendars(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();

        if (calendars.isEmpty()) {
            System.out.println("No calendars to display.\n");
        } else {
            // This displays each calendar with its privacy status.
            for (int i = 0; i < calendars.size(); i++) {
                String availabilityStatus;
                if (calendars.get(i).isPubliclyAvailable()) {
                    availabilityStatus = " (Public)";
                } else {
                    availabilityStatus = " (Private)";
                }
                System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName() + availabilityStatus);
            }

            System.out.print("Enter calendar number to view entries or 0 to go back: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            if (choice >= 1 && choice <= calendars.size()) {
                Calendar selectedCalendar = calendars.get(choice - 1);
                MonthlyDisplay.calendarNavigation(userInput, selectedCalendar);
            } else if (choice != 0) {
                System.out.println("Invalid calendar selection. Please try again.\n");
            }
        }
    }

    /**
     * This method allows the user to create a new calendar and add it to their account.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public static void addCalendar(Scanner userInput, Account account) {
        System.out.println("Enter calendar name: ");
        String calendarName = userInput.nextLine();

        System.out.println("Is this calendar public? (yes/no): ");
        String response = userInput.nextLine();
        boolean isPublic = response.equalsIgnoreCase("yes");

        // Add a new calendar.
        Calendar newCalendar = new Calendar(calendarName, isPublic, account);
        account.addCalendar(newCalendar);

        if (isPublic) {
            Main.addToPublicCalendars(newCalendar);
        }

        System.out.println("Calendar added successfully.");
        if (isPublic) {
            System.out.println("This calendar is Public.\n");
        } else {
            System.out.println("This calendar is Private.\n");
        }
    }

    /**
     * This method allows the user to delete a calendar if it is not the default calendar.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public static void deleteCalendar(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();

        if (calendars.isEmpty()) {
            System.out.println("No calendars to delete.\n");
        } else {
            // This displays all calendars.
            System.out.println("Select calendar to delete:");
            for (int i = 0; i < calendars.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName());
            }

            System.out.print("Enter calendar number or 0 to cancel: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            if (choice >= 1 && choice <= calendars.size()) {
                Calendar selectedCalendar = calendars.get(choice - 1);

                // This prevents the default calendar from being deleted.
                if (selectedCalendar.getName().equals(account.getAccountName())) {
                    System.out.println("Default calendar cannot be deleted.\n");
                } else {
                     // This removes the selected calendar if allowed.
                    if (account.removeCalendar(selectedCalendar)) {
                        if (selectedCalendar.isPubliclyAvailable()) {
                            Main.publicCalendars.remove(selectedCalendar);
                        }
                        System.out.println("Calendar deleted successfully.\n");
                    } else {
                        System.out.println("You do not have permission to delete this calendar.\n");
                    }
                }
            } else if (choice != 0) {
                System.out.println("Invalid calendar selection.\n");
            }
        }
    }

    /**
     * This method allows the user to select a calendar from their list.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     * @return This returns the selected calendar or null if no valid selection is made.
     */
    public static Calendar selectCalendar(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();
        Calendar selectedCalendar = null;

        if (calendars.isEmpty()) {
            System.out.println("No calendars available.\n");
        } else {
            // This displays all calendars.
            System.out.println("Select a calendar:");
            for (int i = 0; i < calendars.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName());
            }

            System.out.print("Enter calendar number or 0 to cancel: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            if (choice >= 1 && choice <= calendars.size()) {
                selectedCalendar = calendars.get(choice - 1);
            } else if (choice != 0) {
                System.out.println("Invalid calendar selection.\n");
            }
        }

        return selectedCalendar;
    }
}
