/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

public class BearScoreCard_A extends BearScoreCard{
    int numberOfPairs=0;
    public BearScoreCard_A(Player player) {
        super(player);
    }
    @Override
    public int countScore() {
        for(int i=0; i<arrayOfTokens.size(); i++){
            if(arrayOfTokens.get(i).getValid()){
                continue;
            }

            /*for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfTokens.get(j).getValid()){
                    arrayOfTokens.remove(j);
                    j--;
                }
            }
             */
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
        return turnNumberOfPairsIntoPoints(numberOfPairs);
    }

    public int turnNumberOfPairsIntoPoints(int pairs){
        if(pairs==0) return 0;
        else if(pairs==1) return 4;
        else if(pairs==2) return 11;
        else if(pairs==3) return 19;
        else if(pairs>=4) return 27;
        else{
            throw new IllegalArgumentException("number of pairs can't be negative");
        }
    }


    public static void explainCard() {
        System.out.println("This is Salmon Scorecard A. Points are given for total number of pairs of bears.\nA pair of bears is exactly two bears adjacent to each other.\n");
    }
}
