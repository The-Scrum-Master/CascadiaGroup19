/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public abstract class SalmonScoreCard implements ScoreCard{
    protected ArrayList<TokenForPoints> arrayOfTokens;
    //public ArrayList<Integer> cordinateX;
    //public  ArrayList<Integer> cordinateY;

    public SalmonScoreCard(Player player){
        arrayOfTokens = new ArrayList<>();
        //cordinateX = new ArrayList<>();
        //cordinateY = new ArrayList<>();
    }

    @Override
    public int countScore() {
        return 0;
    }

    public void getIndexes(Tile[][] playerBoard) {
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                try{
                    if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.SALMON)){
                        arrayOfTokens.add(new TokenForPoints(columns, rows));
                        //cordinateX.add(columns);
                        //cordinateY.add(rows);
                    }
                } catch (Exception ignored){
                }
            }
        }
    }

}
