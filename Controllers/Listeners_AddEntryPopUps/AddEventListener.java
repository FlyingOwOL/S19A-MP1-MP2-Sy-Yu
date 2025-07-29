package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.EventEntry;
import Views.AccountPage;
import Views.AddEntryPopUps.AddEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/**
 * Listener class responsible for handling the submission of a new event entry
 * from the AddEvent popup interface.
 * 
 * It captures user input, validates date and time formats, creates a new EventEntry,
 * and adds it to the currently selected calendar in the AccountPage.
 */
public class AddEventListener implements ActionListener {

    private AddEvent addEvent;           // The event creation popup window
    private AccountPage accountPage;     // The main account page holding the calendar

    /**
     * Constructs a listener for the AddEvent popup.
     *
     * @param addEvent     The pop-up UI for entering event details.
     * @param accountPage  The main account page with calendar access.
     */
    public AddEventListener(AddEvent addEvent, AccountPage accountPage) {
        this.addEvent = addEvent;
        this.accountPage = accountPage;
    }

    /**
     * Triggered when the submit button is clicked in the AddEvent popup.
     * Gathers data from UI components, attempts to create an EventEntry,
     * and handles validation (e.g., date format).
     *
     * If creation is successful, the event is added to the calendar and the popup closes.
     * If there is an error in parsing the dates, a warning dialog is shown.
     *
     * @param e the action event that triggered this method
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEvent.getSubmitButton()) {
            try {
                // Retrieve values from input fields
                String title = addEvent.getTitleField().getText();
                String organizer = addEvent.getOrganizerField().getText();
                String venue = addEvent.getVenueField().getText();
                String startDate = addEvent.getStartDateField().getText();
                String endDate = addEvent.getEndDateField().getText();
                String startTime = (String) addEvent.getStartTime().getSelectedItem();
                String endTime = (String) addEvent.getEndTime().getSelectedItem();
                String details = addEvent.getDetailArea().getText();

                // Create new event and populate its fields
                EventEntry newEvent = new EventEntry(title, venue, organizer);
                newEvent.setStartDate(startDate);
                newEvent.setEndDate(endDate);
                newEvent.setStartTime(startTime);
                newEvent.setEndTime(endTime);
                newEvent.setDetails(details);

                // Add event to the current calendar
                accountPage.getCurrentCalendar().addEntry(newEvent);

                // Close the popup
                addEvent.dispose();
            } catch (DateTimeParseException ex) {
                // Show error dialog if user entered an invalid date
                JOptionPane.showMessageDialog(addEvent,
                        "The date format is incorrect. Please use the format 'M/d/yyyy' for dates.",
                        "Invalid Date Format",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}