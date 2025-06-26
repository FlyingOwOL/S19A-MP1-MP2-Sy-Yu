import java.time.LocalDate; /*  This import is necessary for handling dates in the MonthlyDisplay class. 
It is the current date (year, month, day). */
import java.time.YearMonth; // We import YearMonth for month/year handling
import java.util.ArrayList;
import java.util.Scanner;

public class MonthlyDisplay {

    public static void calendarNavigation(Scanner userInput, Calendar calendar) {
        YearMonth currentMonth = YearMonth.now();
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
                    if (month >= 1 && month <= 12) {
                        currentMonth = YearMonth.of(year, month);
                    } else {
                        System.out.println("Invalid month.\n");
                    }
                    break;
                case 4:
                    selectDateToView(userInput, calendar, currentMonth);
                    break;
                case 0:
                    System.out.println("Returning to calendar menu...\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (navigationChoice != 0);
    }

    public static void displayMonthView(Calendar calendar, YearMonth yearMonth) {
        System.out.println("\n" + yearMonth.getMonth() + " " + yearMonth.getYear());
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        LocalDate firstDay = yearMonth.atDay(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        int currentPosition = dayOfWeek % 7;

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
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static boolean checkEntryExists(Calendar calendar, LocalDate date) {
        ArrayList<Entry> entries = calendar.getCalendarEntries();
        boolean found = false;

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getDate().equals(date)) {
                found = true;
            }
        }

        return found;
    }

    public static void selectDateToView(Scanner userInput, Calendar calendar, YearMonth yearMonth) {
        System.out.print("Enter the day you want to view (example: 5 for the 5th day): ");
        int day = userInput.nextInt();
        userInput.nextLine();

        if (day >= 1 && day <= yearMonth.lengthOfMonth()) {

            System.out.println("Displaying entries for day: " + day);

            LocalDate selectedDate = yearMonth.atDay(day);
            ArrayList<Entry> entries = calendar.getCalendarEntries();
            ArrayList<Entry> entriesOnDate = new ArrayList<>();

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

            // Manual Bubble Sort
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
