
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

    public void tileUniqueColor(String color,String letter1){
        boolean placing1=true;
        boolean placing2=false;
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 || i==2){
                    if(j==0 || j==3){
                        tile[i][j] = color + "   "+DisplayColour.RESET;
                    }
                    else if(placing1){
                        tile[i][j] = " "+ letter1 +" ";
                        placing1=false;
                        //placing2=true;
                    }
                    /*else if(placing2){
                        tile[i][j] = " "+ letter2 +" ";
                        placing2=false;
                    }

                     */
                    else{
                        tile[i][j] = "   ";
                    }
                }
                else{
                    tile[i][j] = color + "   "+DisplayColour.RESET;
                }
            }
        }
    }

    public void tileTwoColors(String color1, String color2, String letter1, String letter2, String letter3){
        String color;
        boolean placing1=true;
        boolean placing2=false;
        boolean placing3=false;
        int toggle=0;
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 || i==2){
                    if(toggle%2==0){
                        color=color1;
                    }
                    else{
                        color=color2;
                    }
                    toggle++;
                    if(j==0 || j==3){
                        tile[i][j] = color + "   "+DisplayColour.RESET;
                    }
                    else if(placing1){
                        tile[i][j] = " "+ letter1 +" ";
                        placing1=false;
                        placing2=true;
                    }
                    else if(placing2){
                        tile[i][j] = " "+ letter2 +" ";
                        placing2=false;
                        placing3=true;
                    }
                    else if(placing3){
                        tile[i][j] = " "+ letter3 +" ";
                        placing3=false;
                    }
                    else{
                        tile[i][j] = "   ";
                    }
                }
                else if(i==0){
                    tile[i][j] = color1 + "   "+DisplayColour.RESET;
                }
                else{
                    tile[i][j] = color2 + "   "+DisplayColour.RESET;
                }
            }
        }
    }

    /*
    public void generateTile(String color, char letter){
        tileUniqueColor(color, letter);
    }
     */

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

        blank.blankTile();
        TileGenerator unique1 =new TileGenerator();
       // unique1.tileUniqueColor("\033[42m", t.colourAnimal(t.getAnimal()), t.colourAnimal(t.getAnimal()));
        TileGenerator unique2 =new TileGenerator();
        //unique2.tileUniqueColor("\033[44m", t.colourAnimal(t.getAnimal()), t.colourAnimal(t.getAnimal()));
        TileGenerator unique3 =new TileGenerator();
        //unique3.tileUniqueColor("\033[43m", t.colourAnimal(t.getAnimal()), t.colourAnimal(t.getAnimal()));
        TileGenerator double1 =new TileGenerator();
        double1.tileTwoColors("\033[42m", t.colourConverter(t.getSymbol()), t.colourAnimal(t.getAnimal()), " ", t.colourAnimal(t.getAnimal()));
        TileGenerator double2 =new TileGenerator();
        double2.tileTwoColors("\033[41m", "\033[45m", "", " ", " ");
//COMMENTS??????
        t.randomSlot();
        double2.tileTwoColors("\033[41m", "\033[45m", t.colourAnimal(t.getAnimal()), " ", " ");


//COMMENTS??????

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
        //map.fillMap(unique1, unique2, unique3);


        //map.fillMapBlank(blank);
        //map.starterTiles(unique2, double1, double2);
        //map.printMapTotal();

        map.fillMapBlankByParts(blank);
        map.starterTilesReducedMap(unique2, double1, double2);
        map.printMapTotalByParts();
    }
}
