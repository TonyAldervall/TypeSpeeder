package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "words_swedish")
public class WordsSwedish implements Words{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String word;

    public WordsSwedish() {

    }

    @Override
    public String getWord() {
        return word;
    }
}
