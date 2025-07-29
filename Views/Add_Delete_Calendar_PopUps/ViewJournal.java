package Views.Add_Delete_Calendar_PopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import java.awt.Color;

public class ViewJournal extends PopUpFormat {
    private JLabel journalLabel = new JLabel("This is the Month's Journal");

    // Use JTextArea instead of JLabel for journal content
    private JTextArea journalContent = new JTextArea("Lahat ng pagmamahal\n" +
            "At oras na aking binigay\n" +
            "Parang 'di mo pansin\n" +
            "Ang sama ko sa 'yong paningin\n" +
            "Hmm, hmm");

    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JButton closeButton = new JButton("Close");

    private JScrollPane scrollPane = new JScrollPane(journalContent);

    private String[] Months = {"Jan", "Mar", "Apr"}; // will change dynamically later
    private JComboBox<String> monthComboBox = new JComboBox<>(Months);

    public ViewJournal() {
        this.setTitle("This Month's Journal");

        // Panel set bounds
        headerPanel.setBounds(0, 0, 400, 100);
        headerPanel.setBackground(Color.CYAN);
        contentPanel.setBounds(0, 100, 400, 300);
        contentPanel.setBackground(Color.LIGHT_GRAY);

        // Components for header
        headerPanel.setLayout(null);
        journalLabel.setFont(FixedValues.TITLE_FONT);
        journalLabel.setHorizontalTextPosition(JLabel.CENTER);
        journalLabel.setBounds(0, 20, 400, 50);
        headerPanel.add(journalLabel);

        // Components for content
        contentPanel.setLayout(null);
        journalContent.setFont(FixedValues.JOURNAL_FONT);
        journalContent.setLineWrap(true);
        journalContent.setWrapStyleWord(true);
        journalContent.setEditable(false);

        monthComboBox.setBounds(340, 0, 50, 25);
        contentPanel.add(monthComboBox);

        scrollPane.setBounds(0, 0, 340, 220);
        contentPanel.add(scrollPane);

        closeButton.setFont(FixedValues.BUTTON_FONT);
        closeButton.setFocusable(false);
        closeButton.setBounds(140, 225, 100, 30);
        contentPanel.add(closeButton);

        // Add to frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.setVisible(true);
    }

    // Getter methods for the listener
    public JLabel getJournalLabel() {
        return journalLabel;
    }

    public JTextArea getJournalContent() {
        return journalContent;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JComboBox<String> getMonthComboBox() {
        return monthComboBox;
    }
}