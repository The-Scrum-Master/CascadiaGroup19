
import java.util.*;

public class TileDeck extends Stack<Tile> {
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

    public void playRiver(){
        TileDeck deck = new TileDeck();
        deck.shuffle();
        Tile[] riverTiles = new Tile[4];
        Wildlife[] riverTokens = new Wildlife[4];
        for(int i = 0; i < 5; i++){
            riverTokens[i] = Wildlife.randWildlife();
            riverTiles[i] = pop();
        }
    }
}
