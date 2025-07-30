package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * This class represents the pop-up window for adding a new calendar.
 * It allows the user to create a new calendar or import an existing one.
 */
public class AddCalendarFrame extends PopUpFormat {
    // Header and content panels
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Title
    private JLabel titleLabel = new JLabel("Add Calendar");

    // Input fields for calendar name and password
    private JTextField calendarNameField = new JTextField();
    private JTextField calendarPasswordField = new JTextField();

    // Button to add the calendar
    private JButton addButton = new JButton("Add");

    // Radio buttons for selecting calendar creation type
    private JRadioButton creationType = new JRadioButton("Create New Calendar");
    private JRadioButton importType = new JRadioButton("Import Calendar");

    // Combo boxes for selecting calendar type and imported calendar
    private String[] calendarTypes = new String[]{"Normal", "Personal", "Family"};
    private String[] importedCalendars = new String[]{};
    private JComboBox<String> calendarTypeBox = new JComboBox<>(calendarTypes);
    private JComboBox<String> importedCalendarBox = new JComboBox<>(importedCalendars);

    /**
     * Constructs the AddCalendarFrame pop-up and initializes all UI components.
     */
    public AddCalendarFrame() {
        this.setTitle("Add Calendar");

        // Panels
        headerPanel.setBounds(0, 0, 400, 100);
        headerPanel.setBackground(Color.CYAN);
        contentPanel.setBounds(0, 100, 400, 300);
        contentPanel.setBackground(Color.LIGHT_GRAY);

        // components for headerPanel
        headerPanel.setLayout(null);
        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setBounds(122, 20, 200, 50);
        headerPanel.add(titleLabel);

        // components for contentPanel
        contentPanel.setLayout(null);
        calendarNameField.setToolTipText("Enter the name of the calendar");
        calendarPasswordField.setToolTipText("Enter the password (only family calendars)");
        calendarNameField.setFont(FixedValues.BUTTON_FONT);
        calendarPasswordField.setFont(FixedValues.BUTTON_FONT);
        calendarNameField.setBounds(50, 5, 300, 30);
        calendarPasswordField.setBounds(50, 40, 300, 30);
        contentPanel.add(calendarNameField);
        contentPanel.add(calendarPasswordField);
        calendarPasswordField.setVisible(false);

        // Add button
        addButton.setBounds(150, 75, 100, 30);
        addButton.setFocusable(false);
        contentPanel.add(addButton);

        // Add radio buttons
        creationType.setBounds(50, 110, 200, 30);
        importType.setBounds(50, 140, 200, 30);

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(creationType);
        group.add(importType);

        // Add radio buttons to contentPanel
        contentPanel.add(creationType);
        contentPanel.add(importType);

        // Add JComboBox
        calendarTypeBox.setBounds(47, 180, 300, 30);
        contentPanel.add(calendarTypeBox);
        calendarTypeBox.setVisible(false);

        importedCalendarBox.setBounds(47, 220, 300, 30);
        contentPanel.add(importedCalendarBox);
        importedCalendarBox.setVisible(false);

        // Add panels to Frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }

     /**
     * Returns the add button used to submit the calendar.
     * @return the JButton for adding a calendar
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * Returns the radio button for creating a new calendar.
     * @return the JRadioButton for create mode
     */
    public JRadioButton getCreationType() {
        return creationType;
    }

    /**
     * Returns the radio button for importing a calendar.
     * @return the JRadioButton for import mode
     */
    public JRadioButton getImportType() {
        return importType;
    }

    /**
     * Returns the combo box used for selecting imported calendars.
     * @return the JComboBox containing imported calendars
     */
    public JComboBox<String> getImportedCalendarBox() {
        return importedCalendarBox;
    }

    /**
     * Returns the text field for entering the calendar password.
     * @return the JTextField for calendar password
     */
    public JTextField getCalendarPasswordField(){
        return calendarPasswordField;
    }

    /**
     * Returns the combo box for selecting calendar type.
     * @return the JComboBox for calendar types
     */
    public JComboBox<String> getCalendarTypeBox() {
        return calendarTypeBox;
    }

    /**
     * Returns the text field for entering the calendar name.
     * @return the JTextField for calendar name
     */
    public JTextField getCalendarNameField() {
        return calendarNameField;
    }

    /**
     * Checks if the creation type radio button is selected.
     * @return true if create mode is selected, false otherwise
     */
    public boolean isCreationTypeSelected() {
        return creationType.isSelected();
    }

    /**
     * Checks if the import type radio button is selected.
     * @return true if import mode is selected, false otherwise
     */
    public boolean isImportTypeSelected() {
        return importType.isSelected();
    }

    /**
     * Adds an ActionListener to the add button.
     * @param actionListener the ActionListener to attach
     */
    public void setButtonActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    /**
     * Sets the calendar name in the input field.
     * @param name the name to be set
     */
    public void setCalendarName(String name) {
        calendarNameField.setText(name);
        updateGUI();
    }

    /**
     * Adds an ActionListener to the import radio button.
     * @param actionListener the ActionListener to attach
     */
    public void setImportCalendarListener(ActionListener actionListener) {
        importType.addActionListener(actionListener);
    }

    /**
     * Adds an ActionListener to the creation radio button.
     * @param actionListener the ActionListener to attach
     */
    public void setCreationTypeListener(ActionListener actionListener) {
        creationType.addActionListener(actionListener);
    }

    /**
     * Adds an ActionListener to the calendar type combo box.
     * @param actionListener the ActionListener to attach
     */
    public void setCalendarTypeBoxListener(ActionListener actionListener) {
        calendarTypeBox.addActionListener(actionListener);
    }

    /**
     * Refreshes the GUI by revalidating and repainting the frame.
     */
    public void updateGUI(){
        this.revalidate();
        this.repaint();
    }

    /**
     * Switches to create mode: hides import combo box and shows calendar type combo box.
     */
    public void createMode(){
        importedCalendarBox.setVisible(false);
        calendarTypeBox.setVisible(true);
        updateGUI();
    }

    /**
     * Switches to import mode: hides calendar type combo box and shows import combo box.
     */
    public void importMode(){
        importedCalendarBox.setVisible(true);
        calendarTypeBox.setVisible(false);
        updateGUI();
    }

    /**
     * Shows the password field, used for family calendar types.
     */
    public void familyCalendarMode() {
        calendarPasswordField.setVisible(true);
        updateGUI();
    }

    /**
     * Hides the password field, used for other calendar types.
     */
    public void anyCalendarMode() {
        calendarPasswordField.setVisible(false);
        updateGUI();
    }
}