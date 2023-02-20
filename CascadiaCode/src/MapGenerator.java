public class MapGenerator {
    public TileGenerator [][] map= new TileGenerator[20][20];



    public void addTile(TileGenerator tile){
        map[0][0]=tile;
        //map[0][0].printTile();
    }

    public void fillMap(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3){
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                if(j%3==0){
                    map[i][j]=tile1;
                }
                else if(j%3==1){
                    map[i][j]=tile2;
                }
                else{
                    map[i][j]=tile3;
                }
            }
        }
    }


    public void fillMapBlank(TileGenerator blank){
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j]=blank;
            }
        }
    }



/*
    public void fillMapBlank(){
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j]=TileGenerator.blankTile();
            }
        }
    }

 */




    public void fillMapBlankByParts(TileGenerator blank){ //NEED TO WORK ON THIS FUNCTION AND CREATE A PRINTMAPBYPARTS ONE, MAKING THEM FILL THE CENTER OF
        // THE ARRAY AND WHEN THE MAP HAS TO GET BIGGER FILL IN ADJACENT CIRCLES
        for(int i=0+8;i< map.length-8;i++) {
            for (int j = 0+8; j < map.length-8; j++) {
                map[i][j]=blank;
            }
        }
    }



/*         NEED TO CHANGE THIS TO TAKE IN AN INT THAT UPDATES FROM 8 TO 0 EVERY TURN SO THAT IN EACH TURN A BIGGER CIRCLE IS PRINTED
    public void printMapTotalByParts(int w){
        for(int w=8; w>=0; w--){
            for(int i=w;i< map.length-w;i++) {
                for(int k=0; k<4; k++){
                    if(i%2!=0)
                    {
                        System.out.print("      ");
                    }
                    for (int j = w; j < map.length-w; j++) {
                        map[i][j].printTilePerRow(k);
                    }
                    System.out.println();
                }
            }
        }
    }

 */


    public void printMapTotalByParts(){
        for(int i=0+8;i< map.length-8;i++) {
            for(int k=0; k<4; k++){
                if(i%2!=0)
                {
                    System.out.print("      ");
                }
                for (int j = 0+8; j < map.length-8; j++) {
                    map[i][j].printTilePerRow(k);
                }
                System.out.println();
            }
        }
    }

    public void setTile(TileGenerator tile, int x, int y){
        map[x][y]=tile;
    }

    public void starterTiles(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3){
        map[9][9]=tile1;
        map[10][9]=tile2;
        map[10][10]=tile3;
    }

    public void printMapTotal(){
        for(int i=0;i< map.length;i++) {

            for(int k=0; k<4; k++){
                if(i%2!=0)
                {
                    System.out.print("      ");
                }
                for (int j = 0; j < map.length; j++) {
                    map[i][j].printTilePerRow(k);
                }
                System.out.println();
            }
        }
    }


    public void fillMapWithAllowedTilePlacements(){
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                if(i==0 && j==0){
                    if(map[i][j].getEmptyTile() && (!map[i][j+1].getEmptyTile() || !map[i+1][j].getEmptyTile() || !map[i+1][j+1].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else if(i==0 && j== map.length-1){
                    if(map[i][j].getEmptyTile() && (!map[i][j-1].getEmptyTile() || !map[i+1][j-1].getEmptyTile() || !map[i+1][j].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else if(i== map.length -1 && j== 0){
                    if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() || !map[i-1][j+1].getEmptyTile() || !map[i][j+1].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else if(j== map.length -1 && j== map.length-1){
                    if(map[i][j].getEmptyTile() && (!map[i-1][j-1].getEmptyTile() || !map[i-1][j].getEmptyTile() || !map[i][j-1].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else if(i==0){
                    if(map[i][j].getEmptyTile() && (!map[i][j-1].getEmptyTile() || !map[i][j+1].getEmptyTile() ||
                            !map[i+1][j-1].getEmptyTile() || !map[i+1][j].getEmptyTile() || !map[i+1][j+1].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else if(i== map.length-1){
                    if(map[i][j].getEmptyTile() && (!map[i-1][j-1].getEmptyTile() || !map[i-1][j].getEmptyTile() ||
                            !map[i-1][j+1].getEmptyTile() || !map[i][j-1].getEmptyTile() || !map[i][j+1].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else if(j==0){
                    if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() || !map[i-1][j+1].getEmptyTile() ||
                            !map[i][j+1].getEmptyTile() || !map[i+1][j].getEmptyTile() || !map[i+1][j+1].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else if(j==map.length-1){
                    if(map[i][j].getEmptyTile() && (!map[i-1][j-1].getEmptyTile() || !map[i-1][j].getEmptyTile() ||
                            !map[i][j-1].getEmptyTile() || !map[i+1][j-1].getEmptyTile() || !map[i+1][j].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
                else {
                    if(map[i][j].getEmptyTile() && (!map[i-1][j-1].getEmptyTile() || !map[i-1][j].getEmptyTile() ||
                            !map[i-1][j+1].getEmptyTile() || !map[i][j-1].getEmptyTile() ||
                            !map[i][j+1].getEmptyTile() || !map[i+1][j-1].getEmptyTile() ||
                            !map[i+1][j].getEmptyTile() || !map[i+1][j+1].getEmptyTile()) ){

                        TileGenerator blankTileWIthCords=new TileGenerator();
                        blankTileWIthCords.blankTileWIthCords(i, j);
                        map[i][j]=blankTileWIthCords;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        MapGenerator map=new MapGenerator();

        TileGenerator blank =new TileGenerator();
        blank.blankTile();

        TileGenerator InitialTileSingleColoured = new TileGenerator(Tile.generateSpecificTile(1));
        TileGenerator InitialTileDoubleColoured1 = new TileGenerator(Tile.generateSpecificTile(2));
        TileGenerator InitialTileDoubleColoured2 = new TileGenerator(Tile.generateSpecificTile(3));

        map.fillMapBlank(blank);
        map.starterTiles(InitialTileSingleColoured, InitialTileDoubleColoured1, InitialTileDoubleColoured2);
        map.printMapTotalByParts();

        map.fillMapWithAllowedTilePlacements();
        map.printMapTotalByParts();
    }
}
