package Sercan;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
//The Board class is the class where the block, picture or object to be placed is assigned to an 
//object that is customized. In this class, values are assigned to properties inherited from the object.
//This is an example of inheritance.

public class Board extends Object{
	
	public Board() {
        name = "Board";
        width = 120;
        height = 96;
        solidArea = new Rectangle(0,0,96,96);	
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

	}
}
