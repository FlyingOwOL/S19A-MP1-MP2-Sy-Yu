package Views.AddEntryPopUps;

import Utilities.FixedValues;
import Views.PopUpFormat;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class represents the UI for adding a journal entry.
 * It provides input components for writing journal details and selecting a month.
 */
public class AddJournal extends PopUpFormat {
    // UI components for the AddJournal pop-up
    private JPanel headerPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel headerLabel = new JLabel("Add Journal Entry");
    
    private JTextArea detailArea = new JTextArea();
    private JScrollPane detailScrollPane = new JScrollPane(detailArea);

    private JButton submitButton = new JButton("Submit");

    private JComboBox<String> monthBox = new JComboBox<>(FixedValues.monthNames);
    private JLabel dateLabel = new JLabel("Enter month(e.g September, May..):");

    /**
     * Constructs an AddJournal pop-up UI with all required components and layout.
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

        // Add month selection
        monthBox.setBounds(210, 5, 80, 20);
        monthBox.setFont(FixedValues.JOURNAL_FONT);
        contentPanel.add(monthBox);

        dateLabel.setBounds(5, 5, 200, 20);
        dateLabel.setFont(FixedValues.LABEL_FONT);
        contentPanel.add(dateLabel);

        this.setVisible(true);
    }

    /**
     * Returns the text area used for entering journal details.
     *
     * @return the detail area text component
     */
    public JTextArea getDetailArea() {
        return this.detailArea;
    }

    /**
     * Returns the combo box for selecting the journal month.
     *
     * @return the month selection combo box
     */
    public JComboBox<String> getMonthtField(){
        return this.monthBox;
    }

    /**
     * Returns the submit button used to finalize the journal entry.
     *
     * @return the submit button
     */
    public JButton getsubmitButton() {
        return this.submitButton;
    }

    /**
     * Sets an ActionListener to the submit button.
     *
     * @param actionListener the ActionListener to attach
     */
    public void setButtonActionListener(ActionListener actionListener) {
        this.submitButton.addActionListener(actionListener);
    }

    /**
     * Sets the content of the detail area.
     *
     * @param detailArea the text to display in the detail area
     */
    public void setDetailArea(String detailArea){
        this.detailArea.setText(detailArea);
    }

    /**
     * Sets the selected item in the month combo box.
     *
     * @param month the month to be selected
     */
    public void setMonthBox(String month){
        this.monthBox.setSelectedItem(month);
    }

    /**
     * Refreshes the GUI to apply layout or content changes.
     */
    public void updateGUI(){
        this.revalidate();
        this.repaint();
    }
}
