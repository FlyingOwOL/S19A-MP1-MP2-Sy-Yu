package Controllers.Listeners_AddEntryPopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.Entry.Journal;
import Models.Entry.EntryModel;
import Models.Calendar.Personal;
import Views.AccountPage;
import Views.AddEntryPopUps.AddJournal;

public class AddJournalListener implements ActionListener{

    private AddJournal addJournal;
    private AccountPage accountPage;

    public AddJournalListener (AddJournal addJournal, AccountPage accountPage){
        this.addJournal = addJournal;
        this.accountPage = accountPage;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addJournal.getsubmitButton()){
            String month = addJournal.getMonthtField().getText().trim();
            String details = addJournal.getDetailArea().getText().trim();

            // Validation
            if (month.isEmpty()) {
                JOptionPane.showMessageDialog(addJournal,
                        "Please enter a month",
                        "Month Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (details.isEmpty()) {
                JOptionPane.showMessageDialog(addJournal,
                        "Please enter journal details",
                        "Details Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Check if current calendar is Personal
            if (!(accountPage.getCurrentCalendar() instanceof Personal)) {
                JOptionPane.showMessageDialog(addJournal,
                        "Journals can only be created in Personal calendars.\nPlease switch to a Personal calendar first.",
                        "Invalid Calendar Type", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if there is already a journal for that month
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