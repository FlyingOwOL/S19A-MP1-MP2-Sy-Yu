package Controllers.Listeners_AddEntryPopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.Entry.EventEntry;
import Views.AddEntryPopUps.AddEvent;

public class EditEventListener implements ActionListener{
    private AddEvent popUp;
    private EventEntry entry;
    
    public EditEventListener(AddEvent popUp, EventEntry entry){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String newTitle = popUp.getTitleField().getText();
            String newOrganizer = popUp.getOrganizerField().getText();
            String newVenue = popUp.getVenueField().getText();
            String newStartDate = popUp.getStartDateField().getText();
            String newEndDate = popUp.getEndDateField().getText();
            String newStartTime = (String)popUp.getStartTime().getSelectedItem();
            String newEndTime = (String)popUp.getEndTime().getSelectedItem();
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
        }catch(Exception ex){
        
        }
    }
}
