package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Models.Calendar.CalendarParentModel;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.SwitchCalendarFrame;

import javax.swing.JOptionPane;

public class SwitchCalendarListener implements ActionListener {
    private SwitchCalendarFrame switchCalendarFrame;    // The frame for switching calendars
    private AccountPage accountPage;                    // The main account page with calendar access

    public SwitchCalendarListener(SwitchCalendarFrame switchCalendarFrame, AccountPage accountPage) {
        this.switchCalendarFrame = switchCalendarFrame;
        this.accountPage = accountPage;

        // Load available calendars into the dropdown
        loadUserCalendars();

        // Setup button listeners
        setupButtonListeners();
    }

    /**
     * Loads the user's calendars into the switch calendar frame's dropdown.
     */
    private void loadUserCalendars() {
        // Get all calendars for the current user
        String[] calendarNames = accountPage.getCurrentAccount().getCalendars()
                .stream()
                .map(CalendarParentModel::getName)
                .toArray(String[]::new);

        // Update the dropdown
        switchCalendarFrame.setCalendarList(calendarNames);
    }

    /**
     * Sets up the action listeners for the buttons in the switch calendar frame.
     */
    private void setupButtonListeners() {
        // Switch button listener
        switchCalendarFrame.getSwitchButton().addActionListener(this);

        // Cancel button listener
        switchCalendarFrame.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchCalendarFrame.dispose();
            }
        });
    }

    /**
     * Handles the action of switching calendars when the switch button is clicked.
     *
     * @param e the action event triggered by the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the source of the event is the switch button
        if (e.getSource() == switchCalendarFrame.getSwitchButton()) {
            String selectedCalendarName = switchCalendarFrame.getSelectedCalendar();

            // Check if a calendar is selected
            if (selectedCalendarName == null || selectedCalendarName.isEmpty()) {
                JOptionPane.showMessageDialog(switchCalendarFrame,
                        "Please select a calendar to switch to",
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
            } else {
                // Find the selected calendar
                CalendarParentModel selectedCalendar = accountPage.getCalendarByName(selectedCalendarName);

                // If the calendar is not found, show error
                if (selectedCalendar == null) {
                    JOptionPane.showMessageDialog(switchCalendarFrame,
                            "Selected calendar not found",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Check if it's already the current calendar
                    if (selectedCalendar.equals(accountPage.getCurrentCalendar())) {
                        JOptionPane.showMessageDialog(switchCalendarFrame,
                                "This calendar is already selected",
                                "Already Active", JOptionPane.INFORMATION_MESSAGE);
                        switchCalendarFrame.dispose();
                    } else {
                        // Switch to the selected calendar
                        accountPage.switchCurrentCalendar(selectedCalendar);

                        JOptionPane.showMessageDialog(switchCalendarFrame,
                                "Successfully switched to: " + selectedCalendarName,
                                "Calendar Switched", JOptionPane.INFORMATION_MESSAGE);

                        switchCalendarFrame.dispose();
                    }
                }
            }
        }
    }
}