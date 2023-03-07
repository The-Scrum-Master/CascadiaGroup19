import java.util.ArrayList;

public class BearScoreCard_A extends BearScoreCard{
    int numberOfPairs=0;

    public BearScoreCard_A(Player player) {
        super(player);
    }
    @Override
    public int countScore(ArrayList<Integer> Xcords, ArrayList<Integer> Ycords) {
        for(int i=0; i<arrayOfTokens.size(); i++){
            if(arrayOfTokens.get(i).getValid()){
                continue;
            }
            for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfTokens.get(j).getValid()){
                    continue;
                }
                if(j!=i) { //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1)  { //looking for adjacent X cord

                        if(arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1)  { //looking for adjacent Y cord

                            arrayOfTokens.get(j).setValid(true);
                            arrayOfTokens.get(i).setValid(true);
                            numberOfPairs++;
                            break;
                        }
                    }
                }
            }
        }
        return numberOfPairs;
    }

    public int turnNumberOfPairsIntoPoints(){
        if(numberOfPairs==0) return 0;
        else if(numberOfPairs==1) return 4;
        else if(numberOfPairs==2) return 11;
        else if(numberOfPairs==3) return 19;
        else if(numberOfPairs>=4) return 27;
        else{
            throw new IllegalArgumentException("number of pairs cant be negative");
        }
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
