package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "words_english")
public class WordsEnglish implements Words{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String word;

    public WordsEnglish() {

    }

    @Override
    public String getWord() {
        return word;
    }
}
