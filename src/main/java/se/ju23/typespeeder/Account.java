package se.ju23.typespeeder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "account")
public class Account {
    @Id
    String username;
    String password;
    String playerName;

    public Account(String username, String password, String playerName){
        this.username = username;
        this.password = password;
        this.playerName = playerName;
    }
    public Account(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public static Account logIn(Scanner sc, List<Account> accountList) {
        String username = enterUsername(sc);
        String password = enterPassword(sc);
        return successfulLogin(accountList, username, password);

    }
    public static void createAccount(Scanner sc) {

    }
    private static String enterUsername(Scanner sc){
        System.out.print("\nEnter username: ");
        return sc.nextLine();
    }
    private static String enterPassword(Scanner sc){
        System.out.print("Enter password: ");
        return sc.nextLine();
    }
    private static Account successfulLogin(List<Account> accountList, String username, String password){
        boolean succesfulLogin = false;

        for(Account a : accountList){
            if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                succesfulLogin = true;
                System.out.println("\nLogin successful!");
                return a;
            }
        }
        System.out.println("\nWrong username or password.");
        return null;
    }

}
