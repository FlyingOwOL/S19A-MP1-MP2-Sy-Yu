package Controllers.Listeners_AddEntryPopUps;

import Views.AccountPage;
import Views.AddEntryPopUps.AddEvent;

import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import Models.Entry.EventEntry;

import java.awt.event.ActionEvent;

public class AddEventListener implements ActionListener {
    
    private AddEvent addEvent;
    private AccountPage accountPage;


    public AddEventListener(AddEvent addEvent, AccountPage accountPage) {
        this.addEvent = addEvent;
        this.accountPage = accountPage;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEvent.getSubmitButton()){
            try {
                String title = addEvent.getTitleField().getText();
                String organizer = addEvent.getOrganizerField().getText();
                String venue = addEvent.getVenueField().getText();
                String startDate = addEvent.getStartDateField().getText();
                String endDate = addEvent.getEndDateField().getText();
                String startTime = (String)addEvent.getStartTime().getSelectedItem();
                String endTime = (String)addEvent.getEndTime().getSelectedItem();
                String details = addEvent.getDetailArea().getText();
                EventEntry newEvent = new EventEntry(title, venue, organizer);
                
                // Attempt to set the start and end dates
                newEvent.setStartDate(startDate);
                newEvent.setEndDate(endDate);
                newEvent.setStartTime(startTime);
                newEvent.setEndTime(endTime);
                newEvent.setDetails(details);
                accountPage.getCurrentCalendar().addEntry(newEvent);  
                addEvent.dispose();
            } catch (DateTimeParseException ex) {
                // Show a pop-up message if the date format is wrong
                JOptionPane.showMessageDialog(addEvent, "The date format is incorrect. Please use the format 'M/d/yyyy' for dates.", "Invalid Date Format", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }
}
