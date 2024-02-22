package se.ju23.typespeeder;

import java.util.List;

public class CombinedLeaderboard {
    public static List<Account> sortLeaderboard(List<Account> accountList){
        for (int i = 1; i < accountList.size(); i++) {
            Account current = accountList.get(i);
            int j = i - 1;

            while(j >= 0 && !isHigherRanked(accountList.get(j), current)){
                accountList.set((j + 1), accountList.get(j));
                j--;
            }
            accountList.set(j + 1, current);
        }

        return accountList;
    }

    private static boolean isHigherRanked(Account account1, Account account2){
        if(isHighestWpm(account1, account2) && hasMostCorrect(account1, account2) && hasMostCorrectInARow(account1, account2)){
            return true;
        } else if (isHighestWpm(account1, account2) && hasMostCorrect(account1, account2)) {
            return true;
        } else if (hasMostCorrect(account1, account2) && hasMostCorrectInARow(account1, account2)) {
            return true;
        } else if (isHighestWpm(account1, account2) && hasMostCorrectInARow(account1, account2)) {
            return true;
        } else if (isHighestWpm(account1, account2)) {
            return true;
        } else if (hasMostCorrect(account1, account2)) {
            return true;
        } else if (hasMostCorrectInARow(account1, account2)) {
            return true;
        }
        else {
            return false;
        }
    }
    private static boolean isHighestWpm(Account account1, Account account2) {
        return account1.getAccountStatistics().getHighestWpm() > account2.getAccountStatistics().getHighestWpm();
    }

    private static boolean hasMostCorrect(Account account1, Account account2) {
        return account1.getAccountStatistics().getCorrect() > account2.getAccountStatistics().getCorrect();
    }

    private static boolean hasMostCorrectInARow(Account account1, Account account2) {
        return account1.getAccountStatistics().getMostCorrectInARow() > account2.getAccountStatistics().getMostCorrectInARow();
    }
    public static void printLeaderboard(List<Account> accountList){
        int n = Math.min(accountList.size(), 10);
        for (int i = 0; i < n; i++) {
            System.out.println("\n#" + (i + 1) + " " + accountList.get(i).toString());
        }
    }

}
