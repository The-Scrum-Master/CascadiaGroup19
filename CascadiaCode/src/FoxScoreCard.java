/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public abstract class FoxScoreCard implements ScoreCard {

        public ArrayList<Integer> cordinateX;
        public  ArrayList<Integer> cordinateY;
    public ArrayList<Wildlife> types;

    Player player;
        public FoxScoreCard(Player player){
            cordinateX = new ArrayList<>();
            cordinateY = new ArrayList<>();
            types = new ArrayList<>();
            this.player=player;
        }

        @Override
        public int countScore() {
            return 0;
        }

      //function to get indexes of all fox tokens
        public void getIndexes(Tile[][] playerBoard) {
            for(int rows = 0; rows < 46; rows++ ){
                for(int columns =0; columns < 46; columns++){
                    try{
                        if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.FOX)){
                            cordinateX.add(columns);
                            cordinateY.add(rows);
                        }
                    } catch (Exception ignored){
                    }
                }
            }
        }
    }


