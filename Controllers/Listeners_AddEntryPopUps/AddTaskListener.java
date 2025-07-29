package Controllers.Listeners_AddEntryPopUps;

import Views.AccountPage;
import Views.AddEntryPopUps.AddTask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Models.Entry.Task;

public class AddTaskListener implements ActionListener{
    
    private AddTask addTask;
    private AccountPage accountPage;

    public AddTaskListener(AddTask addTask, AccountPage accountPage){
        this.addTask = addTask;
        this.accountPage = accountPage;
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTask.getSubmitButton()){
            String title = addTask.getTitleField().getText();
            String createdBy = addTask.getCreatedByField().getText();

            // For JComboBox<String> components (returns selected item as String)
            String priority = (String) addTask.getPriorityBox().getSelectedItem();
            String status = (String) addTask.getStatusBox().getSelectedItem();

            // For JTextArea component (returns text content as String)
            String details = addTask.getDetailArea().getText();

            Task newTask = new Task(title, priority, status, createdBy);
            newTask.setDetails(details);

            accountPage.getCurrentCalendar().addEntry(newTask);
            addTask.dispose();
        }
    }
}
