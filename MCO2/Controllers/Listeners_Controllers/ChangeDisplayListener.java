package Controllers.Listeners_Controllers;

import Views.AccountPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeDisplayListener implements ActionListener {
    private AccountPage accountPage;

    public ChangeDisplayListener(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String selectedDisplay = accountPage.getSelectedCalendarDisplay();
            accountPage.changeCalendarDisplay(selectedDisplay);
        } catch (NumberFormatException ex) {
            System.out.println("Error in changing calendar display:");
        }
    }
}
