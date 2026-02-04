package Sercan;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
//The barrel class has the same logic as the board.The Barreşl class is the class where 
//the block, picture or object to be placed is assigned to an object that is customized. 
//In this class, values are assigned to properties inherited from the object.This is an example of inheritance.
public class Barrels extends Object{
	
	public Barrels() {
        name = "Barrel";
        width = 128;
        height = 48;
        solidArea = new Rectangle(0,0,128,48);	
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Barrels.png"));
        } catch (IOException e) {
            e.printStackTrace();
       }
        collision = true;

	}
}