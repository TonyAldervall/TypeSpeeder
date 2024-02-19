package se.ju23.typespeeder;

import java.util.List;

public class Challenge {
    public void startChallenge(){

    }
    public void lettersToType(){

    }
    public static StringBuilder wordsToType(List<Words> wordsList){
        StringBuilder words = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            words.append(wordsList.get((int) Math.random() * wordsList.size()));
            words.append(" ");
        }
        return words;
    }
    public static String quoteToType(List<Quotes> quotes){
        return quotes.get((int)Math.random() * quotes.size()).getQuote();
    }
}
