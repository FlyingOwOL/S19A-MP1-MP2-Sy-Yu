import java.util.ArrayList;

public class Accounts {
    private String accountName;
    private String accountPasword;
    private ArrayList<Calendar> privateCalendars;
    private ArrayList<Calendar> publicCalendars;
    private boolean isActive;

    public Accounts(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPasword = accountPassword;
        this.privateCalendars = new ArrayList<>();
        this.publicCalendars = new ArrayList<>();
        this.isActive = true; // Default to active
    }

    // Getters for account details
    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPasword;
    }

    public ArrayList<Calendar> getPrivateCalendars() {
        return privateCalendars;
    }


    public boolean isActive() {
        return isActive;
    }

    public boolean addCalendar(){
        return false;
    }

    public boolean removeCalendar (Calendar calendar) {
        return false;
    }
}
