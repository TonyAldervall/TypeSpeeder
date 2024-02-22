package se.ju23.typespeeder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class MostCorrectInARowLeaderboard {
    @Id
    private int id;
    private int level;
    private String playerName;
    private int mostCorrectInARow;
    public MostCorrectInARowLeaderboard() {

    }
    public static void printLeaderboard(MostCorrectInARowLeaderboardRepo mostCorrectInARowLeaderboardRepo){
        List<MostCorrectInARowLeaderboard> mostCorrectInARowLeaderboardList = mostCorrectInARowLeaderboardRepo.findAll();

        for (int i = 0; i < mostCorrectInARowLeaderboardList.size(); i++) {
            System.out.println("\n#" + (i + 1) + " " + mostCorrectInARowLeaderboardList.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "Level " + level + " " + playerName + "#" + id + "\n"
                + "Most Correct In A Row: " + mostCorrectInARow ;
    }
}
