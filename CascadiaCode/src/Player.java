import java.sql.SQLOutput;

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
        map.printMapTotalByParts();

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
            habitatScore(x, y);
            // calls habitat score to check if there is a group
            heldTile = null;
        }
    }
    public void  placeToken(int x, int y){
        if(x<0||x>=46||y<0||y>=46)
        {
            System.out.println("Error placed tile out of bounds please try again");
            int newX = IOcascadia.takeIntInput();
            int newY= IOcascadia.takeIntInput();

        }
        Wildlife WildlifeType = heldToken;
        if (this.playerBoard[x][y] == null || this.playerBoard[x][y].isTokenPlaced()) {
            System.out.println("The board location for the Token placement is not a valid location,\n" +
                    "you must place it on a previously played tile. Please try again");
            int x_axis = IOcascadia.takeIntInput();
            int y_axis = IOcascadia.takeIntInput();
        } else {
            playerBoard[x][y].playToken();
            for (int i = 0; i < playerBoard[x][y].getSlots().length; i++)
            //checks to see if the tiles has a slot for the token on the board
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
            if (isFilled == false) {
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

    public boolean checkToken() {
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard.length; j++) {

                if (playerBoard[i][j] != null) {

                    if (playerBoard[i][j].getSelect() == 1) {
                        if (playerBoard[i][j].getSlot(0) == heldToken && !playerBoard[i][j].isTokenPlaced()) {
                            hasToken = true;
                        }

                    }

                    else if (playerBoard[i][j].getSelect() == 2) {
                        for (int k = 0; k < 2; k++) {
                            if (playerBoard[i][j].getSlot(k) == heldToken && !playerBoard[i][j].isTokenPlaced()) {
                                hasToken = true;
                            }

                        }
                    } else if (playerBoard[i][j].getSelect() == 3) {
                        for (int k = 0; k < 3; k++) {
                            if (playerBoard[i][j].getSlot(k) == heldToken && !playerBoard[i][j].isTokenPlaced()) {
                                hasToken = true;
                            }

                            // idk where to put the istoken playe thng in this function
                            // its gonna check and be like oh space available then itll see if there is
                            // atoken there alresaad
                        }
                    }

                }
            }

        }
        if (hasToken == false) {
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
    // is called when the player chooses to pick a tile and a token from the river at different indexes
        System.out.println("You have chosen to pick any one tile and wildlife token from the river" +
                "\nEnter the index of the tile you would like to chose:");
        int indexChoiceTile = IOcascadia.takeIntInput() - 1;
        while (indexChoiceTile < 0 || indexChoiceTile > 3) {
            System.out.println("The number you have inputted is not a valid index.");
            indexChoiceTile = IOcascadia.takeIntInput();
        }
        this.heldTile = TileDeck.getRiverTilesIndex(indexChoiceTile);
        TileDeck.ReplaceRiverTilesIndex(indexChoiceTile);
        TileDeck.emptyDeckCheck();
        System.out.println("Enter the index of the token you would like to chose:");
        int indexChoiceToken = IOcascadia.takeIntInput() - 1;
        while (indexChoiceToken < 0 || indexChoiceToken > 3) {
            System.out.println("The number you have inputted is not a valid index.");
            indexChoiceToken = IOcascadia.takeIntInput();
        }
        this.heldToken = TileDeck.getRiverTokensIndex(indexChoiceToken);
        TileDeck.ReplaceRiverTokensIndex(indexChoiceToken);
        natureTokenNumber--;
        System.out.println("You now have " + natureTokenNumber + " NatureTokens left");
    }

    public void pickPair(int indexOfSelected) { 
        // called when the player chooses to pick a tile and a token from the river at the same index
        this.heldTile = TileDeck.getRiverTilesIndex(indexOfSelected);
        this.heldToken = TileDeck.getRiverTokensIndex(indexOfSelected);
        TileDeck.ReplaceRiverTilesIndex(indexOfSelected);
        TileDeck.ReplaceRiverTokensIndex(indexOfSelected);
        TileDeck.emptyDeckCheck();
    }
    
    public int riverCounter = 1, riverMax = 1;
    public int forestCounter = 1, forestMax = 1;
    public int prairieCounter = 1, prairieMax = 1;
    public int mountainCounter = 1, mountainMax = 1;
    public int wetlandCounter = 1, wetlandMax = 1;

    public void habitatIncrementer(Habitat habitat) {
        // increment the counter for the habitat
        switch (habitat) {
            case RIVER:
                riverCounter++;
                break;
            case FOREST:
                forestCounter++;
                break;
            case PRAIRIE:
                prairieCounter++;
                break;
            case MOUNTAIN:
                mountainCounter++;
                break;
            case WETLANDS:
                wetlandCounter++;
                break;
        }
    }

    public void habitatMaxTracker(Habitat habitat) {
        // if the counter is greater than the max, set the max to the counter
        switch (habitat) {
            case RIVER:
                if (riverCounter > riverMax) {
                    riverMax = riverCounter;
                }
            case FOREST:
                if (forestCounter > forestMax) {
                    forestMax = forestCounter;
                }
            case PRAIRIE:
                if (prairieCounter > prairieMax) {
                    prairieMax = prairieCounter;
                }
            case MOUNTAIN:
                if (mountainCounter > mountainMax) {
                    mountainMax = mountainCounter;
                }
            case WETLANDS:
                if (wetlandCounter > wetlandMax) {
                    wetlandMax = wetlandCounter;
                }
        }
    }

    public void habitatCounterReset() {
        riverCounter = 0;
        forestCounter = 0;
        prairieCounter = 0;
        mountainCounter = 0;
        wetlandCounter = 0;
    }

    public void recursiveHabibitCounter(int rows, int columns, Habitat habitat) {
        // recursive function to count the number of habitats grouped together
        if (playerBoard[rows - 1][columns] != null && playerBoard[rows - 1][columns].habitatCounted0 != true) {
            //check if the tile above is not null and has not already been counted
            if ((playerBoard[rows - 1][columns].getHabitat(0) == habitat
                    && playerBoard[rows - 1][columns].getSelect() == 1)
                    || playerBoard[rows - 1][columns].getHabitat(1) == habitat) {
                        // if the habitat is the same as the one being counted and the tile is selected
                habitatIncrementer(habitat);
                // increment the counter for the habitat
                playerBoard[rows - 1][columns].habitatCounted0 = true;
                recursiveHabibitCounter(rows - 1, columns, habitat);
                // call the function again with the new tile
            }
        }
        if (playerBoard[rows + 1][columns] != null && playerBoard[rows + 1][columns].habitatCounted0 != true) {
            if ((playerBoard[rows + 1][columns].getHabitat(1) == habitat
                    && playerBoard[rows + 1][columns].getSelect() == 1)
                    || playerBoard[rows + 1][columns].getHabitat(0) == habitat) {
                habitatIncrementer(habitat);
                playerBoard[rows + 1][columns].habitatCounted0 = true;
                recursiveHabibitCounter(rows + 1, columns, habitat);
            }
        }
        if (playerBoard[rows][columns - 1] != null && playerBoard[rows][columns - 1].habitatCounted0 != true) {
            if ((playerBoard[rows][columns - 1].getHabitat(0) == habitat
                    && playerBoard[rows][columns - 1].getSelect() == 1)
                    || playerBoard[rows][columns - 1].getHabitat(1) == habitat) {
                habitatIncrementer(habitat);
                playerBoard[rows][columns - 1].habitatCounted0 = true;
                recursiveHabibitCounter(rows, columns - 1, habitat);
            }
        }
        if (playerBoard[rows][columns + 1] != null && playerBoard[rows][columns + 1].habitatCounted0 != true) {
            if ((playerBoard[rows][columns + 1].getHabitat(1) == habitat
                    && playerBoard[rows][columns + 1].getSelect() == 1)
                    || playerBoard[rows][columns + 1].getHabitat(0) == habitat) {
                habitatIncrementer(habitat);
                playerBoard[rows][columns + 1].habitatCounted0 = true;
                recursiveHabibitCounter(rows, columns + 1, habitat);
            }
        }
    }

    public void habitatScore(int x, int y) {
        try {
            if (heldTile.getSelect() == 1) {
                // if the tile is selected, only check one habitat
                recursiveHabibitCounter(x, y, heldTile.getHabitat(0));
            } else {
                // if the tile is not selected, check both habitats
                recursiveHabibitCounter(x, y, heldTile.getHabitat(0));
                recursiveHabibitCounter(x, y, heldTile.getHabitat(1));
            }
        } catch (Exception e) {
        }
        habitatMaxTracker(heldTile.getHabitat(0));
        // check the max for the habitat
        if(heldTile.getSelect() != 1){
            //if two habitats tile check the second habitat
            habitatMaxTracker(heldTile.getHabitat(1));
        }
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard.length; j++) {
                if (playerBoard[i][j] != null) {
                    // reset the boolean values for the tiles
                    playerBoard[i][j].habitatCounted0 = false;
                    playerBoard[i][j].habitatCounted1 = false;
                }
            }
        }
        habitatCounterReset();
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

}