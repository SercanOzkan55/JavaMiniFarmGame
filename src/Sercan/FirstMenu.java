package Sercan;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/*The FirstMenu class is a graphical user interface (GUI) for the initial screen of a farm-themed game. 
Its primary goal is to present a visually appealing interface, allow users to start the game, 
and display a countdown with instructions before transitioning to the next screen (RegistrationMenu).*/



//FirstMenu: Extends JFrame to create a main window.
//serialVersionUID: Ensures compatibility during object serialization.
//countdownText: Holds the text displayed during the countdown.

public class FirstMenu extends JFrame {
    private static final long serialVersionUID = 1L;
    private String countdownText = ""; // Initial text is empty

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FirstMenu frame = new FirstMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
//  Title and Window Properties:
//  Sets the window title to "Farm Game".
//	Configures dimensions and behavior on close.

//	Panel Initialization:
//	Uses a custom JPanel with an overridden paintComponent method to draw the background image and countdown text.

    public FirstMenu() {
        setTitle("Farm Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 617);
        getContentPane().setLayout(null);

        ImageIcon buttonIcon = new ImageIcon(FirstMenu.class.getResource("/start_Game.png"));

        JPanel panel = new JPanel() {
        	@Override
        	public void paintComponent(Graphics g) {
        	    super.paintComponent(g);
        	    ImageIcon backgroundImageIcon = new ImageIcon(FirstMenu.class.getResource("/farm.png"));
        	    g.drawImage(backgroundImageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);

        	    // If countdownText is not empty, draw it with a black background
        	    if (!countdownText.isEmpty()) {
        	        Graphics2D g2d = (Graphics2D) g;
        	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        	        // Set the font and calculate text dimensions
        	        g2d.setFont(new Font("Segoe UI", Font.BOLD, 30)); // Bigger font size
        	        String[] lines = countdownText.split("\n"); // Split the countdown text by newline character

        	        // Calculate the total text height and maximum line width
        	        FontMetrics fontMetrics = g2d.getFontMetrics();
        	        int textHeight = lines.length * fontMetrics.getHeight(); // Total height of all lines
        	        int maxLineWidth = getMaxLineWidth(lines, g2d); // Width of the longest line

        	        // Calculate padding for the background rectangle
        	        int padding = 15; // Padding around the text
        	        int backgroundWidth = maxLineWidth + 2 * padding; // Background width
        	        int backgroundHeight = textHeight + 2 * padding; // Background height

        	        // Calculate horizontal and vertical positions to center the text
        	        int xPos = (getWidth() - backgroundWidth) / 2; // Horizontally center
        	        int yPos = (getHeight() - backgroundHeight) / 2; // Vertically center

        	        // Draw a semi-transparent background rectangle behind the text
        	        g2d.setColor(new Color(0, 0, 0, 200));  // Semi-transparent black
        	        g2d.fillRoundRect(xPos, yPos, backgroundWidth, backgroundHeight, 25, 25);

        	        // Draw a white border around the rectangle
        	        g2d.setColor(Color.WHITE);
        	        g2d.setStroke(new BasicStroke(5));
        	        g2d.drawRoundRect(xPos, yPos, backgroundWidth, backgroundHeight, 25, 25);

        	        // Set color to white and draw each line of text
        	        g2d.setColor(Color.WHITE);
        	        int textY = yPos + padding + fontMetrics.getAscent(); // Starting Y position for the first line
        	        for (String line : lines) {
        	            // Calculate the X position to center each line horizontally
        	            int lineWidth = fontMetrics.stringWidth(line);
        	            int textX = xPos + (backgroundWidth - lineWidth) / 2; // Center each line
        	            g2d.drawString(line, textX, textY);
        	            textY += fontMetrics.getHeight(); // Move to the next line
        	        }
        	    }
        	}
        };
//      Create the Start button
//      A button is added to start the game.
//      When clicked, the button:
//      Becomes invisible.
//      Initiates the countdown (startCountdown()).

        panel.setBounds(0, -24, 1056, 580);
        getContentPane().add(panel);

        // Create the Start button
        JButton startButton = new JButton();
        panel.add(startButton);
        startButton.setIcon(buttonIcon);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);

        // Start button action to start the countdown directly
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setVisible(false); // Hide the start button when clicked
                startCountdown(); // Start the countdown directly
            }
        });

        // Resize button when window is resized
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeButton(startButton);
            }
        });
        resizeButton(startButton);
    }

    private void resizeButton(JButton startButton) {
        int windowWidth = getWidth();
        int windowHeight = getHeight();

        int buttonWidth = 350;
        int buttonHeight = 161;

        int newButtonX = (windowWidth - buttonWidth) / 2;
        int newButtonY = windowHeight - buttonHeight - 50;

        startButton.setBounds(newButtonX, newButtonY, buttonWidth, buttonHeight);
    }
//  Uses a Timer to update the countdown every second.
//  Displays gameplay instructions during the countdown.
//  Transitions to RegistrationMenu once the countdown ends.

    private void startCountdown() {
        final int[] countdown = {10}; // Start at 10 seconds

        Timer countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countdown[0] > 0) {
                    countdownText = countdown[0] + " seconds remaining...\n" +
                            "How To Play\n" +
                            "Controls: W, A, S, D\n" +
                            "Interaction with Market Place: Press T\n" +
                            "Planting with Num 1-2-3\n" +
                            "Harvesting with Key H\n" +
                            "Pause Game with Key P";
                    countdown[0]--;
                } else {
                    countdownText = "Game Starting...";
                    ((Timer) e.getSource()).stop();

                    // Open RegistrationMenu and close the current menu
                    RegistrationMenu rm = new RegistrationMenu();
                    rm.setVisible(true);
                    dispose(); // Close the current menu
                }

                // Repaint to update the countdown text
                repaint();
            }
        });
        countdownTimer.start();
    }

    // Method to calculate the maximum line width for centering text
    private int getMaxLineWidth(String[] lines, Graphics2D g2d) {
        int maxWidth = 0;
        for (String line : lines) {
            int lineWidth = g2d.getFontMetrics().stringWidth(line);
            if (lineWidth > maxWidth) {
                maxWidth = lineWidth;
            }
        }
        return maxWidth;
    }
}

/*  The FirstMenu class serves as a splash screen and tutorial for the game. 
It introduces the user to the controls and prepares them for
the gameplay experience while maintaining a visually engaging interface. */
