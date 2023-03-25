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
        assertEquals(tile1.getHabitat(0), Habitat.FOREST);
        assertEquals(tile1.getSlot(0), Wildlife.FOX);
        assertEquals(tile1.getColour(), 'F');
    }
    
}