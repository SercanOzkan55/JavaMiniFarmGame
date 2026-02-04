package Sercan;

import java.io.IOException;

import javax.imageio.ImageIO;
//If we need to talk about NPCs in general, we can say the following. 
//We gave flexibility to our code by overriding the GameLoop class in our Entity class. 
//Because this class is the backbone of the game, we can access extra classes such as collisonchecker through this class.
public class NPC_Fiddlestick extends Entity{
	
	public NPC_Fiddlestick(GameLoop gl) {
		super(gl);
		direction="down";
		speed=1;
		
		fiddlestickImage();
		setDialogue();
	}
	
//The setDialogue function is a generic class defined for NPCs and allows the characters to speak, and thanks to this
//code the speak function is enriched by polymorphizing the class in the entity class. It is possible to say the same
//logic for the update function, but here it updates these values to ensure that the character does not move.
	
    public void fiddlestickImage() {
        try {
            up_1 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
            up_2 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
            down_1 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
            down_2 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
            left_1 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
            left_2 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
            right_1 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
            right_2 = ImageIO.read(getClass().getResourceAsStream("/fiddlestick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setDialogue() {
    	dialogues[0]="Don't be scared. It can't hurt you.\n"
    			+"Who's out there? Show yourself.\n"
    			+"Woowwoowh! Did I scare you? Hihiha";
    	dialogues[1]="Press 1,2,3 to sow seeds and H to collect\n"
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
