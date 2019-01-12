package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class ScoreIO {
    private static CopyOnWriteArrayList<Integer> intHighScores = new CopyOnWriteArrayList<>(); //Arraylist to hold the integer scores temporarily
    private static CopyOnWriteArrayList<String> tempHighScores = new CopyOnWriteArrayList<>(); //Arraylist to hold the String names temporarily
    public static CopyOnWriteArrayList<String> highScores = new CopyOnWriteArrayList<>(); //Arraylist to hold the current final highscores list. Publicly avalible

    /**This method is used will simply search through a file for scores and check for a score by a user of the same
     * name if it finds a user of the same name it will then check if the score is lower than the highest score of that
     * user. If it is, it will remove that score.
     *
     * @param fill the fill is a parameter used to set the fill used inbetween the users name and score. This is
     * because it needs to be empty white space when it is comparing scores and then have a colon followed by
     * white space when it's printing to a list.
     */
    private static void remakeScoreList(String fill) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("res/score.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        highScores.clear();
        tempHighScores.clear();
        intHighScores.clear();
        assert scanner != null;
        while (scanner.hasNext()) {
            String name = scanner.next();
            String score = scanner.next();
            int scoreValue = Integer.parseInt(score);
            String finalScore = "\r\n" + name + fill + score;
            intHighScores.add(scoreValue);
            tempHighScores.add(finalScore);
        }
        for(int x = 0; x < 5; x++) {
            int currentHighest = Integer.MIN_VALUE;
            int highestIndex = Integer.MIN_VALUE;
            for(int y = 0; y < intHighScores.size(); y++) {
                int currentValue = intHighScores.get(y);
                if(currentValue > currentHighest) {
                    currentHighest = currentValue;
                    highestIndex = y;
                }
            }
            if(highestIndex >= 0) {
                highScores.add(tempHighScores.get(highestIndex));
                intHighScores.remove(highestIndex);
                tempHighScores.remove(highestIndex);
            }
        }
    }

    /**This method will take the score and name of the player and add them to the list. It does this by first remaking
     * the current list with white spaces, and then adding the players score. It then performs a series of checks.
     * first to see if the which score of the player's is the highest, and the next to remove all scores that are lower
     * then the highest one. Finally it will write these changes to the file, and remake the list with commas instead.
     *
     * @param score - The score that the player achieved this round
     * @param name - The username of the player
     */
    public static void addScore(int score, String name) {
        String temp = name+" "+score;
        String finalTemp = "\r\n"+temp;
        remakeScoreList(" ");

        if(score > 0) {
            highScores.add(finalTemp);
        }

        int bestScore = 0;
        int highScorePos = 0;
        for (int i = 0; i < highScores.size(); i++) {
            int currentScore = Integer.parseInt(highScores.get(i).substring(6, highScores.get(i).length()));
            if (highScores.get(i).substring(0, 5).equals("\r\n" + name)) {
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    highScorePos = i;
                }
            }
        }

        for(int i = 0; i < highScores.size(); i++) {
            int currentScore = Integer.parseInt(highScores.get(i).substring(6,highScores.get(i).length()));
            if(highScores.get(i).substring(0, 5).equals("\r\n"+name)) {
                if(currentScore <= bestScore && i != highScorePos) {
                    highScores.remove(i);
                }
            }
        }

        try {
            PrintWriter writer = new PrintWriter("res/score.txt");
            writer.print("");
            writer.close();
            for(String s:highScores) {
                Files.write(Paths.get("res/score.txt"), s.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException ignored) { }


        remakeScoreList(": ");
    }
}
