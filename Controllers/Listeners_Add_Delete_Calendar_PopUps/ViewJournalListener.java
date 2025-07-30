package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Models.Entry.EntryModel;
import Models.Entry.Journal;
import Models.Calendar.Personal;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.ViewJournal;


/**
 * Listener class that handles viewing journal entries in a Personal calendar.
 */
public class ViewJournalListener implements ActionListener {
    private ViewJournal viewJournal;
    private AccountPage accountPage;
    private ArrayList<Journal> availableJournals;

    public ViewJournalListener(ViewJournal viewJournal, AccountPage accountPage) {
        this.viewJournal = viewJournal;
        this.accountPage = accountPage;
        this.availableJournals = new ArrayList<>();

        // Load available journals and setup listeners
        loadAvailableJournals();
        setupListeners();
    }

    /**
     * Loads journals from the current account's Personal calendars.
     */
    private void loadAvailableJournals() {
        availableJournals.clear();

        // Only check Personal calendars for journals (as per specs)
        for (var calendar : accountPage.getCurrentAccount().getCalendars()) {
            if (calendar instanceof Personal) {
                for (EntryModel entry : calendar.getEntries()) {
                    if (entry instanceof Journal) {
                        availableJournals.add((Journal) entry);
                    }
                }
            }
        }

        // Update the month dropdown
        updateMonthDropdown();

        // Display the first available journal or show default message
        if (availableJournals.isEmpty()) {
            displayNoJournalsMessage();
        } else {
            displayJournal(availableJournals.get(0));
        }
    }

    /**
     * Updates the month dropdown with unique months from available journals.
     */
    private void updateMonthDropdown() {
        viewJournal.getMonthComboBox().removeAllItems();

        if (availableJournals.isEmpty()) {
            viewJournal.getMonthComboBox().addItem("No journals available");
        }

        ArrayList<String> addedMonths = new ArrayList<>();
        for (Journal journal : availableJournals) {
            String month = journal.getMonth();
            boolean found = false;
            for (String m : addedMonths) {
                if (m.equals(month)) {
                    found = true;
                }
            }
            if (!found) {
                viewJournal.getMonthComboBox().addItem(month);
                addedMonths.add(month);
            }
        }
    }

    /**
     * Sets up listeners for the month selection and close button.
     */
    private void setupListeners() {
        // Month selection listener
        viewJournal.getMonthComboBox().addActionListener(new ActionListener() {
            
            /**
             * Handles the action of selecting a month from the dropdown.
             *
             * @param e the action event triggered by the selection
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMonth = (String) viewJournal.getMonthComboBox().getSelectedItem();
                boolean isValid = true;
                if (selectedMonth == null) {
                    isValid = false;
                }
                if (selectedMonth != null && selectedMonth.equals("No journals available")) {
                    isValid = false;
                }

                if (isValid) {
                    displayJournalForMonth(selectedMonth);
                }
            }
        });

        // Close button listener
        viewJournal.getCloseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewJournal.dispose();
            }
        });
    }

    /**
     * Displays the journal entry for the selected month.
     *
     * @param monthName the name of the month to display
     */
    private void displayJournalForMonth(String monthName) {
        Journal targetJournal = null;

        // Loop through available journals to find the one for the selected month
        for (Journal journal : availableJournals) {
            if (journal.getMonth().equals(monthName)) {
                if (targetJournal == null) {
                    targetJournal = journal;
                }
            }
        }

        // If a journal for the month is found, display it; otherwise, show no journal message
        if (targetJournal != null) {
            displayJournal(targetJournal);
        } else {
            displayNoJournalForMonth(monthName);
        }
    }

    /**
     * Displays the details of a specific journal entry.
     *
     * @param journal the Journal entry to display
     */
    private void displayJournal(Journal journal) {
        // Update the header label
        viewJournal.getJournalLabel().setText("Journal for " + journal.getMonth());

        // Update the content
        viewJournal.getJournalContent().setText(journal.getDetails());

        // Show creation date
        String dateInfo = "Created: " + journal.getDate().toString() + "\n\n" + journal.getDetails();
        viewJournal.getJournalContent().setText(dateInfo);
    }

    /**
     * Displays a message when no journal entry is found for the specified month.
     *
     * @param monthName the name of the month with no journal entry
     */
    private void displayNoJournalForMonth(String monthName) {
        viewJournal.getJournalLabel().setText("No Journal for " + monthName);
        viewJournal.getJournalContent().setText("No journal entry found for " + monthName + ".");
    }

    /**
     * Displays a message when no journals are available.
     */
    private void displayNoJournalsMessage() {
        viewJournal.getJournalLabel().setText("No Journals Available");
        viewJournal.getJournalContent().setText("You haven't created any journal entries yet.\n\n" +
                "Journals can only be created in Personal calendars.\n" +
                "To create a journal entry, select a Personal calendar and choose 'Journal' from the entries dropdown.");
    }

    /**
     * Handles the action events for the View Journal listener.
     *
     * @param e the action event triggered by the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // This method can be used for additional actions if needed
        // Currently, specific actions are handled by the individual listeners set up above
    }

    /**
     * Refreshes the list of available journals and updates the view.
     * This can be called when a new journal is added or an existing one is modified.
     */
    public void refreshJournals() {
        loadAvailableJournals();
    }
}