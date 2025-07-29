package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import Controllers.MainController;
import Models.Account.AccountModel;
import Models.Calendar.*;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.AddCalendarFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener for the Add Calendar pop-up.
 * Handles both creation of new calendars and importing existing public calendars.
 */
public class AddCalendarListener implements ActionListener {
    private AddCalendarFrame addCalendarFrame;  // The pop-up frame for adding a calendar
    private AccountPage accountPage;            // The main account page with calendar access

    /**
     * Constructs an AddCalendarListener and sets up button and combo box listeners.
     *
     * @param addCalendarFrame The frame for creating or importing a calendar
     * @param accountPage      The current user's account page
     */
    public AddCalendarListener(AddCalendarFrame addCalendarFrame, AccountPage accountPage) {
        this.addCalendarFrame = addCalendarFrame;
        this.accountPage = accountPage;

        setupRadioButtonListeners();
        setupCalendarTypeListener();

        System.out.println("AddCalendarListener created and connected!");
    }

    /**
     * Sets up listeners for radio buttons to toggle between create and import modes.
     */
    private void setupRadioButtonListeners() {
        addCalendarFrame.setCreationTypeListener(e -> addCalendarFrame.createMode());

        // Sets up listener for the import radio button
        addCalendarFrame.setImportCalendarListener(e -> {
            addCalendarFrame.importMode();
            loadPublicCalendars();
        });
    }

    /**
     * Sets up a listener to switch form elements based on the selected calendar type.
     */
    private void setupCalendarTypeListener() {
        // Listener for the calendar type combo box
        addCalendarFrame.setCalendarTypeBoxListener(e -> {
            String selectedType = (String) addCalendarFrame.getCalendarTypeBox().getSelectedItem();
            if ("Family".equals(selectedType)) {
                addCalendarFrame.familyCalendarMode();
            } else {
                addCalendarFrame.anyCalendarMode();
            }
        });
    }

    /**
     * Loads public calendars into the combo box for importing.
     */
    private void loadPublicCalendars() {
        // Remove the items in the combo box
        addCalendarFrame.getImportedCalendarBox().removeAllItems();

        // Then add the public calendars to the combo box
        for (CalendarParentModel calendar : MainController.publicCalendars) {
            addCalendarFrame.getImportedCalendarBox().addItem(calendar.getName());
        }
    }

    /**
     * Main action handler triggered when the "Add" button is clicked.
     *
     * @param e The ActionEvent from the GUI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the event source is the Add button
        if (e.getSource() == addCalendarFrame.getAddButton()) {
            boolean createSelected = addCalendarFrame.isCreationTypeSelected();
            boolean importSelected = addCalendarFrame.isImportTypeSelected();

            // If neither option is selected, show a warning
            if (createSelected) {
                createNewCalendar();
            } 
            // If import is selected, handle importing an existing calendar
            else if (importSelected) {
                importExistingCalendar();
            }
            // If neither option is selected, show a warning 
            else {
                JOptionPane.showMessageDialog(addCalendarFrame,
                        "Please select either 'Create New Calendar' or 'Import Calendar'",
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Handles logic for creating a new calendar and adding it to the user's account.
     */
    private void createNewCalendar() {
        // Retrieve user input from the form
        String calendarName = addCalendarFrame.getCalendarNameField().getText().trim();

        // Get the selected calendar type from the combo box
        String selectedType = (String) addCalendarFrame.getCalendarTypeBox().getSelectedItem();

        boolean shouldContinue = true;  // Flag to control flow based on validation

        // Validate the calendar name
        if (calendarName.isEmpty()) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "Please enter a calendar name",
                    "Name Required", JOptionPane.WARNING_MESSAGE);
            shouldContinue = false;
        }

        // Check if a calendar with the same name already exists
        if (shouldContinue && accountPage.getCalendarByName(calendarName) != null) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "A calendar with this name already exists",
                    "Duplicate Name", JOptionPane.ERROR_MESSAGE);
            shouldContinue = false;
        }

        // If the selected type is Family, ensure an access code is provided
        if (shouldContinue) {
            CalendarParentModel newCalendar = null;
            AccountModel currentAccount = accountPage.getCurrentAccount();

            if ("Personal".equals(selectedType)) {
                newCalendar = new Personal(calendarName, currentAccount);
            }

            if ("Normal".equals(selectedType)) {
                newCalendar = new Normal(calendarName, currentAccount);
                MainController.publicCalendars.add(newCalendar);
            }

            if ("Family".equals(selectedType)) {
                String accessCode = addCalendarFrame.getCalendarPasswordField().getText().trim();
                if (accessCode.isEmpty()) {
                    JOptionPane.showMessageDialog(addCalendarFrame,
                            "Family calendars require an access code",
                            "Access Code Required", JOptionPane.WARNING_MESSAGE);
                    shouldContinue = false;
                } else {
                    newCalendar = new Family(calendarName, currentAccount, accessCode);
                    MainController.publicCalendars.add(newCalendar);
                }
            }

            // If all validations pass, add the new calendar to the account
            if (shouldContinue && newCalendar != null) {
                currentAccount.getCalendars().add(newCalendar);
                JOptionPane.showMessageDialog(addCalendarFrame,
                        "Calendar created successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                addCalendarFrame.dispose();
                accountPage.updateGUI();
            }
        }
    }

    /**
     * Handles logic for importing a shared public calendar.
     */
    private void importExistingCalendar() {
        // Get the selected calendar name from the combo box
        String selectedCalendarName = (String) addCalendarFrame.getImportedCalendarBox().getSelectedItem();

        // Initialize variables for validation
        boolean shouldContinue = true;
        CalendarParentModel calendarToImport = null;

        // Validate that a calendar is selected for import
        if (selectedCalendarName == null) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "Please select a calendar to import",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            shouldContinue = false;
        }

        // If a calendar is selected, find it in the public calendars list
        if (shouldContinue) {
            for (CalendarParentModel calendar : MainController.publicCalendars) {
                if (calendar.getName().equals(selectedCalendarName)) {
                    calendarToImport = calendar;
                }
            }

        // If the calendar is not found, show an error
            if (calendarToImport == null) {
                JOptionPane.showMessageDialog(addCalendarFrame,
                        "Selected calendar not found",
                        "Error", JOptionPane.ERROR_MESSAGE);
                shouldContinue = false;
            }
        }

        // If the calendar is a Family type, prompt for access code
        if (shouldContinue && calendarToImport instanceof Family) {
            Family familyCalendar = (Family) calendarToImport;
            String enteredCode = JOptionPane.showInputDialog(addCalendarFrame,
                    "Enter access code for family calendar:",
                    "Access Code Required", JOptionPane.QUESTION_MESSAGE);

            if (enteredCode == null || !enteredCode.equals(familyCalendar.getCode())) {
                JOptionPane.showMessageDialog(addCalendarFrame,
                        "Incorrect access code",
                        "Access Denied", JOptionPane.ERROR_MESSAGE);
                shouldContinue = false;
            }
        }

        // If all checks pass, add the calendar to the user's account
        AccountModel currentAccount = accountPage.getCurrentAccount();

        // Check if the user already has access to this calendar
        if (shouldContinue && currentAccount.getCalendars().contains(calendarToImport)) {
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "You already have access to this calendar",
                    "Already Added", JOptionPane.INFORMATION_MESSAGE);
            shouldContinue = false;
        }

        // If the user does not have access, add the calendar to their account
        if (shouldContinue) {
            currentAccount.getCalendars().add(calendarToImport);
            JOptionPane.showMessageDialog(addCalendarFrame,
                    "Calendar imported successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            addCalendarFrame.dispose();
            accountPage.updateGUI();
        }
    }
}