package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A pop-up window for deleting a calendar.
 * This class provides the GUI layout for selecting and deleting an existing calendar.
 */
public class DeleteCalendarFrame extends PopUpFormat {
    private JPanel headerPanel = new JPanel();                                  // Top section with title
    private JPanel contentPanel = new JPanel();                                 // Section with combo box and button

    private JLabel titleLabel = new JLabel("Delete Calendar");             // Title label

    private String[] calendars = {"Calendar 1", "Calendar 2", "Calendar 3"};    // Sample calendar list
    private JComboBox<String> calendarComboBox = new JComboBox<>(calendars);    // Dropdown to select calendar

    private JButton deleteButton = new JButton("Delete");                  // Button to confirm deletion

    /**
     * Constructs the Delete Calendar frame layout and components.
     */
    public DeleteCalendarFrame(){
        this.setTitle("Delete Calendar");

        // Header panel setup
        headerPanel.setBounds(0, 0, 400, 100);
        headerPanel.setBackground(Color.CYAN);
        headerPanel.setLayout(null);
        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setBounds(122, 20, 200, 50);
        headerPanel.add(titleLabel);

        // Content panel setup
        contentPanel.setBounds(0, 100, 400, 150);
        contentPanel.setLayout(null);
        calendarComboBox.setBounds(50, 20, 300, 30);
        deleteButton.setBounds(150, 70, 100, 30);
        deleteButton.setFocusable(false);
        contentPanel.add(calendarComboBox);
        contentPanel.add(deleteButton);

        // Add panels to frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }

    /**
     * Returns the delete button used to confirm the deletion of a selected calendar.
     *
     * This is typically used by controller classes to attach an ActionListener that handles
     * the deletion logic when the button is clicked.
     *
     * @return the JButton component for deleting a calendar
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * Returns the combo box containing the list of available calendars to delete.
     *
     * This can be used to retrieve the currently selected calendar or dynamically
     * update the list of calendar options before showing the frame.
     *
     * @return the JComboBox<String> component for selecting a calendar
     */
    public JComboBox<String> getCalendarComboBox() {
        return calendarComboBox;
    }
}