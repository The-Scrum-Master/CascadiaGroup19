import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is class constructs and holds the functions for the Object Tile
 * The index of the array that holds the habitat is the orientation of the tile.
 */
public class Tile {
    private boolean Played;

    private Wildlife[] slots;
    private Habitat[] habitats;
    private char colour2; // colours for tiles
    private char colour; // colours for tiles
    private   int count = 0; // count for colours
    private int animalCount =0; // count for tokens

    private final int select;

    private char animal;
    private char animal2;

    private char animal3;

    public Tile(int select){
        if(select > 3 || select < 1){
            select = randomNumberGenerator(3)+1; //this generates a number between 0 and 2
        }
        this.select = select;
        Played = false;
            if (select == 2){
                habitats = new Habitat[]{randomHabitat(), randomHabitat()};
                while(habitats[0].getSymbol()==(habitats[1].getSymbol())){ //makes sure the habitats are not the same on the two biomes tiles
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
                while(slots[0] == slots[2] || slots[1] == slots[2] || slots[0] == slots[1]){
                    slots[2] = randomSlot();
                    slots[1] = randomSlot();
                }
            } else if (select == 1) {
                habitats = new Habitat[]{randomHabitat()};
                slots = new Wildlife[]{randomSlot()};
            }
    }
    public void flipTile(Tile t){ //flips tiles by swapping its orientation in array
       Habitat temp =  t.getHabitat(0);
       t.habitats[0] = t.habitats[1];
       t.habitats[1] = temp;
       TileGenerator g = new TileGenerator(t);
       g.generateFlipTile();
       g.printTile();
    }

    public Habitat randomHabitat(){ // generates and returns 1 of 5 habitats
        count++;
        switch (randomNumberGenerator(5)){

            case 0:
                if (count>1) //if function called twice store first colour in colour2
                {
                    colour2=colour;
                }
                colour = 'R';


                return Habitat.RIVER;
            case 1:
                if (count>1)
                {
                    colour2=colour;
                }


                colour = 'F';

                return Habitat.FOREST;
            case 2:
                if (count>1)
                {
                    colour2=colour;
                }

                colour = 'M';

                return Habitat.MOUNTAIN;
            case 3:
                if (count>1)
                {
                    colour2=colour;
                }


                colour = 'W';

                return Habitat.WETLANDS;
            case 4:
                if (count>1)
                {
                    colour2=colour;
                }


                colour = 'P';

                return Habitat.PRAIRIE;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }
    public Wildlife randomSlot(){
        animalCount++;

        switch (randomNumberGenerator(5)){ //generates and returns 1 of 5 tokens
            case 0:
                if (animalCount==2)//if function called twice store first colour in colour2
                {
                    animal2=animal;
                }
                if (animalCount>2)//if function called 3 times store first animal in animal3 and second animal in animal2
                {
                    animal3 = animal;
                }

                animal = 'H';
                return Wildlife.HAWK;
            case 1:
                if (animalCount==2)
                {
                    animal2=animal;
                }
                if (animalCount>2)
                {
                    animal3 = animal;
                }
                animal = 'S';
                return Wildlife.SALMON;
            case 2:
                if (animalCount==2)
                {
                    animal2=animal;
                }
                if (animalCount>2)
                {
                    animal3 = animal;
                }
                animal = 'E';
                return Wildlife.ELK;
            case 3:
                if (animalCount==2)
                {
                    animal2=animal;
                }
                if (animalCount>2)
                {
                    animal3 = animal;
                }
                animal = 'B';
                return Wildlife.BEAR;
            case 4:
                if (animalCount==2)
                {
                    animal2=animal;
                }
                if (animalCount>2)
                {
                    animal3 = animal;
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

    public static int randomNumberGenerator(int upperBound){
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


    public char getColour()
    {
        return colour;
    }
    public char getColour2()
    {
        return colour2;
    }

    public char getAnimal(){
        return animal;
    }
    public char getAnimal2(){
        return animal2;
    }
    public char getAnimal3(){
        return animal3;
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
    }
    public String colourAnimal(char s)
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
        if(t.getSelect()==1)//Solo tile
        {
            g.tileUniqueColor(t.colourConverter(t.getColour()), t.colourAnimal(t.getAnimal()));


            map.addTile(g);

            map.printMapTotal();
        }
        if(t.getSelect()==2)//2 biome tile 2 placeholders
        {


            g.tileTwoColors(t.colourConverter(t.getColour2()), t.colourConverter(t.getColour()), t.colourAnimal(t.getAnimal2()), " ", t.colourAnimal(t.getAnimal()));
            map.addTile(g);

            map.printMapTotal();

        }
        if(t.getSelect()==3)//2 biome tile 2 placeholders
        {


            g.tileTwoColors(t.colourConverter(t.getColour2()), t.colourConverter(t.getColour()), t.colourAnimal(t.getAnimal3()), t.colourAnimal(t.getAnimal2()), t.colourAnimal(t.getAnimal()));
            map.addTile(g);

            map.printMapTotal();

        }


    }

    public static Tile generateRandomTile()
    {
        Tile t = new Tile(0);
        return t;
    }

    public static Tile generateSpecificTile(int x)
    {
        Tile t = new Tile(x);
        return t;
    }


    public static void main(String[] args)
    {

        Tile t =new Tile( 2);
        TileGenerator tg = new TileGenerator(t);
       tg.printTile();
       t.flipTile(t);






    }
}
