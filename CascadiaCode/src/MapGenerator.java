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

    public void printMap(){
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

    public static void main(String[] args) {

    }
}
