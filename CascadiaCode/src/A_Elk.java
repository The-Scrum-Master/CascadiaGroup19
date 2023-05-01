/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public class A_Elk{
    private ArrayList<TokenForPoints> arrayOfTokens;
    private ArrayList<TokenForPoints> arrayOfPlaceholders;
    Player player;

    public A_Elk(Player player){
        this.player=player;
    }

    public void strategy1(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        getIndexes(playerBoard, playerMapGenerator);
        placeholdersScore();
    }

    public void strategy2(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        getIndexes(playerBoard, playerMapGenerator);
        placeAnywhere();
    }

    public int strategy3(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        getIndexes(playerBoard, playerMapGenerator);
        return checkPointsFromRiver(playerBoard, playerMapGenerator);
    }

    public void getIndexes(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        getIndexesOfPlaceholders(playerBoard, playerMapGenerator);
        getIndexesForTokens(playerBoard, playerMapGenerator);
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
                            if(i.equals(Wildlife.ELK)){
                                arrayOfPlaceholders.add(new TokenForPoints(columns, rows));
                            }
                        }
                    }
                }
            }
        }
    }

    public void getIndexesForTokens(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        arrayOfTokens = new ArrayList<>();
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(playerBoard[rows][columns].getTokenPlaced()){
                        if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.ELK)){
                            arrayOfTokens.add(new TokenForPoints(columns, rows));
                        }
                    }
                }
            }
        }
    }

    public void recursiveHorizontalLineCheck(TokenForPoints validElk){
        for(int j=0; j<arrayOfTokens.size(); j++){
            //making sure that the element we are looking at isn't the same one we are comparing it to
            if((validElk.getCordY()==arrayOfTokens.get(j).getCordY() && validElk.getCordX()==arrayOfTokens.get(j).getCordX()) || arrayOfTokens.get(j).getValid() || arrayOfTokens.get(j).getAlreadyAccountedFor())
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
            if((validElk.getCordY()==arrayOfTokens.get(j).getCordY() && validElk.getCordX()==arrayOfTokens.get(j).getCordX()) || arrayOfTokens.get(j).getValid() || arrayOfTokens.get(j).getAlreadyAccountedFor())
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

    public int countScore() {
        int totalPoints=0;
        for(int i=0; i<arrayOfTokens.size(); i++){
            int length=0;
            boolean lookingAtHorizontal=false;
            boolean lookingAtVertical=false;
            if(arrayOfTokens.get(i).getAlreadyAccountedFor()){
                continue;
            }
            arrayOfTokens.get(i).setValid(true);
            for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfTokens.get(j).getAlreadyAccountedFor()){
                    continue;
                }
                if(j!=i) { //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(!lookingAtVertical && arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY() &&
                            (arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                                    arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                                    arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1))  { //looking for horizontal line

                        lookingAtHorizontal=true;
                        arrayOfTokens.get(j).setValid(true);
                        recursiveHorizontalLineCheck(arrayOfTokens.get(i));
                        recursiveHorizontalLineCheck(arrayOfTokens.get(j));
                        break;
                    }
                    else if(!lookingAtHorizontal && arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX() &&
                            (arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                    arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                    arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1))  { //looking for vertical line

                        lookingAtVertical=true;
                        arrayOfTokens.get(j).setValid(true);
                        recursiveVerticalLineCheck(arrayOfTokens.get(i));
                        recursiveVerticalLineCheck(arrayOfTokens.get(j));
                        break;
                    }
                }
            }
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

    public void placeholdersScore() {
        if(arrayOfTokens.isEmpty()){
            placeAnywhere();
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

            insertionSort(arrayOfPlaceholders);
            boolean atLeastOneSingleColorTile=false;
            ArrayList<TokenForPoints> finalDraft = new ArrayList<>();
            if(arrayOfPlaceholders.size()==0){
                placeAnywhere();
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
                    for(TokenForPoints i : arrayOfPlaceholders){
                        if(i.getValid() && i.getSingleColorTile()){
                            finalDraft.add(i);
                        }
                    }
                }
                else{
                    for(TokenForPoints i : arrayOfPlaceholders){
                        if(i.getValid() && i.getNumberOfAdjacent()==max){
                            finalDraft.add(i);
                        }
                    }
                }
            }
            if(finalDraft.size() == 0){
                if(!arrayOfPlaceholders.isEmpty()){
                    boolean havePlacedToken=false;
                    for(int i=0; i<arrayOfPlaceholders.size(); i++){
                        if(atLeastAPartner(arrayOfPlaceholders.get(i))){
                            player.placeToken(arrayOfPlaceholders.get(i).getCordY(), arrayOfPlaceholders.get(i).getCordX());
                            System.out.println("Token placed at "+arrayOfPlaceholders.get(i).getCordY()+","+arrayOfPlaceholders.get(i).getCordX());
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
                System.out.println("Token placed at "+finalDraft.get(0).getCordY()+","+finalDraft.get(0).getCordX());

                //place in the one position
            }
            else{
                int randomPosition=Tile.randomNumberGenerator(finalDraft.size());
                player.placeToken(finalDraft.get(randomPosition).getCordY(), finalDraft.get(randomPosition).getCordX());
                System.out.println("Token placed at "+finalDraft.get(randomPosition).getCordY()+","+finalDraft.get(randomPosition).getCordX());
                //randomise position and place
            }
        }
    }

    public int checkPointsFromRiver(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        if(arrayOfPlaceholders.isEmpty()){
            return 0;
        }
        if(arrayOfTokens.isEmpty()){
            return 2;
            //points for 1 single elk
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

            insertionSort(arrayOfPlaceholders);
            ArrayList<TokenForPoints> finalDraft = new ArrayList<>();
            int max=arrayOfPlaceholders.get(0).getNumberOfAdjacent();
            for(TokenForPoints i : arrayOfPlaceholders){
                if(i.getValid() && i.getNumberOfAdjacent()==max){
                    finalDraft.add(i);
                }
            }
            if(finalDraft.size() == 0){
                if(!arrayOfPlaceholders.isEmpty()){
                    for(int i=0; i<arrayOfPlaceholders.size(); i++){
                        if(atLeastAPartner(arrayOfPlaceholders.get(i))){
                            getIndexes(playerBoard, playerMapGenerator);
                            int maxLengthRun = countLongestRun();
                            return turnLengthOfElksIntoPoints(maxLengthRun+1) - turnLengthOfElksIntoPoints(maxLengthRun);
                        }
                    }
                    return 0;
                }
                else{
                    return 0;
                }
            } else{
                getIndexes(playerBoard, playerMapGenerator);
                int maxLengthRun = countLongestRun();
                return turnLengthOfElksIntoPoints(maxLengthRun+1) - turnLengthOfElksIntoPoints(maxLengthRun);
            }
        }
    }

    public int countLongestRun() {
        int maxLength = -1;
        for(int i=0; i<arrayOfTokens.size(); i++){
            int length=0;
            boolean lookingAtHorizontal=false;
            boolean lookingAtVertical=false;
            if(arrayOfTokens.get(i).getAlreadyAccountedFor()){
                continue;
            }
            arrayOfTokens.get(i).setValid(true);
            for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfTokens.get(j).getAlreadyAccountedFor()){
                    continue;
                }
                if(j!=i) { //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(!lookingAtVertical && arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY() &&
                            (arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                                    arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                                    arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1))  { //looking for horizontal line

                        lookingAtHorizontal=true;
                        arrayOfTokens.get(j).setValid(true);
                        recursiveHorizontalLineCheck(arrayOfTokens.get(i));
                        recursiveHorizontalLineCheck(arrayOfTokens.get(j));
                        break;
                    }
                    else if(!lookingAtHorizontal && arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX() &&
                            (arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                    arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                    arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1))  { //looking for vertical line

                        lookingAtVertical=true;
                        arrayOfTokens.get(j).setValid(true);
                        recursiveVerticalLineCheck(arrayOfTokens.get(i));
                        recursiveVerticalLineCheck(arrayOfTokens.get(j));
                        break;
                    }
                }
            }
            for(int k=0; k<arrayOfTokens.size(); k++){
                if(arrayOfTokens.get(k).getValid()){
                    length++;
                    arrayOfTokens.get(k).setAlreadyAccountedFor(true);
                    arrayOfTokens.get(k).setValid(false);
                }
            }
            maxLength=Math.max(maxLength, length);
        }
        return maxLength;
    }

    public void placeAnywhere(){
        if(arrayOfPlaceholders.size()==0){
            System.out.println("Can't place token");
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
                        System.out.println("Token placed at "+ i.getCordY()+ ","+ i.getCordX());
                        break;
                    }
                }
            }
            else{
                int randomPosition=Tile.randomNumberGenerator(arrayOfPlaceholders.size());
                player.placeToken(arrayOfPlaceholders.get(randomPosition).getCordY(), arrayOfPlaceholders.get(randomPosition).getCordX());
                System.out.println("Token placed at "+ arrayOfPlaceholders.get(randomPosition).getCordY()+ ","+ arrayOfPlaceholders.get(randomPosition).getCordX());
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
        System.out.println("This is Elk Scorecard A. Score points are given for each straight line of adjacent elk, depending on length of the line. \nLines don't necessarily have to be horizontal.\n");
    }

    public boolean checkForSingleTile(int x, int y){
        return player.getPlayerBoard()[y][x].getSelect() == 1;
    }

    public static void insertionSort(ArrayList<TokenForPoints> arrayList) {
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

    public boolean areThereAnyPlaceholders(){
        return !arrayOfPlaceholders.isEmpty();
    }
}
