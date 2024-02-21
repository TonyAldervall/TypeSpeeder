package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "account_statistics")
public class AccountStatistics {
    @Id
    private int id;
    private double highestWpm;
    private int correct;
    private int correctInARow;
    private int mostCorrectInARow;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Account account;
    public AccountStatistics(Account account){
        this.account = account;
        this.highestWpm = 0;
        this.correct = 0;
        this.correctInARow = 0;
        this.mostCorrectInARow = 0;
    }
    public AccountStatistics(){

    }

    public int getId() {
        return id;
    }

    public double getHighestWpm() {
        return highestWpm;
    }

    public int getCorrect() {
        return correct;
    }
    public int getCorrectInARow() {
        return correctInARow;
    }
    public int getMostCorrectInARow() {
        return mostCorrectInARow;
    }

    public void setHighestWpm(double highestWpm) {
        this.highestWpm = highestWpm;
    }
    public void addCorrect(int correct){
        this.correct += correct;
    }
    public void addCorrectInARow(){
        this.correctInARow += 1;
        updateMostCorrectInARow();
    }
    public void resetCorrectInARow(){
        this.correctInARow = 0;
    }
    public void updateMostCorrectInARow(){
        if(getCorrectInARow() > getMostCorrectInARow()){
            this.mostCorrectInARow = getCorrectInARow();
        }
    }

    public void updateHighestWpm(double wpm){
        if(getHighestWpm() < wpm){
            setHighestWpm(wpm);
        }
    }
}
