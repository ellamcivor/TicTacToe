package tictactoe;

/**
 * Represents a TicTacToe board.
 * @author Isabella McIvor
*/
public class XOGrid extends GameGrid {

    private static String[] symbols = {"X", "O"};

    /**
     * Sets the width and height of the grid.
     * @param across - The width of the grid
     * @param down - The height of the grid
    */
    public XOGrid(int across, int down) {
        super(across,down);
    }

    /**
     * Determines whether there is a horiztonal win.
     * @return true if there is a horizontal win
     * @return false if there is not a horizontal win
    */
    public boolean horizontalWin() {
        for (int i = 1; i <= getHeight(); i++) {
            if (rowCheck(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether there is a vertical win.
     * @return true if there is a vertical win
     * @return false if there is not a vertical win
    */
    public boolean verticalWin() {
        for (int i = 1; i <= getWidth(); i++) {
            if (columnCheck(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether there is a diagonal win.
     * @return true if there is a diagonal win.
     * @return false if there is not a diagonal win.
    */
    public boolean diagonalWin() {
        if (leftDiagonalCheck()) {
            return true;
        }
        if (rightDiagonalCheck()) {
            return true;
        }
        return false;
    }

    /**
     * Validates the chosen token to place on the board.
     * @param input - The token
     * @param currentPlayer - The player placing the token
     * @throws Exception if the token is invalid
    */
    @Override
    public void validateInput(String input, int currentPlayer) throws Exception {
        for (String possible: symbols) {
            if (input.equals(possible)) {
                return;
            }
        }
        throw new Exception("Invalid input. Try again.");
    }

    /**
     * Validates the chosen location to place the token on the board.
     * @param across - The chosen across value of the location
     * @param down - The chosen down value of the location
     * @throws Exception - If location is out of boundaries
     * @throws Exception - If location already has a token placed
    */
    @Override
    public void validateLocation(int across, int down) throws Exception {
        if (across < 1 || across > getWidth()) {
            throw new Exception("Out of bounds! Try again.");
        } else if (down < 1 || down > getHeight()) {
            throw new Exception("Out of bounds! Try again.");
        } else if (getValue(across, down) != " ") {
            throw new Exception("Spot taken! Try again.");
        }
    }


    /* helper methods */

    private boolean rowCheck(int row) {
        boolean match = false;
        if (!getValue(1, row).equals(" ")) {
            match = getValue(1, row).equals(getValue(2, row));
            if (match) {
                match = getValue(2, row).equals(getValue(3, row));
            }
        }
        return match;
    }

    private boolean columnCheck(int column) {
        boolean match = false;
        if (!getValue(column, 1).equals(" ")) {
            match = getValue(column, 1).equals(getValue(column, 2));
            if (match) {
                match = getValue(column, 2).equals(getValue(column, 3));
            }
        }
        return match;
    }

    private boolean leftDiagonalCheck() {
        boolean match = false;
        if (!getValue(getWidth(), 1).equals(" ")) {
            match = getValue(getWidth(), 1).equals(getValue(2, 2));
            if (match) {
                match = getValue(2, 2).equals(getValue(1, getHeight()));
            }
        }
        return match;
    }

    private boolean rightDiagonalCheck() {
        boolean match = false;
        if (!getValue(1, 1).equals(" ")) {
            match = getValue(1, 1).equals(getValue(2,2));
            if (match) {
                match = getValue(2, 2).equals(getValue(getWidth(), getHeight()));
            }
        }
        return match;
    }

}