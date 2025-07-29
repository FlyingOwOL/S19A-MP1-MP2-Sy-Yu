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
 * Pop-up window for adding an event entry to the calendar.
 */
public class AddEvent extends PopUpFormat {
    // Panels for header and content
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Labels for the header and input fields
    private JLabel titleLabel = new JLabel("Add Event");
    private JLabel organizerLabel = new JLabel("Organizer:");
    private JLabel venueLabel = new JLabel("Venue:");
    private JLabel startDateLabel = new JLabel("Start date:");
    private JLabel endDateLabel = new JLabel("End date:");
    private JLabel detailsLabel = new JLabel("Details:");
    private JLabel startTimeLabel = new JLabel("Start time:");
    private JLabel endTimeLabel = new JLabel("End time:");

    // Input fields for event details
    private JTextField titleField = new JTextField("add title");
    private JTextField organizerField = new JTextField();
    private JTextField venueField = new JTextField();
    private JTextField startDateField = new JTextField();
    private JTextField endDateField = new JTextField();

    // Combo boxes for start and end times
    private JComboBox<String> startTimeBox = new JComboBox<>(FixedValues.timeSlots);
    private JComboBox<String> endTimeBox = new JComboBox<>(FixedValues.timeSlots);
    
    // Text area for additional event details
    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);
    
    // Submit button for adding the event
    private JButton submitButton = new JButton("SUBMIT");

    /**
     * Constructor for the AddEvent pop-up window.
     * Initializes the layout and components of the pop-up.
     */
    public AddEvent() {
        this.setTitle("Add Event");

        // Panel set bounds
        headerPanel.setBounds(0, 0, 400, 70);
        headerPanel.setBackground(Color.CYAN);
        contentPanel.setBounds(0, 70, 400, 430);
        contentPanel.setLayout(null);
        

        //components for headerPane
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setPreferredSize(new Dimension(400, 50));
        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel);

        // components for contentPane
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
        
        // Configure text area
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true);

        // Submit button
        submitButton.setFont(FixedValues.BUTTON_FONT);
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 300, 100, 30);

        // Add components to content panel
        contentPanel.add(titleField);
        contentPanel.add(organizerLabel);
        contentPanel.add(organizerField);
        contentPanel.add(venueLabel);
        contentPanel.add(venueField);
        contentPanel.add(startDateLabel);
        contentPanel.add(startDateField);
        contentPanel.add(endDateLabel);
        contentPanel.add(startTimeLabel);
        contentPanel.add(startTimeBox);
        contentPanel.add(endTimeLabel);
        contentPanel.add(endTimeBox);
        contentPanel.add(endDateField);
        contentPanel.add(detailsLabel);
        contentPanel.add(detailScrollPane);
        contentPanel.add(submitButton);
        
        //add panels to frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }
    
        /**
     * Gets the title text field where the user enters the event title.
     *
     * @return the title input field
     */
    public JTextField getTitleField() {
        return titleField;
    }

    /**
     * Gets the organizer text field where the user enters the organizer's name.
     *
     * @return the organizer input field
     */
    public JTextField getOrganizerField() {
        return organizerField;
    }

    /**
     * Gets the venue text field where the user enters the event venue.
     *
     * @return the venue input field
     */
    public JTextField getVenueField() {
        return venueField;
    }

    /**
     * Gets the start date text field where the user enters the starting date of the event.
     *
     * @return the start date input field
     */
    public JTextField getStartDateField() {
        return startDateField;
    }

    /**
     * Gets the end date text field where the user enters the ending date of the event.
     *
     * @return the end date input field
     */
    public JTextField getEndDateField() {
        return endDateField;
    }

    /**
     * Gets the combo box containing the list of start time options for the event.
     *
     * @return the start time combo box
     */
    public JComboBox<String> getStartTime() {
        return this.startTimeBox;
    }

    /**
     * Gets the combo box containing the list of end time options for the event.
     *
     * @return the end time combo box
     */
    public JComboBox<String> getEndTime() {
        return this.endTimeBox;
    }

    /**
     * Gets the text area where the user can type in detailed information about the event.
     *
     * @return the detail input text area
     */
    public JTextArea getDetailArea() {
        return detailArea;
    }

    /**
     * Gets the submit button used to finalize and submit the event entry.
     *
     * @return the submit button
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Sets an action listener for the submit button. This listener handles submit actions.
     *
     * @param actionListener the action listener to attach to the submit button
     */
    public void setButtonActionListener(ActionListener actionListener) {
        this.submitButton.addActionListener(actionListener);
    }

}