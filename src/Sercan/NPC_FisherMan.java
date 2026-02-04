package Sercan;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
//If we need to talk about NPCs in general, we can say the following. 
//We gave flexibility to our code by overriding the GameLoop class in our Entity class. 
//Because this class is the backbone of the game, we can access extra classes such as collisonchecker through this class.
public class NPC_FisherMan extends Entity{
	
	public NPC_FisherMan(GameLoop gl) {
		super(gl);
		direction="down";

		speed=0;
		
		fishermanImage();
		setDialogue();
	}
//The setDialogue function is a generic class defined for NPCs and allows the characters to speak, and thanks to this
//code the speak function is enriched by polymorphizing the class in the entity class. It is possible to say the same
//logic for the update function, but here it updates these values to ensure that the character does not move.	
    public void fishermanImage() {
        try {
            up_1 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
            up_2 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
            down_1 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
            down_2 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
            left_1 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
            left_2 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
            right_1 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
            right_2 = ImageIO.read(getClass().getResourceAsStream("/fisher.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
        direction = "left";
        
        spriteCounter = 0;
        spriteNum = 1;
        
        collisionOn = true;
    }
    
    public void setDialogue() {
    	dialogues[0]="Hello, My name is Fishier.\n"
    			+  "You can sell your product them for money.";
    	dialogues[1]="I only fish.";
    	dialogues[2]="Nice to meet you.\n"
    			+" Press Enter to finish the conservation.";

    }

}
