public class MapGenerator {
    public TileGenerator [][] map= new TileGenerator[20][20];

    public void addTile(TileGenerator tile){
        TileGenerator newTile = new TileGenerator();
        //map[0][0]=newTile.generateBlankTile();
    }

    public void printMap(){
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println(DisplayColour.RESET);
        }
    }

    public static void main(String[] args) {

    }
}
