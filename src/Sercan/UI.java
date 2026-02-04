package Sercan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class UI {
    GameLoop gl;
    Graphics2D g2d;
    Font calibri_20;
    BufferedImage WomanUI;
    BufferedImage ManUI;
    BufferedImage invUI;
    public String gender;
    public String playerName;
    public String currentDialogue = "";
    public KeyHandler kh;
//First of all, we start with the constructor again. With the constructor here we handle the parameters of our character, 
    //we make our job easier as we automatically assign parameters such as gender and character name to class variables.
    public UI(GameLoop gl, String gender, String playerName) {
        this.gl = gl;
        this.gender = gender;
        this.playerName = playerName;
        calibri_20 = new Font("Calibri", Font.PLAIN, 20);
    }
//With this function, we assign the interface we provide to the screen so that the parts that should be seen by the user, 
//such as name, money, which contain the information of our characters, appear on the screen.
//We also transfer the user's inventory information to the user on the screen.
    public void draw(Graphics2D g2d) {
        g2d.setFont(calibri_20);
        g2d.setColor(Color.RED);

        // WomanUI veya ManUI'yi çiz
        if ("Woman".equals(gender)) {
            g2d.drawImage(womanUI(), gl.totalSize / 4, gl.totalSize / 4, 5 * gl.totalSize, 4 * gl.totalSize, null);
        } else {
            g2d.drawImage(manUI(), gl.totalSize / 4, gl.totalSize / 4, 5 * gl.totalSize, 4 * gl.totalSize, null);
        }

        // invUI'yi ekranın sol alt köşesine çiz
        int invUIWidth = 3 * gl.totalSize; // invUI genişliği = 96
        int invUIHeight = 1 * gl.totalSize; // invUI yüksekliği = 288
        int invUIX = gl.totalSize/2; 
        int invUIY = gl.screenHeight - invUIHeight*2; // Ekranın alt kenarına yapışık (576 - 288 = 288)

        g2d.drawImage(invUI(), invUIX, invUIY, invUIWidth, invUIHeight, null);
        
        Map<String, Integer> counts = gl.getInventoryCounts();
        int wheatCount = counts.get("Wheat");
        int aubergineCount = counts.get("Aubergine");
        int cornCount = counts.get("Corn");
        int total = wheatCount+aubergineCount+cornCount;

        // Oyuncu adını ve parasını çiz
        g2d.drawString(playerName, 135, 100);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.RED);
        g2d.drawString(" "+total, 160, 145);
        g2d.drawString(" " + gl.player.getMoney(), 100, 170);

        // Diyalog durumunu kontrol et
        if (gl.gameState == gl.dialogState) {
            NPC_Dialogue(g2d);
        }
    }
//In this function, we draw the contents of the player's inventory on the screen, that is, we transfer how many products the user has on the screen.    
    public void drawInventory(Graphics2D g2d, Map<String, Integer> inventory) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.red);

        int startX = 34; // Başlangıç X koordinatı
        int startY = gl.screenHeight - gl.totalSize * 2; // Başlangıç Y koordinatı (ekranın altına yakın)

        // Wheat için
        if (inventory.containsKey("Wheat")) {
            int wheatCount = inventory.get("Wheat");
            g2d.drawString(" " + wheatCount, startX, startY);
        }

        // Aubergine için
        if (inventory.containsKey("Aubergine")) {
            int aubergineCount = inventory.get("Aubergine");
            g2d.drawString(" " + aubergineCount, startX + 48, startY); // X koordinatını 100 piksel kaydır
        }

        // Corn için
        if (inventory.containsKey("Corn")) {
            int cornCount = inventory.get("Corn");
            g2d.drawString(" " + cornCount, startX + 96, startY); // X koordinatını 200 piksel kaydır
        }
    }
    
//With this function, the interface that should come according to the gender selected by the user is read from the file and the presence or absence of the 
//file is processed and used directly from the cache without reloading.    
    public BufferedImage womanUI() {
        if (WomanUI == null) {
            try {
                WomanUI = ImageIO.read(getClass().getResourceAsStream("/WomanUI.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return WomanUI;
    }

    public BufferedImage manUI() {
        if (ManUI == null) {
            try {
                ManUI = ImageIO.read(getClass().getResourceAsStream("/ManUI.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ManUI;
    }
    
    public BufferedImage invUI() {
        if (invUI == null) {
            try {
                invUI = ImageIO.read(getClass().getResourceAsStream("/invUI.png"));
                if (invUI == null) {
                    System.out.println("invUI resmi yüklenemedi!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return invUI;
    }
    
//The pause screen drawing is encapsulated in the stopScreen method. There is no access to the details of this process from the outside. The detail of
//how it is drawn is hidden, only what is done is in the foreground. For example, a pause screen is drawn when the method is called, but how this screen is drawn 
//is not explained to the outside, and we can use this method repeatedly in different places. We can do this by encapsulation method.
    public void stopScreen(Graphics2D g2d,GameLoop gl) {
    	
    	g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, gl.screenWidth, gl.screenHeight);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 32));

        String pausedMessage = "Oyun Duraklatıldı";
        int messageWidth = g2d.getFontMetrics().stringWidth(pausedMessage);
        int messageX = (gl.screenWidth - messageWidth) / 2;
        int messageY = gl.screenHeight / 2 - 50;
        g2d.drawString(pausedMessage, messageX, messageY);

        g2d.setFont(new Font("Arial", Font.PLAIN, 24));

        String continueMessage = "Devam Etmek için 'P' Tuşuna Basın";
        String quitMessage = "Çıkış için 'Q' Tuşuna Basın";
        int continueMessageWidth = g2d.getFontMetrics().stringWidth(continueMessage);
        int quitMessageWidth = g2d.getFontMetrics().stringWidth(quitMessage);

        int continueMessageX = (gl.screenWidth - continueMessageWidth) / 2;
        int quitMessageX = (gl.screenWidth - quitMessageWidth) / 2;
        int continueMessageY = gl.screenHeight / 2;
        int quitMessageY = gl.screenHeight / 2 + 50;

        g2d.drawString(continueMessage, continueMessageX, continueMessageY);
        g2d.drawString(quitMessage, quitMessageX, quitMessageY);
    	
    }
//The NPC_Dialogue and drawSubWindow methods function by hiding the dialog drawing details from the outside world. For example, the currentDialogue 
//variable is stored inside the class and not directly accessible outside. This preserves data integrity and simplifies the management of internal details.    
    public void NPC_Dialogue(Graphics2D g2d) {
        // Diyalog penceresini çiz
        int x = gl.totalSize * 2;
        int y = gl.totalSize / 2;
        int width = gl.screenWidth - (gl.totalSize * 4);
        int height = gl.totalSize * 4;

        drawSubWindow(g2d, x, y, width, height);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 25F));
        x += gl.totalSize / 2;
        y += gl.totalSize;

        for (String line : currentDialogue.split("\n")) {
            g2d.drawString(line, x, y);
            y += 40;
        }
    }
//The NPC_Dialogue method delegates the responsibility of how to draw the dialog to the drawSubWindow method. This hides the details of the drawing process 
//and only emphasizes “what is being done”. This results in a cleaner and more understandable code structure.
    public void drawSubWindow(Graphics2D g2d, int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

}
