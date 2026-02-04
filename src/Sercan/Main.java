package Sercan;

import javax.swing.JFrame;

// The Main class is the entry point of the application.
// It contains the main method, which is the starting point of the program.
// This class is responsible for launching the game by creating and displaying the FirstMenu (the initial menu of the game).
public class Main {

    // Main method:
    // This is the entry point of the application. When the program starts, this method is executed.
    public static void main(String[] args) {
        // Create an instance of the FirstMenu class.
        // The FirstMenu class represents the initial menu of the game, where the player can start the game or view instructions.
        FirstMenu fm = new FirstMenu();
        
        // Make the FirstMenu window visible.
        // The setVisible(true) method displays the window on the screen.
        fm.setVisible(true);
    }
}