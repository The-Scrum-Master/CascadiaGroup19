/*Group: 19
*Team Name: Front Row
*Timi Salam- 21392803(Timisalam)
*Patrick Kelly-21204063(Patkelly17)
*Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
*/

import java.util.ArrayList;
import java.util.Arrays;
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
    private int numberOfBearPairs = 0;
    private int numberOfHawks = 0;
    private int numberOfFoxes = 0;
    private final String name;
    private boolean firstTurnPlayed = false;

    public Tile[][] playerBoard;
    public MapGenerator map = new MapGenerator();
    public Tile heldTile;
    public Wildlife heldToken;
    public int natureTokenNumber;
    ArrayList<MaxCorridor> potentialSuitors = new ArrayList<>();


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

    public int getNumberOfBearPairs() {
        return numberOfBearPairs;
    }

    public void setNumberOfBearPairs(int numberOfBearPairs) {
        this.numberOfBearPairs = numberOfBearPairs;
    }
    public int getNumberOfFoxes() {
        return numberOfFoxes;
    }
    public void setNumberOfFoxes(int numberOfFoxes) {
        this.numberOfFoxes = numberOfFoxes;
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
    private final boolean tokenPlaced = false;

    public void generateInitialMap() {
        TileGenerator blank = new TileGenerator();
        blank.blankTile();
        playerBoard[22][22] = new Tile(1); // generates the three starting tiles
        playerBoard[22][22].playTile(); // changes boolean on tile to played.
        playerBoard[22][22].setBoardIndex(22, 22);
        playerBoard[23][22] = new Tile(2);
        playerBoard[23][22].playTile();
        playerBoard[23][22].setBoardIndex(22, 23);
        playerBoard[23][23] = new Tile(3);
        playerBoard[23][23].playTile();
        playerBoard[23][23].setBoardIndex(23, 23);

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
            playerBoard[x][y].setBoardIndex(x, y);
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

                        System.out.println("You have gained a nature token because you placed a wildlife token on a single-habitat tile");
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
                // if tile they have selected does not have placeholder that matches heldtoken retry
            }
        }
    }
    // need to go through every tile on the board and be able to check if
    // placeholder matches held token

    public void setIsFilledToFalse() {
        isFilled = false;
    }

    public boolean checkToken() {
        hasToken=false;
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard.length; j++) {

                if (playerBoard[i][j] != null &&!playerBoard[i][j].tokenPlayed) {

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

                    else{
                        hasToken = false;
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

    // TODO
    // - function returns arraylist of largest corridores
    // - Arraylist of the cords of all the tiles in that habitat
    // - the type of the habitat that that corridore is

    public int riverMax = 0;
    public int forestMax = 0;
    public int prairieMax = 0;
    public int mountainMax = 0;
    public int wetlandMax = 0;

    //makes an array the maxcorder objects 
    public MaxCorridor[] createMaxCorridorsArray() {
        MaxCorridor[] ordered = corridors;
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                int CorJ = ordered[j].getXcordArrayList().size();
                int CorI = ordered[i].getXcordArrayList().size();
                if (CorJ > CorI) {
                    MaxCorridor temp = ordered[i];
                    ordered[i] = ordered[j];
                    ordered[j] = temp;
                }
            }
        }
        for (int k = 0; k < 5; k++) {
            System.out.println(ordered[k].getXcordArrayList().size());
        }
        return ordered;
    }

    public MaxCorridor[] corridors = {
            // array holds all the diffent corridors for each habitat
            new MaxCorridor(Habitat.RIVER),
            new MaxCorridor(Habitat.FOREST),
            new MaxCorridor(Habitat.PRAIRIE),
            new MaxCorridor(Habitat.MOUNTAIN),
            new MaxCorridor(Habitat.WETLANDS) };

    //sets the max corridors initially 
    public void IntializeCorridors() {
        for (int i = 0; i < corridors.length; i++) {
            if (corridors[i].getHabitatType().equals(playerBoard[22][22].getHabitat(0))) {
                corridors[i].getXcordArrayList().add(22);
                corridors[i].getYcordArrayList().add(22);
            } else if (corridors[i].getHabitatType().equals(playerBoard[23][22].getHabitat(0))) {
                corridors[i].getXcordArrayList().add(22);
                corridors[i].getYcordArrayList().add(23);
            } else if (corridors[i].getHabitatType().equals(playerBoard[23][22].getHabitat(1))) {
                corridors[i].getXcordArrayList().add(22);
                corridors[i].getYcordArrayList().add(23);
            } else if (corridors[i].getHabitatType().equals(playerBoard[23][23].getHabitat(0))) {
                corridors[i].getXcordArrayList().add(23);
                corridors[i].getYcordArrayList().add(23);
            } else if (corridors[i].getHabitatType().equals(playerBoard[23][23].getHabitat(1))) {
                corridors[i].getXcordArrayList().add(23);
                corridors[i].getYcordArrayList().add(23);
            } else {
                System.out.println("The corridor max of " + corridors[i].getHabitatType() + " is 0 to start");
            }
        }
        riverMax = corridors[0].getXcordArrayList().size();
        forestMax = corridors[1].getXcordArrayList().size();
        prairieMax = corridors[2].getXcordArrayList().size();
        mountainMax = corridors[3].getXcordArrayList().size();
        wetlandMax = corridors[4].getXcordArrayList().size();
    }

    public void habitatMaxTracker(Habitat habitat) {
        // if the counter is greater than the max, set the max to the counter
        switch (habitat) {
            case RIVER:
                if (counted.size() > riverMax) {
                    riverMax = counted.size();
                    for (int i = 0; i < counted.size(); i++) {
                        // corridors[0] contains max corridor info for river
                        if (!trackerHelp(0, counted.get(i).boardXIndex, counted.get(i).boardYIndex)) {
                            corridors[0].getXcordArrayList().add(counted.get(i).boardXIndex);
                            System.out.print("The cords are: " + counted.get(i).boardXIndex + "\t");
                            corridors[0].getYcordArrayList().add(counted.get(i).boardYIndex);
                            System.out.println(counted.get(i).boardYIndex);
                        }
                    }
                }
                break;
            case FOREST:
                if (counted.size() > forestMax) {
                    forestMax = counted.size();
                    for (int i = 0; i < counted.size(); i++) {
                        // corridors[1] contains max corridor info for forrest
                        if (!trackerHelp(1, counted.get(i).boardXIndex, counted.get(i).boardYIndex)) {
                            corridors[1].getXcordArrayList().add(counted.get(i).boardXIndex);
                            System.out.print("The cords are: " + counted.get(i).boardXIndex + "\t");
                            corridors[1].getYcordArrayList().add(counted.get(i).boardYIndex);
                            System.out.println(counted.get(i).boardYIndex);
                        }
                    }
                }
                break;
            case PRAIRIE:
                if (counted.size() > prairieMax) {
                    prairieMax = counted.size();
                    for (int i = 0; i < counted.size(); i++) {
                        // corridors[2] contains max corridor info for prairies
                        if (!trackerHelp(2, counted.get(i).boardXIndex, counted.get(i).boardYIndex)) {
                            corridors[2].getXcordArrayList().add(counted.get(i).boardXIndex);
                            System.out.print("The cords are: " + counted.get(i).boardXIndex + "\t");
                            corridors[2].getYcordArrayList().add(counted.get(i).boardYIndex);
                            System.out.println(counted.get(i).boardYIndex);
                        }
                    }
                }
                break;
            case MOUNTAIN:
                if (counted.size() > mountainMax) {
                    mountainMax = counted.size();
                    for (int i = 0; i < counted.size(); i++) {
                        // corridors[3] contains max corridor info for mountains
                        if (!trackerHelp(3, counted.get(i).boardXIndex, counted.get(i).boardYIndex)) {
                            corridors[3].getXcordArrayList().add(counted.get(i).boardXIndex);
                            System.out.print("The cords are: " + counted.get(i).boardXIndex + "\t");
                            corridors[3].getYcordArrayList().add(counted.get(i).boardYIndex);
                            System.out.println(counted.get(i).boardYIndex);
                        }
                    }
                }
                break;
            case WETLANDS:
                if (counted.size() > wetlandMax) {
                    wetlandMax = counted.size();
                    for (int i = 0; i < counted.size(); i++) {
                        // corridors[4] contains max corridor info for wetlands
                        if (!trackerHelp(4, counted.get(i).boardXIndex, counted.get(i).boardYIndex)) {
                            corridors[4].getXcordArrayList().add(counted.get(i).boardXIndex);
                            System.out.print("The cords are: " + counted.get(i).boardXIndex + "\t");
                            corridors[4].getYcordArrayList().add(counted.get(i).boardYIndex);
                            System.out.println(counted.get(i).boardYIndex);
                        }
                    }
                }
                break;
        }
    }

    ArrayList<Tile> counted = new ArrayList<Tile>();

    //if the tile is already counted in the array retrun true
    public boolean countedHelp(Tile tile) {
        for (int i = 0; i < counted.size(); i++) {
            if (tile.equals(counted.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean trackerHelp(int index, int x, int y) {
        for (int i = 0; i < corridors[index].getXcordArrayList().size(); i++) {
            if (corridors[index].getXcordArrayList().get(i).equals(x) &&
                    corridors[index].getYcordArrayList().get(i).equals(y)) {
                return true;
            }
        }
        return false;
    }

    public void recursiveHabibitCounter(int rows, int columns, Habitat habitat) {
        // recusively moves though tiles checking for habitat matches
        if (adjCheck(columns - 1, rows, 0, habitat)) {
            // checks left
            if (!countedHelp(playerBoard[rows][columns - 1])) {
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
            if (!countedHelp(playerBoard[rows - 1][columns])) {
                // makes sure hasnt already been counted
                counted.add(playerBoard[rows - 1][columns]);
                recursiveHabibitCounter(rows - 1, columns, habitat);
                // recusive call
            }
            habitatMaxTracker(habitat);
        }
        if (adjCheck(columns + 1, rows, 1, habitat)) {
            // checks right
            if (!countedHelp(playerBoard[rows][columns + 1])) {
                // makes sure hasnt already been counted
                counted.add(playerBoard[rows][columns + 1]);
                recursiveHabibitCounter(rows, columns + 1, habitat);
                // recusive call
            }
            habitatMaxTracker(habitat);
        }
        if (adjCheck(columns, rows + 1, 1, habitat)) {
            // checks below
            if (!countedHelp(playerBoard[rows + 1][columns])) {
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
                return adjTile.getHabitat(0) == habitat;
            } else {
                // Need to check which direction to check;
                if (direction == 0) {
                    // only checks the bottom and right side of the tile (aka habitat index 1)
                    return adjTile.getHabitat(1) == habitat;
                } else {
                    // otherwise check this tiles top and left side of this tile (aka habitat index
                    // 0)
                    return adjTile.getHabitat(0) == habitat;
                }
            }
        } else {
            return false;
        }
    }

    public void checkHabitats() {
        for (int i = 21 - GameRunner.getHelperIntToPrintMap(); i < 46 - 21
                + GameRunner.getHelperIntToPrintMap(); i++) {
            for (int j = 21 - GameRunner.getHelperIntToPrintMap(); j < 46 - 21
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

    public MaxCorridor[] habitatScore() {
        IntializeCorridors();
        checkHabitats();
        MaxCorridor[] top = createMaxCorridorsArray();
        return top;
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

    public void findBestPosition(int x) {
        System.out.println("Itss me find best habitat");
//potential edge case where if tile which coords point to is surrounded error have code foe this but need to wait till pat fixes his function
//need to know what direction tiles are pointing in

        for (int i = 0; i < potentialSuitors.size(); i++) {
            for (int j = 0; j < heldTile.getHabitats().length; j++) {
                if (potentialSuitors.get(i).getHabitatType().equals(heldTile.getHabitat(j))) {
                    ArrayList<Integer> X = potentialSuitors.get(i).getXcordArrayList();
                    ArrayList<Integer> Y = potentialSuitors.get(i).getYcordArrayList();
                    System.out.println("THis is the length of the coords array "+potentialSuitors.get(i).getXcordArrayList().size());
                    searchRadius(Y.get(x),X.get(x));
                    return;

                }
            }
        }
    }

    public boolean searchRadius(int x, int y) {
        //0 is top 1 is bottom
        System.out.println("its me search radius");
        System.out.println("the coordinnates I have been given are "+x+","+y);
        if (map.getMap()[x][y + 1].getEmptyTile()) {
            if(heldTile.getSelect()==1){
                placeTile(x, y + 1);
                return true;
            }else {
                if(playerBoard[x][y].getSelect()==1){
                    placeTile(x, y + 1);
                    return true;

                }
                else if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    placeTile(x, y + 1);
                    return true;

                } else {
                    heldTile.flipTile(heldTile);
                    TileGenerator g = new TileGenerator(heldTile);
                    g.generateFlipTile();
                    System.out.println("I have decided to flip the tile");
                    g.printTile();
                    placeTile(x, y + 1);
                    return true;

                }
            }


        } else if (map.getMap()[x][y - 1].getEmptyTile()) {
            if(heldTile.getSelect()==1){
                placeTile(x, y - 1);
                return true;

            }else {
                if(playerBoard[x][y].getSelect()==1){
                    placeTile(x, y - 1);
                    return true;

                }
                else if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                    placeTile(x, y - 1);
                    return true;

                } else {
                    heldTile.flipTile(heldTile);
                    TileGenerator g = new TileGenerator(heldTile);
                    g.generateFlipTile();
                    System.out.println("I have decided to flip the tile");
                    g.printTile();
                    placeTile(x, y - 1);
                    return true;

                }
            }
        } else if (map.getMap()[x - 1][y].getEmptyTile()) {
            if(heldTile.getSelect()==1){
                placeTile(x-1, y);
                return true;

            }else {
                if(playerBoard[x][y].getSelect()==1){
                    placeTile(x - 1, y);
                    return true;
                }
                else if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                    placeTile(x - 1, y);
                    return true;

                } else {
                    heldTile.flipTile(heldTile);
                    TileGenerator g = new TileGenerator(heldTile);
                    g.generateFlipTile();
                    System.out.println("I have decided to flip the tile");
                    g.printTile();
                    placeTile(x - 1, y);
                    return true;
                }
            }


        } else if (map.getMap()[x + 1][y].getEmptyTile()) {
            if(heldTile.getSelect()==1){
                placeTile(x+1, y );
                return true;

            }else {
                if(playerBoard[x][y].getSelect()==1){
                    placeTile(x + 1, y);
                    return true;
                }
                else if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    placeTile(x + 1, y);
                    return true;

                } else {
                    heldTile.flipTile(heldTile);
                    TileGenerator g = new TileGenerator(heldTile);
                    g.generateFlipTile();
                    System.out.println("I have decided to flip the tile");
                    g.printTile();
                    placeTile(x + 1, y);
                    return true;

                }
            }

        }
        else{
            System.out.println("FAILED");
            findSecondBestPosition();

            //sometimes the coords of the top dog is surrounded need to pick the next best one
        }
        return false;

    }
    public void findSecondBestPosition() {

  int poo =0;
        for (int x = 0; x < playerBoard.length; x++) {
            for (int y = 0; y < playerBoard.length; y++) {
                if (!map.getMap()[x][y].getEmptyTile()) {
                    innerloop:
                    for(int k=0;k<playerBoard[x][y].getHabitats().length;k++) {
                        for(int l=0;l<heldTile.getHabitats().length;l++) {
                            if (heldTile.getHabitat(l).equals(playerBoard[x][y].getHabitat(k))) {
                                if(x==potentialSuitors.get(0).getYcordArrayList().get(0)){
                                    System.out.println("Entered if statement");
                                    y++;
                                    break innerloop;


                                }

                                searchRadius(x, y);
                                return;
                            }
                        }
                    }

                }

            }
        }
    }
    public boolean checkTilePlacement(int x,int y) {
        if (map.getMap()[x][y + 1].getEmptyTile()) {
            return true;

        } else if (map.getMap()[x][y - 1].getEmptyTile()) {
            return true;


        } else if (map.getMap()[x - 1][y].getEmptyTile()) {
            return true;

        } else return map.getMap()[x + 1][y].getEmptyTile();
    }

    public int chooseFromRiver() {
        System.out.println("its me choose from river");
        potentialSuitors = new ArrayList<>();
        MaxCorridor[] maxQuarters = habitatScore();
        potentialSuitors.addAll(Arrays.asList(maxQuarters).subList(0, habitatScore().length));
        boolean hasBestHabitat = false;

        int position = 0;
       /* int maxQuarterRanking = 0;
        boolean isInvalidSuitor = true;
        if(isAvailable(maxQuarterRanking)){
           isInvalidSuitor =false;
        }
        while (isInvalidSuitor){
            maxQuarterRanking++;
            if(isAvailable(maxQuarterRanking)){
                isInvalidSuitor = false;
            }
        }*/
        //checking if positions for MaxCorridor are available
       if(checkSingleTileInRiver(0)>0){
           return checkSingleTileInRiver(0);
       }
       int k = 0;
        while (!hasBestHabitat) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < TileDeck.getRiverTilesIndex(i).getHabitats().length; j++) {
                    if (potentialSuitors.get(k).getHabitatType().equals(TileDeck.getRiverTilesIndex(i).getHabitat(j))) {
                        hasBestHabitat = true;
                        position = i;
                        System.out.println("this is the max quarter habitat " + potentialSuitors.get(k).getHabitatType());
                    }
                }
                if(hasBestHabitat){
                    return position;
                }

            }
            k++;
            if(checkSingleTileInRiver(k)>0){
                return checkSingleTileInRiver(k);
            }
        }
        //loops through the tiles in the river and checks if their habitat types match the habitat type of max quarter





        return -1;
    }
    public int checkSingleTileInRiver(int habitatToCheck){
        //checks if single habitat tile in river matches max quarter habitat if so return index of tile in river

        ArrayList<Integer> singleTileIndexes = new ArrayList<Integer>();
        for(int i=0;i<TileDeck.getRiverTiles().length;i++){
            if(TileDeck.getRiverTilesIndex(i).getSelect()==1){
               singleTileIndexes.add(i);
            }
        }
        //add single habitat tiles in river to array
        for(int i=0;i<potentialSuitors.size();i++){
            for(int j=0;j<singleTileIndexes.size();j++){
                if(TileDeck.getRiverTilesIndex(singleTileIndexes.get(j)).getHabitat(0).equals(potentialSuitors.get(habitatToCheck).getHabitatType())){
                    return singleTileIndexes.get(j);
                }
            }
        }
        //checks if they match the habitat type of max quarter
        return  -1;
    }

    public boolean isAvailable(int x){
        int coordX=0;
        int coordY=0;

        System.out.println("this is the size of the coords array "+potentialSuitors.get(x).getXcordArrayList().size());
        for(int i=0;i<potentialSuitors.get(x).getXcordArrayList().size();i++) {
             coordX = potentialSuitors.get(x).getYcordArrayList().get(i);
             coordY = potentialSuitors.get(x).getXcordArrayList().get(i);
             if(checkTilePlacement(coordX,coordY)){
                 return true;
             }

        }
        return false;

    }

    public MapGenerator getMap() {
        return map;
    }
}