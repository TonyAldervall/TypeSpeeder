package se.ju23.typespeeder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class MostCorrectLeaderboard {
    @Id
    private int id;
    private int level;
    private String playerName;
    private int correct;
    public MostCorrectLeaderboard() {

    }
    public static void printLeaderboard(MostCorrectLeaderboardRepo mostCorrectLeaderboard){
        List<MostCorrectLeaderboard> mostCorrectLeaderboardList = mostCorrectLeaderboard.findAll();

        for (int i = 0; i < mostCorrectLeaderboardList.size(); i++) {
            System.out.println("\n#" + (i + 1) + " " + mostCorrectLeaderboardList.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "Level: " + level + " " + playerName + "#" + id + ": " + correct + " Correct";
    }
}
