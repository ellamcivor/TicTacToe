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
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

/**
 * Creates a GUI layout for the game.
 * @author Isabella McIvor
*/
public class GameUI extends JFrame {
    private JPanel mainPanel = new JPanel();
    //private JPanel playerProfile = new JPanel();
    private JMenuBar menuBar = new JMenuBar();

    /**
     * Creates JFrame for the game.
    */
    public GameUI() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        mainMenu();

        add(mainPanel, BorderLayout.CENTER);    
        pack();
    }


    private void mainMenu() {
        setTitle("Main Menu");
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.setLayout(new BorderLayout());
        JTextArea openingMessage = new JTextArea(10,4);
        mainPanel.add(openingMessage, BorderLayout.NORTH);
        openingMessage.setText("Welcome to TicTacToe");
        openingMessage.setEditable(false);
        openingButtons();
    }

    private void openingButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        JButton newTTT = new JButton("Play Tic Tac Toe");
        newTTT.addActionListener(e->makeTTTGamePanel());
        buttonPanel.add(newTTT);
        JButton newNumTTT = new JButton("Play Number Tic Tac Toe");
        newNumTTT.addActionListener(e->makeNumTTTGamePanel());
        buttonPanel.add(newNumTTT);
    }

    private void makeTTTGamePanel() {
        setTitle("X's and O's");
        menuBar.removeAll();
        menuBar.revalidate();
        menuBar.repaint();
        createMenu();
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new TicTacToeView(1), BorderLayout.CENTER);
    }

    private void makeNumTTTGamePanel() {
        setTitle("Number Tic Tac Toe");
        menuBar.removeAll();
        menuBar.revalidate();
        menuBar.repaint();
        createMenu();
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new NumTTTView(), BorderLayout.CENTER);
    }

    private void createMenu() {
        add(menuBar, BorderLayout.NORTH);
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem s1 = new JMenuItem("New TicTacToe Game");
        s1.addActionListener(e->makeTTTGamePanel());
        menu.add(s1);
        JMenuItem s2 = new JMenuItem("New Number TicTacToe Game");
        s2.addActionListener(e->makeNumTTTGamePanel());
        menu.add(s2);
        JMenuItem s3 = new JMenuItem("Main menu");
        s3.addActionListener(e->mainMenu());
        menu.add(s3);
    }


    public static void main(String[] args) {
        GameUI tester = new GameUI();
        tester.setVisible(true);
    }
}