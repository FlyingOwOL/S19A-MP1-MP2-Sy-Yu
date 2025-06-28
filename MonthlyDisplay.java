import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles displaying the calendar month view and navigation through months and dates.
 * This has no encapsulation because it is designed to be a helper class for calendar operations.
 */
public class MonthlyDisplay {

    /**
     * This method allows users to navigate the calendar by switching months or selecting specific dates.
     * @param userInput This is the Scanner used to read user input.
     * @param calendar This is the selected calendar to display.
     * @return This returns true if the user chooses to log out, false otherwise.
     */
    public boolean calendarNavigation(Scanner userInput, Calendar calendar) {
        YearMonth currentMonth = YearMonth.now(); // This sets the starting point to the current month (in real life).
        int navigationChoice;
        boolean isLoggingOut = false;

        do {
            displayMonthView(calendar, currentMonth);

            System.out.println("\n[1] Previous Month");
            System.out.println("[2] Next Month");
            System.out.println("[3] Jump to Month and Year");
            System.out.println("[4] Select Date to View Entries");
            System.out.println("[0] Back to Calendar Menu");
            System.out.println("[-1] Logout");
            System.out.print("Enter your choice: ");
            navigationChoice = userInput.nextInt();
            userInput.nextLine();

            switch (navigationChoice) {
                case 1:
                    currentMonth = currentMonth.minusMonths(1);
                    break;

                case 2:
                    currentMonth = currentMonth.plusMonths(1);
                    break;

                case 3:
                    System.out.print("Enter month (1-12): ");
                    int month = userInput.nextInt();
                    System.out.print("Enter year: ");
                    int year = userInput.nextInt();
                    userInput.nextLine();
                    // This checks if the month is valid (between 1 and 12).
                    if (month >= 1 && month <= 12) {
                        currentMonth = YearMonth.of(year, month);
                    } else {
                        System.out.println("Invalid month.\n");
                    }
                    break;

                case 4:
                    if (selectDateToView(userInput, calendar, currentMonth)) {
                        isLoggingOut = true;
                    }
                    break;

                case 0:
                    System.out.println("Returning to calendar menu...\n");
                    break;

                case -1:
                    System.out.println("Logging out...\n");
                    isLoggingOut = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }

        } while (navigationChoice != 0 && !isLoggingOut);

        return isLoggingOut;
    }

    /**
     * This method plays the calendar view for the given month and highlights days with entries.
     */
    public void displayMonthView(Calendar calendar, YearMonth yearMonth) {
        System.out.println("\n" + yearMonth.getMonth() + " " + yearMonth.getYear());
        System.out.println("Legend:");
        System.out.println("[D]  - Day with entry (single digit)");
        System.out.println("[DD] - Day with entry (double digit)");
        System.out.println(" D   - Day without entry (single digit)");
        System.out.println(" DD  - Day without entry (double digit)\n");

        System.out.println(" Sun  Mon  Tue  Wed  Thu  Fri  Sat");

        LocalDate firstDay = yearMonth.atDay(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue();
        int currentPosition = dayOfWeek % 7;

        for (int i = 0; i < currentPosition; i++) {
            System.out.print("     ");
        }

        int daysInMonth = yearMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = yearMonth.atDay(day);
            boolean hasEntry = checkEntryExists(calendar, currentDate);

            if (hasEntry) {
                System.out.printf("[%2d]", day); // For days with entry
            } else {
                System.out.printf(" %2d ", day); // For days without entry
            }

            currentPosition++;

            // Move to the next line after every 7 days
            if (currentPosition % 7 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println(); // This ensures the last line ends properly
    }

    /**
     * This method checks if there are any entries on a specific date.
     */
    public boolean checkEntryExists(Calendar calendar, LocalDate date) {
        ArrayList<Entry> entries = calendar.getCalendarEntries();
        for (Entry entry : entries) {
            if (entry.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method allows the user to select a specific day and view its entries.
     */
    public boolean selectDateToView(Scanner userInput, Calendar calendar, YearMonth yearMonth) {
        System.out.print("Enter the day you want to view (example: 5 for the 5th day): ");
        int day = userInput.nextInt();
        userInput.nextLine();

        boolean isLoggingOut = false;

        if (day >= 1 && day <= yearMonth.lengthOfMonth()) {
            LocalDate selectedDate = yearMonth.atDay(day);
            boolean stayInDateMenu = true;

            while (stayInDateMenu && !isLoggingOut) {
                ArrayList<Entry> entries = calendar.getCalendarEntries();
                ArrayList<Entry> entriesOnDate = new ArrayList<>();

                for (Entry entry : entries) {
                    if (entry.getDate().equals(selectedDate)) {
                        entriesOnDate.add(entry);
                    }
                }

                System.out.println("\nEntries on " + selectedDate + ":");
                if (entriesOnDate.isEmpty()) {
                    System.out.println("No entries on this date.");
                } else {
                    for (Entry entry : entriesOnDate) {
                        System.out.println(entry);
                    }
                }

                System.out.println("\n[1] Add Entry");
                System.out.println("[2] Delete Entry");
                System.out.println("[3] Edit Entry");
                System.out.println("[0] Back to Month View");
                System.out.println("[-1] Logout");
                System.out.print("Enter your choice: ");
                int choice = userInput.nextInt();
                userInput.nextLine();

                switch (choice) {
                    case 1:
                        addEntryOnDate(userInput, calendar, selectedDate);
                        break;

                    case 2:
                        deleteEntryOnDate(userInput, calendar, selectedDate);
                        break;

                    case 3:
                        editEntryOnDate(userInput, calendar, selectedDate);
                        break;

                    case 0:
                        stayInDateMenu = false;
                        break;

                    case -1:
                        System.out.println("Logging out...");
                        isLoggingOut = true;
                        stayInDateMenu = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } else {
            System.out.println("Invalid day.\n");
            System.out.println("Press Enter to continue.");
            userInput.nextLine();
        }

        return isLoggingOut;
    }

    /**
     * This method allows the user to add an entry on the selected date.
     * @param userInput This is the Scanner used to read user input.
     * @param calendar This is the selected calendar where the entry exists.
     * @param date This is the date on which the entry to be edited exists.
     */
    public void addEntryOnDate(Scanner userInput, Calendar calendar, LocalDate date) {
        System.out.println("Enter entry title: ");
        String title = userInput.nextLine();

        System.out.println("Enter entry details: ");
        String details = userInput.nextLine();

        System.out.println("Enter start time (HH:MM): ");
        String startTimeInput = userInput.nextLine();

        System.out.println("Enter end time (HH:MM): ");
        String endTimeInput = userInput.nextLine();

        Entry entry = new Entry(
                title, details, date,
                java.time.LocalTime.parse(startTimeInput),
                java.time.LocalTime.parse(endTimeInput));

        if (calendar.addEntry(entry)) {
            System.out.println("Entry added successfully.");
        } else {
            System.out.println("Failed to add entry.");
        }
    }

    /**
     * This method allows the user to delete an entry on the selected date.
     * @param userInput This is the Scanner used to read user input.
     * @param calendar This is the selected calendar where the entry exists.
     * @param date This is the date on which the entry to be edited exists.
     */
    public void deleteEntryOnDate(Scanner userInput, Calendar calendar, LocalDate date) {
    ArrayList<Entry> entries = calendar.getCalendarEntries();
    ArrayList<Entry> entriesOnDate = new ArrayList<>();

    for (Entry entry : entries) {
        if (entry.getDate().equals(date)) {
            entriesOnDate.add(entry);
        }
    }

    boolean proceed = true;

    if (entriesOnDate.isEmpty()) {
        System.out.println("No entries to delete on this date.");
        proceed = false;
    }

    if (proceed) {
        System.out.println("Select entry to delete:");
        for (int i = 0; i < entriesOnDate.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + entriesOnDate.get(i));
        }
        System.out.println("[0] Cancel");

        int choice = userInput.nextInt();
        userInput.nextLine();

        if (choice >= 1 && choice <= entriesOnDate.size()) {
            Entry toDelete = entriesOnDate.get(choice - 1);
            if (calendar.deleteEntry(toDelete)) {
                System.out.println("Entry deleted successfully.");
            } else {
                System.out.println("Failed to delete entry.");
            }
        } else if (choice == 0) {
            System.out.println("Deletion cancelled.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}


    /**
     * This method allows the user to edit an entry on the selected date.
     * 
     * @param userInput This is the Scanner used to read user input.
     * @param calendar This is the selected calendar where the entry exists.
     * @param date This is the date on which the entry to be edited exists.
     */
    public void editEntryOnDate(Scanner userInput, Calendar calendar, LocalDate date) {
    ArrayList<Entry> entries = calendar.getCalendarEntries();
    ArrayList<Entry> entriesOnDate = new ArrayList<>();

    for (Entry entry : entries) {
        if (entry.getDate().equals(date)) {
            entriesOnDate.add(entry);
        }
    }

    boolean proceed = true;

    if (entriesOnDate.isEmpty()) {
        System.out.println("No entries to edit on this date.");
        proceed = false;
    }

    if (proceed) {
        System.out.println("Select entry to edit:");
        for (int i = 0; i < entriesOnDate.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + entriesOnDate.get(i));
        }
        System.out.println("[0] Cancel");

        int choice = userInput.nextInt();
        userInput.nextLine();

        if (choice == 0) {
            System.out.println("Edit cancelled.");
            proceed = false;
        }

        if (proceed && choice >= 1 && choice <= entriesOnDate.size()) {
            Entry oldEntry = entriesOnDate.get(choice - 1);

            System.out.println("Enter new entry title: ");
            String title = userInput.nextLine();

            System.out.println("Enter new entry details: ");
            String details = userInput.nextLine();

            System.out.println("Enter new start time (HH:MM): ");
            String startTimeInput = userInput.nextLine();

            System.out.println("Enter new end time (HH:MM): ");
            String endTimeInput = userInput.nextLine();

            LocalTime startTime = LocalTime.parse(startTimeInput);
            LocalTime endTime = LocalTime.parse(endTimeInput);

            Entry newEntry = new Entry(
                    title, details, date, startTime, endTime
            );

            if (calendar.editEntry(oldEntry, newEntry)) {
                System.out.println("Entry updated successfully.");
            } else {
                System.out.println("Failed to update entry.");
            }
        } else if (proceed) {
            System.out.println("Invalid selection.");
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
     * @param calendarManager This is the CalendarManager instance used to select calendars.
     */
    public void editEntry(Scanner userInput, Account account, CalendarManager calendarManager) {
        Calendar selectedCalendar = calendarManager.selectCalendar(userInput, account);
        if (selectedCalendar != null) {

            ArrayList<Entry> entries = selectedCalendar.getCalendarEntries();
            boolean validAction = false;

            if (entries.isEmpty()) {
                System.out.println("\nNo entries available to edit.\n");
                validAction = false;
            } else {
                System.out.println("\nSelect an entry to edit:");
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + entries.get(i));
                }
                System.out.println("[0] Cancel");

                int choice = userInput.nextInt();
                userInput.nextLine();

                if (choice == 0) {
                    System.out.println("Edit cancelled.\n");
                    validAction = false;
                } else if (choice >= 1 && choice <= entries.size()) {
                    // This handles a valid entry selection.
                    Entry oldEntry = entries.get(choice - 1);

                    System.out.println("Enter new entry title: ");
                    String title = userInput.nextLine();

                    System.out.println("Enter new entry details: ");
                    String details = userInput.nextLine();

                    // Use InputValidator to ensure proper input
                    InputValidator inputValidator = new InputValidator(); // This creates an instance of InputValidator to validate user input.
                    LocalDate date = inputValidator.readValidDate(userInput); // This reads a valid date from the user.
                    // This reads a valid start time from the user.
                    LocalTime startTime = inputValidator.readValidTime(userInput, "Enter new start time (HH:MM): ");
                    // This reads a valid end time from the user, ensuring it is after the start time. 
                    LocalTime endTime = inputValidator.ensureEndTimeAfterStart(userInput, startTime);

                    // This creates the updated entry object.
                    Entry newEntry = new Entry(
                            title, details, date, startTime, endTime
                    );

                    // This updates the entry in the calendar.
                    if (selectedCalendar.editEntry(oldEntry, newEntry)) {
                        System.out.println("\nEntry updated successfully.\n");
                        validAction = true;
                    } else {
                        System.out.println("\nFailed to update entry.\n");
                        validAction = false;
                    }

                } else {
                    System.out.println("Invalid selection.\n");
                    validAction = false;
                }
            }

            if (!validAction) {
                System.out.println("No editing was performed.\n");
            }
        }
    }
}
