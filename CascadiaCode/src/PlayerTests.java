import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTests{
    @Test // Test to see if player is created
    void TestCreatePlayer(){
        Player player1 = new Player("Player1",1);
        Player player2 = new Player("Player2",2);
        assertFalse(player1 == null);
        assertFalse(player2 == null);
        assertEquals(player1.getName(), "Player1");
        assertEquals(player2.getName(), "Player2");
        assertEquals(player1.getOrder(),1);
        assertEquals(player2.getOrder(),2);
    }
    @Test // Test to see if player board is created
    void TestPlayerBoard(){
        Player player1 = new Player("Player1",1);
        Tile tile1 = new Tile(2);
        player1.playerBoard[0][0] = tile1;
        assertEquals(player1.playerBoard[0][0], tile1);
        assertEquals(player1.getPlayerBoard(), player1.playerBoard);
        assertEquals(player1.playerBoard[0][0], player1.getBoardIndex(0,0));
        player1.generateInitialMap();
        assertEquals(player1.getBoardIndex(22,22).getSelect(),1);
        assertEquals(player1.getBoardIndex(22,23).getSelect(),2);
        assertEquals(player1.getBoardIndex(23,23).getSelect(),3);
        assertTrue(player1.isFirstTurnPlayed());
    }
    @Test // Test to see if player getters work
    void TestPlayerGetters(){ 
        Player player1 = new Player("Player1",1);
        Tile tile1 = new Tile(1);
        tile1.setSlots(Wildlife.BEAR, 0);
        player1.playerBoard[0][0] = tile1;
        assertEquals(player1.getBoardIndex(0,0), tile1);
        player1.setHeldTile(tile1);
        assertEquals(player1.getHeldTile(), tile1);
        player1.setHeldToken(Wildlife.BEAR);
        assertEquals(player1.getHeldToken(), Wildlife.BEAR);
        assertFalse(player1.isFirstTurnPlayed());
    }
    @Test // Test to see if player picks a tile and token
    void TestPlayerPicks(){
        TileDeck.createDeck();
        TileDeck.playRiver();
        Player player1 = new Player("Player1",1);
        player1.pickPair(1);
        assertTrue(player1.heldTile != null);
        assertTrue(player1.heldToken != null);
        // player1.splitPick(); 
        assertTrue(player1.heldTile != null);
        assertTrue(player1.heldToken != null);
    }
    @Test // Test to see if player places a tile 
    void TestPlayerPlace(){
        TileDeck.createDeck();
        TileDeck.playRiver();
        Player player1 = new Player("Player1",1);
        player1.generateInitialMap();
        player1.pickPair(1);
        Tile tileTest = player1.heldTile;
        player1.placeTile(23,24);
        assertTrue(player1.getBoardIndex(24,23).isPlayed());
        assertTrue(player1.getBoardIndex(24,23) == tileTest);
        assertEquals(player1.getBoardIndex(24, 23).getSlot(0),tileTest.getSlot(0));
    }
    @Test // Test to see if player places a token correctly on a tile
    void TestPlayerPlaceToken(){
        Player player1 = new Player("Player1",1);
        player1.generateInitialMap();
        player1.setHeldToken(Wildlife.FOX);
        player1.playerBoard[22][22].setSlots(Wildlife.FOX, 0);
        assertTrue(player1.getBoardIndex(22,22).getSlot(0) == Wildlife.FOX);
        player1.placeToken(22, 22);
        assertTrue(player1.getBoardIndex(22,22).tokenPlayedType == Wildlife.FOX);
    }
    @Test // Test to see if player's nature is incremnted and decremented
    void TestNatureTokens(){
        Player player1 = new Player("Player1",1);
        player1.generateInitialMap();
        player1.setHeldToken(Wildlife.FOX);
        player1.playerBoard[22][22].setSlots(Wildlife.FOX, 0);
        player1.placeToken(22, 22);
        assertTrue(player1.getBoardIndex(22,22).getSlot(0) == Wildlife.FOX);
        assertEquals(1,player1.getNatureTokenNumber());
        player1.reduceNatureTokenNumberByOne();
        assertEquals(0, player1.getNatureTokenNumber());
    }
    @Test
    void TestHabitatScore(){
        Player player1 = new Player("Player1",1);
        player1.generateInitialMap();
        player1.playerBoard[23][22] = new Tile(1);
        player1.playerBoard[24][22] = new Tile(2);
        player1.playerBoard[24][23] = new Tile(2);
        player1.playerBoard[25][22] = new Tile(3);
        player1.map.fillMapWithAllowedTilePlacements();
        player1.printMap(-1);
        player1.habitatScore(22, 23);
    }
    
}