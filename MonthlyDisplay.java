import java.time.LocalDate; /* This import is necessary for handling dates in the MonthlyDisplay class. 
It is the current date (year, month, day). */
import java.time.YearMonth; // We import YearMonth for month/year handling
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
    public static boolean calendarNavigation(Scanner userInput, Calendar calendar) {
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
                // This moves to the previous month.
                currentMonth = currentMonth.minusMonths(1);
            } else if (navigationChoice == 2) {
                // This moves to the next month.
                currentMonth = currentMonth.plusMonths(1);
            } else if (navigationChoice == 3) {
                // This allows users to jump to a specific month and year.
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
                // This allows selecting a specific date to view entries.
                selectDateToView(userInput, calendar, currentMonth);
            } else if (navigationChoice == 0) {
                // This exits back to the calendar menu.
                System.out.println("Returning to calendar menu...\n");
            } else if (navigationChoice == -1) {
                // This logs out the user.
                System.out.println("Logging out...\n");
                isLoggingOut = true;
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }

        } while (navigationChoice != 0 && isLoggingOut == false);

        return isLoggingOut; // Return logout status to CalendarManager
    }

    /**
     * This method displays the calendar view for the given month and highlights days with entries.
     * @param calendar This is the calendar being displayed.
     * @param yearMonth This represents the current month and year being viewed.
     */
    public static void displayMonthView(Calendar calendar, YearMonth yearMonth) {
    System.out.println("\n" + yearMonth.getMonth() + " " + yearMonth.getYear());
    System.out.println("Sun Mon Tue Wed Thu Fri Sat");

    System.out.println("Legend:");
    System.out.println("[D]  - Day with entry (single digit)");
    System.out.println("[DD] - Day with entry (double digit)");
    System.out.println(" D   - Day without entry (single digit)");
    System.out.println(" DD  - Day without entry (double digit)\n");

    System.out.println("Sun Mon Tue Wed Thu Fri Sat");

    LocalDate firstDay = yearMonth.atDay(1);
    int dayOfWeek = firstDay.getDayOfWeek().getValue();

    int currentPosition = dayOfWeek % 7;

    for (int i = 0; i < currentPosition; i++) {
        System.out.print("     "); // Five spaces to match cell width
    }

    int daysInMonth = yearMonth.lengthOfMonth();
    for (int day = 1; day <= daysInMonth; day++) {
        LocalDate currentDate = yearMonth.atDay(day);
        boolean hasEntry = checkEntryExists(calendar, currentDate);

        if (hasEntry) {
            if (day < 10) {
                System.out.printf("[ %d] ", day); // Example: [ 5] 
            } else {
                System.out.printf("[%d] ", day);   // Example: [15]
            }
        } else {
            if (day < 10) {
                System.out.printf("  %d  ", day); // Example: "  5  "
            } else {
                System.out.printf(" %d  ", day);  // Example: " 15  "
            }
        }

        currentPosition++;
        if (currentPosition % 7 == 0) {
            System.out.println(); // New week
        } else {
            System.out.print(" ");
        }
    }
    System.out.println();
}

    /**
     * This method checks if there are any entries on a specific date.
     * @param calendar This is the calendar being checked.
     * @param date This is the date being checked.
     * @return This returns true if there is an entry on the specified date, otherwise false.
     */
    public static boolean checkEntryExists(Calendar calendar, LocalDate date) {
        ArrayList<Entry> entries = calendar.getCalendarEntries();
        boolean found = false;

        // This loops through all entries to see if one matches the date.
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getDate().equals(date)) {
                found = true;
            }
        }

        return found;
    }

    /**
     * This method allows the user to select a specific day and view its entries.
     * @param userInput This is the Scanner used to read user input.
     * @param calendar This is the calendar being viewed.
     * @param yearMonth This represents the current month and year.
     */
    public static void selectDateToView(Scanner userInput, Calendar calendar, YearMonth yearMonth) {
        System.out.print("Enter the day you want to view (example: 5 for the 5th day): ");
        int day = userInput.nextInt();
        userInput.nextLine();

        if (day >= 1 && day <= yearMonth.lengthOfMonth()) {

            System.out.println("Displaying entries for day: " + day);

            LocalDate selectedDate = yearMonth.atDay(day);
            ArrayList<Entry> entries = calendar.getCalendarEntries();
            ArrayList<Entry> entriesOnDate = new ArrayList<>();

            // Find entries on the selected date
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).getDate().equals(selectedDate)) {
                    entriesOnDate.add(entries.get(i));
                }
            }

            if (entriesOnDate.isEmpty()) {
                System.out.println("No entries on this date.\n");
                System.out.println("Press Enter to continue.");
                userInput.nextLine(); // This prevents "Enter" from carrying over to the next input.
            } else {
                // Sort entries by start time using bubble sort
                for (int i = 0; i < entriesOnDate.size() - 1; i++) {
                    for (int j = 0; j < entriesOnDate.size() - i - 1; j++) {
                        if (entriesOnDate.get(j).getStartTime().isAfter(entriesOnDate.get(j + 1).getStartTime())) {
                            Entry temp = entriesOnDate.get(j);
                            entriesOnDate.set(j, entriesOnDate.get(j + 1));
                            entriesOnDate.set(j + 1, temp);
                        }
                    }
                }

                System.out.println("\nEntries on " + selectedDate + ":");
                for (int i = 0; i < entriesOnDate.size(); i++) {
                    Entry entry = entriesOnDate.get(i);
                    System.out.println(entry);
                }

                System.out.println("Press Enter to continue.");
                userInput.nextLine();
            }

        } else {
            System.out.println("Invalid day.\n");
            System.out.println("Press Enter to continue.");
            userInput.nextLine();
        }
    }

}