package Controllers.Listeners_Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Controllers.CalendarDateController;
import Views.AccountPage;

public class JumpDateListener implements ActionListener {
    private AccountPage accountPage;
    private CalendarDateController dateController;

    public JumpDateListener(AccountPage accountPage, CalendarDateController dateController) {
        this.accountPage = accountPage;
        this.dateController = dateController;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(
                accountPage,
                "Enter date (MM/yyyy format):",
                "Jump to Date",
                JOptionPane.QUESTION_MESSAGE
            );

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
