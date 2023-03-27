/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public class TileGenerator {

    public String [][] tile= new String[4][4];
    public static String [][] blankTile= new String[4][4];

    Tile constructorTile;
    boolean emptyTile=false;
    private String stringColour2;
    private String stringColour;


    public TileGenerator(){
    }

    public TileGenerator (Tile constructorTile){
        //put the habitats and the placeholders as inputs into the constructor?
        this.constructorTile = constructorTile;
        if(constructorTile.getSelect()==1){
            tileUniqueColor(constructorTile.colourConverter(constructorTile.getColour()), constructorTile.colourAnimal(constructorTile.getAnimal()));
        }
        else if(constructorTile.getSelect()==2){
            tileTwoColors(constructorTile.colourConverter(constructorTile.getColour()), constructorTile.colourConverter(constructorTile.getColour2()), constructorTile.colourAnimal(constructorTile.getAnimal()), constructorTile.colourAnimal(constructorTile.getAnimal2()), " ");
        }
        else if(constructorTile.getSelect()==3){
            tileTwoColors(constructorTile.colourConverter(constructorTile.getColour()), constructorTile.colourConverter(constructorTile.getColour2()), constructorTile.colourAnimal(constructorTile.getAnimal()), constructorTile.colourAnimal(constructorTile.getAnimal2()), constructorTile.colourAnimal(constructorTile.getAnimal3()));
        }
        else if(constructorTile.getSelect()==0){
            int rand=Tile.randomNumberGenerator(3) + 1;
            if(rand==1){
                tileUniqueColor(constructorTile.colourConverter(constructorTile.getColour()), constructorTile.colourAnimal(constructorTile.getAnimal()));
            }
            else if(rand==2){
                tileTwoColors(constructorTile.colourConverter(constructorTile.getColour2()), constructorTile.colourConverter(constructorTile.getColour()), constructorTile.colourAnimal(constructorTile.getAnimal()), constructorTile.colourAnimal(constructorTile.getAnimal2()), " ");
            }
            else if(rand==3){
                tileTwoColors(constructorTile.colourConverter(constructorTile.getColour2()), constructorTile.colourConverter(constructorTile.getColour()), constructorTile.colourAnimal(constructorTile.getAnimal()), constructorTile.colourAnimal(constructorTile.getAnimal2()), constructorTile.colourAnimal(constructorTile.getAnimal3()));
            }
        }
    }

    public void generateFlipTile()
    {
        if(this.constructorTile.getSelect()==1)
        {
            System.out.println("This is a unique tile cannot be flipped");

        }
        else
        {

            stringColour= this.constructorTile.colourConverter(this.constructorTile.getHabitat(0).getSymbol());

            stringColour2= this.constructorTile.colourConverter(this.constructorTile.getHabitat(1).getSymbol());


            generateNewFlippedTile(stringColour,stringColour2);
        }
    }

    public void generateNewFlippedTile(String color1,String color2)
    {
        String color;
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
                    else{

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



    public void blankTile(){
        emptyTile=true;
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                tile[i][j] = "   ";
            }
        }
    }








    public void blankTileWIthToken(String letter1){
        emptyTile=true;
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 && j==1){
                    tile[i][j] = " "+ letter1 +" ";
                }
                else{
                    tile[i][j] = "   ";
                }
            }
        }
    }

    public void blankTileWIthCords(int cord1, int cord2){
        emptyTile=true;
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 && j==1){
                    if(cord1<10){
                        tile[i][j] = " "+ cord1 +",";
                    }
                    else{
                        tile[i][j] = ""+ cord1 +",";
                    }
                }
                else if(i==1 && j==2){
                    if(cord2<10){
                        tile[i][j] = " "+ cord2 +" ";
                    }
                    else{
                        tile[i][j] = " "+ cord2 +"";
                    }
                }
                else{
                    tile[i][j] = "   ";
                }
            }
        }
    }


    public void tileUniqueColor(String color,String letter1){
        boolean placing1=true;
        emptyTile=false;
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 || i==2){
                    if(j==0 || j==3){
                        tile[i][j] = color + "   "+DisplayColour.RESET;
                    }
                    else if(placing1){
                        tile[i][j] = " "+ letter1 +" ";
                        placing1=false;
                    }
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

    public void tileUniqueColorPlacedToken(String color,String letter1){
        boolean placing1=true;
        emptyTile=false;
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 || i==2){
                    if(j==0 || j==3){
                        tile[i][j] = color + "   "+DisplayColour.RESET;
                    }
                    else if(placing1){
                        tile[i][j] = "\u001B[40m" +" "+  "\u001B[40m"+ removeBackground(letter1) + "\u001B[40m" +" "+DisplayColour.RESET;
                        placing1=false;
                    }
                    else{
                        tile[i][j] = "\u001B[40m" + "   "+DisplayColour.RESET;
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
        emptyTile=false;
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

    public void tileTwoColorsPlacedToken (String color1, String color2, String letter1){
        String color;
        boolean placing1=true;
        emptyTile=false;

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
                        tile[i][j] = "\u001B[40m" +" "+  "\u001B[40m"+ removeBackground(letter1) + "\u001B[40m" +" "+DisplayColour.RESET;
                        placing1=false;
                    }
                    else{
                        tile[i][j] = "\u001B[40m" + "   "+DisplayColour.RESET;
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


    public String removeBackground(String s)
    {
        String ANSI_RESET = "\u001B[0m";
        String returnString =ANSI_RESET +s+ ANSI_RESET;
        return returnString;
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

    public boolean getEmptyTile() {
        return emptyTile;
    }


    public static void main(String[] args) {
        MapGenerator map=new MapGenerator();
        TileGenerator blank =new TileGenerator();
        Tile t = new Tile( 1);
        Tile t1 = new Tile( 1);
        Tile t2 = new Tile( 2);
        Tile t3 = new Tile( 2);

        blank.blankTile();
        TileGenerator unique1 =new TileGenerator();
        unique1.tileUniqueColor(t.colourConverter(t.getColour()), t.colourAnimal(t.getAnimal()));




        map.fillMapBlank(blank);
        map.printMapTotal();








        MapGenerator mapencio=new MapGenerator();
        TileGenerator blankencio =new TileGenerator();
        blank.blankTile();


        TileGenerator tileTrail = new TileGenerator(Tile.generateRandomTile());
        TileGenerator InitialTileSingleColoured = new TileGenerator(Tile.generateSpecificTile(1));
        TileGenerator InitialTileDoubleColoured1 = new TileGenerator(Tile.generateSpecificTile(2));
        TileGenerator InitialTileDoubleColoured2 = new TileGenerator(Tile.generateSpecificTile(3));

        mapencio.fillMapBlank(blank);
        mapencio.starterTiles(InitialTileSingleColoured, InitialTileDoubleColoured1, InitialTileDoubleColoured2);
        mapencio.setTile(tileTrail, 9, 10 );

        mapencio.printMapTotal();


    }
}
