package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;

public class AddJournal extends PopUpFormat {
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel headerLabel = new JLabel("Add Journal Entry");
    
    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);

    private JButton closeButton = new JButton("Submit");

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
        detailScrollPane.setBounds(5, 20, 375, 250); // Set bounds for the scroll pane
        contentPanel.add(detailScrollPane); // Add scroll pane to content panel

        // Configure close button
        closeButton.setFocusable(false);
        closeButton.setBounds(150, 280, 100, 30); // Set bounds for the button
        contentPanel.add(closeButton); // Add button to content panel

        this.setVisible(true);
    }
}
