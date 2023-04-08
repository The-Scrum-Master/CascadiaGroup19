/*Group: 19
*Team Name: Front Row
*Timi Salam- 2139203(Timisalam)
*Patrick Kelly-21204063(Patkelly17)
*Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
*/

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * This Class creates the object Player that hold the information relevant to
 * the individual Players in
 * the game such as board and tiles held. As well as the function necessary to
 * run the backend action
 * of the player
 * Major Methods: generate initial map .... sergio did it
 * - placeTile, takes in x and y coordinates checks to make sure it picked
 * location is adjaent
 * to another tile, if true puts into board at index
 * -pickPair, takes int, grabs the tile and token from that index of the river
 * and moves it into player
 * variable "heldTile" and replaces location on the river
 */

public class Player {
    private final int order;
    private int numberOfHawks;
    private final String name;
    private boolean firstTurnPlayed = false;

    public Tile[][] playerBoard;
    public MapGenerator map = new MapGenerator();
    public Tile heldTile;
    public Wildlife heldToken;
    public int natureTokenNumber;

    public Player(String name, int order) {
        this.name = name;
        this.order = order;
        heldTile = null;
        heldToken = null;
        natureTokenNumber = 0;
        playerBoard = new Tile[46][46];

    }

    public int getNumberOfHawks() {
        return numberOfHawks;
    }

    public void setNumberOfHawks(int numberOfHawks) {
        this.numberOfHawks = numberOfHawks;
    }

    public Tile[][] getPlayerBoard() {
        return playerBoard;
    }

    public Tile getBoardIndex(int column, int row) {
        return playerBoard[row][column];
    }

    public String getName() {
        return name;
    }

    public int getNatureTokenNumber() {
        return natureTokenNumber;
    }

    public Tile getHeldTile() {
        return heldTile;
    }

    public Wildlife getHeldToken() {
        return heldToken;
    }

    public void setHeldToken(Wildlife token) {
        heldToken = token;
    }

    public void setHeldTile(Tile tile) {
        heldTile = tile;
    }

    public int getOrder() {
        return order;
    }

    public void reduceNatureTokenNumberByOne() {
        natureTokenNumber--;
        System.out.println("You now have " + natureTokenNumber + " NatureTokens left");
    }

    public boolean isFirstTurnPlayed() {
        return firstTurnPlayed;
    }

    private boolean isFilled = false;
    private boolean hasToken = false;
    private boolean tokenPlaced = false;

    public void generateInitialMap() {
        TileGenerator blank = new TileGenerator();
        blank.blankTile();
        playerBoard[22][22] = new Tile(1); // generates the three starting tiles
        playerBoard[22][22].playTile(); // changes boolean on tile to played.
        playerBoard[23][22] = new Tile(2);
        playerBoard[23][22].playTile();
        playerBoard[23][23] = new Tile(3);
        playerBoard[23][23].playTile();

        TileGenerator InitialTileSingleColoured = new TileGenerator(playerBoard[22][22]);
        TileGenerator InitialTileDoubleColoured1 = new TileGenerator(playerBoard[23][22]);
        TileGenerator InitialTileDoubleColoured2 = new TileGenerator(playerBoard[23][23]);

        // map.fillMapBlank(blank);
        map.fillMapBlank(blank);
        map.starterTiles(InitialTileSingleColoured, InitialTileDoubleColoured1, InitialTileDoubleColoured2);
        map.printMapTotalByCircle(0);

        firstTurnPlayed = true;
    }

    public void printMap(int helperInt) {
        map.printMapTotalByCircle(helperInt);
    }

    public void placeTile(int x, int y) {
        if (heldTile == null) {
            throw new IllegalArgumentException("held tile is null when calling place tile");
        }

        this.heldTile.playTile();
        if (playerBoard[x][y] != null || (playerBoard[x - 1][y] == null && playerBoard[x][y - 1] == null &&
                playerBoard[x + 1][y] == null && playerBoard[x][y + 1] == null)) {
            System.out.println("The board location for the tile placement is not a valid location,\n" +
                    " you must place it adjacent to another tile. Please try again,input x and y");
            int x_axis = IOcascadia.takeIntInput();
            int y_axis = IOcascadia.takeIntInput();
            placeTile(x_axis, y_axis);
            // recursive call to allow player to place tile again
        } else {
            heldTile.playTile();

            playerBoard[x][y] = heldTile;
            TileGenerator helpTileGenerator = new TileGenerator(heldTile);
            map.setTile(helpTileGenerator, x, y);
            // calls habitat score to check if there is a group

        }
    }

    public void placeToken(int x, int y) {
        if (x < 0 || x >= 46 || y < 0 || y >= 46) {
            System.out.println("Error placed tile out of bounds please try again");
            int x_axis = IOcascadia.takeIntInput();
            int y_axis = IOcascadia.takeIntInput();
            placeToken(x_axis, y_axis);
        }
        if (playerBoard[x][y] == null || map.getMap()[x][y].getEmptyTile() || playerBoard[x][y].getTokenPlaced()) {
            System.out.println("The board location for the Token placement is not a valid location,\n" +
                    "you must place it on a previously played tile. Please try again");
            int x_axis = IOcascadia.takeIntInput();
            int y_axis = IOcascadia.takeIntInput();
            placeToken(x_axis, y_axis);
        } else {
            playerBoard[x][y].playToken();
            for (int i = 0; i < playerBoard[x][y].getSlots().length; i++)
            // checks to see if the tiles has a slot for the token on the board
            {
                if (playerBoard[x][y].getSlot(i) == heldToken) {

                    TileGenerator helpTileGenerator = new TileGenerator(playerBoard[x][y]);
                    if (playerBoard[x][y].getSelect() == 1)
                    // checks to see if the tile is a single habitat tile
                    {
                        helpTileGenerator.tileUniqueColorPlacedToken(
                                playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()),
                                playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal()));
                        playerBoard[x][y].setTokenPlaced(true);

                        System.out.println(
                                "You have gained a nature token because you placed a wildlife token on a single-habitat tile");
                        natureTokenNumber++;
                        isFilled = true;

                    } else if (playerBoard[x][y].getSelect() == 2)
                    // checks to see if the tile is a double wildlife
                    // tile
                    {

                        if (i == 0)
                        // checks to see if the token is the first token on the tile
                        {
                            helpTileGenerator.tileTwoColorsPlacedToken(
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()),
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),
                                    playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal()));
                            playerBoard[x][y].setTokenPlaced(true);
                        }
                        if (i == 1)// checks to see if the token is the second token on the tile
                        {
                            helpTileGenerator.tileTwoColorsPlacedToken(
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()),
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),
                                    playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal2()));
                            playerBoard[x][y].setTokenPlaced(true);
                        }
                        isFilled = true;

                    } else if (playerBoard[x][y].getSelect() == 3)
                    // checks to see if the tile is a triple wildlife tile
                    {
                        if (i == 0) {
                            helpTileGenerator.tileTwoColorsPlacedToken(
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()),
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),
                                    playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal()));
                            playerBoard[x][y].setTokenPlaced(true);
                        }
                        if (i == 1) {
                            helpTileGenerator.tileTwoColorsPlacedToken(
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()),
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),
                                    playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal2()));
                            playerBoard[x][y].setTokenPlaced(true);
                        }
                        if (i == 2) {
                            helpTileGenerator.tileTwoColorsPlacedToken(
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()),
                                    playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),
                                    playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal3()));
                            playerBoard[x][y].setTokenPlaced(true);
                        }
                        isFilled = true;

                    }
                    map.setTile(helpTileGenerator, x, y);
                    playerBoard[x][y].tokenPlayedType = heldToken;
                    heldToken = null;
                }
            }
            if (!isFilled) {
                System.out.println("No placeholder matches your held token");
                System.out.println("Please pick a different tile input x and y below");
                int x_axis = IOcascadia.takeIntInput();
                int y_axis = IOcascadia.takeIntInput();
                placeToken(x_axis, y_axis);
                // if tile they have selected does not have placeholder that matches heldtoken
                // retry
            }
        }
    }
    // need to go through every tile on the board and be able to check if
    // placeholder matches held token

    public void setIsFilledToFalse(){
        isFilled=false;
    }

    public boolean checkToken() {
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard.length; j++) {

                if(map.getMap()[i][j].getEmptyTile()){
                    continue;
                }
                if (playerBoard[i][j] != null) {
                    if (playerBoard[i][j].getSelect() == 1) {
                        if (playerBoard[i][j].getSlot(0) == heldToken && !playerBoard[i][j].getTokenPlaced()) {
                            hasToken = true;
                        }
                    }

                    else if (playerBoard[i][j].getSelect() == 2) {
                        for (int k = 0; k < 2; k++) {
                            if (playerBoard[i][j].getSlot(k) == heldToken && !playerBoard[i][j].getTokenPlaced()) {
                                hasToken = true;
                            }
                        }
                    } else if (playerBoard[i][j].getSelect() == 3) {
                        for (int k = 0; k < 3; k++) {
                            if (playerBoard[i][j].getSlot(k) == heldToken && !playerBoard[i][j].getTokenPlaced()) {
                                hasToken = true;
                            }

                            // idk where to put the istoken played thing in this function
                            // its gonna check and be like oh space available then it'll see if there is
                            // a token there already
                        }
                    }

                }
            }

        }
        if (!hasToken) {
            System.out.println("No placeholders match your held token");
            return true;

        }
        if (tokenPlaced) {
            System.out.println("Tile already has a token");// do later need to check for all scenerios
            return true;
        }

        else {
            return false;
        }
    }// if tile already has a token not let place

    public void splitPick() {
        // is called when the player chooses to pick a tile and a token from the river
        // at different indexes
        System.out.println("You have chosen to pick any one tile and wildlife token from the river" +
                "\nEnter the index of the tile you would like to chose:");
        int indexChoiceTile = IOcascadia.takeIntInput() - 1;
        while (indexChoiceTile < 0 || indexChoiceTile > 3) {
            System.out.println("The number you have inputted is not a valid index.");
            indexChoiceTile = IOcascadia.takeIntInput();
        }
        this.heldTile = TileDeck.getRiverTilesIndex(indexChoiceTile);
        // moves the tile from the river to the players hand
        TileDeck.ReplaceRiverTilesIndex(indexChoiceTile);
        // replaces the tile in the river with a new tile
        TileDeck.emptyDeckCheck();

        System.out.println("Enter the index of the token you would like to chose:");
        int indexChoiceToken = IOcascadia.takeIntInput() - 1;
        while (indexChoiceToken < 0 || indexChoiceToken > 3) {
            System.out.println("The number you have inputted is not a valid index.");
            indexChoiceToken = IOcascadia.takeIntInput();
        }
        this.heldToken = TileDeck.getRiverTokensIndex(indexChoiceToken);
        TileDeck.ReplaceRiverTokensIndex(indexChoiceToken);
        // replaces the token in the river with a new token
        natureTokenNumber--;
        System.out.println("You now have " + natureTokenNumber + " NatureTokens left");
    }

    public void pickPair(int indexOfSelected) {
        // called when the player chooses to pick a tile and a token from the river at
        // the same index
        this.heldTile = TileDeck.getRiverTilesIndex(indexOfSelected);
        this.heldToken = TileDeck.getRiverTokensIndex(indexOfSelected);
        TileDeck.ReplaceRiverTilesIndex(indexOfSelected);
        TileDeck.ReplaceRiverTokensIndex(indexOfSelected);
        TileDeck.emptyDeckCheck();
    }
    public int riverMax = 1;
    public int forestMax = 1;
    public int prairieMax = 1;
    public int mountainMax = 1;
    public int wetlandMax = 1;

    public void habitatMaxTracker(Habitat habitat) {
        // if the counter is greater than the max, set the max to the counter
        switch (habitat) {
            case RIVER:
                if (counted.size() > riverMax) {
                    riverMax = counted.size();
                }
                break;
            case FOREST:
                if (counted.size() > forestMax) {
                    forestMax = counted.size();
                }
                break;
            case PRAIRIE:
                if (counted.size() > prairieMax) {
                    prairieMax = counted.size();
                }
                break;
            case MOUNTAIN:
                if (counted.size() > mountainMax) {
                    mountainMax = counted.size();
                }
                break;
            case WETLANDS:
                if (counted.size() > wetlandMax) {
                    wetlandMax = counted.size();
                }
                break;
        }
    }

    ArrayList<Tile> counted = new ArrayList<Tile>();

    public boolean countedHelp(Tile tile) {
        for (int i = 0; i < counted.size(); i++) {
            if (tile.equals(counted.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void recursiveHabibitCounter(int rows, int columns, Habitat habitat) {
        // recusively moves though tiles checking for habitat matches
        if (adjCheck(columns - 1, rows, 0, habitat)) {
            // checks left
            if (countedHelp(playerBoard[rows][columns - 1]) != true) {
                // makes sure hasnt already been counted
                // adds the tiles to a array of counted tiles
                counted.add(playerBoard[rows][columns - 1]);
                recursiveHabibitCounter(rows, columns - 1, habitat);
                // recusive call
            }
            habitatMaxTracker(habitat);
        }
        if (adjCheck(columns, rows - 1, 0, habitat)) {
            // checks above
            if (countedHelp(playerBoard[rows - 1][columns]) != true) {
                // makes sure hasnt already been counted
                counted.add(playerBoard[rows - 1][columns]);
                recursiveHabibitCounter(rows - 1, columns, habitat);
                // recusive call
            }
            habitatMaxTracker(habitat);
        }
        if (adjCheck(columns + 1, rows, 1, habitat)) {
            // checks right
            if (countedHelp(playerBoard[rows][columns + 1]) != true) {
                // makes sure hasnt already been counted
                counted.add(playerBoard[rows][columns + 1]);
                recursiveHabibitCounter(rows, columns + 1, habitat);
                // recusive call
            }
            habitatMaxTracker(habitat);
        }
        if (adjCheck(columns, rows + 1, 1, habitat)) {
            // checks below
            if (countedHelp(playerBoard[rows + 1][columns]) != true) {
                // makes sure hasnt already been counted
                counted.add(playerBoard[rows + 1][columns]);
                recursiveHabibitCounter(rows + 1, columns, habitat);
                // recusive call
            }
            habitatMaxTracker(habitat);
        }
    }



    public boolean adjCheck(int x, int y, int direction, Habitat habitat) {

        if (playerBoard[y][x] != null) {
            Tile adjTile = playerBoard[y][x];

            if (adjTile.getSelect() == 1) {
                // If the tile is a solo then no need to check which direction habitat is on
                if (adjTile.getHabitat(0) == habitat) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // Need to check which direction to check;
                if (direction == 0) {
                    // only checks the bottom and right side of the tile (aka habitat index 1)
                    if (adjTile.getHabitat(1) == habitat) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // otherwise check this tiles top and left side of this tile (aka habitat index
                    // 0)
                    if (adjTile.getHabitat(0) == habitat) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }

    public void checkHabitats() {
        for (int i = 0 + 21 - GameRunner.getHelperIntToPrintMap(); i < 46 - 21
                + GameRunner.getHelperIntToPrintMap(); i++) {
            for (int j = 0 + 21 - GameRunner.getHelperIntToPrintMap(); j < 46 - 21
                    + GameRunner.getHelperIntToPrintMap(); j++) {
                Tile curr = playerBoard[i][j];
                if (curr != null) {
                    if (curr.getSelect() == 1) {
                        if (adjCheck(j - 1, i, 0, curr.getHabitat(0))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(0));
                            counted.removeAll(counted);
                        }
                        if (adjCheck(j, i - 1, 0, curr.getHabitat(0))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(0));
                            counted.removeAll(counted);
                        }
                        if (adjCheck(j, i + 1, 1, curr.getHabitat(0))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(0));
                            counted.removeAll(counted);
                        }
                        if (adjCheck(j + 1, i, 1, curr.getHabitat(0))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(0));
                            counted.removeAll(counted);
                        }
                    } else {
                        if (adjCheck(j - 1, i, 0, curr.getHabitat(0))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(0));
                            counted.removeAll(counted);
                        }
                        if (adjCheck(j, i - 1, 0, curr.getHabitat(0))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(0));
                            counted.removeAll(counted);
                        }
                        if (adjCheck(j, i + 1, 1, curr.getHabitat(1))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(1));
                            counted.removeAll(counted);
                        }
                        if (adjCheck(j + 1, i, 1, curr.getHabitat(1))) {
                            recursiveHabibitCounter(i, j, curr.getHabitat(1));
                            habitatMaxTracker(curr.getHabitat(1));
                            counted.removeAll(counted);
                        }
                    }
                    counted.removeAll(counted);
                }
            }
        }
    }

    public void habitatScore() {

        checkHabitats();
        System.out.println("the max forest gorup is " + forestMax);
        System.out.println("the max prairie gorup is " + prairieMax);
        System.out.println("the max mountian gorup is " + mountainMax);
        System.out.println("the max river gorup is " + riverMax);
        System.out.println("the max wetland gorup is " + wetlandMax);

    }

    public int getForestMax() {
        return forestMax;
    }

    public int getMountainMax() {
        return mountainMax;
    }

    public int getPrairieMax() {
        return prairieMax;
    }

    public int getRiverMax() {
        return riverMax;
    }

    public int getWetlandMax() {
        return wetlandMax;
    }

    public void findBestHabitat() {

        ArrayList<Habitat> heldTileHabitats = new ArrayList<Habitat>();
        boolean found = false;
        boolean placed = false;
        for (int k = 0; k < heldTile.getHabitats().length; k++) {
            heldTileHabitats.add(heldTile.getHabitat(k));
        }

        for (int x = 0; x < playerBoard.length; x++) {
            for (int y = 0; y < playerBoard.length; y++) {
                if (!map.getMap()[x][y].getEmptyTile()) {
                    ArrayList<Habitat> playerTileHabitats = new ArrayList<Habitat>();
                    // searching through placed tiles
                    // if tile is not null
                    for (int k = 0; k < playerBoard[x][y].getHabitats().length; k++) {
                        playerTileHabitats.add(playerBoard[x][y].getHabitat(k));
                    }

                    // filling arrays with held tile habitats and selected tile habitats
                    for (int i = 0; i < playerTileHabitats.size(); i++) {
                        for (int j = 0; j < heldTileHabitats.size(); j++) {

                            if (playerTileHabitats.get(i).equals(heldTileHabitats.get(j))) {
                                found = true;
                            }
                        }
                    }
                    // checking if they have matching habitats

                }
                if (found) {
                    searchRadius(x, y);
                    return;

                }

            }
        }

    }

    public void searchRadius(int x, int y) {

        if (map.getMap()[x][y + 1].getEmptyTile()) {
            placeTile(x, y + 1);

        } else if (map.getMap()[x][y - 1].getEmptyTile()) {
            placeTile(x, y - 1);

        } else if (map.getMap()[x - 1][y].getEmptyTile()) {
            placeTile(x - 1, y);

        } else if (map.getMap()[x + 1][y].getEmptyTile()) {
            placeTile(x + 1, y);

        }

    }

    public int chooseFromRiver() {
        Habitat[] playerTileHabitats;
        ArrayList<Integer> potentialSuitors = new ArrayList<Integer>();

        for (int x = 0; x < playerBoard.length; x++) {
            for (int y = 0; y < playerBoard.length; y++) {
                // search through entire map
                if (!map.getMap()[x][y].getEmptyTile()) {
                    // find where tiles have been placed
                    playerTileHabitats = playerBoard[x][y].getHabitats();

                    if (playerBoard[x][y].getSelect() == 1) {
                        // if habitats match select that tile from river
                        for (int k = 0; k < 4; k++) {
                            for (int w = 0; w < TileDeck.getRiverTilesIndex(k).getHabitats().length; w++) {
                                if (playerTileHabitats[0].equals(TileDeck.getRiverTilesIndex(k).getHabitat(w))) {
                                    potentialSuitors.add(k);
                                }

                            }
                        }

                    } else {
                        int k = 0;
                        while (k < 4) {
                            for (int i = 0; i < playerTileHabitats.length; i++) {
                                for (int j = 0; j < TileDeck.getRiverTilesIndex(k).getHabitats().length; j++) {
                                    if (playerTileHabitats[i].equals(TileDeck.getRiverTilesIndex(k).getHabitat(j))) {
                                        // if habitats match put indexes of those tiles into an arraylist
                                        potentialSuitors.add(k);
                                    }
                                }
                            }
                            k++;
                        }

                    }
                }
            }
        }
        for (int i = 0; i < potentialSuitors.size(); i++) {
            for (int j = i + 1; j < potentialSuitors.size(); j++) {
                if (Objects.equals(potentialSuitors.get(i), potentialSuitors.get(j))) {
                    potentialSuitors.remove(j);
                    j--;
                }
            }
        }
        Random rand = new Random();
        int randomNum;

        if (potentialSuitors.size() == 0) {
            randomNum = rand.nextInt(4);

        } else {
            randomNum = rand.nextInt(potentialSuitors.size());

        }
        return potentialSuitors.get(randomNum);

        // randomly select one of the potential suitors

    }

    public MapGenerator getMap() {
        return map;
    }
}