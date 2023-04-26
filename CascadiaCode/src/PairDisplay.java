/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public class PairDisplay {
    public Tile tile1;
    public Tile tile2;
    public Tile tile3;
    public Tile tile4;
    public static TileGenerator [] displayTilesToChooseFrom= new TileGenerator[4];
    public static TileGenerator [] displayTokensToChooseFrom= new TileGenerator[4];

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

    public static void fillTileArray(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3, TileGenerator tile4){
        displayTilesToChooseFrom[0]=tile1;
        displayTilesToChooseFrom[1]=tile2;
        displayTilesToChooseFrom[2]=tile3;
        displayTilesToChooseFrom[3]=tile4;
    }

    public static void tileDisplay(){ //there has to be an array of
        for(int k=0; k<4; k++){
            for (int j = 0; j < displayTilesToChooseFrom.length; j++) {
                displayTilesToChooseFrom[j].printTilePerRow(k);
            }
            System.out.println();
        }
    }

    public static void tokensDisplay(){ //there has to be an array of
        for(int k=0; k<4; k++){
            for (int j = 0; j < displayTokensToChooseFrom.length; j++) {
                displayTokensToChooseFrom[j].printTilePerRow(k);
            }
            System.out.println();
        }
    }

    public static void fillTokenArray(TileGenerator token1, TileGenerator token2, TileGenerator token3, TileGenerator token4){
        displayTokensToChooseFrom[0]=token1;
        displayTokensToChooseFrom[1]=token2;
        displayTokensToChooseFrom[2]=token3;
        displayTokensToChooseFrom[3]=token4;
    }

    public static void showPairs(){
        TileGenerator riverTile1 = new TileGenerator(TileDeck.getRiverTilesIndex(0));
        TileGenerator riverTile2 = new TileGenerator(TileDeck.getRiverTilesIndex(1));
        TileGenerator riverTile3 = new TileGenerator(TileDeck.getRiverTilesIndex(2));
        TileGenerator riverTile4 = new TileGenerator(TileDeck.getRiverTilesIndex(3));

        /*

        Tile t = new Tile(0);
        Tile t1 = new Tile( 0);
        Tile t2 = new Tile(0);
        Tile t3 = new Tile(0);

        TileGenerator unique1 =new TileGenerator();
        //unique1.tileUniqueColor(t.colourConverter(t.getSymbol()), t.colourAnimal(t.getAnimal()));
        TileGenerator double2 =new TileGenerator();
        //double2.tileTwoColors(t2.colourConverter(t2.getSymbol()), t2.colourConverter(t2.getSymbol2()), t2.colourAnimal(t2.getAnimal2()), t2.colourAnimal(t2.getAnimal()), t2.colourAnimal(t2.getAnimal()));
        TileGenerator double3 =new TileGenerator();
        //double3.tileTwoColors(t3.colourConverter(t3.getSymbol()), t3.colourConverter(t3.getSymbol2()), t3.colourAnimal(t3.getAnimal2()), " ", t3.colourAnimal(t3.getAnimal()));
        TileGenerator double1 =new TileGenerator();
        //double1.tileTwoColors(t1.colourConverter(t1.getSymbol()), t1.colourConverter(t1.getSymbol2()), t1.colourAnimal(t1.getAnimal2()), t1.colourAnimal(t1.getAnimal()), t1.colourAnimal(t1.getAnimal()));
         */




        TileGenerator blankTileWIthToken1 =new TileGenerator();
        blankTileWIthToken1.blankTileWIthToken(Wildlife.animalSymbol(TileDeck.getRiverTokensIndex(0)));
        TileGenerator blankTileWIthToken2 =new TileGenerator();
        blankTileWIthToken2.blankTileWIthToken(Wildlife.animalSymbol(TileDeck.getRiverTokensIndex(1)));
        TileGenerator blankTileWIthToken3 =new TileGenerator();
        blankTileWIthToken3.blankTileWIthToken(Wildlife.animalSymbol(TileDeck.getRiverTokensIndex(2)));
        TileGenerator blankTileWIthToken4 =new TileGenerator();
        blankTileWIthToken4.blankTileWIthToken(Wildlife.animalSymbol(TileDeck.getRiverTokensIndex(3)));

        fillTileArray(riverTile1, riverTile2, riverTile3, riverTile4);
        fillTokenArray(blankTileWIthToken1, blankTileWIthToken2, blankTileWIthToken3, blankTileWIthToken4);
        tileDisplay();
        tokensDisplay();
        TileDeck.cullCheck();
    }
}
