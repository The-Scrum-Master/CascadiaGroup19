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
    public  ArrayList<Integer> totalScore = new ArrayList<Integer>();
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
        strategy=Tile.randomNumberGenerator(3);
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
                    } else{
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
        for(int i = 0; i < top.length; i++){
            for(int j = i; j < top[i].getSize(); j++){
                System.out.println("("+ top[i].getYcordArrayList().get(j) + ", " + top[i].getXcordArrayList().get(j)+")");
            }
        }
        return top;
    }

    // public int getIndexToChooseFromRiver(){
    //     MaxCorridor[] corridorsToAddTo = habitatScore();
    //     MaxCorridor corridorChosen = pickCorridorToBuildOff(corridorsToAddTo);
    //     int indexOfTileToPlace = checkRiverForHabitat(corridorChosen.getHabitatType());
    //     return indexOfTileToPlace;
    // }

    // public void placeTileinBestCorridor(){
    //     MaxCorridor[] corridorsToAddTo = habitatScore();
    //     MaxCorridor corridorChosen = pickCorridorToBuildOff(corridorsToAddTo);
    //     ArrayList<Tile> potentialSpots = getPotentialTilesToAddTo(corridorChosen);
    //     spotFinder(potentialSpots, corridorChosen.getHabitatType(), true);
    // }

    // private MaxCorridor pickCorridorToBuildOff(MaxCorridor[] maxCorridorsArray){
    //     ArrayList<Tile> tilesToBuildOff;
    //     Habitat corridorType;
    //     int indexOfRiverTile;
    //     //while the maxcorridor of of a habitat doesnt have a potential spot to place a tile increment corridor type
    //     for(int index = 0; index < maxCorridorsArray.length; index++){
    //         tilesToBuildOff = getPotentialTilesToAddTo(maxCorridorsArray[index]);
    //         corridorType = maxCorridorsArray[index].getHabitatType();
    //         indexOfRiverTile = checkRiverForHabitat(corridorType);
    //         if(indexOfRiverTile < -1 || indexOfRiverTile > 4){
    //             throw new IllegalStateException("index of river Tile is outside the possible values");
    //         }
    //         if(indexOfRiverTile != -1){
    //             if(spotFinder(tilesToBuildOff, corridorType, false) != -1){
    //                 return maxCorridorsArray[index];
    //             } 
    //         }
    //     }
    //     throw new IllegalStateException("There is no possible corridors to add too");
    // }

    // private int checkRiverForHabitat(Habitat habitatToCheck){
    //     Tile curTile;
    //     for(int index = 0; index < TileDeck.getRiverTiles().length; index++){
    //         curTile = TileDeck.getRiverTilesIndex(index);
    //         if(curTile.getHabitat(0) == habitatToCheck){
    //             return index;
    //         }
    //         if(curTile.getSelect() != 1){
    //             if (curTile.getHabitat(1) == habitatToCheck){
    //                 return index;
    //             }
    //         }
    //     }
    //     return -1;
    // }

    // private int spotFinder(ArrayList<Tile> tilesToBuildOff, Habitat corridorType, Boolean placeHeldTile){
    //     Tile curTile;
    //     int canBeBuiltOff;
    //     //int that tells what direction to build off, 0 left, 1 up, 2 right, 3 bottom, -1 cant.
    //     for(int i = 0; i < tilesToBuildOff.size(); i++){
    //         curTile = tilesToBuildOff.get(i);
    //         if(curTile.getSelect() == 1){
    //             canBeBuiltOff = buildOffSoloTile(curTile, corridorType);
    //             if(canBeBuiltOff != -1){
    //                 if(placeHeldTile){
    //                     integerToPlaceTile(curTile, canBeBuiltOff);
    //                 }
    //                 return canBeBuiltOff;
    //             }
    //         }else{
    //             canBeBuiltOff = buildOffDoubleTile(curTile, corridorType);
    //             if(canBeBuiltOff != -1){
    //                 if(placeHeldTile){
    //                     integerToPlaceTile(curTile, canBeBuiltOff);
    //                 }
    //                 return canBeBuiltOff;
    //             }
    //         }
    //     }
    //     return -1;
    // }

    // private void integerToPlaceTile(Tile curTile, int input){
    //     int xCord = curTile.getBoardXIndex();
    //     int yCord = curTile.getBoardYIndex();
    //     switch(input){
    //         case 0:
    //         placeTile(xCord - 1, yCord);
    //         break;
    //         case 1:
    //         placeTile(xCord, yCord - 1);
    //         break;
    //         case 2:
    //         placeTile(xCord + 1, yCord);
    //         break;
    //         case 3:
    //         placeTile(xCord, yCord + 1);
    //         break;
    //         default: throw new IllegalArgumentException("integerToTile only takes in ints between 0 - 3");
    //     }
    // }

    // private int buildOffDoubleTile(Tile tileToBuildOff, Habitat corridorType){
    //     //checks if any spots can be build off a double type for a given habitat, returns - 1 otherwise
    //     if(tileToBuildOff == null || tileToBuildOff.getSelect() == 1){
    //         throw new IllegalArgumentException("the tile handed to BuildOffDoubleTile is null or a solo-tile");
    //     }
    //     int currentXCord = tileToBuildOff.getBoardXIndex();
    //     int currentYCord = tileToBuildOff.getBoardYIndex();
    //     Habitat tilesTopandLeft = tileToBuildOff.getHabitat(0);
    //     Habitat tilesRightandBottom = tileToBuildOff.getHabitat(1);
    //     // Habitat tilesRightandBottom;
    //     if(tilesTopandLeft == corridorType){
    //         if(isSpotAvailable(currentXCord - 1, currentYCord)){
    //             //check left
    //             return 0;
    //         }else if(isSpotAvailable(currentXCord, currentYCord - 1)){
    //             //check up
    //             return 1;
    //         }
    //         return -1;
    //     }else if(tilesRightandBottom == corridorType){
    //         if(isSpotAvailable(currentXCord + 1, currentYCord)){
    //             //check right
    //             return 2;
    //         }else if(isSpotAvailable(currentXCord, currentYCord + 1)){
    //             return 3;
    //         }
    //         return -1;
    //     }else{
    //         return -1;
    //     }
    // }
    // private int buildOffSoloTile(Tile tileToBuildOff, Habitat corridorType){
    //     //checks if any spots can be build off a solo type for a given habitat, returns - 1 otherwise
    //     if(tileToBuildOff.getSelect() != 1){
    //         throw new IllegalArgumentException("Non Solo-tile handed to buildOffSoloTile for solo-tile");
    //     }
    //     Habitat habitatType = tileToBuildOff.getHabitat(0);
    //     int currentXCord = tileToBuildOff.getBoardXIndex();
    //     int currentYCord = tileToBuildOff.getBoardYIndex();
    //     if(habitatType == corridorType){ 
    //         if(isSpotAvailable(currentXCord - 1, currentYCord)){
    //             //check left
    //             return 0;
    //         }
    //         if(isSpotAvailable(currentXCord, currentYCord - 1)){
    //             //check up
    //             return 1;
    //         }
    //         if(isSpotAvailable(currentXCord + 1, currentYCord)){
    //             //check right
    //             return 2;
    //         }
    //         if(isSpotAvailable(currentXCord, currentYCord + 1)){
    //             //check down
    //             return 3;
    //         }
    //     }
    //     return -1;
    // }


    // private ArrayList<Tile> getPotentialTilesToAddTo(MaxCorridor Corridor){
    //     ArrayList<Tile> potentialTilesToAddTo = new ArrayList<>();
    //     for(int i = 0; i < Corridor.getSize(); i++){
    //         int xCord = Corridor.getXcordArrayList().get(i);
    //         int yCord = Corridor.getYcordArrayList().get(i);
    //         if(isTileAvailbleToPlayOff(xCord, yCord)){
    //             potentialTilesToAddTo.add(playerBoard[yCord][xCord]);
    //         }
    //     }
    //     if(potentialTilesToAddTo.size() < 1){
    //         System.out.println("there are no avialble spots to add to this corridor");
    //     }
    //     return potentialTilesToAddTo;
    // }

    private boolean isTileAvailbleToPlayOff(int xCord, int yCord){
        if(playerBoard[yCord][xCord] == null){
            System.out.println("the tile you are attmepting to place next to is null");
            return false;
        }else{
            if(isSpotAvailable(xCord - 1, yCord) || isSpotAvailable(xCord + 1, yCord) ||
            isSpotAvailable(xCord, yCord - 1) || isSpotAvailable(xCord, yCord + 1)){
                return true;
            }else{
                return false;
            }
        }
    }

    private boolean isSpotAvailable(int xCord, int yCord){
        if(playerBoard[yCord][xCord] == null){
            return true;
        }else{
            return false;
        }
    }



    public void findBestPosition(int x,int strategy){
        System.out.println("Itss me find best habitat");
        //potential edge case where if tile which coords point to is surrounded error have code foe this but need to wait till pat fixes his function

//potential edge case where if tile which coords point to is surrounded error have code foe this but need to wait till pat fixes his function
        if(strategy!=0){
            potentialSuitors = new ArrayList<>();
            MaxCorridor[] maxQuarters = habitatScore();
            potentialSuitors.addAll(Arrays.asList(maxQuarters).subList(0, maxQuarters.length));
        }
        for(int i=0;i<potentialSuitors.size();i++){
            System.out.println("Potential suitors habitats "+potentialSuitors.get(i).getHabitatType());
        }
        for(int j=0;j<potentialSuitors.size();j++) {
            for (int i = 0; i < potentialSuitors.get(j).getXcordArrayList().size(); i++) {
                System.out.println("habitat coords " + potentialSuitors.get(j).getXcordArrayList().get(i) + "," + potentialSuitors.get(0).getYcordArrayList().get(i));
            }
        }
        for (int i = 0; i < potentialSuitors.size(); i++) {
            for (int j = 0; j < heldTile.getHabitats().length; j++) {
                if (potentialSuitors.get(i).getHabitatType().equals(heldTile.getHabitat(j))) {
                    ArrayList<Integer> Y = potentialSuitors.get(i).getXcordArrayList();
                    ArrayList<Integer> X = potentialSuitors.get(i).getYcordArrayList();
                    System.out.println("THis is the length of the coords array " + potentialSuitors.get(i).getXcordArrayList().size());
                    System.out.println("this is the max quarter habitat " + potentialSuitors.get(i).getHabitatType());

                    boolean isTilePlaced=searchRadius(X.get(x), Y.get(x));
                    if(!isTilePlaced)
                    {
                        findSecondBestPosition();
                    }
                    return;
                }
            }
        }
    }

    public boolean searchRadius(int x, int y) {
        //0 is top 1 is bottom
        System.out.println("its me search radius");
        System.out.println("the coordinnates I have been given are " + x + "," + y);
        if(heldTile.getSelect()==1){
            System.out.println("entered if statement");
            searchSingleTileRadius(x,y);
            return true;
        }
        if (map.getMap()[x][y + 1].getEmptyTile()) {
            System.out.println("This is the right side ");
            if(playerBoard[x][y].getSelect()==1){
                if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    flipPlayersTile();
                }
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))){
                    placeTile(x, y + 1);
                    return true;
                }
            }
            else {
                if (!playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    flipPlayersTile();
                }
                if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))){
                    placeTile(x, y + 1);
                    return true;
                }

            }



        } else if (map.getMap()[x][y - 1].getEmptyTile()) {
            System.out.println("This is the left side ");

            if(playerBoard[x][y].getSelect()==1){
                if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                    flipPlayersTile();

                }
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))){
                    placeTile(x, y - 1);
                    return true;
                }
            }
            else {
                if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                    flipPlayersTile();

                }
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))){
                    placeTile(x, y - 1);
                    return true;
                }

            }



        }  else if (map.getMap()[x - 1][y].getEmptyTile()) {
            System.out.println("This is above");

            if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))) {
                flipPlayersTile();

            }
            if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(1))){
                placeTile(x-1, y );
                return true;
            }



        }else if (map.getMap()[x + 1][y].getEmptyTile()) {
            System.out.println("This is the below ");

            if(playerBoard[x][y].getSelect()==1){
                if (!playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    flipPlayersTile();
                }
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))){
                    placeTile(x+1, y );
                    return true;
                }

            }
            else {
                if (!playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))) {
                    flipPlayersTile();
                }
                if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))){
                    placeTile(x+1, y );
                    return true;
                }


            }



        }else {
            System.out.println("FAILED");

            //sometimes the coords of the top dog is surrounded need to pick the next best one
        }
        return false;
    }
    public void flipPlayersTile(){
        heldTile.flipTile(heldTile);
        TileGenerator g = new TileGenerator(heldTile);
        g.generateFlipTile();
        System.out.println("I have decided to flip the tile");
        g.printTile();
    }
    public boolean searchSingleTileRadius(int x,int y) {
        if (map.getMap()[x][y + 1].getEmptyTile()) {
            if(playerBoard[x][y].getSelect()==1){
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))){
                    placeTile(x,y+1);
                    return true;
                }
            }else{
                if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))){
                    placeTile(x,y+1);
                    return true;
                }
            }
        }
        else if (map.getMap()[x][y - 1].getEmptyTile()) {
            if(playerBoard[x][y].getSelect()==1){
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))){
                    placeTile(x,y-1);
                    return true;
                }
            }
            else{
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    placeTile(x, y - 1);
                    return true;
                }
            }
        }
        if (map.getMap()[x-1][y].getEmptyTile()) {
            if (playerBoard[x][y].getSelect()== 1) {
                if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    placeTile(x-1, y );
                    return true;
                }
            } else {
                if (playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))) {
                    placeTile(x - 1, y);
                    return true;
                }
            }
        }
        if (map.getMap()[x+1][y].getEmptyTile()) {
            if(playerBoard[x][y].getSelect()==1){
                if(playerBoard[x][y].getHabitat(0).equals(heldTile.getHabitat(0))){
                    placeTile(x+1,y);
                    return true;
                }
            }else{
                if(playerBoard[x][y].getHabitat(1).equals(heldTile.getHabitat(0))){
                    placeTile(x+1,y);
                    return true;
                }
            }
        }
        else{
            System.out.println("Failed");
            findSecondBestPosition();

        }
        return false;

    }

    public void findSecondBestPosition() {


        for (int x = 0; x < playerBoard.length; x++) {
            innerloop:
            for (int y = 0; y < playerBoard.length; y++) {
                if (!map.getMap()[x][y].getEmptyTile()) {
                    for (int k = 0; k < playerBoard[x][y].getHabitats().length; k++) {
                        for (int l = 0; l < heldTile.getHabitats().length; l++) {
                            if (heldTile.getHabitat(l).equals(playerBoard[x][y].getHabitat(k))) {
                                if (x == potentialSuitors.get(0).getYcordArrayList().get(0)) {
                                    System.out.println("Entered if statement");
                                    x++;
                                    continue innerloop;


                                }

                                searchRadius(x, y);
                                return;
                            }
                        }
                    }

                }

            }
        }
        placeAnywhere();
    }


    public boolean placeAnywhere() {
        System.out.println("its me placeAnywhere");
        int count=0;
        coordX = new ArrayList<>();
        coordY  =new ArrayList<>();
        Random rand = new Random();
        int randomNumber = rand.nextInt(2);
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!map.getMap()[rows][columns].getEmptyTile()){
                    if(isAvailable(rows,columns)){
                        count++;

                    }

                    if(count==5){
                        System.out.println("count is 7");
                        System.out.println("coorda are "+coordX.get(coordX.size()-1)+","+coordY.get(coordY.size()-1));

                        if(randomNumber==2){
                            flipPlayersTile();
                        }
                        placeTile(coordX.get(coordX.size()-1),coordY.get(coordY.size()-1));
                        return true;
                    }
                }
            }
        }
        System.out.println("finishing iuterating");
        System.out.println("coorda are "+coordX.get(coordX.size()-1)+","+coordY.get(coordY.size()-1));
        if(randomNumber==2){
            flipPlayersTile();
        }
        placeTile(coordX.get(coordX.size()-1),coordY.get(coordY.size()-1));
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
        System.out.println("its me choose from river");
        potentialSuitors = new ArrayList<>();
        MaxCorridor[] maxQuarters = habitatScore();
        potentialSuitors.addAll(Arrays.asList(maxQuarters).subList(0, maxQuarters.length));
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
       /*if(checkSingleTileInRiver(0)>0){
           return checkSingleTileInRiver(0);
       }*/
        int k = 0;
        while (k < 5) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < TileDeck.getRiverTilesIndex(i).getHabitats().length; j++) {
                    if (potentialSuitors.get(k).getHabitatType().equals(TileDeck.getRiverTilesIndex(i).getHabitat(j))) {
                        hasBestHabitat = true;
                        position = i;
                        System.out.println("this is the max quarter habitat " + potentialSuitors.get(k).getHabitatType());
                    }
                }
                if (hasBestHabitat) {
                    return position;
                }

            }
            k++;
            /*if(checkSingleTileInRiver(k)>0){
                return checkSingleTileInRiver(k);
            }*/
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