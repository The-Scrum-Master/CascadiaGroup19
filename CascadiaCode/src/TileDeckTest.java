/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TileDeckTest {
    @Test
    void testDeck(){
        TileDeck.createDeck();
        assertFalse(TileDeck.deck == null);
        assertEquals(TileDeck.deck.size(), 85);
        while(TileDeck.deck.size() > 0){
            TileDeck.deck.pop();
        }
        TileDeck.emptyDeckCheck();
        assertFalse(GameRunner.continueGame == true);
    }
    @Test
    void testRiver(){
        TileDeck.createDeck();
        TileDeck.playRiver();
        assertFalse(TileDeck.riverTiles == null);
        assertFalse(TileDeck.riverTokens == null);
        assertEquals(TileDeck.riverTiles.length, 4);
        assertEquals(TileDeck.riverTokens.length, 4);
        TileDeck.riverTokens[0] = Wildlife.HAWK;
        TileDeck.riverTokens[1] = Wildlife.HAWK;
        TileDeck.riverTokens[2] = Wildlife.HAWK;
        TileDeck.riverTokens[3] = Wildlife.HAWK;
        TileDeck.cullRiver(4);
        assertFalse(TileDeck.riverTokens[0] == Wildlife.HAWK
        && TileDeck.riverTokens[1] == Wildlife.HAWK
        && TileDeck.riverTokens[2] == Wildlife.HAWK
        && TileDeck.riverTokens[3] == Wildlife.HAWK);
        assertEquals(TileDeck.riverTokens.length, 4);
        TileDeck.riverTokens[0] = Wildlife.HAWK;
        TileDeck.riverTokens[1] = Wildlife.HAWK;
        TileDeck.riverTokens[2] = Wildlife.HAWK;
        TileDeck.riverTokens[3] = Wildlife.HAWK;
        TileDeck.cullCheck();
        assertFalse(TileDeck.riverTokens[0] == Wildlife.HAWK
        && TileDeck.riverTokens[1] == Wildlife.HAWK
        && TileDeck.riverTokens[2] == Wildlife.HAWK
        && TileDeck.riverTokens[3] == Wildlife.HAWK);
        assertEquals(TileDeck.riverTokens.length, 4);
        
    }
}
