package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.Journal;
import Views.AddEntryPopUps.AddJournal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Handles editing an existing Journal entry from the AddJournal pop-up.
 * Updates the journal details and displays a confirmation message.
 */
public class EditJournalListener implements ActionListener {
    
    private AddJournal popUp;   // The pop-up window for editing the journal entry
    private Journal entry;      // The journal entry being edited

    /**
     * Constructs the listener for editing a journal entry.
     *
     * @param popUp the journal editing form
     * @param entry the journal entry to be edited
     */
    public EditJournalListener(AddJournal popUp, Journal entry) {
        this.popUp = popUp;
        this.entry = entry;

        this.popUp.setDetailArea(entry.getDetails());
        this.popUp.setMonthBox(entry.getMonth());
        this.popUp.updateGUI();
    }

    /**
     * Updates the journal entry when the user confirms changes.
     * Displays a success message and closes the form.
     *
     * @param e the triggered action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String newDetails = popUp.getDetailArea().getText().trim();
            entry.setDetails(newDetails);

            JOptionPane.showMessageDialog(popUp, 
                "Edit Successful", 
                "Changes", 
                JOptionPane.INFORMATION_MESSAGE);
            popUp.dispose();
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(popUp,
                "An error occurred while editing the journal entry: " + ex.getMessage(),
                "Edit Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
