package Controllers;

import Views.AccountPage;
import Views.Add_Delete_Calendar_PopUps.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controllers.Listeners_Add_Delete_Calendar_PopUps.*;

public class AccountSelectionController {
    private AccountPage accountPage;
    
    public AccountSelectionController(AccountPage accountPage) {
        this.accountPage = accountPage;
        this.accountPage.changeAccountSelection(new AccountSelectionListener());
    }
    class AccountSelectionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String optionSelected;
            try {
                optionSelected = accountPage.getSelectedAccount();
                JFrame popUp = null;
                switch (optionSelected) {
                    //"Add Calendar", "Delete Calendar", "View Journal","Sign out"
                    case "Add Calendar":
                        popUp = new AddCalendarFrame();
                        addNewCalendar ((AddCalendarFrame) popUp);
                        break;
                    case "Delete Calendar":
                        popUp = new DeleteCalendarFrame();
                        break;
                    case "View Journal":
                        popUp = new ViewJournal();
                        break;
                    case "Sign out":
                        accountPage.dispose(); // Close the account page
                        new LoginController();
                        break;
                }
                if (popUp != null) {
                    popUp.setLocationRelativeTo(accountPage); //centers it to accountPage;
                }
            } catch (Exception ex) {
                System.out.println("Error in account selection: " + ex.getMessage());
            }
        }
        public void addNewCalendar(AddCalendarFrame PopUp) {
            accountPage.getAddCalendarFrame().setButtonActionListener(new AddCalendar());
        }

    }
}
