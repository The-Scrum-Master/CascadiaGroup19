/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public class SalmonScoreCard_A extends SalmonScoreCard{
    public SalmonScoreCard_A (Player player) {
        super(player);
    }
    public void recursiveHorizontalLineCheck(TokenForPoints validSalmon){
        for(int j=0; j<arrayOfTokens.size(); j++){
            //making sure that the element we are looking at isn't the same one we are comparing it to
            if((validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() && validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()) || arrayOfTokens.get(j).getValid())
                continue;
            else{
                if(validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() &&
                        (validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()   ||
                                validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                                validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()-1))  { //looking for horizontal line

                    arrayOfTokens.get(j).setValid(true);
                    recursiveHorizontalLineCheck(arrayOfTokens.get(j));
                }
            }
        }
    }
    public void recursiveRunCheck(TokenForPoints validSalmon){
        for(int j=0; j<arrayOfTokens.size(); j++){
            //making sure that the element we are looking at isn't the same one we are comparing it to
            if((validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() && validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()) || arrayOfTokens.get(j).getValid())
                continue;
            else{
                if(validSalmon.getCordX()==arrayOfTokens.get(j).getCordX() &&
                        (validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()-1))  { //looking for vertical line

                    arrayOfTokens.get(j).setValid(true);
                    recursiveRunCheck(arrayOfTokens.get(j));
                }
            }
        }

        for(int j=0; j<arrayOfTokens.size(); j++){
            //System.out.println("iteration= i=" + i + "     j= "+j);
            if(arrayOfTokens.get(j).getAlreadyAccountedFor() || (validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() && validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()) || validSalmon.getNumberOfAdjacent()>2){
                continue;
            }
            else{
                if(validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()   ||
                        validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                        validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()-1)  { //looking for adjacent X cord

                    if(validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()   ||
                            validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                            validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()-1)  { //looking for adjacent Y cord

                        validSalmon.setNumberOfAdjacent(validSalmon.getNumberOfAdjacent()+1);
                        validSalmon.setValid(true);
                        recursiveRunCheck(validSalmon);
                        recursiveRunCheck(arrayOfTokens.get(j));
                        break;
                    }
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
                if(j!=i && arrayOfTokens.get(i).getNumberOfAdjacent()<3) { //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1)  { //looking for adjacent X cord

                        if(arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1)  { //looking for adjacent Y cord

                            arrayOfTokens.get(i).setNumberOfAdjacent(arrayOfTokens.get(i).getNumberOfAdjacent()+1);
                            arrayOfTokens.get(i).setValid(true);
                            recursiveRunCheck(arrayOfTokens.get(i));
                            recursiveRunCheck(arrayOfTokens.get(j));
                            break;
                        }
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
            totalPoints+=turnLengthOfSalmonsIntoPoints(length);
        }
        return totalPoints;
    }

    public int turnLengthOfSalmonsIntoPoints(int Salmons){
        if(Salmons==0) return 0;
        else if(Salmons==1) return 2;
        else if(Salmons==2) return 4;
        else if(Salmons==3) return 7;
        else if(Salmons==4) return 11;
        else if(Salmons==5) return 15;
        else if(Salmons==6) return 20;
        else if(Salmons>=7) return 26;
        else{
            throw new IllegalArgumentException("number of pairs can't be negative");
        }
    }


    public static void explainCard() {
        System.out.println("This is Salmon Scorecard A. Points are given for each run of salmon, depending on its length. \nA run is defined " +
                "as a group of adjacent salmon where each salmon is adjacent to no more than two other salmon.\n");
    }
}
