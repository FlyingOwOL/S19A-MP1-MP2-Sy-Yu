package Controllers.Listeners_Controllers;

import Controllers.Listeners_Add_Delete_Calendar_PopUps.*;
import Controllers.LoginController;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.*;
import Views.EntriesDisplayView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This listener handles actions related to account-level operations from the AccountPage UI.
 * 
 * Based on the user's selection, it opens the appropriate pop-up window for:
 * viewing entries, switching calendars, adding or deleting calendars, viewing journals,
 * or signing out.
 */
public class AccountSelectionListener implements ActionListener {
    private AccountPage accountPage;    // Reference to the current AccountPage

    /**
     * Constructs a listener bound to a specific AccountPage.
     * 
     * @param accountPage the account page where this listener will be attached
     */
    public AccountSelectionListener(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    /**
     * Responds to action events triggered by the account option selection.
     * Based on the selected action, opens and configures the corresponding pop-up view.
     *
     * @param e the action event triggered by a UI interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String optionSelected;
        try {
            optionSelected = accountPage.getSelectedAccount();
            JFrame popUp = null;

            switch (optionSelected) {
                case "View Entries":
                    popUp = new EntriesDisplayView(this.accountPage.getCurrentCalendar());
                    accountPage.setEntriesDisplayView((EntriesDisplayView) popUp);

                    // Attach edit listener for the entry list
                    accountPage.getEntriesDisplayView()
                               .getEditButton()
                               .addActionListener(new EditEntryListener(accountPage, (EntriesDisplayView) popUp));
                    break;

                case "Switch":
                    popUp = new SwitchCalendarFrame();
                    switchCalendar((SwitchCalendarFrame) popUp, accountPage);
                    break;

                case "Add Calendar":
                    popUp = new AddCalendarFrame();
                    addNewCalendar((AddCalendarFrame) popUp, accountPage);
                    break;

                case "Delete Calendar":
                    popUp = new DeleteCalendarFrame();
                    deleteCalendar((DeleteCalendarFrame) popUp, accountPage);
                    break;

                case "View Journal":
                    popUp = new ViewJournal();
                    viewJournal((ViewJournal) popUp, accountPage);
                    break;

                case "Sign out":
                    accountPage.dispose();  // Close the current UI
                    new LoginController(); // Return to login screen
                    return; // Prevent showing a popup
            }

            // Display the selected pop-up if one was set
            if (popUp != null) {
                popUp.setLocationRelativeTo(accountPage);
                popUp.setVisible(true);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(accountPage,
                "An error occurred in account selection: \n\n" + ex.getMessage(),
                "Edit Failed",
                JOptionPane.ERROR_MESSAGE);

            ex.printStackTrace(); // Helpful for debugging
        }
    }

    /**
     * Attaches and configures the listener for the Add Calendar pop-up.
     *
     * @param popUp       the AddCalendarFrame pop-up instance
     * @param accountPage the current account page context
     */
    private void addNewCalendar(AddCalendarFrame popUp, AccountPage accountPage) {
        accountPage.setAddCalendarFrame(popUp);
        AddCalendarListener listener = new AddCalendarListener(popUp, accountPage);
        popUp.setButtonActionListener(listener);
    }

    /**
     * Attaches and configures the listener for the Switch Calendar pop-up.
     *
     * @param popUp       the SwitchCalendarFrame pop-up instance
     * @param accountPage the current account page context
     */
    private void switchCalendar(SwitchCalendarFrame popUp, AccountPage accountPage) {
        accountPage.setSwitchCalendarFrame(popUp);
        new SwitchCalendarListener(popUp, accountPage);
    }

    /**
     * Attaches and configures the listener for the Delete Calendar pop-up.
     *
     * @param popUp       the DeleteCalendarFrame pop-up instance
     * @param accountPage the current account page context
     */
    private void deleteCalendar(DeleteCalendarFrame popUp, AccountPage accountPage) {
        accountPage.setDeleteCalendarFrame(popUp);
        new DeleteCalendarListener(popUp, accountPage);
    }

    /**
     * Attaches and configures the listener for the View Journal pop-up.
     *
     * @param popUp       the ViewJournal pop-up instance
     * @param accountPage the current account page context
     */
    private void viewJournal(ViewJournal popUp, AccountPage accountPage) {
        accountPage.setViewJournal(popUp);
        new ViewJournalListener(popUp, accountPage);
    }
}
