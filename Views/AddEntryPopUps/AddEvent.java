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
 * Represents a pop-up form for adding an Event entry.
 * Users can input event details such as title, organizer, venue, date, time, and description.
 */
public class AddEvent extends PopUpFormat {
    // UI Components
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel titleLabel = new JLabel("Add Event");
    private JLabel organizerLabel = new JLabel("Organizer:");
    private JLabel venueLabel = new JLabel("Venue:");
    private JLabel startDateLabel = new JLabel("Start date:");
    private JLabel endDateLabel = new JLabel("End date:");
    private JLabel detailsLabel = new JLabel("Details:");
    private JLabel startTimeLabel = new JLabel("Start time:");
    private JLabel endTimeLabel = new JLabel("End time:");

    private JTextField titleField = new JTextField("add title");
    private JTextField organizerField = new JTextField();
    private JTextField venueField = new JTextField();
    private JTextField startDateField = new JTextField();
    private JTextField endDateField = new JTextField();

    private JComboBox<String> startTimeBox = new JComboBox<>(FixedValues.timeSlots);
    private JComboBox<String> endTimeBox = new JComboBox<>(FixedValues.timeSlots);

    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);

    private JButton submitButton = new JButton("SUBMIT");

    /**
     * Constructs the AddEvent pop-up and initializes all UI components.
     */
    public AddEvent() {
        this.setTitle("Add Event");
        this.startTimeBox.setSelectedItem("12:00:00");
        this.endTimeBox.setSelectedItem("13:00:00");

        headerPanel.setBounds(0, 0, 400, 70);
        headerPanel.setBackground(Color.CYAN);
        contentPanel.setBounds(0, 70, 400, 430);
        contentPanel.setLayout(null);

        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setPreferredSize(new Dimension(400, 50));
        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel);

        // Layout for form fields
        titleField.setBounds(20, 20, 350, 20);
        organizerLabel.setBounds(20, 45, 100, 20);
        organizerField.setBounds(120, 45, 250, 25);
        venueLabel.setBounds(20, 75, 100, 20);
        venueField.setBounds(120, 75, 250, 25);
        startDateLabel.setBounds(20, 105, 100, 20);
        startDateField.setBounds(120, 105, 250, 25);
        endDateLabel.setBounds(20, 135, 100, 20);
        endDateField.setBounds(120, 135, 250, 25);
        startTimeLabel.setBounds(20, 165, 100, 20);
        startTimeBox.setBounds(120, 165, 250, 25);
        endTimeLabel.setBounds(20, 195, 100, 20);
        endTimeBox.setBounds(120, 195, 250, 25);
        detailsLabel.setBounds(20, 225, 100, 20);
        detailScrollPane.setBounds(120, 225, 250, 70);
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true);

        submitButton.setFont(FixedValues.BUTTON_FONT);
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 300, 100, 30);

        // Add all components
        contentPanel.add(titleField);
        contentPanel.add(organizerLabel);
        contentPanel.add(organizerField);
        contentPanel.add(venueLabel);
        contentPanel.add(venueField);
        contentPanel.add(startDateLabel);
        contentPanel.add(startDateField);
        contentPanel.add(endDateLabel);
        contentPanel.add(endDateField);
        contentPanel.add(startTimeLabel);
        contentPanel.add(startTimeBox);
        contentPanel.add(endTimeLabel);
        contentPanel.add(endTimeBox);
        contentPanel.add(detailsLabel);
        contentPanel.add(detailScrollPane);
        contentPanel.add(submitButton);

        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }

    /**
     * Returns the title text field.
     * @return the title text field
     */
    public JTextField getTitleField() {
        return titleField;
    }

    /**
     * Returns the organizer text field.
     * @return the organizer text field
     */
    public JTextField getOrganizerField() {
        return organizerField;
    }

    /**
     * Returns the venue text field.
     * @return the venue text field
     */
    public JTextField getVenueField() {
        return venueField;
    }

    /**
     * Returns the start date text field.
     * @return the start date text field
     */
    public JTextField getStartDateField() {
        return startDateField;
    }

    /**
     * Returns the end date text field.
     * @return the end date text field
     */
    public JTextField getEndDateField() {
        return endDateField;
    }

    /**
     * Returns the start time combo box.
     * @return the start time combo box
     */
    public JComboBox<String> getStartTime() {
        return this.startTimeBox;
    }

    /**
     * Returns the end time combo box.
     * @return the end time combo box
     */
    public JComboBox<String> getEndTime() {
        return this.endTimeBox;
    }

    /**
     * Returns the details text area.
     * @return the detail area text component
     */
    public JTextArea getDetailArea() {
        return detailArea;
    }

    /**
     * Returns the submit button.
     * @return the submit button
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Adds an ActionListener to the submit button.
     * @param actionListener the listener to handle submit actions
     */
    public void setButtonActionListener(ActionListener actionListener) {
        this.submitButton.addActionListener(actionListener);
    }

    /**
     * Sets the event title.
     * @param title the event title
     */
    public void setTitleField(String title) {
        this.titleField.setText(title);
    }

    /**
     * Sets the organizer name.
     * @param organizer the organizer's name
     */
    public void setOrganizerField(String organizer) {
        this.organizerField.setText(organizer);
    }

    /**
     * Sets the venue.
     * @param venue the venue of the event
     */
    public void setVenueField(String venue) {
        this.venueField.setText(venue);
    }

    /**
     * Sets the start date.
     * @param startDate the start date of the event
     */
    public void setStartDateField(String startDate) {
        this.startDateField.setText(startDate);
    }

    /**
     * Sets the end date.
     * @param endDate the end date of the event
     */
    public void setEndDateField(String endDate) {
        this.endDateField.setText(endDate);
    }

    /**
     * Sets the details text area.
     * @param details the details of the event
     */
    public void setDetailArea(String details) {
        this.detailArea.setText(details);
    }

    /**
     * Sets the selected start time.
     * @param startTime the start time to select
     */
    public void setStartTime(String startTime) {
        this.startTimeBox.setSelectedItem(startTime);
    }

    /**
     * Sets the selected end time.
     * @param endTime the end time to select
     */
    public void setEndTime(String endTime) {
        this.endTimeBox.setSelectedItem(endTime);
    }

    /**
     * Refreshes the GUI layout and repaint.
     */
    public void updateGUI() {
        this.revalidate();
        this.repaint();
    }
}
