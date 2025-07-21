package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
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

    private JTextField organizerField = new JTextField();
    private JTextField venueField = new JTextField();
    private JTextField startDateField = new JTextField();
    private JTextField endDateField = new JTextField();
    
    private JTextArea detailArea = new JTextArea("put la la la la ooh la lal alala ooh");
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);
    
    private JButton submitButton = new JButton("SUBMIT");

    public AddEvent() {
        this.setTitle("Add Event");

        // Panel set bounds
        headerPanel.setBounds(0, 0, 400, 70);
        headerPanel.setBackground(Color.CYAN);
        contentPanel.setBounds(0, 70, 400, 330);
        contentPanel.setLayout(null);
        

        //components for headerPane
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setPreferredSize(new Dimension(400, 50));
        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel);

        // components for contentPane
        organizerLabel.setBounds(20, 20, 100, 20);
        organizerField.setBounds(120, 20, 250, 25);
        
        venueLabel.setBounds(20, 50, 100, 20);
        venueField.setBounds(120, 50, 250, 25);
        
        startDateLabel.setBounds(20, 80, 100, 20);
        startDateField.setBounds(120, 80, 250, 25);
        
        endDateLabel.setBounds(20, 110, 100, 20);
        endDateField.setBounds(120, 110, 250, 25);
        
        detailsLabel.setBounds(20, 140, 100, 20);
        detailScrollPane.setBounds(120, 140, 250, 100);
        
        // Configure text area
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true);

        // Submit button
        submitButton.setFont(FixedValues.BUTTON_FONT);
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 250, 100, 30);

        // Add components to content panel
        contentPanel.add(organizerLabel);
        contentPanel.add(organizerField);
        contentPanel.add(venueLabel);
        contentPanel.add(venueField);
        contentPanel.add(startDateLabel);
        contentPanel.add(startDateField);
        contentPanel.add(endDateLabel);
        contentPanel.add(endDateField);
        contentPanel.add(detailsLabel);
        contentPanel.add(detailScrollPane);
        contentPanel.add(submitButton);
        
        //add panels to frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }
}
