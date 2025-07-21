package Controllers.Listeners_Controllers;

import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.*;

import Controllers.Listeners_Add_Delete_Calendar_PopUps.AddCalendarListener;
import Controllers.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountSelectionListener implements ActionListener {
    private AccountPage accountPage;

    public AccountSelectionListener(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String optionSelected;
        try {
            optionSelected = accountPage.getSelectedAccount();
            JFrame popUp = null;
            switch (optionSelected) {
                case "Switch":
                    popUp = new SwitchCalendarFrame();
                    break;
                case "Add Calendar":
                    popUp = new AddCalendarFrame();
                    addNewCalendar((AddCalendarFrame) popUp);
                    break;
                case "Delete Calendar":
                    popUp = new DeleteCalendarFrame();
                    break;
                case "View Journal":
                    popUp = new ViewJournal();
                    break;
                case "Sign out":
                    accountPage.dispose();
                    new LoginController();
                    break;
            }
            if (popUp != null) {
                popUp.setLocationRelativeTo(accountPage);
            }
        } catch (Exception ex) {
            System.out.println("Error in account selection: " + ex.getMessage());
        }
    }

    private void addNewCalendar(AddCalendarFrame popUp) {
        accountPage.getAddCalendarFrame().setButtonActionListener(new AddCalendarListener());
    }
}
