import java.util.ArrayList;

public class BearScoreCard_A extends BearScoreCard{

    public BearScoreCard_A(Player player) {
        super(player);
    }
    @Override
    public int countScore(ArrayList<Integer> Xcords, ArrayList<Integer> Ycords) {
        for(int i=0; i<arrayOfTokens.size(); i++){

        }
        return 0;
    }

    @Override
    public void explainCard() {
        System.out.println(" You have chosen Elk Scorecard A, Score points shown for each straight line of adjacent Elk, depending on length of the line. \n" +
                "Two lines of Elk may be adjacent to one another, however, each Elk may only count for a single line. Lines do not need to be horizontal.");
        System.out.println( "A solo ElK:             2 points\n" +
                "2 Elk line:             5 points\n" +
                "3 Elk line:             9 points\n" +
                "4 Elk line:             13 points");
    }
}
