package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.Task;
import Views.AccountPage;
import Views.AddEntryPopUps.AddTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

/**
 * Listener class for handling the "Submit" action in the AddTask pop-up window.
 * 
 * This listener retrieves user input from the AddTask form, creates a new Task
 * entry with the provided information, and adds it to the current calendar in
 * the AccountPage view.
 */
public class AddTaskListener implements ActionListener {
    
    private AddTask addTask;              // Reference to the AddTask pop-up view
    private AccountPage accountPage;      // Reference to the main AccountPage view

    /**
     * Constructor for AddTaskListener.
     * 
     * @param addTask the AddTask form where task details are entered
     * @param accountPage the main account page where the calendar is located
     */
    public AddTaskListener(AddTask addTask, AccountPage accountPage) {
        this.addTask = addTask;
        this.accountPage = accountPage;
    }

    /**
     * Handles the action event when the "Submit" button is clicked.
     * 
     * @param e the ActionEvent triggered by the button click
     */
    public void actionPerformed(ActionEvent e) {
        // Check if the event source is the Submit button
        if (e.getSource() == addTask.getSubmitButton()) {
            try{
                // Retrieve text input fields
                String title = addTask.getTitleField().getText();
                String createdBy = addTask.getCreatedByField().getText();
                String date = addTask.getDateField().getText();

                // Retrieve selected items from combo boxes
                String priority = (String) addTask.getPriorityBox().getSelectedItem();
                String status = (String) addTask.getStatusBox().getSelectedItem();

                // Retrieve the content from the text area
                String details = addTask.getDetailArea().getText();

                boolean stop = false;
                if (createdBy.isEmpty()){
                    JOptionPane.showMessageDialog(accountPage, 
                                        "Missing created by person name", 
                                        "Task", JOptionPane.ERROR_MESSAGE);
                    stop = true;
                }

                if (title.isEmpty() && !stop){
                    JOptionPane.showMessageDialog(accountPage,
                                        "Missing title", 
                                            "Task", JOptionPane.ERROR_MESSAGE);
                    stop = true;
                }

                if (date.isEmpty() && !stop){
                    JOptionPane.showMessageDialog(accountPage, 
                                        "Missing date", 
                                            "Task", JOptionPane.ERROR_MESSAGE);
                }

                if(!stop){
                    // Create and populate the new Task entry
                    Task newTask = new Task(title, priority, status, createdBy);
                    newTask.setDetails(details);
                    newTask.setDate(date);

                    // Add the task to the current calendar
                    accountPage.getCurrentCalendar().addEntry(newTask);

                    // Close the AddTask pop-up window
                    addTask.dispose();
                }
            } catch (DateTimeParseException ex){
                JOptionPane.showMessageDialog(accountPage, 
                                 "date format should be like '2025-06-03", 
                                    "Wrong date format", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}