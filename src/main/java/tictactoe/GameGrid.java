package tictactoe;

import java.util.Iterator;

/**
 * Represents a general board game grid.
*/
public abstract class GameGrid extends boardgame.Grid {

    /**
     * Sets the width and height of the grid.
     * @param across - The width of the grid
     * @param down - The height of the grid
    */
    public GameGrid(int across, int down) {
        super(across,down);
    }

    /**
     * Determines whether there has been a horizontal win.
    */
    public abstract boolean horizontalWin();

    /**
     * Determines whether there has been a vertical win.
    */
    public abstract boolean verticalWin();

    /**
     * Determines whether there has been a diagonal win.
    */
    public abstract boolean diagonalWin();

    /**
     * Validates the chosen input.
     * @param input -- The token chosen to be placed
     * @param currentPlayer -- The player that chose the token
     * @throws Exception - If input is invalid
    */
    public abstract void validateInput(String input, int currentPlayer) throws Exception;

    /**
     * Validates the chosen location on the grid.
     * @param across - The width of the grid
     * @param down - The height of the grid
     * @throws Exception - If location is invalid
    */
    public abstract void validateLocation(int across, int down) throws Exception;

    /**
     * Determines if board is full.
     * @return true if board is full
     * @return false if board is not full
    */
    public boolean isFull() {
        Iterator<String> iter = iterator();
        int count = 0;
        while(iter.hasNext()) {
            if(!iter.next().equals(" ")) {
                count++;
            }
        }
        if (count == getWidth()*getHeight()) {
            return true;
        }
        return false;
    }
}