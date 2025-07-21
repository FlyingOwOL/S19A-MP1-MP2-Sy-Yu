package Controllers.Listeners_Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers.CalendarDateController;
import Views.AccountPage;

public class PreviousDateListener implements ActionListener {
    private AccountPage accountPage;
    private CalendarDateController dateController;

    public PreviousDateListener(AccountPage accountPage, CalendarDateController dateController) {
        this.accountPage = accountPage;
        this.dateController = dateController;
    }

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
