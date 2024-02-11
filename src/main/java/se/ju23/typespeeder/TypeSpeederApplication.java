package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);
    }

    public static Menu menu = new Menu();
    public static Validator validator = new Validator();
    public static Scanner sc = new Scanner(System.in);
    public static Account currentUser;

    @Autowired
    AccountRepo accountRepo;
    @Override
    public void run(String... args) throws Exception {
        logIn();
    }
    public void logIn(){
        boolean loop = true;
        do {
            menu.displayLogInMenu();
            int menuChoice = validator.validateInt();

            switch (menuChoice) {
                case 1 -> {
                    List<Account> accountList = accountRepo.findAll();
                    currentUser = Account.logIn(sc, accountList);
                    if (currentUser == null) {
                        break;
                    }
                    System.out.println("Welcome " + currentUser.getPlayerName());
                    menu();
                }
                case 2 -> currentUser.createAccount();
            }
        }while(loop);
    }
    public void menu(){
        menu.displayMenu();
    }
}
