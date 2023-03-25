import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TileTest{
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
        assertEquals(tile1.getColour(), "F");
        assertEquals(tile2.getColour(), "M");
        assertEquals(tile3.getColour(), "R");
        
    }
}