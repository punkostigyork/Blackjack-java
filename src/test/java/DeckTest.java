import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the methods of the Deck class
 */
public class DeckTest {
    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testDeckSize() {
        assertEquals(52, deck.getSize());
    }

    @Test
    public void testDeckShuffle() {
        Deck originalDeck = new Deck();
        originalDeck.shuffle();
        deck.shuffle();
        List<Card> originalCards = originalDeck.getDeck();
        List<Card> shuffledCards = deck.getDeck();
        assertNotEquals(originalCards, shuffledCards);
    }

    @Test
    public void testDrawCard() {
        try {
            Card card = deck.draw();
            assertNotNull(card);
            assertEquals(51, deck.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testEmptyDeckException() {
        try {
            while (true) {
                deck.draw();
            }
        } catch (Exception e) {
            assertEquals("The deck is empty.", e.getMessage());
        }
    }
}
