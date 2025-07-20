package Controllers.Listeners_Add_Delete_Calendar_PopUps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.Add_Delete_Calendar_PopUps.AddCalendarFrame;

public class AddCalendar implements ActionListener{
    AddCalendarFrame addCalendarFrame;
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == addCalendarFrame.getCreationType()){

        } else if (e.getSource() == addCalendarFrame.getImportType()){

        } 
        if (e.getSource() == addCalendarFrame.getAddButton()){
            
        }
    }
}
