/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a window for game settings.
 * This class provides a graphical interface allowing users to manage game settings,
 * such as returning to the main menu or exiting the application.
 * It contains buttons to return to the main menu and exit the game.
 */

public class Settings {
    private JFrame frame;

    /**
     * Constructs a Settings window for managing game settings.
     * Initializes a window with buttons for returning to the main menu and exiting the game.
     *
     * @param player The player object associated with the game.
     * @param dealer The dealer object associated with the game.
     * @param deck   The deck object associated with the game.
     */
    public Settings(Player player, Dealer dealer, Deck deck, JFrame blackjackFrame, JFrame balanaceManagerFrame) {
        frame = new JFrame("Settings");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JButton returnButton = new JButton("RETURN");
        JButton saveButton = new JButton("SAVE");
        JButton exitButton = new JButton("EXIT");

        frame.add(returnButton);
        frame.add(saveButton);
        frame.add(exitButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle RETURN button click event
                frame.dispose(); // Close the Settings window
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveTheGame saveGameWindow = new SaveTheGame(player, dealer, deck);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle EXIT button click event
                SwingUtilities.invokeLater(Menu::new);
                blackjackFrame.dispose();
                balanaceManagerFrame.dispose();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
