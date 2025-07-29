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

/**
 * Represents the main account page where users can view and manage their calendars and entries.
 * This page includes features for adding, deleting, and switching calendars, as well as viewing entries.
 */
public class AccountPage extends JFrame {
    // Panels to organize the layout
    private JPanel headerPanel = new JPanel();
    private JPanel calendarPanel = new JPanel();
    private JPanel footerPanel = new JPanel();
    private JPanel leftSidePanel = new JPanel();
    private JPanel rightSidePanel = new JPanel();

    // Labels and buttons for the header and footer
    private JLabel dateLabel = new JLabel();
    private JLabel footerLabel = new JLabel("Footer Information");

    // Buttons for navigation and actions
    private JButton previousButton = new JButton("<-");
    private JButton nextButton = new JButton("->");
    private JButton jumpDateButton = new JButton("Jump to Date");

    // Combo boxes for selecting entries, accounts, and calendar display modes
    private String[] entrySelection = {"-- Select Entry Type --", "Event", "Task", "Meeting", "Journal"};
    private String[] accountSelection = {"View Entries","Switch", "Add Calendar", "Delete Calendar", "View Journal", "Sign out"};
    private String[] calendarDisplayModes = {"Month", "Week"};
    private JComboBox<String> entriesBox = new JComboBox<>(entrySelection);
    private JComboBox<String> accountsBox = new JComboBox<>(accountSelection);
    private JComboBox<String> calendarDisplayBox = new JComboBox<>(calendarDisplayModes);

    // Calendar views for monthly and weekly displays
    private CalendarMonthlyView monthlyCalendarView;
    private CalendarWeeklyView weeklyCalendarView;

    // Models and views for calendar management and entry addition
    private AccountModel currentAccount;
    private ArrayList<CalendarParentModel> calendarList;
    private CalendarParentModel currentCalendar;
    private EntriesDisplayView entriesDisplayView;

    // Pop-up windows for adding entries
    private AddCalendarFrame addCalendarFrame;
    private DeleteCalendarFrame deleteCalendarFrame;
    private ViewJournal viewJournal;
    private SwitchCalendarFrame switchCalendarFrame;

    // Pop-up windows for adding entries
    private AddEvent addEvent;
    private AddTask addTask;
    private AddMeeting addMeeting;
    private AddJournal addJournal;

    /**
     * Constructor for the AccountPage.
     * Initializes the layout, components, and sets up the initial state of the page.
     *
     * @param account The account model associated with this page.
     */ 
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

    /**
     * Gets the selected calendar display mode from the combo box.
     *
     * @return the selected display mode as a String
     */
    public String getSelectedCalendarDisplay() {
        return (String) calendarDisplayBox.getSelectedItem();
    }

    /**
     * Gets the selected entry type from the combo box.
     *
     * @return the selected entry type as a String
     */
    public String getSelectedEntry() {
        return (String) entriesBox.getSelectedItem();
    }

    /**
     * Gets the selected account from the combo box.
     *
     * @return the selected account as a String
     */
    public String getSelectedAccount() {
        return (String) accountsBox.getSelectedItem();
    }

    /**
     * Gets the current date label text.
     *
     * @return the current date label text
     */
    public AddCalendarFrame getAddCalendarFrame() {
        return addCalendarFrame;
    }

    /**
     * Gets the delete calendar frame.
     *
     * @return the delete calendar frame
     */
    public DeleteCalendarFrame getDeleteCalendarFrame() {
        return deleteCalendarFrame;
    }

    /**
     * Gets the view journal frame.
     *
     * @return the view journal frame
     */
    public ViewJournal getViewJournal() {
        return viewJournal;
    }

    /**
     * Gets the switch calendar frame.
     *
     * @return the switch calendar frame
     */
    public SwitchCalendarFrame getSwitchCalendarFrame() {
        return switchCalendarFrame;
    }

    /**
     * Gets the current account model.
     *
     * @return the current account model
     */
    public AccountModel getCurrentAccount() {
        return this.currentAccount;
    }

    /**
     * Gets the list of calendars associated with the current account.
     *
     * @return an ArrayList of CalendarParentModel objects
     */
    public AddEvent getAddEvent() {
        return this.addEvent;
    }

    /**
     * Gets the list of calendars associated with the current account.
     *
     * @return an ArrayList of CalendarParentModel objects
     */
    public AddTask getAddTask() {
        return this.addTask;
    }

    /**
     * Gets the list of calendars associated with the current account.
     *
     * @return an ArrayList of CalendarParentModel objects
     */
    public AddMeeting getAddMeeting() {
        return this.addMeeting;
    }

    /**
     * Gets the list of calendars associated with the current account.
     *
     * @return an ArrayList of CalendarParentModel objects
     */
    public AddJournal getAddJournal() {
        return this.addJournal;
    }

    /**
     * Gets the current calendar being displayed.
     *
     * @return the current CalendarParentModel
     */
    public CalendarParentModel getCalendarByName(String calendarName) {
        CalendarParentModel returnCalendar = null;
        for (CalendarParentModel calendar : calendarList) {
            // If the calendar name matches the provided name, return it
            if (calendar.getName().equals(calendarName)) {
                returnCalendar =  calendar;
            }
        }
        return returnCalendar; // Return null if no account found
    }

    /**
     * Gets the current calendar being displayed.
     *
     * @return the current CalendarParentModel
     */
    public CalendarParentModel getCurrentCalendar(){
        return this.currentCalendar;
    }

    /**
     * Gets the entries display view.
     *
     * @return the EntriesDisplayView
     */
    public void updateGUI(){
        this.dateLabel.revalidate();
        this.dateLabel.repaint();
        this.calendarPanel.revalidate();
        this.calendarPanel.repaint();
    }

    /**
     * Changes the calendar display mode based on the selected option.
     * Updates the calendar panel to show either monthly or weekly view.
     *
     * @param displayMode the selected display mode ("Month" or "Week")
     */
    public void changeCalendarDisplay(String displayMode) {
        calendarPanel.removeAll(); // Clear existing components

        // Add the appropriate calendar view based on the selected display mode
        if (displayMode.equals("Month")) {
            calendarPanel.add(monthlyCalendarView);
        } else {
            calendarPanel.add(weeklyCalendarView);
        }
        updateGUI();
    }

    /**
     * Updates the calendar views with the current date.
     * This method is called to refresh the calendar display when the date changes.
     *
     * @param date the new date to be displayed
     */
    public void updateCalendarViewsWithDate(LocalDate date) {
        monthlyCalendarView.updateDate(date);
        weeklyCalendarView.updateDate(date);

        // Refresh the current view
        String currentDisplay = getSelectedCalendarDisplay();
        changeCalendarDisplay(currentDisplay);
    }

    /**
     * Switches the current calendar to a new one.
     * This method updates the current calendar and refreshes the GUI.
     *
     * @param newCalendar the new CalendarParentModel to switch to
     */
    public void switchCurrentCalendar (CalendarParentModel newCalendar){
        this.currentCalendar = newCalendar;
        updateGUI();
    }

    /**
     * Sets the add calendar frame for this account page.
     *
     * @param addCalendarFrame the AddCalendarFrame to be set
     */
    public void setAddCalendarFrame(AddCalendarFrame addCalendarFrame) {
        this.addCalendarFrame = addCalendarFrame;
    }

    /**
     * Sets the delete calendar frame for this account page.
     *
     * @param deleteCalendarFrame the DeleteCalendarFrame to be set
     */
    public void setDeleteCalendarFrame(DeleteCalendarFrame deleteCalendarFrame) {
        this.deleteCalendarFrame = deleteCalendarFrame;
    }

    /**
     * Sets the view journal frame for this account page.
     *
     * @param viewJournal the ViewJournal to be set
     */
    public void setViewJournal(ViewJournal viewJournal) {
        this.viewJournal = viewJournal;
    }

    /**
     * Sets the switch calendar frame for this account page.
     *
     * @param switchCalendarFrame the SwitchCalendarFrame to be set
     */
    public void setSwitchCalendarFrame(SwitchCalendarFrame switchCalendarFrame) {
        this.switchCalendarFrame = switchCalendarFrame;
    }

    /**
     * Sets the add event pop-up for this account page.
     *
     * @param addEvent the AddEvent to be set
     */
    public void setAddEvent(AddEvent addEvent) {
        this.addEvent = addEvent;
    }

    /**
     * Sets the add task pop-up for this account page.
     *
     * @param addTask the AddTask to be set
     */
    public void setAddTask(AddTask addTask) {
        this.addTask = addTask;
    }

    /**
     * Sets the add meeting pop-up for this account page.
     *
     * @param addMeeting the AddMeeting to be set
     */
    public void setAddMeeting(AddMeeting addMeeting) {
        this.addMeeting = addMeeting;
    }

    /**
     * Sets the add journal pop-up for this account page.
     *
     * @param addJournal the AddJournal to be set
     */
    public void setAddJournal(AddJournal addJournal) {
        this.addJournal = addJournal;
    }

    /**
     * Updates the date label in the header panel.
     *
     * @param dateText the new date text to be displayed
     */    
    public void updateDateLabel(String dateText) {
        dateLabel.setText(dateText);
        updateGUI();
    }

    /**
     * Registers an ActionListener for the "Submit" button in the add entry pop-ups.
     *
     * @param listener the ActionListener to be added
     */
    public void changeCalendarDisplay(ActionListener e) {
        calendarDisplayBox.addActionListener(e);
    }

    /**
     * Registers an ActionListener for the "Submit" button in the add entry pop-ups.
     *
     * @param listener the ActionListener to be added
     */
    public void selectPopUps(ActionListener e) {
        entriesBox.addActionListener(e);
    }

    /**
     * Registers an ActionListener for the account selection combo box.
     *
     * @param e the ActionListener to be added
     */
    public void changeAccountSelection(ActionListener e) {
        accountsBox.addActionListener(e);
    }

    /**
     * Registers an ActionListener for the previous button.
     *
     * @param listener the ActionListener to be added
     */
    public void setPreviousButtonListener(ActionListener listener) {
        previousButton.addActionListener(listener);
    }

    /**
     * Registers an ActionListener for the next button.
     *
     * @param listener the ActionListener to be added
     */
    public void setNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    /**
     * Registers an ActionListener for the jump date button.
     *
     * @param listener the ActionListener to be added
     */
    public void setJumpDateButtonListener(ActionListener listener) {
        jumpDateButton.addActionListener(listener);
    }
}