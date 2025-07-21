package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;

public class SwitchCalendarFrame extends PopUpFormat {
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel titleLabel = new JLabel("Switch Calendar");
    private JLabel selectedCalendarLabel = new JLabel("Select Calendar:");

    private String[] calendars = {"Calendar 1", "Calendar 2", "Calendar 3"};
    private JComboBox<String> calendarComboBox = new JComboBox<>(calendars);

    private JButton switchButton = new JButton("Switch");
    private JButton cancelButton = new JButton("Cancel");

    public SwitchCalendarFrame() {
        this.setTitle("Switch Calendar");

        // Setup header panel
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(100, 149, 237)); // Cornflower blue
        headerPanel.setBounds(0, 0, 400, 60);

        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(20, 15, 200, 30);
        headerPanel.add(titleLabel);
        this.add(headerPanel);

        // Setup content panel
        contentPanel.setLayout(null);
        contentPanel.setBounds(0, 60, 400, 340);

        selectedCalendarLabel.setBounds(40, 30, 150, 25);
        contentPanel.add(selectedCalendarLabel);

        calendarComboBox.setBounds(170, 30, 170, 25);
        contentPanel.add(calendarComboBox);

        switchButton.setBounds(90, 100, 100, 30);
        contentPanel.add(switchButton);

        cancelButton.setBounds(210, 100, 100, 30);
        contentPanel.add(cancelButton);

        this.add(contentPanel);

        // Make the popup visible
        this.setVisible(true);
    }

    // Getter methods for controller access
    public JButton getSwitchButton() {
        return switchButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public String getSelectedCalendar() {
        return (String) calendarComboBox.getSelectedItem();
    }

    public void setCalendarList(String[] calendars) {
        calendarComboBox.removeAllItems();
        for (String cal : calendars) {
            calendarComboBox.addItem(cal);
        }
    }
}
