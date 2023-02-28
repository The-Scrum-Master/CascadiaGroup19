import java.util.ArrayList;

public abstract class ElkScoreCard implements ScoreCard{
    public ArrayList<Integer> cordinateX;
    public  ArrayList<Integer> cordinateY;
    public ElkScoreCard(Player player){
        cordinateX = new ArrayList<>();
        cordinateY = new ArrayList<>();
    }

    @Override
    public int countScore(ArrayList<Integer> Xcords, ArrayList<Integer> Ycords) {
        return 0;
    }

    public void getIndexes(Tile[][] playerBoard) {
        for(int rows = 0; rows < 20; rows++ ){
            for(int columns =0; columns < 20; columns++){
                if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.ELK)){
                   cordinateX.add(columns);
                   cordinateY.add(rows);
                }
            }
        }
    }
    @Override
    public void explainCard() {
        System.out.println("This Scorecard is used to determine the points for the Wildlife type Elk.\n" +
                "There are 3 types of this card.");
    }
}
