package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import java.awt.Dimension;
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
 * A pop-up window for adding a new meeting entry.
 * 
 * This form allows the user to input details such as title, modality, venue,
 * link, start and end dates, and times for the meeting.
 */
public class AddMeeting extends PopUpFormat {
    // Header and content panels
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    
    // Labels and input fields
    private String[] modalities = {"online", "hybrid", "onsite"};
    private JComboBox<String> modalityBox = new JComboBox<>(modalities);
    private JComboBox<String> startTimeBox = new JComboBox<>(FixedValues.timeSlots);
    private JComboBox<String> endTimeBox = new JComboBox<>(FixedValues.timeSlots);

    // Input fields for meeting details
    private JTextField venueField = new JTextField();
    private JTextField linkField = new JTextField();
    private JTextField startDateField = new JTextField();
    private JTextField endDateField = new JTextField();
    private JTextField titleField = new JTextField("Add title");

    // Labels for the input fields
    private JLabel titleLabel = new JLabel("Add Meeting");
    private JLabel modalityLabel = new JLabel("Modality:");
    private JLabel venueLabel = new JLabel("Venue:");
    private JLabel linkLabel = new JLabel("Link:");
    private JLabel startLabel = new JLabel("Start Date:");
    private JLabel endDateLabel = new JLabel("End Date:");
    private JLabel startTimeLabel = new JLabel("Start time:");
    private JLabel endTimeLabel = new JLabel("End Time:");
    
    private JTextArea detailArea = new JTextArea();

    // Scroll pane for the detail area
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);
    private JButton submitButton = new JButton("Submit");

    /**
     * Constructs the AddMeeting pop-up window and initializes all GUI components.
     */
    public AddMeeting() {
        this.setTitle("Add Meeting");
        this.startTimeBox.setSelectedItem("12:00:00");
        this.endTimeBox.setSelectedItem("13:00:00");

        // Set up header panel
        headerPanel.setBounds(0, 0, 400, 50);
        headerPanel.setBackground(Color.CYAN);
        this.add(headerPanel);


        // components for header
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        headerPanel.setPreferredSize(new Dimension(400, 60));
        titleLabel.setFont(FixedValues.TITLE_FONT);
        headerPanel.add(titleLabel);

        // Set up content panel
        contentPanel.setBounds(0, 60, 400, 440);
        contentPanel.setLayout(null); // Use null layout for manual positioning
        this.add(contentPanel);

        // Position labels and fields with fixed coordinates
        titleField.setBounds(20, 20, 350, 20);

        modalityLabel.setBounds(20, 45, 100, 20);
        modalityBox.setBounds(120, 45, 250, 25);
        
        venueLabel.setBounds(20, 75, 100, 20);
        venueField.setBounds(120, 75, 250, 25);
        
        linkLabel.setBounds(20, 105, 100, 20);
        linkField.setBounds(120, 105, 250, 25);
        
        startLabel.setBounds(20, 135, 100, 20);
        startDateField.setBounds(120, 135, 250, 25);
        
        endDateLabel.setBounds(20, 165, 100, 20);
        endDateField.setBounds(120, 165, 250, 25);

        startTimeLabel.setBounds(20, 195, 100, 20);
        startTimeBox.setBounds(120, 195, 250, 25);

        endTimeLabel.setBounds(20, 225, 100, 20);
        endTimeBox.setBounds(120, 225, 250, 25);
        
        // Configure detail area
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true); // Allow editing
        detailScrollPane.setBounds(20, 255, 350, 75); // Set bounds for the scroll pane
        contentPanel.add(detailScrollPane); // Add scroll pane to content panel

        // Configure submit button
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 335, 100, 30); // Set bounds for the button
        contentPanel.add(submitButton); // Add button to content panel

        // Add labels and fields to content panel
        contentPanel.add(titleField);
        contentPanel.add(modalityLabel);
        contentPanel.add(modalityBox);
        contentPanel.add(venueLabel);
        contentPanel.add(venueField);
        contentPanel.add(linkLabel);
        contentPanel.add(linkField);
        contentPanel.add(startLabel);
        contentPanel.add(startDateField);
        contentPanel.add(endDateLabel);
        contentPanel.add(endDateField);
        contentPanel.add(startTimeLabel);
        contentPanel.add(startTimeBox);
        contentPanel.add(endTimeLabel);
        contentPanel.add(endTimeBox);

        this.setVisible(true);
    }

    /**
     * Gets the text field used for entering the meeting title.
     *
     * @return the meeting title input field
     */
    public JTextField getTitleField() {
        return this.titleField;
    }

    /**
     * Gets the combo box for selecting the meeting's modality.
     *
     * @return the modality selection combo box
     */
    public JComboBox<String> getModalityBox() {
        return this.modalityBox;
    }

    /**
     * Gets the combo box for selecting the meeting's start time.
     *
     * @return the start time selection combo box
     */
    public JComboBox<String> getStartTimeBox(){
        return this.startTimeBox;
    }

    /**
     * Gets the combo box for selecting the meeting's end time.
     *
     * @return the end time selection combo box
     */
    public JComboBox<String> getEndTimeBox(){
        return this.endTimeBox;
    }

    /**
     * Gets the text field for specifying the meeting's venue.
     *
     * @return the venue input field
     */
    public JTextField getVenueField() {
        return this.venueField;
    }

    /**
     * Gets the text field for specifying the meeting's link.
     *
     * @return the link input field
     */
    public JTextField getLinkField() {
        return this.linkField;
    }

    /**
     * Gets the text field for specifying the meeting's start date.
     *
     * @return the start date input field
     */
    public JTextField getStartDateField() {
        return this.startDateField;
    }

    /**
     * Gets the text field for specifying the meeting's end date.
     *
     * @return the end date input field
     */
    public JTextField getEndDateField() {
        return this.endDateField;
    }

    /**
     * Returns the text area for entering additional meeting details.
     *
     * @return the detail text area
     */
    public JTextArea getDetailArea() {
        return this.detailArea;
    }

    /**
     * Returns the submit button for the meeting entry.
     *
     * @return the submit button
     */
    public JButton getSubmitButton() {
        return this.submitButton;
    }

    /**
     * Sets the action listener for the submit button.
     *
     * @param actionListener the ActionListener to be attached to the submit button
     */
    public void setButtonActionListener(ActionListener actionListener) {
        System.out.println("setButtonActionListener called!");
        submitButton.addActionListener(actionListener);
        System.out.println("ActionListener added to submitButton!");
    }

    /**
     * Sets the text for the title input field.
     *
     * @param title the title to set in the title field
     */
    public void setTitleField(String title) {
        this.titleField.setText(title);
    }

    /**
     * Sets the text for the venue input field.
     *
     * @param venue the venue to set in the venue field
     */
    public void setVenueField(String venue) {
        this.venueField.setText(venue);
    }

    /**
     * Sets the text for the link input field.
     *
     * @param link the link to set in the link field
     */
    public void setLinkField(String link) {
        this.linkField.setText(link);
    }

    /**
     * Sets the text for the start date input field.
     *
     * @param startDate the start date to set in the start date field
     */
    public void setStartDateField(String startDate) {
        this.startDateField.setText(startDate);
    }

    /**
     * Sets the text for the end date input field.
     *
     * @param endDate the end date to set in the end date field
     */
    public void setEndDateField(String endDate) {
        this.endDateField.setText(endDate);
    }

    /**
     * Sets the text in the details text area.
     *
     * @param details the details to set in the text area
     */
    public void setDetailArea(String details) {
        this.detailArea.setText(details);
    }

    /**
     * Sets the selected modality in the combo box.
     *
     * @param modality the modality option to select
     */
    public void setModality(String modality) {
        this.modalityBox.setSelectedItem(modality);
    }

    /**
     * Sets the selected start time in the combo box.
     *
     * @param startTime the start time option to select
     */
    public void setStartTime(String startTime) {
        this.startTimeBox.setSelectedItem(startTime);
    }

    /**
     * Sets the selected end time in the combo box.
     *
     * @param endTime the end time option to select
     */
    public void setEndTime(String endTime) {
        this.endTimeBox.setSelectedItem(endTime);
    }

    /**
     * Refreshes the GUI to reflect any updates or changes.
     */
    public void updateGUI() {
        this.revalidate();
        this.repaint();
    }
}
