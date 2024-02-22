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
    public static Level level;
    public static String ANSI_GREEN = "\033[32m";
    public static String ANSI_RED = "\033[31m";
    public static String ANSI_CYAN = "\033[36m";
    public static String ANSI_RESET = "\033[0m";


    @Autowired
    AccountRepo accountRepo;
    @Autowired
    AccountStatisticsRepo accountStatsRepo;
    @Autowired
    HighestWpmLeaderboardRepo highestWpmLeaderboardRepo;
    @Autowired
    MostCorrectLeaderboardRepo mostCorrectLeaderboardRepo;
    @Autowired
    MostCorrectInARowLeaderboardRepo mostCorrectInARowLeaderboardRepo;
    @Autowired
    QuotesEnglishRepo quotesEnglishRepo;
    @Autowired
    WordsEnglishRepo wordsEnglishRepo;
    @Autowired
    QuotesSwedishRepo quotesSwedishRepo;
    @Autowired
    WordsSwedishRepo wordsSwedishRepo;


    @Override
    public void run(String... args) throws Exception {
        startMenu();
    }
    public void startMenu(){
        boolean loop = true;
        do {
            menu.displayStartMenu();
            int menuChoice = validator.validateInt();

            switch (menuChoice) {
                case 1 -> logIn();
                case 2 -> Account.createAccount(sc, accountRepo);
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-2");
            }
        }while(loop);
    }
    public void logIn(){
        List<Account> accountList = accountRepo.findAll();
        currentUser = Account.logIn(sc, accountList);
        if (currentUser != null) {
            menu();
        }
    }
    public void menu(){
        System.out.println("Welcome " + currentUser.getPlayerName() + "!");
        boolean loop = true;
        do {
            menu.displayMenu();
            int menuChoice = validator.validateInt();

            switch (menuChoice) {
                case 1 -> challenge();
                case 2 -> leaderboard();
                case 3 -> manageAccount();
                case 4 ->{} //TODO Settings, language option? patch information? newsletter?
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-4");
            }

        }while(loop);
    }
    public void leaderboard(){
        boolean loop = true;
        do{
            menu.displayLeaderboardMenu();
            int menuChoice = validator.validateInt();

            switch(menuChoice){
                case 1 -> HighestWpmLeaderboard.printLeaderboard(highestWpmLeaderboardRepo);
                case 2 -> MostCorrectLeaderboard.printLeaderboard(mostCorrectLeaderboardRepo);
                case 3 -> MostCorrectInARowLeaderboard.printLeaderboard(mostCorrectInARowLeaderboardRepo);
                case 4 -> combinedLeaderboard();
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-4");
            }
        }while (loop);
    }
    public void combinedLeaderboard(){
        List<Account> accountList = accountRepo.findAll();
        List<Account> sortedList = CombinedLeaderboard.sortLeaderboard(accountList);
        CombinedLeaderboard.printLeaderboard(sortedList);
    }
    public void challenge(){
        boolean loop = true;
        do{
            menu.displayChallengeMenu();
            int menuChoice = validator.validateInt();

            switch(menuChoice){
                case 1 -> quotesEnglish();
                case 2 -> wordsEnglish25();
                case 3 -> quotesSwedish();
                case 4 -> wordsSwedish25();
                case 5 -> letters();
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-4");
            }
        }while (loop);
    }
    public void quotesEnglish(){
        List<Quotes> quotes = quotesEnglishRepo.findAllByIdNotNull();
        String quote = Challenge.quoteToType(quotes);
        double wpm = Challenge.startChallenge(quote, sc, currentUser);
        currentUser.getAccountStatistics().updateHighestWpm(wpm);
        currentUser.getLevel().tryLevelUp();
        accountRepo.save(currentUser);
    }
    public void wordsEnglish25(){
        List<Words> wordsList = wordsEnglishRepo.findAllByIdNotNull();
        String words = Challenge.wordsToType(wordsList);
        double wpm = Challenge.startChallenge(words, sc, currentUser);
        currentUser.getAccountStatistics().updateHighestWpm(wpm);
        currentUser.getLevel().tryLevelUp();
        accountRepo.save(currentUser);
    }
    public void quotesSwedish(){
        List<Quotes> quotes = quotesSwedishRepo.findAllByIdNotNull();
        String quote = Challenge.quoteToType(quotes);
        double wpm = Challenge.startChallenge(quote, sc, currentUser);
        currentUser.getAccountStatistics().updateHighestWpm(wpm);
        currentUser.getLevel().tryLevelUp();
        accountRepo.save(currentUser);
    }
    public void wordsSwedish25(){
        List<Words> wordsList = wordsSwedishRepo.findAllByIdNotNull();
        String words = Challenge.wordsToType(wordsList);
        double wpm = Challenge.startChallenge(words, sc, currentUser);
        currentUser.getAccountStatistics().updateHighestWpm(wpm);
        currentUser.getLevel().tryLevelUp();
        accountRepo.save(currentUser);
    }
    public void letters(){
        List<Words> wordsList = wordsEnglishRepo.findAllByIdNotNull();
        //TODO
    }
    public void manageAccount(){
        boolean loop = true;
        do {
            menu.displayManageAccountMenu();
            int menuChoice = validator.validateInt();

            switch(menuChoice){
                case 1 -> Account.manageUsername(sc, accountRepo, currentUser);
                case 2 -> Account.managePassword(sc, accountRepo, currentUser);
                case 3 -> Account.managePlayerName(sc, accountRepo, currentUser);
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-3");
            }

        }while (loop);
    }
    public void setting(){
        boolean loop = true;
        do{
            menu.displaySettingsMenu();
            int menuChoice = validator.validateInt();

            switch(menuChoice){
                case 1 -> menu.changeLanguage();
                case 2 -> {}
                case 3 -> {}
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-3");
            }
        }while (loop);
    }
}
