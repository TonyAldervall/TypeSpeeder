package se.ju23.typespeeder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuService {
    private String language;
    private Scanner sc;
    public Menu(){
        sc = new Scanner (System.in);
        this.language = "engelska";
    }
    public void displayMenu() { //TODO change tests so i can make menus better
        System.out.print("Välj språk (svenska/engelska):");
        String language = sc.nextLine();
        while(!language.equalsIgnoreCase("svenska") && !language.equalsIgnoreCase("engelska")){
            System.out.print("Välj språk (svenska/engelska):");
            language = sc.nextLine();
        }
        setLanguage(language);
        if(language.equalsIgnoreCase("svenska")){
            System.out.print("Svenska valt.");
        }
        else{
            System.out.print("English chosen.");
        }
        List<String> menu = getMenuOptions();
        for(String s : menu){
            System.out.println(s);
        }
        System.out.print("\nYour choice: ");
    }
    public List<String> getMenuOptions() {
        List<String> menu = new ArrayList<>();
        if(language.equalsIgnoreCase("engelska")){
            menu.add("\n1. Play.");
            menu.add("2. Leaderboard.");
            menu.add("3. Manage Account.");
            menu.add("4. Settings.");
            menu.add("0. Logout.");
        }
        else{
            menu.add("\n1. Spela.");
            menu.add("2. Ranking Lista.");
            menu.add("3. Hantera Konto.");
            menu.add("4. Inställningar.");
            menu.add("0. Logga ut.");
        }
        return menu;
    }
    public void displayStartMenu(){
        if(language.equalsIgnoreCase("engelska")) {
            System.out.println("""
                                    
                    1. Login.
                    2. Create Account.
                    0. Exit."""
            );
            System.out.print("\nYour choice: ");
        }
        else{
            System.out.println("""
                                    
                    1. Logga in.
                    2. Skapa ett konto.
                    0. Avsluta."""
            );
            System.out.print("\nDitt val: ");
        }
    }
    public void displayManageAccountMenu(){
        if(language.equalsIgnoreCase("engelska")) {
            System.out.println("""
                                    
                    1. Change username.
                    2. Change password.
                    3. Change player name.
                    0. Exit."""
            );
            System.out.print("\nYour choice: ");
        }
        else{
            System.out.println("""
                                    
                    1. Ändra användarnamn.
                    2. Ändra lösenord.
                    3. Ändra spelarnamn.
                    0. Avsluta."""
            );
            System.out.print("\nDitt val: ");
        }
    }
    public void displayChallengeMenu() {
        if (language.equalsIgnoreCase("engelska")) {
            System.out.println("""
                                    
                    1. English quotes.
                    2. English words 25.
                    3. Swedish quotes.
                    4. Swedish words 25.
                    5. Letters.
                    0. Exit."""
            );
            System.out.print("\nYour choice: ");
        } else {
            System.out.println("""
                                    
                    1. Engelska citat.
                    2. Engelska ord 25.
                    3. Svenska citat.
                    4. Svenska ord 25.
                    5. Bokstäver.
                    0. Avsluta."""
            );
            System.out.print("\nDitt val: ");
        }
    }
    public void displayErrorMessage(){
        if (language.equalsIgnoreCase("engelska")) {
            System.out.println("You need to type something!");
        } else {
            System.out.println("Du måste skriva något!");
        }
    }

    public void setLanguage(String language) {
        if(language.equalsIgnoreCase("engelska") || language.equalsIgnoreCase("svenska")){
            this.language = language;
        }
    }
}
