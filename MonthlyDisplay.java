import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles displaying the calendar month view and navigation through months and dates.
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

            if (navigationChoice == 1) {
                currentMonth = currentMonth.minusMonths(1);
            } else if (navigationChoice == 2) {
                currentMonth = currentMonth.plusMonths(1);
            } else if (navigationChoice == 3) {
                System.out.print("Enter month (1-12): ");
                int month = userInput.nextInt();
                System.out.print("Enter year: ");
                int year = userInput.nextInt();
                userInput.nextLine();
                if (month >= 1 && month <= 12) {
                    currentMonth = YearMonth.of(year, month);
                } else {
                    System.out.println("Invalid month.\n");
                }
            } else if (navigationChoice == 4) {
                selectDateToView(userInput, calendar, currentMonth);
            } else if (navigationChoice == 0) {
                System.out.println("Returning to calendar menu...\n");
            } else if (navigationChoice == -1) {
                System.out.println("Logging out...\n");
                isLoggingOut = true;
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }

        } while (navigationChoice != 0 && !isLoggingOut);

        return isLoggingOut;
    }

    /**
     * Displays the calendar view for the given month and highlights days with entries.
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
            System.out.print("      ");
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
        System.out.println(); // Final newline
    }

    /**
     * This checks if there are any entries on a specific date.
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
     * This allows the user to select a specific day and view its entries.
     */
    public void selectDateToView(Scanner userInput, Calendar calendar, YearMonth yearMonth) {
        System.out.print("Enter the day you want to view (example: 5 for the 5th day): ");
        int day = userInput.nextInt();
        userInput.nextLine();

        if (day >= 1 && day <= yearMonth.lengthOfMonth()) {
            LocalDate selectedDate = yearMonth.atDay(day);
            boolean stayInDateMenu = true;

            while (stayInDateMenu && !UserMenu.logoutFlag) {
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

                    case 0:
                        stayInDateMenu = false;
                        break;

                    case -1:
                        System.out.println("Logging out...");
                        UserMenu.logoutFlag = true;
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
    }

    /**
     * This method allows the user to add an entry on the selected date.
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
                new IDGenerator().getNextEntryID(),
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
     */
    public void deleteEntryOnDate(Scanner userInput, Calendar calendar, LocalDate date) {
        ArrayList<Entry> entries = calendar.getCalendarEntries();
        ArrayList<Entry> entriesOnDate = new ArrayList<>();

        for (Entry entry : entries) {
            if (entry.getDate().equals(date)) {
                entriesOnDate.add(entry);
            }
        }

        if (entriesOnDate.isEmpty()) {
            System.out.println("No entries to delete on this date.");
            return;
        }

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
