package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers.MainController;
import Models.Account.AccountModel;
import Models.Calendar.*;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.AddCalendarFrame;

import javax.swing.JOptionPane;

/**
 * This class handles the logic for adding a new calendar or importing an existing one.
 * It listens for actions on the AddCalendarFrame and performs the necessary operations
 * to create or import calendars based on user input.
 */
public class AddCalendarListener implements ActionListener {
    private AddCalendarFrame addCalendarFrame;  // The frame where users can add or import calendars
    private AccountPage accountPage;            // The account page where the calendar will be displayed

    /**
     * Constructs an AddCalendarListener with the given AddCalendarFrame and AccountPage.
     *
     * @param addCalendarFrame The frame for adding or importing calendars.
     * @param accountPage The account page where the calendar will be displayed.
     */
    public AddCalendarListener(AddCalendarFrame addCalendarFrame, AccountPage accountPage) {
        this.addCalendarFrame = addCalendarFrame;
        this.accountPage = accountPage;

        // Setup radio button listeners
        setupRadioButtonListeners();
        setupCalendarTypeListener();

        System.out.println("AddCalendarListener created and connected!"); // Debug message
    }

    /**
     * Sets up listeners for the radio buttons in the AddCalendarFrame.
     * This allows users to choose between creating a new calendar or importing an existing one.
     */
    private void setupRadioButtonListeners() {
        addCalendarFrame.setCreationTypeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCalendarFrame.createMode();
            }
        });

        
        addCalendarFrame.setImportCalendarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCalendarFrame.importMode();
                loadPublicCalendars();
            }
        });
    }

    /**
     * Sets up a listener for the calendar type selection box.
     * This allows users to choose between different types of calendars (e.g., Personal, Normal, Family).
     */
    private void setupCalendarTypeListener() {
        addCalendarFrame.setCalendarTypeBoxListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) addCalendarFrame.getCalendarTypeBox().getSelectedItem();
                if ("Family".equals(selectedType)) {
                    addCalendarFrame.familyCalendarMode();
                } else {
                    addCalendarFrame.anyCalendarMode();
                }
            }
        });
    }

    /**
     * Loads the public calendars into the dropdown for importing.
     * This method populates the imported calendar box with available public calendars.
     */
    private void loadPublicCalendars() {
        // Update the imported calendar dropdown with available public calendars
        addCalendarFrame.getImportedCalendarBox().removeAllItems();

        // Loop through public calendars and add them to the dropdown
        for (CalendarParentModel calendar : MainController.publicCalendars) {
            addCalendarFrame.getImportedCalendarBox().addItem(calendar.getName());
        }
    }

    /**
     * Handles the action performed when the user clicks the Add button.
     * Depending on the selected radio button, it either creates a new calendar or imports an existing one.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("AddCalendarListener.actionPerformed() called!"); // Debug

        if (e.getSource() == addCalendarFrame.getAddButton()) {
            System.out.println("Add button clicked!"); // Debug

            if (addCalendarFrame.isCreationTypeSelected()) {
                System.out.println("Creating new calendar..."); // Debug
                createNewCalendar();
            } else if (addCalendarFrame.isImportTypeSelected()) {
                System.out.println("Importing existing calendar..."); // Debug
                importExistingCalendar();
            } else {
                System.out.println("No radio button selected!"); // Debug
                JOptionPane.showMessageDialog(addCalendarFrame,
                        "Please select either 'Create New Calendar' or 'Import Calendar'",
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            System.out.println("Event source is not the Add button!"); // Debug
        }
    }

    /**
     * Creates a new calendar based on user input from the AddCalendarFrame.
     * Validates the input and adds the new calendar to the user's account.
     */
    private void createNewCalendar() {
        String calendarName = addCalendarFrame.getCalendarNameField().getText().trim();
        String selectedType = (String) addCalendarFrame.getCalendarTypeBox().getSelectedItem();

        boolean stop = false;
        if (calendarName.isEmpty()) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "Please enter a calendar name",
                    "Name Required", JOptionPane.WARNING_MESSAGE);
            stop = true;
        }

        // Check if calendar name already exists for this account
        if (accountPage.getCalendarByName(calendarName) != null && !stop) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "A calendar with this name already exists",
                    "Duplicate Name", JOptionPane.ERROR_MESSAGE);
            stop = true;
        }

        if (!stop){
            CalendarParentModel newCalendar = null;
            AccountModel currentAccount = accountPage.getCurrentAccount();

            switch (selectedType) {
                case "Personal":
                    newCalendar = new Personal(calendarName, currentAccount);
                    break;
                case "Normal":
                    newCalendar = new Normal(calendarName, currentAccount);
                    MainController.publicCalendars.add(newCalendar);
                    break;
                case "Family":
                    String accessCode = addCalendarFrame.getCalendarPasswordField().getText().trim();
                    if (accessCode.isEmpty()) {
                        JOptionPane.showMessageDialog(addCalendarFrame,
                                "Family calendars require an access code",
                                "Access Code Required", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    newCalendar = new Family(calendarName, currentAccount, accessCode);
                    MainController.publicCalendars.add(newCalendar);
                    break;
            }

            if (newCalendar != null) {
                currentAccount.getCalendars().add(newCalendar);
                JOptionPane.showMessageDialog(addCalendarFrame,
                        "Calendar created successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                addCalendarFrame.dispose();
                accountPage.updateGUI(); // Refresh the account page
            }
        }
        
    }

    /**
     * Imports an existing calendar based on user selection from the dropdown.
     * Validates the selection and checks for access codes if necessary.
     */
    private void importExistingCalendar() {
        String selectedCalendarName = (String) addCalendarFrame.getImportedCalendarBox().getSelectedItem();
        boolean stop = false;
        if (selectedCalendarName == null && !stop) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "Please select a calendar to import",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            stop = true;
        }

        // Find the calendar in public calendars
        CalendarParentModel calendarToImport = null;
        for (CalendarParentModel calendar : MainController.publicCalendars) {
            if (calendar.getName().equals(selectedCalendarName) && !stop) {
                calendarToImport = calendar;
                stop = true;
            }
        }
        stop = false; //back to false after getting out of the for loop

        if (calendarToImport == null && !stop) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "Selected calendar not found",
                    "Error", JOptionPane.ERROR_MESSAGE);
            stop = true;
        }

        // Check if it's a Family calendar requiring access code
        if (calendarToImport instanceof Family) {
            Family familyCalendar = (Family) calendarToImport;
            String enteredCode = JOptionPane.showInputDialog(addCalendarFrame,
                    "Enter access code for family calendar:",
                    "Access Code Required", JOptionPane.QUESTION_MESSAGE);

            if (enteredCode == null || !enteredCode.equals(familyCalendar.getCode()) && !stop) {
                JOptionPane.showMessageDialog(addCalendarFrame,
                        "Incorrect access code",
                        "Access Denied", JOptionPane.ERROR_MESSAGE);
                stop = true;
            }
        }

        // Check if user already has this calendar
        AccountModel currentAccount = accountPage.getCurrentAccount();
        if (currentAccount.getCalendars().contains(calendarToImport) && !stop) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "You already have access to this calendar",
                    "Already Added", JOptionPane.INFORMATION_MESSAGE);
            stop = true;
        }
        
        if (!stop){
            // Add calendar to user's list
            currentAccount.getCalendars().add(calendarToImport);
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "Calendar imported successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            addCalendarFrame.dispose();
            accountPage.updateGUI(); // Refresh the account page            
        }
    }
}