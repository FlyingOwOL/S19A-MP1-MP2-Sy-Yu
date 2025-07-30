package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the pop-up window for switching between calendars.
 * It allows the user to select a calendar from a dropdown list and either confirm or cancel the switch.
 */
public class SwitchCalendarFrame extends PopUpFormat {
    // Panels for organizing header and main content
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Labels for UI instructions
    private JLabel titleLabel = new JLabel("Switch Calendar");
    private JLabel selectedCalendarLabel = new JLabel("Select Calendar:");

    // ComboBox for calendar selection
    private String[] calendars = {"Calendar 1", "Calendar 2", "Calendar 3"};
    private JComboBox<String> calendarComboBox = new JComboBox<>(calendars);

    // Action buttons
    private JButton switchButton = new JButton("Switch");
    private JButton cancelButton = new JButton("Cancel");

    /**
     * Constructs the Switch Calendar pop-up UI.
     * Sets up the panels, layout, labels, combo box, and buttons.
     */
    public SwitchCalendarFrame() {
        this.setTitle("Switch Calendar");

        // Header section
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(100, 149, 237)); // Cornflower blue
        headerPanel.setBounds(0, 0, 400, 60);
        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(20, 15, 200, 30);
        headerPanel.add(titleLabel);
        this.add(headerPanel);

        // Main content section
        contentPanel.setLayout(null);
        contentPanel.setBounds(0, 60, 400, 340);

        selectedCalendarLabel.setBounds(40, 30, 150, 25);
        calendarComboBox.setBounds(170, 30, 170, 25);

        switchButton.setBounds(90, 100, 100, 30);
        cancelButton.setBounds(210, 100, 100, 30);

        contentPanel.add(selectedCalendarLabel);
        contentPanel.add(calendarComboBox);
        contentPanel.add(switchButton);
        contentPanel.add(cancelButton);
        this.add(contentPanel);

        this.setVisible(true);
    }

    /**
     * Returns the button used to confirm switching to the selected calendar.
     * 
     * Used by controllers to attach listeners that will perform the switch logic.
     *
     * @return JButton for confirming calendar switch
     */
    public JButton getSwitchButton() {
        return switchButton;
    }

    /**
     * Returns the button used to cancel the switch operation and close the frame.
     * 
     * Typically used to attach a listener that will close or hide the window.
     *
     * @return JButton for cancelling the operation
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Returns the name of the currently selected calendar from the combo box.
     * 
     * This is used when performing the switch logic to know which calendar
     * the user chose to switch to.
     *
     * @return the selected calendar's name as a String
     */
    public String getSelectedCalendar() {
        return (String) calendarComboBox.getSelectedItem();
    }

    /**
     * Updates the calendar combo box with a new list of calendar names.
     * 
     * This is useful when the list of calendars is dynamic and depends
     * on the currently logged-in user or changes in data.
     *
     * @param calendars an array of calendar names to display in the combo box
     */
    public void setCalendarList(String[] calendars) {
        calendarComboBox.removeAllItems();
        for (String cal : calendars) {
            calendarComboBox.addItem(cal);
        }
    }
}