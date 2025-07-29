package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;

public class AddMeeting extends PopUpFormat {
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    
    private String[] modalities = {"online", "hybrid", "onsite"};
    private JComboBox<String> modalityBox = new JComboBox<>(modalities);
    private JComboBox<String> startTimeBox = new JComboBox<>(FixedValues.timeSlots);
    private JComboBox<String> endTimeBox = new JComboBox<>(FixedValues.timeSlots);

    private JTextField venueField = new JTextField();
    private JTextField linkField = new JTextField();
    private JTextField startDateField = new JTextField();
    private JTextField endDateField = new JTextField();
    private JTextField titleField = new JTextField("Add title");

    private JLabel titleLabel = new JLabel("Add Meeting");
    private JLabel modalityLabel = new JLabel("Modality:");
    private JLabel venueLabel = new JLabel("Venue:");
    private JLabel linkLabel = new JLabel("Link:");
    private JLabel startLabel = new JLabel("Start Date:");
    private JLabel endDateLabel = new JLabel("End Date:");
    private JLabel startTimeLabel = new JLabel("Start time:");
    private JLabel endTimeLabel = new JLabel("End Time:");
    
    private JTextArea detailArea = new JTextArea();

    private JScrollPane detailScrollPane = new JScrollPane(detailArea);
    private JButton submitButton = new JButton("Submit");

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

    //getters
    public JTextField getTitleField() {
        return this.titleField;
    }
    public JComboBox<String> getModalityBox() {
        return this.modalityBox;
    }
    public JComboBox<String> getStartTimeBox(){
        return this.startTimeBox;
    }
    public JComboBox<String> getEndTimeBox(){
        return this.endTimeBox;
    }
    public JTextField getVenueField() {
        return this.venueField;
    }
    public JTextField getLinkField() {
        return this.linkField;
    }
    public JTextField getStartDateField() {
        return this.startDateField;
    }
    public JTextField getEndDateField() {
        return this.endDateField;
    }
    public JTextArea getDetailArea() {
        return this.detailArea;
    }
    public JButton getSubmitButton() {
        return this.submitButton;
    }

    //setters
    public void setButtonActionListener(ActionListener actionListener) {
        System.out.println("setButtonActionListener called!");
        submitButton.addActionListener(actionListener);
        System.out.println("ActionListener added to submitButton!");
    }

    public void setTitleField(String title) {
        this.titleField.setText(title);
    }

    public void setVenueField(String venue) {
        this.venueField.setText(venue);
    }

    public void setLinkField(String link) {
        this.linkField.setText(link);
    }

    public void setStartDateField(String startDate) {
        this.startDateField.setText(startDate);
    }

    public void setEndDateField(String endDate) {
        this.endDateField.setText(endDate);
    }

    public void setDetailArea(String details) {
        this.detailArea.setText(details);
    }

    public void setModality(String modality) {
        this.modalityBox.setSelectedItem(modality);
    }

    public void setStartTime(String startTime) {
        this.startTimeBox.setSelectedItem(startTime);
    }

    public void setEndTime(String endTime) {
        this.endTimeBox.setSelectedItem(endTime);
    }

    public void updateGUI(){
        this.revalidate();
        this.repaint();
    }
}
