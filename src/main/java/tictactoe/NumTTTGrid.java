package tictactoe;

/**
 * Represents a number TicTacToe board.
 * @author Isabella McIvor
*/
public class NumTTTGrid extends GameGrid {

    private static String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final int sumToWin = 15;

    /**
     * Sets the width and height of the grid.
     * @param across - The width of the grid
     * @param down - The height of the grid
    */
    public NumTTTGrid(int across, int down) {
        super(across, down);
    }

    /**
     * Determines whether there is a horizontal win.
     * @return true if there is a horizontal win
     * @return false if there is a not a horizontal win
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
     * Determines whether there is a vertical win
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
     * Determines whether there is a diagonal win
     * @return true if there is a diagonal win
     * @return false if there is not a diagonal win
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
     * Validates the chosen input of token to place on board.
     * @param input - The chosen token to place
     * @param currentPlayer - The player placing the token
     * @throws Exception - If a no token is chosen
     * @throws Exception - If the token is not a valid symbol
     * @throws Exception - If player one choses an even input
     * @throws Exception - If player two choses an odd input
     * @throws Exception - If token has already been placed on the board
    */
    @Override 
    public void validateInput(String input, int currentPlayer) throws Exception {
        if (noInput(input)) {
            throw new Exception("Please choose a number first.");
        }
        if (!validSymbol(input)) {
            throw new Exception("Invalid input. Try again.");
        }
        if (oddPlayerEvenNumber(input, currentPlayer)) {
            throw new Exception("Must be an odd number. Try again.");
        }
        if (evenPlayerOddNumber(input, currentPlayer)) {
            throw new Exception("Must be an even number. Try again.");
        }
        if (alreadyPlaced(input)) {
            throw new Exception("This token was already used! Try again.");
        }
    }

    /**
     * Validates the chosen location to place token.
     * @param across - The across value of the location
     * @param down - The down value of the location
     * @throws Exception - If the location is out of boundaries
     * @throws Exception - If a token is already placed on that location
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

    private boolean noInput(String input) {
        if (input.equals("")) {
            return true;
        }
        return false;
    }

    private boolean validSymbol(String input) {
        for (String possible: symbols) {
            if (input.equals(possible)) {
                return true;
            } 
        }
        return false;
    }

    private boolean oddPlayerEvenNumber(String input, int currentPlayer) {
        int n = Integer.parseInt(input);
        if ((currentPlayer == 1) && (n % 2 == 0)) {
            return true;
        }
        return false;
    }

    private boolean evenPlayerOddNumber(String input, int currentPlayer) {
        int n = Integer.parseInt(input);
        if ((currentPlayer == 2) && (n % 2 != 0)) {
            return true;
        }
        return false;
    }

    private boolean alreadyPlaced(String input) {
        for (int i = 1; i <= getWidth(); i++) {
            for (int j = 1; j <= getHeight(); j++) {
                if (getValue(i, j).equals(input)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean rowCheck(int row) {
        int sum = 0;
        for (int i = 1; i <= getWidth(); i++) {
            if (getValue(i, row).equals(" ")) {
                return false;
            }
            int n = Integer.parseInt(getValue(i, row));
            sum = sum + n;
        }
        if (sum == sumToWin) {
            return true;
        }
        return false;
    }

    private boolean columnCheck(int column) {
        int sum = 0;
        for (int i = 1; i <= getHeight(); i++) {
            if (getValue(column, i).equals(" ")) {
                return false;
            }
            int n = Integer.parseInt(getValue(column, i));
            sum = sum + n;
        }
        if (sum == sumToWin) {
            return true;
        }
        return false;
    }

    private boolean leftDiagonalCheck() {
        if (getValue(1,1).equals(" ") 
            || getValue(2,2).equals(" ") 
            || getValue(getWidth(), getHeight()).equals(" ")) {
            return false;
        }
        int topLeft = Integer.parseInt(getValue(1,1));
        int middle = Integer.parseInt(getValue(2, 2));
        int bottomRight = Integer.parseInt(getValue(getWidth(), getHeight()));
        int sum = topLeft + middle + bottomRight;
        if (sum == sumToWin) {
            return true;
        }
        return false;
    }

    private boolean rightDiagonalCheck() {
        if (getValue(getWidth(), 1).equals(" ") 
            || getValue(2, 2).equals(" ") 
            || getValue(1, getHeight()).equals(" ")) {
            return false;
        }
        int topRight = Integer.parseInt(getValue(getWidth(), 1));
        int middle = Integer.parseInt(getValue(2,2));
        int bottomLeft = Integer.parseInt(getValue(1, getHeight()));
        int sum = topRight + middle + bottomLeft;
        if (sum == sumToWin) {
            return true;
        }
        return false;
    }

}