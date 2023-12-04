import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the methods of the Card class
 */
public class CardTest {

    @Test
    public void testGetSuit() {
        Card card = new Card("Hearts", "Ace", 1);
        assertEquals("Hearts", card.getSuit());
    }

    @Test
    public void testSetSuit() {
        Card card = new Card("Diamonds", "2", 2);
        card.setSuit("Clubs");
        assertEquals("Clubs", card.getSuit());
    }

    @Test
    public void testGetRank() {
        Card card = new Card("Spades", "King", 10);
        assertEquals("King", card.getRank());
    }

    @Test
    public void testSetRank() {
        Card card = new Card("Hearts", "5", 5);
        card.setRank("Ace");
        assertEquals("Ace", card.getRank());
    }

    @Test
    public void testGetValue() {
        Card card = new Card("Clubs", "Jack", 10);
        assertEquals(10, card.getValue());
    }

    @Test
    public void testSetValue() {
        Card card = new Card("Diamonds", "Queen", 10);
        card.setValue(12);
        assertEquals(12, card.getValue());
    }

    @Test
    public void testToString() {
        Card card1 = new Card("H", "A", 1);
        assertEquals("A-H", card1.toString());

        Card card2 = new Card("D", "7", 7);
        assertEquals("7-D", card2.toString());
    }

    @Test
    public void testGetImagePath() {
        Card card = new Card("C", "Q", 10);
        assertEquals("./cards/Q-C.png", card.getImagePath());
    }
}
