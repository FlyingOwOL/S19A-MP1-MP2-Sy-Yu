package Views;

import Controllers.Listeners_AddEntryPopUps.*;
import Models.Account.AccountModel;
import Models.Calendar.CalendarParentModel;
import Utilities.FixedValues;
import Views.AddEntryPopUps.*;
import Views.Add_Delete_Calendar_PopUps.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Represents the main account page for a user.
 * This page allows users to view and manage their calendars, entries, and account settings.
 */
public class AccountPage extends JFrame {
    // UI components
    private JPanel headerPanel = new JPanel();
    private JPanel calendarPanel = new JPanel();
    private JPanel footerPanel = new JPanel();
    private JPanel leftSidePanel = new JPanel();
    private JPanel rightSidePanel = new JPanel();

    // Labels and buttons for header and footer
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

    // Pop-up frames for adding, deleting, and switching calendars
    private AccountModel currentAccount;
    private ArrayList<CalendarParentModel> calendarList;
    private CalendarParentModel currentCalendar;
    private EntriesDisplayView entriesDisplayView;

    // Pop-up frames for adding entries
    private AddCalendarFrame addCalendarFrame;
    private DeleteCalendarFrame deleteCalendarFrame;
    private ViewJournal viewJournal;
    private SwitchCalendarFrame switchCalendarFrame;

    private AddEvent addEvent;      // Pop-up for adding events
    private AddTask addTask;        // Pop-up for adding tasks
    private AddMeeting addMeeting;  // Pop-up for adding meetings
    private AddJournal addJournal;  // Pop-up for adding journals

    /**
     * Constructs the AccountPage GUI.
     * Initializes the frame layout and adds all components including labels,
     * buttons, combo boxes, and custom fonts.
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
     * Retrieves the currently selected calendar display mode.
     * 
     * @return "Month" or "Week" depending on user selection
     */
    public String getSelectedCalendarDisplay() {
        return (String) calendarDisplayBox.getSelectedItem();
    }

    /**
     * Retrieves the currently selected entry type.
     * 
     * @return Selected entry type as a string (e.g., "Event", "Task")
     */
    public String getSelectedEntry() {
        return (String) entriesBox.getSelectedItem();
    }

    /**
     * Retrieves the currently selected account name.
     * 
     * @return The name of the selected account
     */
    public String getSelectedAccount() {
        return (String) accountsBox.getSelectedItem();
    }

    /**
     * Returns the AddCalendarFrame instance.
     * 
     * @return The AddCalendarFrame used for creating new calendars
     */
    public AddCalendarFrame getAddCalendarFrame() {
        return addCalendarFrame;
    }

    /**
     * Returns the DeleteCalendarFrame instance.
     * 
     * @return The DeleteCalendarFrame used for removing calendars
     */
    public DeleteCalendarFrame getDeleteCalendarFrame() {
        return deleteCalendarFrame;
    }

    /**
     * Returns the ViewJournal instance.
     * 
     * @return The ViewJournal component for viewing journal entries
     */
    public ViewJournal getViewJournal() {
        return viewJournal;
    }

    /**
     * Retrieves the view responsible for displaying entries.
     * 
     * @return The EntriesDisplayView instance
     */
    public EntriesDisplayView getEntriesDisplayView(){
        return this.entriesDisplayView;
    }

    /**
     * Returns the frame for switching between calendars.
     * 
     * @return The SwitchCalendarFrame component
     */
    public SwitchCalendarFrame getSwitchCalendarFrame() {
        return switchCalendarFrame;
    }

    /**
     * Retrieves the current AccountModel being used.
     * 
     * @return The current AccountModel instance
     */
    public AccountModel getCurrentAccount() {
        return this.currentAccount;
    }

    /**
     * Returns the AddEvent instance.
     * 
     * @return The AddEvent popup used to create events
     */
    public AddEvent getAddEvent() {
        return this.addEvent;
    }

    /**
     * Returns the AddTask instance.
     * 
     * @return The AddTask popup used to create tasks
     */
    public AddTask getAddTask() {
        return this.addTask;
    }

    /**
     * Returns the AddMeeting instance.
     * 
     * @return The AddMeeting popup used to create meetings
     */
    public AddMeeting getAddMeeting() {
        return this.addMeeting;
    }

    /**
     * Returns the AddJournal instance.
     * 
     * @return The AddJournal popup used to create journal entries
     */
    public AddJournal getAddJournal() {
        return this.addJournal;
    }

    /**
     * Retrieves a calendar by its name from the calendar list.
     * 
     * @param calendarName The name of the calendar to search for
     * @return The CalendarParentModel matching the name, or null if not found
     */
    public CalendarParentModel getCalendarByName(String calendarName) {
        CalendarParentModel returnCalendar = null;
        for (CalendarParentModel calendar : calendarList) {
            if (calendar.getName().equals(calendarName)) {
                returnCalendar =  calendar;
            }
        }
        return returnCalendar; // Return null if no account found
    }

    /**
     * Retrieves the current active calendar.
     * 
     * @return The CalendarParentModel currently being displayed
     */
    public CalendarParentModel getCurrentCalendar(){
        return this.currentCalendar;
    }

    /**
     * Refreshes and repaints the calendar and date components.
     */
    public void updateGUI(){
        this.dateLabel.revalidate();
        this.dateLabel.repaint();
        this.calendarPanel.revalidate();
        this.calendarPanel.repaint();
    }

    /**
     * Changes the displayed calendar view based on the given mode.
     * 
     * @param displayMode Either "Month" or "Week"
     */
    public void changeCalendarDisplay(String displayMode) {
        calendarPanel.removeAll(); // Clear existing components
        if (displayMode.equals("Month")) {
            calendarPanel.add(monthlyCalendarView);
        } else {
            calendarPanel.add(weeklyCalendarView);
        }
        updateGUI();
    }

    /**
     * Updates both the monthly and weekly calendar views to reflect the given date.
     * 
     * @param date The new date to set for both calendar views
     */
    public void updateCalendarViewsWithDate(LocalDate date) {
        monthlyCalendarView.updateDate(date);
        weeklyCalendarView.updateDate(date);

        // Refresh the current view
        String currentDisplay = getSelectedCalendarDisplay();
        changeCalendarDisplay(currentDisplay);
    }

    /**
     * Changes the current calendar to the specified one and refreshes the view.
     * 
     * @param newCalendar The calendar to switch to
     */
    public void switchCurrentCalendar (CalendarParentModel newCalendar){
        this.currentCalendar = newCalendar;
        updateGUI();
    }

    /**
     * Sets the AddCalendarFrame instance.
     * 
     * @param addCalendarFrame The frame used for adding calendars
     */
    public void setAddCalendarFrame(AddCalendarFrame addCalendarFrame) {
        this.addCalendarFrame = addCalendarFrame;
    }

    /**
     * Sets the DeleteCalendarFrame instance.
     * 
     * @param deleteCalendarFrame The frame used for deleting calendars
     */
    public void setDeleteCalendarFrame(DeleteCalendarFrame deleteCalendarFrame) {
        this.deleteCalendarFrame = deleteCalendarFrame;
    }

    /**
     * Sets the ViewJournal instance.
     * 
     * @param viewJournal The component used to view journal entries
     */
    public void setViewJournal(ViewJournal viewJournal) {
        this.viewJournal = viewJournal;
    }

    /**
     * Sets the SwitchCalendarFrame instance.
     * 
     * @param switchCalendarFrame The frame used to switch calendars
     */
    public void setSwitchCalendarFrame(SwitchCalendarFrame switchCalendarFrame) {
        this.switchCalendarFrame = switchCalendarFrame;
    }

    /**
     * Sets the EntriesDisplayView instance.
     * 
     * @param entriesDisplayView The view used to display calendar entries
     */
    public void setEntriesDisplayView(EntriesDisplayView entriesDisplayView){
        this.entriesDisplayView = entriesDisplayView;
    }

    /**
     * Sets the AddEvent popup component.
     * 
     * @param addEvent The AddEvent instance used for creating events
     */
    public void setAddEvent(AddEvent addEvent) {
        this.addEvent = addEvent;
    }

    /**
     * Sets the AddTask popup component.
     * 
     * @param addTask The AddTask instance used for creating tasks
     */
    public void setAddTask(AddTask addTask) {
        this.addTask = addTask;
    }

    /**
     * Sets the AddMeeting popup component.
     * 
     * @param addMeeting The AddMeeting instance used for creating meetings
     */
    public void setAddMeeting(AddMeeting addMeeting) {
        this.addMeeting = addMeeting;
    }

    /**
     * Sets the AddJournal popup component.
     * 
     * @param addJournal The AddJournal instance used for creating journals
     */
    public void setAddJournal(AddJournal addJournal) {
        this.addJournal = addJournal;
    }

    /**
     * Updates the date label with a given string and refreshes the UI.
     * 
     * @param dateText The text to display on the date label
     */
    public void updateDateLabel(String dateText) {
        dateLabel.setText(dateText);
        updateGUI();
    }

    /**
     * Registers an ActionListener for changes in calendar display mode.
     * 
     * @param e The ActionListener to be triggered when the mode changes
     */
    public void changeCalendarDisplay(ActionListener e) {
        calendarDisplayBox.addActionListener(e);
    }

    /**
     * Registers an ActionListener for selecting pop-up entry types.
     * 
     * @param e The ActionListener to handle entry selection
     */
    public void selectPopUps(ActionListener e) {
        entriesBox.addActionListener(e);
    }

    /**
     * Registers an ActionListener for account selection changes.
     * 
     * @param e The ActionListener to handle account selection
     */
    public void changeAccountSelection(ActionListener e) {
        accountsBox.addActionListener(e);
    }

    /**
     * Registers an ActionListener for the "Previous" date navigation button.
     * 
     * @param listener The ActionListener to be triggered on button click
     */
    public void setPreviousButtonListener(ActionListener listener) {
        previousButton.addActionListener(listener);
    }

    /**
     * Registers an ActionListener for the "Next" date navigation button.
     * 
     * @param listener The ActionListener to be triggered on button click
     */
    public void setNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    /**
     * Registers an ActionListener for the "Jump to Date" button.
     * 
     * @param listener The ActionListener to be triggered on button click
     */
    public void setJumpDateButtonListener(ActionListener listener) {
        jumpDateButton.addActionListener(listener);
    }

}
