package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import Models.Entry.EntryModel;
import Models.Entry.Journal;
import Models.Calendar.Personal;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.ViewJournal;

import javax.swing.JOptionPane;

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

    private void loadAvailableJournals() {
        availableJournals.clear();

        // Only check Personal calendars for journals (as per specs)
        accountPage.getCurrentAccount().getCalendars().stream()
                .filter(calendar -> calendar instanceof Personal)
                .forEach(calendar -> {
                    for (EntryModel entry : calendar.getEntries()) {
                        if (entry instanceof Journal) {
                            availableJournals.add((Journal) entry);
                        }
                    }
                });

        // Update the month dropdown
        updateMonthDropdown();

        // Display the first available journal or show default message
        if (!availableJournals.isEmpty()) {
            displayJournal(availableJournals.get(0));
        } else {
            displayNoJournalsMessage();
        }
    }

    private void updateMonthDropdown() {
        viewJournal.getMonthComboBox().removeAllItems();

        if (availableJournals.isEmpty()) {
            viewJournal.getMonthComboBox().addItem("No journals available");
            return;
        }

        // Get unique months from available journals
        availableJournals.stream()
                .map(Journal::getMonth)
                .distinct()
                .forEach(month -> viewJournal.getMonthComboBox().addItem(month));
    }

    private void setupListeners() {
        // Month selection listener
        viewJournal.getMonthComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMonth = (String) viewJournal.getMonthComboBox().getSelectedItem();
                if (selectedMonth != null && !selectedMonth.equals("No journals available")) {
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

    private void displayJournalForMonth(String monthName) {
        Journal targetJournal = availableJournals.stream()
                .filter(journal -> journal.getMonth().equals(monthName))
                .findFirst()
                .orElse(null);

        if (targetJournal != null) {
            displayJournal(targetJournal);
        } else {
            displayNoJournalForMonth(monthName);
        }
    }

    private void displayJournal(Journal journal) {
        // Update the header label
        viewJournal.getJournalLabel().setText("Journal for " + journal.getMonth());

        // Update the content
        viewJournal.getJournalContent().setText(journal.getDetails());

        // Show creation date
        String dateInfo = "Created: " + journal.getDate().toString() + "\n\n" + journal.getDetails();
        viewJournal.getJournalContent().setText(dateInfo);
    }

    private void displayNoJournalForMonth(String monthName) {
        viewJournal.getJournalLabel().setText("No Journal for " + monthName);
        viewJournal.getJournalContent().setText("No journal entry found for " + monthName + ".");
    }

    private void displayNoJournalsMessage() {
        viewJournal.getJournalLabel().setText("No Journals Available");
        viewJournal.getJournalContent().setText("You haven't created any journal entries yet.\n\n" +
                "Journals can only be created in Personal calendars.\n" +
                "To create a journal entry, select a Personal calendar and choose 'Journal' from the entries dropdown.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This method can be used for additional actions if needed
        // Currently, specific actions are handled by the individual listeners set up above
    }

    // Helper method to refresh the journal view (can be called from outside)
    public void refreshJournals() {
        loadAvailableJournals();
    }
}