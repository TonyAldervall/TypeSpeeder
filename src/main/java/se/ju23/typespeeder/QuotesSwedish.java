package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes_swedish")
public class QuotesSwedish implements Quotes{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quote;

    public QuotesSwedish() {

    }

    @Override
    public String getQuote() {
        return quote;
    }
}
