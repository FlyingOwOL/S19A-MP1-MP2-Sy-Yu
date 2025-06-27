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
     * @return This returns true if the user chooses to log out, false otherwise.
     */
    public static boolean viewCalendars(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();
        boolean isLoggingOut = false;

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

            System.out.print("Enter calendar number to view entries, 0 to go back, or -1 to logout: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            if (choice >= 1 && choice <= calendars.size()) {
                Calendar selectedCalendar = calendars.get(choice - 1);
                if (MonthlyDisplay.calendarNavigation(userInput, selectedCalendar)) {
                    // MonthlyDisplay requested a logout
                    isLoggingOut = true;
                }
            } else if (choice == -1) {
                System.out.println("Logging out...\n");
                isLoggingOut = true;
            } else if (choice != 0) {
                System.out.println("Invalid calendar selection. Please try again.\n");
            }
        }

        return isLoggingOut;
    }

    /**
     * This method allows the user to either:
     * - Choose from the list of existing public calendars (not already added), or
     * - Create a brand-new calendar and decide whether it is public or private.
     * The chosen or created calendar is then added to the user's account.
     * 
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the currently logged-in user's account.
     */
 
    public static void addCalendar(Scanner userInput, Account account) {
        System.out.println("[1] Choose from existing public calendars");
        System.out.println("[2] Create a new calendar");
        System.out.print("Enter your choice: ");
        
        int choice = userInput.nextInt();
        userInput.nextLine();

        if (choice == 1) {
            //  Choose from existing public calendars 

            ArrayList<Calendar> publicOptions = new ArrayList<>();
            for (Calendar cal : Main.publicCalendars) {
                if (!account.getCalendars().contains(cal)) {
                    publicOptions.add(cal);
                }
            }

            if (publicOptions.isEmpty()) {
                System.out.println("No available public calendars to add.\n");
                return;
            }

            System.out.println("Public Calendars Available:");
            for (int i = 0; i < publicOptions.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + publicOptions.get(i).getName());
            }

            System.out.print("Enter calendar number to add, or 0 to cancel: ");
            int selected = userInput.nextInt();
            userInput.nextLine();

            if (selected >= 1 && selected <= publicOptions.size()) {
                Calendar selectedCalendar = publicOptions.get(selected - 1);
                if (account.addCalendar(selectedCalendar)) {
                    System.out.println("Calendar added successfully!\n");
                } else {
                    System.out.println("Failed to add calendar.\n");
                }
            } else if (selected == 0) {
                System.out.println("Cancelled.\n");
            } else {
                System.out.println("Invalid selection.\n");
            }

        } else if (choice == 2) {
            // Create a new calendar 

            System.out.println("Enter calendar name: ");
            String calendarName = userInput.nextLine();

            // Check if the calendar name already exists in user's account
            for (Calendar c : account.getCalendars()) {
                if (c.getName().equalsIgnoreCase(calendarName)) {
                    System.out.println("A calendar with that name already exists in your account.\n");
                    return;
                }
            }

            System.out.println("Is this calendar public? (yes/no): ");
            String response = userInput.nextLine();
            boolean isPublic = response.equalsIgnoreCase("yes");

            Calendar newCalendar = new Calendar(calendarName, isPublic, account);
            if (account.addCalendar(newCalendar)) {
                if (isPublic) {
                    Main.addToPublicCalendars(newCalendar);
                }
                System.out.println("New calendar created and added successfully.");
            } else {
                System.out.println("Failed to create calendar.");
            }

        } else {
            System.out.println("Invalid choice.\n");
        }
    }


    /**
     * This method allows the user to delete a calendar if it is not the default calendar.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public static boolean deleteCalendar(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();
        boolean isLoggingOut = false;

        if (calendars.isEmpty()) {
            System.out.println("No calendars to delete.\n");
        } else {
            System.out.println("Select calendar to delete:");
            for (int i = 0; i < calendars.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName());
            }

            System.out.print("Enter calendar number, 0 to cancel, or -1 to logout: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            if (choice == -1) {
                System.out.println("Logging out...\n");
                isLoggingOut = true;
            } else if (choice >= 1 && choice <= calendars.size()) {
                Calendar selectedCalendar = calendars.get(choice - 1);

                if (selectedCalendar.getName().equals(account.getAccountName())) {
                    System.out.println("Default calendar cannot be deleted.\n");
                } else {
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

        return isLoggingOut;
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
        System.out.println("Select a calendar:");
        for (int i = 0; i < calendars.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName());
        }

        System.out.print("Enter calendar number, 0 to cancel, or -1 to logout: ");
        int choice = userInput.nextInt();
        userInput.nextLine();

        if (choice == -1) {
            System.out.println("Logging out...\n");
            UserMenu.logoutFlag = true;
        } else if (choice >= 1 && choice <= calendars.size()) {
            selectedCalendar = calendars.get(choice - 1);
        } else if (choice != 0) {
            System.out.println("Invalid calendar selection.\n");
        }
    }

    return selectedCalendar;
}

}
