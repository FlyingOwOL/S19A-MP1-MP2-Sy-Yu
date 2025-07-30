package Views;

import Utilities.FixedValues;
import Views.AddEntryPopUps.*;
import Views.Add_Delete_Calendar_PopUps.*;
import Controllers.Listeners_AddEntryPopUps.*;

import javax.swing.*;

import Models.Account.AccountModel;
import Models.Calendar.CalendarParentModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class AccountPage extends JFrame {
    private JPanel headerPanel = new JPanel();
    private JPanel calendarPanel = new JPanel();
    private JPanel footerPanel = new JPanel();
    private JPanel leftSidePanel = new JPanel();
    private JPanel rightSidePanel = new JPanel();

    private JLabel dateLabel = new JLabel();
    private JLabel footerLabel = new JLabel("Footer Information");

    private JButton previousButton = new JButton("<-");
    private JButton nextButton = new JButton("->");
    private JButton jumpDateButton = new JButton("Jump to Date");

    private String[] entrySelection = {"-- Select Entry Type --", "Event", "Task", "Meeting", "Journal"};
    private String[] accountSelection = {"View Entries","Switch", "Add Calendar", "Delete Calendar", "View Journal", "Sign out"};
    private String[] calendarDisplayModes = {"Month", "Week"};
    private JComboBox<String> entriesBox = new JComboBox<>(entrySelection);
    private JComboBox<String> accountsBox = new JComboBox<>(accountSelection);
    private JComboBox<String> calendarDisplayBox = new JComboBox<>(calendarDisplayModes);

    private CalendarMonthlyView monthlyCalendarView;
    private CalendarWeeklyView weeklyCalendarView;

    private AccountModel currentAccount;
    private ArrayList<CalendarParentModel> calendarList;
    private CalendarParentModel currentCalendar;
    private EntriesDisplayView entriesDisplayView;

    private AddCalendarFrame addCalendarFrame;
    private DeleteCalendarFrame deleteCalendarFrame;
    private ViewJournal viewJournal;
    private SwitchCalendarFrame switchCalendarFrame;

    private AddEvent addEvent;
    private AddTask addTask;
    private AddMeeting addMeeting;
    private AddJournal addJournal;

    public AccountPage(AccountModel account) {
        this.currentAccount = account;
        this.calendarList = account.getCalendars();
        this.currentCalendar = calendarList.get(0);

        // Initialize calendar views with current date
        this.monthlyCalendarView = new CalendarMonthlyView();
        this.weeklyCalendarView = new CalendarWeeklyView();

        this.setTitle("Welcome back " + currentAccount.getName() + "!");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1010, 637);
        this.setResizable(false);
        this.setLayout(null);

        // Set bounds for each panel in the frame
        headerPanel.setBounds(50, 0, 900, 100);
        headerPanel.setBackground(Color.BLUE);
        calendarPanel.setBounds(50, 100, 900, 400);
        calendarPanel.setBackground(Color.LIGHT_GRAY);
        footerPanel.setBounds(50, 500, 900, 100);
        footerPanel.setBackground(Color.GREEN);
        leftSidePanel.setBounds(0, 0, 50, 637);
        leftSidePanel.setBackground(Color.MAGENTA);
        rightSidePanel.setBounds(950, 0, 50, 637);
        rightSidePanel.setBackground(Color.MAGENTA);

        // Components for headerPanel
        headerPanel.setLayout(null);
        dateLabel.setFont(FixedValues.TITLE_FONT);
        dateLabel.setHorizontalTextPosition(JLabel.CENTER);
        dateLabel.setBounds(350, 25, 350, 50);
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

        // Components for calendarPanel
        calendarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        calendarPanel.add(monthlyCalendarView); // Start with monthly view

        // Components for footerPanel
        footerPanel.setLayout(null);
        footerLabel.setFont(FixedValues.TITLE_FONT);
        footerLabel.setBounds(20, 25, 200, 50);
        footerPanel.add(footerLabel);

        entriesBox.setBounds(600, 25, 290, 50);
        footerPanel.add(entriesBox);

        // DIRECT CONNECTION TO ENTRIES DROPDOWN - THIS FIXES THE ISSUE
        entriesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEntry = (String) entriesBox.getSelectedItem();
                
                // Skip if placeholder is selected
                if (selectedEntry.equals("-- Select Entry Type --")) {
                    return;
                }
                
                JFrame popUp = null;
                
                switch (selectedEntry) {
                    case "Task":
                        popUp = new AddTask();
                        addTask = (AddTask) popUp;
                        addTask.setButtonActionListener(new AddTaskListener(addTask, AccountPage.this));
                        break;
                    case "Event":
                        popUp = new AddEvent();
                        addEvent = (AddEvent) popUp;
                        addEvent.setButtonActionListener(new AddEventListener(addEvent, AccountPage.this));
                        break;
                    case "Meeting":
                        popUp = new AddMeeting();
                        addMeeting = (AddMeeting) popUp;
                        addMeeting.setButtonActionListener(new AddMeetingListener(addMeeting, AccountPage.this));
                        break;
                    case "Journal":
                        popUp = new AddJournal();
                        addJournal = (AddJournal) popUp;
                        addJournal.setButtonActionListener(new AddJournalListener(addJournal, AccountPage.this));
                        break;
                }

                if (popUp != null) {
                    popUp.setLocationRelativeTo(AccountPage.this);
                    popUp.setVisible(true);
                }
                
                // Reset selection to placeholder
                entriesBox.setSelectedIndex(0);
            }
        });

        jumpDateButton.setBounds(360, 25, 150, 50);
        jumpDateButton.setFont(FixedValues.BUTTON_FONT);
        jumpDateButton.setFocusable(false);
        footerPanel.add(jumpDateButton);

        // Add panels to the frame
        this.add(leftSidePanel);
        this.add(rightSidePanel);
        this.add(headerPanel);
        this.add(calendarPanel);
        this.add(footerPanel);
        this.setVisible(true);
    }

    // getters
    public String getSelectedCalendarDisplay() {
        return (String) calendarDisplayBox.getSelectedItem();
    }
    public String getSelectedEntry() {
        return (String) entriesBox.getSelectedItem();
    }
    public String getSelectedAccount() {
        return (String) accountsBox.getSelectedItem();
    }
    public AddCalendarFrame getAddCalendarFrame() {
        return addCalendarFrame;
    }
    public DeleteCalendarFrame getDeleteCalendarFrame() {
        return deleteCalendarFrame;
    }
    public ViewJournal getViewJournal() {
        return viewJournal;
    }
    public EntriesDisplayView getEntriesDisplayView(){
        return this.entriesDisplayView;
    }
    public SwitchCalendarFrame getSwitchCalendarFrame() {
        return switchCalendarFrame;
    }
    public AccountModel getCurrentAccount() {
        return this.currentAccount;
    }
    public AddEvent getAddEvent() {
        return this.addEvent;
    }
    public AddTask getAddTask() {
        return this.addTask;
    }
    public AddMeeting getAddMeeting() {
        return this.addMeeting;
    }
    public AddJournal getAddJournal() {
        return this.addJournal;
    }
    public CalendarParentModel getCalendarByName(String calendarName) {
        CalendarParentModel returnCalendar = null;
        for (CalendarParentModel calendar : calendarList) {
            if (calendar.getName().equals(calendarName)) {
                returnCalendar =  calendar;
            }
        }
        return returnCalendar; // Return null if no account found
    }
    public CalendarParentModel getCurrentCalendar(){
        return this.currentCalendar;
    }

    //setters
    public void updateGUI(){
        this.dateLabel.revalidate();
        this.dateLabel.repaint();
        this.calendarPanel.revalidate();
        this.calendarPanel.repaint();
    }

    public void changeCalendarDisplay(String displayMode) {
        calendarPanel.removeAll(); // Clear existing components
        if (displayMode.equals("Month")) {
            calendarPanel.add(monthlyCalendarView);
        } else {
            calendarPanel.add(weeklyCalendarView);
        }
        updateGUI();
    }

    // New method to update calendar views with current date from CalendarDateController
    public void updateCalendarViewsWithDate(LocalDate date) {
        monthlyCalendarView.updateDate(date);
        weeklyCalendarView.updateDate(date);

        // Refresh the current view
        String currentDisplay = getSelectedCalendarDisplay();
        changeCalendarDisplay(currentDisplay);
    }

    public void switchCurrentCalendar (CalendarParentModel newCalendar){
        this.currentCalendar = newCalendar;
        updateGUI();
    }

    public void setAddCalendarFrame(AddCalendarFrame addCalendarFrame) {
        this.addCalendarFrame = addCalendarFrame;
    }
    public void setDeleteCalendarFrame(DeleteCalendarFrame deleteCalendarFrame) {
        this.deleteCalendarFrame = deleteCalendarFrame;
    }
    public void setViewJournal(ViewJournal viewJournal) {
        this.viewJournal = viewJournal;
    }
    public void setSwitchCalendarFrame(SwitchCalendarFrame switchCalendarFrame) {
        this.switchCalendarFrame = switchCalendarFrame;
    }
    public void setEntriesDisplayView(EntriesDisplayView entriesDisplayView){
        this.entriesDisplayView = entriesDisplayView;
    }

    public void setAddEvent(AddEvent addEvent) {
        this.addEvent = addEvent;
    }
    public void setAddTask(AddTask addTask) {
        this.addTask = addTask;
    }
    public void setAddMeeting(AddMeeting addMeeting) {
        this.addMeeting = addMeeting;
    }
    public void setAddJournal(AddJournal addJournal) {
        this.addJournal = addJournal;
    }

    public void updateDateLabel(String dateText) {
        dateLabel.setText(dateText);
        updateGUI();
    }
    public void changeCalendarDisplay(ActionListener e) {
        calendarDisplayBox.addActionListener(e);
    }
    public void selectPopUps(ActionListener e) {
        entriesBox.addActionListener(e);
    }
    public void changeAccountSelection(ActionListener e) {
        accountsBox.addActionListener(e);
    }
    public void setPreviousButtonListener(ActionListener listener) {
        previousButton.addActionListener(listener);
    }
    public void setNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }
    public void setJumpDateButtonListener(ActionListener listener) {
        jumpDateButton.addActionListener(listener);
    }
}
