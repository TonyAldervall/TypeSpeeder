package se.ju23.typespeeder;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ChallengePerformanceTest {
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    @Test
    public void testStartChallengePerformance() {
        Challenge challenge = new Challenge();
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("This is a test text.");
        Account mockUser = mock(Account.class);
        AccountStatistics mockStats = mock(AccountStatistics.class);
        Level mockLevel = mock(Level.class);
        when(mockUser.getAccountStatistics()).thenReturn(mockStats);
        when(mockUser.getLevel()).thenReturn(mockLevel);

        long startTime = System.nanoTime();
        challenge.startChallenge("This is a test text.", mockScanner, mockUser);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }
    @Test
    public void testLettersToTypePerformance() {
        Challenge challenge = new Challenge();
        long startTime = System.nanoTime();
        List<Words> wordsList = new ArrayList<>();
        WordsEnglish wordsEnglish = new WordsEnglish("Test");
        for (int i = 0; i < 25; i++) {
            wordsList.add(wordsEnglish);
        }
        challenge.wordsToType(wordsList);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}
