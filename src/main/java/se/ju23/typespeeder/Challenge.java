package se.ju23.typespeeder;

import java.util.List;
import java.util.Scanner;

import static se.ju23.typespeeder.TypeSpeederApplication.*;

public class Challenge {
    public static double startChallenge(String text){
        System.out.println("Type the following text as fast as you can:");
        System.out.println(ANSI_CYAN + "\n\"" + text + "\"" + ANSI_RESET);
        System.out.println("Press enter to start the timer");
        sc.nextLine();
        long startTime = System.currentTimeMillis();
        String typedText = sc.nextLine();
        long endTime = System.currentTimeMillis();
        if(typedText.isEmpty()){
            menu.displayErrorMessage();
            return 0;
        }

        String[] textWords = text.split("\\s+");
        String[] typedWords = typedText.split("\\s+");

        int[] errors = challengeOutput(textWords, typedWords, text);
        int wordErrors = errors[0];
        int charErrors = errors[1];

        double timeTakenMinutes = ((endTime - startTime) / 1000.0) / 60;
        double raw = typedWords.length / timeTakenMinutes;
        double wpm = (typedWords.length - wordErrors) / timeTakenMinutes;

        System.out.println("\nYour Raw WPM: " + (int) raw);
        System.out.println("Your WPM: " + (int) wpm);
        System.out.println("Errors: " + charErrors);

        return wpm;
    }
    public static int[] challengeOutput(String[] textWords, String[] typedWords, String text){
        StringBuilder outputBuilder = new StringBuilder();
        int wordErrors = 0;
        int charErrors = 0;
        int maxWordsLength = Math.max(typedWords.length, textWords.length);
        int minWordsLength = Math.min(typedWords.length, textWords.length);

        for (int i = 0; i < maxWordsLength; i++) { //Loops through the longest String.
            if(i < typedWords.length && i < textWords.length) { //If the index is in bounds for both strings.
                if (!typedWords[i].equals(textWords[i])) { //If the words don't match, check which specific characters aren't matching.
                    wordErrors++;

                    for (int j = 0; j < Math.max(typedWords[i].length(), textWords[i].length()); j++) { // Loops through the longest word.
                        if(j < typedWords[i].length() && j < textWords[i].length()){
                            char typedChar = typedWords[i].charAt(j);
                            char expectedChar = textWords[i].charAt(j);
                            if (typedChar == expectedChar) {
                                outputBuilder.append(ANSI_GREEN);
                                outputBuilder.append(typedChar);
                            } else {
                                charErrors++;
                                outputBuilder.append(ANSI_RED);
                                outputBuilder.append(typedChar);
                            }
                        }
                        else if (typedWords[i].length() > textWords[i].length()) { //Handle any excess characters in a word as errors.
                            char typedChar = typedWords[i].charAt(j);
                            charErrors++;
                            outputBuilder.append(ANSI_RED);
                            outputBuilder.append(typedChar);

                        } else if (typedWords[i].length() < textWords[i].length()) { //Handle any missing characters in a word as errors.
                            //char missingChar = textWords[i].charAt(j);
                            charErrors++;
                            //outputBuilder.append(ANSI_RED);
                            //outputBuilder.append(missingChar);
                        }
                    }
                    outputBuilder.append(" ");
                } else { //If the words match just accept them.
                    outputBuilder.append(ANSI_GREEN);
                    outputBuilder.append(typedWords[i]);
                    outputBuilder.append(" ");
                }
            } else if (i < typedWords.length) { // If typedWords is too long, handle the remaining words as errors.
                wordErrors++;
                outputBuilder.append(ANSI_RED).append(typedWords[i]).append(" ");
            }
            else if (i < textWords.length) { // If typedWords is too short, handle the remaining words as errors.
                wordErrors++;
                outputBuilder.append(ANSI_RED).append(textWords[i]).append(" ");
            }
        }
        System.out.println(ANSI_CYAN + "\n\"" + text + "\"" + ANSI_RESET);
        System.out.println(outputBuilder);
        System.out.print(ANSI_RESET);

        return new int[]{wordErrors, charErrors};
    }

    public void lettersToType(){
        //TODO
    }
    public static String wordsToType(List<Words> wordsList){
        StringBuilder words = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            words.append(wordsList.get((int) (Math.random() * wordsList.size())).getWord());
            words.append(" ");
        }
        return words.toString();
    }
    public static String quoteToType(List<Quotes> quotes){
        return quotes.get((int)(Math.random() * quotes.size())).getQuote();
    }
}
