package se.ju23.typespeeder;

import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String playerName;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private AccountStatistics accountStatistics;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Level level;

    public Account(String username, String password, String playerName){
        this.username = username;
        this.password = password;
        this.playerName = playerName;
        this.accountStatistics = new AccountStatistics(this);
        this.level = new Level(this);
    }
    public Account(){

    }

    public int getId(){
        return id;
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

    public AccountStatistics getAccountStatistics() {
        return accountStatistics;
    }

    public Level getLevel() {
        return level;
    }

    public static Account logIn(Scanner sc, List<Account> accountList) {
        System.out.print("\nEnter username (\"0\" to cancel): ");
        String username = sc.nextLine();
        if (username.equals("0")){
            return null;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        return successfulLogin(accountList, username, password);
    }

    public static void createAccount(Scanner sc, AccountRepo accountRepo) {
        String username = inputUsername(sc, accountRepo);
        if(username.equals("0")){
            return;
        }
        String password = inputPassword(sc);
        String playerName = inputPlayerName(sc);

        Account a = new Account(username, password, playerName);
        accountRepo.save(a);
    }
    private static String inputUsername(Scanner sc, AccountRepo accountRepo){
        List<Account> accountList = accountRepo.findAll();
        String username;

        boolean loop = true;
        do {
            System.out.print("Enter username (\"0\" to cancel):");
            username = sc.nextLine();
            if (username.equals("0")){
                return username;
            }
            boolean usernameTaken = false;
            for (Account a : accountList) {
                if (a.getUsername().equalsIgnoreCase(username)) {
                    usernameTaken = true;
                    System.out.println("\nUsername already taken!");
                    break;
                }
            }
            if(!usernameTaken){
                loop = false;
            }
        }while(loop);

        return username;
    }
    private static String inputPassword(Scanner sc){
        String password;

        boolean loop = true;
        do {
            System.out.print("Enter password: ");
            password = sc.nextLine();
            System.out.print("Repeat password: ");
            String repeatPassword = sc.nextLine();

            if (!password.equals(repeatPassword)) {
                System.out.println("Password does not match.");
            }
            else{
                loop = false;
            }
        }while(loop);
        return password;
    }
    private static String inputPlayerName(Scanner sc){
        System.out.print("Enter a Player Name: ");
        return sc.nextLine();
    }
    public static void manageUsername(Scanner sc, AccountRepo accountRepo, Account currentUser){
        String username = inputUsername(sc, accountRepo);
        if(username.equals("0")){
            return;
        }
        currentUser.setUsername(username);
        accountRepo.save(currentUser);
    }
    public static void managePassword(Scanner sc, AccountRepo accountRepo, Account currentUser){
        String password = inputPassword(sc);
        currentUser.setPassword(password);
        accountRepo.save(currentUser);
    }
    public static void managePlayerName(Scanner sc, AccountRepo accountRepo, Account currentUser){
        String playerName = inputPlayerName(sc);
        currentUser.setPlayerName(playerName);
        accountRepo.save(currentUser);
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
