package Controllers.Listeners_Controllers;

import Controllers.Listeners_AddEntryPopUps.*;
import Models.Entry.*;
import Views.AccountPage;
import Views.AddEntryPopUps.*;
import Views.EntriesDisplayView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This listener handles the editing of different types of entries
 * (Task, Event, Meeting, Journal) from the EntriesDisplayView in the AccountPage.
 * It identifies the selected entry type and initializes the corresponding editing popup.
 */
public class EditEntryListener implements ActionListener {
    private AccountPage accountPage;                // The main account page where the user manages their calendar
    private EntryModel entry;                       // The currently selected entry to be edited    
    private EntriesDisplayView entriesDisplayView;  // The view showing the list of entries

    /**
     * Constructs a listener for editing calendar entries.
     *
     * @param accountPage         The main account page where the user manages their calendar.
     * @param popUp               The pop-up view showing the list of entries.
     */
    public EditEntryListener(AccountPage accountPage, EntriesDisplayView popUp) {
        this.accountPage = accountPage;
        this.entriesDisplayView = popUp;
        this.entry = (EntryModel) popUp.getEntriesBox().getSelectedItem();
    }

    /**
     * Called upon when the edit button is clicked.
     * Determines the type of the selected entry and opens the corresponding edit pop-up.
     *
     * @param e The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == accountPage.getEntriesDisplayView().getEditButton()) {
            String optionSelected = "";

            // Determine the type of the entry
            if (entry instanceof EventEntry) {
                optionSelected = "Event";
            } else if (entry instanceof Journal) {
                optionSelected = "Journal";
            } else if (entry instanceof Meeting) {
                optionSelected = "Meeting";
            } else if (entry instanceof Task) {
                optionSelected = "Task";
            }

            // Proceed if a valid entry type was selected
            if (!optionSelected.isBlank()) {
                try {
                    System.out.println("Selected entry: " + optionSelected);
                    JFrame popUp = null;

                    // Open corresponding edit window
                    switch (optionSelected) {
                        case "Task":
                            popUp = new AddTask();
                            EditTask((AddTask) popUp, (Task) entry);
                            break;
                        case "Event":
                            popUp = new AddEvent();
                            EditEvent((AddEvent) popUp, (EventEntry) entry);
                            break;
                        case "Meeting":
                            popUp = new AddMeeting();
                            EditMeeting((AddMeeting) popUp, (Meeting) entry);
                            break;
                        case "Journal":
                            popUp = new AddJournal();
                            EditJournal((AddJournal) popUp, (Journal) entry);
                            break;
                    }

                    // Display pop-up if successfully initialized
                    if (popUp != null) {
                        popUp.setLocationRelativeTo(accountPage);
                        popUp.setVisible(true);
                        entriesDisplayView.updateGUI(accountPage.getCurrentCalendar());
                    }

                } catch (Exception ex) {
                    System.out.println("Error in account selection: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                // Show error if no valid entry is available
                JOptionPane.showMessageDialog(accountPage,
                        "No available entry to edit",
                        "No entries",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Sets up the edit behavior for a Journal entry.
     *
     * @param popUp The AddJournal pop-up window.
     * @param entry The Journal entry to edit.
     */
    private void EditJournal(AddJournal popUp, Journal entry) {
        accountPage.setAddJournal(popUp);
        accountPage.getAddJournal().setButtonActionListener(new EditJournalListener(popUp, entry));
    }

    /**
     * Sets up the edit behavior for a Meeting entry.
     *
     * @param popUp The AddMeeting pop-up window.
     * @param entry The Meeting entry to edit.
     */
    private void EditMeeting(AddMeeting popUp, Meeting entry) {
        accountPage.setAddMeeting(popUp);
        accountPage.getAddMeeting().setButtonActionListener(new EditMeetingListener(popUp, entry));
    }

    /**
     * Sets up the edit behavior for an Event entry.
     *
     * @param popUp The AddEvent pop-up window.
     * @param entry The EventEntry to edit.
     */
    private void EditEvent(AddEvent popUp, EventEntry entry) {
        accountPage.setAddEvent(popUp);
        accountPage.getAddEvent().setButtonActionListener(new EditEventListener(popUp, entry));
    }

    /**
     * Sets up the edit behavior for a Task entry.
     *
     * @param popUp The AddTask pop-up window.
     * @param entry The Task entry to edit.
     */
    private void EditTask(AddTask popUp, Task entry) {
        accountPage.setAddTask(popUp);
        accountPage.getAddTask().setButtonActionListener(new EditTaskListener(popUp, entry));
    }
}
