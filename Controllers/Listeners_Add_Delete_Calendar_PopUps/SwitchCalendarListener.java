package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Models.Calendar.CalendarParentModel;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.SwitchCalendarFrame;

import javax.swing.JOptionPane;

public class SwitchCalendarListener implements ActionListener {
    private SwitchCalendarFrame switchCalendarFrame;
    private AccountPage accountPage;

    public SwitchCalendarListener(SwitchCalendarFrame switchCalendarFrame, AccountPage accountPage) {
        this.switchCalendarFrame = switchCalendarFrame;
        this.accountPage = accountPage;

        // Load available calendars into the dropdown
        loadUserCalendars();

        // Setup button listeners
        setupButtonListeners();
    }

    private void loadUserCalendars() {
        // Get all calendars for the current user
        String[] calendarNames = accountPage.getCurrentAccount().getCalendars()
                .stream()
                .map(CalendarParentModel::getName)
                .toArray(String[]::new);

        // Update the dropdown
        switchCalendarFrame.setCalendarList(calendarNames);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == switchCalendarFrame.getSwitchButton()) {
            String selectedCalendarName = switchCalendarFrame.getSelectedCalendar();

            if (selectedCalendarName == null || selectedCalendarName.isEmpty()) {
                JOptionPane.showMessageDialog(switchCalendarFrame,
                        "Please select a calendar to switch to",
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Find the selected calendar
            CalendarParentModel selectedCalendar = accountPage.getCalendarByName(selectedCalendarName);

            if (selectedCalendar == null) {
                JOptionPane.showMessageDialog(switchCalendarFrame,
                        "Selected calendar not found",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if it's already the current calendar
            if (selectedCalendar.equals(accountPage.getCurrentCalendar())) {
                JOptionPane.showMessageDialog(switchCalendarFrame,
                        "This calendar is already selected",
                        "Already Active", JOptionPane.INFORMATION_MESSAGE);
                switchCalendarFrame.dispose();
                return;
            }

            // Switch to the selected calendar
            accountPage.switchCurrentCalendar(selectedCalendar);

            JOptionPane.showMessageDialog(switchCalendarFrame,
                    "Successfully switched to: " + selectedCalendarName,
                    "Calendar Switched", JOptionPane.INFORMATION_MESSAGE);

            switchCalendarFrame.dispose();
        }
    }
}