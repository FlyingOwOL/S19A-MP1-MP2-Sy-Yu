package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.Meeting;
import Views.AddEntryPopUps.AddMeeting;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * EditMeetingListener handles the logic for updating a Meeting entry
 * when the user confirms edits made through the AddMeeting pop-up form.
 */
public class EditMeetingListener implements ActionListener {
    private AddMeeting popUp;   // The pop-up window for editing the meeting entry
    private Meeting entry;      // The meeting entry being edited

    /**
     * Constructs the listener for editing a meeting entry.
     *
     * @param popUp the meeting editing form
     * @param entry the meeting entry to be edited
     */
    public EditMeetingListener(AddMeeting popUp, Meeting entry) {
        this.popUp = popUp;
        this.entry = entry;

        this.popUp.setTitle(entry.getTitle());
        this.popUp.setModality(entry.getModality());
        this.popUp.setVenueField(entry.getVenue());
        this.popUp.setLinkField(entry.getLink());
        this.popUp.setStartDateField(entry.getStartDate().toString());
        this.popUp.setEndDateField(entry.getEndDate().toString());
        this.popUp.setEndTime(entry.getEndDateTime().toString());
        this.popUp.setStartTime(entry.getStartDateTime().toString());
        this.popUp.setDetailArea(entry.getDetails());
        this.popUp.updateGUI();
    }

    /**
     * Called when the user clicks the edit confirmation button.
     * Updates the associated Meeting object with new input values.
     * Displays a success message on completion.
     * If an error occurs, an error message is shown using a dialog.
     *
     * @param e the triggered ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String newTitle = popUp.getTitleField().getText().trim();
            String newModality = (String) popUp.getModalityBox().getSelectedItem();
            String newVenue = popUp.getVenueField().getText().trim();
            String newLink = popUp.getLinkField().getText().trim();
            String newStartDate = popUp.getStartDateField().getText().trim();
            String newEndDate = popUp.getEndDateField().getText().trim();
            String newStartTime = (String) popUp.getStartTimeBox().getSelectedItem();
            String newEndTime = (String) popUp.getEndTimeBox().getSelectedItem();
            String newDetail = popUp.getDetailArea().getText();

            entry.setTitle(newTitle);
            entry.setModality(newModality);
            entry.setVenue(newVenue);
            entry.setLink(newLink);
            entry.setStartDate(newStartDate);
            entry.setEndDate(newEndDate);
            entry.setStartTime(newStartTime);
            entry.setEndTime(newEndTime);
            entry.setDetails(newDetail);

            JOptionPane.showMessageDialog(popUp, 
                "Edit Successful", 
                newTitle, 
                JOptionPane.INFORMATION_MESSAGE);
            popUp.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(popUp, 
                "An error occurred while editing the meeting:\n" + ex.getMessage(), 
                "Edit Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
