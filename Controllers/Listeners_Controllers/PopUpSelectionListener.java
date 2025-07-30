package Controllers.Listeners_Controllers;

import Controllers.Listeners_AddEntryPopUps.AddEventListener;
import Controllers.Listeners_AddEntryPopUps.AddJournalListener;
import Controllers.Listeners_AddEntryPopUps.AddMeetingListener;
import Controllers.Listeners_AddEntryPopUps.AddTaskListener;
import Views.AccountPage;
import Views.AddEntryPopUps.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class handles the logic for showing the appropriate pop-up
 * window based on the selected entry type (Task, Event, Meeting, Journal)
 * in the AccountPage. It assigns the corresponding listener for the
 * pop-up's confirmation button.
 */
public class PopUpSelectionListener implements ActionListener {
    private AccountPage accountPage; // Reference to the current AccountPage

    /**
     * Constructs a PopUpSelectionListener with the given AccountPage.
     *
     * @param accountPage The AccountPage where the entry selection is made.
     */
    public PopUpSelectionListener(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    /**
     * Called when an action occurs (e.g., "Add Entry" button is clicked).
     * Displays the correct pop-up based on the selected entry type.
     *
     * @param e The ActionEvent triggering this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String selectedEntry = accountPage.getSelectedEntry();
            System.out.println("Selected entry: " + selectedEntry);
            JFrame popUp = null;

            switch (selectedEntry) {
                case "Task":
                    popUp = new AddTask();
                    addNewTask((AddTask) popUp, accountPage);
                    break;
                case "Event":
                    popUp = new AddEvent();
                    addNewEvent((AddEvent) popUp, accountPage);
                    break;
                case "Meeting":
                    System.out.println("Meeting case reached!");
                    popUp = new AddMeeting();
                    addNewMeeting((AddMeeting) popUp, accountPage);
                    break;
                case "Journal":
                    popUp = new AddJournal();
                    addNewJournal((AddJournal) popUp, accountPage);
                    break;
            }

            if (popUp != null) {
                popUp.setLocationRelativeTo(accountPage); // Center popup relative to account page
                popUp.setVisible(true); // Show the popup
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(accountPage,
                "An error occurred while selecting entry type:\n\n" + ex.getMessage(),
                "Navigation Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Assigns the AddEvent pop-up and its listener to the account page.
     *
     * @param popUp       The AddEvent pop-up frame.
     * @param accountPage The current AccountPage.
     */
    private void addNewEvent(AddEvent popUp, AccountPage accountPage) {
        accountPage.setAddEvent(popUp);
        accountPage.getAddEvent().setButtonActionListener(new AddEventListener(popUp, accountPage));
    }

    /**
     * Assigns the AddTask pop-up and its listener to the account page.
     *
     * @param popUp       The AddTask pop-up frame.
     * @param accountPage The current AccountPage.
     */
    private void addNewTask(AddTask popUp, AccountPage accountPage) {
        accountPage.setAddTask(popUp);
        accountPage.getAddTask().setButtonActionListener(new AddTaskListener(popUp, accountPage));
    }

    /**
     * Assigns the AddMeeting pop-up and its listener to the account page.
     *
     * @param popUp       The AddMeeting pop-up frame.
     * @param accountPage The current AccountPage.
     */
    private void addNewMeeting(AddMeeting popUp, AccountPage accountPage) {
        System.out.println("addNewMeeting method called!");
        accountPage.setAddMeeting(popUp);
        accountPage.getAddMeeting().setButtonActionListener(new AddMeetingListener(popUp, accountPage));
        System.out.println("Meeting listener connected!");
    }

    /**
     * Assigns the AddJournal pop-up and its listener to the account page.
     *
     * @param popUp       The AddJournal pop-up frame.
     * @param accountPage The current AccountPage.
     */
    private void addNewJournal(AddJournal popUp, AccountPage accountPage) {
        accountPage.setAddJournal(popUp);
        accountPage.getAddJournal().setButtonActionListener(new AddJournalListener(popUp, accountPage));
    }
}
