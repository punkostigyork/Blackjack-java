/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a window to save the state of a Blackjack game.
 * Allows the user to input a unique game name to save the game state, including player and dealer information,
 * and the current deck configuration, into a file.
 */
public class SaveTheGame {
    private JFrame saveFrame;
    private JTextField gameNameField;
    private JButton saveButton;
    private Player player;
    private Dealer dealer;
    private Deck deck;

    /**
     * Constructs a window to save the state of a Blackjack game.
     * Initializes the window with fields to input the game name and a button to trigger the saving process.
     * @param player The player object representing the player's information.
     * @param dealer The dealer object representing the dealer's information.
     * @param deck The deck object representing the current state of the game deck.
     */
    public SaveTheGame(Player player, Dealer dealer, Deck deck) {
        this.player = player;
        this.dealer = dealer;
        this.deck = deck;

        saveFrame = new JFrame("Save Game");
        saveFrame.setSize(300, 200);
        saveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saveFrame.setLayout(new GridLayout(2, 1));
        saveFrame.setLocationRelativeTo(null);

        gameNameField = new JTextField();
        saveButton = new JButton("Save");

        saveFrame.add(gameNameField);
        saveFrame.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameName = gameNameField.getText();
                if (!gameName.isEmpty()) {
                    if (!checkExistingSaveID(gameName)) {
                        saveGame(gameName); // Call the method to save the game
                        saveFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "ID already exists, try another one!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a game name!");
                }
            }
        });

        saveFrame.setVisible(true);
    }

    /**
     * Saves the current state of the game into a file with a unique identifier provided by the user.
     * Checks for an existing identifier in the file and prompts the user if the provided identifier already exists.
     * @param gameName The identifier for the saved game.
     */
    private void saveGame(String gameName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("saved_game.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date now = new Date();

            String date = dateFormat.format(now);
            String time = timeFormat.format(now);
            String playerID = player.getName();
            double playerBalance = player.getBalance();
            int playerCardsNumber = player.getHand().getCards().size();
            String playerCards = cardsToString(player.getHand().getCards());
            double dealerBalance = dealer.getBalance();
            int dealerCardsNumber = dealer.getHand().getCards().size();
            String dealerCards = cardsToString(dealer.getHand().getCards());
            int deckCardNumber = deck.getDeck().size();
            String deckCards = cardsToString(deck.getDeck());

            String gameInfo = String.format("%s;%s;%s;%s;%.2f;%d;%s;%.2f;%d;%s;%d;%s\n",
                    gameName, date, time, playerID, playerBalance, playerCardsNumber, playerCards,
                    dealerBalance, dealerCardsNumber, dealerCards, deckCardNumber, deckCards);

            writer.write(gameInfo);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a provided save ID already exists in the saved game file.
     * @param saveID The identifier to be checked for existence.
     * @return True if the save ID already exists, False otherwise.
     */
    private boolean checkExistingSaveID(String saveID) {
        try (BufferedReader reader = new BufferedReader(new FileReader("saved_game.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length > 0 && tokens[0].equals(saveID)) {
                    return true; // SaveID already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // SaveID does not exist
    }

    /**
     * Converts a list of cards into a formatted string representation.
     * @param cards The list of Card objects to be converted.
     * @return A formatted string containing information about the provided cards.
     */
    private String cardsToString(java.util.List<Card> cards) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            builder.append(cards.get(i));
            if (i < cards.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
