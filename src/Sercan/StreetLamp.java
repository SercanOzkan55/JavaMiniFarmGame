package Sercan;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
//The streetlamp class has the same logic as the board.The Barreşl class is the class where the block, 
//picture or object to be placed is assigned to an object that is customized. In this class, 
//values are assigned to properties inherited from the object.This is an example of inheritance.
public class StreetLamp extends Object{
	
	public StreetLamp() {
		
		name = "Street Lamp";
        width = 96;
        height = 200;
        solidArea = new Rectangle(-24,-24,96,128);		
		try {
			
			image=ImageIO.read(getClass().getResourceAsStream("/lamp.png"));
		
		} catch(IOException e) {
			
			e.printStackTrace();
		
		}
		collision = true;
	}

}
