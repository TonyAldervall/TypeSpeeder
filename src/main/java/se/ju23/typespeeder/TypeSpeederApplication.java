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
    public static String ANSI_GREEN = "\033[32m";
    public static String ANSI_RED = "\033[31m";
    public static String ANSI_CYAN = "\033[36m";
    public static String ANSI_RESET = "\033[0m";


    @Autowired
    AccountRepo accountRepo;
    @Autowired
    AccountStatisticsRepo accountStatsRepo;
    @Autowired
    AccountLeaderboardRepo accountLeaderboardRepo;
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
                case 2 -> AccountLeaderboard.printLeaderboard(accountLeaderboardRepo);
                case 3 -> manageAccount();
                case 4 ->{} //TODO Settings, menu settings? language option?
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-4");
            }

        }while(loop);
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
                case 0 -> loop = false;
                default -> System.out.println("\nPlease enter a number between 0-4");
            }
        }while (loop);
    }
    public void quotesEnglish(){
        List<QuotesEnglish> quotes = quotesEnglishRepo.findAll();

    }
    public void wordsEnglish25(){

    }
    public void quotesSwedish(){

    }
    public void wordsSwedish25(){

    }

    public void typingTest(){
        System.out.println("Type the following quote as fast as you can:");
        String quote = "The quick brown fox jumps over the lazy dog.";
        System.out.println(ANSI_CYAN + "\"" + quote + "\"" + ANSI_RESET);
        System.out.println("Press enter to start the timer");
        sc.nextLine();

        long startTime = System.currentTimeMillis();
        String typedText = sc.nextLine();
        long endTime = System.currentTimeMillis();

        int wordErrors = 0;
        int charErrors = 0;

        StringBuilder outputBuilder = new StringBuilder();
        String[] quoteWords = quote.split("\\s+");
        String[] typedWords = typedText.split("\\s+");

        for (int i = 0; i < Math.min(typedWords.length, quoteWords.length); i++) {
            if (!typedWords[i].equals(quoteWords[i])) {
                wordErrors++;
                for (int j = 0; j < Math.min(typedWords[i].length(), quoteWords[i].length()); j++) {
                    char typedChar = typedWords[i].charAt(j);
                    char expectedChar = quoteWords[i].charAt(j);
                    if (typedChar == expectedChar) {
                        outputBuilder.append(ANSI_GREEN);
                        outputBuilder.append(typedChar);
                    }
                    else {
                        charErrors++;
                        outputBuilder.append(ANSI_RED);
                        outputBuilder.append(typedChar);
                    }
                }
                if(typedWords[i].length() > quoteWords[i].length()){
                    for (int j = quoteWords[i].length(); j < typedWords[i].length(); j++) {
                        char typedChar = typedWords[i].charAt(j);
                        charErrors++;
                        outputBuilder.append(ANSI_RED);
                        outputBuilder.append(typedChar);
                    }
                }
                outputBuilder.append(" ");
            }
            else{
                outputBuilder.append(ANSI_GREEN);
                outputBuilder.append(typedWords[i]);
                outputBuilder.append(" ");
            }
        }

        System.out.println(outputBuilder);
        System.out.print(ANSI_RESET);



        double timeTakenMinutes = ((endTime - startTime) / 1000.0) / 60;
        double raw = typedWords.length / timeTakenMinutes;
        double wpm = (typedWords.length - wordErrors) / timeTakenMinutes;



        System.out.println("Your Raw WPM: " + (int) raw);
        System.out.println("Your WPM: " + (int) wpm);
        System.out.println("Errors: " + charErrors);

        updateHighestWpm(wpm);

    } //TODO optimize this method more //TODO add more quotes //TODO add different difficulty levels
    public void updateHighestWpm(double wpm){
        AccountStatistics accountStats = accountStatsRepo.findById(currentUser.getId());
        if(accountStats.getHighestWpm() < wpm){
            accountStats.setHighestWpm(wpm);
            accountStatsRepo.save(accountStats);
        }
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
}
