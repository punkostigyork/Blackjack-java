/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.lang.Thread;


/**
 * Represents the main controller class for a Blackjack game application.
 * Manages game logic, player interactions, and the graphical user interface.
 */
public class Blackjack {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private final BalanceManager balanceManager;

    /**
     * Constructs a Blackjack game and initializes the game window and components.
     * Reads player information, sets up the graphical interface, and defines
     * action listeners for game buttons.
     */
    public Blackjack() {
        startTheGame();

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(40, 52, 44));
        frame.add(gamePanel);

        hit.setFocusable(false);
        buttonPanel.add(hit);
        stand.setFocusable(false);
        buttonPanel.add(stand);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    player.getHand().addCard(deck.draw());
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                if(player.getHand().isBusted())
                    hit.setEnabled(false);
                gamePanel.repaint();
            }
        });

        // Add a button for SETTINGS
        JButton settingsButton = new JButton("SETTINGS");
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Aligns the button to the right
        settingsPanel.add(settingsButton);
        frame.add(settingsPanel, BorderLayout.NORTH);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings(player, dealer, deck, frame, balanceManager.getFrame());
            }
        });

        stand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hit.setEnabled(false);
                stand.setEnabled(false);

                if (!player.getHand().isBusted()) {
                    while(dealer.getHand().getHandValue() < 17) {
                        try {
                            dealer.getHand().addCard(deck.draw());
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                gamePanel.repaint();

                final boolean didPlayerWin = dealer.getHand().isBusted() || (
                        !player.getHand().isBusted()
                        && player.getHand().getHandValue() > dealer.getHand().getHandValue()
                );
                final boolean didPlayerLose = player.getHand().isBusted() || (
                        !dealer.getHand().isBusted()
                        && player.getHand().getHandValue() < dealer.getHand().getHandValue()
                );

                double newPlayerBalance = player.getBalance();
                double newDealerBalance = dealer.getBalance();

                if (didPlayerLose) {
                    newPlayerBalance -= balanceManager.getAddedAmount();
                    newDealerBalance += balanceManager.getAddedAmount();
                }
                if (didPlayerWin) {
                    newPlayerBalance += balanceManager.getAddedAmount();
                    newDealerBalance -= balanceManager.getAddedAmount();
                }

                player.setBalance(newPlayerBalance);
                dealer.setBalance(newDealerBalance);

                System.out.println("Did player lose: " + didPlayerLose);
                System.out.println("Did player win: " + didPlayerWin);
                System.out.println("Player balance: " + newPlayerBalance + " dealer balance: " + newDealerBalance);

                balanceManager.updateBalances(newPlayerBalance, newDealerBalance);

                String message = "";

                if (newPlayerBalance > 0 && newDealerBalance > 0) {

                    SwingUtilities.invokeLater(() -> {

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                        deck = new Deck();
                        deck.shuffle();

                        player.getHand().clearHand();
                        dealer.getHand().clearHand();

                        try {
                            player.getHand().addCard(deck.draw());
                            player.getHand().addCard(deck.draw());

                            dealer.getHand().addCard(deck.draw());
                            dealer.getHand().addCard(deck.draw());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                        hit.setEnabled(true);
                        stand.setEnabled(true);

                        frame.repaint();
                    });
                } else {
                    if(newPlayerBalance > 0) {
                        try (PrintWriter writer = new PrintWriter(new FileWriter("ranking.txt", true))) {
                            writer.println(player.getName() + ";" + newPlayerBalance + ";" + "WIN");
                            message = "Congratulations, you won!";
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try (PrintWriter writer = new PrintWriter(new FileWriter("ranking.txt", true))) {
                            writer.println(player.getName() + ";" + -newDealerBalance + ";" + "LOST");
                            message = "We're sorry, you lost!";
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    EndOfGame endOfGame = new EndOfGame(message);
                    frame.dispose();
                    balanceManager.getFrame().dispose();
                }
            }
        });

        this.balanceManager = new BalanceManager(player.getBalance(), dealer.getBalance());
    }

    //WINDOW
    int boardWidth = 800;
    int boardHeight = 600;
    int cardWidth = 110;
    int cardHeight = 154;

    JFrame frame = new JFrame("Blackjack");
    JPanel gamePanel = new JPanel() {
        /**
         * Custom painting method for rendering graphical elements on the screen.
         * Draws cards for the dealer and player, manages game state rendering.
         *
         * @param gr The Graphics context for rendering
         */
        @Override
        public void paintComponent(Graphics gr) {
            super.paintComponent(gr);

            try {
                //draw hidden card
                for (int i = 0; i < dealer.getHand().getCards().size(); i++) {
                    if (stand.isEnabled() && i == 0) { // Show the hidden card if 'stay' is clicked
                        System.out.println("Hidden Card Path: " + "./cards/BACK.png");
                        Image hiddenImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./cards/BACK.png"))).getImage();
                        gr.drawImage(hiddenImage, 130, 20, cardWidth, cardHeight, null);
                    } else {
                        Card card = dealer.getHand().getCards().get(i);
                        System.out.println("Card Path: " + card.getImagePath());
                        Image cardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(card.getImagePath()))).getImage();
                        gr.drawImage(cardImage, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
                    }
                }

                //draw player's hand
                for (int i = 0; i < player.getHand().getCards().size(); i++) {
                    Card card = player.getHand().getCards().get(i);
                    System.out.println("Card Path: " + card.getImagePath());
                    Image cardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(card.getImagePath()))).getImage();
                    gr.drawImage(cardImage, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
                }

                if(!stand.isEnabled()) {
                    System.out.println("STAND: ");
                    System.out.println(dealer.getHand().getHandValue());
                    System.out.println(player.getHand().getHandValue());

                    String message = "";
                    if(player.getHand().isBusted())
                        message = "YOU LOSE.";
                    else if(dealer.getHand().isBusted())
                        message = "YOU WIN.";
                    else if(player.getHand().getHandValue() == dealer.getHand().getHandValue())
                        message = "TIE.";
                    else if(player.getHand().getHandValue() > dealer.getHand().getHandValue())
                        message = "YOU WIN.";
                    else if(player.getHand().getHandValue() < dealer.getHand().getHandValue())
                        message = "YOU LOSE.";

                    gr.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                    gr.setColor(Color.white);
                    gr.drawString(message, 293, 250);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    };
    JPanel buttonPanel = new JPanel();
    JButton hit = new JButton("HIT");
    JButton stand = new JButton("STAND");


    /**
     * Starts the game by initializing player information, creating a deck,
     * shuffling it, and dealing cards to the player and dealer.
     */
    private void startTheGame() {
        // Read player information from player_info.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("player_info.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    double playerBalance = Double.parseDouble(parts[1]);
                    player = new Player(playerName, playerBalance);
                    dealer = new Dealer(playerBalance);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        //We have to initialize the deck and shuffle it
        deck = new Deck();
        deck.shuffle();

        if (player == null)
            player = new Player("DEFAULT NAME", 1000);
        if (dealer == null)
            dealer = new Dealer(1000);

        try {
            for(int i = 0; i < 2; i++)
                player.getHand().addCard(deck.draw());
            for(int i = 0; i < 2; i++)
                dealer.getHand().addCard(deck.draw());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Card hiddenCard = dealer.getHand().getCards().get(0);

        System.out.println("DEALER: ");
        System.out.println(hiddenCard);
        System.out.println(dealer.getHand().getCards());
        System.out.println(dealer.getHand().getHandValue());

        System.out.println("PLAYER: ");
        System.out.println(player.getHand().getCards());
        System.out.println(player.getHand().getHandValue());
    }
}
