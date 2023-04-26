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

    public void recursiveRunCheck(TokenForPoints validSalmon){
        for(int j=0; j<arrayOfTokens.size(); j++){
            //making sure that the element we are looking at isn't the same one we are comparing it to
            if((validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() && validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()) || arrayOfTokens.get(j).getValid())
                continue;
            else{
                if(validSalmon.getCordX()==arrayOfTokens.get(j).getCordX() &&
                        (validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                validSalmon.getCordY()==arrayOfTokens.get(j).getCordY()-1))  {
                    //looking for vertical line

                    arrayOfTokens.get(j).setValid(true);
                    recursiveRunCheck(arrayOfTokens.get(j));
                }
                else if(validSalmon.getCordY()==arrayOfTokens.get(j).getCordY() &&
                        (validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()   ||
                                validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                                validSalmon.getCordX()==arrayOfTokens.get(j).getCordX()-1))  {
                    //looking for vertical line

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
                if(j!=i && arrayOfTokens.get(i).getNumberOfAdjacent()<3) {
                    //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1)  { //looking for adjacent X cord

                        if(arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1)  { //looking for adjacent Y cord

                            arrayOfTokens.get(i).setNumberOfAdjacent(arrayOfTokens.get(i).getNumberOfAdjacent()+1);
                            arrayOfTokens.get(i).setValid(true); //maybe problem here
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

    public void placeholdersScore(int turnTheGameIsAt) {
        //System.out.println(arrayOfPlaceholders.size());
        //System.out.println(arrayOfTokens.size());
        System.out.println("Array of tokens size: "+arrayOfTokens.size());
        System.out.println("Array of placeholders size: "+arrayOfPlaceholders.size());

        if(arrayOfTokens.isEmpty()){
            System.out.println("Place anywhere function");
            placeAnywhereLateGame(turnTheGameIsAt);
        }
        else{
            for(int i=0; i<arrayOfPlaceholders.size(); i++){
                for(int j=0; j<arrayOfTokens.size(); j++){
                    if(     ((arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX() ) && ( arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() + 1 )) ||
                            ((arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX() ) && ( arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() - 1 )) ||
                            ((arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX() + 1 ) && ( arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() )) ||
                            ((arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX() - 1 ) && ( arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() )) ){
                        //looking for adjacent in cross

                        arrayOfPlaceholders.get(i).setValid(true);
                        arrayOfPlaceholders.get(i).setNumberOfAdjacent(arrayOfPlaceholders.get(i).getNumberOfAdjacent()+1);
                    }
                }
            }

            System.out.println("all the valid elks: ");
            for( TokenForPoints i : arrayOfPlaceholders){
                if(i.getValid()){
                    System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                }
            }

            System.out.println("The best position/s to obtain the greatest amount of points are:");
            insertionSort(arrayOfPlaceholders);

            boolean atLeastOneSingleColorTile=false;
            ArrayList<TokenForPoints> finalDraft = new ArrayList<>();
            if(arrayOfPlaceholders.size()==0){
                System.out.println("Place anywhere function V2");
                placeAnywhereLateGame(turnTheGameIsAt);
            }
            else{
                int max=arrayOfPlaceholders.get(0).getNumberOfAdjacent();
                for(TokenForPoints i : arrayOfPlaceholders){
                    if(checkForSingleTile(i.getCordX(), i.getCordY()) && i.getValid() && i.getNumberOfAdjacent()==max){
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
                        if(i.getValid() && i.getNumberOfAdjacent()==max){
                            finalDraft.add(i);
                            System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                        }
                    }
                }
            }
            if(finalDraft.size() == 0){
                if(!arrayOfPlaceholders.isEmpty()){
                    boolean havePlacedToken=false;
                    for(int i=0; i<arrayOfPlaceholders.size(); i++){
                        if(atLeastAPartner(arrayOfPlaceholders.get(i))){
                            System.out.println("X: "+ arrayOfPlaceholders.get(i).getCordY()+ " and Y: "+ arrayOfPlaceholders.get(i).getCordX() + " V2");
                            player.placeToken(arrayOfPlaceholders.get(i).getCordY(), arrayOfPlaceholders.get(i).getCordX());
                            havePlacedToken=true;
                            break;
                        }
                    }
                    if(!havePlacedToken){
                        System.out.println("Don't want to place token");
                    }
                }
                else{
                    System.out.println("Don't want to place token");
                }
                //dont place
            } else if(finalDraft.size() == 1){
                player.placeToken(finalDraft.get(0).getCordY(), finalDraft.get(0).getCordX());
                //place in the one position
            }
            else{
                int randomPosition=Tile.randomNumberGenerator(finalDraft.size());
                player.placeToken(finalDraft.get(randomPosition).getCordY(), finalDraft.get(randomPosition).getCordX());
                //randomise position and place
            }
        }
    }

    public void placeAnywhereLateGame(int turnTheGameIsAt){
        if(arrayOfPlaceholders.size()==0){
            System.out.println("Don't want to place token");
        }
        else{
            boolean atLeastOneSingleColorTile=false;
            for(TokenForPoints i : arrayOfPlaceholders){
                if(checkForSingleTile(i.getCordX(), i.getCordY()) && atLeastAPartner(i)){
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
                boolean havePlacedToken=false;
                ArrayList<TokenForPoints> arrayListToSelectRandomlyFrom = new ArrayList<TokenForPoints>();

                for(int i=0; i<arrayOfPlaceholders.size(); i++){
                    if(atLeastAPartner(arrayOfPlaceholders.get(i))){
                        arrayListToSelectRandomlyFrom.add(arrayOfPlaceholders.get(i));
                        havePlacedToken=true;
                    }
                }
                if(!havePlacedToken){
                    boolean finalChanceForPlacing=false;
                    for(TokenForPoints i : arrayOfPlaceholders){
                        if(checkForSingleTile(i.getCordX(), i.getCordY())){
                            player.placeToken(i.getCordY(), i.getCordX());
                            System.out.println("Token placed at X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                            finalChanceForPlacing=true;
                            break;
                        }
                    }
                    if(!finalChanceForPlacing){
                        if(turnTheGameIsAt<7){
                            placeAnywhereEarlyGame();
                        }
                        else{
                            System.out.println("Don't want to place token");
                        }
                    }
                }
                else{
                    int randomPosition=Tile.randomNumberGenerator(arrayListToSelectRandomlyFrom.size());
                    player.placeToken(arrayListToSelectRandomlyFrom.get(randomPosition).getCordY(), arrayListToSelectRandomlyFrom.get(randomPosition).getCordX());
                    System.out.println("Token placed at X: "+ arrayListToSelectRandomlyFrom.get(randomPosition).getCordY()+ " and Y: "+ arrayListToSelectRandomlyFrom.get(randomPosition).getCordX());
                }
            }
        }
    }

    public void placeAnywhereEarlyGame(){
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
