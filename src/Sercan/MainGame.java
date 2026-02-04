package Sercan;

import java.awt.EventQueue;
import javax.swing.JFrame;

// The MainGame class is the entry point for the game's main window.
// It extends JFrame, which is a top-level container in Java Swing used to create a window.
// This class initializes the game window and starts the game loop.
public class MainGame extends JFrame {

    // GameLoop object:
    // This is the main game panel where the game logic and rendering take place.
    // The GameLoop class handles the game's main loop, updates, and rendering.
    GameLoop gameLoopPanel;

    // Constructor:
    // The MainGame constructor initializes the game window and sets up the GameLoop panel.
    // It takes two parameters: genderSelection (the player's chosen gender) and playerName (the player's name).
    public MainGame(String genderSelection, String playerName) {
        // Set the title of the game window.
        setTitle("Main Game");
        
        // Set the default close operation to exit the application when the window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the game window to 768x576 pixels.
        setSize(768, 576);
        
        // Center the window on the screen.
        setLocationRelativeTo(null);

        // Initialize the GameLoop panel with the player's gender and name.
        // The GameLoop class is responsible for managing the game's logic, updates, and rendering.
        gameLoopPanel = new GameLoop(genderSelection, playerName);
        
        // Add the GameLoop panel to the content pane of the JFrame.
        // This makes the GameLoop panel visible inside the game window.
        getContentPane().add(gameLoopPanel);

        // Set up the game by initializing objects, NPCs, and other game elements.
        gameLoopPanel.setupGame();
        
        // Start the game thread, which runs the game loop and updates the game state.
        gameLoopPanel.startGameThread();
    }

    // Main method:
    // This is the entry point of the application. It launches the game window.
    public static void main(String[] args) {
        // Use EventQueue.invokeLater to ensure that the GUI creation happens on the Event Dispatch Thread (EDT).
        // This is a best practice for Swing applications to avoid threading issues.
        EventQueue.invokeLater(() -> {
            try {
                // Default values for gender and player name.
                String genderSelection = "Man"; 
                String playerName = "Player";
                
                // Create an instance of MainGame with the default gender and player name.
                MainGame mg = new MainGame(genderSelection, playerName);
                
                // Make the game window visible.
                mg.setVisible(true);
            } catch (Exception e) {
                // If an exception occurs, print the stack trace for debugging.
                e.printStackTrace();
            }
        });
    }
}