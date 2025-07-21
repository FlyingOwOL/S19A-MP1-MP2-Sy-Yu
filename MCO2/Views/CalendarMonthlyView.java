package Views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;

public class CalendarMonthlyView extends JPanel {

    private String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private LocalDate currentDate;

    public CalendarMonthlyView() {
        this.currentDate = LocalDate.now(); // Get the current date
        this.setPreferredSize(new Dimension(900, 400));
        this.setLayout(new GridLayout(7, 7)); // 6 rows for the month and 7 columns for the days of the week

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
            this.add(new JButton(String.valueOf(day))); // Button with the day number
        }

        // Fill the remaining buttons in the month grid with empty buttons
        int totalButtons = 42; // 6 rows * 7 columns = 42 buttons
        for (int i = daysInMonth + firstDayOfWeek; i < totalButtons; i++) {
            this.add(new JButton()); // Empty button for days after the last day of the month
        }

        this.setVisible(true);
    }
}
