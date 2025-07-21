package Models.Calendar;

import Models.Account.AccountModel;

public class Normal extends CalendarParentModel{
    private final boolean isPubliclyAvailable = true;

    public Normal(String name, AccountModel owner){
        super(name, owner);
    }

    //getters
    public boolean getAvailability() {
        return isPubliclyAvailable;
    }
}
