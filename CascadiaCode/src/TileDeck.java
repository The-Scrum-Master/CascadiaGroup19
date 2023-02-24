import java.util.*;

/**
 * This class creates a stack of deck of object tiles and has the functions for the river, and the cull options
 * Notes -- we may want to fix the fact that every time we call function repopulate the river we generate 85 tile for the stack;
 *Functions: createNewDeck= fills the deck variable with new 85 tiles;
 *           playRiver= takes in deck and fills in the two arrays of tiles and tokens;
 *           cullCheck= checks if a cull is needed and calls the function cullOption from IO if relevant
 * @author Patrick Kelly
 */
public class TileDeck extends Stack<Tile> {

    public static Tile[] riverTiles = new Tile[4];
    public static Wildlife[] riverTokens = new Wildlife[4];
    static TileDeck deck;

    TileDeck(){
        for(int i = 0; i < 85; i++){
            if(i < 25){
                super.add(new Tile(0));
            } else if (i < 55) {
                super.add(new Tile(2));
            }else{
                super.add(new Tile(3));
            }
        }
    }
    public void shuffle () {
        Collections.shuffle(this);
    }

    public TileDeck getDeck(){return deck;}
    public static Tile[] getRiverTiles(){ return riverTiles;}

    public static Wildlife[] getRiverTokens(){return riverTokens;}
    public static Tile getRiverTilesIndex(int index){ return riverTiles[index];}
     public static Wildlife getRiverTokensIndex(int index){return riverTokens[index];}
    public static void ReplaceRiverTilesIndex(int index){  riverTiles[index] = deck.pop();}
    public static void ReplaceRiverTokensIndex(int index){ riverTokens[index] = Wildlife.randWildlife();}

    public static TileDeck createNewDeck(){
        deck = new TileDeck();
        deck.shuffle();
        return deck;
    }

    public  static void playRiver(TileDeck deck){
        for(int i = 0; i < 4; i++){
            riverTokens[i] = Wildlife.randWildlife();
            riverTiles[i] = deck.pop();
        }
    }

    public static void cullCheck(TileDeck deck){
        int hawkNum = 0;
        int elkNum = 0;
        int bearNum = 0;
        int foxNum = 0;
        int salmonNum = 0;
        for(int i = 0; i < 4; i++){
            if(getRiverTokensIndex(i) == Wildlife.HAWK){
                hawkNum++;
            } else if (getRiverTokensIndex(i) == Wildlife.BEAR){
                bearNum++;
            } else if(getRiverTokensIndex(i) == Wildlife.FOX){
                foxNum++;
            } else if (getRiverTokensIndex(i) == Wildlife.ELK) {
                elkNum++;
            } else if (getRiverTokensIndex(i) == Wildlife.SALMON) {
                salmonNum++;
            }
        }
        if (hawkNum == 4 || elkNum == 4 || bearNum == 4 || foxNum == 4 || salmonNum ==4){
            System.out.println("The river has been automatically culled\n\n");
            playRiver(deck);
            PairDisplay.showPairs();
            cullCheck(deck);
        }
        else if (hawkNum == 3 || elkNum == 3 || bearNum == 3 || foxNum == 3 || salmonNum ==3){
            if (IOcascadia.cullOption()){
                playRiver(deck);
                PairDisplay.showPairs();
                cullCheck(deck);
            }
        }
    }

    public static void main(String[] args) {
        TileDeck deckTest = createNewDeck();
        deckTest.shuffle();
        PairDisplay p = new PairDisplay();
        playRiver(deckTest);
//        p.showPairs();
        cullCheck(deckTest);
//        Player jimmy = new Player("pat", 3);
//        jimmy.pickPair(2);
        PairDisplay.showPairs();
    }
}
