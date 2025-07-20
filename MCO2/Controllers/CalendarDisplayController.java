package Controllers;

import Views.AccountPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarDisplayController {
    private AccountPage accountPage;

    public CalendarDisplayController(AccountPage accountPage){
        this.accountPage = accountPage;

        this.accountPage.changeCalendarDisplay(new changeDisplay());
    }
    class changeDisplay implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String selectedDisplay;
            try{
                selectedDisplay = accountPage.getSelectedCalendarDisplay();
                accountPage.changeCalendarDisplay(selectedDisplay);
            } catch (NumberFormatException ex){
                System.out.println("Error in changing calendar display: ");
            }
        }
    }
}
