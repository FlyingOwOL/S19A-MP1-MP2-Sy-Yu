package Controllers.Listeners_Controllers;

import Views.AccountPage;
import Views.EntriesDisplayView;
import Views.Add_Delete_Calendar_PopUps.*;

import Controllers.LoginController;
import Controllers.Listeners_Add_Delete_Calendar_PopUps.*;

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
                case "View Entries":
                    popUp = new EntriesDisplayView(this.accountPage.getCurrentCalendar());
                    break;
                case "Switch":
                    popUp = new SwitchCalendarFrame();
                    switchCalendar((SwitchCalendarFrame)popUp, accountPage);
                    break;
                case "Add Calendar":
                    popUp = new AddCalendarFrame();
                    addNewCalendar((AddCalendarFrame)popUp, accountPage);
                    break;
                case "Delete Calendar":
                    popUp = new DeleteCalendarFrame();
                    deleteCalendar((DeleteCalendarFrame)popUp, accountPage);
                    break;
                case "View Journal":
                    popUp = new ViewJournal();
                    viewJournal((ViewJournal)popUp, accountPage);
                    break;
                case "Sign out":
                    accountPage.dispose();
                    new LoginController();
                    return; // Don't try to show popup for sign out
            }

            if (popUp != null) {
                popUp.setLocationRelativeTo(accountPage);
                popUp.setVisible(true);
            }
        } catch (Exception ex) {
            System.out.println("Error in account selection: " + ex.getMessage());
            ex.printStackTrace(); // This will help debug
        }
    }

    private void addNewCalendar(AddCalendarFrame popUp, AccountPage accountPage) {
        accountPage.setAddCalendarFrame(popUp);
        // The key fix: Create the listener that actually handles the Add button
        AddCalendarListener listener = new AddCalendarListener(popUp, accountPage);
        popUp.setButtonActionListener(listener); // This connects the Add button to the listener
    }

    private void switchCalendar(SwitchCalendarFrame popUp, AccountPage accountPage) {
        accountPage.setSwitchCalendarFrame(popUp);
        new SwitchCalendarListener(popUp, accountPage);
    }

    private void deleteCalendar(DeleteCalendarFrame popUp, AccountPage accountPage) {
        accountPage.setDeleteCalendarFrame(popUp);
        new DeleteCalendarListener(popUp, accountPage);
    }

    private void viewJournal(ViewJournal popUp, AccountPage accountPage) {
        accountPage.setViewJournal(popUp);
        new ViewJournalListener(popUp, accountPage);
    }
}