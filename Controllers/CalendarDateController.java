package Controllers;

import Views.AccountPage;
import Controllers.Listeners_Controllers.PreviousDateListener;
import Controllers.Listeners_Controllers.NextDateListener;
import Controllers.Listeners_Controllers.JumpDateListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class CalendarDateController {
    private AccountPage accountPage;
    private LocalDate currentDate; // Changed to LocalDate
    private DateTimeFormatter displayFormatter;

    public CalendarDateController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.currentDate = LocalDate.now(); // Initialize with current date
        this.displayFormatter = DateTimeFormatter.ofPattern("MMM - yyyy");

        // Connect the navigation listeners
        setupNavigationListeners();

        // Initial display
        updateDateDisplay();
    }

    private void setupNavigationListeners() {
        // Connect Previous button
        accountPage.setPreviousButtonListener(new PreviousDateListener(accountPage, this));

        // Connect Next button
        accountPage.setNextButtonListener(new NextDateListener(accountPage, this));

        // Connect Jump Date button
        accountPage.setJumpDateButtonListener(new JumpDateListener(accountPage, this));
    }

    public void updateDateDisplay() {
        String displayText;
        boolean isWeekView = "Week".equals(accountPage.getSelectedCalendarDisplay());

        if (isWeekView) {
            // Calculate the start and end of the week
            LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate endOfWeek = startOfWeek.plusDays(6); // End of the week is 6 days after the start

            // Check if the start and end months are the same
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

        // Update the calendar view to reflect the new date
        updateCalendarView();
    }

    private void updateCalendarView() {
        // Force the calendar view to refresh with the new date
        accountPage.updateCalendarViewsWithDate(currentDate);
    }

    public void navigateToPreviousMonth() {
        currentDate = currentDate.minusMonths(1);
        updateDateDisplay();
    }

    public void navigateToNextMonth() {
        currentDate = currentDate.plusMonths(1);
        updateDateDisplay();
    }

    public void navigateToPreviousWeek() {
        // Move to the previous week based on the start of the current week
        currentDate = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).minusWeeks(1);
        updateDateDisplay();
    }

    public void navigateToNextWeek() {
        // Move to the next week based on the start of the current week
        currentDate = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).plusWeeks(1);
        updateDateDisplay();
    }

    public void jumpToDate(int month, int year) {
        LocalDate newDate = LocalDate.of(year, month, 1);
        newDate = newDate.withDayOfMonth(Math.min(currentDate.getDayOfMonth(), newDate.lengthOfMonth()));
        currentDate = newDate;
        updateDateDisplay();
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public int getCurrentYear() {
        return currentDate.getYear();
    }

    public int getCurrentMonth() {
        return currentDate.getMonthValue();
    }

    public int getCurrentDay() {
        return currentDate.getDayOfMonth();
    }

    public String getCurrentMonthName() {
        return currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public String getFormattedCurrentDate() {
        return currentDate.format(displayFormatter);
    }
}
