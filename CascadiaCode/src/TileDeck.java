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

    public TileDeck createNewDeck(){
        deck = new TileDeck();
        deck.shuffle();
        return deck;
    }

    public void playRiver(TileDeck deck){
        for(int i = 0; i < 4; i++){
            riverTokens[i] = Wildlife.randWildlife();
            riverTiles[i] = deck.pop();
        }
    }
    public void cullCheck(){
        for(int j = 0; j < 4; j++){
            int cullCount = 0;
            for(int k = 0; k < 4; k++){
                if(riverTokens[j] == riverTokens[k]){
                    cullCount++;
                }
                if(cullCount == 3){
                    if(IOcascadia.cullOption()){
                        playRiver(deck);
                        cullCheck();
                    }
                } else if (cullCount > 3){
                    playRiver(deck);
                    cullCheck();
                }
            }
        }
    }
}
