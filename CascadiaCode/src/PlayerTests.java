import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTests{
    @Test
    void CreatePlayer(){
        Player player1 = new Player("Player1",1);
        Player player2 = new Player("Player2",2);
        assertFalse(player1 == null);
        assertFalse(player2 == null);
        assertEquals(player1.getName(), "Player1");
        assertEquals(player2.getName(), "Player2");
        Tile tile1  = new Tile(1);
        player1.playerBoard[0][0] = tile1;
        assertEquals(player1.playerBoard[0][0],tile1);
    }
}