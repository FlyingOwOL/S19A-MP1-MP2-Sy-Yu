import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public ArrayList<Accounts> accounts = new ArrayList<>();
    public ArrayList<Calendar> publicCalendars = new ArrayList<>();
    public static ArrayList<Accounts> activeAccounts = new ArrayList<>();
    public ArrayList<Accounts> deactivatedAccounts = new ArrayList<>();
    public static void addToPublicCalendars(Calendar calendar) {
        Main main = new Main();
        main.publicCalendars.add(calendar);
    }
    private static void createAccount (Scanner userInput){
        Accounts newAccount = MainMethods.createAccount(userInput, activeAccounts);
        if (newAccount != null) {
            activeAccounts.add(newAccount);
            System.out.println ("Account created successfully!");
        }
    }
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        int menuChoice = -1;
        do{
            MainMethods.displayMenu();
            menuChoice = userInput.nextInt();
            if (menuChoice == 1) {
                createAccount(userInput);
            } else if (menuChoice == 2) {
                if (activeAccounts.size() > 0) {
                    //Accounts.login();
                }
            } else if (menuChoice != 0) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (menuChoice != 0);
        System.out.println("Thank you for using the Calendar Application!");
        userInput.close();
    }
}