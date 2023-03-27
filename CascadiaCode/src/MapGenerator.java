/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public class MapGenerator {
    public TileGenerator [][] map= new TileGenerator[46][46];


    public void fillMapBlank(TileGenerator blank){
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j]=blank;
            }
        }
    }



    public void fillMapBlankByParts(TileGenerator blank){ //NEED TO WORK ON THIS FUNCTION AND CREATE A PRINTMAPBYPARTS ONE, MAKING THEM FILL THE CENTER OF
        // THE ARRAY AND WHEN THE MAP HAS TO GET BIGGER FILL IN ADJACENT CIRCLES
        for(int i=0+21;i< map.length-21;i++) {
            for (int j = 0+21; j < map.length-21; j++) {
                map[i][j]=blank;
            }
        }
    }

    public void printMapTotalByCircle(int helperInt){ //handle whenever helperInt gets too big, need to see what value is "too big"
        for(int i=0+21-helperInt;i< map.length-21+helperInt;i++) {
            for(int k=0; k<4; k++){
                if(i%2!=0)
                {
                    //System.out.print("      ");
                }
                for (int j = 0+21-helperInt; j < map.length-21+helperInt; j++) {
                    map[i][j].printTilePerRow(k);
                }
                System.out.println();
            }
        }
    }

    public void printMapTotalByParts(){
        for(int i=0+21;i< map.length-21;i++) {
            for(int k=0; k<4; k++){
                if(i%2!=0)
                {
                    //System.out.print("      ");
                }
                for (int j = 0+21; j < map.length-21; j++) {
                    map[i][j].printTilePerRow(k);
                }
                System.out.println();
            }
        }
    }

    public void setTile(TileGenerator tile, int x, int y){
        map[x][y]=tile;
    }

    public void fillStarterMapBlank(TileGenerator blank){
        for(int i=21;i< map.length-21;i++) {
            for (int j = 21; j < map.length-21; j++) {
                map[i][j]=blank;
            }
        }
    }

    public void starterTiles(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3){
        map[22][22]=tile1;
        map[23][22]=tile2;
        map[23][23]=tile3;
    }

    public void printMapTotal(){
        for(int i=0;i< map.length;i++) {

            for(int k=0; k<4; k++){
                if(i%2!=0)
                {
                    //System.out.print("      ");
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
                if(map[i][j]!=null){
                    if(i==0 && j==0){
                        if(map[i][j].getEmptyTile() && (!map[i][j+1].getEmptyTile() || !map[i+1][j].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else if(i==0 && j== map.length-1){
                        if(map[i][j].getEmptyTile() && (!map[i][j-1].getEmptyTile() || !map[i+1][j].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else if(i== map.length -1 && j== 0){
                        if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() || !map[i][j+1].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else if(j== map.length -1 && j== map.length-1){
                        if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() || !map[i][j-1].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else if(i==0){
                        if(map[i][j].getEmptyTile() && (!map[i][j-1].getEmptyTile() || !map[i][j+1].getEmptyTile() ||
                                !map[i+1][j].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else if(i== map.length-1){
                        if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() || !map[i][j-1].getEmptyTile() || !map[i][j+1].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else if(j==0){
                        if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() ||
                                !map[i][j+1].getEmptyTile() || !map[i+1][j].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else if(j==map.length-1){
                        if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() ||
                                !map[i][j-1].getEmptyTile() || !map[i+1][j].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
                    }
                    else {
                        if(map[i][j].getEmptyTile() && (!map[i-1][j].getEmptyTile() || !map[i][j-1].getEmptyTile() ||
                                !map[i][j+1].getEmptyTile() || !map[i+1][j].getEmptyTile()) ){

                            TileGenerator blankTileWIthCords=new TileGenerator();
                            blankTileWIthCords.blankTileWIthCords(i, j);
                            map[i][j]=blankTileWIthCords;
                        }
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
        map.printMapTotal();

        map.fillMapWithAllowedTilePlacements();
        map.printMapTotal();
    }
}
