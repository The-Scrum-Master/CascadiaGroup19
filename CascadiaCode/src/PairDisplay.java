/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public class PairDisplay {
    public static TileGenerator [] displayTilesToChooseFrom= new TileGenerator[4];
    public static TileGenerator [] displayTokensToChooseFrom= new TileGenerator[4];

    public static void fillTileArray(TileGenerator tile1, TileGenerator tile2, TileGenerator tile3, TileGenerator tile4){
        displayTilesToChooseFrom[0]=tile1;
        displayTilesToChooseFrom[1]=tile2;
        displayTilesToChooseFrom[2]=tile3;
        displayTilesToChooseFrom[3]=tile4;
    }

    public static void tileDisplay(){
        for(int k=0; k<4; k++){
            for (int j = 0; j < displayTilesToChooseFrom.length; j++) {
                displayTilesToChooseFrom[j].printTilePerRow(k);
            }
            System.out.println();
        }
    }

    public static void tokensDisplay(){
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
