package Controllers;

import Views.AccountPage;
import Views.AddEntryPopUps.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EntriesPopUpsController {
    private AccountPage accountPage;

    public EntriesPopUpsController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.accountPage.selectPopUps(new PopUpSelection());
    }

    class PopUpSelection implements ActionListener {
        String selectedEntry;
        public void actionPerformed(ActionEvent e) {
            String selectedEntry;
            try {
                selectedEntry = accountPage.getSelectedEntry();
                // Logic to handle the selected entry type
                JFrame popUp = null;
                switch (selectedEntry){
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
                }

                if (popUp != null) {
                    popUp.setLocationRelativeTo(accountPage); //centers it to accountPage;
                }
            } catch (Exception ex) {
                System.out.println("Error in selecting entry type: ");
            }
        }
    }
    
}
