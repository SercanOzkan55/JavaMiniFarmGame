package Sercan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// The KeyHandler class implements the KeyListener interface.
// It is responsible for handling keyboard input in the game.
// This class tracks which keys are pressed and released, and updates the game state accordingly.
public class KeyHandler implements KeyListener {

    // Boolean flags to track the state of movement keys:
    // These flags indicate whether a specific key is currently pressed.
    public boolean upPressed, downPressed, leftPressed, rightPressed, harvestPressed;

    // Boolean flags to track the state of crop-related keys:
    // These flags indicate whether a specific crop key (wheat, aubergine, corn) is pressed.
    public boolean wheatPressed, auberginePressed, cornPressed;

    // Reference to the GameLoop object:
    // This allows the KeyHandler to interact with the game logic and update the game state based on key inputs.
    private GameLoop gl;

    // Constructor:
    // The KeyHandler constructor takes a GameLoop object as a parameter.
    // This allows the KeyHandler to communicate with the game logic when keys are pressed or released.
    public KeyHandler(GameLoop gl) {
        this.gl = gl;
    }

    // keyTyped method:
    // This method is called when a key is typed (pressed and released).
    // It is not used in this implementation, so it is left empty.
    @Override
    public void keyTyped(KeyEvent e) {}

    // keyPressed method:
    // This method is called when a key is pressed.
    // It updates the corresponding boolean flags based on the key that was pressed.
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // Get the key code of the pressed key.

        // Movement keys:
        // Update the movement flags based on the key pressed.
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        // Crop-related keys:
        // Update the crop flags based on the key pressed.
        if (code == KeyEvent.VK_1) {
            wheatPressed = true;
            auberginePressed = false;
            cornPressed = false;
        }
        if (code == KeyEvent.VK_2) {
            auberginePressed = true;
            wheatPressed = false;
            cornPressed = false;
        }
        if (code == KeyEvent.VK_3) {
            cornPressed = true;
            wheatPressed = false;
            auberginePressed = false;
        }

        // Harvest key:
        // Set the harvestPressed flag when the 'H' key is pressed.
        if (code == KeyEvent.VK_H) {
            harvestPressed = true;
        }

        // Pause key:
        // Toggle the game's pause state when the 'P' key is pressed.
        if (code == KeyEvent.VK_P) {
            gl.togglePause();
        }

        // Quit key:
        // Exit the game when the 'Q' key is pressed.
        if (code == KeyEvent.VK_Q) {
            System.exit(0);
        }

        // Market interaction key:
        // Open the market window when the 'T' key is pressed, but only if the player is on the market.
        if (code == KeyEvent.VK_T) {
            if (gl.isOnMarket()) {
                new MarketWindow(gl); // Open the market window.        // Market interaction key:
        // Open the market window when the 'T' key is pressed, but only if the player is on the market.
            } else {
                System.out.println("Not on the market!");
            }
        }

        // Dialogue state handling:
        // If the game is in the dialogue state and the Enter key is pressed, return to the play state.
        if (gl.gameState == gl.dialogState && code == KeyEvent.VK_ENTER) {
            gl.gameState = gl.playState;
        }
    }

    // keyReleased method:
    // This method is called when a key is released.
    // It updates the corresponding boolean flags to indicate that the key is no longer pressed.
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); // Get the key code of the released key.

        // Movement keys:
        // Update the movement flags when the keys are released.
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        // Crop-related keys:
        // Update the crop flags when the keys are released.
        if (code == KeyEvent.VK_1) {
            wheatPressed = false;
        }
        if (code == KeyEvent.VK_2) {
            auberginePressed = false;
        }
        if (code == KeyEvent.VK_3) {
            cornPressed = false;
        }

        // Harvest key:
        // Reset the harvestPressed flag when the 'H' key is released.
        if (code == KeyEvent.VK_H) {
            harvestPressed = false;
        }
    }
}