package Controllers.Listeners_Controllers;

import Controllers.CalendarDateController;
import Views.AccountPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextDateListener implements ActionListener {
    private AccountPage accountPage;               // The view that displays the calendar and user interface
    private CalendarDateController dateController; // The controller that handles calendar date navigation

    /**
     * Creates a listener that moves the calendar to the next date range.
     *
     * @param accountPage the account page where the calendar is shown
     * @param dateController the controller that manages calendar date changes
     */
    public NextDateListener(AccountPage accountPage, CalendarDateController dateController) {
        this.accountPage = accountPage;
        this.dateController = dateController;
    }

    /**
     * Handles the action when the "next" button is clicked.
     * Moves the calendar view to the next week or next month,
     * depending on the currently selected display mode.
     *
     * @param e the action event triggered by the button click
     */
    public void actionPerformed(ActionEvent e) {
        try {
            String displayMode = accountPage.getSelectedCalendarDisplay();
            if ("Week".equals(displayMode)) {
                dateController.navigateToNextWeek();
            } else {
                dateController.navigateToNextMonth();
            }
        } catch (Exception ex) {
            System.out.println("Error navigating to next date: " + ex.getMessage());
        }
    }
}
