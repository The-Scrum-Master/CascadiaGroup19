import java.sql.SQLOutput;

/**
 * This Class creates the object Player that hold the information relevant to the individual Players in
 * the game such as board and tiles held. As well as the function necessary to run the backend action
 * of the player
 * Major Methods: generate initial map .... sergio did it
 *          - placeTile, takes in x and y coordinates checks to make sure it picked location is adjaent
 *          to another tile, if true puts into board at index
 *          -pickPair, takes int, grabs the tile and token from that index of the river and moves it into player
 *          variable "heldTile" and replaces location on the river
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
        playerBoard = new Tile[20][20];

    }
    public Tile[][] getPlayerBoard(){return  playerBoard;}
    public Tile getBoardIndex(int column, int row){
        return playerBoard[row][column];
    }
    public String getName() {
        return name;
    }

    public Tile getHeldTile() {
        return heldTile;
    }
    public Wildlife getHeldToken(){
        return heldToken;
    }

    public int getOrder() {
        return order;
    }

    public boolean isFirstTurnPlayed() {
        return firstTurnPlayed;
    }
    private boolean isFilled = false;
    private boolean hasToken = false;

    public void generateInitialMap(){
        TileGenerator blank =new TileGenerator();
        blank.blankTile();

        playerBoard[9][9] = new Tile(1); //generates the three starting tiles
        playerBoard[9][9].playTile(); //changes boolean on tile to played.
        playerBoard[10][9] = new Tile(2);
        playerBoard[10][9].playTile();
        playerBoard[10][10] = new Tile(3);
        playerBoard[10][10].playTile();


        TileGenerator InitialTileSingleColoured = new TileGenerator(playerBoard[9][9]);
        TileGenerator InitialTileDoubleColoured1 = new TileGenerator( playerBoard[10][9]);
        TileGenerator InitialTileDoubleColoured2 = new TileGenerator(playerBoard[10][10]);

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
        if(playerBoard[x][y] != null||(playerBoard[x - 1][y] == null && playerBoard[x][y-1] == null &&
        playerBoard[x + 1][y] == null && playerBoard[x][y + 1] == null )){
            System.out.println("The board location for the tile placement is not a valid location,\n" +
                    " you must place it adjacent to another tile. Please try again,input x and y");
            int x_axis = IOcascadia.takeIntInput();
            int y_axis = IOcascadia.takeIntInput();
            placeTile(x_axis,y_axis);//recursive call to allow player to place tile again
        }
        else {
            playerBoard[x][y] = heldTile;
            TileGenerator helpTileGenerator = new TileGenerator(heldTile);
            map.setTile(helpTileGenerator,x,y);
            heldTile = null;


        }
    }
    public void placeToken(int x, int y)
    {
        Wildlife WildlifeType = heldToken;
        if (this.playerBoard[x][y] ==null)
        {
            System.out.println("The board location for the Token placement is not a valid location,\n" +
                    "you must place it on a previously played tile. Please try again");
            int x_axis = IOcascadia.takeIntInput();
            int y_axis = IOcascadia.takeIntInput();
            placeToken(x_axis, y_axis);       //if they pick a location when there is no tile recall function
        } else
        {
            playerBoard[x][y].playToken();
            for (int i = 0; i < playerBoard[x][y].getSlots().length; i++)
            {
                if (playerBoard[x][y].getSlot(i) == heldToken)
                {
                    TileGenerator helpTileGenerator = new TileGenerator(playerBoard[x][y]);
                    if (playerBoard[x][y].getSelect() == 1)
                    {
                        helpTileGenerator.tileUniqueColor(playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()), playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal()));

                        System.out.println("You have gained a nature token because you placed a wildlife token on a Keystone tile");
                        natureTokenNumber++;
                        isFilled =true;

                    } else if (playerBoard[x][y].getSelect() == 2)
                    {

                        if (i == 0)
                        {
                            helpTileGenerator.tileTwoColorsPlacedToken(playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()), playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()), playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal()));

                        }
                        if (i == 1)
                        {
                            helpTileGenerator.tileTwoColorsPlacedToken(playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()), playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal2()));

                        }
                        isFilled =true;

                    } else if (playerBoard[x][y].getSelect() == 3)
                    {
                        if (i == 0)
                        {
                            helpTileGenerator.tileTwoColorsPlacedToken(playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()), playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal()));

                        }
                        if (i == 1)
                        {
                            helpTileGenerator.tileTwoColorsPlacedToken(playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()), playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal2()));

                        }
                        if (i == 2)
                        {
                            helpTileGenerator.tileTwoColorsPlacedToken(playerBoard[x][y].colourConverter(playerBoard[x][y].getColour()), playerBoard[x][y].colourConverter(playerBoard[x][y].getColour2()),playerBoard[x][y].colourAnimal(playerBoard[x][y].getAnimal3()));

                        }
                        isFilled =true;

                    }
                    map.setTile(helpTileGenerator, x, y);


                    heldToken = null;


                }

            }
            if(isFilled==false)
            {
                System.out.println("No placeholder matches your held token");
                System.out.println("Please pick a different tile input x and y below");
                int x_axis = IOcascadia.takeIntInput();
                int y_axis = IOcascadia.takeIntInput();
                placeToken(x_axis,y_axis);
                // if tile they have selected does not have placeholder that matches heldtoken retry
            }
        }

    }
    //need to go through every tile on the board and be able to check if placeholder matches held token

    public boolean checkToken()
    {
        for(int i=0;i<playerBoard.length;i++)
        {
            for(int j=0;j<playerBoard.length;j++)
            {
                if(playerBoard[i][j]!=null)
                {
                    if(playerBoard[i][j].getSelect()==1)
                    {
                        if(playerBoard[i][j].getSlot(0)==heldToken)
                        {
                            hasToken=true;
                        }
                    }

                    else if(playerBoard[i][j].getSelect()==2)
                    {
                        for(int k=0;k<2;k++)
                        {
                            if (playerBoard[i][j].getSlot(k) == heldToken) {
                                hasToken = true;
                            }
                        }
                    }
                    else if(playerBoard[i][j].getSelect()==3)
                    {
                        for(int k=0;k<3;k++)
                        {
                            if (playerBoard[i][j].getSlot(k) == heldToken) {
                                hasToken = true;
                            }
                        }
                    }

                }
            }

        }
        if(hasToken==false)
        {
            System.out.println("No placeholders match your held token");
            return true;

        }
        else {
            return false;
        }
    }
    public void splitPick(){
        System.out.println("You have chosen to pick any one tile and wildlife token from the river" +
                "\n Enter the index of the tile you would like to chose:");
        int indexChoiceTile = IOcascadia.takeIntInput();
       while(indexChoiceTile < 1 || indexChoiceTile > 4){
           System.out.println("The number you have inputted is not a valid index.");
           indexChoiceTile = IOcascadia.takeIntInput();
       }
       this.heldTile = TileDeck.getRiverTilesIndex(indexChoiceTile);
       TileDeck.ReplaceRiverTilesIndex(indexChoiceTile);
       TileDeck.emptyDeckCheck();
        int indexChoiceToken = IOcascadia.takeIntInput();
        while(indexChoiceToken < 1 || indexChoiceToken > 4) {
            System.out.println("The number you have inputted is not a valid index.");
            indexChoiceToken = IOcascadia.takeIntInput();
        }
        this.heldToken = TileDeck.getRiverTokensIndex(indexChoiceToken);
        TileDeck.ReplaceRiverTokensIndex(indexChoiceToken);
        natureTokenNumber--;
        System.out.println("You now have " + natureTokenNumber + " NatureTokens left");
    }

    public void pickPair(int indexOfSelected){
        this.heldTile = TileDeck.getRiverTilesIndex(indexOfSelected);
        this.heldToken = TileDeck.getRiverTokensIndex(indexOfSelected);
        TileDeck.ReplaceRiverTilesIndex(indexOfSelected);
        TileDeck.ReplaceRiverTokensIndex(indexOfSelected);
        TileDeck.emptyDeckCheck();
    }


}

