package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The AddJournal class provides a pop-up window for users to add a journal entry.
 * It contains input fields for a journal detail, the month of the entry, and a submit button.
 * This class extends PopUpFormat for consistent styling and structure.
 */
public class AddJournal extends PopUpFormat {
    // Panels for header and content
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Header label for the pop-up
    private JLabel headerLabel = new JLabel("Add Journal Entry");

    // Text area for entering journal details
    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);

    // Submit button to save the journal entry
    private JButton submitButton = new JButton("Submit");

    // Text field for entering the month of the journal entry
    private JTextField monthField = new JTextField();
    private JLabel dateLabel = new JLabel("Enter month(e.g September, May..):");

    /**
     * Constructs the AddJournal pop-up window, initializes and lays out all UI components.
     */
    public AddJournal() {
        this.setTitle("Add Journal");

        // Set up header panel
        headerPanel.setBounds(0, 0, 400, 50);
        headerPanel.setBackground(Color.CYAN);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(headerLabel);
        this.add(headerPanel);

        // Set up content panel
        contentPanel.setBounds(0, 50, 400, 350);
        contentPanel.setLayout(null);
        this.add(contentPanel);

        // Configure header label
        headerLabel.setFont(FixedValues.TITLE_FONT);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Configure detail area
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(true);
        detailScrollPane.setBounds(5, 30, 375, 240);
        contentPanel.add(detailScrollPane);

        // Configure submit button
        submitButton.setFocusable(false);
        submitButton.setBounds(150, 280, 100, 30);
        contentPanel.add(submitButton);

        // Add month field
        monthField.setBounds(210, 5, 80, 20);
        monthField.setFont(FixedValues.JOURNAL_FONT);
        contentPanel.add(monthField);

        // Add label for month field
        dateLabel.setBounds(5, 5, 200, 20);
        dateLabel.setFont(FixedValues.LABEL_FONT);
        contentPanel.add(dateLabel);

        this.setVisible(true);
    }

    /**
     * Gets the text area for entering journal details.
     *
     * @return the detail area text input
     */
    public JTextArea getDetailArea() {
        return this.detailArea;
    }

    /**
     * Gets the text field where the user enters the month of the journal entry.
     *
     * @return the month text input field
     */
    public JTextField getMonthField() {
        return this.monthField;
    }

    /**
     * Gets the submit button that is clicked to save the journal entry.
     *
     * @return the submit button
     */
    public JButton getsubmitButton() {
        return this.submitButton;
    }

    /**
     * Attaches an ActionListener to the submit button.
     *
     * @param actionListener the listener that handles submit button clicks
     */
    public void setButtonActionListener(ActionListener actionListener) {
        this.submitButton.addActionListener(actionListener);
    }
}