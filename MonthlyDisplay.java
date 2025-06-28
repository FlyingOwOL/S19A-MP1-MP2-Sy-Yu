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
     * This method allows the user to navigate through the calendar, view months, and select dates to view entries.
     * @param userInput Thsi is the scanner to read user input.
     * @param calendar The calendar object containing entries.
     * @return Returns true if the user logs out, false otherwise.
     */
    public boolean calendarNavigation(Scanner userInput, Calendar calendar) {
        YearMonth currentMonth = YearMonth.now(); 
        int navigationChoice = -999;
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

            if (userInput.hasNextInt()) {
                navigationChoice = userInput.nextInt();
                userInput.nextLine();

                if (navigationChoice == 1) {
                    currentMonth = currentMonth.minusMonths(1);
                } else if (navigationChoice == 2) {
                    currentMonth = currentMonth.plusMonths(1);
                } else if (navigationChoice == 3) {
                    System.out.print("Enter month (1-12): ");
                    if (userInput.hasNextInt()) {
                        int month = userInput.nextInt();
                        System.out.print("Enter year: ");
                        if (userInput.hasNextInt()) {
                            int year = userInput.nextInt();
                            userInput.nextLine();
                            if (month >= 1 && month <= 12) {
                                currentMonth = YearMonth.of(year, month);
                            } else {
                                System.out.println("Invalid month.\n");
                            }
                        } else {
                            System.out.println("Invalid year input.\n");
                            userInput.nextLine();
                        }
                    } else {
                        System.out.println("Invalid month input.\n");
                        userInput.nextLine();
                    }
                } else if (navigationChoice == 4) {
                    if (selectDateToView(userInput, calendar, currentMonth)) {
                        isLoggingOut = true;
                    }
                } else if (navigationChoice == 0) {
                    System.out.println("Returning to calendar menu...\n");
                } else if (navigationChoice == -1) {
                    System.out.println("Logging out...\n");
                    isLoggingOut = true;
                } else {
                    System.out.println("Invalid choice. Please try again.\n");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.\n");
                userInput.nextLine();
            }

        } while (navigationChoice != 0 && !isLoggingOut);

        return isLoggingOut;
    }

    /**
     * This method displays the month view of the calendar.
     * 
     * @param calendar The calendar object containing entries.
     * @param yearMonth The YearMonth object representing the month to display.
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

    /**
     * This method checks if a calendar has any entry on the given date.
     * 
     * @param calendar The calendar to check.
     * @param date The date to check entries for.
     * @return True if an entry exists on that date, false otherwise.
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
     * This method allows the user to select a specific day and interact with entries on that day.
     * 
     * @param userInput Scanner to read input.
     * @param calendar The calendar to view.
     * @param yearMonth The current month/year being displayed.
     * @return True if the user logs out; false otherwise.
     */
    public boolean selectDateToView(Scanner userInput, Calendar calendar, YearMonth yearMonth) {
        System.out.print("Enter the day you want to view (example: 5 for the 5th day): ");
        boolean isLoggingOut = false;

        if (userInput.hasNextInt()) {
            int day = userInput.nextInt();
            userInput.nextLine();

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

                    if (userInput.hasNextInt()) {
                        int choice = userInput.nextInt();
                        userInput.nextLine();

                        if (choice == 1) {
                            addEntryOnDate(userInput, calendar, selectedDate);
                        } else if (choice == 2) {
                            deleteEntryOnDate(userInput, calendar, selectedDate);
                        } else if (choice == 3) {
                            editEntryOnDate(userInput, calendar, selectedDate);
                        } else if (choice == 0) {
                            stayInDateMenu = false;
                        } else if (choice == -1) {
                            System.out.println("Logging out...");
                            isLoggingOut = true;
                            stayInDateMenu = false;
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        userInput.nextLine();
                    }
                }
            } else {
                System.out.println("Invalid day.\n");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.\n");
            userInput.nextLine();
        }

        return isLoggingOut;
    }

    /**
     * This method adds a new entry on the specified date.
     * 
     * @param userInput Scanner for input.
     * @param calendar Calendar to add the entry to.
     * @param date The date the entry is for.
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
     * This method allows the user to delete an entry on a specific date.
     * 
     * @param userInput Scanner for user input.
     * @param calendar Calendar to delete from.
     * @param date The target date of the entry.
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

            if (userInput.hasNextInt()) {
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
            } else {
                System.out.println("Invalid input. Please enter a number.");
                userInput.nextLine();
            }
        }
    }

    /**
     * This method edits an entry on the given date.
     * 
     * @param userInput Scanner to read input.
     * @param calendar Calendar to edit.
     * @param date The target date of the entry.
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

            if (userInput.hasNextInt()) {
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
            } else {
                System.out.println("Invalid input. Please enter a number.");
                userInput.nextLine();
            }
        }
    }
}
