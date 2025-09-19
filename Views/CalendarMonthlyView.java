package Views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CalendarMonthlyView is a JPanel that displays a monthly calendar view.
 * It shows the days of the month in a grid format, allowing users to see all days at a glance.
 */
public class CalendarMonthlyView extends JPanel {
    // Array of day names for the header of the calendar
    private String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private LocalDate currentDate;  // The current date

    /**
     * Default constructor that initializes the calendar to the current date.
     * Sets the preferred size and layout for the calendar panel.
     */
    public CalendarMonthlyView() {
        this.currentDate = LocalDate.now(); // Get the current date
        initializeCalendar();
    }

    /**
     * Constructor that allows setting a specific date for the calendar view.
     *
     * @param date the LocalDate to display in the calendar
     */
    public CalendarMonthlyView(LocalDate date) {
        this.currentDate = date;
        initializeCalendar();
    }

    /**
     * Initializes the calendar layout and components.
     * Sets the preferred size and layout for the calendar panel.
     */
    private void initializeCalendar() {
        this.setPreferredSize(new Dimension(900, 400));
        this.setLayout(new GridLayout(7, 7)); // 6 rows for the month and 7 columns for the days of the week
        buildCalendar();
        this.setVisible(true);
    }

    /**
     * Updates the calendar view with a new date.
     * Clears existing components and rebuilds the calendar for the new date.
     *
     * @param newDate the LocalDate to update the calendar to
     */
    public void updateDate(LocalDate newDate) {
        this.currentDate = newDate;
        this.removeAll(); // Clear existing components
        buildCalendar();
        this.revalidate();
        this.repaint();
    }

    /**
     * Builds the calendar grid for the current month.
     * Adds buttons for each day of the month, including empty buttons for days before the first day of the month.
     */
    private void buildCalendar() {
        // Add day names to the top of the calendar
        for (String dayName : dayNames) {
            this.add(new JLabel(dayName));
        }

        // Get the first day of the month and the number of days in the month
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1 = Monday, ..., 7 = Sunday

        // Convert to zero-based index for the grid (0 = Sunday, ..., 6 = Saturday)
        firstDayOfWeek = firstDayOfWeek % 7; // Convert to 0 (Sunday) to 6 (Saturday)

        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        // Fill the calendar with empty buttons until the first day of the month
        for (int i = 0; i < firstDayOfWeek; i++) {
            this.add(new JButton()); // Empty button for days before the first day of the month
        }

        // Fill the calendar with buttons for each day of the month
        for (int day = 1; day <= daysInMonth; day++) {
            JButton dayButton = new JButton(String.valueOf(day));

            // Highlight current day if viewing current month
            if (currentDate.getYear() == LocalDate.now().getYear() &&
                    currentDate.getMonth() == LocalDate.now().getMonth() &&
                    day == LocalDate.now().getDayOfMonth()) {
                dayButton.setBackground(java.awt.Color.LIGHT_GRAY);
            }

            this.add(dayButton); // Button with the day number
        }

        // Fill the remaining buttons in the month grid with empty buttons
        int totalButtons = 42; // 6 rows * 7 columns = 42 buttons
        for (int i = daysInMonth + firstDayOfWeek; i < totalButtons; i++) {
            this.add(new JButton()); // Empty button for days after the last day of the month
        }
    }

    /**
     * Returns the current date displayed in the calendar.
     *
     * @return the LocalDate representing the current date
     */
    public LocalDate getCurrentDate() {
        return currentDate;
    }
}