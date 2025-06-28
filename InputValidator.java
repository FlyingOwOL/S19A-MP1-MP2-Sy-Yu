import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * This class provides helper methods to validate user input for dates and times.
 */
public class InputValidator {

    /**
     * This method prompts the user to enter a valid date in the format YYYY-MM-DD.
     * If the input is invalid, it will keep asking until a correct date is entered.
     * @param userInput This is the Scanner used to read user input.
     * @return The valid LocalDate entered by the user.
     */
    public static LocalDate readValidDate(Scanner userInput) {
        LocalDate date = null;
        boolean valid = false;

        while (!valid) {
            System.out.println("Enter entry date (YYYY-MM-DD): ");
            String dateInput = userInput.nextLine();
            try { // Try to parse the date input
                date = LocalDate.parse(dateInput); // Parse the date string
                // Check if the date is valid (e.g., not February 30th)
                valid = true;
            } catch (DateTimeParseException e) { 
                //If the input is not in the correct format, handle the mistake and let the user enter it again.
                System.out.println("Invalid date format or non-existent date. Please try again.");
            }
        }

        return date;
    }

    /**
     * This method prompts the user to enter a valid time in the format HH:MM (24-hour format).
     * If the input is invalid, it will keep asking until a correct time is entered.
     * @param userInput This is the Scanner used to read user input.
     * @param prompt The specific prompt message to show to the user.
     * @return The valid LocalTime entered by the user.
     */
    public static LocalTime readValidTime(Scanner userInput, String prompt) {
        LocalTime time = null;
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            String timeInput = userInput.nextLine();
            try {
                time = LocalTime.parse(timeInput); // Parse the time string
                // Check if the time is valid (e.g., not 25:00)
                valid = true;
            } catch (DateTimeParseException e) { 
                // If the input is not in the correct format, handle the mistake and let the user enter it again.
                System.out.println("Invalid time format. Please enter time as HH:MM (24-hour format).");
            }
        }

        return time;
    }

    /**
     * This method ensures that the end time is after the start time.
     * It keeps asking for a valid end time until the condition is satisfied.
     * @param userInput This is the Scanner used to read user input.
     * @param startTime The start time to compare against.
     * @return A valid LocalTime that is after the start time.
     */
    public static LocalTime ensureEndTimeAfterStart(Scanner userInput, LocalTime startTime) {
        LocalTime endTime = readValidTime(userInput, "Enter end time (HH:MM): ");
        while (!endTime.isAfter(startTime)) { // Check if end time is after start time
            System.out.println("End time must be after start time. Please re-enter end time.");
            endTime = readValidTime(userInput, "Enter end time (HH:MM): "); // Prompt again for end time
        }
        return endTime;
    }
}
