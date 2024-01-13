package tictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

/**
 * Creates a GUI layout for a TicTacToe game.
 * @author Isabella McIvor
*/
public class TicTacToeView extends JPanel {
    
    private int kind;
    private String lines = " , , , , , , , , ,";
    private TicTacToe game;
    private Player playerOne = new Player("player_one", 0, 0, 0);
    private Player playerTwo = new Player("player_two", 0, 0, 0);
    private JPanel mainSpace = new JPanel();
    private JPanel profile1 = new JPanel();
    private JPanel profile2 = new JPanel();
    private JTextArea output = new JTextArea(15, 4);

    /**
     * Sets up the layout for the GUI.
     * @param gameType - Representing the type of TicTacToe game
    */
    public TicTacToeView(int gameType) {
        super();
        kind = gameType;
        setLayout(new BorderLayout());
        game = new TicTacToe(3, 3);
        game.setGrid(TicTacToe.newGrid(kind, 3, 3));
        add(output, BorderLayout.NORTH);
        outputSetUp();
        add(mainSpace, BorderLayout.CENTER);
        chooseLoadProfile();
    }

    /* public methods */

    /**
     * Accessor for the token to be placed by the current player.
     * @return The token to be placed
    */
    public String getToken() {
        String token = "X";
        if (game.getCurrentPlayer() == 2) {
            token = "O";
        }
        return token;
    }

    /**
     * Adds text to the current message being displayed
     * @param toAdd - Text to add
    */
    public void outputAppend(String toAdd) {
        output.append(toAdd);
    }

    /**
     * Changes the current message being displayed
     * @param message - The message to display
    */
    public void outputSet(String message) {
        output.setText(message);
    }

    /* private methods */

    private void outputSetUp() {
        output.setEditable(false);
        output.setLineWrap(true);
    }

    private void chooseLoadProfile() {
        mainSpace.setLayout(new BorderLayout());
        output.setText("Would you like to load a user profile?");
        JPanel buttonPanel = new JPanel();
        mainSpace.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton yes = new JButton("Yes");
        yes.addActionListener(e->chooseProfileFile());
        buttonPanel.add(yes);
        JButton no = new JButton("No");
        no.addActionListener(e->chooseLoadGame());
        buttonPanel.add(no);
    }

    private void chooseProfileFile() {
        clearSpace();
        output.setText("Choose a file to load your profile.");
        mainSpace.setLayout(new BoxLayout(mainSpace, BoxLayout.Y_AXIS));
        JFileChooser fileChooser = new JFileChooser();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        mainSpace.add(buttonPanel, BorderLayout.EAST);
        JButton loadFileButton = new JButton("Browse files");
        loadFileButton.addActionListener(e->loadProfile(fileChooser));
        buttonPanel.add(loadFileButton);
        JButton done = new JButton("Done");
        done.addActionListener(e->chooseLoadGame());
        buttonPanel.add(done);
    }

    private void loadProfile(JFileChooser fileChooser) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().canRead()) {
               try {
                    lines = FileReader.readFile(fileChooser.getSelectedFile().toString());
                    playerOne.loadSavedString(lines);
               } catch (Exception e) {
                    output.setText(e.getMessage());
               }
            }
        }
    }

    private void createPlayerPanels() {
        clearSpace();
        profile1.removeAll();
        profile1.revalidate();
        profile1.repaint();
        profile2.removeAll();
        profile2.revalidate();
        profile2.repaint();
        add(profile1, BorderLayout.WEST);
        profile1.setLayout(new BoxLayout(profile1, BoxLayout.Y_AXIS));
        JTextArea info1 = new JTextArea(10, 20);
        profile1.add(info1);
        info1.setText(playerOne.getUsername());
        info1.append("\nGames played:\n" + playerOne.getGamesPlayed());
        info1.append("\nGames won:\n" + playerOne.getGamesWon());
        info1.append("\nGames lost:\n" + playerOne.getGamesLost());
        add(profile2, BorderLayout.EAST);
        profile2.setLayout(new BoxLayout(profile2, BoxLayout.Y_AXIS));
        JTextArea info2 = new JTextArea(10, 20);
        profile2.add(info2);
        info2.setText(playerTwo.getUsername());
        info2.append("\nGames played:\n" + playerTwo.getGamesPlayed());
        info2.append("\nGames won:\n" + playerTwo.getGamesWon());
        info2.append("\nGames lost:\n" + playerTwo.getGamesLost());
    }  

    private void chooseLoadGame() {
        clearSpace();
        createPlayerPanels();
        output.setText("Would you like to start a new game?");
        mainSpace.setLayout(new BoxLayout(mainSpace, BoxLayout.Y_AXIS));
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(e->makeGameGrid());
        mainSpace.add(newGame);
        JButton loadSaved = new JButton("Load previous game");
        loadSaved.addActionListener(e->chooseGameFile());
        mainSpace.add(loadSaved);
    }  

    private void chooseGameFile() {
        clearSpace();
        output.setText("Choose a file to load your game.");
        mainSpace.setLayout(new BorderLayout());
        JFileChooser fileChooser = new JFileChooser();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        mainSpace.add(buttonPanel, BorderLayout.WEST);
        JButton loadFileButton = new JButton("Browse files");
        loadFileButton.addActionListener(e->loadGame(fileChooser));
        buttonPanel.add(loadFileButton);
        JButton done = new JButton("Done");
        done.addActionListener(e->makeGameGrid());
        buttonPanel.add(done, BorderLayout.EAST);
    }

    private void loadGame(JFileChooser fileChooser) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().canRead()) {
               try {
                    lines = FileReader.readFile(fileChooser.getSelectedFile().toString());
                    game.loadSavedString(lines);
               } catch (Exception e) {
                    output.setText(e.getMessage());
               }
            }
        }
    }  
      
    private void makeGameGrid() {
        clearSpace();
        game = new TicTacToe(3, 3);
        game.setGrid(TicTacToe.newGrid(kind, 3, 3));
        game.loadSavedString(lines);
        output.setText(game.getGameStateMessage());
        mainSpace.setLayout(new GridLayout(3, 3, 3, 3));
        addFirstRow();
        addSecondRow();
        addThirdRow();
        JButton quitGame = new JButton("Save and quit");
        quitGame.addActionListener(e->saveAndQuit());
        profile2.add(quitGame, BorderLayout.NORTH);
    }  

    private void turn(int across, int down, JButton button) {
        if (game.isDone()) {
            output.setText(game.getGameStateMessage());
            updateProfiles();
            return;
        } 
        try {
            String token = getToken();
            game.takeTurn(across, down, token);
            changeButton(token, button);
            output.setText(game.getGameStateMessage());
            updateProfiles();
        } catch (RuntimeException e) {
            output.setText(game.getGameStateMessage());
        }
        if (game.isDone()) {
            lines = " , , , , , , , , ,";
            newGameOption();
        }
    }

    private void saveAndQuit() {
        clearSpace();
        output.setText("Choose the items you'd like to save.");
        mainSpace.setLayout(new BoxLayout(mainSpace, BoxLayout.Y_AXIS));
        JButton saveProfile = new JButton("Save profile");
        saveProfile.addActionListener(e->chooseSaveProfileFile());
        mainSpace.add(saveProfile);
        JButton saveGame = new JButton("Save game");
        saveGame.addActionListener(e->chooseSaveGameFile());
        mainSpace.add(saveGame);
    }

    private void chooseSaveProfileFile() {
        clearSpace();
        output.setText("Choose a file to save your profile to.");
        mainSpace.setLayout(new BorderLayout());
        JFileChooser fileChooser = new JFileChooser();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        mainSpace.add(buttonPanel, BorderLayout.WEST);
        JButton loadFileButton = new JButton("Browse files");
        loadFileButton.addActionListener(e->saveProfile(fileChooser));
        buttonPanel.add(loadFileButton);
        JButton done = new JButton("Done");
        done.addActionListener(e->saveAndQuit());
        buttonPanel.add(done, BorderLayout.EAST);
    }

    private void saveProfile(JFileChooser fileChooser) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().canRead()) {
               try {
                    FileWriter.writeFile(fileChooser.getSelectedFile().toString(), playerOne.getStringToSave());
               } catch (Exception e) {
                    output.setText(e.getMessage());
               }
            }
        }
    }

    private void chooseSaveGameFile() {
        clearSpace();
        output.setText("Choose a file to save your game to.");
        mainSpace.setLayout(new BorderLayout());
        JFileChooser fileChooser = new JFileChooser();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        mainSpace.add(buttonPanel, BorderLayout.WEST);
        JButton loadFileButton = new JButton("Browse files");
        loadFileButton.addActionListener(e->saveGame(fileChooser));
        buttonPanel.add(loadFileButton);
        JButton done = new JButton("Done");
        done.addActionListener(e->saveAndQuit());
        buttonPanel.add(done, BorderLayout.EAST);
    }

    private void saveGame(JFileChooser fileChooser) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().canRead()) {
               try {
                    FileWriter.writeFile(fileChooser.getSelectedFile().toString(), game.getStringToSave());
               } catch (Exception e) {
                    output.setText(e.getMessage());
               }
            }
        }
    }

    private void newGameOption() {
        clearSpace();
        mainSpace.setLayout(new BoxLayout(mainSpace, BoxLayout.Y_AXIS));
        output.append("\nNew Game? (To go to main menu click Options)");
        JButton yes = new JButton("Yes");
        yes.addActionListener(e->makeGameGrid());
        mainSpace.add(yes);
        JButton no = new JButton("No");
        no.addActionListener(e->end());
        mainSpace.add(no);
    }

    private void end() {
        clearSpace();
        output.setText("Thanks for playing! To go to main menu click Options.");
    }

    /* helper methods */

    private void changeButton(String newText, JButton button) {
        button.setText(newText);
    }

    private void updateProfiles() {
        if (game.isDone()) {
            playerOne.newGamePlayed();
            playerTwo.newGamePlayed();
            if (game.getWinner() == 1) {
                playerOne.newGameWon();
                playerTwo.newGameLost();
            } else {
                playerTwo.newGameWon();
                playerOne.newGameLost();
            }
            createPlayerPanels();
        }
    }

    private void clearSpace() {
        mainSpace.removeAll();
        mainSpace.revalidate();
        mainSpace.repaint();
    }

    private void addFirstRow() {
        JButton b1 = new JButton(game.getPosition(1, 1));
        b1.addActionListener(e->turn(1, 1, b1));
        mainSpace.add(b1);

        JButton b2 = new JButton(game.getPosition(2, 1));
        b2.addActionListener(e->turn(2, 1, b2));
        mainSpace.add(b2);

        JButton b3 = new JButton(game.getPosition(3, 1));
        b3.addActionListener(e->turn(3, 1, b3));
        mainSpace.add(b3);
    }

    private void addSecondRow() {
        JButton b4 = new JButton(game.getPosition(1, 2));
        b4.addActionListener(e->turn(1, 2, b4));
        mainSpace.add(b4);

        JButton b5 = new JButton(game.getPosition(2, 2));
        b5.addActionListener(e->turn(2, 2, b5));
        mainSpace.add(b5);

        JButton b6 = new JButton(game.getPosition(3, 2));
        b6.addActionListener(e->turn(3, 2, b6));
        mainSpace.add(b6);
    }

    private void addThirdRow() {
        JButton b7 = new JButton(game.getPosition(1, 3));
        b7.addActionListener(e->turn(1, 3, b7));
        mainSpace.add(b7);

        JButton b8 = new JButton(game.getPosition(2, 3));
        b8.addActionListener(e->turn(2, 3, b8));
        mainSpace.add(b8);

        JButton b9 = new JButton(game.getPosition(3, 3));
        b9.addActionListener(e->turn(3, 3, b9));
        mainSpace.add(b9);
    }

}