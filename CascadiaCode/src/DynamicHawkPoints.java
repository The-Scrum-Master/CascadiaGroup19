/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

/*import java.util.ArrayList;

public class DynamicHawkPoints {
    protected ArrayList<TokenForPoints> arrayOfPlaceholders;
    Player player;

    public DynamicHawkPoints (Player player) {
        this.player=player;
    }

    public int countPosibleScore() {
        for(int i=0; i<arrayOfPlaceholders.size(); i++){
            boolean foundAdjacentHawk=false;
            for(int j=0; j<arrayOfPlaceholders.size(); j++){
                if(arrayOfPlaceholders.get(j).getValid()){
                    continue;
                }
                if(j!=i) {
                    //making sure that the element we are looking at isn't the same one we are comparing it to
                    if(arrayOfPlaceholders.get(i).getCordX()==arrayOfPlaceholders.get(j).getCordX()   ||
                            arrayOfPlaceholders.get(i).getCordX()==arrayOfPlaceholders.get(j).getCordX()+1 ||
                            arrayOfPlaceholders.get(i).getCordX()==arrayOfPlaceholders.get(j).getCordX()-1)
                    { //looking for adjacent X cord

                        if(arrayOfPlaceholders.get(i).getCordY()==arrayOfPlaceholders.get(j).getCordY()   ||
                                arrayOfPlaceholders.get(i).getCordY()==arrayOfPlaceholders.get(j).getCordY()+1 ||
                                arrayOfPlaceholders.get(i).getCordY()==arrayOfPlaceholders.get(j).getCordY()-1)  {
                            //looking for adjacent Y cord
                            foundAdjacentHawk=true;
                            break;
                        }
                    }
                }
            }
            if(!foundAdjacentHawk){
                arrayOfPlaceholders.get(i).setValid(true);
                //numberOfHawks++;
            }
            //System.out.println(numberOfHawks);
        }

        int pointsForNextHawkPlacement = hawksIntoPoints(player.getNumberOfHawks()+1);


        return ;
    }


    public void getIndexesOfPlaceholders(Tile[][] playerBoard, MapGenerator playerMapGenerator) {
        //get indexes of all places there are hawk placeholders
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
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

    private int hawksIntoPoints(int hawks){
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
}

 */
