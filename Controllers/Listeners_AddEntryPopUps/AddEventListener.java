package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.EventEntry;
import Views.AccountPage;
import Views.AddEntryPopUps.AddEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/**
 * The AddEventListener class handles the logic for creating an EventEntry
 * when the submit button is clicked in the AddEvent pop-up.
 *
 * This listener retrieves the input from the user interface, performs validation on required fields,
 * creates a new EventEntry if all inputs are valid, and adds it to the currently selected calendar
 * in the AccountPage.
 *
 */
public class AddEventListener implements ActionListener {

    // Reference to the AddEvent pop-up window
    private AddEvent addEvent;

    // Reference to the AccountPage from which the pop-up was opened
    private AccountPage accountPage;

    /**
     * Constructs a listener for handling event submission.
     *
     * @param addEvent the pop-up view where the user inputs event details
     * @param accountPage the main account page where the event will be added
     */
    public AddEventListener(AddEvent addEvent, AccountPage accountPage) {
        this.addEvent = addEvent;
        this.accountPage = accountPage;

        this.addEvent.getOrganizerField().setText(accountPage.getOwner().getName());
        this.addEvent.updateGUI();
    }

    /**
     * Handles the submission of a new event entry when the user clicks the submit button.
     * 
     * - Validates that the title, start date, and end date are not empty.
     * - Shows warning dialogs for missing required fields.
     * - Attempts to create an EventEntry and add it to the current calendar.
     * - Handles DateTimeParseException if the date format is incorrect.
     * - Shows appropriate success or error messages to the user.
     *
     * @param e the ActionEvent triggered when a button is pressed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEvent.getSubmitButton()) {
            try {
                String title = addEvent.getTitleField().getText();
                String organizer = addEvent.getOrganizerField().getText();
                String venue = addEvent.getVenueField().getText();
                String startDate = addEvent.getStartDateField().getText();
                String endDate = addEvent.getEndDateField().getText();
                String startTime = (String) addEvent.getStartTime().getSelectedItem();
                String endTime = (String) addEvent.getEndTime().getSelectedItem();
                String details = addEvent.getDetailArea().getText();

                boolean stop = false;

                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(addEvent, "Please enter a title", "Title Required", JOptionPane.WARNING_MESSAGE);
                    stop = true;
                }

                if (startDate.isEmpty() && !stop) {
                    JOptionPane.showMessageDialog(addEvent, "Please enter a start date", "Start Date Required", JOptionPane.WARNING_MESSAGE);
                    stop = true;
                }

                if (endDate.isEmpty() && !stop) {
                    JOptionPane.showMessageDialog(addEvent, "Please enter an end date", "End Date Required", JOptionPane.WARNING_MESSAGE);
                    stop = true;
                }

                if (!stop) {
                    EventEntry newEvent = new EventEntry(title, venue, organizer);
                    newEvent.setStartDate(startDate);
                    newEvent.setEndDate(endDate);
                    newEvent.setStartTime(startTime);
                    newEvent.setEndTime(endTime);
                    newEvent.setDetails(details);
                    accountPage.getCurrentCalendar().addEntry(newEvent);

                    JOptionPane.showMessageDialog(addEvent,
                        "Event '" + title + "' created successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    addEvent.dispose();
                }

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(addEvent,
                    "The date format is incorrect. Please use the format 'YYYY-MM-DD' for dates.",
                    "Invalid Date Format",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(accountPage,
                    "ERROOOOOOOOOOOOOR",
                    "ERROOOOOOOOOOOOOR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
