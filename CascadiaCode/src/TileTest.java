/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TileTest{

    @Test
    void testTile(){
        Tile tile1 = new Tile(1);
        Tile tile2 = new Tile(2);
        Tile tile3 = new Tile(3);
        assertFalse(tile1 == null);
        assertFalse(tile2 == null);
        assertFalse(tile3 == null);
        assertFalse(tile1 == tile2);
        assertFalse(tile1 == tile3);
        assertFalse(tile2 == tile3);
    }
    @Test
    void testTileHabitat(){
        Tile tile1 = new Tile(1);
        Tile tile2 = new Tile(2);
        assertFalse(tile1 == null);
        assertEquals(tile1.getHabitats().length, 1);
        assertEquals(tile2.getHabitats().length, 2);
    }

    @Test
    void testTileSlots(){
        Tile tile1 = new Tile(1);
        Tile tile2 = new Tile(2);
        Tile tile3 = new Tile(3);
        assertEquals(tile1.getSlots().length, 1);
        assertEquals(tile2.getSlots().length, 2);
        assertEquals(tile3.getSlots().length, 3);
        
    }
    @Test
    void testTileColour(){
        Tile tile1 = new Tile(1);
        Tile tile2 = new Tile(2);
        Tile tile3 = new Tile(3);
        tile1.setColour('R');
        tile2.setColour('M');
        tile3.setColour('F');
        assertEquals(tile1.getColour(), 'R');
        assertEquals(tile2.getColour(), 'M');
        assertEquals(tile3.getColour(), 'F');
        assertEquals(tile1.colourConverter('R'),"\033[44m");
        assertEquals(tile2.colourConverter('M'),"\033[47m");
        assertEquals(tile3.colourConverter('F'),"\033[42m");
    }
    @Test
    void testGetterSetter(){
        Tile tile1 = new Tile(1);
        tile1.setHabitats(Habitat.FOREST,0);
        tile1.setSlots(Wildlife.FOX, 0);
        tile1.setColour('F');
        assertEquals(1, tile1.getSelect());
        assertEquals(tile1.getHabitat(0), Habitat.FOREST);
        assertEquals(tile1.getSlot(0), Wildlife.FOX);
        assertEquals(tile1.getColour(), 'F');
    }
    @Test
    void testTileFlip(){
        Tile tile1 = new Tile(2);
        tile1.setHabitats(Habitat.FOREST,0);
        tile1.setHabitats(Habitat.MOUNTAIN, 1);
        tile1.flipTile(tile1);
        assertEquals(tile1.getHabitat(0), Habitat.MOUNTAIN);
        assertEquals(tile1.getHabitat(1), Habitat.FOREST);
    }
    @Test
    void testTilePlaced(){
        Tile tile1 = new Tile(1);
        tile1.setSlots(Wildlife.FOX,0);
        Player player1 = new Player("Player1",1);
        player1.setHeldTile(tile1);
        player1.generateInitialMap();
        player1.placeTile(23,24);
        assertEquals(player1.getPlayerBoard()[23][24].isPlayed(), true);
        player1.setHeldToken(Wildlife.FOX);
        player1.placeToken(23,24);
        assertEquals(player1.getPlayerBoard()[23][24].tokenPlayedType, Wildlife.FOX);
    }
    
}