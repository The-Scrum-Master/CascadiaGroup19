import java.util.ArrayList;

public class A_CommonTokenFunctions {

    public static ArrayList<TokenForPoints> getIndexesOfPlaceholders(Tile[][] playerBoard, MapGenerator playerMapGenerator, Wildlife animal) {
        //get indexes of all places where there are type animal placeholders
        ArrayList<TokenForPoints> arrayOfPlaceholders = new ArrayList<>();
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(!playerBoard[rows][columns].getTokenPlaced()){
                        for(Wildlife i : playerBoard[rows][columns].getSlots()){
                            //loop through placeholders of the tile
                            if(i.equals(animal)){
                                arrayOfPlaceholders.add(new TokenForPoints(columns, rows));
                            }
                        }
                    }
                }
            }
        }
        return arrayOfPlaceholders;
    }

    public static ArrayList<TokenForPoints> getIndexesOfTokens(Tile[][] playerBoard, MapGenerator playerMapGenerator, Wildlife animal) {
        //get indexes of all places where there are type animal tokens
        ArrayList<TokenForPoints> arrayOfTokens = new ArrayList<>();
        for(int rows = 0; rows < 46; rows++ ){
            for(int columns =0; columns < 46; columns++){
                if(!playerMapGenerator.getMap()[rows][columns].getEmptyTile()){
                    //if emptyTile is false=if the tile is occupied
                    if(playerBoard[rows][columns].getTokenPlaced()){
                        if(playerBoard[rows][columns].tokenPlayedType.equals(animal)){
                            arrayOfTokens.add(new TokenForPoints(columns, rows));
                        }
                    }
                }
            }
        }
        return arrayOfTokens;
    }
}
