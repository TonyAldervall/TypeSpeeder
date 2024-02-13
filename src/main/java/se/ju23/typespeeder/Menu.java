package se.ju23.typespeeder;

import java.util.ArrayList;
import java.util.List;

public class Menu implements MenuService {
    public void displayMenu() {
        List<String> menu = getMenuOptions();
        for(String s : menu){
            System.out.println(s);
        }
        System.out.print("\nYour choice: ");
    }
    public List<String> getMenuOptions() {
        List<String> menu = new ArrayList<>();
        menu.add("\n1. Play.");
        menu.add("2. Leaderboard.");
        menu.add("3. Manage Account.");
        menu.add("4. Settings.");
        menu.add("0. Logout.");
        return menu;
    }
    public void displayStartMenu(){
        System.out.println("""
                
                1. Login.
                2. Create Account.
                0. Exit."""
        );
        System.out.print("\nYour choice: ");
    }
}
