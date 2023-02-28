import java.util.ArrayList;

public interface ScoreCard {
    int countScore(ArrayList<Integer> Xcords, ArrayList<Integer> Ycords);
    void explainCard();
    public void getIndexes(Tile[][] playerBoard);
}
