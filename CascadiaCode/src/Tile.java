import java.util.ArrayList;
import java.util.Random;

public class Tile {
    public enum tileType { //types of tiles available to make
        NORMAL,
        SOLO,
        START,
    }
    private final tileType type;
    private boolean Played;

    private Wildlife[] slots;
    private Habitat[] habitats;
    private char symbol;

    public Tile(tileType type){
        this.type = type;
        Played = false;
        if (this.type == tileType.NORMAL) { //creates a two biomes tile with either 3 or 2 slots for tokens
            habitats = new Habitat[]{randomHabitat(), randomHabitat()};
            while(habitats[0] == habitats[1]){ //makes sure the habitats are not the same on the two biomes tiles
                habitats[1] = randomHabitat();
            }
            if (randomNumberGenerator(1) == 0) {
                slots = new Wildlife[]{randomSlot(), randomSlot()};
                while(slots[0] == slots[1]){ //makes sure the habitats are not the same on the two biomesS
                    slots[1] = randomSlot();}
            } else {
                slots = new Wildlife[]{randomSlot(), randomSlot(), randomSlot()};
                while(slots[0] == slots[1]){ //makes sure the habitats are not the MATCHING biomesS
                    slots[1] = randomSlot();}
                while(slots[0] == slots[2] || slots[1] == slots[2]){
                    slots[2] = randomSlot();
                }
            }
        } else if (this.type == tileType.SOLO) { // creates a solo-tile with one biome and one token slot
            habitats = new Habitat[]{randomHabitat()};
            slots = new Wildlife[]{randomSlot()};
        } else if (this.type == tileType.START) { // still need to manually put in
            //need to fill
        } else {
            throw new IllegalArgumentException("not a valid type of tile");
        }
    }

    public Habitat randomHabitat(){ // generates and returns 1 of 5 habitats
        switch (randomNumberGenerator(5)){
            case 0:
                symbol = 'R';
                return Habitat.RIVER;
            case 1:
                symbol = 'F';

                return Habitat.FOREST;
            case 2:
                symbol = 'M';

                return Habitat.MOUNTAIN;
            case 3:
                symbol = 'W';

                return Habitat.WETLANDS;
            case 4:
                symbol = 'P';

                return Habitat.PRAIRIE;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }
    public Wildlife randomSlot(){
        switch (randomNumberGenerator(5)){ //generates and returns 1 of 5 tokens
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

    public tileType getType() { // accessor and getter methods
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


    public Wildlife[] getSlots() { //gives array
        return slots;
    }
    public Wildlife getSlot(int index){ //gives wildlife
        return slots[index];
    }

    public Habitat getHabitat(int index){
        return habitats[index];
    }
    public Habitat[] getHabitats() {
        return habitats;
    }


    public char getSymbol()
    {
        return symbol;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "\n type=" + type +
                ", Played=" + Played +
                '}';
    }
    public String colourConverter(char s)
    {
        switch (s)
        {
            case 'R':
                return "\033[0;34m"; //Blue
            case 'F':
                return "\033[0;32m"; //Green
            case 'M':
                return "\033[0;37m"; //White
            case 'W':
                return "\033[0;36m";//Cyan
            case 'P':
                return "\033[0;33m";//Yellow
            default:
                throw new IllegalArgumentException("Error");
        }
    }

    public static void main(String[] args) {
        Tile t =new Tile(tileType.SOLO);
        System.out.println(t.randomHabitat());
        System.out.println(t.getSymbol());
        System.out.println(t.colourConverter(t.getSymbol())+"Hi do I work?");



    }
}

