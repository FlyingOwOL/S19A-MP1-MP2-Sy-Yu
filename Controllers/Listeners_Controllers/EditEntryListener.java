package Controllers.Listeners_Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controllers.Listeners_AddEntryPopUps.*;
import Models.Entry.*;
import Views.AccountPage;
import Views.AddEntryPopUps.*;


public class EditEntryListener implements ActionListener{
    private AccountPage accountPage;
    private EntryModel entry;

    public EditEntryListener(AccountPage accountPage){
        this.accountPage = accountPage;
        this.entry = (EntryModel)accountPage.getEntriesDisplayView().getEntriesBox().getSelectedItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String optionSelected = "";

        if (entry instanceof EventEntry){optionSelected = "Event";}
        else if (entry instanceof Journal){optionSelected = "Journal";}
        else if (entry  instanceof Meeting){optionSelected = "Meeting";}
        else if (entry instanceof Task){optionSelected = "Task";}
        
        if (!optionSelected.isBlank()){
            try {
                System.out.println("Selected entry: " + optionSelected);
                JFrame popUp = null;

                switch (optionSelected) {
                    case "Task":
                        popUp = new AddTask();
                        EditTask ((AddTask)popUp, (Task)entry);
                        break;
                    case "Event":
                        popUp = new AddEvent();
                        EditEvent ((AddEvent)popUp, (EventEntry)entry);
                        break;
                    case "Meeting":
                        popUp = new AddMeeting();
                        EditMeeting ((AddMeeting)popUp, (Meeting)entry);
                        break;
                    case "Journal":
                        popUp = new AddJournal();
                        EditJournal ((AddJournal)popUp, (Journal)entry);
                        break;
                }

                if (popUp != null) {
                    popUp.setLocationRelativeTo(accountPage);
                    popUp.setVisible(true);
                }
                accountPage.getEntriesDisplayView().updateGUI();
            } catch (Exception ex) {
                System.out.println("Error in account selection: " + ex.getMessage());
                ex.printStackTrace(); // This will help debug
            }
        } else {
            JOptionPane.showMessageDialog(accountPage,
             "No available entry to edit",
              "No entries", 
              JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void EditJournal(AddJournal popUp, Journal entry) {
        accountPage.setAddJournal(popUp);
        accountPage.getAddJournal().setButtonActionListener(new EditJournalListener(popUp, entry));
    }

    private void EditMeeting(AddMeeting popUp, Meeting entry) {
        accountPage.setAddMeeting(popUp);
        accountPage.getAddMeeting().setButtonActionListener(new EditMeetingListener(popUp, entry));
    }

    private void EditEvent(AddEvent popUp, EventEntry entry) {
        accountPage.setAddEvent(popUp);
        accountPage.getAddEvent().setButtonActionListener(new EditEventListener(popUp, entry));
    }

    private void EditTask(AddTask popUp, Task entry) {
        accountPage.setAddTask(popUp);
        accountPage.getAddTask().setButtonActionListener(new EditTaskListener(popUp, entry));
    }
}
