package Sercan;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
//Player class is the class that we control ourselves. This class allows us to interact with the functions we create in the GameLoop or with the classes 
//included in the GameLoop. It also keeps the dynamics of our code alive by overwriting methods such as character control and collision between blocks. 
//The reason why GameLoop is called 2 times here is that the purpose of the two is different, the one specified with this only covers the player class, 
//but the one with super covers the entire entity class. With SolidArea we define the collision area and with the functions we call in it we assign our character to the game screen.
public class Player extends Entity {

    GameLoop gLoop;
    KeyHandler kHolder;
    CollisionChecker checker;
    public String playerName;
    public int money = 0; // Başlangıçta 0 altın

    public final int screenX;
    public final int screenY;

    public Player(GameLoop gLoop, KeyHandler kHolder, String gender,String playerName) {
        super(gLoop);
        this.gLoop = gLoop;
        this.kHolder = kHolder;
        this.checker=gLoop.checker;
        this.playerName=playerName;

        screenX = gLoop.screenWidth / 2 - gLoop.totalSize / 2;
        screenY = gLoop.screenHeight / 2 - gLoop.totalSize / 2;
        
        solidArea = new Rectangle(0,0,gLoop.totalSize,gLoop.totalSize);
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        charMovementValues();
        initializeCharacter(gender);


    }
//It has been created to appear according to the gender of the character that will appear on the screen.    
    private void initializeCharacter(String gender) {
        charMovementValues();
        if ("Man".equals(gender)) {
            manCharacter();
        } else {
            womanCharacter();
        }
    }
    
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
//It represents the square in which the character will be spawned.It also specifies the character's speed and direction by default at startup.

    public void charMovementValues() {
        worldX = gLoop.totalSize * 30;
        worldY = gLoop.totalSize * 60;
        speed = 10;
        direction = "down";
    }
//The event here is that the commands received from the keyboard are pulled from the KeyHandler class and the direction of the character is decided 
//thanks to the data returned, and according to this direction, the character is processed depending on the speed of the direction the character will go. 
//The value 12 in the place called SpriteCounter and actively values like 1,2 represent this. Every 0.2 seconds from 12/60 the character cycles between the 
//numbers 1-2, this cycle is important to add walking mechanics to the character.
    public void updateChanges() {
        if (kHolder.upPressed || kHolder.downPressed || kHolder.leftPressed || kHolder.rightPressed) {
            if (kHolder.upPressed) {
                direction = "up";
            } else if (kHolder.downPressed) {
                direction = "down";
            } else if (kHolder.leftPressed) {
                direction = "left";
            } else if (kHolder.rightPressed) {
                direction = "right";
            }

            // Çarpışma kontrolü
            collisionOn = false;
            gLoop.checker.checkTile(this);
            int objIndex = gLoop.checker.checkObject(this, true);
            
            int npcIndex = gl.checker.checkNPC(this, gl.npc);
            interactNPC(npcIndex);
            
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
    }
//This 999 value is used to represent whether there is a collision with the character, and as a result of this collision, it interacts with the character and displays the output visually.
    public void interactNPC(int i) {
    	if(i != 999) {
    		gl.gameState = gl.dialogState;
    		gl.npc[i].speak();
    	}
    }
//Determines the order of the images sent to the screen and the order of the images for the motion dynamics of the selected characters. 
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

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

        g2d.drawImage(image, screenX, screenY, gLoop.totalSize, gLoop.totalSize, null);
    }

    //As with other NPCs, they are the character's directional outputs.
    public void manCharacter() {
        try {
            up_1 = ImageIO.read(getClass().getResourceAsStream("/arka1.png"));
            up_2 = ImageIO.read(getClass().getResourceAsStream("/arka2.png"));
            down_1 = ImageIO.read(getClass().getResourceAsStream("/ön1.png"));
            down_2 = ImageIO.read(getClass().getResourceAsStream("/ön2.png"));
            left_1 = ImageIO.read(getClass().getResourceAsStream("/sol1.png"));
            left_2 = ImageIO.read(getClass().getResourceAsStream("/sol2.png"));
            right_1 = ImageIO.read(getClass().getResourceAsStream("/sağ1.png"));
            right_2 = ImageIO.read(getClass().getResourceAsStream("/sağ2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void womanCharacter() {
        try {
            up_1 = ImageIO.read(getClass().getResourceAsStream("/woman_up_1.png"));
            up_2 = ImageIO.read(getClass().getResourceAsStream("/woman_up_2.png"));
            down_1 = ImageIO.read(getClass().getResourceAsStream("/woman_down_1.png"));
            down_2 = ImageIO.read(getClass().getResourceAsStream("/woman_down_2.png"));
            right_1 = ImageIO.read(getClass().getResourceAsStream("/woman_right_1.png"));
            right_2 = ImageIO.read(getClass().getResourceAsStream("/woman_right_2.png"));
            left_1 = ImageIO.read(getClass().getResourceAsStream("/woman_left_1.png"));
            left_2 = ImageIO.read(getClass().getResourceAsStream("/woman_left_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
