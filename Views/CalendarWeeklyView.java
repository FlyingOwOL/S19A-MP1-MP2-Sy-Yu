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
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class CalendarWeeklyView extends JScrollPane {
    private String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private String[] timeSlots = {"1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", 
                                  "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", 
                                  "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", 
                                  "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
                                  "9:00 PM", "10:00 PM", "11:00 PM", "12:00 AM"};
    private LocalDate currentDate;
    private ArrayList<JLabel> dayLabel = new ArrayList<>();
    private JPanel calendarPanel = new JPanel();    
    private ArrayList<JPanel> dayPanel = new ArrayList<>();
    public CalendarWeeklyView() {
        // Get the current date
        currentDate = LocalDate.now();
        calendarPanel.setLayout(new GridLayout(timeSlots.length + 2, dayNames.length + 1)); // +2 for header row and time column
        // Add day names and day numbers to the header row
        calendarPanel.add(new JLabel("Time", SwingConstants.CENTER)); // Top-left corner
        for (int i = 0; i < dayNames.length; i++) {
            // Create a panel for each day to hold the day name and day number
            dayPanel.add(new JPanel());
            dayPanel.get(i).setLayout(new BorderLayout());
            
            dayPanel.get(i).add(new JLabel(dayNames[i], SwingConstants.CENTER), BorderLayout.NORTH);
            // Calculate the date for the current week
            LocalDate dayDate = currentDate.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)).plusDays(i);
            dayLabel.add(new JLabel(String.valueOf(dayDate.getDayOfMonth()), SwingConstants.CENTER));
            dayPanel.get(i).add(dayLabel.get(i), BorderLayout.SOUTH);
            
            calendarPanel.add(dayPanel.get(i));
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
