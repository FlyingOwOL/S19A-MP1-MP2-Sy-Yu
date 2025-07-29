package Models.Calendar;

import Models.Account.AccountModel;
import Models.Entry.EntryModel;

import java.util.ArrayList;

public class CalendarParentModel {
    private String name;
    private AccountModel owner;
    private ArrayList<EntryModel> entries; //includes all calendar entries such as events, tasks, etc.

    public CalendarParentModel(String name, AccountModel owner){
        this.entries = new ArrayList<EntryModel>();
        this.name = name;
        this.owner = owner;
    }

    //getters
    public String getName() {
        return name;
    }
    public AccountModel getOwner() {
        return owner;
    }
    public ArrayList<EntryModel> getEntries() {
        return entries;
    }
    public CalendarParentModel getCalendar() {
        return this;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }


    //methods for children classes only
    public void addEntry(EntryModel entry) {
        entries.add(entry);
    }
    public void editEntry(EntryModel newEntry, EntryModel oldEntry){
        int index = entries.indexOf(oldEntry);
        if (index != -1){
            entries.set(index, newEntry);
            System.out.println("Entry updated successfully.");
        } else {
            System.out.println("Entry not found.");
        }
    }
    public void deleteEntry(EntryModel entry) {
        entries.remove(entry);
    }
}
