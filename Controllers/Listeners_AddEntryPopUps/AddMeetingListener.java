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
            try{
                // For JTextField components
                String title = addMeeting.getTitleField().getText();
                String modality = (String)addMeeting.getModalityBox().getSelectedItem();
                String venue = addMeeting.getVenueField().getText();
                String link = addMeeting.getLinkField().getText();
                String startDate = addMeeting.getStartDateField().getText();
                String endDate = addMeeting.getEndDateField().getText();
                String startTime = (String)addMeeting.getStartTimeBox().getSelectedItem(); 
                String endTime = (String)addMeeting.getEndTimeBox().getSelectedItem();

                // For JTextArea component
                String details = addMeeting.getDetailArea().getText();

                Meeting newMeeting = new Meeting(title, modality);
                newMeeting.setLink(link);
                newMeeting.setVenue(venue);
                newMeeting.setStartDate(startDate);
                newMeeting.setEndDate(endDate);
                newMeeting.setDetails(details);
                newMeeting.setStartTime(startTime);
                newMeeting.setEndTime(endTime);

                accountPage.getCurrentCalendar().addEntry(newMeeting);
            }catch(DateTimeParseException ex){
                // Show a pop-up message if the date format is wrong
                JOptionPane.showMessageDialog(addMeeting, "The date format is incorrect. Please use the format 'M/d/yyyy' for dates.", "Invalid Date Format", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    
}
