package Sercan;

import java.awt.image.BufferedImage;

// The Tile class represents a tile in the game world. 
// A tile is a basic building block of the game world, typically used to represent the environment, like grass, walls, etc.
// This class holds properties for each tile, such as its name, whether it has collision properties, and its image.
public class Tile {
    
    // The name of the tile (e.g., "grass", "wall", "water").
    public String name;
    
    // A boolean value indicating whether this tile causes collision in the game (e.g., walls, obstacles).
    public boolean collision;
    
    // The image that represents this tile visually in the game world.
    public BufferedImage image;

}
