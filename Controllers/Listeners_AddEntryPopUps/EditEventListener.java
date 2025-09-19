package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.EventEntry;
import Views.AddEntryPopUps.AddEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener class that handles editing an existing EventEntry using the AddEvent popup form.
 * It pre-fills the form fields with the existing event's data and updates the event upon submission.
 */
public class EditEventListener implements ActionListener {
    private AddEvent popUp;     // The AddEvent form used for editing
    private EventEntry entry;   // The EventEntry to be edited 

    /**
     * Constructs an EditEventListener and populates the AddEvent form with the existing entry's data.
     *
     * @param popUp the AddEvent form used for editing
     * @param entry the EventEntry to be edited
     */
    public EditEventListener(AddEvent popUp, EventEntry entry) {
        this.popUp = popUp;
        this.entry = entry;

        this.popUp.setTitle(entry.getTitle());
        this.popUp.setOrganizerField(entry.getOrganizer());
        this.popUp.setVenueField(entry.getVenue());
        this.popUp.setStartDateField(entry.getStartDate().toString());
        this.popUp.setEndDateField(entry.getEndDate().toString());
        this.popUp.setEndTime(entry.getEndTime().toString());
        this.popUp.setStartTime(entry.getStartTime().toString());
        this.popUp.setDetailArea(entry.getDetails());
        this.popUp.updateGUI();
    }

    /**
     * Handles the action event for saving the edited EventEntry.
     * Validates and updates the entry with new data from the form fields.
     *
     * @param e the action event triggered when the user submits the form
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // These lines retrieve the data from the form fields
            String newTitle = popUp.getTitleField().getText();
            String newOrganizer = popUp.getOrganizerField().getText();
            String newVenue = popUp.getVenueField().getText();
            String newStartDate = popUp.getStartDateField().getText();
            String newEndDate = popUp.getEndDateField().getText();
            String newStartTime = (String) popUp.getStartTime().getSelectedItem();
            String newEndTime = (String) popUp.getEndTime().getSelectedItem();
            String newDetails = popUp.getDetailArea().getText();

            entry.setTitle(newTitle);
            entry.setOrganizer(newOrganizer);
            entry.setVenue(newVenue);
            entry.setStartDate(newStartDate);
            entry.setEndDate(newEndDate);
            entry.setStartTime(newStartTime);
            entry.setEndTime(newEndTime);
            entry.setDetails(newDetails);

            JOptionPane.showMessageDialog(popUp,
                "Edit Successful",
                newTitle,
                JOptionPane.INFORMATION_MESSAGE);
            popUp.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(popUp,
                "An error occurred while editing the event entry: \n\n" + ex.getMessage(),
                "Edit Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
