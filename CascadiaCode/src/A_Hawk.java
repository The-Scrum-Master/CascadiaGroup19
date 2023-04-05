/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;

public class A_Hawk{
    protected ArrayList<TokenForPoints> arrayOfTokens;
    protected ArrayList<TokenForPoints> arrayOfPlaceholders;
    int numberOfHawks=0;
    Player player;

    public A_Hawk(Player player){
        arrayOfTokens = new ArrayList<>();
        this.player=player;
    }

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

    public void getIndexesForTokens(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(playerBoard[rows][columns].getTokenPlaced()){
                        if(playerBoard[rows][columns].tokenPlayedType.equals(Wildlife.HAWK)){
                            arrayOfTokens.add(new TokenForPoints(columns, rows));
                            //cordinateX.add(columns);
                            //cordinateY.add(rows);
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
        System.out.println("The following positions award the max amount of points ("+ turnNumberOfHawksIntoPoints(player.getNumberOfHawks()+1)+")");
        for(TokenForPoints i : arrayOfPlaceholders){
            if(i.getValid()){
                System.out.println("X: "+ i.getCordY()+ " and Y: "+ i.getCordX());
            }
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
                                //cordinateX.add(columns);
                                //cordinateY.add(rows);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void explainCard() {
        System.out.println("This is Hawk Scorecard A. Points are given for total number of hawks that are not adjacent to any other hawk.\n");
    }
}


/*
* NEED TO ADD THAT WHENEVER A HAWK IS PLACED, NUMBEROFHAWKS IN PLAYER GOES UP
* ALSO NEED TO ADD THAT THESE HAWK FUNCTIONS ARE ONLY CALLED WHEN A HAWK TOKEN IS HELD
* NEED TO ADD FUNCTION THAT CHECKS IF ONE OF THE ELITE POSITION IS A SINGLE PLACEHOLDER, PLACE THERE
* NEED TO ADD FUNCTION THAT, IF NOT 1-PLACEHOLDER TILE, IF RANDOMLY SELECTS WHERE TO PLACE IT
* */