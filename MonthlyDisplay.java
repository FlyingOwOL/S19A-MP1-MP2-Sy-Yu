import java.time.LocalDate; /*  This import is necessary for handling dates in the MonthlyDisplay class. 
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
     */
    public static void calendarNavigation(Scanner userInput, Calendar calendar) {
        YearMonth currentMonth = YearMonth.now(); // This sets the starting point to the current month (in real life).
        int navigationChoice;

        do {
            displayMonthView(calendar, currentMonth);

            System.out.println("\n[1] Previous Month");
            System.out.println("[2] Next Month");
            System.out.println("[3] Jump to Month and Year");
            System.out.println("[4] Select Date to View Entries");
            System.out.println("[0] Back to Calendar Menu");
            System.out.print("Enter your choice: ");
            navigationChoice = userInput.nextInt();
            userInput.nextLine();

            switch (navigationChoice) {
                case 1:
                // This moves to the previous month.
                    currentMonth = currentMonth.minusMonths(1);
                    break;
                case 2:
                 // This moves to the next month.
                    currentMonth = currentMonth.plusMonths(1);
                    break;
                case 3:
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
                    break;
                case 4:
                // This allows selecting a specific date to view entries.
                    selectDateToView(userInput, calendar, currentMonth);
                    break;
                case 0:  
                // This exits back to the calendar menu.
                    System.out.println("Returning to calendar menu...\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (navigationChoice != 0);
    }

    /**
     * This method displays the calendar view for the given month and highlights days with entries.
     * @param calendar This is the calendar being displayed.
     * @param yearMonth This represents the current month and year being viewed.
     */
    public static void displayMonthView(Calendar calendar, YearMonth yearMonth) {
        System.out.println("\n" + yearMonth.getMonth() + " " + yearMonth.getYear());
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        LocalDate firstDay = yearMonth.atDay(1); // This gets the first day of the month.
        int dayOfWeek = firstDay.getDayOfWeek().getValue(); // This gets the day of the week (1=Monday, 7=Sunday).

        int currentPosition = dayOfWeek % 7; // This adjusts Sunday to index 0 since there are 7 days.

        // This just prints spaces for alignment.
        for (int i = 0; i < currentPosition; i++) {
            System.out.print("    ");
        }

        int daysInMonth = yearMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = yearMonth.atDay(day);
            boolean hasEntry = checkEntryExists(calendar, currentDate);

            if (hasEntry) {
                System.out.printf("[%2d]", day);
            } else {
                System.out.printf(" %2d ", day);
            }

            currentPosition++;
            if (currentPosition % 7 == 0) {
                System.out.println(); // This starts a new week.
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

            // This finds all entries on the selected date.
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).getDate().equals(selectedDate)) {
                    entriesOnDate.add(entries.get(i));
                }
            }

            if (entriesOnDate.isEmpty()) {
                System.out.println("No entries on this date.\n");
            } else {
                for (Entry entry : entriesOnDate) {
                    System.out.println(entry);
                }
            }

            System.out.println("Press Enter to continue.");
            userInput.nextLine();

            // This sorts entries on the selected date by start time using bubble sort.
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

        } else {
            System.out.println("Invalid day.\n");
            System.out.println("Press Enter to continue.");
            userInput.nextLine();
        }
    }
}
