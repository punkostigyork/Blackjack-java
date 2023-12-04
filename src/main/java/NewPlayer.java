/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

/**
 * Represents a window for creating a new player profile.
 * Allows the user to input the player's name and starting balance,
 * then saves this information into a file.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Represents an interface to create a new player profile for a Blackjack game.
 * Enables users to input the player's name and starting balance, then saves this information into a file.
 * Provides validation for the input fields and displays appropriate messages upon successful submission or errors.
 */
public class NewPlayer {
    /**
     * The main window frame for the NewPlayer interface.
     */
    JFrame window;/**
     * Text field to input the player's name.
     */
    JTextField nameField;
    /**
     * Text field to input the starting balance for the player.
     */
    JTextField balanceField;

    /**
     * Constructs a new NewPlayer window where the user can input player information.
     * The window provides text fields for the player's name and starting balance,
     * along with a submit button to save the information.
     * Upon submission, the player's information is saved into a file named "player_info.txt".
     */
    public NewPlayer() {
        // Code to initialize the window and UI components
        window = new JFrame("NEW PLAYER");
        window.setSize(400, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Enter Player Name:");
        nameField = new JTextField();

        JLabel balanceLabel = new JLabel("Enter Starting Balance:");
        balanceField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        window.add(nameLabel);
        window.add(nameField);
        window.add(balanceLabel);
        window.add(balanceField);
        window.add(new JLabel()); // Empty label for layout purposes
        window.add(buttonPanel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePlayerInfo();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.dispose(); // Close the window when going back
                // Open the main menu
                SwingUtilities.invokeLater(Menu::new);
            }
        });

        window.setVisible(true);
    }

    /**
     * Saves the player information provided in the text fields into a file.
     * This method is called when the submit button is pressed.
     * It validates the input, saves the player's name and balance,
     * and shows appropriate messages in case of success or errors.
     */
    private void savePlayerInfo() {
        // Code to retrieve and validate player information, then save it to a file
        String playerName = nameField.getText();
        String balanceText = balanceField.getText();

        if (playerName.isEmpty() || balanceText.isEmpty()) {
            JOptionPane.showMessageDialog(window, "Please fill in all fields.");
            return;
        }

        try {
            double balance = Double.parseDouble(balanceText);

            // Save player info to a text file
            try (PrintWriter writer = new PrintWriter(new FileWriter("player_info.txt"))) {
                writer.println(playerName + ";" + String.format("%.2f", balance));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(window, "Error saving player information.");
            }

            JOptionPane.showMessageDialog(window, "Player information saved successfully.");
            window.dispose(); // Close the window after saving

            new Blackjack();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(window, "Please enter a valid number for balance.");
        }
    }

}
