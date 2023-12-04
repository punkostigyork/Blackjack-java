/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

/**
 * Represents a player in a Blackjack game.
 * Manages the player's name, balance, and hand of cards.
 * Provides methods to access and modify player information such as retrieving the name and balance,
 * setting a new balance, accessing the player's hand, and clearing the hand.
 */
public class Player {
    private String name;
    private double balance;
    private Hand hand;

    /**
     * Constructs a new Player with the specified name and balance.
     * Initializes an empty hand for the player.
     *
     * @param name     The name of the player.
     * @param balance  The initial balance of the player.
     */
    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.hand = new Hand();
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the current balance of the player.
     *
     * @return The current balance of the player.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets a new balance for the player.
     *
     * @param newBalance The new balance to be set for the player.
     */
    public void setBalance(double newBalance) { this.balance = newBalance; }

    /**
     * Retrieves the hand of cards held by the player.
     *
     * @return The hand of cards held by the player.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Clears the player's hand by removing all cards.
     */
    public void clearHand() {
        hand.clearHand();
    }
}
