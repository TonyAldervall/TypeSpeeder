package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes_english")
public class QuotesEnglish implements Quotes{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quote;

    public QuotesEnglish() {

    }

    @Override
    public String getQuote() {
        return null;
    }
}
