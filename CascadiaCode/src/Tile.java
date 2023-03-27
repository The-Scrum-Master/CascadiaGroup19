/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
import java.util.Random;

/**
 * This is class constructs and holds the functions for the Object Tile
 * The index of the array that holds the habitat is the orientation of the tile.
 */
public class Tile {
    public boolean Played;
    public boolean tokenPlayed;
    private boolean tokenPlaced = false;
    public boolean habitatCounted0;
    public boolean habitatCounted1;

    public Wildlife tokenPlayedType;

    private Wildlife[] slots;
    private Habitat[] habitats;
    private char colour2;
    private char colour;

    private boolean firstHabitat;
    private boolean secondHabitat;
    private final int select;
    private boolean firstPlaceHolder = false;

    private boolean secondPlaceHolder = false;

    private char animal;
    private char animal2;

    private char animal3;

    private boolean notRand = false;

    public Tile(int select) {
        // this is the constructor for the tile class that takes in a number between 1

        // and 3 to determine the type of tile
        // 1 being a single habitat tile
        //2 being a double habitat tile with 2 placeholders and 3 being a double habitat tile with three placeholders
        if (select > 3 || select < 1) {
            select = randomNumberGenerator(3) + 1;
            // this generates a number between 0 and 2
        }
        this.select = select;
        Played = false;
        tokenPlayed = false;
        tokenPlayedType = null;
        habitatCounted0 = false;
        habitatCounted1 = false;
        if (select == 2) {
            Habitat h;
            Habitat h2;
            habitats = new Habitat[2];
            firstHabitat = false;
            habitats[0] = randomHabitat();
            firstHabitat = true;
            habitats[1] = randomHabitat();


            if ((habitats[0].getSymbol()) == (habitats[1].getSymbol())) {
                notRand = true;
            }
            while (notRand) // makes sure the habitats are not the same on the two biomes tiles
            {
                habitats[1] = randomHabitat();

                if ((habitats[0].getSymbol()) != (habitats[1].getSymbol())) {
                    notRand = false;
                }

            }


            firstPlaceHolder = false;
            slots = new Wildlife[2];
            slots[0] = randomSlot();
            firstPlaceHolder = true;
            slots[1] = randomSlot();

            while (slots[0] == slots[1]) {
                // makes sure the placeholders are not the same on the two biomesS
                slots[1] = randomSlot();
            }
        } else if (select == 3) {
            habitats = new Habitat[2];
            firstHabitat = false;
            habitats[0] = randomHabitat();
            firstHabitat = true;
            habitats[1] = randomHabitat();
            while (habitats[0].equals(habitats[1])) {
                // makes sure the habitats are not the same on the two biomes

                // tiles
                habitats[1] = randomHabitat();
            }
            firstPlaceHolder = false;

            slots = new Wildlife[3];
            slots[0] = randomSlot();
            firstPlaceHolder = true;
            slots[1] = randomSlot();

            while (slots[0] == slots[1]) {
                // makes sure the habitats are not the MATCHING biomesS
                slots[1] = randomSlot();
            }
            secondPlaceHolder = true;
            slots[2] = randomSlot();

            while (slots[0] == slots[2] || slots[1] == slots[2]) {
                slots[2] = randomSlot();

            }

        } else if (select == 1) {
            habitats = new Habitat[] { randomHabitat() };
            slots = new Wildlife[] { randomSlot() };
        }
    }

    public boolean isTokenPlaced() {
        return tokenPlaced;
    }

    public void setTokenPlaced(boolean tokenPlaced) {
        this.tokenPlaced = tokenPlaced;
    }

    public void flipTile(Tile t) {
        // flips tiles by swapping its orientation in array


        if (t.getSelect() == 1) {
            System.out.println("Unable to flip one habitat tile ");
        } else {

            char tempColor = colour;
            setColour(colour2);
            setColour2(tempColor);

            Habitat temp = t.getHabitat(0);
            t.habitats[0] = t.habitats[1];
            t.habitats[1] = temp;
        }


    }

    public Habitat randomHabitat() {
        // generates and returns 1 of 5 habitats

        switch (randomNumberGenerator(5)) {

            case 0:
                if (firstHabitat) {
                    colour2 = 'R';

                } else {
                    colour = 'R';
                }

                return Habitat.RIVER;
            case 1:
                if (firstHabitat) {
                    colour2 = 'F';

                } else {
                    colour = 'F';
                }
                return Habitat.FOREST;
            case 2:
                if (firstHabitat) {
                    colour2 = 'M';
                } else {

                    colour = 'M';
                }
                return Habitat.MOUNTAIN;
            case 3:
                if (firstHabitat) {
                    colour2 = 'W';
                } else {

                    colour = 'W';
                }
                return Habitat.WETLANDS;
            case 4:
                if (firstHabitat) {
                    colour2 = 'P';
                } else {
                    colour = 'P';
                }
                return Habitat.PRAIRIE;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }

    public Wildlife randomSlot() {


        switch (randomNumberGenerator(5)) {
            // generates and returns 1 of 5 tokens
            case 0:

                if (!(firstPlaceHolder)) {
                    animal = 'H';
                    //for tiles with multiple placeholders booleans are used to keep track of what placeholders have been set
                }
                if (firstPlaceHolder && !secondPlaceHolder) {
                    animal2 = 'H';
                }
                if (secondPlaceHolder)

                {
                    animal3 = 'H';
                }

                return Wildlife.HAWK;
            case 1:
                if (!(firstPlaceHolder)) {
                    animal = 'S';
                }
                if (firstPlaceHolder && !secondPlaceHolder) {
                    animal2 = 'S';
                }
                if (secondPlaceHolder)
                {
                    animal3 = 'S';
                }

                return Wildlife.SALMON;
            case 2:
                if (!(firstPlaceHolder)) {
                    animal = 'E';
                }
                if (firstPlaceHolder && !secondPlaceHolder) {
                    animal2 = 'E';
                }
                if (secondPlaceHolder)
                {
                    animal3 = 'E';
                }

                return Wildlife.ELK;
            case 3:
                if (!(firstPlaceHolder)) {
                    animal = 'B';
                }
                if (firstPlaceHolder && !secondPlaceHolder) {
                    animal2 = 'B';
                }
                if (secondPlaceHolder)
                {
                    animal3 = 'B';
                }

                return Wildlife.BEAR;
            case 4:
                if (!(firstPlaceHolder)) {
                    animal = 'F';
                }
                if (firstPlaceHolder && !secondPlaceHolder) {
                    animal2 = 'F';
                }
                if (secondPlaceHolder)
                {
                    animal3 = 'F';
                }

                return Wildlife.FOX;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }

    public int getSelect() {
        return this.select;
    }

    public boolean isPlayed() {
        return this.Played;
    }

    public void playTile() {
        this.Played = true;
    }

    public void playToken() {
        this.tokenPlayed = true;
    }

    public static int randomNumberGenerator(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }

    public Wildlife[] getSlots() { // gives array
        return slots;
    }

    public Wildlife getSlot(int index) { // gives wildlife
        return slots[index];
    }

    public void setSlots(Wildlife wildlife, int index) { // sets wildlife in array
        this.slots[index] = wildlife;
    }

    public Habitat getHabitat(int index) {
        return habitats[index];
    }

    public Habitat[] getHabitats() {
        return habitats;
    }

    public void setHabitats(Habitat habitat, int index) {
        this.habitats[index] = habitat;
    }

    public char getColour() {
        return colour;
    }

    public char getColour2() {
        return colour2;
    }

    public void setColour2(char colour2) {
        this.colour2 = colour2;
    }

    public void setColour(char colour) {
        this.colour = colour;
    }

    public char getAnimal() {
        return animal;
    }

    public char getAnimal2() {
        return animal2;
    }

    public char getAnimal3() {
        return animal3;
    }

    @Override
    public String toString() {
        return "Tile{" +
                ", Played=" + Played +
                '}';
    }

    public String colourConverter(char s) {
        switch (s) {
            case 'R':
                return "\033[44m";
                // Blue
            case 'F':
                return "\033[42m";
                // Green
            case 'M':
                return "\033[47m";
                // White
            case 'W':
                return "\033[46m";
                // Cyan
            case 'P':
                return "\033[43m";
                // Yellow
            default:
                throw new IllegalArgumentException("Error");
        }
    }

    // function to colour placeholder/token
    public String colourAnimal(char s) {
        switch (s) {

            case 'H':
                return "\033[0;34m" + "H" + DisplayColour.RESET;
                // Blue
            case 'F':
                return "\033[0;33m" + "F" + DisplayColour.RESET;
                // Orange
            case 'E':
                return "\033[0;30m" + "E" + DisplayColour.RESET;
                // BLACK
            case 'S':
                return "\033[0;31m" + "S" + DisplayColour.RESET;
                // RED
            case 'B':
                return "\033[0;35m" + "B" + DisplayColour.RESET;
                // PURPLE
            default:
                throw new IllegalArgumentException("Error");
        }
    }

    public static Tile generateRandomTile() {
        Tile t = new Tile(0);
        return t;
    }

    public static Tile generateSpecificTile(int x) {
        Tile t = new Tile(x);
        return t;
    }

    public static void main(String[] args) {
    }
}
