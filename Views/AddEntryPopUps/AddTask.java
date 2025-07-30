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
 * A pop-up window for adding a new task entry.
 * 
 * This form allows the user to input details such as title, priority, status,
 * who created and finished the task, and additional notes or descriptions.
 */
public class AddTask extends PopUpFormat {

    // Header and content panels
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Labels
    private JLabel titleLabel = new JLabel("Add Task");
    private JLabel priorityLabel = new JLabel("Priority:");
    private JLabel statusLabel = new JLabel("Status:");
    private JLabel createdByLabel = new JLabel("Created By:");
    private JLabel finishedByLabel = new JLabel("Finished By:");
    private JLabel dateLabel = new JLabel("Date:");

    // Input fields
    private JTextField createdByField = new JTextField();
    private JTextField finishedByField = new JTextField();
    private JTextField titleField = new JTextField();
    private JTextField dateField = new JTextField();

    // Combo boxes
    private String[] priorityTypes = {"High", "Medium", "Low"};
    private String[] statusTypes = {"Pending", "Done"};
    private JComboBox<String> priorityBox = new JComboBox<>(priorityTypes);
    private JComboBox<String> statusBox = new JComboBox<>(statusTypes);

    // Detail area
    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);

    // Submit button
    private JButton submitButton = new JButton("Submit");

    /**
     * Constructs the AddTask pop-up window and initializes all GUI components.
     */
    public AddTask() {
        this.setTitle("Add Task");

        // Header panel setup
        headerPanel.setBounds(0, 0, 400, 50);
        headerPanel.setBackground(Color.CYAN);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titleLabel.setFont(FixedValues.TITLE_FONT);
        headerPanel.add(titleLabel);
        this.add(headerPanel);

        // Content panel setup
        contentPanel.setBounds(0, 50, 400, 350);
        contentPanel.setLayout(null);
        this.add(contentPanel);

        // Field positions
        titleField.setBounds(20, 20, 350, 20);

        dateLabel.setBounds(20, 45, 100, 20);
        dateField.setBounds(120, 45, 250, 25);

        priorityLabel.setBounds(20, 75, 100, 20);
        priorityBox.setBounds(120, 75, 250, 25);

        statusLabel.setBounds(20, 105, 100, 20);
        statusBox.setBounds(120, 105, 250, 25);

        createdByLabel.setBounds(20, 135, 100, 20);
        createdByField.setBounds(120, 135, 250, 25);

        finishedByLabel.setBounds(20, 165, 100, 20);
        finishedByField.setBounds(120, 165, 250, 25);

        // Detail area setup
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true);
        detailScrollPane.setBounds(20, 195, 350, 100);
        contentPanel.add(detailScrollPane);

        // Submit button setup
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 310, 100, 30);
        contentPanel.add(submitButton);

        // Add all remaining components
        contentPanel.add(titleField);
        contentPanel.add(dateLabel);
        contentPanel.add(dateField);
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
     * Returns the text field used for entering the task title.
     *
     * @return the task title input field
     */
    public JTextField getTitleField() {
        return titleField;
    }

    /**
     * Returns the combo box for selecting the task's priority.
     *
     * @return the priority selection combo box
     */
    public JComboBox<String> getPriorityBox() {
        return priorityBox;
    }

    /**
     * Returns the combo box for selecting the task's status.
     *
     * @return the status selection combo box
     */
    public JComboBox<String> getStatusBox() {
        return statusBox;
    }

    public JTextField getDateField(){
        return dateField;
    }

    /**
     * Returns the text field for specifying who created the task.
     *
     * @return the "created by" input field
     */
    public JTextField getCreatedByField() {
        return createdByField;
    }

    /**
     * Returns the text field for specifying who finished the task.
     *
     * @return the "finished by" input field
     */
    public JTextField getFinishedByField() {
        return finishedByField;
    }

    /**
     * Returns the text area for entering additional task details.
     *
     * @return the detail text area
     */
    public JTextArea getDetailArea() {
        return detailArea;
    }

    /**
     * Returns the submit button.
     *
     * @return the submit button
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Sets an ActionListener for the submit button.
     *
     * @param actionListener the listener to attach to the submit button
     */
    public void setButtonActionListener(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
    }

    /**
     * Sets the task title in the title field.
     *
     * @param title the title to set
     */
    public void setTitleField(String title) {
        this.titleField.setText(title);
    }

    /**
     * Sets the name of the person who created the task.
     *
     * @param createdBy the name to set
     */
    public void setCreatedByField(String createdBy) {
        this.createdByField.setText(createdBy);
    }

    /**
     * Sets the name of the person who finished the task.
     *
     * @param finishedBy the name to set
     */
    public void setFinishedByField(String finishedBy) {
        this.finishedByField.setText(finishedBy);
    }

    /**
     * Sets the task details in the detail area.
     *
     * @param details the task description to set
     */
    public void setDetailArea(String details) {
        this.detailArea.setText(details);
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority the priority to select (High, Medium, Low)
     */
    public void setPriority(String priority) {
        this.priorityBox.setSelectedItem(priority);
    }

    /**
     * Sets the status of the task.
     *
     * @param status the status to select (Pending, Done)
     */
    public void setStatus(String status) {
        this.statusBox.setSelectedItem(status);
    }

    public void setDate(String date){
        this.dateField.setText(date);
    }

    /**
     * Refreshes the pop-up by revalidating and repainting the components.
     */
    public void updateGUI() {
        this.revalidate();
        this.repaint();
    }
}
