package Models.Entry;

import Models.Account.AccountModel;

import java.time.LocalDate;

public class Event extends EntryModel{
    private String venue;
    private String organizer;
    private LocalDate startDate;
    private LocalDate endDate;
    
    public Event(String title, String venue, AccountModel owner){
        super(title);
        this.organizer = owner.getName();
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    //getters
    public String getVenue() {
        return venue;
    }
    public String getOrganizer() {
        return organizer;
    }

    //setters
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /*
     * 
     */
    public boolean isValidStartDate(){
        return true;
    }
    /*
     * 
     */
    public boolean isValidEndDate(){
        return true;
    }
}
