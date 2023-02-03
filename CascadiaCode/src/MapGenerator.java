public class MapGenerator {
    public TileGenerator [][] map= new TileGenerator[20][20];

    public void addTile(TileGenerator tile){
        map[0][0]=tile;
        //map[0][0].printTile();
    }

    public void fillMap(TileGenerator tile){
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j]=tile;
            }
        }
    }

    public void printMap(){
        for(int i=0;i< map.length;i++) {
            for(int k=0; k<4; k++){
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
