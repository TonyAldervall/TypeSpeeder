package se.ju23.typespeeder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class AccountLeaderboard {
    @Id
    int id;
    private String playerName;
    private int highestWpm;
    public AccountLeaderboard() {

    }
    public static void printLeaderboard(AccountLeaderboardRepo accountLeaderboardRepo){
        List<AccountLeaderboard> accountLeaderboardList = accountLeaderboardRepo.findAll();

        for (int i = 0; i < accountLeaderboardList.size(); i++) {
            System.out.println("\n#" + (i + 1) + " " + accountLeaderboardList.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return playerName + "#" + id + ": " +highestWpm + " WPM";
    }
}
