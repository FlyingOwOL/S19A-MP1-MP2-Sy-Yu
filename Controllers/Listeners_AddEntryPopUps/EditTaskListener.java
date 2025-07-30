package Controllers.Listeners_AddEntryPopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.Entry.Task;
import Views.AddEntryPopUps.AddTask;

public class EditTaskListener implements ActionListener {
    private AddTask popUp;
    private Task entry; // Change to Task

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

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String newTitle = popUp.getTitleField().getText();
            String newCreatedBy = popUp.getCreatedByField().getText();
            String newFinishedBy = popUp.getFinishedByField().getText();

            // For JComboBox<String> components (returns selected item as String)
            String newPriority = (String) popUp.getPriorityBox().getSelectedItem();
            String popUpStatus = (String) popUp.getStatusBox().getSelectedItem();

            // For JTextArea component (returns text content as String)
            String newDetails = popUp.getDetailArea().getText();

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
        }catch(Exception ex){
        
        }
    }
}

