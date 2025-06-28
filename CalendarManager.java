import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all calendar-related operations such as viewing, adding,
 * and deleting calendars.
 */
public class CalendarManager {

    private MonthlyDisplay monthlyDisplay;

    /**
     * This constructor initializes the MonthlyDisplay object used for visualizing calendars.
     */
    public CalendarManager() {
        this.monthlyDisplay = new MonthlyDisplay();
    }

    /**
     * This method displays the list of calendars for the logged-in account and
     * allows the user to navigate into a selected calendar.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     * @return This returns true if the user chooses to log out, false otherwise.
     */
    public boolean viewCalendars(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();
        boolean isLoggingOut = false;

        if (calendars.isEmpty()) {
            System.out.println("No calendars to display.\n");
        } else {
            for (int i = 0; i < calendars.size(); i++) {
                String status = calendars.get(i).isPubliclyAvailable() ? " (Public)" : " (Private)";
                System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName() + status);
            }

            System.out.print("Enter calendar number to view entries, 0 to go back, or -1 to logout: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            if (choice >= 1 && choice <= calendars.size()) {
                Calendar selectedCalendar = calendars.get(choice - 1);
                if (monthlyDisplay.calendarNavigation(userInput, selectedCalendar)) {
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
     * Allows a user to choose a public calendar to add or create a new one.
     * @param userInput Input scanner.
     * @param account The currently logged-in account.
     * @param publicCalendars Shared list of public calendars.
     */
    public void addCalendar(Scanner userInput, Account account, ArrayList<Calendar> publicCalendars) {
        System.out.println("[1] Choose from existing public calendars");
        System.out.println("[2] Create a new calendar");
        System.out.print("Enter your choice: ");

        int choice = userInput.nextInt();
        userInput.nextLine();

        if (choice == 1) {
            ArrayList<Calendar> publicOptions = new ArrayList<>();
            for (Calendar cal : publicCalendars) {
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
            System.out.println("Enter calendar name: ");
            String calendarName = userInput.nextLine();

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
                    publicCalendars.add(newCalendar);
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
     * This method allows the user to delete a calendar if:
     * - It is not the default calendar
     * - The user is the creator/owner of the calendar
     * 
     * If deleted, the calendar is also removed from all other users' accounts and public list.
     *
     * @param userInput Input scanner.
     * @param account The currently logged-in user.
     * @param publicCalendars The global public calendar list.
     * @param activeAccounts The list of all active accounts.
     * @return true if user chooses to log out; false otherwise.
     */
    public boolean deleteCalendar(Scanner userInput, Account account, ArrayList<Calendar> publicCalendars, ArrayList<Account> activeAccounts) {
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

                // Cannot delete default calendar
                if (selectedCalendar.getName().equals(account.getAccountName())) {
                    System.out.println("Default calendar cannot be deleted.\n");
                }
                // Only allow owner to delete it
                else if (selectedCalendar.getOwner() != account) {
                    System.out.println("Only the calendar's creator can delete this calendar.\n");
                }
                // Proceed with deletion
                else {
                    // Remove from public list if it's public
                    if (selectedCalendar.isPubliclyAvailable()) {
                        publicCalendars.remove(selectedCalendar);
                    }
                    // Remove from all accounts that added it
                    for (Account acc : activeAccounts) {
                        acc.getCalendars().remove(selectedCalendar);
                    }

                    // Clear entries from memory 
                    selectedCalendar.getCalendarEntries().clear();

                    System.out.println("Calendar deleted successfully.\n");
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
    public Calendar selectCalendar(Scanner userInput, Account account) {
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
