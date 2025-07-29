package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import Controllers.MainController;
import Models.Account.AccountModel;
import Models.Calendar.CalendarParentModel;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.DeleteCalendarFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * This class handles deleting calendars from the account page.
 * It shows calendars owned by the user (excluding default personal calendar)
 * and deletes the selected one after confirmation.
 */
public class DeleteCalendarListener implements ActionListener {
    private DeleteCalendarFrame deleteCalendarFrame;
    private AccountPage accountPage;

    /**
     * Sets up the delete calendar listener.
     *
     * @param deleteCalendarFrame The pop-up window for deletion
     * @param accountPage         The main user interface page
     */
    public DeleteCalendarListener(DeleteCalendarFrame deleteCalendarFrame, AccountPage accountPage) {
        this.deleteCalendarFrame = deleteCalendarFrame;
        this.accountPage = accountPage;

        loadDeletableCalendars(); // Populate combo box
        deleteCalendarFrame.getDeleteButton().addActionListener(this); // Attach listener
    }

    /**
     * Loads calendars that the user can delete (not default).
     */
    private void loadDeletableCalendars() {
        // Delete the items in the combo box 
        deleteCalendarFrame.getCalendarComboBox().removeAllItems();

        // Access the current account and its calendars
        AccountModel currentAccount = accountPage.getCurrentAccount();

        // We loop through the calendars to add them to the deletion combo box
        for (CalendarParentModel calendar : currentAccount.getCalendars()) {
            boolean isOwned = calendar.getOwner().equals(currentAccount);
            boolean isNotDefault = !calendar.getName().equals(currentAccount.getName());

            // Check if it's default
            if (isOwned && isNotDefault) {
                deleteCalendarFrame.getCalendarComboBox().addItem(calendar.getName());
            }
        }

        // If no calendars are available
        if (deleteCalendarFrame.getCalendarComboBox().getItemCount() == 0) {
            deleteCalendarFrame.getCalendarComboBox().addItem("No calendars available for deletion");
        }
    }

    /**
     * Handles deletion of the selected calendar when the delete button is clicked.
     *
     * @param e The event triggered by clicking the delete button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    // Check if the event source is the delete button
    boolean validSource = (e.getSource() == deleteCalendarFrame.getDeleteButton());

    if (validSource) {
        // Get the name of the selected calendar from the combo box
        String selectedCalendarName = (String) deleteCalendarFrame.getCalendarComboBox().getSelectedItem();

        // Check if no calendar is selected or the combo box indicates that no calendars are available
        boolean noCalendarSelected = (selectedCalendarName == null ||
                selectedCalendarName.equals("No calendars available for deletion"));

        if (!noCalendarSelected) {
            // Show a confirmation dialog before deleting the calendar
            int confirm = JOptionPane.showConfirmDialog(deleteCalendarFrame,
                    "Are you sure you want to delete '" + selectedCalendarName + "'?\n" +
                    "This will permanently delete all entries in this calendar and\n" +
                    "remove it from all users who have access to it.",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                AccountModel currentAccount = accountPage.getCurrentAccount();
                CalendarParentModel calendarToDelete = null;

                // Loop through the current user's calendars to find the one that matches the selected name
                for (CalendarParentModel calendar : currentAccount.getCalendars()) {
                    boolean nameMatches = calendar.getName().equals(selectedCalendarName);
                    boolean isOwned = calendar.getOwner().equals(currentAccount);

                    // Set the calendar to delete only if it matches the name and is owned by the current user
                    if (calendarToDelete == null && nameMatches && isOwned) {
                        calendarToDelete = calendar;
                    }
                }

                if (calendarToDelete != null) {
                    // Check if the calendar being deleted is currently selected in the GUI
                    boolean wasCurrentCalendar = calendarToDelete.equals(accountPage.getCurrentCalendar());

                    // Remove the calendar from the global list of public calendars
                    MainController.publicCalendars.remove(calendarToDelete);

                    // Loop through all accounts and remove the calendar from each account's list
                    for (AccountModel account : MainController.accounts) {
                        account.getCalendars().remove(calendarToDelete);
                    }

                    // If the deleted calendar was currently active, switch to the default personal calendar
                    if (wasCurrentCalendar) {
                        CalendarParentModel defaultCalendar = accountPage.getCalendarByName(currentAccount.getName());
                        if (defaultCalendar != null) {
                            accountPage.switchCurrentCalendar(defaultCalendar);
                        }
                    }

                    // Inform the user that the calendar was successfully deleted
                    JOptionPane.showMessageDialog(deleteCalendarFrame,
                            "Calendar '" + selectedCalendarName + "' has been deleted successfully",
                            "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);

                    // Close the deletion pop-up and refresh the GUI
                    deleteCalendarFrame.dispose();
                    accountPage.updateGUI();
                } else {
                    // The calendar wasn't found or the user doesn’t own it
                    JOptionPane.showMessageDialog(deleteCalendarFrame,
                            "Calendar not found or you don't have permission to delete it",
                            "Cannot Delete", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // No calendar was selected in the combo box
            JOptionPane.showMessageDialog(deleteCalendarFrame,
                    "No calendar selected or available for deletion",
                    "Cannot Delete", JOptionPane.WARNING_MESSAGE);
        }
    }
}
}