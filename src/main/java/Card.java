/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

/**
 * Represents a playing card in a standard deck.
 * Each card has a suit, rank, and a value.
 * Provides methods to retrieve and manipulate card details.
 *
 */


public class Card {
    private String suit;
    private String rank;
    private int value;

    /**
     * Constructs a Card object with the specified suit, rank, and value.
     *
     * @param suit The suit of the card (e.g., hearts, diamonds, etc.)
     * @param rank The rank of the card (e.g., Ace, 2, King, etc.)
     * @param value The numerical value of the card in the game
     */
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    /**
     * Gets the suit of the card.
     *
     * @return The suit of the card (e.g., hearts, diamonds, etc.)
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Sets the suit of the card.
     *
     * @param suit The suit of the card to be set (e.g., hearts, diamonds, etc.)
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * Gets the rank of the card.
     *
     * @return The rank of the card (e.g., Ace, 2, King, etc.)
     */
    public String getRank() {
        return rank;
    }

    /**
     * Sets the rank of the card.
     *
     * @param rank The rank of the card to be set (e.g., Ace, 2, King, etc.)
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Gets the value of the card.
     *
     * @return The numerical value of the card in the game
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the card.
     *
     * @param value The numerical value of the card to be set in the game
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns a string representation of the card.
     * The format is Rank-Suit for non-numeric ranks, e.g., Ace-H for Ace of Hearts,
     * and Rank-Suit for numeric ranks, e.g., 2-D for 2 of Diamonds.
     *
     * @return A string representation of the card
     */
    @Override
    public String toString() {
        return rank + "-" + suit;
    }

    /**
     * Gets the image path of the card.
     *
     * @return The file path of the image representing the card
     */
    public String getImagePath() { return "./cards/" + toString() + ".png"; }
}

