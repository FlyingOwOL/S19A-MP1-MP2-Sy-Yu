package Controllers.Listeners_AddEntryPopUps;

import Models.Calendar.Personal;
import Models.Entry.Journal;
import Views.AccountPage;
import Views.AddEntryPopUps.AddJournal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener for adding a Journal entry to a Personal calendar.
 * 
 * Validates input (month and journal details), ensures only one journal per month
 * is added, and checks that the current calendar is of type Personal before adding the entry.
 */
public class AddJournalListener implements ActionListener {

    private AddJournal addJournal;
    private AccountPage accountPage;

    /**
     * Constructs a listener for handling the addition of journal entries.
     *
     * @param addJournal  the AddJournal pop-up window
     * @param accountPage the main AccountPage containing the current calendar
     */
    public AddJournalListener(AddJournal addJournal, AccountPage accountPage) {
        this.addJournal = addJournal;
        this.accountPage = accountPage;
    }

    /**
     * Handles the logic for adding a journal when the submit button is clicked.
     *
     * @param e the ActionEvent triggered by the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addJournal.getsubmitButton()) {
            String month = (String) addJournal.getMonthtField().getSelectedItem();
            String details = addJournal.getDetailArea().getText().trim();

            // Validation
            if (month == null || month.isEmpty()) {
                JOptionPane.showMessageDialog(addJournal,
                        "Please enter a month.",
                        "Month Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // If details are empty, show a warning
            if (details.isEmpty()) {
                JOptionPane.showMessageDialog(addJournal,
                        "Please enter journal details.",
                        "Details Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Calendar type check
            if (!(accountPage.getCurrentCalendar() instanceof Personal)) {
                JOptionPane.showMessageDialog(addJournal,
                        "Journals can only be created in Personal calendars.\nPlease switch to a Personal calendar first.",
                        "Invalid Calendar Type", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for existing journal for that month
            boolean journalExists = accountPage.getCurrentCalendar().getEntries().stream()
                    .filter(entry -> entry instanceof Journal)
                    .map(entry -> (Journal) entry)
                    .anyMatch(journal -> journal.getMonth().equalsIgnoreCase(month));

            if (journalExists) {
                JOptionPane.showMessageDialog(addJournal,
                        "A journal for '" + month + "' already exists.\nOnly one journal per month is allowed.",
                        "Journal Already Exists", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add new journal
            String title = "This " + month + " Journal";
            Journal newJournal = new Journal(title, details, month);
            accountPage.getCurrentCalendar().addEntry(newJournal);

            JOptionPane.showMessageDialog(addJournal,
                    "Journal for '" + month + "' created successfully!",
                    "Journal Created", JOptionPane.INFORMATION_MESSAGE);

            addJournal.dispose();
        }
    }
}
