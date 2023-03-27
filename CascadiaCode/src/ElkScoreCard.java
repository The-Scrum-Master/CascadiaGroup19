import java.util.ArrayList;

public abstract class ElkScoreCard implements ScoreCard{
    protected ArrayList<TokenForPoints> arrayOfTokens;
    //public ArrayList<Integer> cordinateX;
    //public  ArrayList<Integer> cordinateY;
    public ElkScoreCard(Player player){
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
                    if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.ELK)){
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
