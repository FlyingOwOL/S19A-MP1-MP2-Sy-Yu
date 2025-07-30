package Controllers.Listeners_Controllers;

import Controllers.CalendarDateController;
import Views.AccountPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousDateListener implements ActionListener {
    private AccountPage accountPage;               // The view that displays the calendar and user interface
    private CalendarDateController dateController; // The controller that handles calendar date navigation

    /**
     * Creates a listener that moves the calendar to the previous date range.
     *
     * @param accountPage the account page where the calendar is shown
     * @param dateController the controller that manages calendar date changes
     */
    public PreviousDateListener(AccountPage accountPage, CalendarDateController dateController) {
        this.accountPage = accountPage;
        this.dateController = dateController;
    }

    /**
     * Handles the action when the "previous" button is clicked.
     * Moves the calendar view to the previous week or previous month,
     * depending on the currently selected display mode.
     *
     * @param e the action event triggered by the button click
     */
    public void actionPerformed(ActionEvent e) {
        try {
            String displayMode = accountPage.getSelectedCalendarDisplay();
            if ("Week".equals(displayMode)) {
                dateController.navigateToPreviousWeek();
            } else {
                dateController.navigateToPreviousMonth();
            }
        } catch (Exception ex) {
            System.out.println("Error navigating to previous date: " + ex.getMessage());
        }
    }
}
