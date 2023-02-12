
import java.util.*;

public class TileDeck extends Stack<Tile> {
    TileDeck(){
        for(int i = 0; i < 85; i++){
            if(i < 25){
                super.add(new Tile(Tile.tileType.SOLO, 0));
            } else if (i < 55) {
                super.add(new Tile(Tile.tileType.NORMAL, 2));
            }else{
                super.add(new Tile(Tile.tileType.NORMAL, 3));
            }
        }
    }
    public void shuffle () {
        Collections.shuffle(this);
    }

    public void playRiver(){
        TileDeck deck = new TileDeck();
        deck.shuffle();
        Tile[] riverTiles = new Tile[5];
        Wildlife[] riverTokens = new Wildlife[5];
        for(int i = 0; i < 5; i++){
            //riverTokens[i] = Tile.randomSlot();
            riverTiles[i] = pop();
        }

    }
}