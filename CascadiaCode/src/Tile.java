import java.util.ArrayList;

public class Tile {
    private ArrayList<Wildlife> slots;
    private ArrayList<Habitat> habitats;

    private boolean Played;

    public Tile(ArrayList<Wildlife> slots, ArrayList<Habitat> habitats) {
        if(habitats.size() > 2 || habitats.size() < 1){
            throw new IllegalArgumentException( habitats.size() + " habitats is outside parameters");
        }
        if(slots.size() > 3 || slots.size() < 1){
            throw new IllegalArgumentException(slots.size() + " slots is outside parameters");
        }
        this.habitats = habitats;
        this.slots = slots;
        Played = false;
    }

    public Habitat randHabitat(){

    }

    public void playTile(){this.Played = true;}

    public ArrayList<Wildlife> getSlots() {
        return slots;
    }

    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }
}
