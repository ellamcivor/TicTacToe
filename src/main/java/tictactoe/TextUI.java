package tictactoe;

import java.util.Scanner;

/** 
 * Class for printing out a TicTacToe game and getting user input in the terminal
 * @author Isabella McIvor
*/
public class TextUI {

    private TicTacToe game = new TicTacToe(3, 3);
    private Scanner scan = new Scanner(System.in);
    private int acrossVal = -1;
    private int downVal = -1;

    /**
     * Creates a new TicTacToe game and prints a welcome message.
     * @param gameType - Integer value representing the type of TicTacToe game
    */
    public TextUI(int gameType) {
        game = new TicTacToe(3, 3);
        System.out.println(game.getGameStateMessage());
        game.setGrid(TicTacToe.newGrid(gameType, 3, 3));
    }

    /**
     * Runs the game.
    */
    public void play() {
        while(!game.isDone()) {
            getPosition();
            if (acrossVal == 0 || downVal == 0) {
                game.setGameOver(true);
                break;
            }
            try {
                game.takeTurn(acrossVal, downVal, getToken());
                System.out.println(game);
                System.out.println(game.getGameStateMessage());
            } catch (RuntimeException e) {
                System.out.println(game.getGameStateMessage());
            }
        }
    }

    /* helper methods */

    private void getPosition() {
        System.out.println("First enter across value. (To quit enter 0)");
        acrossVal = getNumInput();
        if (acrossVal == 0) {
            return;
        }
        System.out.println("Now enter down value. (To quit enter 0)");
        downVal = getNumInput();
    }

    private String getToken() {
        String token = "X";
        if (game.getCurrentPlayer() == 2) {
            token = "O";
        }
        return token;
    }

    private int getNumInput() {
        String input;
        int inputNum = 0;
        boolean valid = false;
        while (!valid) {
            input = scan.nextLine();
            if (isInt(input)) {
                inputNum = Integer.parseInt(input);
                valid = true;
            }
        }
        return inputNum;
    }

    private boolean isInt(String theInput) {
        try {
            int inputNum = Integer.parseInt(theInput);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Not an integer input. Try again.");
            return false;
        }
    }

    public static void main(String[] args) {
        TextUI interact = new TextUI(1);
        interact.play();
    }
}