package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Color;

public class AddCalendarFrame extends JFrame {
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel titleLabel = new JLabel("Add Calendar");
        
    private JTextField calendarNameField = new JTextField();    

    private JButton addButton = new JButton("Add");

    private JRadioButton creationType = new JRadioButton("Create New Calendar");
    private JRadioButton importType = new JRadioButton("Import Calendar");

    private String[] calendarTypes = new String[]{"Normal", "Personal", "Family"};
    private String[] importedCalendars = new String[]{"Suck", "My", "Calendar", "off", "Bro"};
    private JComboBox<String> calendarTypeBox = new JComboBox<>(calendarTypes);
    private JComboBox<String> importedCalendarBox = new JComboBox<>(importedCalendars);

    public AddCalendarFrame() {
        this.setTitle("Add Calendar");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 430);
        this.setResizable(false);
        this.setLayout(null);

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
        calendarNameField.setBounds(50, 20, 300, 30);
        contentPanel.add(calendarNameField);        
        addButton.setBounds(150, 70, 100, 30);
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
        importedCalendarBox.setBounds(47, 220, 300, 30);
        contentPanel.add(importedCalendarBox);

        // Add panels to Frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }
}
