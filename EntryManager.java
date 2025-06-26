import java.util.ArrayList;
import java.util.Scanner;

public class EntryManager {

    // Display Calendar Entries (Sorted Manually by Start Time)
    public static void displayCalendarEntries(Scanner userInput, Account account) {
        Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();

            if (entries.isEmpty()) {
                System.out.println("No entries in this calendar.");
            } else {
                // Manual Bubble Sort (Ascending by Start Time)
                for (int i = 0; i < entries.size() - 1; i++) {
                    for (int j = 0; j < entries.size() - i - 1; j++) {
                        if (entries.get(j).getStartTime().isAfter(entries.get(j + 1).getStartTime())) {
                            Entry temp = entries.get(j);
                            entries.set(j, entries.get(j + 1));
                            entries.set(j + 1, temp);
                        }
                    }
                }

                System.out.println("\nEntries in " + selectedCalendar.getName() + ":");
                for (Entry entry : entries) {
                    System.out.println(entry);
                }
            }

            System.out.println("Press Enter to return to the calendar list.");
            userInput.nextLine();
        }
    }

    // Add a new entry to a calendar
    public static void addEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            System.out.println("Enter entry ID: ");
            int id = userInput.nextInt();
            userInput.nextLine();

            System.out.println("Enter entry title: ");
            String title = userInput.nextLine();

            System.out.println("Enter entry details: ");
            String details = userInput.nextLine();

            System.out.println("Enter entry date (YYYY-MM-DD): ");
            String dateInput = userInput.nextLine();

            System.out.println("Enter start time (HH:MM): ");
            String startTimeInput = userInput.nextLine();

            System.out.println("Enter end time (HH:MM): ");
            String endTimeInput = userInput.nextLine();

            Entry entry = new Entry(id, title, details,
                    java.time.LocalDate.parse(dateInput),
                    java.time.LocalTime.parse(startTimeInput),
                    java.time.LocalTime.parse(endTimeInput));

            if (selectedCalendar.addEntry(entry)) {
                System.out.println("Entry added successfully.");
            } else {
                System.out.println("Failed to add entry.");
            }
        }
    }

    // Edit an entry by entry ID
    public static void editEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();

            if (entries.isEmpty()) {
                System.out.println("\nNo entry to be edited.\n");
            } else {
                System.out.println("Enter the entry ID to edit: ");

                if (!userInput.hasNextInt()) {
                    System.out.println("\nInvalid input. Please enter a valid entry ID (number).\n");
                    userInput.nextLine(); // Discard invalid input
                } else {
                    int id = userInput.nextInt();
                    userInput.nextLine();

                    System.out.println("Enter new entry title: ");
                    String title = userInput.nextLine();

                    System.out.println("Enter new entry details: ");
                    String details = userInput.nextLine();

                    System.out.println("Enter new entry date (YYYY-MM-DD): ");
                    String dateInput = userInput.nextLine();

                    System.out.println("Enter new start time (HH:MM): ");
                    String startTimeInput = userInput.nextLine();

                    System.out.println("Enter new end time (HH:MM): ");
                    String endTimeInput = userInput.nextLine();

                    Entry newEntry = new Entry(id, title, details,
                            java.time.LocalDate.parse(dateInput),
                            java.time.LocalTime.parse(startTimeInput),
                            java.time.LocalTime.parse(endTimeInput));

                    if (selectedCalendar.editEntry(id, newEntry)) {
                        System.out.println("\nEntry updated successfully.\n");
                    } else {
                        System.out.println("\nFailed to update entry. Entry ID not found.\n");
                    }
                }
            }
        }
    }

    // Delete an entry by ID
    public static void deleteEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();

            if (entries.isEmpty()) {
                System.out.println("\nNo entry to be deleted.\n");
            } else {
                System.out.println("Enter the entry ID to delete: ");

                if (!userInput.hasNextInt()) {
                    System.out.println("\nInvalid input. Please enter a valid entry ID (number).\n");
                    userInput.nextLine(); // Consume the invalid input
                } else {
                    int id = userInput.nextInt();
                    userInput.nextLine();

                    Entry targetEntry = null;
                    int i = 0;

                    while (i < entries.size() && targetEntry == null) {
                        if (entries.get(i).getEntryID() == id) {
                            targetEntry = entries.get(i);
                        }
                        i++;
                    }

                    if (targetEntry != null && selectedCalendar.deleteEntry(targetEntry)) {
                        System.out.println("\nEntry deleted successfully.\n");
                    } else {
                        System.out.println("\nEntry not found or could not be deleted.\n");
                    }
                }
            }
        }
    }
}
