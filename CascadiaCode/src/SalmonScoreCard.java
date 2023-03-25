import java.util.ArrayList;

public abstract class SalmonScoreCard implements ScoreCard {

    public ArrayList<Integer> cordinateX;
    public  ArrayList<Integer> cordinateY;
    Player player;
    public SalmonScoreCard(Player player){
        cordinateX = new ArrayList<>();
        cordinateY = new ArrayList<>();
        this.player=player;
    }

    @Override
    public int countScore() {
        return 0;
    }

    public void getIndexes(Tile[][] playerBoard) {
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.SALMON)){
                    cordinateX.add(columns);
                    cordinateY.add(rows);
                }
            }
        }
    }
    @Override
    public void explainCard() {
        System.out.println("This Scorecard is used to determine the points for the Wildlife type Fox.\n" +
                "There are 3 types of this card.");
    }
}


