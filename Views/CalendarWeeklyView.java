package Views;

import Utilities.FixedValues;

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

    private LocalDate currentDate;
    private ArrayList<JLabel> dayLabel = new ArrayList<>();
    private JPanel calendarPanel = new JPanel();
    private ArrayList<JPanel> dayPanel = new ArrayList<>();

    //TODO entries should also be shown in the weekly view

    public CalendarWeeklyView() {
        // Get the current date
        currentDate = LocalDate.now();
        initializeCalendar();
    }

    // Constructor that accepts a specific date
    public CalendarWeeklyView(LocalDate date) {
        this.currentDate = date;
        initializeCalendar();
    }

    private void initializeCalendar() {
        buildCalendar();
        this.setPreferredSize(new Dimension(900, 400)); // Set preferred size for the scroll pane
    }

    public void updateDate(LocalDate newDate) {
        this.currentDate = newDate;
        // Clear existing components
        calendarPanel.removeAll();
        dayLabel.clear();
        dayPanel.clear();
        buildCalendar();
        this.revalidate();
        this.repaint();
    }

    private void buildCalendar() {
        calendarPanel.setLayout(new GridLayout(FixedValues.timeSlots.length + 2, dayNames.length + 1)); // +2 for header row and time column

        // Add day names and day numbers to the header row
        calendarPanel.add(new JLabel("Time", SwingConstants.CENTER)); // Top-left corner
        for (int i = 0; i < dayNames.length; i++) {
            // Create a panel for each day to hold the day name and day number
            JPanel dayPanelItem = new JPanel();
            dayPanel.add(dayPanelItem);
            dayPanelItem.setLayout(new BorderLayout());

            dayPanelItem.add(new JLabel(dayNames[i], SwingConstants.CENTER), BorderLayout.NORTH);
            
            // Calculate the date for the current week
            LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));
            LocalDate dayDate = startOfWeek.plusDays(i);
            
            JLabel dayLabelItem = new JLabel(String.valueOf(dayDate.getDayOfMonth()), SwingConstants.CENTER);
            dayLabel.add(dayLabelItem);

            // Highlight current day
            if (dayDate.equals(LocalDate.now())) {
                dayPanelItem.setBackground(Color.LIGHT_GRAY);
            }

            dayPanelItem.add(dayLabelItem, BorderLayout.SOUTH);

            calendarPanel.add(dayPanelItem);
        }

        // Add time slots and interactive rectangles
        for (int i = 0; i < FixedValues.timeSlots.length; i++) {
            // Add time slot label
            calendarPanel.add(new JLabel(FixedValues.timeSlots[i], SwingConstants.CENTER));

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
    }


    public LocalDate getCurrentDate() {
        return currentDate;
    }
}