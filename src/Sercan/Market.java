package Sercan;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

// The Market class represents the "Market" object in the game world.
// This class inherits from the Object class (inheritance), which contains the basic properties and behaviors for all objects in the game.
// The Market class extends the Object class to add specific properties and behaviors for the market object.
public class Market extends Object {

    // Constructor:
    // This constructor is called when a Market object is created.
    // It initializes the market object with default values and loads its image.
    public Market() {
        // Set the name of the market object to "Market".
        name = "Market";
        
        // Set the width and height of the market object.
        width = 200; 
        height = 200;
        
        // Define the collision area (solidArea) for the market object.
        // The Rectangle class is used to represent the collision area, which is used for collision detection with the player or other objects.
        solidArea = new Rectangle(0, 0, 175, 175);

        // Load the image for the market object.
        // The image is read from the "/market.png" file and stored as a BufferedImage.
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/market.png"));
        } catch (IOException e) {
            // If an error occurs while loading the image, print the stack trace.
            e.printStackTrace();
        }

        // Enable collision for the market object.
        // This means the player can collide with the market object.
        collision = true;
    }
}