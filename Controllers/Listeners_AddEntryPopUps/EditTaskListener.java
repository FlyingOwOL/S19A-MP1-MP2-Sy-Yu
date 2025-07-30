package Controllers.Listeners_AddEntryPopUps;

import Models.Entry.Task;
import Views.AddEntryPopUps.AddTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener class for handling the editing of an existing task entry.
 * This listener is attached to the submit button in the AddTask pop-up.
 * It updates the Task model based on the user's modifications and closes the window after saving.
 */
public class EditTaskListener implements ActionListener {
    private AddTask popUp;  // The pop-up window for editing the task entry
    private Task entry;     // The task entry being edited

    /**
     * Constructs the listener that enables editing of a task entry via the AddTask form.
     * It also pre-fills the form with the existing values from the given task.
     *
     * @param popUp the AddTask GUI component containing form fields
     * @param entry the task entry to be edited and updated
     */
    public EditTaskListener(AddTask popUp, Task entry) {
        this.popUp = popUp;
        this.entry = entry;

        this.popUp.setTitle(entry.getTitle());
        this.popUp.setCreatedByField(entry.getCreatedBy());
        this.popUp.setPriority(entry.getPriority());
        this.popUp.setStatus(entry.getStatus());
        this.popUp.setDetailArea(entry.getDetails());
        this.popUp.updateGUI();
    }

    /**
     * Called when the user confirms the edits. Updates the task entry with
     * the new values from the form and shows a success message.
     *
     * @param e the ActionEvent triggered when the submit button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String newTitle = popUp.getTitleField().getText();
            String newCreatedBy = popUp.getCreatedByField().getText();
            String newFinishedBy = popUp.getFinishedByField().getText();

            String newPriority = (String) popUp.getPriorityBox().getSelectedItem();     // selected priority
            String popUpStatus = (String) popUp.getStatusBox().getSelectedItem();       // selected status
            String newDetails = popUp.getDetailArea().getText();                        // detail text

            entry.setTitle(newTitle);
            entry.setCreatedBy(newCreatedBy);
            entry.setFinishedBy(newFinishedBy);
            entry.setPriority(newPriority);
            entry.setStatus(popUpStatus);
            entry.setDetails(newDetails);

            JOptionPane.showMessageDialog(popUp, 
                "Edit Successful", 
                newTitle, 
                JOptionPane.INFORMATION_MESSAGE);
            popUp.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(popUp,
                "An error occurred while editing the task entry: " + ex.getMessage(),
                "Edit Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
