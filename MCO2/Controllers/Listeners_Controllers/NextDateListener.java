package Controllers.Listeners_Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers.CalendarDateController;
import Views.AccountPage;

public class NextDateListener implements ActionListener {
    private AccountPage accountPage;
    private CalendarDateController dateController;

    public NextDateListener(AccountPage accountPage, CalendarDateController dateController) {
        this.accountPage = accountPage;
        this.dateController = dateController;
    }

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
