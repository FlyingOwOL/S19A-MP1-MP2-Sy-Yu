package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.FlowLayout;

public class AddTask extends PopUpFormat{
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel titleLabel = new JLabel("Add Task");
    private JLabel priorityLabel = new JLabel("Priority:");
    private JLabel statusLabel = new JLabel("Status:");
    private JLabel createdByLabel = new JLabel("Created By:");
    private JLabel finishedByLabel = new JLabel("Finished By:");

    private JTextField createdByField = new JTextField();
    private JTextField finishedByField = new JTextField();

    private String[] priorityTypes = {"High", "Medium", "Low"};
    private String[] statusTypes = {"Pending", "Done"};
    private JComboBox<String> priorityBox = new JComboBox<>(priorityTypes);
    private JComboBox<String> statusBox = new JComboBox<>(statusTypes);

    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);
    private JButton submitButton = new JButton("Submit");

    public AddTask() {
        this.setTitle("Add Task");

        // Set up header panel
        headerPanel.setBounds(0, 0, 400, 50);
        headerPanel.setBackground(Color.CYAN);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the title label
        titleLabel.setFont(FixedValues.TITLE_FONT);
        headerPanel.add(titleLabel);
        this.add(headerPanel);

        // Set up content panel
        contentPanel.setBounds(0, 50, 400, 350);
        contentPanel.setLayout(null); // Use null layout for manual positioning
        this.add(contentPanel);

        // Position labels and fields with fixed coordinates
        priorityLabel.setBounds(20, 20, 100, 20);
        priorityBox.setBounds(120, 20, 250, 25);
        
        statusLabel.setBounds(20, 60, 100, 20);
        statusBox.setBounds(120, 60, 250, 25);
        
        createdByLabel.setBounds(20, 100, 100, 20);
        createdByField.setBounds(120, 100, 250, 25);
        
        finishedByLabel.setBounds(20, 140, 100, 20);
        finishedByField.setBounds(120, 140, 250, 25);
        
        // Configure detail area
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true); // Allow editing
        detailScrollPane.setBounds(5, 175, 375, 100); // Set bounds for the scroll pane
        contentPanel.add(detailScrollPane); // Add scroll pane to content panel

        // Configure submit button
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 280, 100, 30); // Set bounds for the button
        contentPanel.add(submitButton); // Add button to content panel

        // Add labels and fields to content panel
        contentPanel.add(priorityLabel);
        contentPanel.add(priorityBox);
        contentPanel.add(statusLabel);
        contentPanel.add(statusBox);
        contentPanel.add(createdByLabel);
        contentPanel.add(createdByField);
        contentPanel.add(finishedByLabel);
        contentPanel.add(finishedByField);

        this.setVisible(true);
    }
}
