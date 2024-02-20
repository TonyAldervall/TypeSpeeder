package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "account_statistics")
public class AccountStatistics {
    @Id
    private int id;
    private double highestWpm;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Account account;
    public AccountStatistics(Account account){
        this.account = account;
        this.highestWpm = 0;
    }
    public AccountStatistics(){

    }

    public int getId() {
        return id;
    }

    public double getHighestWpm() {
        return highestWpm;
    }

    public void setHighestWpm(double highestWpm) {
        this.highestWpm = highestWpm;
    }
}
