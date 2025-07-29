package Controllers.Listeners_AddEntryPopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import Models.Entry.Meeting;
import Views.AccountPage;
import Views.AddEntryPopUps.AddMeeting;

public class AddMeetingListener implements ActionListener{
    
    private AddMeeting addMeeting;
    private AccountPage accountPage;

    public AddMeetingListener(AddMeeting addMeeting, AccountPage accountPage){
        this.addMeeting = addMeeting;
        this.accountPage = accountPage;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMeeting.getSubmitButton()){
            try {
                // For JTextField components - trim all inputs
                String title = addMeeting.getTitleField().getText().trim();
                String modality = (String)addMeeting.getModalityBox().getSelectedItem();
                String venue = addMeeting.getVenueField().getText().trim();
                String link = addMeeting.getLinkField().getText().trim();
                String startDate = addMeeting.getStartDateField().getText().trim();
                String endDate = addMeeting.getEndDateField().getText().trim();
                String startTime = (String)addMeeting.getStartTimeBox().getSelectedItem(); 
                String endTime = (String)addMeeting.getEndTimeBox().getSelectedItem();

                // Validate required fields
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter a title", "Title Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (startDate.isEmpty()) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter a start date", "Start Date Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (endDate.isEmpty()) {
                    JOptionPane.showMessageDialog(addMeeting, "Please enter an end date", "End Date Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // For JTextArea component
                String details = addMeeting.getDetailArea().getText().trim();

                Meeting newMeeting = new Meeting(title, modality);
                newMeeting.setLink(link);
                newMeeting.setVenue(venue);
                newMeeting.setStartDate(startDate);  // This will now handle parsing properly
                newMeeting.setEndDate(endDate);      // This will now handle parsing properly
                newMeeting.setDetails(details);
                newMeeting.setStartTime(startTime);
                newMeeting.setEndTime(endTime);

                accountPage.getCurrentCalendar().addEntry(newMeeting);
                
                // Show success message
                JOptionPane.showMessageDialog(addMeeting, 
                    "Meeting '" + title + "' created successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                addMeeting.dispose();
                
            } catch (DateTimeParseException ex) {
                // Show a pop-up message if the date format is wrong
                JOptionPane.showMessageDialog(addMeeting, 
                    "The date format is incorrect. Please use the format like '7/25/2025' for dates.", 
                    "Invalid Date Format", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Catch any other unexpected errors
                JOptionPane.showMessageDialog(addMeeting, 
                    "An error occurred: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
