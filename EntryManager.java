import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class manages all entry-related operations such as adding, viewing,
 * editing, and deleting calendar entries.
 */
public class EntryManager {

    private CalendarManager calendarManager; // Handles calendar selection and navigation

    /**
     * This constructor initializes the entry manager with a reference to a calendar manager.
     * @param calendarManager The CalendarManager object.
     */
    public EntryManager(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
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
                System.out.println("No entries in this calendar.\n");
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
     * It first displays all current entries in the selected calendar.
     * No abrupt return statements are used to maintain smooth program flow.
     * 
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public void addEntry(Scanner userInput, Account account) {
    Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
    if (selectedCalendar != null) {

        ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();
        boolean validAction = false;

        if (entries.isEmpty()) {
            System.out.println("\nNo entries currently in this calendar.\n");
        } else {
            System.out.println("\nCurrent entries in " + selectedCalendar.getName() + ":");
            for (Entry entry : entries) {
                System.out.println(entry);
            }
            System.out.println();
        }

        System.out.println("Enter entry title: ");
        String title = userInput.nextLine();

        System.out.println("Enter entry details: ");
        String details = userInput.nextLine();

        // Use InputValidator class to check if dates and times are valid
        LocalDate date = InputValidator.readValidDate(userInput);
        LocalTime startTime = InputValidator.readValidTime(userInput, "Enter start time (HH:MM): ");
        LocalTime endTime = InputValidator.ensureEndTimeAfterStart(userInput, startTime);

        Entry entry = new Entry(
                title, details, date,
                startTime, endTime
        );

        if (selectedCalendar.addEntry(entry)) {
            System.out.println("\nEntry added successfully.\n");
            validAction = true;
        } else {
            System.out.println("\nFailed to add entry.\n");
        }

        if (!validAction) {
            System.out.println("No entry was added.\n");
        }
    }
}


    /**
     * This method allows the user to edit an existing entry by selecting from a list of entries.
     * The user can cancel the operation or proceed to update the entry details.
     * No abrupt return statements are used to maintain smooth program flow.
     *
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public void editEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();
            boolean validAction = false; // Flag to control flow

            if (entries.isEmpty()) {
                // This prints when there are no entries to edit.
                System.out.println("\nNo entries available to edit.\n");
                validAction = false; // No entries to process
            } else {
                // This displays all current entries for selection.
                System.out.println("\nSelect an entry to edit:");
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + entries.get(i));
                }
                System.out.println("[0] Cancel");

                int choice = userInput.nextInt();
                userInput.nextLine(); // Clear input buffer

                if (choice == 0) {
                    // This handles the user cancelling the edit.
                    System.out.println("Edit cancelled.\n");
                    validAction = false; // User cancelled the operation
                } else if (choice >= 1 && choice <= entries.size()) {
                    // This handles a valid entry selection.
                    Entry oldEntry = entries.get(choice - 1);

                    // This prompts the user for updated entry details.
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

                    // This creates the updated entry object.
                    Entry newEntry = new Entry(
                            title, details,
                            java.time.LocalDate.parse(dateInput),
                            java.time.LocalTime.parse(startTimeInput),
                            java.time.LocalTime.parse(endTimeInput)
                    );

                    // This updates the entry in the calendar.
                    if (selectedCalendar.editEntry(oldEntry, newEntry)) {
                        System.out.println("\nEntry updated successfully.\n");
                    } else {
                        System.out.println("\nFailed to update entry.\n");
                    }

                    validAction = true; // Action was completed
                } else {
                    // This handles invalid selection.
                    System.out.println("Invalid selection.\n");
                    validAction = false; // Invalid option selected
                }
            }

            // This is a final message if no valid editing action was performed.
            if (!validAction) {
                System.out.println("No editing was performed.\n");
            }
        }
    }

    /**
     * This method allows the user to delete an entry by selecting from a list of entries.
     * The user can cancel the operation or proceed to delete the selected entry.
     * No abrupt return statements are used to maintain smooth program flow.
     *
     * @param userInput This is the Scanner used to read user input.
     * @param account This is the logged-in user's account.
     */
    public void deleteEntry(Scanner userInput, Account account) {
        Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();
            boolean validAction = false; // Flag to control flow

            if (entries.isEmpty()) {
                // This prints when there are no entries to delete.
                System.out.println("\nNo entry to be deleted.\n");
                validAction = false; // No entries to process
            } else {
                // This displays all current entries for selection.
                System.out.println("\nSelect entry to delete:");
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + entries.get(i));
                }
                System.out.println("[0] Cancel");

                int choice = userInput.nextInt();
                userInput.nextLine(); // Clear input buffer

                if (choice == 0) {
                    // This handles the user cancelling the deletion.
                    System.out.println("Delete cancelled.\n");
                    validAction = false; // User cancelled the operation
                } else if (choice >= 1 && choice <= entries.size()) {
                    // This handles a valid entry selection.
                    Entry targetEntry = entries.get(choice - 1);

                    if (selectedCalendar.deleteEntry(targetEntry)) {
                        System.out.println("\nEntry deleted successfully.\n");
                    } else {
                        System.out.println("\nEntry could not be deleted.\n");
                    }

                    validAction = true; // Action was completed
                } else {
                    // This handles invalid selection.
                    System.out.println("Invalid selection.\n");
                    validAction = false; // Invalid option selected
                }
            }

            // This is a final message if no valid deletion action was performed.
            if (!validAction) {
                System.out.println("No deletion was performed.\n");
            }
        }
    }
}
