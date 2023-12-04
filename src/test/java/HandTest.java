import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the methods of the Hand class
 */
public class HandTest {
    private Hand hand;

    @BeforeEach
    public void setUp() {
        hand = new Hand();
    }

    @Test
    public void testAddCard() {
        assertEquals(0, hand.getCards().size());
        Card card = new Card("Hearts", "Ace", 1); // Create a card for testing
        hand.addCard(card);
        assertEquals(1, hand.getCards().size());
        assertEquals(card, hand.getCards().get(0));
    }

    @Test
    public void testGetHandValue() {
        // Test with a single Ace
        Card ace = new Card("Hearts", "Ace", 1);
        hand.addCard(ace);
        assertEquals(11, hand.getHandValue());

        // Test with multiple cards
        hand.addCard(new Card("Diamonds", "5", 5));
        assertEquals(16, hand.getHandValue());

        // Test with multiple cards and an Ace counted as 11
        hand.addCard(new Card("Clubs", "Ace", 1));
        assertEquals(17, hand.getHandValue());
    }

    @Test
    public void testIsBusted() {
        assertFalse(hand.isBusted());

        // Add cards to the hand to make it busted
        hand.addCard(new Card("Hearts", "10", 10));
        hand.addCard(new Card("Diamonds", "Queen", 10));
        hand.addCard(new Card("Clubs", "King", 10));
        assertTrue(hand.isBusted());
    }

    @Test
    public void testHasBlackjack() {
        assertFalse(hand.hasBlackjack());

        // Add cards to the hand to make it a blackjack
        hand.addCard(new Card("Hearts", "Ace", 1));
        hand.addCard(new Card("Diamonds", "King", 10));
        assertTrue(hand.hasBlackjack());

        // Test with more or less than 2 cards
        hand.addCard(new Card("Clubs", "2", 2));
        assertFalse(hand.hasBlackjack());
    }

    @Test
    public void testGetCards() {
        assertTrue(hand.getCards().isEmpty());

        // Add a card and check if it's present in the hand
        Card card = new Card("Hearts", "Ace", 1);
        hand.addCard(card);
        assertEquals(1, hand.getCards().size());
        assertEquals(card, hand.getCards().get(0));
    }

    @Test
    public void testClearHand() {
        Card card = new Card("Hearts", "Ace", 1);
        hand.addCard(card);
        assertFalse(hand.getCards().isEmpty());

        hand.clearHand();
        assertTrue(hand.getCards().isEmpty());
    }
}
