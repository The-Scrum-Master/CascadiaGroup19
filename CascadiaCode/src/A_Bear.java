/* Group 19
 * Group name: Front row
 * Timi Salam- 21392803(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public class A_Bear{
    public ArrayList<TokenForPoints> arrayOfTokens;
    private ArrayList<TokenForPoints> arrayOfPlaceholders;
    Player player;

    public A_Bear(Player player){
        this.player=player;
    }

    public void strategy1(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        getIndexes(playerBoard, playerMapGenerator);
        checkMapPositionAndPlaceToken();
    }

    public void strategy2(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        getIndexes(playerBoard, playerMapGenerator);
        placeAnywhere();
    }

    public int strategy3(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        getIndexes(playerBoard, playerMapGenerator);
        return checkPointsFromRiver();
    }

    public void getIndexes(Tile[][] playerBoard, MapGenerator playerMapGenerator){
        arrayOfPlaceholders=A_CommonTokenFunctions.getIndexesOfPlaceholders(playerBoard, playerMapGenerator, Wildlife.BEAR);
        arrayOfTokens=A_CommonTokenFunctions.getIndexesOfTokens(playerBoard, playerMapGenerator, Wildlife.BEAR);
        checkForPairs();
        //get indexes of tokens and placeholders
    }

    public void initialiseArrayOfTokens(ArrayList<TokenForPoints> array){
        arrayOfTokens=array;
    }

    public void checkForPairs(){
        for(int i=0; i<arrayOfTokens.size(); i++){
            if(arrayOfTokens.get(i).getAlreadyPairedUp()){
                continue;
            }
            for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfTokens.get(j).getAlreadyPairedUp()){
                    continue;
                }
                if(j!=i) {
                    //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1)  {
                        //looking for adjacent X cord

                        if(arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1)  {
                            //looking for adjacent Y cord

                            arrayOfTokens.get(j).setAlreadyPairedUp(true);
                            arrayOfTokens.get(i).setAlreadyPairedUp(true);
                            break;
                        }
                    }
                }
            }
        }
    }

    public int countScore() {
        int numberOfPairs=0;
        for(int i=0; i<arrayOfTokens.size(); i++){
            if(arrayOfTokens.get(i).getValid()){
                continue;
            }
            for(int j=0; j<arrayOfTokens.size(); j++){
                if(arrayOfTokens.get(j).getValid()){
                    continue;
                }
                if(j!=i) {
                    //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfTokens.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1)  {
                        //looking for adjacent X cord

                        if(arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()   ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()+1 ||
                                arrayOfTokens.get(i).getCordY()==arrayOfTokens.get(j).getCordY()-1)  {
                            //looking for adjacent Y cord

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

    public void checkMapPositionAndPlaceToken() {
        if(arrayOfTokens.isEmpty()){
            placeAnywhere();
        }
        else{
            for(int i=0; i<arrayOfPlaceholders.size(); i++){
                boolean foundAdjacentBear=false;
                int numberOfAdjacentBears=0;
                for(int j=0; j<arrayOfTokens.size(); j++){
                    if(arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1) {
                        //looking for adjacent X cord

                        if (arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() ||
                                arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() + 1 ||
                                arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() - 1) {
                            //looking for adjacent Y cord

                            numberOfAdjacentBears++;
                            if(!arrayOfTokens.get(j).getAlreadyPairedUp()){
                                foundAdjacentBear = true;
                            }
                        }
                    }
                }
                if(foundAdjacentBear && numberOfAdjacentBears<2){
                    arrayOfPlaceholders.get(i).setValid(true);
                }
            }

            boolean atLeastOneSingleColorTile=false;
            ArrayList<TokenForPoints> finalDraft = new ArrayList<>();
            if(arrayOfPlaceholders.size()==0){
                placeAnywhere();
            }
            else{
                for(TokenForPoints i : arrayOfPlaceholders){
                    if(checkForSingleTile(i.getCordX(), i.getCordY()) && i.getValid()){
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
                        if(i.getValid()){
                            finalDraft.add(i);
                        }
                    }
                }
            }
            if(finalDraft.size() == 0){
                ArrayList<TokenForPoints> notAllowed = new ArrayList<>();
                for(int i=0; i<arrayOfPlaceholders.size(); i++){
                    for(int j=0; j<arrayOfTokens.size(); j++){
                        if(arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                                arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                                arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1) {
                            //looking for adjacent X cord

                            if (arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() ||
                                    arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() + 1 ||
                                    arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() - 1) {
                                //looking for adjacent Y cord

                                if(arrayOfTokens.get(j).getAlreadyPairedUp()){
                                    notAllowed.add(arrayOfPlaceholders.get(i));
                                }
                            }
                        }
                    }
                }

                ArrayList<TokenForPoints> nonPairedButAllowed=new ArrayList<>();
                if(notAllowed.isEmpty()){
                    nonPairedButAllowed=arrayOfPlaceholders;
                }
                else{
                    for (int i=0; i<arrayOfPlaceholders.size(); i++){
                        boolean goodToAdd=true;
                        for (int j=0; j<notAllowed.size(); j++){
                            if(arrayOfPlaceholders.get(i).getCordX()==notAllowed.get(j).getCordX() && arrayOfPlaceholders.get(i).getCordY()==notAllowed.get(j).getCordY()){
                                goodToAdd=false;
                                break;
                            }
                        }
                        if(goodToAdd){
                            nonPairedButAllowed.add(arrayOfPlaceholders.get(i));
                        }
                    }
                }

                if(nonPairedButAllowed.size() == 0){
                    System.out.println("I have decided not to place the token ");
                }
                else if(nonPairedButAllowed.size() == 1){
                    player.placeToken(nonPairedButAllowed.get(0).getCordY(), nonPairedButAllowed.get(0).getCordX());
                    System.out.println("Token placed at "+nonPairedButAllowed.get(0).getCordY()+","+nonPairedButAllowed.get(0).getCordX());
                    //place in the one position
                }
                else{
                    int randomPosition=Tile.randomNumberGenerator(nonPairedButAllowed.size());
                    player.placeToken(nonPairedButAllowed.get(randomPosition).getCordY(), nonPairedButAllowed.get(randomPosition).getCordX());
                    System.out.println("Token placed at "+nonPairedButAllowed.get(randomPosition).getCordY()+","+nonPairedButAllowed.get(randomPosition).getCordX());
                    //randomise position and place
                }
            } else if(finalDraft.size() == 1){
                player.placeToken(finalDraft.get(0).getCordY(), finalDraft.get(0).getCordX());
                increaseNumberOfBearsPairs();
                //place in the one position
            }
            else{
                int randomPosition=Tile.randomNumberGenerator(finalDraft.size());
                player.placeToken(finalDraft.get(randomPosition).getCordY(), finalDraft.get(randomPosition).getCordX());
                increaseNumberOfBearsPairs();
                //randomise position and place
            }
        }
    }

    public int checkPointsFromRiver() {
        if(arrayOfPlaceholders.isEmpty()){
            return 0;
        }
        if(arrayOfTokens.isEmpty()){
            return 0;
        }
        else{
            for(int i=0; i<arrayOfPlaceholders.size(); i++){
                boolean foundAdjacentBear=false;
                int numberOfAdjacentBears=0;
                for(int j=0; j<arrayOfTokens.size(); j++){
                    if(arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()   ||
                            arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()+1 ||
                            arrayOfPlaceholders.get(i).getCordX()==arrayOfTokens.get(j).getCordX()-1) {
                        //looking for adjacent X cord

                        if (arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() ||
                                arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() + 1 ||
                                arrayOfPlaceholders.get(i).getCordY() == arrayOfTokens.get(j).getCordY() - 1) {
                            //looking for adjacent Y cord

                            numberOfAdjacentBears++;
                            if(!arrayOfTokens.get(j).getAlreadyPairedUp()){
                                foundAdjacentBear = true;
                            }
                        }
                    }
                }
                if(foundAdjacentBear && numberOfAdjacentBears<2){
                    arrayOfPlaceholders.get(i).setValid(true);
                }
            }

            ArrayList<TokenForPoints> finalDraft = new ArrayList<>();
            for(TokenForPoints i : arrayOfPlaceholders){
                if(i.getValid()){
                    finalDraft.add(i);
                }
            }
            if(finalDraft.size() == 0){
                return 0;
            } else {
                return turnNumberOfPairsIntoPoints((player.getNumberOfBearPairs()+1)) - turnNumberOfPairsIntoPoints((player.getNumberOfBearPairs()));
            }
        }
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

    public static void explainBearCard() {
        System.out.println("\tThis is Bear Scorecard A. Points are given for total number of pairs of bears.\n\tA pair of bears " +
                "is exactly two bears adjacent to each other.\n");
    }

    public void increaseNumberOfBearsPairs(){
        player.setNumberOfBearPairs(player.getNumberOfBearPairs()+1);
    }

    public boolean checkForSingleTile(int x, int y){
        return player.getPlayerBoard()[y][x].getSelect() == 1;
    }

    public boolean areThereAnyPlaceholders(){
        return !arrayOfPlaceholders.isEmpty();
    }
}