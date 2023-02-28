
public class TileGenerator {

    public String [][] tile= new String[4][4];
    public static String [][] blankTile= new String[4][4];

    Tile constructorTile;
    boolean emptyTile=false;
    private String stringColour2;
    private String stringColour;


    public TileGenerator(){
    }

    public TileGenerator (Tile constructorTile){ //put the habitats and the placeholders as inputs into the constructor?
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
            //generate random number and use that number with some if else statements to generate either single colour or double
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
            //System.out.println("Tilegnerator colours");
            //System.out.println(stringColour);
            //System.out.println(stringColour2);

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



/*
    public static void blankTileFiller(TileGenerator tile){
        for(int i=0;i< blankTile.length;i++) {
            for (int j = 0; j < blankTile.length; j++) {
                blankTile[i][j] = "   ";
            }
        }
    }

    public static TileGenerator blankTile(){
        TileGenerator blank=new TileGenerator();
        for(int i=0;i< blankTile.length;i++) {
            for (int j = 0; j < blankTile.length; j++) {
                blankTile[i][j] = "   ";
            }
        }
        return blank;
    }

 */



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
                        tile[i][j] = " "+ cord1 +",";
                    }
                }
                else if(i==1 && j==2){
                    if(cord1<10){
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

    /*
    public void generateTile(String color, char letter){
        tileUniqueColor(color, letter);
    }
     */
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
        //TileGenerator unique2 =new TileGenerator();
        //unique2.tileUniqueColor(t1.colourConverter(t1.getSymbol()), t1.colourAnimal(t1.getAnimal()));
        //TileGenerator unique3 =new TileGenerator();
        //unique3.tileUniqueColor("\033[43m", t.colourAnimal(t.getAnimal()), t.colourAnimal(t.getAnimal()));
        //TileGenerator double1 =new TileGenerator();
        //double1.tileTwoColors(t2.colourConverter(t2.getSymbol()), t2.colourConverter(t2.getSymbol2()), t2.colourAnimal(t2.getAnimal2()), t2.colourAnimal(t2.getAnimal()), t2.colourAnimal(t2.getAnimal()));
        //TileGenerator double2 =new TileGenerator();
        //double2.tileTwoColors(t3.colourConverter(t3.getSymbol()), t3.colourConverter(t3.getSymbol2()), t3.colourAnimal(t3.getAnimal2()), " ", t3.colourAnimal(t3.getAnimal()));
//COMMENTS??????



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
        map.fillMapBlank(blank);
        //map.starterTiles(unique2, double1, double2);
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


        //map.fillMapBlankByParts(blank);
        //map.starterTilesReducedMap(unique1, double1, double2);
        //map.printMapTotalByParts();
    }
}
