package Sercan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
//First of all, we should characterize it as a large class that holds the dynamics of the game in GameLoop. In this class, the game cycle has been dynamized and created 
//with the elements of object-oriented programming such as encapsulation, inheritance or polymorphism.
public class GameLoop extends JPanel implements Runnable {
//Variables such as originalSize, scale, totalSize defined in the first phase, the block structure that makes up our game, or the particles of characters briefly represent 
//the size of the basic units of our game.
	
//In addition, the final assignment of these variables also provides control of the code in many respects, such as reliability, immutability and readability.
    final int originalSize = 16;
    final int scale = 3;
    final int totalSize = originalSize * scale;
//maxWorldColumn and Row represent the length of our world in 1 block.
    public final int maxWorldColumn = 85;
    public final int maxWorldRow = 85;
//screenRow and screenCol, on the other hand, represent the size of the screen that will appear on the screen, that is, they show us a perspective on a 4:3 scale.
    public final int screenRow = 12;
    public final int screenCol = 16;

    public final int screenWidth = totalSize * screenCol;
    public final int screenHeight = totalSize * screenRow;

    private boolean running = false;
    private boolean isPaused = false;
//gameState mechanics is a block that examines the state of the game. He has been appointed to provide in-game management. It is aimed to provide balance to the changing game conditions.
    public int gameState;
    public int playState = 1;
    public int stopState = 2;
    public int dialogState = 3;
    
	private boolean hasEnoughMoney;
	private int countdownTime;
//In this part of the code, a single object of each class's own element is created, and each class processes the data by taking its own parameters. The places specified as this mean that they create a reference in GameLoop.
    private List<Crop> crops = new ArrayList<>();
    private Map<String, Integer> inventory = new HashMap<>();

    Sound sound = new Sound();
    KeyHandler kHolder = new KeyHandler(this);

    public CollisionChecker checker;
    public UI ui;
    public TileOperation tileOp;
    public Player player;

    public AssetMap aMap;
    private RemainingTime remainingTimeWindow;
    public Entity[] npc = new Entity[10];
    public Object[] obj = new Object[10];

    Thread gameTh;
//The constructor here is automatically called when the object of these classes is created. It has been used to determine the condition of objects.
    public GameLoop(String genderSelection, String playerName) {
//This line allows the GameLoop object to receive keyboard inputs.
    	this.setFocusable(true);
//This line adds the kHolder object (an instance of the KeyHandler class) to this class as a keyboard listener (key listener).
        this.addKeyListener(kHolder);
//CollisionChecker is a class that performs collision control in the game.
        this.checker = new CollisionChecker(this);
//An object is created from the player class and assigned to the player variable.
        this.player = new Player(this, kHolder, genderSelection, playerName);
        this.ui = new UI(this, genderSelection, playerName);
        this.tileOp = new TileOperation(this);
        this.aMap = new AssetMap(this);
//This method makes the initial settings of the game.
//Prepares the initial state of a possible game (for example, initializes objects, sets variables).
        setupGame();
        remainingTimeWindow = new RemainingTime(this);
    }
//Here we have defined our getter-setter methods. We have defined these methods in order to have direct access to our character's inventory and money. 
    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public int getPlayerMoney() {
        return player.money;
    }

    public void setPlayerMoney(int money) {
        player.money = money;
    }
//The things we call in the function here are put in order to add the functions we have built in the previous classes into the game. It adds objects on 
//the map, game music, NPCs, and anything else that we can add more examples of as they are added to our map.
    public void setupGame() {
        aMap.setObject();
        playMusic(0);
        aMap.setNPC();
        gameState = playState;
    }
//This basic particle provides the flow of our game, that is, the main loop that makes the game work runs on this thread. If the game is not stopped, it 
//returns true to indicate that the game is flowing and in the function below it returns false to indicate that the game has stopped.
    public void startGameThread() {
        running = true;
        gameTh = new Thread(this);
        gameTh.start();
    }

    public void stopGameThread() {
        running = false;
        gameTh = null;
    }
//The function here tells the user whether the game has stopped or not. Returns true if the user has stopped the game, false if not. It also shows the
//change on the screen with repaint().
    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            System.out.println("Oyun Duraklatıldı.");
        } else {
            System.out.println("Oyun Devam Ediyor.");
        }
        repaint();
    }
//With the plantCrop() function, we access the data in other classes by using the values we define in the relevant class through the constructor in this function. 
    public void plantCrop() {
        if (kHolder.wheatPressed || kHolder.auberginePressed || kHolder.cornPressed) {
            int playerX = player.worldX / totalSize;
            int playerY = player.worldY / totalSize;

            if (playerX < 0 || playerY < 0 || playerX >= maxWorldColumn || playerY >= maxWorldRow) {
                System.out.println("Harita sınırlarının dışına çıkamazsınız!");
                return;
            }

//In addition, in this function, it examines the location of the character on the world and whether it is within the boundaries on the world, and the special planting authorization is assigned to the relevant area.   
            int tileNum = tileOp.mapSize[playerX][playerY];

            if (tileNum < 0 || tileNum >= tileOp.tile.length || tileOp.tile[tileNum] == null) {
                System.out.println("Geçersiz bir zemine ekim yapamazsınız!");
                return;
            }
//In this loop, the planted crop is marked in the relevant area and if there is a crop, it does not allow planting another crop.
            if ("Field".equals(tileOp.tile[tileNum].name)) {
                boolean alreadyPlanted = crops.stream().anyMatch(crop -> crop.x == playerX && crop.y == playerY);
                if (!alreadyPlanted) {
                    String cropType = null;

                    if (kHolder.wheatPressed) {
                        cropType = "Wheat";
                    } else if (kHolder.auberginePressed) {
                        cropType = "Aubergine";
                    } else if (kHolder.cornPressed) {
                        cropType = "Corn";
                    }

                    if (cropType != null) {
                        crops.add(new Crop(playerX, playerY, System.currentTimeMillis(), cropType));
                        System.out.println(cropType + " tohumu ekildi: " + playerX + ", " + playerY);
                    } else {
                        System.out.println("Geçerli bir ürün seçilmedi!");
                    }
                } else {
                    System.out.println("Bu alanda zaten bir ekin var!");
                }
            } else {
                System.out.println("Buraya tohum ekemezsiniz!");
            }
//Finally, when the crop is planted, it returns false and prepares for replanting in other regions.
            kHolder.wheatPressed = false;
            kHolder.auberginePressed = false;
            kHolder.cornPressed = false;
        }
    }
//The purpose of this function is to determine the suitability of the product for collection. The crop.isMature() 
//method hides how it calculates the ripening time of the crop. This is an example of abstraction and shortcuts like 
//hasNext and iterator keep the flow going. These check if there is an object in the queue and then use iterator to maintain this loop.
    public void harvestCrop() {
        if (kHolder.harvestPressed) {
            int playerX = player.worldX / totalSize;
            int playerY = player.worldY / totalSize;

            for (Iterator<Crop> iterator = crops.iterator(); iterator.hasNext(); ) {
                Crop crop = iterator.next();
                if (crop.x == playerX && crop.y == playerY && crop.isMature()) {
                    inventory.put(crop.name, inventory.getOrDefault(crop.name, 0) + 1);
                    iterator.remove();
                    System.out.println(crop.name + " hasat edildi!");
                    tileOp.mapSize[crop.x][crop.y] = 78; // 78, Field tile'ının ID'si
                    return;
                }
            }

            System.out.println("Hasat edilecek bir ekin yok!");
        }
    }
//In this function, the key lock compatible value named inventory that we created is a function that holds our product and the quantity 
//of our product. In this function, the product quantities in count are overridden and used differently for each product.   
    public Map<String, Integer> getInventoryCounts() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Wheat", inventory.getOrDefault("Wheat", 0));
        counts.put("Aubergine", inventory.getOrDefault("Aubergine", 0));
        counts.put("Corn", inventory.getOrDefault("Corn", 0));
        return counts;
    }
//In this function, a specific location is assigned to the area where the boundary areas of the market and the player's boundary areas
//intersect, and the user interacts with the market within this location.
    public boolean isOnMarket() {
        for (Object obj : this.obj) {
            if (obj != null && "Market".equals(obj.name)) {
                Rectangle playerArea = new Rectangle(player.worldX, player.worldY, player.solidArea.width, player.solidArea.height);
                Rectangle marketArea = new Rectangle(obj.worldX + obj.solidArea.x, obj.worldY + obj.solidArea.y, obj.solidArea.width, obj.solidArea.height);

                if (playerArea.intersects(marketArea)) {
                    return true;
                }
            }
        }
        return false;
    }
//First of all, to make more general comments about the run method, this method is a basic structure that allows the game loop to be constantly updated and drawn on the screen.

    @Override
    public void run() {
        double interval = 1000000000 / 60.0;
        double nextTime = System.nanoTime() + interval;
        int timeCounter = 0;
//It represents how many frames will appear on the screen in every 1 second. If we are in the game loop, it continues to generate frames and keeps the image flowing on the screen.
//This new frame generation continues as new view distances are opened in the game.

        while (running) {
            if (!isPaused) {
                updateChanges();
                timeCounter++;

                if (timeCounter >= 60) {
                    remainingTimeWindow.update();
                    timeCounter = 0;
                }

                repaint();
            }

            try {
                double remaining = nextTime - System.nanoTime();
                remaining = Math.max(remaining / 1000000, 0);
                Thread.sleep((long) remaining);
                nextTime += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//This updateChanges() method is used to update the state of the game. This method updates different components of the game (player, crops, NPCs) and performs different actions
//according to the current state of the game (gameState). 
    public void updateChanges() {
        if (gameState == playState) {
            player.updateChanges();
//Variables such as player, crops, npc, kHolder are encapsulated inside the GameLoop class. These variables are not directly accessible from outside; they are only used 
//inside the updateChanges() method.
            long currentTime = System.currentTimeMillis();
            for (Crop crop : crops) {
                crop.update(currentTime);
            }
//Methods such as player.updateChanges(), crop.update(currentTime), npcEntity.update() abstract complex operations. The internal details of these methods (for example, 
//how the player moves, how crops grow, how NPCs are updated) are hidden from the outside.
            if (kHolder.wheatPressed || kHolder.auberginePressed || kHolder.cornPressed) {
                plantCrop();
            }

            harvestCrop();

            for (Entity npcEntity : npc) {
                if (npcEntity != null) {
                    npcEntity.update();
                }
            }
        } else if (gameState == stopState) {
            // Duraklatma durumu
        }
    }
//endOfGame() performs a function that manages the end of the game. It plays different music depending on the player's money, pauses the game, starts a countdown 
//and allows the user to close the game by pressing Enter.    
    public void endOfGame() {
        int necessaryMoney = 0;
        hasEnoughMoney = player.getMoney() >= necessaryMoney;
//In case the game ends in different scenarios, it turns off the current music and gives new music according to the final state.
        if(hasEnoughMoney) {
        	stopMusic();
        	playMusic(1);
        }else {
        	stopMusic();
        	playMusic(2);
        }

        gameState = stopState; // Oyunu duraklat

        countdownTime = 10;

        javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
            countdownTime--; // Geri sayımı azalt

            // Ekranı yeniden çiz
            repaint();

            if (countdownTime <= 0) {
                ((javax.swing.Timer) e.getSource()).stop(); // Timer'ı durdur
                SwingUtilities.getWindowAncestor(GameLoop.this).dispose(); // Ana pencereyi kapat
            }
        });
        timer.start(); // Timer'ı başlat
//Finally, when the end screen arrives, a time is defined and at the end of this time, the game is closed by interacting with the enter key and the game ends.
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    timer.stop();
                    SwingUtilities.getWindowAncestor(GameLoop.this).dispose();
                }
            }
        });

        this.repaint();
    }
//This paintComponent(Graphics g) method draws the visual content of the game on the screen. This method draws the game's background, crops, objects, NPCs, 
//player and user interface (UI). It also draws different content according to the different states of the game (e.g. game in progress, paused, in dialog state, endgame).
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//Methods like tileOp.draw(g2d), crop.getTileID(), obj.draw(g2d, this), npc.draw(g2d), ui.draw(g2d) abstract complex operations. The internal details of these methods 
//(e.g. how the map is drawn, how crops are drawn, how NPCs are drawn) are hidden from the outside.

        tileOp.draw(g2d);

        for (Crop crop : crops) {
            int screenX = crop.x * totalSize - player.worldX + player.screenX;
            int screenY = crop.y * totalSize - player.worldY + player.screenY;

            int tileID = crop.getTileID();
            if (tileID >= 0 && tileID < tileOp.tile.length && tileOp.tile[tileID] != null) {
                g2d.drawImage(tileOp.tile[tileID].image, screenX, screenY, totalSize, totalSize, null);
            }
        }
//Methods like obj.draw(g2d, this), npc.draw(g2d), player.draw(g2d) are used repeatedly in different functions or classes. As a result, we can see it as an example of polymorphism.
//Also, as mentioned in the function above, values are encapsulated.        
        for (Object obj : this.obj) {
            if (obj != null) {
                obj.draw(g2d, this);
            }
        }

        for (Entity npc : this.npc) {
            if (npc != null) {
                npc.draw(g2d);
            }
        }

        ui.draw(g2d);
        ui.drawInventory(g2d, inventory);
        
        if (gameState == playState || gameState == dialogState) {
            player.draw(g2d);
        }

        if (isPaused) {
            ui.stopScreen(g2d, this);
        }

        remainingTimeWindow.draw(g2d);
//The order in which class items are called is also very important here. Because if the order of one of the elements in the visual order changes,
//for example, if stopScreen and player.draw are switched, then when we stop the game, it will be as if the player object takes precedence over this object, and this is the reason why drawing
//the map should be prioritized because it should be the thing that will cover all of them at the bottom.
        if (gameState == stopState) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());
//This is the order of the items that will appear on the screen at the end of the game. Operations such as comparing the player's money were done in the previous function. As the last of these 
//operations, this part in this function is handled and the images of the player winning or losing are transferred to the screen and the remaining time is shown to the user.
            try {
            	BufferedImage image = ImageIO.read(new File(hasEnoughMoney ? "Images/win.jpg" : "Images/lose.jpg"));
            	int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();

                double aspectRatio = (double) imgWidth / imgHeight;
                int newHeight = getHeight();
                int newWidth = (int) (newHeight * aspectRatio);

                int x = (getWidth() - newWidth) / 2;
                int y = 0;
                g2d.drawImage(image, x, y, newWidth, newHeight, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            FontMetrics metrics = g2d.getFontMetrics(); 
            String text = "Remaining Time to Close: " + countdownTime + " second";
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();

            int textX = (getWidth() - textWidth) / 2;
            int textY = getHeight() - textHeight - 20;
            g2d.setColor(Color.WHITE);
            g2d.drawString(text, textX, textY);
        }


        if (gameState == dialogState) {
            ui.NPC_Dialogue(g2d);
        }

        g2d.dispose();
    }
//Here, events such as music playing, looping or stopping are discussed.
    public void playMusic(int i) {
        if (sound.clip != null && sound.clip.isRunning()) {
            return;
        }

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

}