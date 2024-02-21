package se.ju23.typespeeder;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {
    @Id
    private int id;
    private int level;
    private int points;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Account account;

    public Level(Account account) {
        this.level = 1;
        this.points = 0;
        this.account = account;
    }
    public Level(){

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        if(this.points + points < 0){
            setPoints(0);
        }
        else {
            this.points += points;
        }
    }
    public void tryLevelUp() {
        int requiredPoints = 50 * getLevel();

        if (getPoints() >= requiredPoints) {
            int remainingPoints = getPoints() - requiredPoints;
            int newLevel = getLevel() + 1;

            setLevel(newLevel);
            setPoints(remainingPoints);
        }
    }
}


