package se.ju23.typespeeder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuService {
    private String language;
    private Scanner sc;
    public Menu(){
        sc = new Scanner (System.in);
        this.language = "swedish";
    }
    public void displayMenu() {
        List<String> menu = getMenuOptions();
        for(String s : menu){
            System.out.println(s);
        }
        System.out.print("\nYour choice: ");
    }
    public List<String> getMenuOptions() {
        List<String> menu = new ArrayList<>();
        if(language.equalsIgnoreCase("english")){
            menu.add("\n1. Play.");
            menu.add("2. Leaderboards.");
            menu.add("3. Manage Account.");
            menu.add("4. Settings.");
            menu.add("0. Logout.");
        }
        else{
            menu.add("\n1. Spela.");
            menu.add("2. Ranking Listor.");
            menu.add("3. Hantera Konto.");
            menu.add("4. Inställningar.");
            menu.add("0. Logga ut.");
        }
        return menu;
    }
    public void displayStartMenu(){
        if(language.equalsIgnoreCase("english")) {
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
        if(language.equalsIgnoreCase("english")) {
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
        if (language.equalsIgnoreCase("english")) {
            System.out.println("""
                                    
                    1. English quotes.
                    2. English words 25.
                    3. Swedish quotes.
                    4. Swedish words 25.
                    0. Exit."""
            );
            System.out.print("\nYour choice: ");
        } else {
            System.out.println("""
                                    
                    1. Engelska citat.
                    2. Engelska ord 25.
                    3. Svenska citat.
                    4. Svenska ord 25.
                    0. Avsluta."""
            );
            System.out.print("\nDitt val: ");
        }
    }
    public void displayErrorMessage(){
        if (language.equalsIgnoreCase("english")) {
            System.out.println("You need to type something!");
        } else {
            System.out.println("Du måste skriva något!");
        }
    }
    public void displayLeaderboardMenu(){
        if (language.equalsIgnoreCase("english")) {
            System.out.println("""
                                    
                    1. Show Highest WPM Leaderboard.
                    2. Show Most Correct Leaderboard.
                    3. Show Most Correct In A Row Leaderboard.
                    4. Show Combined Leaderboard.
                    0. Exit."""
            );
            System.out.print("\nYour choice: ");
        } else {
            System.out.println("""
                                    
                    1. Visa Högst WPM Ranking Lista.
                    2. Visa Mest Rätt Ranking Lista.
                    3. Visa Mest Rätt I Rad Ranking Lista.
                    4. Visa Kombinerad Ranking Lista.
                    0. Avsluta."""
            );
            System.out.print("\nDitt val: ");
        }
    }
    public void displaySettingsMenu(){
        if (language.equalsIgnoreCase("english")) {
            System.out.println("""
                                    
                    1. Change menu language.
                    2. Show Newsletter.
                    3. Show Patch Version.
                    0. Exit."""
            );
            System.out.print("\nYour choice: ");
        } else {
            System.out.println("""
                                    
                    1. Ändra menyspråk.
                    2. Visa Nyhetsbrev.
                    3. Visa Uppdateringsversion.
                    0. Avsluta."""
            );
            System.out.print("\nDitt val: ");
        }
    }
    public void changeLanguage(){
        if (language.equalsIgnoreCase("english")) {
            System.out.print("\nChoose language (swedish/english):");
            String language = sc.nextLine();
            while (!language.equalsIgnoreCase("swedish") && !language.equalsIgnoreCase("english")) {
                System.out.print("Please choose language (swedish/english):");
                language = sc.nextLine();
            }
            setLanguage(language);
            if(language.equalsIgnoreCase("english")){
                System.out.println("English chosen.");
            }else {
                System.out.println("Svenska valt.");
            }
        }
        else {
            System.out.print("\nVälj språk (svenska/engelska):");
            String language = sc.nextLine();
            while (!language.equalsIgnoreCase("svenska") && !language.equalsIgnoreCase("engelska")) {
                System.out.print("Vänligen välj språk (svenska/engelska):");
                language = sc.nextLine();
            }
            setLanguage(language);
            if(language.equalsIgnoreCase("engelska")){
                System.out.println("English chosen.");
            }else {
                System.out.println("Svenska valt.");
            }
        }
    }

    public void setLanguage(String language) {
        if(language.equalsIgnoreCase("engelska") || language.equalsIgnoreCase("english")){
            this.language = "english";
        } else if (language.equalsIgnoreCase("svenska") || language.equalsIgnoreCase("swedish")) {
            this.language = "swedish";
        }
    }
}
