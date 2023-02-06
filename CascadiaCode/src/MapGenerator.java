public class MapGenerator {
    public TileGenerator [][] map= new TileGenerator[20][20];
    public TileGenerator [] displayTilesToChooseFrom= new TileGenerator[8];
    int currentMapWidth=4;

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


    public void fillMapBlankByParts(TileGenerator blank){ //NEED TO WORK ON THIS FUNCTION AND CREATE A PRINTMAPBYPARTS ONE, MAKING THEM FILL THE CENTER OF
        // THE ARRAY AND WHEN THE MAP HAS TO GET BIGGER FILL IN ADJACENT CIRCLES
        for(int i=0+8;i< map.length-8;i++) {
            for (int j = 0+8; j < map.length-8; j++) {
                map[i][j]=blank;
            }
        }
    }
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
    public void starterTilesReducedMap(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3){
        map[9][9]=tile1;
        map[10][9]=tile2;
        map[10][10]=tile3;
    }

    public void starterTiles(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3){
        map[0][0]=tile1;
        map[1][0]=tile2;
        map[1][1]=tile3;
    }

    public void printMapTotal(){
        for(int i=0;i< map.length;i++) {

            for(int k=0; k<4; k++){
                if(i%2!=1)
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

    public void fillArrayToTest(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3, TileGenerator tile4, TileGenerator blank){
        for(int i=0;i<8;i++) {
            if (i % 2 == 1) {
                displayTilesToChooseFrom[i] = blank;
            }
        }
        displayTilesToChooseFrom[0]=tile1;
        displayTilesToChooseFrom[2]=tile2;
        displayTilesToChooseFrom[4]=tile3;
        displayTilesToChooseFrom[6]=tile4;
    }

    public void fillArrayToTestWIthTokens(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3, TileGenerator tile4, TileGenerator token1, TileGenerator token2, TileGenerator token3, TileGenerator token4){
        displayTilesToChooseFrom[0]=tile1;
        displayTilesToChooseFrom[1]=token1;
        displayTilesToChooseFrom[2]=tile2;
        displayTilesToChooseFrom[3]=token2;
        displayTilesToChooseFrom[4]=tile3;
        displayTilesToChooseFrom[5]=token3;
        displayTilesToChooseFrom[6]=tile4;
        displayTilesToChooseFrom[7]=token4;
    }

    public void tileDisplay(){ //there has to be an array of
        for(int k=0; k<4; k++){
            for (int j = 0; j < displayTilesToChooseFrom.length; j++) {
                displayTilesToChooseFrom[j].printTilePerRow(k);
            }
            System.out.println();
        }
    }




    public static void main(String[] args) {
    }
}
