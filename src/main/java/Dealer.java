/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

/**
 * Represents a dealer in the Blackjack game.
 * Dealers have a hand of cards and a balance.
 */
public class Dealer {
    private Hand hand;
    private double balance;

    /**
     * Constructs a Dealer object with the specified initial balance.
     * Initializes the dealer's hand.
     *
     * @param balance The initial balance of the dealer
     */
    public Dealer(double balance) {
        this.hand = new Hand();
        this.balance = balance;
    }

    /**
     * Gets the dealer's hand.
     *
     * @return The dealer's hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Gets the dealer's balance.
     *
     * @return The dealer's balance
     */
    public double getBalance() { return balance; }

    /**
     * Sets the dealer's balance to a new value.
     *
     * @param newBalance The new balance to set for the dealer
     */
    public void setBalance(double newBalance) { this.balance = newBalance; }

    /**
     * Clears the dealer's hand and resets the balance to zero.
     */
    public void clearHand() {
        hand.clearHand();
        balance = 0;
    }

    /**
     * Initiates the dealer's turn in the game by making decisions based on the game rules.
     * If the hand value is less than 17, the dealer continues to draw cards (hit).
     *
     * @param deck The deck from which cards are drawn
     */
    public void play(Deck deck) {
        while(hand.getHandValue() < 17)
            hit(deck);
    }

    /**
     * Adds a card to the dealer's hand by drawing from the deck.
     *
     * @param deck The deck from which a card is drawn
     */
    private void hit(Deck deck) {
        try {
            hand.addCard(deck.draw());
        } catch(Exception e) {
            System.out.println("Deck is empty!");
            e.printStackTrace();
        }
    }
}
