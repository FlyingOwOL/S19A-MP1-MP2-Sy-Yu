package Controllers.Listeners_Controllers;

import Views.AccountPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 * Listener class responsible for handling changes in the calendar display view
 * (e.g., switching between Month and Week views) on the AccountPage.
 * 
 * It updates both the calendar layout and the display label according to the
 * currently selected display mode.
 */
public class ChangeDisplayListener implements ActionListener {
    private AccountPage accountPage;    // The main account page where the calendar is displayed
    private LocalDate currentDate;      // Stores the current system date when listener is instantiated

    /**
     * Constructs a ChangeDisplayListener that binds to the given AccountPage.
     *
     * @param accountPage the AccountPage where calendar view changes will be reflected
     */
    public ChangeDisplayListener(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.currentDate = LocalDate.now(); // Capture current date upon creation
    }

    /**
     * Called upon when the user triggers a change in calendar display (e.g., switching views).
     * Updates the AccountPage calendar view and label accordingly.
     *
     * @param e the action event triggered by the user's interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String selectedDisplay = accountPage.getSelectedCalendarDisplay();  // "Month" or "Week"
            String newDateLabel = getFormattedDateLabel();                       // Get appropriate label
            accountPage.changeCalendarDisplay(selectedDisplay);                 // Change display view
            accountPage.updateDateLabel(newDateLabel);                          // Update label text
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(accountPage,
                "An error occurred in changing the calendar display: " + ex.getMessage(),
                "Display Change Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Generates a human-readable label based on the current display mode and current date.
     * 
     * If the calendar is in "Week" mode, it shows a label for the start and end week range.
     * If in "Month" mode, it shows the full month and year.
     *
     * @return a formatted string to be shown as the calendar label
     */
    public String getFormattedDateLabel() {
        String displayText;
        String selectedDisplay = accountPage.getSelectedCalendarDisplay();

        // If week view is selected, compute the start and end of the week
        if ("Week".equals(selectedDisplay)) {
            LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate endOfWeek = startOfWeek.plusDays(6);

            if (startOfWeek.getMonth() == endOfWeek.getMonth()) {
                // Same month range (e.g., March 2025)
                displayText = String.format("%s %d",
                        startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        startOfWeek.getYear());
            } else {
                // Spanning months (e.g., March - April 2025)
                displayText = String.format("%s - %s %d",
                        startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        endOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        endOfWeek.getYear());
            }
        } else {
            // Default to month view (e.g., March 2025)
            displayText = String.format("%s %d",
                    currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    currentDate.getYear());
        }

        return displayText;
    }
}
