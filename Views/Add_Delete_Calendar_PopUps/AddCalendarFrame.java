package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Color;

import java.awt.event.ActionListener;

public class AddCalendarFrame extends PopUpFormat {
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel titleLabel = new JLabel("Add Calendar");

    private JTextField calendarNameField = new JTextField();
    private JTextField calendarPasswordField = new JTextField();

    private JButton addButton = new JButton("Add");

    private JRadioButton creationType = new JRadioButton("Create New Calendar");
    private JRadioButton importType = new JRadioButton("Import Calendar");

    private String[] calendarTypes = new String[]{"Normal", "Personal", "Family"};
    //TODO importedCalendars dynamically change depending on what's in public calendars
    private String[] importedCalendars = new String[]{"Suck", "My", "Calendar", "off", "Bro"};
    private JComboBox<String> calendarTypeBox = new JComboBox<>(calendarTypes);
    private JComboBox<String> importedCalendarBox = new JComboBox<>(importedCalendars);

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

    //getters
    public JButton getAddButton() {
        return addButton;
    }
    public JRadioButton getCreationType() {
        return creationType;
    }
    public JRadioButton getImportType() {
        return importType;
    }
    public JComboBox<String> getImportedCalendarBox() {
        return importedCalendarBox;
    }
    public JTextField getCalendarPasswordField(){
        return calendarPasswordField;
    }
    public JComboBox<String> getCalendarTypeBox() {
        return calendarTypeBox;
    }
    public JTextField getCalendarNameField() {
        return calendarNameField;
    }
    public boolean isCreationTypeSelected() {
        return creationType.isSelected();
    }
    public boolean isImportTypeSelected() {
        return importType.isSelected();
    }

    //setters
    public void setButtonActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }
    public void setCalendarName(String name) {
        calendarNameField.setText(name);
        updateGUI();
    }
    public void setImportCalendarListener(ActionListener actionListener) {
        importType.addActionListener(actionListener);
    }
    public void setCreationTypeListener(ActionListener actionListener) {
        creationType.addActionListener(actionListener);
    }
    public void setCalendarTypeBoxListener(ActionListener actionListener) {
        calendarTypeBox.addActionListener(actionListener);
    }
    public void updateGUI(){
        this.revalidate();
        this.repaint();
    }

    public void createMode(){
        importedCalendarBox.setVisible(false);
        calendarTypeBox.setVisible(true);
        updateGUI();
    }
    public void importMode(){
        importedCalendarBox.setVisible(true);
        calendarTypeBox.setVisible(false);
        updateGUI();
    }
    public void familyCalendarMode() {
        calendarPasswordField.setVisible(true);
        updateGUI();
    }
    public void anyCalendarMode() {
        calendarPasswordField.setVisible(false);
        updateGUI();
    }
}