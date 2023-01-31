import java.util.ArrayList;
import java.util.Random;

public class Tile {
    public enum tileType {
        NORMAL,
        SOLO,
        START,
    }
    private final ArrayList<Wildlife> slots = new ArrayList<>();
    private final ArrayList<Habitat> habitats = new ArrayList<>();

    private final tileType type;
    private boolean Played;

    public Tile(tileType type){
        this.type = type;
        ArrayList<Wildlife> slots = new ArrayList<>();
        ArrayList<Habitat> habitats = new ArrayList<>();
        boolean Played = false;
    }
    public void populateTile() {
        if (this.type == tileType.NORMAL) {
            this.habitats.add(randomHabitat());
            this.habitats.add(randomHabitat());
            if (randomNumberGenerator(1) == 0) {
                this.slots.add(randomSlot());
                this.slots.add(randomSlot());
            } else {
                this.slots.add(randomSlot());
                this.slots.add(randomSlot());
                this.slots.add(randomSlot());
            }
        } else if (this.type == tileType.SOLO) {
            this.habitats.add(randomHabitat());
            this.slots.add(randomSlot());
        } else if (this.type == tileType.START) {
            //need to fill
        } else {
            throw new IllegalArgumentException("not a valid type of tile");
        }
    }
    public Habitat randomHabitat(){
        switch (randomNumberGenerator(5)){
            case 0:
                return Habitat.RIVER;
            case 1:
                return Habitat.FOREST;
            case 2:
                return Habitat.MOUNTAIN;
            case 3:
                return Habitat.WETLANDS;
            case 4:
                return Habitat.PRAIRIE;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }
    public Wildlife randomSlot(){
        switch (randomNumberGenerator(5)){
            case 0:
                return Wildlife.HAWK;
            case 1:
                return Wildlife.SALMON;
            case 2:
                return Wildlife.ELK;
            case 3:
                return Wildlife.BEAR;
            case 4:
                return Wildlife.FOX;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }

    public tileType getType() {
        return this.type;
    }

    public boolean isPlayed() {
        return this.Played;
    }

    public void playTile(){this.Played = true;}

    public int randomNumberGenerator(int upperBound){
        Random rand = new Random ();
        return rand.nextInt(upperBound);
    }

    public ArrayList<Wildlife> getSlots() {
        return this.slots;
    }

    public ArrayList<Habitat> getHabitats() {
        return this.habitats;
    }


    public String printHabitat(ArrayList<Habitat> arrayList) {
        String total = null;
        String strLine;
        for(int i = 0; i < arrayList.size(); i++) {
            strLine = "Index " + i + " contains " + arrayList.get(i);
            total.concat( "\n" + strLine);
        }
        return total;
    }

    @Override
    public String toString() {
       String h = printHabitat(getHabitats());
        return "Tile{" + h +
                "\n type=" + type +
                ", Played=" + Played +
                '}';
    }
}

