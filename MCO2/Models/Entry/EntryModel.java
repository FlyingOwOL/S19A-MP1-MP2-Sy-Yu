package Models.Entry;

import java.time.LocalDate;

public class EntryModel {
    private String title;
    private String details;
    private LocalDate date;

    public EntryModel(String title){
        this.title = title;
        this.details = "";
        this.date = LocalDate.now();
    }

    //getters
    public String getTitle() {
        return title;
    }
    public String getDetails() {
        return details;
    }
    public LocalDate getDate() {
        return date;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }


    //@Override
    public String toString() {
        return "Title: " + title + "\nDetails: " + details + "\nDate: " + date;
    }
}
