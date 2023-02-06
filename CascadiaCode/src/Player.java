public class Player {
    private final int order;
    private final String name;

    public Tile [][] playerBoard;

    public Player(String name, int order){
        this.name = name;
        this.order = order;
        playerBoard= new Tile[20][20]; //new board of 20 by 20 tiles
        playerBoard[9][9] = new Tile(Tile.tileType.SOLO,0); //generates the three starting tiles
        playerBoard[9][9].playTile(); //changes boolean on tile to played.
        playerBoard[10][9] = new Tile(Tile.tileType.NORMAL,2);
        playerBoard[10][9].playTile();
        playerBoard[10][10] = new Tile(Tile.tileType.NORMAL, 3);
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

}

