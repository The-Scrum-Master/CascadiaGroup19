/*Group: 19
 *Team Name: Front Row
 *Timi Salam- 21392803(Timisalam)
 *Patrick Kelly-21204063(Patkelly17)
 *Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private final int strategy;
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
    public ArrayList<Integer> totalScore = new ArrayList<Integer>();
    FindCorridors playerCorridors;
    private ArrayList<Integer> coordX = new ArrayList<Integer>();
    private ArrayList<Integer> coordY = new ArrayList<Integer>();

    public Player(String name, int order) {
        this.name = name;
        this.order = order;
        heldTile = null;
        heldToken = null;
        natureTokenNumber = 0;
        playerBoard = new Tile[46][46];
        strategy = Tile.randomNumberGenerator(3);
    }

    public int getStrategy() {
        return strategy;
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

    public FindCorridors getPlayerCorridors() {
        return playerCorridors;
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
        playerCorridors = new FindCorridors(playerBoard);
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
            playerBoard[x][y].setBoardIndex(y, x);
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
        hasToken = false;
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard.length; j++) {

                if (playerBoard[i][j] != null && !playerBoard[i][j].tokenPlayed) {
                    if (playerBoard[i][j].getSelect() == 1) {
                        if (playerBoard[i][j].getSlot(0) == heldToken && !playerBoard[i][j].getTokenPlaced()) {
                            hasToken = true;
                        }
                    } else if (playerBoard[i][j].getSelect() == 2) {
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
                    } else {
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
        } else {
            return false;
        }
    }// if tile already has a token not let place

    public void splitPick(int tileIndex, int tokenIndex) {
        // is called when the player chooses to pick a tile and a token from the river
        // at different indexes
        System.out.println("You have chosen to pick any one tile and wildlife token from the river" +
                "\nEnter the index of the tile you would like to chose:");
        System.out.println(tileIndex);
        this.heldTile = TileDeck.getRiverTilesIndex(tileIndex);
        // moves the tile from the river to the players hand
        TileDeck.ReplaceRiverTilesIndex(tileIndex);
        // replaces the tile in the river with a new tile
        TileDeck.emptyDeckCheck();

        System.out.println("Enter the index of the token you would like to chose:");
        System.out.println(tokenIndex);
        this.heldToken = TileDeck.getRiverTokensIndex(tokenIndex);
        TileDeck.ReplaceRiverTokensIndex(tokenIndex);
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


    public MaxCorridor[] habitatScore() {
        MaxCorridor[] top = playerCorridors.mapIterator(playerBoard);

        return top;
    }

    public void findBestPosition(int x, int strategy) {
        if (strategy != 0) {
            potentialSuitors = new ArrayList<>();
            MaxCorridor[] maxQuarters = habitatScore();
            potentialSuitors.addAll(Arrays.asList(maxQuarters).subList(0, maxQuarters.length));
        }

        for (int i = 0; i < potentialSuitors.size(); i++) {
            for (int j = 0; j < heldTile.getHabitats().length; j++) {
                if (potentialSuitors.get(i).getHabitatType().equals(heldTile.getHabitat(j))) {
                    //linear search to go through the habitats of the maxQuarters and the heldtile finds match then searches for where to place tile
                    ArrayList<Integer> Y = potentialSuitors.get(i).getXcordArrayList();
                    ArrayList<Integer> X = potentialSuitors.get(i).getYcordArrayList();

                    if(potentialSuitors.get(i).getXcordArrayList().size()==0){
                        //if the tile chosen does not have any other matching habitats on the board place anywhere
                        placeAnywhere();
                        return;
                    }
                    boolean isTilePlaced = searchRadius(X.get(x), Y.get(x));
                    while(!isTilePlaced) {
                        x++;
                        if(x<potentialSuitors.get(i).getXcordArrayList().size())
                        {
                            isTilePlaced=searchRadius(X.get(x), Y.get(x));
                        }
                        else{
                            placeAnywhere();
                            isTilePlaced=true;
                        }
                    }
                    //while the tile which has the matching habitat is found and heldtile cannot be placed around it go to the next matching habitat tile
                    return;
                }
            }
        }
    }

    public boolean searchRadius(int x, int y) {
// Check if there is any empty tile adjacent to the current position
        if (heldTile.getSelect() == 1) {
           return searchSingleTileRadius(x, y);
        }
        if (map.getMap()[x][y + 1].getEmptyTile()) {
            //checking the right
            if (playerBoard[x][y].getSelect() == 1) {
                //if tile on the board is a single habitat tile
                if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                        //if flipping tile matches the habitats do so
                        flipPlayersTile();
                    }
                }
                if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    //if they now match placeTile
                    placeTile(x, y + 1);
                    return true;
                }
            } else {
                if (!playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(1))) {
                        flipPlayersTile();
                    }
                }
                 if (playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    placeTile(x, y + 1);
                    return true;
                }

            }


        }  if (map.getMap()[x][y - 1].getEmptyTile()) {
            //checking the left

            if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {

                    flipPlayersTile();
                }

            }
            if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                placeTile(x, y - 1);
                return true;
            }


        }  if (map.getMap()[x - 1][y].getEmptyTile()) {
            //checking above

            if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {

                    flipPlayersTile();
                }
            }
            if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                placeTile(x - 1, y);
                return true;
            }


        } if (map.getMap()[x + 1][y].getEmptyTile()) {
            //checking below
            if (playerBoard[x][y].getSelect() == 1) {
                if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                        flipPlayersTile();
                    }
                }
                if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    placeTile(x + 1, y);
                    return true;
                }

            } else {
                if (!playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(1))){

                        flipPlayersTile();
                    }
                }
                if (playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    placeTile(x + 1, y);
                    return true;
                }


            }


        } else {
            return false;

        }

        return false;
    }

    public void flipPlayersTile() {
        heldTile.flipTile(heldTile);
        TileGenerator g = new TileGenerator(heldTile);
        g.generateFlipTile();
        System.out.println("I have decided to flip the tile");
        g.printTile();
    }

    public boolean searchSingleTileRadius(int x, int y) {
        // Check if there is any empty tile adjacent to the current position when heldtile is a singleHabitat tile
        if (map.getMap()[x][y + 1].getEmptyTile()) {//checking right
            if (playerBoard[x][y].getSelect() == 1) {
                if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    placeTile(x, y + 1);
                    return true;
                }
            } else {
                if (playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    placeTile(x, y + 1);
                    return true;
                }
            }
        } if (map.getMap()[x][y - 1].getEmptyTile()) {
           //checking left
            if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                placeTile(x, y - 1);
                return true;
            }
        }  if (map.getMap()[x - 1][y].getEmptyTile()) {
            //checks above
            if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                placeTile(x - 1, y);
                return true;
            }
        }  if (map.getMap()[x + 1][y].getEmptyTile()) {
            //checks below
            if(playerBoard[x][y].getSelect()==1){
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0)));
                {
                    placeTile(x + 1, y);
                    return true;
                }
            }
            if (playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                placeTile(x + 1, y);
                return true;
            }

        } else {
           return false;
        }
        return false;

    }






    public boolean placeAnywhere() {
        //function to randomly place tiles
        int count = Tile.randomNumberGenerator(6);
        coordX = new ArrayList<>();
        coordY = new ArrayList<>();
        int randomNumber = Tile.randomNumberGenerator(2);
        for (int rows = 0; rows < 46; rows++) {
            for (int columns = 0; columns < 46; columns++) {
                if (!map.getMap()[rows][columns].getEmptyTile()) {
                    if (isAvailable(rows, columns)) {
                        count++;

                    }
                    //go through map if a tile is available count++

                    if (count == 7) {

                        if (randomNumber == 1) {
                            flipPlayersTile();
                        }
                        //randomly flip tiles
                        placeTile(coordX.get(coordX.size() - 1), coordY.get(coordY.size() - 1));
                        return true;
                    }
                    //this is make sure tiles are not placed one sided
                }
            }
        }

        if (randomNumber == 1) {
            flipPlayersTile();
        }
        placeTile(coordX.get(coordX.size() - 1), coordY.get(coordY.size() - 1));
        return true;
    }


    public boolean isAvailable(int x, int y) {
        //function searches for available tiles placements around a specific tile
        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(1);
        // right
        directions.add(-1);
        // left
        directions.add(-1);
        // up
        directions.add(1);
        // down
        Collections.shuffle(directions);
        // shuffle the directions randomly

        for (int dir : directions) {
            if (dir == 1 && y < map.getMap()[0].length - 1 && map.getMap()[x][y + dir].getEmptyTile()) {
                coordX.add(x);
                coordY.add(y + dir);
                return true;
            } else if (dir == -1 && y > 0 && map.getMap()[x][y + dir].getEmptyTile()) {
                coordX.add(x);
                coordY.add(y + dir);
                return true;
            } else if (dir == -1 && x > 0 && map.getMap()[x + dir][y].getEmptyTile()) {
                coordX.add(x + dir);
                coordY.add(y);
                return true;
            } else if (dir == 1 && x < map.getMap().length - 1 && map.getMap()[x + dir][y].getEmptyTile()) {
                coordX.add(x + dir);
                coordY.add(y);
                return true;
            }
        }
        //search for available tile placements in random order each time
        return false;
    }


    public int chooseFromRiver() {
        potentialSuitors = new ArrayList<>();
        MaxCorridor[] maxQuarters = habitatScore();
        potentialSuitors.addAll(Arrays.asList(maxQuarters).subList(0, maxQuarters.length));
        boolean hasBestHabitat = false;

        int position = 0;

        int k = 0;
        while (k < 5) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < TileDeck.getRiverTilesIndex(i).getHabitats().length; j++) {
                    if (potentialSuitors.get(k).getHabitatType().equals(TileDeck.getRiverTilesIndex(i).getHabitat(j))) {
                        hasBestHabitat = true;
                        position = i;
                    }
                }
                if (hasBestHabitat) {
                    return position;
                }

            }
            k++;

        }
        //loops through the tiles in the river and checks if their habitat types match the habitat type of max quarter


        return -1;
    }

    public int checkSingleTileInRiver(int habitatToCheck) {
        //checks if single habitat tile in river matches max quarter habitat if so return index of tile in river

        ArrayList<Integer> singleTileIndexes = new ArrayList<Integer>();
        for (int i = 0; i < TileDeck.getRiverTiles().length; i++) {
            if (TileDeck.getRiverTilesIndex(i).getSelect() == 1) {
                singleTileIndexes.add(i);
            }
        }
        //add single habitat tiles in river to array
        for (int i = 0; i < potentialSuitors.size(); i++) {
            for (int j = 0; j < singleTileIndexes.size(); j++) {
                if (TileDeck.getRiverTilesIndex(singleTileIndexes.get(j)).getHabitat(0).equals(potentialSuitors.get(habitatToCheck).getHabitatType())) {
                    return singleTileIndexes.get(j);
                }
            }
        }
        //checks if they match the habitat type of max quarter
        return -1;
    }


    public MapGenerator getMap() {
        return map;
    }
}