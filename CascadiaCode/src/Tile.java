import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is class constructs and holds the functions for the Object Tile
 * The index of the array that holds the habitat is the orientation of the tile.
 * @author Patrick Kelly
 */
public class Tile {
    private boolean Played;

    private Wildlife[] slots;
    private Habitat[] habitats;
    private char symbol;
    private char symbol2;
    private  static  int count = 0;

    private final int select;

    private char animal;
    private char animal2;

    public Tile(int select){
        this.select = select;
        if(select > 4 || select < 0){
            select = randomNumberGenerator(3);
        }
        Played = false;
            if (select == 2){
                habitats = new Habitat[]{randomHabitat(), randomHabitat()};
                while(habitats[0].equals(habitats[1])){ //makes sure the habitats are not the same on the two biomes tiles
                    habitats[1] = randomHabitat();
                }
                slots = new Wildlife[]{randomSlot(), randomSlot()};
                habitats = new Habitat[]{randomHabitat(), randomHabitat()};
                while(slots[0] == slots[1]){ //makes sure the habitats are not the same on the two biomesS
                    slots[1] = randomSlot();}
            } else if (select == 3) {
                habitats = new Habitat[]{randomHabitat(), randomHabitat()};
                while(habitats[0].equals(habitats[1])){ //makes sure the habitats are not the same on the two biomes tiles
                    habitats[1] = randomHabitat();
                }
                slots = new Wildlife[]{randomSlot(), randomSlot(), randomSlot()};
                while(slots[0] == slots[1]){ //makes sure the habitats are not the MATCHING biomesS
                    slots[1] = randomSlot();}
                while(slots[0] == slots[2] || slots[1] == slots[2]){
                    slots[2] = randomSlot();
                }
            } else if (select == 1) {
                habitats = new Habitat[]{randomHabitat()};
                slots = new Wildlife[]{randomSlot()};
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
                if (count>1)
                {
                    symbol2=symbol;
                }
                symbol = 'R';


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

    public int getSelect(){
        return this.select;
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
                return "\033[0;34m" + "H"+ DisplayColour.RESET; //Blue
            case 'F':
                return "\033[0;33m" + "F"+ DisplayColour.RESET;//Orange
            case 'E':
                return "\033[0;30m" + "E"+ DisplayColour.RESET; //BLACK
            case 'S':
                return "\033[0;31m"+ "S"+ DisplayColour.RESET;//RED
            case 'B':
                return "\033[0;35m" + "B"+ DisplayColour.RESET;//PURPLE
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
        if(select ==1)
        {
            g.tileUniqueColor(t.colourConverter(t.getSymbol()), t.colourAnimal(t.getAnimal()));


            map.addTile(g);

            map.printMapTotal();
        }
        if(select == 2 || select == 3)
        {


            g.tileTwoColors(t.colourConverter(t.getSymbol2()), t.colourConverter(t.getSymbol()), t.colourAnimal(t.getAnimal2()), " ", t.colourAnimal(t.getAnimal()));
            map.addTile(g);

            map.printMapTotal();

        }

    }


    public static void main(String[] args) {
        Tile t =new Tile(0);
       t.generateTile(t);
       TileGenerator g = new TileGenerator();
        System.out.println(t.getSymbol2());
        System.out.println(t.getHabitat(0));
        System.out.println(t.getSymbol());
        System.out.println(t.getHabitat(1));

    }
}

