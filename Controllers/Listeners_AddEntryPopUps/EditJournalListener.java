package Controllers.Listeners_AddEntryPopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.Entry.Journal;
import Views.AddEntryPopUps.AddJournal;

public class EditJournalListener implements ActionListener {
    private AddJournal popUp;
    private Journal entry; // Change to Journal

    public EditJournalListener(AddJournal popUp, Journal entry) {
        this.popUp = popUp;
        this.entry = entry;

        this.popUp.setDetailArea(entry.getDetails());
        this.popUp.setMonthBox(entry.getMonth());
        this.popUp.updateGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String newDetails = popUp.getDetailArea().getText().trim();

            entry.setDetails(newDetails);

            JOptionPane.showMessageDialog(popUp, 
            "Edit Successful", 
            "Changes", 
            JOptionPane.INFORMATION_MESSAGE);
            popUp.dispose();
        }catch(Exception ex){

        }
    }
}

