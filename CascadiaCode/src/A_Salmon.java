/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public class A_Salmon{
    private ArrayList<TokenForPoints> arrayOfTokens;
    private ArrayList<TokenForPoints> arrayOfPlaceholders;
    Player player;

    public A_Salmon(Player player){
        this.player=player;
    }

    public int recursiveRunCheck(TokenForPoints validSalmon, int length, boolean valid){
        ArrayList<TokenForPoints> adjacent = new ArrayList<>();
        for(int j=0; j<arrayOfTokens.size(); j++){
            //System.out.println("iteration= i=" + i + "     j= "+j);
            if(validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() && validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()) {
                //making sure that the element we are looking at isn't the same one we are comparing it to
                continue;
            }
            if(validSalmon.getCordX()==arrayOfTokens.get(j).getCordX() ||
                    validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                    validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()-1)  {

                if(validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()   ||
                        validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                        validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()-1)  {

                    adjacent.add(arrayOfTokens.get(j));
                }
            }
        }

        if(adjacent.size()>2  || !valid){
            validSalmon.setAlreadyAccountedFor(true);
            for(int j=0; j< adjacent.size();j++){
                if(!adjacent.get(j).getAlreadyAccountedFor()){
                    adjacent.get(j).setAlreadyAccountedFor(true);
                    recursiveRunCheck(adjacent.get(j), length, false);
                }
            }
            return 0;
        }
        //this part of the code above checks if there are more than 2 salmons around the one we are looking at, if so, returns 0 because it is not allowed
        //else, it will restart the process knowing that this specific salmon is not illegal and taking into account the alreadyAccountedSalmon for counting
        //the points, something that it did not do in the above algorithm

        if(validSalmon.getAlreadyAccountedFor()){
            return length;
        }
        validSalmon.setAlreadyAccountedFor(true);
        adjacent = new ArrayList<>();
        for(int j=0; j<arrayOfTokens.size(); j++){
            //System.out.println("iteration= i=" + i + "     j= "+j);
            if(arrayOfTokens.get(j).getAlreadyAccountedFor()){
                continue;
            }
            if(validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() && validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()) {
                //making sure that the element we are looking at isn't the same one we are comparing it to
                continue;
            }
            if(validSalmon.getCordX()==arrayOfTokens.get(j).getCordX() ||
                    validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                    validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()-1)  {

                if(validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()   ||
                        validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                        validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()-1)  {

                    adjacent.add(arrayOfTokens.get(j));
                }
            }
        }
        if(adjacent.isEmpty()){
            length++;
        }
        else{
            length++;
            if(!valid){
                length=0;
            }
            else{
                for(int j=0; j< adjacent.size();j++){
                    length=recursiveRunCheck(adjacent.get(j), length, valid);
                }
            }
        }
        if(!valid){
            length=0;
        }
        return length;
    }

    public int countScore() {
        int totalPoints=0;
        int length;
        boolean valid;
        //System.out.println("size=" + arrayOfTokens.size());
        for(int i=0; i<arrayOfTokens.size(); i++){
            length=0;
            valid=true;
            length=recursiveRunCheck(arrayOfTokens.get(i), length, valid);
            totalPoints+=turnLengthOfSalmonsIntoPoints(length);
        }
        return totalPoints;
    }

    public void getIndexesForTokens(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        arrayOfTokens = new ArrayList<>();
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(playerBoard[rows][columns].getTokenPlaced()){
                        if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.SALMON)){
                            arrayOfTokens.add(new TokenForPoints(columns, rows));
                        }
                    }
                }
            }
        }
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

    public void getIndexesOfPlaceholders(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        //get indexes of all places there are bear placeholders
        arrayOfPlaceholders = new ArrayList<>();
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(!playerBoard[rows][columns].getTokenPlaced()){
                        for(Wildlife i : playerBoard[rows][columns].getSlots()){
                            //loop through placeholders of the tile
                            if(i.equals(Wildlife.SALMON)){
                                arrayOfPlaceholders.add(new TokenForPoints(columns, rows));
                            }
                        }
                    }
                }
            }
        }
    }

    public void placeholdersScore(int turnTheGameIsAt) {

    }

    public void placeAnywhere(){
        if(arrayOfPlaceholders.size()==0){
            System.out.println("Don't want to place token");
        }
        else{
            boolean atLeastOneSingleColorTile=false;
            for(TokenForPoints i : arrayOfPlaceholders){
                if(checkForSingleTile(i.getCordX(), i.getCordY())){
                    i.setSingleColorTile(true);
                    atLeastOneSingleColorTile=true;
                }
            }
            if(atLeastOneSingleColorTile){
                for(TokenForPoints i : arrayOfPlaceholders){
                    if(i.getSingleColorTile()){
                        player.placeToken(i.getCordY(), i.getCordX());
                        System.out.println("Token placed at X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                        break;
                    }
                }
            }
            else{
                int randomPosition=Tile.randomNumberGenerator(arrayOfPlaceholders.size());
                player.placeToken(arrayOfPlaceholders.get(randomPosition).getCordY(), arrayOfPlaceholders.get(randomPosition).getCordX());
                System.out.println("Token placed at X: "+ arrayOfPlaceholders.get(randomPosition).getCordY()+ " and Y: "+ arrayOfPlaceholders.get(randomPosition).getCordX());
            }
        }
    }

    public boolean atLeastAPartner(TokenForPoints placeholder) {
        boolean result=false;
        for(int i=0; i<arrayOfPlaceholders.size(); i++){
            if(arrayOfPlaceholders.get(i).equals(placeholder)){
                continue;
            }
            if( ((arrayOfPlaceholders.get(i).getCordX()==placeholder.getCordX() ) && ( arrayOfPlaceholders.get(i).getCordY() == placeholder.getCordY() + 1 )) ||
                    ((arrayOfPlaceholders.get(i).getCordX()==placeholder.getCordX() ) && ( arrayOfPlaceholders.get(i).getCordY() == placeholder.getCordY() - 1 )) ||
                    ((arrayOfPlaceholders.get(i).getCordX()==placeholder.getCordX() + 1 ) && ( arrayOfPlaceholders.get(i).getCordY() == placeholder.getCordY() )) ||
                    ((arrayOfPlaceholders.get(i).getCordX()==placeholder.getCordX() - 1 ) && ( arrayOfPlaceholders.get(i).getCordY() == placeholder.getCordY() )) ){
                //looking for adjacent in cross

                result=true;
                break;
            }
        }
        return result;
    }

    public static void explainCard() {
        System.out.println("This is Salmon Scorecard A. Points are given for each run of salmon, depending on its length. \nA run is defined " +
                "as a group of adjacent salmon where each salmon is adjacent to no more than two other salmon.\n");
    }

    public boolean checkForSingleTile(int x, int y){
        if (player.getPlayerBoard()[y][x].getSelect()==1){
            return true;
        }
        else{
            return false;
        }
    }

    public void insertionSort(ArrayList<TokenForPoints> arrayList) {
        //we use insertion sort because the arraylist size is not going to be bigger than 10 most probably, and a max size of 20
        int n = arrayList.size();
        for (int i = 1; i < n; i++) {
            TokenForPoints temp=arrayList.get(i);
            int j = i - 1;
            while (j >= 0 && arrayList.get(j).getNumberOfAdjacent() < temp.getNumberOfAdjacent()) {
                arrayList.set(j+1, arrayList.get(j));
                j = j - 1;
            }
            arrayList.set(j+1, temp);
        }
    }
}
