package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;

public class AddEvent extends PopUpFormat {
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

    public AddEvent() {
        this.setTitle("Add Event");
        this.startTimeBox.setSelectedItem("12:00:00");
        this.endTimeBox.setSelectedItem("13:00:00");
        
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
    //getters
    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getOrganizerField() {
        return organizerField;
    }

    public JTextField getVenueField() {
        return venueField;
    }

    public JTextField getStartDateField() {
        return startDateField;
    }

    public JTextField getEndDateField() {
        return endDateField;
    }  

    public JComboBox<String> getStartTime(){
        return this.startTimeBox;
    }

    public JComboBox<String> getEndTime(){
        return this.endTimeBox;
    }
    
    public JTextArea getDetailArea() {
        return detailArea;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }


    //setters
    public void setButtonActionListener(ActionListener actionListener){
        this.submitButton.addActionListener(actionListener);
    }

    public void setTitleField(String title) {
        this.titleField.setText(title);
    }

    public void setOrganizerField(String organizer) {
        this.organizerField.setText(organizer);
    }

    public void setVenueField(String venue) {
        this.venueField.setText(venue);
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