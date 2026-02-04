package Sercan;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
//The MarketWindow class creates a window where the player can view and sell products from their inventory. 
//Each product has a button to sell it, and the player's money is updated accordingly. 
//If the player doesn't have any of the product in their inventory, an alert is shown. The layout is dynamic, so as new products are added,
//they will appear in a scrollable list. The window refreshes after a sale is made.

public class MarketWindow extends JFrame {

	private GameLoop gl;
	
    //MarketWindow(GameLoop gl): Constructor that takes an instance of GameLoop as an argument, which is needed to access game data such as the inventory.
    //setTitle("Market"): The title of the market window is set to "Market".
    //setSize(400, 300): The size of the window is set to 400 pixels wide and 300 pixels high.
    //setLocationRelativeTo(null): This centers the window on the screen.
    //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE): This ensures that when the market window is closed, the window is disposed of without affecting the game.
    //setLayout(new BorderLayout()): The layout manager is set to BorderLayout 

    public MarketWindow(GameLoop gl) {
        this.gl = gl;

        setTitle("Market");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //JPanel productPanel: A panel is created to hold the product information and buttons.
        //setLayout(new GridLayout(0, 1)): The layout is set to a GridLayout, which means each product will have its own row.
        //The number of rows is dynamic (0 rows, and the number of columns is set to 1).


        JLabel label = new JLabel("Market'e Hoş Geldiniz!", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 1));

        Map<String, Integer> inventory = gl.getInventory();
        //Map<String, Integer> inventory: The inventory map is obtained from the GameLoop instance.
        //It contains product names as keys and their quantities as values.

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String productName = entry.getKey();
            int productCount = entry.getValue();
            int productPrice = getProductPrice(productName);
            //  A new panel (productInfoPanel) is created to display the product name, its quantity (productCount), and the price (productPrice).
            //  FlowLayout(FlowLayout.LEFT): The flow layout ensures the components are left-aligned.

            JPanel productInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            productInfoPanel.add(new JLabel(productName + ": " + productCount + " adet - Fiyat: " + productPrice + " altın"));

            JButton sellButton = new JButton("Sat");
            sellButton.addActionListener(e -> sellProduct(productName, productPrice));
            // A JButton for selling the product is created with the label "Sat" (which means "Sell").
            // The button is given an ActionListener, so when clicked, it triggers the sellProduct() method, passing the product's name and price as arguments.

            // The product information panel and the sell button are added to the main product panel (productPanel).

            productPanel.add(productInfoPanel);
            productPanel.add(sellButton);
        }

        add(new JScrollPane(productPanel), BorderLayout.CENTER);

        setVisible(true);
    }
    //getProductPrice(): This method returns the price for a specific product based on its name.
    //If the product is not recognized, the default price is set to 0.

    private int getProductPrice(String productName) {
        switch (productName) {
            case "Wheat":
                return 10;
            case "Aubergine":
                return 15;
            case "Corn":
                return 12;
            default:
                return 0; 
        }
    }
    //sellProduct(): This method is called when a player clicks the "Sell" button for a product.
    //The inventory is checked for the product's count.
    //If the product count is greater than 0, the product is "sold," meaning the inventory count for that product is set to 0.
    //The player's money is updated by adding the total value of the sold products.
    //A message is displayed with the amount of gold earned from the sale.
    //The window is disposed of, and a new MarketWindow is created to update the display.

    private void sellProduct(String productName, int productPrice) {
        Map<String, Integer> inventory = gl.getInventory();
        int productCount = inventory.getOrDefault(productName, 0);

        if (productCount > 0) {
            inventory.put(productName, 0);
            gl.setInventory(inventory);

            gl.player.setMoney(gl.player.getMoney() + (productCount * productPrice));

            JOptionPane.showMessageDialog(this, productName + " satıldı! Kazanılan Altın: " + (productCount * productPrice));

            dispose();
            new MarketWindow(gl);
        } else {
            JOptionPane.showMessageDialog(this, "Envanterde " + productName + " yok!");
        }
    }
}
