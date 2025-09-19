package Controllers;

import Controllers.Listeners_Controllers.JumpDateListener;
import Controllers.Listeners_Controllers.NextDateListener;
import Controllers.Listeners_Controllers.PreviousDateListener;
import Views.AccountPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Controls the navigation and display of calendar dates on the AccountPage.
 * 
 * Supports month and week views, and allows navigation to previous/next month or week,
 * as well as jumping to a specific month/year. Updates the GUI accordingly.
 */
public class CalendarDateController {
    private AccountPage accountPage;            // The AccountPage view this controller manages
    private LocalDate currentDate;              // The currently selected date in the calendar
    private DateTimeFormatter displayFormatter; // Formatter for displaying the date in "MMM - yyyy" format

    /**
     * Constructs a CalendarDateController associated with the given AccountPage.
     * Initializes the current date and sets up navigation button listeners.
     * 
     * @param accountPage the AccountPage view this controller manages
     */
    public CalendarDateController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.currentDate = LocalDate.now();
        this.displayFormatter = DateTimeFormatter.ofPattern("MMM - yyyy");

        setupNavigationListeners();
        updateDateDisplay();
    }

    /**
     * Sets up listeners for the navigation buttons (Previous, Next, Jump Date).
     */
    private void setupNavigationListeners() {
        accountPage.setPreviousButtonListener(new PreviousDateListener(accountPage, this));
        accountPage.setNextButtonListener(new NextDateListener(accountPage, this));
        accountPage.setJumpDateButtonListener(new JumpDateListener(accountPage, this));
    }

    /**
     * Updates the date display on the AccountPage, based on the selected view (Week or Month).
     * Also refreshes the calendar view.
     */
    public void updateDateDisplay() {
        String displayText;
        boolean isWeekView = "Week".equals(accountPage.getSelectedCalendarDisplay());

        if (isWeekView) {
            LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate endOfWeek = startOfWeek.plusDays(6);

            if (startOfWeek.getMonth() == endOfWeek.getMonth()) {
                displayText = String.format("%s %d",
                        startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        startOfWeek.getYear());
            } else {
                displayText = String.format("%s - %s %d",
                        startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        endOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        endOfWeek.getYear());
            }
        } else {
            displayText = currentDate.format(displayFormatter);
        }

        accountPage.updateDateLabel(displayText);
        updateCalendarView();
    }

    /**
     * Refreshes the calendar view with the current date.
     */
    private void updateCalendarView() {
        accountPage.updateCalendarViewsWithDate(currentDate);
    }

    /**
     * Navigates to the previous month and updates the display.
     */
    public void navigateToPreviousMonth() {
        currentDate = currentDate.minusMonths(1);
        updateDateDisplay();
    }

    /**
     * Navigates to the next month and updates the display.
     */
    public void navigateToNextMonth() {
        currentDate = currentDate.plusMonths(1);
        updateDateDisplay();
    }

    /**
     * Navigates to the previous week (starting on Monday) and updates the display.
     */
    public void navigateToPreviousWeek() {
        currentDate = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).minusWeeks(1);
        updateDateDisplay();
    }

    /**
     * Navigates to the next week (starting on Monday) and updates the display.
     */
    public void navigateToNextWeek() {
        currentDate = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).plusWeeks(1);
        updateDateDisplay();
    }

    /**
     * Jumps to the specified month and year, preserving the current day if possible.
     * 
     * @param month the target month (1-12)
     * @param year  the target year
     */
    public void jumpToDate(int month, int year) {
        LocalDate newDate = LocalDate.of(year, month, 1);
        newDate = newDate.withDayOfMonth(Math.min(currentDate.getDayOfMonth(), newDate.lengthOfMonth()));
        currentDate = newDate;
        updateDateDisplay();
    }

    /**
     * Returns the currently selected date.
     * 
     * @return the current LocalDate
     */
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    /**
     * Returns the current year.
     * 
     * @return the current year as an integer
     */
    public int getCurrentYear() {
        return currentDate.getYear();
    }

    /**
     * Returns the current month (1–12).
     * 
     * @return the current month as an integer
     */
    public int getCurrentMonth() {
        return currentDate.getMonthValue();
    }

    /**
     * Returns the current day of the month.
     * 
     * @return the current day as an integer
     */
    public int getCurrentDay() {
        return currentDate.getDayOfMonth();
    }

    /**
     * Returns the full name of the current month (e.g., "January").
     * 
     * @return the name of the current month
     */
    public String getCurrentMonthName() {
        return currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    /**
     * Returns a formatted string of the current date in "MMM - yyyy" format.
     * 
     * @return the formatted current date
     */
    public String getFormattedCurrentDate() {
        return currentDate.format(displayFormatter);
    }
}