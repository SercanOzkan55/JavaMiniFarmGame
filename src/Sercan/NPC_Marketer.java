package Sercan;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//If we need to talk about NPCs in general, we can say the following. 
//We gave flexibility to our code by overriding the GameLoop class in our Entity class. 
//Because this class is the backbone of the game, we can access extra classes such as collisonchecker through this class.
public class NPC_Marketer extends Entity{
	
	public NPC_Marketer(GameLoop gl) {
		super(gl);
		direction="down";

		speed=1;
		
		marketerImage();
		setDialogue();
	}
//The setDialogue function is a generic class defined for NPCs and allows the characters to speak, and thanks to this
//code the speak function is enriched by polymorphizing the class in the entity class. It is possible to say the same
//logic for the update function, but here it updates these values to ensure that the character does not move.	
    public void marketerImage() {
        try {
            up_1 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
            up_2 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
            down_1 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
            down_2 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
            left_1 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
            left_2 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
            right_1 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
            right_2 = ImageIO.read(getClass().getResourceAsStream("/marketer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setDialogue() {
    	dialogues[0]="Hello, My name is George. I am greengorecer.\n";
    	dialogues[1]="You can sell your product them for money.\n"
    			+"Press T to interact with the market";
    	dialogues[2]="I am nice to meet you.\n"
    			+"Press Enter the finish the conservation.";
    }
    
    public void update() {
        direction = "down";
        
        spriteCounter = 0;
        spriteNum = 1;
        
        collisionOn = true;
    }
    
    public void speak() {
    	super.speak();
    }

}
