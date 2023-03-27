/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public class ElkScoreCard_A extends ElkScoreCard{

    public ElkScoreCard_A (Player player) {
        super(player);
    }

    public void recursiveHorizontalLineCheck(TokenForPoints validElk){
        for(int j=0; j<arrayOfTokens.size(); j++){
            //making sure that the element we are looking at isn't the same one we are comparing it to
            if((validElk.getCordY()==arrayOfTokens.get(j).getCordY() && validElk.getCordX()==arrayOfTokens.get(j).getCordX()) || arrayOfTokens.get(j).getValid())
                continue;
            else{
                if(validElk.getCordY()==arrayOfTokens.get(j).getCordY() &&
                        (validElk.getCordX()==arrayOfTokens.get(j).getCordX()   ||
                                validElk.getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                                validElk.getCordX()==arrayOfTokens.get(j).getCordX()-1))  { //looking for horizontal line

                    arrayOfTokens.get(j).setValid(true);
                    recursiveHorizontalLineCheck(arrayOfTokens.get(j));
                }
            }
        }
    }

    public void recursiveVerticalLineCheck(TokenForPoints validElk){
        for(int j=0; j<arrayOfTokens.size(); j++){
            //making sure that the element we are looking at isn't the same one we are comparing it to
            if((validElk.getCordY()==arrayOfTokens.get(j).getCordY() && validElk.getCordX()==arrayOfTokens.get(j).getCordX()) || arrayOfTokens.get(j).getValid())
                continue;
            else{
                if(validElk.getCordX()==arrayOfTokens.get(j).getCordX() &&
                        (validElk.getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                validElk.getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                validElk.getCordY()==arrayOfTokens.get(j).getCordY()-1))  { //looking for vertical line

                    arrayOfTokens.get(j).setValid(true);
                    recursiveVerticalLineCheck(arrayOfTokens.get(j));
                }
            }
        }
    }

    @Override
    public int countScore() {
        int totalPoints=0;
        //System.out.println("size=" + arrayOfTokens.size());
        for(int i=0; i<arrayOfTokens.size(); i++){
            int length=0;
            if(arrayOfTokens.get(i).getAlreadyAccountedFor()){
                continue;
            }
            arrayOfTokens.get(i).setValid(true);
            for(int j=0; j<arrayOfTokens.size(); j++){
                //System.out.println("iteration= i=" + i + "     j= "+j);
                if(arrayOfTokens.get(j).getAlreadyAccountedFor()){
                    continue;
                }
                if(j!=i) { //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY() &&
                            (arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1))  { //looking for horizontal line

                        arrayOfTokens.get(j).setValid(true);
                        recursiveHorizontalLineCheck(arrayOfTokens.get(i));
                        recursiveHorizontalLineCheck(arrayOfTokens.get(j));
                        break;
                    }
                    else if(arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX() &&
                            (arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                    arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                    arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1))  { //looking for vertical line

                        arrayOfTokens.get(j).setValid(true);
                        recursiveVerticalLineCheck(arrayOfTokens.get(i));
                        recursiveVerticalLineCheck(arrayOfTokens.get(j));
                        break;
                    }
                }
            }
            //System.out.println("length="+length);
            for(int k=0; k<arrayOfTokens.size(); k++){
                if(arrayOfTokens.get(k).getValid()){
                    length++;
                    arrayOfTokens.get(k).setAlreadyAccountedFor(true);
                    arrayOfTokens.get(k).setValid(false);
                }
            }
            totalPoints+=turnLengthOfElksIntoPoints(length);
        }
        return totalPoints;
    }

    public int turnLengthOfElksIntoPoints(int elks){
        if(elks==0) return 0;
        else if(elks==1) return 2;
        else if(elks==2) return 5;
        else if(elks==3) return 9;
        else if(elks>=4) return 13;
        else{
            throw new IllegalArgumentException("number of pairs can't be negative");
        }
    }



    public static void explainCard() {
        System.out.println("This is Elk Scorecard A. Score points are given for each straight line of adjacent elk, depending on length of the line. \nLines don't necessarily have to be horizontal.\n");
    }
}
