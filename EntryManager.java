import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class manages all entry-related operations such as adding, viewing,
 * editing, and deleting calendar entries.
 */
public class EntryManager {

    private CalendarManager calendarManager; // Handles calendar selection and navigation
    private IDGenerator idGenerator;         // Generates unique entry IDs

    /**
     * This constructor initializes the entry manager with a reference to a calendar manager and ID generator.
     * @param calendarManager The CalendarManager object.
     * @param idGenerator The IDGenerator object to create unique entry IDs.
     */
    public EntryManager(CalendarManager calendarManager, IDGenerator idGenerator) {
        this.calendarManager = calendarManager;
        this.idGenerator = idGenerator;
    }

    /**
     * This method displays all entries in the selected calendar.
     * Entries are sorted manually in ascending order by their start time.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public void displayCalendarEntries(Scanner userInput, Account account) {
        Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();

            if (entries.isEmpty()) {
                System.out.println("No entries in this calendar.");
            } else {
                // This sorts entries manually by start time using bubble sort.
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

    /**
     * This method allows the user to add a new entry to a selected calendar.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public void addEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            int id = idGenerator.getNextEntryID(); // This generates a unique ID for the new entry

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

            // This creates the entry object.
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

    /**
     * This method allows the user to edit an existing entry by its entry ID.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public void editEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();

            if (entries.isEmpty()) {
                System.out.println("\nNo entry to be edited.\n");
                return;
            }

            System.out.println("Enter the entry ID to edit: ");
            if (!userInput.hasNextInt()) {
                System.out.println("\nInvalid input. Please enter a valid entry ID (number).\n");
                userInput.nextLine(); 
                return;
            }

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

    /**
     * This method allows the user to delete an entry by its entry ID.
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public void deleteEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();

            if (entries.isEmpty()) {
                System.out.println("\nNo entry to be deleted.\n");
                return;
            }

            System.out.println("Enter the entry ID to delete: ");

            if (!userInput.hasNextInt()) {
                System.out.println("\nInvalid input. Please enter a valid entry ID (number).\n");
                userInput.nextLine();
                return;
            }

            int id = userInput.nextInt();
            userInput.nextLine();

            Entry targetEntry = null;
            for (Entry entry : entries) {
                if (entry.getEntryID() == id) {
                    targetEntry = entry;
                    break;
                }
            }

            if (targetEntry != null && selectedCalendar.deleteEntry(targetEntry)) {
                System.out.println("\nEntry deleted successfully.\n");
            } else {
                System.out.println("\nEntry not found or could not be deleted.\n");
            }
        }
    }
}