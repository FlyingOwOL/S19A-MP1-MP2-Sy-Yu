package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers.MainController;
import Models.Account.AccountModel;
import Models.Calendar.CalendarParentModel;
import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.DeleteCalendarFrame;

import javax.swing.JOptionPane;

public class DeleteCalendarListener implements ActionListener {
    private DeleteCalendarFrame deleteCalendarFrame;
    private AccountPage accountPage;

    public DeleteCalendarListener(DeleteCalendarFrame deleteCalendarFrame, AccountPage accountPage) {
        this.deleteCalendarFrame = deleteCalendarFrame;
        this.accountPage = accountPage;

        // Load calendars that can be deleted
        loadDeletableCalendars();

        // Setup delete button listener
        deleteCalendarFrame.getDeleteButton().addActionListener(this);
    }

    private void loadDeletableCalendars() {
        // Clear existing items
        deleteCalendarFrame.getCalendarComboBox().removeAllItems();

        AccountModel currentAccount = accountPage.getCurrentAccount();

        // Add calendars that can be deleted (owned by current user, not the default personal calendar)
        for (CalendarParentModel calendar : currentAccount.getCalendars()) {
            // Can delete if:
            // 1. User is the owner
            // 2. It's not the default personal calendar (same name as username)
            if (calendar.getOwner().equals(currentAccount) &&
                    !calendar.getName().equals(currentAccount.getName())) {
                deleteCalendarFrame.getCalendarComboBox().addItem(calendar.getName());
            }
        }

        // If no calendars can be deleted, show message
        if (deleteCalendarFrame.getCalendarComboBox().getItemCount() == 0) {
            deleteCalendarFrame.getCalendarComboBox().addItem("No calendars available for deletion");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteCalendarFrame.getDeleteButton()) {
            String selectedCalendarName = (String) deleteCalendarFrame.getCalendarComboBox().getSelectedItem();

            if (selectedCalendarName == null || selectedCalendarName.equals("No calendars available for deletion")) {
                JOptionPane.showMessageDialog(deleteCalendarFrame,
                        "No calendar selected or available for deletion",
                        "Cannot Delete", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(deleteCalendarFrame,
                    "Are you sure you want to delete '" + selectedCalendarName + "'?\n" +
                            "This will permanently delete all entries in this calendar and\n" +
                            "remove it from all users who have access to it.",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Find the calendar to delete
            AccountModel currentAccount = accountPage.getCurrentAccount();
            CalendarParentModel calendarToDelete = null;

            for (CalendarParentModel calendar : currentAccount.getCalendars()) {
                if (calendar.getName().equals(selectedCalendarName) &&
                        calendar.getOwner().equals(currentAccount)) {
                    calendarToDelete = calendar;
                    break;
                }
            }

            if (calendarToDelete == null) {
                JOptionPane.showMessageDialog(deleteCalendarFrame,
                        "Calendar not found or you don't have permission to delete it",
                        "Cannot Delete", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if it's the currently active calendar
            boolean wasCurrentCalendar = calendarToDelete.equals(accountPage.getCurrentCalendar());

            // Remove from public calendars if it exists there
            MainController.publicCalendars.remove(calendarToDelete);

            // Remove from all accounts that have access to this calendar
            for (AccountModel account : MainController.accounts) {
                account.getCalendars().remove(calendarToDelete);
            }

            // If this was the current calendar, switch to the default personal calendar
            if (wasCurrentCalendar) {
                CalendarParentModel defaultCalendar = accountPage.getCalendarByName(currentAccount.getName());
                if (defaultCalendar != null) {
                    accountPage.switchCurrentCalendar(defaultCalendar);
                }
            }

            JOptionPane.showMessageDialog(deleteCalendarFrame,
                    "Calendar '" + selectedCalendarName + "' has been deleted successfully",
                    "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);

            deleteCalendarFrame.dispose();
            accountPage.updateGUI(); // Refresh the account page
        }
    }
}