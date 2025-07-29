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
 * A pop-up window for adding a new calendar.
 * This class provides the GUI layout for entering calendar details.
 */
public class AddCalendarFrame extends PopUpFormat {
    // Panels for layout
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Components for the header
    private JLabel titleLabel = new JLabel("Add Calendar");

    // Input fields for calendar details
    private JTextField calendarNameField = new JTextField();
    private JTextField calendarPasswordField = new JTextField();

    // Button to submit the new calendar
    private JButton addButton = new JButton("Add");

    // Radio buttons for calendar creation type
    private JRadioButton creationType = new JRadioButton("Create New Calendar");
    private JRadioButton importType = new JRadioButton("Import Calendar");

    
    private String[] calendarTypes = new String[]{"Normal", "Personal", "Family"};
    //TODO importedCalendars dynamically change depending on what's in public calendars
    private String[] importedCalendars = new String[]{"Suck", "My", "Calendar", "off", "Bro"};

    // Combo boxes for selecting calendar type and imported calendars
    private JComboBox<String> calendarTypeBox = new JComboBox<>(calendarTypes);
    private JComboBox<String> importedCalendarBox = new JComboBox<>(importedCalendars);

    /**
     * Constructs the Add Calendar frame layout and components.
     * Initializes the GUI components and sets their properties.
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
     * Returns the button used to add a new calendar.
     * This is typically used by controller classes to attach an ActionListener
     * that handles the addition of a new calendar when the button is clicked.
     *
     * @return the JButton component for adding a calendar
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * Returns the text field for entering the calendar name.
     * This can be used to retrieve the name input by the user before adding a new calendar.
     *
     * @return the JTextField component for the calendar name
     */
    public JRadioButton getCreationType() {
        return creationType;
    }

    /**
     * Returns the text field for entering the calendar password.
     * This is typically used for family calendars where a password is required.
     *
     * @return the JTextField component for the calendar password
     */
    public JRadioButton getImportType() {
        return importType;
    }

    /**
     * Returns the combo box for selecting the type of calendar.
     * This can be used to retrieve the selected calendar type before adding a new calendar.
     *
     * @return the JComboBox<String> component for selecting calendar type
     */
    public JComboBox<String> getImportedCalendarBox() {
        return importedCalendarBox;
    }

    /**
     * Returns the combo box for selecting the type of calendar.
     * This can be used to retrieve the selected calendar type before adding a new calendar.
     *
     * @return the JComboBox<String> component for selecting calendar type
     */
    public JTextField getCalendarPasswordField(){
        return calendarPasswordField;
    }

    /**
     * Returns the combo box for selecting the type of calendar.
     * This can be used to retrieve the selected calendar type before adding a new calendar.
     *
     * @return the JComboBox<String> component for selecting calendar type
     */
    public JComboBox<String> getCalendarTypeBox() {
        return calendarTypeBox;
    }

    /**
     * Returns the text field for entering the calendar name.
     * This can be used to retrieve the name input by the user before adding a new calendar.
     *
     * @return the JTextField component for the calendar name
     */
    public JTextField getCalendarNameField() {
        return calendarNameField;
    }

    /**
     * Checks if the creation type radio button is selected.
     * This indicates whether the user wants to create a new calendar.
     *
     * @return true if the creation type is selected, false otherwise
     */
    public boolean isCreationTypeSelected() {
        return creationType.isSelected();
    }

    /**
     * Checks if the import type radio button is selected.
     * This indicates whether the user wants to import an existing calendar.
     *
     * @return true if the import type is selected, false otherwise
     */
    public boolean isImportTypeSelected() {
        return importType.isSelected();
    }

    /**
     * Sets the action listener for the add button.
     * This allows the controller to define what happens when the button is clicked.
     *
     * @param actionListener the ActionListener to be set for the add button
     */
    public void setButtonActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    /**
     * Sets the name of the calendar in the text field.
     * This can be used to pre-fill the calendar name field or update it dynamically.
     *
     * @param name the new name of the calendar to be set
     */
    public void setCalendarName(String name) {
        calendarNameField.setText(name);
        updateGUI();
    }

    /**
     * Sets the action listener for the import type radio button.
     * This allows the controller to define what happens when the import type is selected.
     *
     * @param actionListener the ActionListener to be set for the import type radio button
     */
    public void setImportCalendarListener(ActionListener actionListener) {
        importType.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the creation type radio button.
     * This allows the controller to define what happens when the creation type is selected.
     *
     * @param actionListener the ActionListener to be set for the creation type radio button
     */
    public void setCreationTypeListener(ActionListener actionListener) {
        creationType.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the calendar type combo box.
     * This allows the controller to define what happens when a calendar type is selected.
     *
     * @param actionListener the ActionListener to be set for the calendar type combo box
     */
    public void setCalendarTypeBoxListener(ActionListener actionListener) {
        calendarTypeBox.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the imported calendar combo box.
     * This allows the controller to define what happens when an imported calendar is selected.
     *
     * @param actionListener the ActionListener to be set for the imported calendar combo box
     */
    public void updateGUI(){
        this.revalidate();
        this.repaint();
    }

    /**
     * Sets the action listener for the imported calendar combo box.
     * This allows the controller to define what happens when an imported calendar is selected.
     *
     * @param actionListener the ActionListener to be set for the imported calendar combo box
     */
    public void createMode(){
        importedCalendarBox.setVisible(false);
        calendarTypeBox.setVisible(true);
        updateGUI();
    }

    /**
     * Sets the action listener for the imported calendar combo box.
     * This allows the controller to define what happens when an imported calendar is selected.
     *
     * @param actionListener the ActionListener to be set for the imported calendar combo box
     */
    public void importMode(){
        importedCalendarBox.setVisible(true);
        calendarTypeBox.setVisible(false);
        updateGUI();
    }

    /**
     * Sets the action listener for the imported calendar combo box.
     * This allows the controller to define what happens when an imported calendar is selected.
     *
     * @param actionListener the ActionListener to be set for the imported calendar combo box
     */
    public void familyCalendarMode() {
        calendarPasswordField.setVisible(true);
        updateGUI();
    }

    /**
     * Sets the action listener for the imported calendar combo box.
     * This allows the controller to define what happens when an imported calendar is selected.
     *
     * @param actionListener the ActionListener to be set for the imported calendar combo box
     */
    public void anyCalendarMode() {
        calendarPasswordField.setVisible(false);
        updateGUI();
    }
}