public class MapGenerator {
    public TileGenerator [][] map= new TileGenerator[20][20];

    public void addTile(TileGenerator tile){
        map[0][0]=tile;
        map[0][0].printTile();
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
            for (int j = 0; j < map.length; j++) {
                map[i][j].printTile();
            }
            System.out.println(DisplayColour.RESET);
        }
    }

    public static void main(String[] args) {

    }
}
