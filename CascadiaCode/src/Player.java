
/**
 * This Class creates the object Player that hold the information relevant to the individual Players in
 * the game such as board and tiles held. As well as the function necessary to run the backend action
 * of the player
 *
 * Major Methods: generate initial map .... sergio did it
 *          - placeTile, takes in x and y coordinates checks to make sure it picked location is adjaent
 *          to another tile, if true puts into board at index
 *          -pickPair, takes int, grabs the tile and token from that index of the river and moves it into player
 *          variable "heldTile" and replaces location on the river
 * @author Patrick Kelly, Sergio Jjjiemmemnnezz.
 */




public class Player {
    private final int order;
    private final String name;
    private boolean firstTurnPlayed=false;

    public Tile [][] playerBoard;
    public MapGenerator map=new MapGenerator();

    public Tile heldTile;
    public Wildlife heldToken;
    public int natureTokenNumber;

    public Player(String name, int order){
        this.name = name;
        this.order = order;
        heldTile = null;
        heldToken = null;
        natureTokenNumber = 0;
        playerBoard= new Tile[20][20]; //new board of 20 by 20 tiles
        playerBoard[9][9] = new Tile(0); //generates the three starting tiles
        playerBoard[9][9].playTile(); //changes boolean on tile to played.
        playerBoard[10][9] = new Tile(2);
        playerBoard[10][9].playTile();
        playerBoard[10][10] = new Tile(3);
        playerBoard[10][10].playTile();
    }
    public Tile[][] getPlayerBoard(){return  playerBoard;}
    public Tile getBoardIndex(int column, int row){
        return playerBoard[row][column];
    }
    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public boolean isFirstTurnPlayed() {
        return firstTurnPlayed;
    }

    public void generateInitialMap(){
        TileGenerator blank =new TileGenerator();
        blank.blankTile();


        TileGenerator InitialTileSingleColoured = new TileGenerator(Tile.generateSpecificTile(1));
        TileGenerator InitialTileDoubleColoured1 = new TileGenerator(Tile.generateSpecificTile(2));
        TileGenerator InitialTileDoubleColoured2 = new TileGenerator(Tile.generateSpecificTile(3));

        map.fillMapBlank(blank);
        map.starterTiles(InitialTileSingleColoured, InitialTileDoubleColoured1, InitialTileDoubleColoured2);
        map.printMapTotalByParts();

        firstTurnPlayed=true;
    }

    public void printMap(){
        map.printMapTotalByParts();
    }





    public void placeTile(int x, int y){
        if(heldTile == null){
            throw new IllegalArgumentException("held tile is null when calling place tile");
        }
        this.heldTile.playTile();
        if(playerBoard[x - 1][y] == null && playerBoard[x][y-1] == null &&
        playerBoard[x + 1][y] == null && playerBoard[x][y + 1] == null && playerBoard[x][y] != null){
            System.out.println("The board location for the tile placement is not a valid location,\n" +
                    " you must place it adjacent to another tile. Please try again");
            placeTile(x,y);
        }
        playerBoard[x][y] = heldTile;
        heldTile = null;
    }
    public String placeToken(int x, int y) {
        Wildlife WildlifeType = heldToken;
        if (heldToken == null) {
            throw new IllegalArgumentException("held tile is null when calling place tile");
        }
        if (playerBoard[x][y] != null && (playerBoard[x][y].getSlot(0) == heldToken
                || playerBoard[x][y].getSlot(1) == heldToken)
                || playerBoard[x][y].getSlot(3) == heldToken) {
            if (playerBoard[x][y].getSelect() == 1) {
                natureTokenNumber++;
            }
            playerBoard[x][y].playToken();
            heldToken = null;
            return Wildlife.animalSymbol(WildlifeType);
        } else {
            System.out.println("The board location for the Token placement is not a valid location,\n" +
                    " you must place it on a previously played tile. Please try again");
            placeToken(x, y);
        }
        throw new IllegalArgumentException("error in place token");
    }

    public void pickPair(int indexOfSelected){
        this.heldTile = TileDeck.getRiverTilesIndex(indexOfSelected);
        this.heldToken = TileDeck.getRiverTokensIndex(indexOfSelected);
        if(TileDeck.deck.empty()) {
            GameRunner.setContinueGame(false);
        }
        TileDeck.ReplaceRiverTilesIndex(indexOfSelected);
        TileDeck.ReplaceRiverTokensIndex(indexOfSelected);
    }


}

