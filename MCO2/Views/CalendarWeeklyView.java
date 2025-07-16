package Views;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;


public class CalendarWeeklyView extends JScrollPane {
    private String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private String[] timeSlots = {"1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", 
                                  "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", 
                                  "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", 
                                  "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
                                  "9:00 PM", "10:00 PM", "11:00 PM", "12:00 AM"};

    public CalendarWeeklyView() {
        // Create a panel to hold the calendar components
        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(timeSlots.length + 1, dayNames.length + 1)); // +1 for header row and time column

        // Add day names to the header row
        calendarPanel.add(new JLabel("Time", SwingConstants.CENTER)); // Top-left corner
        for (String day : dayNames) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        // Add time slots and interactive rectangles
        for (int i = 0; i < timeSlots.length; i++) {
            // Add time slot label
            calendarPanel.add(new JLabel(timeSlots[i], SwingConstants.CENTER));

            // Add interactive rectangles for each time slot and day
            for (int j = 0; j < dayNames.length; j++) {

                JPanel timeSlotPanel = new JPanel();
                timeSlotPanel.setBackground(Color.LIGHT_GRAY);
                timeSlotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                timeSlotPanel.setPreferredSize(new Dimension(100, 50)); // Set preferred size for the rectangles


                timeSlotPanel.setLayout(new BorderLayout());
                timeSlotPanel.add(new JLabel("", SwingConstants.CENTER), BorderLayout.CENTER); // Placeholder for event text
                calendarPanel.add(timeSlotPanel);
            }
        }

        // Add the calendar panel to the scroll pane
        this.setViewportView(calendarPanel);
        this.setPreferredSize(new Dimension(900, 400)); // Set preferred size for the scroll pane
    }
}
