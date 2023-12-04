import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test the methods of the Player class
 */
public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer", 100.0); // Assuming initial balance of 100.0
    }

    @Test
    public void testGetName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    public void testGetBalance() {
        assertEquals(100.0, player.getBalance());
    }

    @Test
    public void testSetBalance() {
        player.setBalance(200.0);
        assertEquals(200.0, player.getBalance());
    }

    @Test
    public void testGetHand() {
        assertNotNull(player.getHand());
    }

    @Test
    public void testClearHand() {
        player.getHand().addCard(new Card("Hearts", "Ace", 1)); // Assuming adding a card to the hand
        assertFalse(player.getHand().getCards().isEmpty());
        player.clearHand();
        assertTrue(player.getHand().getCards().isEmpty());
    }
}
