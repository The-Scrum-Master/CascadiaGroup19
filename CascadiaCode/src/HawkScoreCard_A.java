/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

public class HawkScoreCard_A extends HawkScoreCard{
    int numberOfHawks=0;

    public HawkScoreCard_A (Player player) {
        super(player);
    }
    @Override
    public int countScore() {
        for(int i=0; i<arrayOfTokens.size(); i++){
            boolean foundAdjacentHawk=false;
            for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfTokens.get(j).getValid()){
                    continue;
                }
                if(j!=i) { 
                    //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1)  
                            { //looking for adjacent X cord

                        if(arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1)  { 
                                    //looking for adjacent Y cord
                            foundAdjacentHawk=true;
                            break;
                        }
                    }
                }
            }
            if(!foundAdjacentHawk){
                arrayOfTokens.get(i).setValid(true);
                numberOfHawks++;
            }
            //System.out.println(numberOfHawks);
        }
        return turnNumberOfHawksIntoPoints(numberOfHawks);
    }

    public int turnNumberOfHawksIntoPoints(int hawks){
        if(hawks==0) return 0;
        else if(hawks==1) return 2;
        else if(hawks==2) return 5;
        else if(hawks==3) return 8;
        else if(hawks==4) return 11;
        else if(hawks==5) return 14;
        else if(hawks==6) return 18;
        else if(hawks==7) return 22;
        else if(hawks>=8) return 26;
        else{
            throw new IllegalArgumentException("number of pairs can't be negative");
        }
    }


    public static void explainCard() {
        System.out.println("This is Hawk Scorecard A. Points are given for total number of hawks that are not adjacent to any other hawk.\n");
    }
}
