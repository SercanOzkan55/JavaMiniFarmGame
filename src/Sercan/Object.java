package Sercan;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//We can call an object class a class that is the heir that transfers data to other classes. In this class
//, the image data to be transferred to the classes was processed first, in addition to this data, the points
//where they will be placed in the game world we have created, as well as the height and width data of these units were examined.
public class Object {
	
	public BufferedImage image;
	public String name;
	
	public boolean collision = false;
//In addition, classes of units have been assigned in order to provide collision feature with Rectangle.	
	public int worldX,worldY;
	public int width,height;
	
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public Rectangle solidArea = new Rectangle(0,0,48,48);

//Finally, the draw function calculates the coordinate of the object when the user approaches the calculated coordinate, 
//the image of this shape is drawn.	
	public void draw(Graphics g2d, GameLoop gl) {
        
		int screenX = worldX - gl.player.worldX + gl.player.screenX;
        int screenY = worldY - gl.player.worldY + gl.player.screenY;

        if (worldX + gl.totalSize > gl.player.worldX - gl.player.screenX &&
            worldX - gl.totalSize < gl.player.worldX + gl.player.screenX &&
            worldY + gl.totalSize > gl.player.worldY - gl.player.screenY &&
            worldY - gl.totalSize < gl.player.worldY + gl.player.screenY) {

            g2d.drawImage(image, screenX, screenY, width, height, null);
        }
	}

}
//In addition, although it does not see an encapsulation directly, the board class accesses this data by pulling the necessary
//ones from the object with tweezers, and assigns them to the constructor.