package se.ju23.typespeeder;

import java.util.List;

import static se.ju23.typespeeder.TypeSpeederApplication.*;

public class Challenge {
    //TODO add different difficulty levels?
    public static double startChallenge(String text){
        System.out.println("Type the following text as fast as you can:");
        System.out.println(ANSI_CYAN + "\"" + text + "\"" + ANSI_RESET);
        System.out.println("Press enter to start the timer");
        sc.nextLine();

        long startTime = System.currentTimeMillis();
        String typedText = sc.nextLine();
        long endTime = System.currentTimeMillis();

        String[] textWords = text.split("\\s+");
        String[] typedWords = typedText.split("\\s+");
        int wordErrors = 0;
        int charErrors = 0;

        challengeOutput(textWords, typedWords, wordErrors, charErrors);

        double raw = 0;
        double wpm = 0;
        calculateTypingSpeed(startTime, endTime, typedWords, wordErrors);





        System.out.println("Your Raw WPM: " + (int) raw);
        System.out.println("Your WPM: " + (int) wpm);
        System.out.println("Errors: " + charErrors);

        return wpm;
    }
    public static void challengeOutput(String[] textWords, String[] typedWords, int wordErrors, int charErrors){
        StringBuilder outputBuilder = new StringBuilder();

        for (int i = 0; i < Math.min(typedWords.length, textWords.length); i++) {
            if (!typedWords[i].equals(textWords[i])) {
                wordErrors++;
                for (int j = 0; j < Math.min(typedWords[i].length(), textWords[i].length()); j++) {
                    char typedChar = typedWords[i].charAt(j);
                    char expectedChar = textWords[i].charAt(j);
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
                if(typedWords[i].length() > textWords[i].length()){
                    for (int j = textWords[i].length(); j < typedWords[i].length(); j++) {
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
    }
    public static void calculateTypingSpeed(long startTime, long endTime, String[] typedWords, int wordErrors){
        double timeTakenMinutes = ((endTime - startTime) / 1000.0) / 60;
        double raw = typedWords.length / timeTakenMinutes;
        double wpm = (typedWords.length - wordErrors) / timeTakenMinutes;
    }
    public void lettersToType(){

    }
    public static String wordsToType(List<Words> wordsList){
        StringBuilder words = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            words.append(wordsList.get((int) Math.random() * wordsList.size()));
            words.append(" ");
        }
        return words.toString();
    }
    public static String quoteToType(List<Quotes> quotes){
        return quotes.get((int)Math.random() * quotes.size()).getQuote();
    }
}
