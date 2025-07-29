package Controllers.Listeners_AddEntryPopUps;

import Models.Calendar.Personal;
import Models.Entry.Journal;
import Views.AccountPage;
import Views.AddEntryPopUps.AddJournal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener class that handles the creation of a new Journal entry.
 */
public class AddJournalListener implements ActionListener {

    private AddJournal addJournal;      // The pop-up window for adding journal entries
    private AccountPage accountPage;    // The main account page with calendar access

    /**
     * Constructor for the listener.
     *
     * @param addJournal   The popup window for adding journal entries.
     * @param accountPage  The main account page with calendar access.
     */
    public AddJournalListener(AddJournal addJournal, AccountPage accountPage) {
        this.addJournal = addJournal;
        this.accountPage = accountPage;
    }

    /**
     * Handles the action when the Submit button is pressed.
     * Validates input, checks for duplicates, and creates a journal if valid.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addJournal.getsubmitButton()) {
            String month = addJournal.getMonthField().getText().trim();
            String details = addJournal.getDetailArea().getText().trim();
            boolean isValid = true;

            // Validate that a month was entered
            if (month.isEmpty()) {
                JOptionPane.showMessageDialog(addJournal,
                        "Please enter a month",
                        "Month Required", JOptionPane.WARNING_MESSAGE);
                isValid = false;
            }

            // Validate that details were entered
            if (details.isEmpty()) {
                JOptionPane.showMessageDialog(addJournal,
                        "Please enter journal details",
                        "Details Required", JOptionPane.WARNING_MESSAGE);
                isValid = false;
            }

            // Ensure the current calendar is of type Personal
            if (!(accountPage.getCurrentCalendar() instanceof Personal)) {
                JOptionPane.showMessageDialog(addJournal,
                        "Journals can only be created in Personal calendars.\nPlease switch to a Personal calendar first.",
                        "Invalid Calendar Type", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            // Ensure only one journal per month exists
            if (isValid) {
                boolean journalExists = accountPage.getCurrentCalendar().getEntries().stream()
                        .filter(entry -> entry instanceof Journal)
                        .map(entry -> (Journal) entry)
                        .anyMatch(journal -> journal.getMonth().equalsIgnoreCase(month));

                if (journalExists) {
                    JOptionPane.showMessageDialog(addJournal,
                            "A journal for '" + month + "' already exists.\nOnly one journal per month is allowed.",
                            "Journal Already Exists", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }
            }

            // Create and add the new journal entry if all checks pass
            if (isValid) {
                String title = "This " + month + " Journal";
                Journal newJournal = new Journal(title, details, month);

                accountPage.getCurrentCalendar().addEntry(newJournal);

                JOptionPane.showMessageDialog(addJournal,
                        "Journal for '" + month + "' created successfully!",
                        "Journal Created", JOptionPane.INFORMATION_MESSAGE);

                addJournal.dispose(); // Close the popup
            }
        }
    }
}