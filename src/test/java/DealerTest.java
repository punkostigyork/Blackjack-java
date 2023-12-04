import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the methods of the Dealer class
 */
public class DealerTest {
    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    public void setUp() {
        dealer = new Dealer(100.0); // Initial balance of 100.0
        deck = new Deck();
        deck.shuffle();
    }

    @Test
    public void testInitialBalance() {
        assertEquals(100.0, dealer.getBalance());
    }

    @Test
    public void testSetBalance() {
        dealer.setBalance(150.0);
        assertEquals(150.0, dealer.getBalance());
    }

    @Test
    public void testClearHand() {
        dealer.getHand().addCard(new Card("Hearts", "Ace", 1));
        dealer.getHand().addCard(new Card("Diamonds", "King", 10));
        dealer.clearHand();
        assertEquals(0, dealer.getHand().getCards().size());
        assertEquals(0.0, dealer.getBalance());
    }

    @Test
    public void testHit() {
        dealer.play(deck);
        assertTrue(dealer.getHand().getHandValue() >= 17);
    }
}
