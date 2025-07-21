package Models.Calendar;

import Models.Account.AccountModel;

public class Family extends CalendarParentModel {
    private final boolean isPubliclyAvailable = true;
    private String accessCode;

    public Family(String name, AccountModel owner, String accessCode) {
        super(name, owner);
        this.accessCode = accessCode;
    }

    //getters
    public boolean getAvailability() {
        return isPubliclyAvailable;
    }
    public String getCode() {
        return accessCode;
    }

    //setters
    public void setCode(String newAccessCode){
        this.accessCode = newAccessCode;
    }
}
