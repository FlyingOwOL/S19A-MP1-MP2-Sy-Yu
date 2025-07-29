package Models.Calendar;

import Models.Account.AccountModel;

public class Personal extends CalendarParentModel{
    private final boolean isPubliclyAvailable = false;

    public Personal(String name, AccountModel owner){
        super(name, owner);
    }

    //getters
    public boolean getAvailability() {
        return isPubliclyAvailable;
    }
}
