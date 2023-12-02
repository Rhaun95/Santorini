package tnt.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Model Class for the Highscore
 * @author Hendrik Gonschor
 */

public class Highscores {
    private static final String HIGHSCORE_FILE = "src/main/resources/saves/highscores.txt";
    private static final int MAX_SCORES = 3;

    /**
     * Updates the Highscore Methode with the given params
     * @param playerName for the identification and the name in the Highscore List
     * @param turnAmount for the amount in how many turns the Player has won
     */

    public static void updateHighscore(String playerName, int turnAmount) {
        List<ScoreEntry> scores = readHighscores();
        ScoreEntry newEntry = new ScoreEntry(playerName, turnAmount);
        scores.add(newEntry);
        sortScores(scores);
        scores = scores.subList(0, Math.min(scores.size(), MAX_SCORES));
        writeHighscores(scores);
    }

    /**
     * makes the new Highscore List
     * @return the updatet Highscore List
     */
    private static List<ScoreEntry> readHighscores() {
        List<ScoreEntry> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String playerName = parts[1];
                    int turnAmount = Integer.parseInt(parts[0]);
                    ScoreEntry entry = new ScoreEntry(playerName, turnAmount);
                    scores.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    /**
     * sorts the element in the list
     * @param scores scroes from the list
     */
    private static void sortScores(List<ScoreEntry> scores) {
        Collections.sort(scores, Comparator.comparingInt(ScoreEntry::getTurnAmount));
    }

    /**
     * writes the sorted List in the Highscore.txt
     * @param scores is our list
     */
    private static void writeHighscores(List<ScoreEntry> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            for (ScoreEntry score : scores) {
                writer.write(score.getTurnAmount() + " " + score.getPlayerName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class that holds the ScoreEntrys for the highscore Class
     */
    private static class ScoreEntry {
        private String playerName;
        private int turnAmount;

        public ScoreEntry(String playerName, int turnAmount) {
            this.playerName = playerName;
            this.turnAmount = turnAmount;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getTurnAmount() {
            return turnAmount;
        }
    }
}
