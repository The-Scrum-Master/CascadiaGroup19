
public class TileGenerator {

    public String [][] tile= new String[4][4];

    public TileGenerator (){ //put the habitats and the placeholders as inputs into the constructor

    }

    public void blankTile(){
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                tile[i][j] = "   ";
            }
        }
    }

    public void tileUniqueColor(String color, char letter){
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 || i==2){
                    if(j==0 || j==3){
                        tile[i][j] = color + "   "+DisplayColour.RESET;
                    }
                    else{
                        tile[i][j] = " "+ letter +" ";
                    }
                }
                else{
                    tile[i][j] = color + "   "+DisplayColour.RESET;
                }
            }
        }
    }

    public void generateTile(String color, char letter){
        tileUniqueColor(color, letter);
    }

    public void printTile(){
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                System.out.print(tile[i][j]);
            }
            System.out.println(DisplayColour.RESET);
        }
    }

    public void printTilePerRow(int row){
        for(int j=0;j< tile.length;j++) {
            System.out.print(tile[row][j]);
        }
    }


    public static void main(String[] args) {
        MapGenerator map=new MapGenerator();
        TileGenerator blank =new TileGenerator();
        Tile t = new Tile(Tile.tileType.SOLO);

        t.randomHabitat();

        System.out.println(t.colourConverter(t.getSymbol()));
        blank.blankTile();
        TileGenerator unique1 =new TileGenerator();
        unique1.generateTile(t.colourConverter(t.getSymbol()),t.getSymbol());
        TileGenerator unique2 =new TileGenerator();
        unique2.generateTile("\033[44m", 'F');
        TileGenerator unique3 =new TileGenerator();
        unique3.generateTile("\033[43m", 'F');



        /*
        blank.printTile();
        blank.printTile();

        unique.printTile();
        blank.printTile();
        blank.printTile();
        blank.printTile();blank.printTile();
        blank.printTile();blank.printTile();blank.printTile();blank.printTile();*/

        /*unique.printTilePerRow(0);
        blank.printTile();blank.printTile();blank.printTile();blank.printTile();
         */


        //map.addTile(unique);
        map.fillMap(unique1, unique2, unique3);
        map.printMap();
    }
}
