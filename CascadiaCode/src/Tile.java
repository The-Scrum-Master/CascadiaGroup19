import java.util.ArrayList;
import java.util.Random;

public class Tile {
    public enum tileType {
        NORMAL,
        SOLO,
        START,
    }
    private ArrayList<Wildlife> slots = new ArrayList<>();
    private ArrayList<Habitat> habitats = new ArrayList<>();

    private tileType type;
    private boolean Played;

    public Tile(tileType type){
        this.type = type;
        ArrayList<Wildlife> slots = new ArrayList<>();
        ArrayList<Habitat> habitats = new ArrayList<>();
        boolean Played = false;
        if(type == tileType.NORMAL) {
                habitats.add(randomHabitat());
                habitats.add(randomHabitat());
                if(randomNumberGenerator(1) == 0){
                    slots.add(randomSlot());
                    slots.add(randomSlot());
                }else{
                    slots.add(randomSlot());
                    slots.add(randomSlot());
                    slots.add(randomSlot());
                }
        } else if (type == tileType.SOLO) {
            habitats.add(randomHabitat());
            slots.add(randomSlot());
        } else if (type == tileType.START) {
            //need to fill
        } else{
            throw new IllegalArgumentException("not a valid type of tile");
        }
        if(habitats.size() > 2 || habitats.size() < 1){
            throw new IllegalArgumentException( habitats.size() + " habitats is outside parameters");
        }
        if(slots.size() > 3 || slots.size() < 1) {
            throw new IllegalArgumentException(slots.size() + " slots is outside parameters");
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

    /*
    public String printHabitat(ArrayList<Habitat> arrayList) {
        String total = null;
        String strLine;
        for(int i = 0; i < arrayList.size(); i++) {
            strLine = "Index " + i + " contains " + arrayList.get(i);
            total = total + "\n" + strLine;
        }
        return total;
    }
*/

    @Override
    public String toString() {
        return "Tile{" +
                "slots=" + slots +
                ", habitats=" + habitats +
                ", type=" + type +
                ", Played=" + Played +
                '}';
    }
}

