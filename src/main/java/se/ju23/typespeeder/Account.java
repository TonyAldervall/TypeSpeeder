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
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

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
    public void createAccount() {

    }

}
