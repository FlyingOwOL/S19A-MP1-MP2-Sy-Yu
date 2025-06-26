import java.util.ArrayList;

public class Accounts {
    private String accountName;
    private String accountPassword;
    private ArrayList<Calendar> privateCalendars;
    private ArrayList<Calendar> publicCalendars;
    private final boolean isActive;

    public Accounts(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.privateCalendars = new ArrayList<>();
        this.publicCalendars = new ArrayList<>();
        this.isActive = true; // Default to active
    }

    // Getters for account details
    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public ArrayList<Calendar> getPrivateCalendars() {
        return privateCalendars;
    }


    public boolean isActive() {
        return isActive;
    }

    public boolean addCalendar(Calendar calendar) {
        boolean isSuccessful = false;
        if (calendar != null) {
            if (calendar.getAvailability()) {
                publicCalendars.add(calendar);
            } else {
                privateCalendars.add(calendar);
            }
            isSuccessful = true;
        }
        return isSuccessful;
    }

    public boolean removeCalendar (Calendar calendar) {
        boolean isSuccessful = false;
        if (calendar != null) {
            if (publicCalendars.contains(calendar)) {
                publicCalendars.remove(calendar);
            } else if (privateCalendars.contains(calendar)) {
                privateCalendars.remove(calendar);
            }
            isSuccessful = true;
        }
        return isSuccessful;
    }
}
