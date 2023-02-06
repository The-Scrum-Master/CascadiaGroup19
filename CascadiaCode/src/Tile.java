import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is class constructs and holds the functions for the Object Tile
 * The index of the array that holds the habitat is the orientation of the tile.
 * @author Patrick Kelly
 */
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
    private char symbol2;
    private  int count = 0;

    private final int select;

    private char animal;
    private char animal2;

    public Tile(tileType type, int select){
        this.select = select;
        if(select > 4 || select < 2){
            select = randomNumberGenerator(1);
        }
        this.type = type;
        Played = false;
        if (this.type == tileType.NORMAL) { //creates a two biomes tile with either 3 or 2 slots for tokens
            habitats = new Habitat[]{randomHabitat(), randomHabitat()};
            while(habitats[0] == habitats[1]){ //makes sure the habitats are not the same on the two biomes tiles
                habitats[1] = randomHabitat();
            }
            if (select == 0 || select == 2){
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
        } else {
            throw new IllegalArgumentException("not a valid type of tile");
        }
    }
    public void flipTile(){ //flips tiles by swapping its orientation in array
       Habitat temp =  habitats[0];
       habitats[0] = habitats[1];
       habitats[1] = temp;
    }

    public Habitat randomHabitat(){ // generates and returns 1 of 5 habitats
        count++;
        switch (randomNumberGenerator(5)){

            case 0:
                symbol = 'R';
                if (count>1)
                {
                    symbol2=symbol;
                }

                return Habitat.RIVER;
            case 1:
                if (count>1)
                {
                    symbol2=symbol;
                }


                symbol = 'F';

                return Habitat.FOREST;
            case 2:
                if (count>1)
                {
                    symbol2=symbol;
                }

                symbol = 'M';

                return Habitat.MOUNTAIN;
            case 3:
                if (count>1)
                {
                    symbol2=symbol;
                }


                symbol = 'W';

                return Habitat.WETLANDS;
            case 4:
                if (count>0)
                {
                    symbol2=symbol;
                }


                symbol = 'P';

                return Habitat.PRAIRIE;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }
    public Wildlife randomSlot(){
        count++;

        switch (randomNumberGenerator(5)){ //generates and returns 1 of 5 tokens
            case 0:
                if (count>1)
                {
                    animal2=animal;
                }
                animal = 'H';
                return Wildlife.HAWK;
            case 1:
                if (count>1)
                {
                    animal2=animal;
                }
                animal = 'S';
                return Wildlife.SALMON;
            case 2:
                if (count>1)
                {
                    animal2=animal;
                }
                animal = 'E';
                return Wildlife.ELK;
            case 3:
                if (count>1)
                {
                    animal2=animal;
                }
                animal = 'B';
                return Wildlife.BEAR;
            case 4:
                if (count>1)
                {
                    animal2=animal;
                }
                animal = 'F';
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
    public char getSymbol2()
    {
        return symbol2;
    }

    public char getAnimal(){
        return animal;
    }
    public char getAnimal2(){
        return animal2;
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
                return "\033[44m"; //Blue
            case 'F':
                return "\033[42m"; //Green
            case 'M':
                return "\033[47m"; //White
            case 'W':
                return "\033[46m";//Cyan
            case 'P':
                return "\033[43m";//Yellow
            default:
                throw new IllegalArgumentException("Error");
        }
    }public String colourAnimal(char s)
    {
        switch (s)
        {
            case 'H':
                return "\033[0;34m" + "H"; //Blue
            case 'F':
                return "\033[0;33m" + "F";//Orange
            case 'E':
                return "\033[0;30m" + "E"; //BLACK
            case 'S':
                return "\033[0;31m"+ "S";//RED
            case 'B':
                return "\033[0;35m" + "B";//PURPLE
            default:
                throw new IllegalArgumentException("Error");
        }
    }
    public void generateTile(Tile t)
    {
        MapGenerator map=new MapGenerator();
        TileGenerator blank =new TileGenerator();
        map.fillMapBlank(blank);
        blank.blankTile();


        TileGenerator g = new TileGenerator();
        if(tileType.SOLO==t.type)
        {
            g.tileUniqueColor(t.colourConverter(t.getSymbol()), t.colourAnimal(t.getAnimal()));


            map.addTile(g);

            map.printMapTotal();
        }
        if(tileType.NORMAL==t.type)
        {


            g.tileTwoColors(t.colourConverter(t.getSymbol2()), t.colourConverter(t.getSymbol()), t.colourAnimal(t.getAnimal2()), " ", t.colourAnimal(t.getAnimal()));
            map.addTile(g);

            map.printMapTotal();

        }



    }


    public static void main(String[] args) {
        Tile t =new Tile(tileType.NORMAL, 0);
       t.generateTile(t);


    }
}

