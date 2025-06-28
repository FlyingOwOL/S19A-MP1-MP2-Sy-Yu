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
            System.out.print("     ");
        }

        int daysInMonth = yearMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = yearMonth.atDay(day);
            boolean hasEntry = checkEntryExists(calendar, currentDate);

            if (hasEntry) {
                if (day < 10) {
                    System.out.printf("[ %d] ", day);
                } else {
                    System.out.printf("[%d] ", day);
                }
            } else {
                if (day < 10) {
                    System.out.printf("  %d  ", day);
                } else {
                    System.out.printf(" %d  ", day);
                }
            }

            currentPosition++;
            if (currentPosition % 7 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
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
            System.out.println("Displaying entries for day: " + day);

            LocalDate selectedDate = yearMonth.atDay(day);
            ArrayList<Entry> entries = calendar.getCalendarEntries();
            ArrayList<Entry> entriesOnDate = new ArrayList<>();

            for (Entry entry : entries) {
                if (entry.getDate().equals(selectedDate)) {
                    entriesOnDate.add(entry);
                }
            }

            if (entriesOnDate.isEmpty()) {
                System.out.println("No entries on this date.\n");
                System.out.println("Press Enter to continue.");
                userInput.nextLine();
            } else {
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
                for (Entry entry : entriesOnDate) {
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
