package Sercan;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
// gl: A reference to the GameLoop instance, likely the main game loop where general game logic resides (like handling player movement and screen rendering).
// tile: An array of Tile objects, each representing a different type of tile in the game world (e.g., water, grass, walls, etc.).
// mapSize: A 2D array representing the layout of the game world map. 
// Each element holds a tile index from the tile array, defining the type of tile at each location in the world.

public class TileOperation {

    GameLoop gl;
    public Tile[] tile;
    public int[][] mapSize;
    
//    The constructor initializes the tile array with 90 Tile objects and
//    the mapSize array based on the GameLoop instance's maximum world columns and rows (gl.maxWorldColumn and gl.maxWorldRow).
//    It calls getTileImage() to load the images for the tiles and loadMap() to read and load the map from a file.

    public TileOperation(GameLoop gl) {
        this.gl = gl;
        tile = new Tile[90];
        mapSize = new int[gl.maxWorldColumn][gl.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
        	tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/water.png"));
            tile[0].collision=true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/1.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/2.png"));
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/3.png"));
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/4.png"));
            
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/5.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/6.png"));
            
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/7.png"));
            
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/8.png"));
            
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/9.png"));
            
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/10.png"));
                       
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/11.png"));
            
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/12.png"));
            
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/13.png")); 
            
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/14.png")); 
            
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/15.png")); 
            
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/16.png")); 
            
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/17.png")); 
            
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/kayacıl.png"));
            tile[18].collision=true;
            
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/duvar.png"));
            tile[19].collision=true;
            
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/20.png"));
            
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/21.png"));
            
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/22.png"));
            tile[22].collision=true;
            
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/23.png"));
            tile[23].collision=true;
            
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/k1.png"));
            
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/k2.png"));
            
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/k3.png"));
            
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/k4.png"));
            
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/k5.png"));
            
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/k6.png"));
            
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/k7.png"));
            
            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/k8.png"));
            
            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/k9.png"));
            
            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/k10.png"));
            
            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/k11.png"));
            
            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/k12.png"));
            
            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/24.png"));
            tile[36].collision=true;
            
            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/kuyu1.png"));
            tile[37].collision=true;
            
            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/kuyu2.png"));
            tile[38].collision=true;
            
            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/kuyu3.png"));
            tile[39].collision=true;
            
            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/kuyu4.png"));
            tile[40].collision=true;
            
            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/kuyu5.png"));
            tile[41].collision=true;
            
            tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getResourceAsStream("/kuyu6.png"));
            tile[42].collision=true;
            
            tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getResourceAsStream("/kuyu7.png"));
            tile[43].collision=true;
            
            tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("/kuyu8.png"));
            tile[44].collision=true;
            
            tile[45] = new Tile();
            tile[45].image = ImageIO.read(getClass().getResourceAsStream("/kuyu9.png"));
            tile[45].collision=true;
            
            tile[46] = new Tile();
            tile[46].image = ImageIO.read(getClass().getResourceAsStream("/p1.png"));
            
            tile[47] = new Tile();
            tile[47].image = ImageIO.read(getClass().getResourceAsStream("/p2.png"));
            
            tile[48] = new Tile();
            tile[48].image = ImageIO.read(getClass().getResourceAsStream("/p3.png"));
 
            tile[49] = new Tile();
            tile[49].image = ImageIO.read(getClass().getResourceAsStream("/fence.png"));
            tile[49].collision=true;
            
            tile[50] = new Tile();
            tile[50].image = ImageIO.read(getClass().getResourceAsStream("/path1.png"));
            
            tile[51] = new Tile();
            tile[51].image = ImageIO.read(getClass().getResourceAsStream("/path2.png"));
            
            tile[52] = new Tile();
            tile[52].image = ImageIO.read(getClass().getResourceAsStream("/cf1.png"));
            tile[52].collision=true;
            
            tile[53] = new Tile();
            tile[53].image = ImageIO.read(getClass().getResourceAsStream("/cf2.png"));
            tile[53].collision=true;
            
            tile[54] = new Tile();
            tile[54].image = ImageIO.read(getClass().getResourceAsStream("/cf3.png"));
            tile[54].collision=true;
            
            tile[55] = new Tile();
            tile[55].image = ImageIO.read(getClass().getResourceAsStream("/cf4.png"));
            tile[55].collision=true;
           
            tile[56] = new Tile();
            tile[56].image = ImageIO.read(getClass().getResourceAsStream("/cf5.png"));
            tile[56].collision=true;
            
            tile[57] = new Tile();
            tile[57].image = ImageIO.read(getClass().getResourceAsStream("/cf6.png"));
            tile[57].collision=true;
            
            tile[58] = new Tile();
            tile[58].image = ImageIO.read(getClass().getResourceAsStream("/cf7.png"));
            tile[58].collision=true;
           
            tile[59] = new Tile();
            tile[59].image = ImageIO.read(getClass().getResourceAsStream("/k_1.png"));
            
            tile[60] = new Tile();
            tile[60].image = ImageIO.read(getClass().getResourceAsStream("/k_2.png"));
            
            tile[61] = new Tile();
            tile[61].image = ImageIO.read(getClass().getResourceAsStream("/k_3.png"));
            
            tile[62] = new Tile();
            tile[62].image = ImageIO.read(getClass().getResourceAsStream("/k_4.png"));
            
            tile[63] = new Tile();
            tile[63].image = ImageIO.read(getClass().getResourceAsStream("/k_5.png"));
            
            tile[64] = new Tile();
            tile[64].image = ImageIO.read(getClass().getResourceAsStream("/k_6.png"));
            
            tile[65] = new Tile();
            tile[65].image = ImageIO.read(getClass().getResourceAsStream("/lily.png"));
            tile[65].collision=true;
            
            tile[66] = new Tile();
            tile[66].image = ImageIO.read(getClass().getResourceAsStream("/kök1.png"));
            tile[66].collision=true;
            
            tile[67] = new Tile();
            tile[67].image = ImageIO.read(getClass().getResourceAsStream("/kök2.png"));
            tile[67].collision=true;
            
            tile[68] = new Tile();
            tile[68].image = ImageIO.read(getClass().getResourceAsStream("/odun1.png"));
            tile[68].collision=true;
            
            tile[69] = new Tile();
            tile[69].image = ImageIO.read(getClass().getResourceAsStream("/odun2.png"));
            tile[69].collision=true;
           
            tile[70] = new Tile();
            tile[70].image = ImageIO.read(getClass().getResourceAsStream("/fence2.png"));
            tile[70].collision=true;
          
            tile[71] = new Tile();
            tile[71].image = ImageIO.read(getClass().getResourceAsStream("/fence3.png"));
            tile[71].collision=true;
     
            tile[72] = new Tile();
            tile[72].image = ImageIO.read(getClass().getResourceAsStream("/fence4.png"));
            tile[72].collision=true;
        
            tile[73] = new Tile();
            tile[73].image = ImageIO.read(getClass().getResourceAsStream("/fence5.png"));
            tile[73].collision=true;
      
            tile[74] = new Tile();
            tile[74].image = ImageIO.read(getClass().getResourceAsStream("/fence6.png"));
            tile[74].collision=true;

            tile[75] = new Tile();
            tile[75].image = ImageIO.read(getClass().getResourceAsStream("/fence7.png"));
            tile[75].collision=true;

            tile[76] = new Tile();
            tile[76].image = ImageIO.read(getClass().getResourceAsStream("/fence8.png"));
            tile[76].collision=true;

            tile[77] = new Tile();
            tile[77].image = ImageIO.read(getClass().getResourceAsStream("/fence9.png"));
            tile[77].collision=true;
            
            tile[78] = new Tile(); // Tarla
            tile[78].image = ImageIO.read(getClass().getResourceAsStream("/dirt.png"));
            tile[78].collision = false; // Tarla ekilebilir, çarpışma yok
            tile[78].name = "Field";
            
            tile[79] = new Tile(); // Tohum aşaması
            tile[79].image = ImageIO.read(getClass().getResourceAsStream("/wheat_seed.png")); // Buğday tohumu görseli

            tile[80] = new Tile(); // Filiz aşaması
            tile[80].image = ImageIO.read(getClass().getResourceAsStream("/wheat_sprout.png")); // Buğday filizi görseli

            tile[81] = new Tile(); // Olgun aşama
            tile[81].image = ImageIO.read(getClass().getResourceAsStream("/wheat_mature.png")); // Olgun buğday görseli
            
            tile[82] = new Tile(); // Aubergine tohumu
            tile[82].image = ImageIO.read(getClass().getResourceAsStream("/aubergine_seed.png"));
            tile[82].name = "Aubergine";

            tile[83] = new Tile(); // Aubergine filizi
            tile[83].image = ImageIO.read(getClass().getResourceAsStream("/aubergine_sprout.png"));
            tile[83].name = "Aubergine";

            tile[84] = new Tile(); // Olgun Aubergine
            tile[84].image = ImageIO.read(getClass().getResourceAsStream("/aubergine_mature.png"));
            tile[84].name = "Aubergine";

            tile[85] = new Tile(); // Corn tohumu
            tile[85].image = ImageIO.read(getClass().getResourceAsStream("/corn_seed.png"));
            tile[85].name = "Corn";

            tile[86] = new Tile(); // Corn filizi
            tile[86].image = ImageIO.read(getClass().getResourceAsStream("/corn_sprout.png"));
            tile[86].name = "Corn";

            tile[87] = new Tile(); // Olgun Corn
            tile[87].image = ImageIO.read(getClass().getResourceAsStream("/corn_mature.png"));
            tile[87].name = "Corn";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//  This method loads the map layout from a text file (worldmap.txt), which contains the indices of the tiles that define the world’s structure.
    public void loadMap() {
        try {
        	InputStream input = getClass().getResourceAsStream("/Sercan/worldmap.txt");
            if (input == null) {
                throw new Exception("Harita dosyası bulunamadı: worldmap.txt");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            int row = 0;

            while (row < gl.maxWorldRow) {
                String line = reader.readLine();
                if (line == null) break;
                String[] numbers = line.trim().split("\\s+");
                for (int col = 0; col < numbers.length && col < gl.maxWorldColumn; col++) {
                    mapSize[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Harita yüklenemedi: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
//    This method renders the game world by drawing tiles to the screen based on their type and position.
//    For each tile in the mapSize array, it calculates the screen position using the player's position and the size of the tiles.
//    It then uses Graphics2D.drawImage() to draw the corresponding tile image at the calculated position.

    public void draw(Graphics2D g2) {
        try {
            for (int row = 0; row < gl.maxWorldRow; row++) {
                for (int col = 0; col < gl.maxWorldColumn; col++) {
                    int tileNum = mapSize[col][row];

                    int worldX = col * gl.totalSize;
                    int worldY = row * gl.totalSize;
                    int screenX = worldX - gl.player.worldX + gl.player.screenX;
                    int screenY = worldY - gl.player.worldY + gl.player.screenY;

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gl.totalSize, gl.totalSize, null);
                }
            }      
        } catch (Exception e) {
            System.out.println("Error drawing tiles: " + e.getMessage());
            e.printStackTrace();
        }
    }

}