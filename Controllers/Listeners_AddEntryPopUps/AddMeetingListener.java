package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.Meeting;
import Views.AccountPage;
import Views.AddEntryPopUps.AddMeeting;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;


/**
 * Handles the creation of a Meeting entry when the user submits the form in the AddMeeting pop-up.
 * Validates inputs, creates the entry, and adds it to the current calendar.
 */
public class AddMeetingListener implements ActionListener {

    private AddMeeting addMeeting;
    private AccountPage accountPage;

    /**
     * Constructs the listener with the AddMeeting pop-up and AccountPage reference.
     *
     * @param addMeeting  the meeting entry form
     * @param accountPage the user's account page
     */
    public AddMeetingListener(AddMeeting addMeeting, AccountPage accountPage) {
        this.addMeeting = addMeeting;
        this.accountPage = accountPage;
    }

    /**
     * Triggered when the submit button is clicked.
     * Validates required fields and creates a new Meeting entry if valid.
     *
     * @param e the action event triggered
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMeeting.getSubmitButton()) {
            try {
                // These lines retrieve the data from the form fields
                String title = addMeeting.getTitleField().getText().trim();
                String modality = (String) addMeeting.getModalityBox().getSelectedItem();
                String venue = addMeeting.getVenueField().getText().trim();
                String link = addMeeting.getLinkField().getText().trim();
                String startDate = addMeeting.getStartDateField().getText().trim();
                String endDate = addMeeting.getEndDateField().getText().trim();
                String startTime = (String) addMeeting.getStartTimeBox().getSelectedItem(); 
                String endTime = (String) addMeeting.getEndTimeBox().getSelectedItem();

                boolean stop = false;
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter a title", "Title Required", JOptionPane.WARNING_MESSAGE);
                    stop = true;
                }

                if (startDate.isEmpty() && !stop) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter a start date", "Start Date Required", JOptionPane.WARNING_MESSAGE);
                    stop = true;
                }

                if (endDate.isEmpty() && !stop) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter an end date", "End Date Required", JOptionPane.WARNING_MESSAGE);
                    stop = true;
                }

                if (!stop) {
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
                    "The date format is incorrect. Please use the format YYYY-MM-DD for dates.",
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
