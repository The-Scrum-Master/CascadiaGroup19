public class Player {
    private final int order;
    private final String name;
    private boolean firstTurnPlayed=false;

    public Tile [][] playerBoard;
    public MapGenerator map=new MapGenerator();

    public Tile heldTile;
    public Wildlife heldToken;

    public Player(String name, int order){
        this.name = name;
        this.order = order;
        heldTile = null;
        heldToken = null;

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


        TileGenerator InitialTileSingleColoured = new TileGenerator(generateSpecificTile(1));
        TileGenerator InitialTileDoubleColoured1 = new TileGenerator(generateSpecificTile(2));
        TileGenerator InitialTileDoubleColoured2 = new TileGenerator(generateSpecificTile(3));

        map.fillMapBlankByParts(blank);
        map.starterTilesReducedMap(InitialTileSingleColoured, InitialTileDoubleColoured1, InitialTileDoubleColoured2);
        map.printMapTotalByParts();


        /*
        blank.blankTile();
        TileGenerator unique1 =new TileGenerator();
        unique1.tileUniqueColor(t.colourConverter(t.getColour()), t.colourAnimal(t.getAnimal()));
        TileGenerator unique2 =new TileGenerator();
        //unique2.tileUniqueColor("\033[44m", t.colourAnimal(t.getAnimal()), t.colourAnimal(t.getAnimal()));
        TileGenerator unique3 =new TileGenerator();
        //unique3.tileUniqueColor("\033[43m", t.colourAnimal(t.getAnimal()), t.colourAnimal(t.getAnimal()));
        TileGenerator double1 =new TileGenerator();
        double1.tileTwoColors(t2.colourConverter(t2.getColour()), t2.colourConverter(t2.getColour2()), t2.colourAnimal(t2.getAnimal2()), t2.colourAnimal(t2.getAnimal()), t2.colourAnimal(t2.getAnimal()));
        TileGenerator double2 =new TileGenerator();
        double2.tileTwoColors(t3.colourConverter(t3.getColour()), t3.colourConverter(t3.getColour2()), t3.colourAnimal(t3.getAnimal2()), " ", t3.colourAnimal(t3.getAnimal()));




        map.fillMapBlankByParts(blank);
        map.starterTilesReducedMap(InitialTileSingleColoured, InitialTileDoubleColoured1, InitialTileDoubleColoured2);
        map.printMapTotalByParts();

         */

        firstTurnPlayed=true;
    }

    public void pickPair(int indexOfSelected){
        this.heldTile = TileDeck.getRiverTilesIndex(indexOfSelected);
        this.heldToken = TileDeck.getRiverTokensIndex(indexOfSelected);
        TileDeck.ReplaceRiverTilesIndex(indexOfSelected);
        TileDeck.ReplaceRiverTokensIndex(indexOfSelected);
    }
    public Tile generateRandomTile()
    {
        Tile t = new Tile(0);
        return t;
    }

    public Tile generateSpecificTile(int x)
    {
        Tile t = new Tile(x);
        return t;
    }

}

