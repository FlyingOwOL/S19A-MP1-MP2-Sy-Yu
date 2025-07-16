package Controllers;

// Models import
import Models.Account.AccountModel;
import Models.Calendar.*;
import Models.Entry.*;

// Views import
import Views.Add_Delete_Calendar_PopUps.*;
import Views.AddEntryPopUps.*;
import Views.*;

// Other imports
import java.util.ArrayList;

public class MainController {
    private ArrayList<AccountModel> accounts;
    private ArrayList<CalendarParentModel> publicCalendars; // includes Normal and Family calendars only
    
    public MainController() {
        this.accounts = new ArrayList<>();
        this.publicCalendars = new ArrayList<>();
    }
}
