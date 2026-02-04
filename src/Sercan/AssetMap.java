package Sercan;
//This AssetMap class is used to place objects and NPCs (in-game characters) at specific locations in the game world. 
//This class creates the map of the game and sets the starting positions of objects and NPCs.
public class AssetMap {

    GameLoop gl;
//By assigning gameloop to the constructor, it accesses the data in this class and allows the user to physically access 
//it while playing the game in the main game loop.
    public AssetMap(GameLoop gl) {
        this.gl = gl;
    }
//First of all, two different functions have been created for object and npcs, these functions separate groups from each other 
//for a common purpose.AssetMap has hidden this data in it(Encapsulation). In addition, the code is intended to be reusable by 
//defining NPCs or generations in one place.
    public void setObject() {

        gl.obj[0] = new Tree();
        gl.obj[0].worldX = 16 * gl.totalSize;
        gl.obj[0].worldY = 39 * gl.totalSize;

        gl.obj[1] = new Tree();
        gl.obj[1].worldX = 27 * gl.totalSize;
        gl.obj[1].worldY = 39 * gl.totalSize;

        gl.obj[2] = new Houses();
        gl.obj[2].worldX = 45 * gl.totalSize;
        gl.obj[2].worldY = 52 * gl.totalSize;

        gl.obj[3] = new StreetLamp();
        gl.obj[3].worldX = 16 * gl.totalSize;
        gl.obj[3].worldY = 45 * gl.totalSize;

        gl.obj[4] = new StreetLamp();
        gl.obj[4].worldX = 28 * gl.totalSize;
        gl.obj[4].worldY = 45 * gl.totalSize;
        
        gl.obj[5] = new Barrels();
        gl.obj[5].worldX = 50 * gl.totalSize;
        gl.obj[5].worldY = 54 * gl.totalSize;
        
        gl.obj[6] = new Board();
        gl.obj[6].worldX = 34 * gl.totalSize;
        gl.obj[6].worldY = 59 * gl.totalSize;
        
        gl.obj[7] = new Market();
        gl.obj[7].worldX = 31 * gl.totalSize;
        gl.obj[7].worldY = 53 * gl.totalSize;

    }
    
    public void setNPC() {
    	gl.npc[0]=new NPC_Marketer(gl);
    	gl.npc[0].worldX=gl.totalSize*35;
    	gl.npc[0].worldY=gl.totalSize*56;
    	
    	gl.npc[1]=new NPC_FisherMan(gl);
    	gl.npc[1].worldX=gl.totalSize*47;
    	gl.npc[1].worldY=gl.totalSize*42;
    	
    	gl.npc[2]=new NPC_Prayer(gl);
    	gl.npc[2].worldX=gl.totalSize*45;
    	gl.npc[2].worldY=gl.totalSize*61;
    	
    	gl.npc[3]=new NPC_MadScientist(gl);
    	gl.npc[3].worldX=gl.totalSize*55;
    	gl.npc[3].worldY=gl.totalSize*61;
    	
    	gl.npc[4]=new NPC_OLDMAN(gl);
    	gl.npc[4].worldX=gl.totalSize*28;
    	gl.npc[4].worldY=gl.totalSize*60;

    	gl.npc[5]=new NPC_Fiddlestick(gl);
    	gl.npc[5].worldX=gl.totalSize*51;
    	gl.npc[5].worldY=gl.totalSize*43;
    }
}
