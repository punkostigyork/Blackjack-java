/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Represents a deck of cards used in the Blackjack game.
 * It contains a list of cards and provides functionalities to shuffle the deck,
 * draw a card, and get information about the deck.
 */
public class Deck {
    private List<Card> cards;

    /**
     * Constructs a standard 52-card deck with all the possible combinations of suits and ranks.
     * Each card is created with its corresponding suit, rank, and value.
     */
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"H", "D", "C", "S"};
        String[] ranks = {"A", "2", "3", "4", "5", "6",
                        "7", "8", "9", "10", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        for (String suit : suits)
            for (int i = 0; i < ranks.length; i++)
                cards.add(new Card(suit, ranks[i], values[i]));
    }

    /**
     * Shuffles the deck by randomly rearranging the order of cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the deck. Removes the top card from the deck and returns it.
     *
     * @return The card drawn from the deck
     * @throws Exception If the deck is empty
     */
    public Card draw() throws Exception {
        if (cards.isEmpty()) throw new Exception("The deck is empty.");
        return cards.remove(cards.size() - 1);
    }

    /**
     * Retrieves the list of cards in the deck.
     *
     * @return The list of cards in the deck
     */
    public List<Card> getDeck() { return cards; };

    /**
     * Gets the current size of the deck, i.e., the number of cards remaining in the deck.
     *
     * @return The size of the deck
     */
    public int getSize() {
        return cards.size();
    }
}