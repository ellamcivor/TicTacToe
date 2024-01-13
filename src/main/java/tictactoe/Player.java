package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Holds the attributes of a player profile
 * @author Isabella McIvor
*/
public class Player implements boardgame.Saveable {

    private String username;
    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private int gamesLost = 0;

    /**
     * Sets the attributes of the player.
     * @param playerName - The username of the player
     * @param totalGames - The total games played by the player
     * @param won - The total games won by the player
     * @param lost - The total games lost by the player
    */
    public Player(String playerName, int totalGames, int won, int lost) {
        username = playerName;
        gamesPlayed = totalGames;
        gamesWon = won;
        gamesLost = lost;
    }

    /**
     * Sets the username of the player.
     * @param user - The username
    */
    public void setUsername(String user) {
        username = user;
    }

    /**
     * Accessor for player username.
     * @return The username
    */
    public String getUsername() {
        return username;
    }

    /**
     * Increments the number of games played by one.
    */
    public void newGamePlayed() {
        gamesPlayed++;
    }

    /**
     * Accessor for number of games played.
     * @return The number of games played
    */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Increments the number of games won by one.
    */
    public void newGameWon() {
        gamesWon++;
    }

    /**
     * Accessor for the number of games won.
     * @return The number of games won
    */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Increments the number of games lost by one.
    */
    public void newGameLost() {
        gamesLost++;
    }

    /**
     * Accessor for the number of games lost
     * @return The number of games lost
    */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * Returns the player profile in a file format.
     * @return The String format of the player profile
    */
    @Override
    public String getStringToSave() {
        String toSave = "";
        toSave = toSave + getUsername() + "\n";
        toSave = toSave + getGamesPlayed() + "\n";
        toSave = toSave + getGamesWon() + "\n";
        toSave = toSave + getGamesLost() + "\n";
        return toSave;
    }

    /**
     * Mutates the attributes of the player from a String
     * @param toLoad - A csv formatted String of the player attributes
    */
    @Override
    public void loadSavedString(String toLoad) {
        ArrayList<String> info = new ArrayList<>(Arrays.asList(toLoad.split("\n")));
        setUsername(info.get(0));
        setGamesPlayed(Integer.parseInt(info.get(1)));
        setGamesWon(Integer.parseInt(info.get(2)));
        setGamesLost(Integer.parseInt(info.get(3)));
    }


    /* private methods */

    private void setGamesPlayed(int numGames) {
        gamesPlayed = numGames;
    }

    private void setGamesWon(int numWon) {
        gamesWon = numWon;
    }

    private void setGamesLost(int numLost) {
        gamesLost = numLost;
    }
}