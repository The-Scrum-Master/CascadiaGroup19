import java.util.ArrayList;

public abstract class BearScoreCard implements ScoreCard{
    protected ArrayList<TokenForPoints> arrayOfTokens;
    //public ArrayList<Integer> cordinateX;
    //public  ArrayList<Integer> cordinateY;
    public BearScoreCard(Player player){
        arrayOfTokens = new ArrayList<>();
        //cordinateX = new ArrayList<>();
        //cordinateY = new ArrayList<>();
    }

    @Override
    public int countScore(ArrayList<Integer> Xcords, ArrayList<Integer> Ycords) {
        return 0;
    }

    public void getIndexes(Tile[][] playerBoard) {
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.BEAR)){
                    arrayOfTokens.add(new TokenForPoints(columns, rows));
                    //cordinateX.add(columns);
                    //cordinateY.add(rows);
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
