package Sercan;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;

// The RemainingTime class manages the countdown timer for the game.
// It tracks the total remaining time, updates it each frame, and formats the time to be displayed.
public class RemainingTime {

    public int totalTime = 5; // Initial time in seconds (1200 seconds = 20 minutes)
    public GameLoop gl; // Reference to the GameLoop object to interact with the game loop and game state

//      Constructor that initializes the RemainingTime class with a reference to the GameLoop.
//      gl The GameLoop object that controls the main game flow.
     
    public RemainingTime(GameLoop gl) {
        this.gl = gl;
    }

    
//      Formats the given time in seconds into a "MM:SS" format.
//      timeInSeconds The time in seconds to be formatted.
//    @return A string representing the formatted time (MM:SS).
     
    public String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60; // Get the minutes part of the time
        int seconds = timeInSeconds % 60; // Get the seconds part of the time
        return String.format("%02d:%02d", minutes, seconds); // Return the time in MM:SS format
    }

    
//      Updates the remaining time by reducing it by one second each time it is called.
//      If the time reaches zero, the game is stopped.
     
    public void update() {
        if (totalTime > 0) {
            totalTime--; // Decrease totalTime by 1 second if it's greater than zero
        } else {
            gl.stopGameThread(); // Stop the game loop if the time is up
            gl.endOfGame(); // Call the method to display the end of game screen
        }
    }

//      Draws the remaining time on the screen at a specified position.
//      This method uses the Graphics2D object to render the time on the screen.
//       g2d The Graphics2D object used for drawing on the screen.
     
    public void draw(Graphics2D g2d) {
        String timeText = formatTime(totalTime);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.WHITE);
        g2d.drawString(timeText, gl.screenWidth / 2 - 50, 50); // Ortada göster
    }
}