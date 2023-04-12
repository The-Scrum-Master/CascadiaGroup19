/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public class A_Hawk{
    private ArrayList<TokenForPoints> arrayOfTokens;
    private ArrayList<TokenForPoints> arrayOfPlaceholders;
    Player player;

    public A_Hawk(Player player){
        this.player=player;
    }

    public int countScore() {
        int numberOfHawks=0;
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

    public void getIndexesForTokens(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        arrayOfTokens = new ArrayList<>();
        //maybe the line above is not necessary
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(playerBoard[rows][columns].getTokenPlaced()){
                        if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.HAWK)){
                            arrayOfTokens.add(new TokenForPoints(columns, rows));
                        }
                    }
                }
            }
        }
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

    public void placeholdersScore() {
        //System.out.println(arrayOfPlaceholders.size());
        //System.out.println(arrayOfTokens.size());

        for(int i=0; i<arrayOfPlaceholders.size(); i++){
            boolean foundAdjacentHawk=false;
            for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                        arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                        arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1) { //looking for adjacent X cord

                    if (arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() ||
                            arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() + 1 ||
                            arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() - 1) {
                        //looking for adjacent Y cord
                        foundAdjacentHawk = true;
                        break;
                    }
                }
            }
            if(!foundAdjacentHawk){
                arrayOfPlaceholders.get(i).setValid(true);
            }
        }
        System.out.println("The best position/s which award the greatest amount of points are:");
        boolean atLeastOneSingleColorTile=false;
        ArrayList<TokenForPoints> finalDraft = new ArrayList<>();
        if(arrayOfPlaceholders.size()==0){
            System.out.println("Don't want to place token");
        }
        else{
            for(TokenForPoints i : arrayOfPlaceholders){
                if(checkForSingleTile(i.getCordX(), i.getCordY()) && i.getValid()){
                    i.setSingleColorTile(true);
                    atLeastOneSingleColorTile=true;
                }
            }
            if(atLeastOneSingleColorTile){
                System.out.println("At least one good single color tile");
                for(TokenForPoints i : arrayOfPlaceholders){
                    if(i.getValid() && i.getSingleColorTile()){
                        finalDraft.add(i);
                        System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                    }
                }
            }
            else{
                System.out.println("Not even one good single color tile");
                for(TokenForPoints i : arrayOfPlaceholders){
                    if(i.getValid()){
                        finalDraft.add(i);
                        System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                    }
                }
            }
        }
        if(finalDraft.size() == 0){
            System.out.println("I have decided not to place the token");
            //dont place
        } else if(finalDraft.size() == 1){
            player.placeToken(finalDraft.get(0).getCordY(), finalDraft.get(0).getCordX());
            increaseNumberOfHawks();
            //place in the one position
        }
        else{
            int randomPosition=Tile.randomNumberGenerator(finalDraft.size());
            player.placeToken(finalDraft.get(randomPosition).getCordY(), finalDraft.get(randomPosition).getCordX());
            increaseNumberOfHawks();
            //randomise position and place
        }
    }

    public void getIndexesOfPlaceholders(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        //get indexes of all places there are hawk placeholders
        arrayOfPlaceholders = new ArrayList<>();
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(!playerBoard[rows][columns].getTokenPlaced()){
                        for(Wildlife i : playerBoard[rows][columns].getSlots()){
                            //loop through placeholders of the tile
                            if(i.equals(Wildlife.HAWK)){
                                arrayOfPlaceholders.add(new TokenForPoints(columns, rows));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void explainHawkCard() {
        System.out.println("This is Hawk Scorecard A. Points are given for total number of hawks that are not adjacent to any other hawk.\n");
    }

    public void increaseNumberOfHawks(){
        player.setNumberOfHawks(player.getNumberOfHawks()+1);
    }

    public boolean checkForSingleTile(int x, int y){
        if (player.getPlayerBoard()[y][x].getSelect()==1){
            return true;
        }
        else{
            return false;
        }
    }
}
