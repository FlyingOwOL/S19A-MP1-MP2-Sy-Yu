package Controllers.Listeners_Controllers;

import Views.AccountPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class ChangeDisplayListener implements ActionListener {
    private AccountPage accountPage;
    private LocalDate currentDate; // Store the current date

    public ChangeDisplayListener(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.currentDate = LocalDate.now();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String selectedDisplay = accountPage.getSelectedCalendarDisplay();
            String newDateLabel = getFormattedDateLabel();
            accountPage.changeCalendarDisplay(selectedDisplay);
            accountPage.updateDateLabel(newDateLabel);
        } catch (NumberFormatException ex) {
            System.out.println("Error in changing calendar display:");
        }
    }

    // Method to return the formatted date label based on the current date and selected display type
    public String getFormattedDateLabel() {
        String displayText;
        String selectedDisplay = accountPage.getSelectedCalendarDisplay();

        if ("Week".equals(selectedDisplay)) {
            LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate endOfWeek = startOfWeek.plusDays(6);

            if (startOfWeek.getMonth() == endOfWeek.getMonth()) {
                displayText = String.format("%s %d", 
                        startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), 
                        startOfWeek.getYear());
            } else {
                displayText = String.format("%s - %s %d", 
                        startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), 
                        endOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), 
                        endOfWeek.getYear());
            }
        } else { // Default to month view
            displayText = String.format("%s %d", 
                    currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), 
                    currentDate.getYear());
        }

        return displayText;
    }
}
