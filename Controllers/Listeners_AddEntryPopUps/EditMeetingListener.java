package Controllers.Listeners_AddEntryPopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.Entry.Meeting;
import Views.AddEntryPopUps.AddMeeting;

public class EditMeetingListener implements ActionListener {
    private AddMeeting popUp;
    private Meeting entry; // Change to Meeting

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

    @Override
    public void actionPerformed(ActionEvent e) {
        try{

            String newTitle = popUp.getTitleField().getText().trim();
            String newModality = (String)popUp.getModalityBox().getSelectedItem();
            String newVenue = popUp.getVenueField().getText().trim();
            String newLink = popUp.getLinkField().getText().trim();
            String newStartDate = popUp.getStartDateField().getText().trim();
            String newEndDate = popUp.getEndDateField().getText().trim();
            String newStartTime = (String)popUp.getStartTimeBox().getSelectedItem(); 
            String newEndTime = (String)popUp.getEndTimeBox().getSelectedItem();
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
        }catch(Exception ex){
        
        }
    }
}
   
