import java.util.ArrayList;
import java.util.Scanner;

public class CalendarManager {

    // View Calendars and Navigate Entries
    public static void viewCalendars(Scanner userInput, Account account) {
    ArrayList<Calendar> calendars = account.getCalendars();
    if (calendars.isEmpty()) {
        System.out.println("No calendars to display.");
        return;
    }

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
                System.out.println("Invalid calendar selection. Please try again.");
            }
    }


    // Add a new calendar
    public static void addCalendar(Scanner userInput, Account account) {
        System.out.println("Enter calendar name: ");
        String calendarName = userInput.nextLine();

        System.out.println("Is this calendar public? (yes/no): ");
        String response = userInput.nextLine();
        boolean isPublic = response.equalsIgnoreCase("yes");

        Calendar newCalendar = new Calendar(calendarName, isPublic, account);
        account.addCalendar(newCalendar);

        if (isPublic) {
            Main.addToPublicCalendars(newCalendar);
        }

        System.out.println("Calendar added successfully.");
        if (isPublic) {
            System.out.println("This calendar is Public.");
        } else {
            System.out.println("This calendar is Private.");
        }
    }


    // Delete a calendar
    public static void deleteCalendar(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();
        if (calendars.isEmpty()) {
            System.out.println("No calendars to delete.");
            return;
        }

        System.out.println("Select calendar to delete:");
        for (int i = 0; i < calendars.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName());
        }

        System.out.print("Enter calendar number or 0 to cancel: ");
        int choice = userInput.nextInt();
        userInput.nextLine();

        if (choice >= 1 && choice <= calendars.size()) {
            Calendar selectedCalendar = calendars.get(choice - 1);

            if (selectedCalendar.getName().equals(account.getAccountName())) {
                System.out.println("Default calendar cannot be deleted.");
                return;
            }

            if (account.removeCalendar(selectedCalendar)) {
                if (selectedCalendar.isPubliclyAvailable()) {
                    Main.publicCalendars.remove(selectedCalendar);
                }
                System.out.println("Calendar deleted successfully.");
            } else {
                System.out.println("You do not have permission to delete this calendar.");
            }
        }
    }

    // Helper to select a calendar
    public static Calendar selectCalendar(Scanner userInput, Account account) {
        ArrayList<Calendar> calendars = account.getCalendars();
        if (calendars.isEmpty()) {
            System.out.println("No calendars available.");
            return null;
        }

        System.out.println("Select a calendar:");
        for (int i = 0; i < calendars.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + calendars.get(i).getName());
        }

        System.out.print("Enter calendar number or 0 to cancel: ");
        int choice = userInput.nextInt();
        userInput.nextLine();

        if (choice >= 1 && choice <= calendars.size()) {
            return calendars.get(choice - 1);
        } else {
            return null;
        }
    }
}
