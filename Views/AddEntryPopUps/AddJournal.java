package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class AddJournal extends PopUpFormat {
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel headerLabel = new JLabel("Add Journal Entry");
    
    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);

    private JButton submitButton = new JButton("Submit");

    private JComboBox<String> monthBox = new JComboBox<>(FixedValues.monthNames);
    private JLabel dateLabel = new JLabel("Enter month(e.g September, May..):");

    public AddJournal() {
        this.setTitle("Add Journal");

        // Set up header panel
        headerPanel.setBounds(0, 0, 400, 50);
        headerPanel.setBackground(Color.CYAN);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the header label
        headerPanel.add(headerLabel);
        this.add(headerPanel);

        // Set up content panel
        contentPanel.setBounds(0, 50, 400, 350);
        contentPanel.setLayout(null); // Use null layout for manual positioning
        this.add(contentPanel);

        // Configure header label
        headerLabel.setFont(FixedValues.TITLE_FONT); // Set font from FixedValues
        headerLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally

        // Configure detail area
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true); // Allow editing
        detailScrollPane.setBounds(5, 30, 375, 240); // Set bounds for the scroll pane
        contentPanel.add(detailScrollPane); // Add scroll pane to content panel

        // Configure close button
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 280, 100, 30); // Set bounds for the button
        contentPanel.add(submitButton); // Add button to content panel


        //add date field
        monthBox.setBounds(210, 5, 80, 20);
        monthBox.setFont(FixedValues.JOURNAL_FONT);
        contentPanel.add(monthBox);

        dateLabel.setBounds(5, 5, 200, 20);
        dateLabel.setFont(FixedValues.LABEL_FONT);
        contentPanel.add(dateLabel);

        this.setVisible(true);
    }

    //getters
    public JTextArea getDetailArea() {
        return this.detailArea;
    }
    public JComboBox<String> getMonthtField(){
        return this.monthBox;
    }
    public JButton getsubmitButton() {
        return this.submitButton;
    }
    

    //setters
    public void setButtonActionListener(ActionListener actionListener) {
        this.submitButton.addActionListener(actionListener);
    }
}
