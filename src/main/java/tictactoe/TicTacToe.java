package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a TicTacToe game
 * @author Isabella McIvor
*/
public class TicTacToe extends boardgame.BoardGame implements boardgame.Saveable { 

    private int currentPlayer = 1;
    private String gameStateMessage;
    private boolean done = false;

    /**
     * Sets the width and length of board and opening message.
     * @param wide - The width of the grid
     * @param high - The height of the grid
    */
    public TicTacToe(int wide, int high) {
        super(wide, high);
        setGameStateMessage(openingMessage());
    }

    /**
     * Accessor for current player.
     * @return Integer value representing current player
    */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the game over status of the game.
     * @param state - The current game over status
    */
    public void setGameOver(boolean state) {
        done = state;
    }

    /**
     * Accessor for the token on certain position of board
     * @param across - The number of spaces across the token is located
     * @param down - The number of spaces down the token is locatd
     * @return The token in the specified spot
    */
    public String getPosition(int across, int down) {
        String position = grid().getValue(across, down);
        return position;
    }

    /**
     * Performs one turn of the game.
     * @param across - The number of spaces across the token will be placed
     * @param down - The number of spaces down the token will be placed
     * @param input - The String value of the token to be placed
     * @throws RuntimeException - If the input is invalid
    */
    @Override
    public boolean takeTurn(int across, int down, String input) {
        try {
            //validate location
            grid().validateLocation(across, down);
            //validate input
            grid().validateInput(input, currentPlayer);
            //place token
            setValue(across, down, input);
            //check for done and only switch player if not done
            if (!isDone()) {
                //switch player
                switchPlayer();
                //get a message to the UI
                setGameStateMessage(nextPlayerMessage());
            } else {
                setGameStateMessage(gameOverMessage());
            }
            return true;
        } catch (Exception e) {
            setGameStateMessage(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Does not do anything for class.
     * @param across - The number of spaces across the token will be placed
     * @param down - The number of spaces down the token will be placed
     * @param input - The integer value of the token to be placed
     * @return false always 
    */
    @Override 
    public boolean takeTurn(int across, int down, int input) {
        return false;
    }

    /**
     * Sets the grid.
     * @param grid - The grid
    */
    @Override
    public void setGrid(boardgame.Grid grid) {
        super.setGrid(grid);
        setGameOver(false);
    }

    /**
     * Determines if the game is finished.
     * @return true if finished
     * @return false if not finished
    */
    @Override
    public boolean isDone() {
        if (gameWon()) {
            done = true;
        } else if (gameTied()) {
            done = true;

        }
        return done;
    }

    /**
     * Accessor for game winner
     * @return The winner
    */
    @Override
    public int getWinner() {
        return currentPlayer;
    }

    /**
     * Accessor for a message representing the current state of the game.
     * @return The game state message
    */
    @Override 
    public String getGameStateMessage() {
        return gameStateMessage;
    }

    /**
     * Returns the current game into a file format.
     * @return The String representation of the game in file format
    */
    @Override
    public String getStringToSave() {
        String toSave = "";
        if (getCurrentPlayer() == 2) {
            toSave = toSave + "O\n";
        } else {
            toSave = toSave + "E\n";
        }
        for (int i = 1; i <= getHeight(); i++) {
            for (int j = 1; j <= getWidth(); j++) {
                if (grid().getValue(j, i).equals(" ") && j == getWidth()) {
                    toSave = toSave + "\n";
                } else if (grid().getValue(j, i).equals(" ")) {
                    toSave = toSave + ",";
                } else if (j == getWidth()) {
                    toSave = toSave + grid().getValue(j, i) + "\n";
                } else {
                    toSave = toSave + grid().getValue(j, i) + ",";
                }
            }
        }
        return toSave;
    }


    /**
     * Inputs a csv formatted String representation of the board into the game
     * @param toLoad - csv formatted String representation of board
    */
    @Override
    public void loadSavedString(String toLoad) {
        ArrayList<String> positions = new ArrayList<>(Arrays.asList(toLoad.split("[\\s,]", -1))); //[\\s,]+
        int n = 1;
        if (positions.get(0) == "O") {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
        for (int i = 1; i <= getHeight(); i++) {
            for (int j = 1; j <= getWidth(); j++) {
                if (positions.get(n).equals("")) {
                    setValue(j, i, " ");
                } else {
                    setValue(j, i, positions.get(n));
                }
                n++;
            }
        }
    }

    /* helper methods */
    private void switchPlayer() {
        if (getCurrentPlayer() == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }

    private boolean gameWon() {
        if (grid().horizontalWin()) {
            return true;
        }
        if (grid().verticalWin()) {
            return true;
        }
        if (grid().diagonalWin()) {
            return true;
        }
        return false;
    }

    private boolean gameTied() {
        boolean tied = false;
        if (!gameWon()) {
            tied = grid().isFull();
        }
        return tied;
    }

    private GameGrid grid() {
        return (GameGrid) getGrid();
    }

    private void setGameStateMessage(String msg) {
        gameStateMessage = msg;
    }

    private String nextPlayerMessage() {
        String player = "Player 1";
        if (currentPlayer == 2) {
            player = "Player 2";
        }
        return (player + "'s turn. Choose a position on the board.");
    }

    private String gameOverMessage() {
        String message = "";
        if (gameWon()) {
            message = "Player " + currentPlayer + " wins!";
        } else if (gameTied()) {
            message = "Tie game!";
        }
        return message;
    }

    private String openingMessage() {
        String msg = "Welcome to TicTacToe.\n";
        msg = msg + "Player " + currentPlayer + " goes first.";
        return msg;
    }

    /**
     * Creates a new grid for the given type of TicTacToe game.
     * @param kind - The type of TicTacToe game
     * @param wide - The width of the board
     * @param high - The height of the board
    */
    public static GameGrid newGrid(int kind, int wide, int high) {
        if (kind == 1) {
            return new XOGrid(wide, high);
        } else {
            return new NumTTTGrid(wide, high);
        }
    }

}