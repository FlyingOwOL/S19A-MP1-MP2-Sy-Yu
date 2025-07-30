package Controllers.Listeners_Controllers;

import Controllers.CalendarDateController;
import Views.AccountPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener for the "Jump to Date" functionality.
 * 
 * Prompts the user to enter a date in MM/yyyy format and navigates
 * the calendar to the specified month and year.
 */
public class JumpDateListener implements ActionListener {
    private AccountPage accountPage;                // The account page view instance 
    private CalendarDateController dateController;  // Controller responsible for managing calendar date navigation 

    /**
     * Constructs a JumpDateListener with references to the AccountPage
     * and CalendarDateController.
     *
     * @param accountPage the account page view where the calendar is displayed
     * @param dateController the controller managing calendar date logic
     */
    public JumpDateListener(AccountPage accountPage, CalendarDateController dateController) {
        this.accountPage = accountPage;
        this.dateController = dateController;
    }

    /**
     * Handles the action event triggered when the user clicks the "Jump" button.
     * Prompts for a date in MM/yyyy format, validates the input, and calls
     * the controller to jump to that date if valid.
     *
     * @param e the action event triggered by the user
     */
    public void actionPerformed(ActionEvent e) {
        try {
            // Prompt the user for a date in MM/yyyy format
            String input = JOptionPane.showInputDialog(
                accountPage,
                "Enter date (MM/yyyy format):",
                "Jump to Date",
                JOptionPane.QUESTION_MESSAGE
            );

            // Check if input is not null and not empty
            if (input != null && !input.trim().isEmpty()) {
                String[] parts = input.trim().split("/");
                if (parts.length == 2) {
                    int month = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);

                    if (month < 1 || month > 12) {
                        JOptionPane.showMessageDialog(
                            accountPage,
                            "Invalid month. Please enter a month between 1 and 12.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    dateController.jumpToDate(month, year);
                } else {
                    JOptionPane.showMessageDialog(
                        accountPage,
                        "Invalid format. Please use MM/yyyy format (e.g., 12/2024).",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                accountPage,
                "Invalid date format. Please use MM/yyyy format (e.g., 12/2024).",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            System.out.println("Error jumping to date: " + ex.getMessage());
            JOptionPane.showMessageDialog(
                accountPage,
                "An error occurred while jumping to the date.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
