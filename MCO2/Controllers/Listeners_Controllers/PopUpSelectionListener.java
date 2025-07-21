package Controllers.Listeners_Controllers;

import Views.AccountPage;
import Views.AddEntryPopUps.*;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpSelectionListener implements ActionListener {
    private AccountPage accountPage;

    public PopUpSelectionListener(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String selectedEntry = accountPage.getSelectedEntry();
            JFrame popUp = null;

            switch (selectedEntry) {
                case "Task":
                    popUp = new AddTask();
                    break;
                case "Event":
                    popUp = new AddEvent();
                    break;
                case "Meeting":
                    popUp = new AddMeeting();
                    break;
                case "Journal":
                    popUp = new AddJournal();
                    break;
                default:
                    System.out.println("Unrecognized entry type: " + selectedEntry);
                    return;
            }

            if (popUp != null) {
                popUp.setLocationRelativeTo(accountPage); // Center popup relative to account page
                popUp.setVisible(true); // Don't forget this!
            }
        } catch (Exception ex) {
            System.out.println("Error in selecting entry type: " + ex.getMessage());
        }
    }
}
