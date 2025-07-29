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
import java.awt.event.ActionListener;

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

    private JTextField titleField = new JTextField();
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
        titleField.setBounds(20, 20, 350, 20);

        priorityLabel.setBounds(20, 45, 100, 20);
        priorityBox.setBounds(120, 45, 250, 25);
        
        statusLabel.setBounds(20, 75, 100, 20);
        statusBox.setBounds(120, 75, 250, 25);
        
        createdByLabel.setBounds(20, 105, 100, 20);
        createdByField.setBounds(120, 105, 250, 25);
        
        finishedByLabel.setBounds(20, 135, 100, 20);
        finishedByField.setBounds(120, 135, 250, 25);
        
        // Configure detail area
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true); // Allow editing
        detailScrollPane.setBounds(20, 165, 350, 100); // Set bounds for the scroll pane
        contentPanel.add(detailScrollPane); // Add scroll pane to content panel

        // Configure submit button
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 280, 100, 30); // Set bounds for the button
        contentPanel.add(submitButton); // Add button to content panel

        // Add labels and fields to content panel
        contentPanel.add(titleField);
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

    //getters
    public JTextField getTitleField() {
        return titleField;
    }
    public JComboBox<String> getPriorityBox() {
        return priorityBox;
    }
    public JComboBox<String> getStatusBox() {
        return statusBox;
    }
    public JTextField getCreatedByField() {
        return createdByField;
    }
    public JTextField getFinishedByField() {
        return finishedByField;
    }
    public JTextArea getDetailArea() {
        return detailArea;
    }
    public JButton getSubmitButton() {
        return submitButton;
    }


    //setters
    public void setButtonActionListener(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
    }

    // Setters for JTextField and JTextArea
    public void setTitleField(String title) {
        this.titleField.setText(title);
    }

    public void setCreatedByField(String createdBy) {
        this.createdByField.setText(createdBy);
    }

    public void setFinishedByField(String finishedBy) {
        this.finishedByField.setText(finishedBy);
    }

    public void setDetailArea(String details) {
        this.detailArea.setText(details);
    }

    // Setters for JComboBox
    public void setPriority(String priority) {
        this.priorityBox.setSelectedItem(priority);
    }

    public void setStatus(String status) {
        this.statusBox.setSelectedItem(status);
    }

    public void updateGUI(){
        this.revalidate();
        this.repaint();
    }
}
