package tictactoe;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

/**
 * Class representing a Number Tic Tac Toe GUI.
 * @author Isabella McIvor
*/
public class NumTTTView extends TicTacToeView {

    private String token = "";
    private JPanel turnOptions = new JPanel();

    /**
     * Sets the opening visual of the game. 
    */
    public NumTTTView() {
        super(2);
        add(turnOptions, BorderLayout.SOUTH);
        makeTurnOptions();
        outputAppend("\n\nSelect a number to input from the options below.\n");
    }

    /* public methods */

    /**
     * Returns the token to be placed by the current player.
     * @return The token to be placed
    */
    @Override
    public String getToken() {
        return token;
    }

    /* setup methods */

    private void makeTurnOptions() {
        turnOptions.setLayout(new FlowLayout(FlowLayout.LEADING));
        ButtonGroup options = new ButtonGroup();
        JRadioButton c1 = new JRadioButton("0");
        addRadioButton(c1, options);
        JRadioButton c2 = new JRadioButton("1");
        addRadioButton(c2, options);
        JRadioButton c3 = new JRadioButton("2");
        addRadioButton(c3, options);
        JRadioButton c4 = new JRadioButton("3");
        addRadioButton(c4, options);
        JRadioButton c5 = new JRadioButton("4");
        addRadioButton(c5, options);
        JRadioButton c6 = new JRadioButton("5");
        addRadioButton(c6, options);
        JRadioButton c7 = new JRadioButton("6");
        addRadioButton(c7, options);
        JRadioButton c8 = new JRadioButton("7");
        addRadioButton(c8, options);
        JRadioButton c9 = new JRadioButton("8");
        addRadioButton(c9, options);
        JRadioButton c10 = new JRadioButton("9");
        addRadioButton(c10, options);
    }

    private void addRadioButton(JRadioButton button, ButtonGroup group) {
        group.add(button);
        button.addActionListener(e->setToken(button));
        turnOptions.add(button);
    }   

    /* listener methods */

    private void setToken(JRadioButton button) {
        token = button.getText();
        outputSet("Now choose a location from the grid.");
    }
 


}