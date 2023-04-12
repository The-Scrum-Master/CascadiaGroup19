/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public class A_Bear{
    private ArrayList<TokenForPoints> arrayOfTokens;
    private ArrayList<TokenForPoints> arrayOfPlaceholders;
    Player player;

    public A_Bear(Player player){
        this.player=player;
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

    public void getIndexesForTokens(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        arrayOfTokens = new ArrayList<>();
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(playerBoard[rows][columns].getTokenPlaced()){
                        if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.BEAR)){
                            arrayOfTokens.add(new TokenForPoints(columns, rows));
                        }
                    }
                }
            }
        }
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
                System.out.println("\n");
                System.out.println("number of adjacent bears: "+numberOfAdjacentBears);
                System.out.println("Found adjacent bear: "+foundAdjacentBear);
                System.out.println("\n");
                if(foundAdjacentBear && numberOfAdjacentBears<2){
                    arrayOfPlaceholders.get(i).setValid(true);
                    System.out.println("Inside the if to add the bear to the arraylist");
                }
            }

            System.out.println("all the valid bears: ");
            for( TokenForPoints i : arrayOfPlaceholders){
                if(i.getValid()){
                    System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                }
            }

            System.out.println("The best position/s to obtain the greatest amount of points are:");
            boolean atLeastOneSingleColorTile=false;
            ArrayList<TokenForPoints> finalDraft = new ArrayList<>();
            if(arrayOfPlaceholders.size()==0){
                System.out.println("Place anywhere function V2");
                placeAnywhereLateGame(turnTheGameIsAt);
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

                System.out.println("the not allowed arraylist: ");
                for( TokenForPoints i : notAllowed){
                    System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                }

                ArrayList<TokenForPoints> nonPairedButAllowed=new ArrayList<>();
                if(notAllowed.isEmpty()){
                    nonPairedButAllowed=arrayOfPlaceholders;
                }
                else{
                    for (int i=0; i<arrayOfPlaceholders.size(); i++){
                        for (int j=0; j<notAllowed.size(); j++){
                            if(arrayOfPlaceholders.get(i).getCordX()==notAllowed.get(j).getCordX() && arrayOfPlaceholders.get(i).getCordY()==notAllowed.get(j).getCordY()){
                                continue;
                            }
                            else{
                                nonPairedButAllowed.add(arrayOfPlaceholders.get(i));
                            }
                        }
                    }
                }

                System.out.println("the nonPairedButAllowed arraylist: ");
                for( TokenForPoints i : nonPairedButAllowed){
                    System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
                }

                if(nonPairedButAllowed.size() == 0){
                    System.out.println("I have decided not to place the token V2");
                }
                else if(nonPairedButAllowed.size() == 1){
                    player.placeToken(nonPairedButAllowed.get(0).getCordY(), nonPairedButAllowed.get(0).getCordX());
                    //place in the one position
                }
                else{
                    int randomPosition=Tile.randomNumberGenerator(nonPairedButAllowed.size());
                    player.placeToken(nonPairedButAllowed.get(randomPosition).getCordY(), nonPairedButAllowed.get(randomPosition).getCordX());
                    //randomise position and place
                }
                /*
                int decision=Tile.randomNumberGenerator(2);
                if(decision==0){
                    System.out.println("I have decided not to place the token");
                }
                else{
                    //podria ir aqui el codigo este
                }

                 */
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
                            if(i.equals(Wildlife.BEAR)){
                                arrayOfPlaceholders.add(new TokenForPoints(columns, rows));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void explainBearCard() {
        System.out.println("This is Bear Scorecard A. Points are given for total number of pairs of bears.\nA pair of bears is exactly two bears adjacent to each other.\n");
    }

    public void increaseNumberOfBearsPairs(){
        player.setNumberOfBearPairs(player.getNumberOfBearPairs()+1);
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