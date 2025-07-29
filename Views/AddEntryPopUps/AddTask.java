package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Pop-up window for adding a task entry to the calendar.
 */
public class AddTask extends PopUpFormat{
    // Panels for header and content
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Header label for the pop-up
    private JLabel titleLabel = new JLabel("Add Task");
    private JLabel priorityLabel = new JLabel("Priority:");
    private JLabel statusLabel = new JLabel("Status:");
    private JLabel createdByLabel = new JLabel("Created By:");
    private JLabel finishedByLabel = new JLabel("Finished By:");

    // Input fields for task details
    private JTextField createdByField = new JTextField();
    private JTextField finishedByField = new JTextField();

    // Priority and status selection boxes
    private String[] priorityTypes = {"High", "Medium", "Low"};
    private String[] statusTypes = {"Pending", "Done"};
    private JComboBox<String> priorityBox = new JComboBox<>(priorityTypes);
    private JComboBox<String> statusBox = new JComboBox<>(statusTypes);

    // Text field for task title
    private JTextField titleField = new JTextField();
    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);
    private JButton submitButton = new JButton("Submit");

    /**
     * Constructor for the AddTask pop-up window.
     * Initializes the layout and components of the pop-up.
     */
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

    /**
    * Gets the title input field for the task.
    *
    * @return the JTextField for entering the task title
    */
    public JTextField getTitleField() {
        return titleField;
    }

    /**
     * Gets the combo box for selecting the task's priority.
     *
     * @return the JComboBox containing priority options (High, Medium, Low)
     */
    public JComboBox<String> getPriorityBox() {
        return priorityBox;
    }

    /**
     * Gets the combo box for selecting the task's status.
     *
     * @return the JComboBox containing status options (Pending, Done)
     */
    public JComboBox<String> getStatusBox() {
        return statusBox;
    }

    /**
     * Gets the input field for specifying who created the task.
     *
     * @return the JTextField for entering the creator's name
     */
    public JTextField getCreatedByField() {
        return createdByField;
    }

    /**
     * Gets the input field for specifying who is expected to finish the task.
     *
     * @return the JTextField for entering the finisher's name
     */
    public JTextField getFinishedByField() {
        return finishedByField;
    }

    /**
     * Gets the text area for entering additional details about the task.
     *
     * @return the JTextArea for task description
     */
    public JTextArea getDetailArea() {
        return detailArea;
    }

    /**
     * Gets the submit button for the pop-up.
     *
     * @return the JButton used to submit the task information
     */
    public JButton getSubmitButton() {
        return submitButton;
    }


    /**
     * Adds an ActionListener to the submit button.
     *
     * @param actionListener the ActionListener to be attached to the submit button
     */
    public void setButtonActionListener(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
    }
}