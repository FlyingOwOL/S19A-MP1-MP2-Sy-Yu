package Views;

import Utilities.FixedValues;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class AccountPage extends JFrame{
    private JPanel headerPanel = new JPanel();
    private JPanel calendarPanel = new JPanel();
    private JPanel footerPanel = new JPanel();
    private JPanel leftSidePanel = new JPanel();
    private JPanel rightSidePanel = new JPanel();

    private JLabel dateLabel = new JLabel("Jun - Jul 2025");
    private JLabel footerLabel = new JLabel("Footer Information");

    private JButton previousButton = new JButton ("<-");
    private JButton nextButton = new JButton ("->");
    private JButton jumpDateButton = new JButton("Jump to Date");

    private String[] entrySelection = {"Event", "Task", "Meeting", "Journal"};
    private String[] accountSelection = {"Account 1", "Add Calendar", "Delete Calendar", "View Journal","Sign out"};
    private String[] calendarDisplayModes = {"Month", "Week"};
    private JComboBox<String> entriesBox = new JComboBox<>(entrySelection);
    private JComboBox<String> accountsBox = new JComboBox<>(accountSelection);
    private JComboBox<String> calendarDisplayBox = new JComboBox<>(calendarDisplayModes);

    private CalendarMonthlyView monthlyCalendarView = new CalendarMonthlyView();
    private CalendarWeeklyView weeklyCalendarView = new CalendarWeeklyView();
    
    public AccountPage(){
        this.setTitle("Calendar Page");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1010, 637);
        this.setResizable(false);
        this.setLayout(null);               

        // Set bounds for each panel in the frame
        headerPanel.setBounds(50, 0, 900, 100);
        headerPanel.setBackground(Color.BLUE);
        calendarPanel.setBounds(50, 100, 900, 400);
        calendarPanel.setBackground(Color.LIGHT_GRAY);
        footerPanel.setBounds(50, 500, 900, 637);
        footerPanel.setBackground(Color.GREEN);
        leftSidePanel.setBounds(0, 0, 50, 637);
        leftSidePanel.setBackground(Color.magenta); 
        rightSidePanel.setBounds(950, 0, 50, 637);
        rightSidePanel.setBackground(Color.magenta);       

        //components for headerPanel
        headerPanel.setLayout(null);
        dateLabel.setFont(FixedValues.TITLE_FONT);
        dateLabel.setHorizontalTextPosition(JLabel.CENTER);
        dateLabel.setBounds(350, 25, 200, 50);
        headerPanel.add(dateLabel);

        previousButton.setBounds(20, 35, 50, 30);
        previousButton.setFont(FixedValues.BUTTON_FONT);
        previousButton.setFocusable(false);
        headerPanel.add(previousButton);
        nextButton.setBounds(830, 35, 50, 30); 
        nextButton.setFont(FixedValues.BUTTON_FONT);
        nextButton.setFocusable(false);
        headerPanel.add(nextButton);


        accountsBox.setBounds(810, 70, 80, 20);
        headerPanel.add(accountsBox);
        calendarDisplayBox.setBounds(720, 70, 80, 20);
        headerPanel.add(calendarDisplayBox);

        //components for calendarPanel
        calendarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        calendarPanel.add(weeklyCalendarView);

        //components for footerPanel
        footerPanel.setLayout(null);
        footerLabel.setFont(FixedValues.TITLE_FONT);
        footerLabel.setBounds(20, 25, 200, 50);
        footerPanel.add(footerLabel);

        entriesBox.setBounds(600, 25, 290, 50);
        footerPanel.add(entriesBox);

        jumpDateButton.setBounds(360, 25, 150, 50);
        jumpDateButton.setFont(FixedValues.BUTTON_FONT);
        jumpDateButton.setFocusable(false);
        footerPanel.add(jumpDateButton);

        //add panels to the frame
        this.add(leftSidePanel);
        this.add(rightSidePanel);
        this.add(headerPanel);
        this.add(calendarPanel);
        this.add(footerPanel);
        this.setVisible(true);
    }
}
