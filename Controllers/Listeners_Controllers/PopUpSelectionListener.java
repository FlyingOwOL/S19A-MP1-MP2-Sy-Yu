package Controllers.Listeners_Controllers;

import Views.AccountPage;
import Views.AddEntryPopUps.*;

import javax.swing.JFrame;

import Controllers.Listeners_AddEntryPopUps.AddEventListener;
import Controllers.Listeners_AddEntryPopUps.AddJournalListener;
import Controllers.Listeners_AddEntryPopUps.AddMeetingListener;
import Controllers.Listeners_AddEntryPopUps.AddTaskListener;

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
                    addNewTask ((AddTask)popUp, accountPage);
                    break;
                case "Event":
                    popUp = new AddEvent();
                    addNewEvent ((AddEvent)popUp, accountPage);
                    break;
                case "Meeting":
                    popUp = new AddMeeting();
                    addNewMeeting ((AddMeeting)popUp, accountPage);
                    break;
                case "Journal":
                    popUp = new AddJournal();
                    addNewJournal ((AddJournal)popUp, accountPage);
                    break;
            }

            if (popUp != null) {
                popUp.setLocationRelativeTo(accountPage); // Center popup relative to account page
                popUp.setVisible(true); // Don't forget this!
            }
        } catch (Exception ex) {
            System.out.println("Error in selecting entry type: " + ex.getMessage());
        }
    }

    private void addNewEvent (AddEvent popUp, AccountPage accountPage) {
        accountPage.setAddEvent(popUp);
        accountPage.getAddEvent().setButtonActionListener(new AddEventListener(popUp, accountPage));
    }
    private void addNewTask (AddTask popUp, AccountPage accountPage){
        accountPage.setAddTask(popUp);
        accountPage.getAddTask().setButtonActionListener(new AddTaskListener(popUp, accountPage));
    }
    private void addNewMeeting (AddMeeting popUp, AccountPage accountPage){
        accountPage.setAddMeeting(popUp);
        accountPage.getAddMeeting().setButtonActionListener(new AddMeetingListener(popUp, accountPage));
    }
    private void addNewJournal (AddJournal popUp, AccountPage accountPage){
        accountPage.setAddJournal(popUp);
        accountPage.getAddJournal().setButtonActionListener(new AddJournalListener(popUp, accountPage));
    }
}
