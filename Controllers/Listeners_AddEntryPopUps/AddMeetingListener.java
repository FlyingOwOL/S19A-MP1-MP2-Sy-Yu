package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.Meeting;
import Views.AccountPage;
import Views.AddEntryPopUps.AddMeeting;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/**
 * This class listens for the "Submit" button click, gathers user input,
 * creates a new Meeting object, and adds it to the current calendar.
 */
public class AddMeetingListener implements ActionListener {

    private AddMeeting addMeeting;           // Reference to the meeting pop-up window
    private AccountPage accountPage;         // Reference to the main account page

    /**
     * Constructs the AddMeetingListener.
     *
     * @param addMeeting the view component for meeting entry form
     * @param accountPage the main view managing the calendar
     */
    public AddMeetingListener(AddMeeting addMeeting, AccountPage accountPage) {
        this.addMeeting = addMeeting;
        this.accountPage = accountPage;
    }

    /**
     * Called when the "Submit" button is clicked.
     * 
     * @param e the ActionEvent triggered by the button click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMeeting.getSubmitButton()) {
            try {
                // For JTextField components - trim all inputs
                String title = addMeeting.getTitleField().getText().trim();
                String modality = (String) addMeeting.getModalityBox().getSelectedItem();
                String venue = addMeeting.getVenueField().getText().trim();
                String link = addMeeting.getLinkField().getText().trim();
                String startDate = addMeeting.getStartDateField().getText().trim();
                String endDate = addMeeting.getEndDateField().getText().trim();
                String startTime = (String) addMeeting.getStartTimeBox().getSelectedItem();
                String endTime = (String) addMeeting.getEndTimeBox().getSelectedItem();

                boolean hasError = false;

                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter a title", "Title Required", JOptionPane.WARNING_MESSAGE);
                    hasError = true;
                }

                if (startDate.isEmpty()) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter a start date", "Start Date Required", JOptionPane.WARNING_MESSAGE);
                    hasError = true;
                }

                if (endDate.isEmpty()) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter an end date", "End Date Required", JOptionPane.WARNING_MESSAGE);
                    hasError = true;
                }

                if (!hasError) {
                    // For JTextArea component
                    String details = addMeeting.getDetailArea().getText().trim();

                    Meeting newMeeting = new Meeting(title, modality);
                    newMeeting.setLink(link);
                    newMeeting.setVenue(venue);
                    newMeeting.setStartDate(startDate);
                    newMeeting.setEndDate(endDate);
                    newMeeting.setDetails(details);
                    newMeeting.setStartTime(startTime);
                    newMeeting.setEndTime(endTime);

                    accountPage.getCurrentCalendar().addEntry(newMeeting);

                    JOptionPane.showMessageDialog(addMeeting,
                            "Meeting '" + title + "' created successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    addMeeting.dispose();
                }

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(addMeeting,
                        "The date format is incorrect. Please use the format like '7/25/2025' for dates.",
                        "Invalid Date Format",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addMeeting,
                        "An error occurred: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}