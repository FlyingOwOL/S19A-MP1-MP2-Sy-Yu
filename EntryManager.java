import java.util.ArrayList;
import java.util.Scanner;

public class EntryManager {

    // Display Calendar Entries (Sorted Manually by Start Time)
    public static void displayCalendarEntries(Scanner userInput, Account account) {
    Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
    if (selectedCalendar == null) return;

    ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();

    if (entries.isEmpty()) {
        System.out.println("No entries in this calendar.");
        return;
    }

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

    System.out.println("Press Enter to return to the calendar list.");
    userInput.nextLine();
}

    // Add a new entry to a calendar
    public static void addEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
        if (selectedCalendar == null) return;

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

    // Edit an entry by entry ID
    public static void editEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
        if (selectedCalendar == null) return;

        System.out.println("Enter the entry ID to edit: ");
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
            System.out.println("Entry updated successfully.");
        } else {
            System.out.println("Failed to update entry.");
        }
    }

    // Delete an entry by ID
    public static void deleteEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = CalendarManager.selectCalendar(userInput, account);
        if (selectedCalendar == null) return;

        System.out.println("Enter the entry ID to delete: ");
        int id = userInput.nextInt();
        userInput.nextLine();

        ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();
        Entry targetEntry = null;

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getEntryID() == id) {
                targetEntry = entries.get(i);
                break;
            }
        }

        if (targetEntry != null && selectedCalendar.deleteEntry(targetEntry)) {
            System.out.println("Entry deleted successfully.");
        } else {
            System.out.println("Entry not found or could not be deleted.");
        }
    }
}
