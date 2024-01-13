# TicTacToe

A Graphical User Interface for two users to play two different versions of Tic Tac Toe.

## Description

* The main menu displays a welcome message and two buttons to play Tic Tac Toe or Number Tic Tac Toe
* After choosing the game that the user would like to play, the user is asked if they would like to load a user profile with the options Yes or No.
  * If the user choses to load a profile, their profile will show up as the Player One profile along with a default "player_two" profile that has no games played.
  * If the user choses not to load a profile, the Player One and Player Two profiles will be both set to their default profiles.
* Once the profiles are set up, the user is asked whether they would like to start a new game or load a previous game.
  * If they start a new game, a clear grid will appear.
  * If they load a previous game from a file of the form described in the Assignment Three rules, the grid will be set to the previous game.
* The user can then play the game.
  * In a regular tic tac toe game, the user can click on the space and their token will appear. Player One is always "X" and Player Two is always "O".
  * In a number tic tac toe game, the user must choose an option for a number at the bottom of the panel and then click on the location that they would like to input that number. If they click on the location first they will get a message to choose a number. Player One is always odd numbers and Player Two is always even numbers. An error message will appear if a player chooses a number that doesn't belong to them.
* Throughout the game, there is a button to choose to save and quit the game.
  * If clicked the user can choose to save their player profile and save their game.
* Whenever the game is not on the main menu, there is a menu bar with an option to go back to the main menu. This is the only way to go back. The other options in the menu bar are to start a new TicTacToe game without saving current progress and to start a new Number Tic Tac Toe game without saving current progress. If these are clicked the user will be re-prompted to load profiles and games.

## Getting Started

### Dependencies

* No dependencies used.

### Executing program

* Source files can be found in package "tictactoe" and package "boardgame"
* Build using gradle file and command "gradle build"
* To run the TextUI
    * Run with command "java -cp build/classes/java/main tictactoe.TextUI"
* To run the GUI
    * Run with command "java -jar build/libs/A3.jar"
    * Must be run in a non headless shell


## Limitations

* There is no file format or file existing checking.
* In the situation where the user loads a profile and then starts a new game, the game grid does not show up.
* In the situation where the user decides to play a Number Tic Tac Toe game, the options for the number inputs show up before the game starts (when the file loading prompts are occurring).
* There is no option to load or save a profile for Player Two but as the session is progressing, their status will be updated on the screen.

## Author Information

* Name: Isabella McIvor
* Email: imcivor@uoguelph.ca

## Development History

* 23/11/22
  created classes for tic tac toe game and grid, 
  created textUI, 
  created messages for gameState, 
* 24/11/22
  created NumTTTGrid, 
  started GUI, 
  created grid buttons for tic tac toe game, 
  created menu bar, 
  created player class, 
  incorporated switching games, 
* 25/11/22
  created main menu, 
  cleaned up TicTacToeView and NumTTTView by getting rid of duplicate code and having one extend the other, 
  created FileReader and FileWriter class, 
  created file choosing methods, 
  created file loading methods, 
  created file saving methods, 
* 12/11/22
  fixed minor bugs, 
  fixed checkstyle errors, 

## Acknowledgments

* CIS2430 Starter files:
    * November 21
      * TicTacToe.java
      * GameGrid.java
      * XOGrid.java
      * NumTTTGRid.java
      * TextUI.java
        * moodle.socs.uoguelph.ca
