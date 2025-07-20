package Controllers;

import Views.AccountPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JOptionPane;

public class CalendarDateController {
    private AccountPage accountPage;
    private LocalDateTime currentDate;
    private DateTimeFormatter displayFormatter;
    
    public CalendarDateController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.currentDate = LocalDateTime.now();
        this.displayFormatter = DateTimeFormatter.ofPattern("MMM - yyyy");
        
        // Set up action listeners for navigation buttons
        this.accountPage.setPreviousButtonListener(new PreviousDateListener());
        this.accountPage.setNextButtonListener(new NextDateListener());
        this.accountPage.setJumpDateButtonListener(new JumpDateListener());
        
        // Update the display with current date
        updateDateDisplay();
    }
    
    /**
     * Updates the date label on the account page with the current date
     */
    private void updateDateDisplay() {
        String displayText;
        boolean isWeekView = "Week".equals(accountPage.getSelectedCalendarDisplay());

        if (isWeekView) {
            // Get start (Monday) and end (Sunday) of the current week
            LocalDateTime startOfWeek = currentDate.with(java.time.DayOfWeek.MONDAY);
            LocalDateTime endOfWeek = currentDate.with(java.time.DayOfWeek.SUNDAY);

            // If months are the same
            if (startOfWeek.getMonth() == endOfWeek.getMonth()) {
                displayText = String.format("%s %d",
                    startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    endOfWeek.getYear()
                );
            } else {
                // Different months
                displayText = String.format("%s - %s %d",
                    startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    endOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    endOfWeek.getYear()
                );
            }
        } else {
            // Month view
            displayText = currentDate.format(displayFormatter); // MMM - yyyy
        }

        accountPage.updateDateLabel(displayText);
    }

    
    /**
     * Navigates to the previous month
     */
    private void navigateToPreviousMonth() {
        try {
            currentDate = currentDate.minusMonths(1);
            updateDateDisplay();
            System.out.println("Navigated to previous month: " + currentDate.format(displayFormatter));
        } catch (Exception e) {
            System.out.println("Error navigating to previous month: " + e.getMessage());
        }
    }
    
    /**
     * Navigates to the next month
     */
    private void navigateToNextMonth() {
        try {
            currentDate = currentDate.plusMonths(1);
            updateDateDisplay();
            System.out.println("Navigated to next month: " + currentDate.format(displayFormatter));
        } catch (Exception e) {
            System.out.println("Error navigating to next month: " + e.getMessage());
        }
    }
    
    /**
     * Navigates to the previous week (for weekly view)
     */
    private void navigateToPreviousWeek() {
        try {
            currentDate = currentDate.minusWeeks(1);
            updateDateDisplay();
            System.out.println("Navigated to previous week: " + currentDate.format(displayFormatter));
        } catch (Exception e) {
            System.out.println("Error navigating to previous week: " + e.getMessage());
        }
    }
    
    /**
     * Navigates to the next week (for weekly view)
     */
    private void navigateToNextWeek() {
        try {
            currentDate = currentDate.plusWeeks(1);
            updateDateDisplay();
            System.out.println("Navigated to next week: " + currentDate.format(displayFormatter));
        } catch (Exception e) {
            System.out.println("Error navigating to next week: " + e.getMessage());
        }
    }
    
    /**
     * Handles jumping to a specific date
     */
    private void handleJumpToDate() {
        try {
            String input = JOptionPane.showInputDialog(
                accountPage, 
                "Enter date (MM/yyyy format):", 
                "Jump to Date", 
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (input != null && !input.trim().isEmpty()) {
                // Parse the input date
                String[] parts = input.trim().split("/");
                if (parts.length == 2) {
                    int month = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    
                    // Validate month
                    if (month < 1 || month > 12) {
                        JOptionPane.showMessageDialog(
                            accountPage, 
                            "Invalid month. Please enter a month between 1 and 12.", 
                            "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    
                    // Create new date with current day (or 1st if current day doesn't exist in that month)
                    LocalDateTime newDate = LocalDateTime.of(year, month, 1, 
                        currentDate.getHour(), currentDate.getMinute(), currentDate.getSecond());
                    
                    // Try to set the same day as current date, but handle cases where it doesn't exist
                    try {
                        newDate = newDate.withDayOfMonth(Math.min(currentDate.getDayOfMonth(), 
                            newDate.toLocalDate().lengthOfMonth()));
                    } catch (Exception e) {
                        // If day doesn't exist in the new month, use the last day of that month
                        newDate = newDate.withDayOfMonth(newDate.toLocalDate().lengthOfMonth());
                    }
                    
                    currentDate = newDate;
                    updateDateDisplay();
                    
                    System.out.println("Jumped to date: " + currentDate.format(displayFormatter));
                } else {
                    JOptionPane.showMessageDialog(
                        accountPage, 
                        "Invalid format. Please use MM/yyyy format (e.g., 12/2024).", 
                        "Invalid Input", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                accountPage, 
                "Invalid date format. Please use MM/yyyy format (e.g., 12/2024).", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            System.out.println("Error jumping to date: " + e.getMessage());
            JOptionPane.showMessageDialog(
                accountPage, 
                "An error occurred while jumping to the date.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Getter methods for accessing current date information
    public LocalDateTime getCurrentDate() {
        return currentDate;
    }
    
    public int getCurrentYear() {
        return currentDate.getYear();
    }
    
    public int getCurrentMonth() {
        return currentDate.getMonthValue();
    }
    
    public int getCurrentDay() {
        return currentDate.getDayOfMonth();
    }
    
    public String getCurrentMonthName() {
        return currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
    
    public String getFormattedCurrentDate() {
        return currentDate.format(displayFormatter);
    }
    
    // Action listener classes
    class PreviousDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Check current display mode to determine navigation type
            String displayMode = accountPage.getSelectedCalendarDisplay();
            if ("Week".equals(displayMode)) {
                navigateToPreviousWeek();
            } else {
                navigateToPreviousMonth();
            }
        }
    }
    
    class NextDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Check current display mode to determine navigation type
            String displayMode = accountPage.getSelectedCalendarDisplay();
            if ("Week".equals(displayMode)) {
                navigateToNextWeek();
            } else {
                navigateToNextMonth();
            }
        }
    }
    
    class JumpDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            handleJumpToDate();
        }
    }
}