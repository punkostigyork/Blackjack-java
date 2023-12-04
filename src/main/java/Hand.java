/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

/**
 * A következő osztályban a Hand osztályt implementáljuk
 * Az osztály egy játékos vagy a dealer kártyáit kezeli
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's or dealer's hand in the game.
 */
public class
Hand {
    private List<Card> cards;

    /**
     * Constructs a Hand object.
     * Initializes an empty list of cards.
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Adds a card to the hand.
     *
     * @param card The card to be added to the hand
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Calculates the value of the hand considering the rules of the game.
     * Aces are valued as 1 or 11 depending on the total value of the hand.
     *
     * @return The total value of the hand
     */
    public int getHandValue() {
        int value = 0;
        int aceCount = 0;

        for(Card card : cards) {
            int cardValue = card.getValue();
            if(cardValue == 1)
                aceCount++;
            value += cardValue;
        }

        while(aceCount > 0 && value + 10 <= 21) {
            value += 10;
            aceCount--;
        }
        return value;
    }

    /**
     * Checks if the hand's value exceeds 21 (busted).
     *
     * @return True if the hand value is greater than 21, false otherwise
     */
    public boolean isBusted() {
        return getHandValue() > 21;
    }

    /**
     * Checks if the hand has a blackjack (contains 2 cards totaling 21).
     *
     * @return True if the hand has a blackjack, false otherwise
     */
    public boolean hasBlackjack() {
        return cards.size() == 2 && getHandValue() == 21;
    }

    /**
     * Retrieves the list of cards in the hand.
     *
     * @return The list of cards in the hand
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Clears the hand by removing all cards.
     */
    public void clearHand() {
        cards.clear();
    }
}
