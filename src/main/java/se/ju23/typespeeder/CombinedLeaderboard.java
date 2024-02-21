package se.ju23.typespeeder;

import java.util.List;

public class CombinedLeaderboard {
    public static void sortLeaderboard(){

    }
    public static void printLeaderboard(List<CombinedLeaderboard> combinedLeaderboardList){
        for (int i = 0; i < combinedLeaderboardList.size(); i++) {
            System.out.println("\n#" + (i + 1) + " " + combinedLeaderboardList.get(i).toString());
        }
    }

}
