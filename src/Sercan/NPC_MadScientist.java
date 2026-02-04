package Sercan;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

//If we need to talk about NPCs in general, we can say the following. 
//We gave flexibility to our code by overriding the GameLoop class in our Entity class. 
//Because this class is the backbone of the game, we can access extra classes such as collisonchecker through this class.

public class NPC_MadScientist extends Entity{
	
	public NPC_MadScientist(GameLoop gl) {
		super(gl);
		direction="down";

		speed=1;
		
		madScientistImage();
		setDialogue();
	}
	
	
    public void madScientistImage() {
        try {
            up_1 = ImageIO.read(getClass().getResourceAsStream("/sön1.png"));
            up_2 = ImageIO.read(getClass().getResourceAsStream("/sön2.png"));
            down_1 = ImageIO.read(getClass().getResourceAsStream("/sarka1.png"));
            down_2 = ImageIO.read(getClass().getResourceAsStream("/sarka2.png"));
            left_1 = ImageIO.read(getClass().getResourceAsStream("/ssol1.png"));
            left_2 = ImageIO.read(getClass().getResourceAsStream("/ssol2.png"));
            right_1 = ImageIO.read(getClass().getResourceAsStream("/ssağ1.png"));
            right_2 = ImageIO.read(getClass().getResourceAsStream("/ssağ2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setDialogue() {
    	dialogues[0]="Hello, I'm DR.Mundo.";
    	dialogues[1]="I must be a good doctor. \n"
    			+"Patients never come back.";
    	dialogues[2]="The best cure for old pain? New pain.\n"
    			+ "Mundo goes wherever he wants!";
    	dialogues[3]="Press Enter the finish the conservation.";
    }
    
//We can say the following about this setAction function, the random variable we assign is an automatic process that determines
//the direction our character will go, it is done by the system independent of the user, but the most important part of this process 
//is actionLockCounter, this value is initially set to 0 and the fact that it is actually equal to 120 means that it is equal to 2, that 
//is, we say that every 2 seconds, change the direction or choose the direction as desired with this if statement, so where does 2 seconds mean,
//if we think it is 60 fps, we express that it is 2 seconds from 120/60.
    
    public void setAction() {
    	
    	actionLockCounter++;
    	
    	if(actionLockCounter == 120) {
        	
    		Random r = new Random();
        	int i = r.nextInt(100)+1;
        	
        	if(i<=25) {
        		direction = "up";	
        	}
        	if(i>25&&i<=50) {
        		direction="down";
        	}
        	if(i>50&&i<=75) {
        		direction="left";
        	}
        	if(i>75&&i<=100) {
        		direction="right";
        	}
        	actionLockCounter=0;
    	}

    }
    
    public void speak() {
    	super.speak();
    }

}
