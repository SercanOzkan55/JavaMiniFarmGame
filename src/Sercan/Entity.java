package Sercan;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

class Entity {
	
	//First of all, the purpose of the entity class is this. It's a class that represents the player, 
	//enemy entities, or any character that roams around in the game. It's also a class that 
	//can handle properties common to all characters, as well as properties common to a specific group.
	
	//For all creatures, the values we assign as sprite counter, collision, given by direction names are 
	//common. These and such things are the building blocks or milestones that make up the mechanics of the characters.
	
	GameLoop gl;
    
	protected int worldX, worldY, speed;
    
    public BufferedImage up_1, up_2, down_1, down_2, left_1, left_2, right_1, right_2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    
    public boolean collisionOn = false;
    public int actionLockCounter=0;
    
    String dialogues[] = new String[10];
    int dialogueIndex=0;
    
    public Entity(GameLoop gl) {
    	this.gl=gl;
    }
    
    //Although the functions specified here with rectangle are explained in more detail in the collisionchecker class, 
    //to mention, they are the boundaries determined in a black rectangle style based on the dimensions of the character, 
    //if the two tangular rectangles, which we can call boundaries, coincide, the character's speed is as if it is struggling where it is 0.
    
    public void setAction() {}
    
    public void speak() {
    	if(dialogues[dialogueIndex]==null) {
    		dialogueIndex=0;
    	}
        gl.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    
    	
    	switch(gl.player.direction) {
    	case "up":
    		direction="down";
    		break;
    	case "down":
    		direction="up";
    		break;
    	case "left":
    		direction="right";
    		break;
    	case "right":
    		direction="left";
    		break;
    	}
    }
    
    public void update() {
    	setAction();
    	
    	collisionOn=false;
    	gl.checker.checkTile(this);
    	gl.checker.checkObject(this,false);
    	gl.checker.checkPlayer(this);
    	
        if (!collisionOn) {
            if (direction.equals("up")) worldY -= speed;
            if (direction.equals("down")) worldY += speed;
            if (direction.equals("left")) worldX -= speed;
            if (direction.equals("right")) worldX += speed;
        }
        
        spriteCounter++;

        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    
    //Functions such as update, action and speak are functions that will undergo character-specific changes, 
    //be squashed or polymorphized. These are actions that give characters their own dynamics and differentiate them from each other.
    
    public void draw(Graphics2D g2d) {
    	
    	BufferedImage image=null;
		
    	int screenX = worldX - gl.player.worldX + gl.player.screenX;
        int screenY = worldY - gl.player.worldY + gl.player.screenY;

        if (worldX + gl.totalSize > gl.player.worldX - gl.player.screenX &&
            worldX - gl.totalSize < gl.player.worldX + gl.player.screenX &&
            worldY + gl.totalSize > gl.player.worldY - gl.player.screenY &&
            worldY - gl.totalSize < gl.player.worldY + gl.player.screenY) {
        	
            switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up_1 : up_2;
                break;
            case "down":
                image = (spriteNum == 1) ? down_1 : down_2;
                break;
            case "right":
                image = (spriteNum == 1) ? right_1 : right_2;
                break;
            case "left":
                image = (spriteNum == 1) ? left_1 : left_2;
                break;
        }

            g2d.drawImage(image, screenX, screenY, gl.totalSize, gl.totalSize, null);
        }
    }
    //Finally, it is the function that handles the properties of the characters we want to appear on the screen, that is, the characters
    //we draw on the screen. The main purpose of this function is to check whether the character is on the screen or not, and if it is 
    //on the screen, to print the pictures on the screen in the directions we assign to the character.
}
