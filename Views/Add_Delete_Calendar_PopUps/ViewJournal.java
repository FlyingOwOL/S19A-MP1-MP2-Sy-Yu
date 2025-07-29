package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class defines the pop-up GUI for viewing journal entries for each month.
 * It includes a non-editable text area, a month selector, and a close button.
 */
public class ViewJournal extends PopUpFormat {
    // Label for the journal window title
    private JLabel journalLabel = new JLabel("This is the Month's Journal");

    // Text area to display the journal content
    private JTextArea journalContent = new JTextArea("Lahat ng pagmamahal\n" +
            "At oras na aking binigay\n" +
            "Parang 'di mo pansin\n" +
            "Ang sama ko sa 'yong paningin\n" +
            "Hmm, hmm");

    // Panels to organize the layout
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Button to close the window
    private JButton closeButton = new JButton("Close");

    // Scroll pane to enable scrolling in the journal content area
    private JScrollPane scrollPane = new JScrollPane(journalContent);

    // Month selector dropdown (will be populated dynamically later)
    private String[] Months = {"Jan", "Mar", "Apr"};
    private JComboBox<String> monthComboBox = new JComboBox<>(Months);

    /**
     * Constructor sets up the layout, components, and visibility of the journal viewer popup.
     */
    public ViewJournal() {
        this.setTitle("This Month's Journal");

        // Setup header panel
        headerPanel.setBounds(0, 0, 400, 100);
        headerPanel.setBackground(Color.CYAN);
        headerPanel.setLayout(null);

        journalLabel.setFont(FixedValues.TITLE_FONT);
        journalLabel.setHorizontalTextPosition(JLabel.CENTER);
        journalLabel.setBounds(0, 20, 400, 50);
        headerPanel.add(journalLabel);

        // Setup content panel
        contentPanel.setBounds(0, 100, 400, 300);
        contentPanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.setLayout(null);

        journalContent.setFont(FixedValues.JOURNAL_FONT);
        journalContent.setLineWrap(true);
        journalContent.setWrapStyleWord(true);
        journalContent.setEditable(false);

        scrollPane.setBounds(0, 0, 340, 220);
        contentPanel.add(scrollPane);

        monthComboBox.setBounds(340, 0, 50, 25);
        contentPanel.add(monthComboBox);

        closeButton.setFont(FixedValues.BUTTON_FONT);
        closeButton.setFocusable(false);
        closeButton.setBounds(140, 225, 100, 30);
        contentPanel.add(closeButton);

        // Add components to frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }

    /**
     * Gets the journal label at the top of the popup.
     *
     * @return JLabel component displaying the journal title
     */
    public JLabel getJournalLabel() {
        return journalLabel;
    }

    /**
     * Gets the text area containing the journal content.
     * The content is read-only.
     *
     * @return JTextArea displaying the journal entry
     */
    public JTextArea getJournalContent() {
        return journalContent;
    }

    /**
     * Gets the close button.
     * This button is used to close the popup window.
     *
     * @return JButton for closing the popup
     */
    public JButton getCloseButton() {
        return closeButton;
    }

    /**
     * Gets the month selection dropdown.
     * This allows the user to choose which month's journal to view.
     *
     * @return JComboBox containing the list of months
     */
    public JComboBox<String> getMonthComboBox() {
        return monthComboBox;
    }
}