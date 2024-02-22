package se.ju23.typespeeder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class HighestWpmLeaderboard {
    @Id
    private int id;
    private int level;
    private String playerName;
    private int highestWpm;
    public HighestWpmLeaderboard() {

    }
    public static void printLeaderboard(HighestWpmLeaderboardRepo highestWpmLeaderboardRepo){
        List<HighestWpmLeaderboard> highestWpmLeaderboardList = highestWpmLeaderboardRepo.findAll();

        for (int i = 0; i < highestWpmLeaderboardList.size(); i++) {
            System.out.println("\n#" + (i + 1) + " " + highestWpmLeaderboardList.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "Level " + level + " " + playerName + "#" + id + "\n"
                + "WPM: " + highestWpm ;
    }
}
