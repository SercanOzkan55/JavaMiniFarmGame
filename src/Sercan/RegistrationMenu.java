package Sercan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
//This code defines a simple registration menu for a game where players can input their name and select a gender. 
//Once the user clicks the start button, the input is validated, and the relevant game components are initialized. 
//The main functionality of this class is to set up the user interface and manage the player’s input.


public class RegistrationMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    // serialVersionUID is a unique identifier used for version control of the class.
    // It helps ensure that a loaded class matches the serialized object version.
    // It's especially important when serializing (saving to a byte stream) objects.

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegistrationMenu frame = new RegistrationMenu();
                frame.setVisible(true);// Making the window visible.
            } catch (Exception e) {
                e.printStackTrace();// If any exception occurs, print the error.

            }
        });
    }

    /**
     * Constructor for creating the RegistrationMenu window.
     * This method initializes the window, components, and appearance settings.
     */

    public RegistrationMenu() {
        setTitle("Registration Menu");
        // When the window is closed, the whole application should close as well.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the window's position at (400, 50) and its size to 720x720.
        setBounds(400, 50, 720, 720);

        
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background image for the panel. The image file is "karakterMenü.png".
                ImageIcon backgroundImageIcon = new ImageIcon(RegistrationMenu.class.getResource("/karakterMenü.png"));
                g.drawImage(backgroundImageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                // Drawing the image across the entire panel.
            }
        };
        // Set the layout of the panel to null (absolute positioning for components).
        panel.setLayout(null);
        
        // Set the content pane of the frame to this panel.
        setContentPane(panel);

        // Create a label for entering the player's name.

        JLabel lblName = new JLabel("Player's Name:");
        lblName.setForeground(new Color(64, 0, 128)); // Set the text color to purple.
        lblName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20)); // Set the font style.
        lblName.setBounds(279, 233, 131, 39); // Position and size the label.
        panel.add(lblName); // Add the label to the panel.

        // Create a text area where the player can enter their name.
        JTextArea text = new JTextArea();
        text.setBounds(281, 282, 129, 39); // Position and size the text area.
        text.setEditable(true); // Allow the user to edit the text area.
        text.setLineWrap(true); // Enable line wrapping for text.
        text.setWrapStyleWord(true); // Wrap at word boundaries instead of characters.
        panel.add(text); // Add the text area to the panel.

        // Create a label for selecting the player's gender.
        JLabel lblGender = new JLabel("Player's Gender:");
        lblGender.setForeground(new Color(64, 0, 128)); // Set the text color to purple.
        lblGender.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20)); // Set the font style.
        lblGender.setBounds(281, 366, 139, 46); // Position and size the label.
        panel.add(lblGender); // Add the label to the panel.

        // Create a combo box (dropdown menu) for selecting gender.
        String[] genderOptions = {"Select Gender", "Man", "Woman"}; // Gender options.
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions); // Create the dropdown menu.
        genderComboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20)); // Set the font style for the menu.
        genderComboBox.setBounds(279, 414, 131, 39); // Position and size the combo box.
        panel.add(genderComboBox); // Add the combo box to the panel.


        // Create a welcome message label.
        JLabel lblWelcome = new JLabel("WELCOME TO FARM GAME");
        lblWelcome.setFont(new Font("Segoe UI Black", Font.BOLD | Font.ITALIC, 26)); // Set the font style for the welcome message.
        lblWelcome.setBounds(163, 10, 379, 86); // Position and size the label.
        panel.add(lblWelcome); // Add the welcome message to the panel.

        // Create a start button with an icon.
        JButton startButton = new JButton();
        startButton.setIcon(new ImageIcon(RegistrationMenu.class.getResource("/start button.png"))); // Set an icon for the start button.
        startButton.setBounds(279, 554, 131, 56); // Position and size the button.
        panel.add(startButton); // Add the start button to the panel.


        startButton.addActionListener(e -> {
        	// Get the first word of the player's name entered.
        	String playerName = text.getText().trim().split("\\s+")[0];
            String genderSelection = (String) genderComboBox.getSelectedItem();

            if (playerName.isEmpty() || genderComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Please Fill The Empty Spaces", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                // Create necessary game components if the input is valid.
            	 GameLoop gameLoopPanel = new GameLoop(genderSelection,playerName);
            	 // Create the GameLoop object with selected details.
                 KeyHandler kHolder = new KeyHandler(gameLoopPanel);
                 Player player = new Player(gameLoopPanel, kHolder, genderSelection,playerName);

                dispose();
                MainGame mg = new MainGame(genderSelection,playerName);
                mg.setVisible(true);
                // Additional logic to start the game could be added here.
                panel.setVisible(false);
            }
        });
    }
}