/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
import java.util.ArrayList;

public class FindCorridors {
    private Tile[][] playerBoard;
    private static ArrayList<Tile> countedTiles = new ArrayList<>();
    private MaxCorridor[] maxCorridors = { new MaxCorridor(Habitat.RIVER, 0) , new MaxCorridor(Habitat.FOREST, 1),
            new MaxCorridor(Habitat.PRAIRIE, 2), new MaxCorridor(Habitat.MOUNTAIN, 3), new MaxCorridor(Habitat.WETLANDS ,4) };

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    };

    FindCorridors(Tile[][] playerBoard) {
        this.playerBoard = playerBoard;
    }

    public MaxCorridor[] mapIterator(Tile[][] board) {
        // iterates through the playerboard calls recurisive check on any corridors found
        resetMaxCorridors();
        for (int i = 0; i < 46; i++) {
            for (int j = 0; j < 46; j++) {
                Tile cur = board[i][j];
                if (cur != null) {
                    firstHabitatCheck(cur);
                    Habitat corridorHabitat;
                    if (corridorFound(Direction.RIGHT, cur)) {
                        corridorHabitat = corridorFoundType(Direction.RIGHT, cur);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.RIGHT, cur), Direction.RIGHT, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                    if (corridorFound(Direction.DOWN, cur)) {
                        corridorHabitat = corridorFoundType(Direction.DOWN, cur);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.DOWN, cur), Direction.DOWN, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                    if (corridorFound(Direction.LEFT, cur)) {
                        corridorHabitat = corridorFoundType(Direction.LEFT, cur);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.LEFT, cur), Direction.LEFT, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                    if (corridorFound(Direction.UP, cur)) {
                        corridorHabitat = corridorFoundType(Direction.UP, cur);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.UP, cur), Direction.UP, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                }
            }
        }
        MaxCorridor[] copyofMaxCorridor = new MaxCorridor[5];
        for(int i = 0; i < maxCorridors.length; i ++){
            copyofMaxCorridor[i] = maxCorridors[i];
        }
        return insertionSort(copyofMaxCorridor);
    }

    //recursivly checks for corridores of a certain type in all adjecent tiles to current, if found calls on the adj tile
    private void recursiveCheck(Tile curTile, Direction direction, Habitat corridorHabitat) {
        if (curTile != null && !countedTiles.contains(curTile)) {
            if (corridorCheck(Direction.RIGHT, curTile, corridorHabitat)){
                countedTiles.add(curTile);
                recursiveCheck(getAdjTile(Direction.RIGHT, curTile), Direction.RIGHT, corridorHabitat);
            }else if (corridorCheck(Direction.DOWN, curTile, corridorHabitat)) {
                countedTiles.add(curTile);
                recursiveCheck(getAdjTile(Direction.DOWN, curTile), Direction.DOWN, corridorHabitat);
            }else if (corridorCheck(Direction.LEFT, curTile, corridorHabitat)) {
                countedTiles.add(curTile);
                recursiveCheck(getAdjTile(Direction.LEFT, curTile), Direction.LEFT, corridorHabitat);
            }else if(corridorCheck(Direction.UP, curTile, corridorHabitat)) {
                countedTiles.add(curTile);
                recursiveCheck(getAdjTile(Direction.UP, curTile), Direction.UP, corridorHabitat);
            }else{
            largestCorridorTracker(corridorHabitat);
            }
        }
    }

    private boolean corridorCheck(Direction direction, Tile current, Habitat corridorHabitat) {
        return corridorFound(direction, current) && corridorFoundType(direction, current) == corridorHabitat;
    }

    //gets the habitat type of a corridor that has been found
    private Habitat corridorFoundType(Direction direction, Tile current){
        if(current == null){
            throw new NullPointerException("current tile passed to tileMatch is null");
        }
        Habitat habitatTopAndLeft = current.getHabitat(0);
        Habitat habitatBottomAndRight;
        if (current.getSelect() == 1) {
            habitatBottomAndRight = current.getHabitat(0);
        } else {
            habitatBottomAndRight = current.getHabitat(1);
        }
        if (direction == Direction.UP) {
            return habitatTopAndLeft;
        } else if (direction == Direction.LEFT) {
            return habitatTopAndLeft;
        } else if (direction == Direction.RIGHT) {
            return habitatBottomAndRight;
        } else if (direction == Direction.DOWN) {
            return habitatBottomAndRight;
        } else {
            throw new IllegalArgumentException("The Direction is not a valid direction");
        }
    }

    //checks if tile current creates a corridor with a tile adjenct in a given direction.
    private Boolean corridorFound(Direction direction, Tile current) {
        if (current == null) {
            throw new NullPointerException("current tile passed to tileMatch is null");
        }
        Habitat habitatTopAndLeft = current.getHabitat(0);
        Habitat habitatBottomAndRight;
        if (current.getSelect() == 1) {
            habitatBottomAndRight = current.getHabitat(0);
        } else {
            habitatBottomAndRight = current.getHabitat(1);
        }
        Tile adjTile;
        if (direction == Direction.UP) {
            adjTile = getAdjTile(Direction.UP, current);
            return adjHabitatCheck(Direction.UP, adjTile, habitatTopAndLeft);
        } else if (direction == Direction.LEFT) {
            adjTile = getAdjTile(Direction.LEFT, current);
            return adjHabitatCheck(Direction.LEFT, adjTile, habitatTopAndLeft);
        } else if (direction == Direction.RIGHT) {
            adjTile = getAdjTile(Direction.RIGHT, current);
            return adjHabitatCheck(Direction.RIGHT, adjTile, habitatBottomAndRight);
        } else if (direction == Direction.DOWN) {
            adjTile = getAdjTile(Direction.DOWN, current);
            return adjHabitatCheck(Direction.DOWN, adjTile, habitatBottomAndRight);
        } else {
            throw new IllegalArgumentException("The Direction is not a valid direction");
        }
    }

    //gets the tile adjencent to current tile in a given direction
    private Tile getAdjTile(Direction direction, Tile current) {
        if (current == null) {
            throw new NullPointerException("current tile passed to getAdjTile is null");
        }
        switch (direction) {
            case UP:
                return playerBoard[current.getBoardYIndex() - 1][current.getBoardXIndex()];
            case DOWN:
                return playerBoard[current.getBoardYIndex() + 1][current.getBoardXIndex()];
            case LEFT:
                return playerBoard[current.getBoardYIndex()][current.getBoardXIndex() - 1];
            case RIGHT:
                return playerBoard[current.getBoardYIndex()][current.getBoardXIndex() + 1];
            default:
                throw new IllegalStateException("The Direction is not a valid state");
        }
    }

    //checks that the tile has habitat type habitatTocheck is facing the directionComingFrom
    private boolean adjHabitatCheck(Direction directionComingFrom, Tile tileInDirection, Habitat habitatToCheck) {
        if (tileInDirection != null) {
            if (tileInDirection.getSelect() == 1) {
                // If the tile is a solo then no need to check which direction habitat is on
                if (tileInDirection.getHabitat(0) == habitatToCheck) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // Need to check which direction to check;
                if (directionComingFrom == Direction.UP || directionComingFrom == Direction.LEFT) {
                    // only checks the bottom and right side of the tile (aka habitat index 1)
                    if (tileInDirection.getHabitat(1) == habitatToCheck) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // otherwise check this tiles top and left side of this tile (aka habitat index 0)
                    if (tileInDirection.getHabitat(0) == habitatToCheck) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }
    //gets the index of a given corridor type
    private int getIndexOfType(Habitat habitat){
        for(int i = 0; i < maxCorridors.length; i++){
            if(maxCorridors[i].getHabitatType() == habitat){
                return maxCorridors[i].getIndex();
            }
        }
        throw new IllegalStateException("There is a an invalid habitat inputed into getindexType");
    }

    public void largestCorridorTracker(Habitat habitat) {
        // if the counter is greater than the max, set the max to the counter
        int index;
        switch (habitat) {
            case RIVER:
                index = getIndexOfType(Habitat.RIVER);
                if (countedTiles.size() > maxCorridors[index].getSize()) {
                    maxCorridors[index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case FOREST:
                index = getIndexOfType(Habitat.FOREST);
                if (countedTiles.size() > maxCorridors[index].getSize()) {
                    maxCorridors[index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case PRAIRIE:
                index = getIndexOfType(Habitat.PRAIRIE);
                if (countedTiles.size() > maxCorridors[index].getSize()) {
                    maxCorridors[index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case MOUNTAIN:
                index = getIndexOfType(Habitat.MOUNTAIN);
                if (countedTiles.size() > maxCorridors[index].getSize()) {
                    maxCorridors[index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case WETLANDS:
                index = getIndexOfType(Habitat.WETLANDS);
                if (countedTiles.size() > maxCorridors[index].getSize()) {
                    maxCorridors[index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            default:
                throw new IllegalStateException("The Habitat is not a valid state in LargetCorridorTracker");
        }
    }
    //checks for corridors of size 1
    private void firstHabitatCheck(Tile curTile) {
        Habitat habitat_index0 = curTile.getHabitat(0);
        for (int i = 0; i < maxCorridors.length; i++) {
            if (maxCorridors[i].getHabitatType() == habitat_index0) {
                countedTiles.add(curTile);
                largestCorridorTracker(maxCorridors[i].getHabitatType());
                countedTiles.clear();
            }
            if(curTile.getSelect() != 1){
                Habitat habitat_index1 = curTile.getHabitat(1);
                if (maxCorridors[i].getHabitatType() == habitat_index1) {
                    countedTiles.add(curTile);
                    largestCorridorTracker(maxCorridors[i].getHabitatType());
                    countedTiles.clear();
                }
            }
        }
    }


    private MaxCorridor[] insertionSort(MaxCorridor[] arrayMaxCorridors) {
        //we use insertion sort because the arraylist size is not going to be bigger than 10 most probably, and a max size of 20
        int n = arrayMaxCorridors.length;
        for (int i = 1; i < n; i++) {
            MaxCorridor temp = arrayMaxCorridors[i];
            int j = i - 1;
            while (j >= 0 && arrayMaxCorridors[j].getSize() < temp.getSize()) {
                arrayMaxCorridors[j+1] =  arrayMaxCorridors[j];
                j = j - 1;
            }
            arrayMaxCorridors[j+1] = temp;
        }
        return arrayMaxCorridors;
    }

    public void printMaxCorridors(MaxCorridor[] array){
        System.out.println("Finished iterating through the playerboard for, the longest corridors are: ");
        for(int i = 0; i < array.length; i++){
            System.out.println("Corridor " + i + " is of type: " + array[i].getHabitatType() + " and has a size of: " + array[i].getSize());
        }
    }

    public void resetMaxCorridors(){
        for(int i = 0; i < maxCorridors.length; i++){
            maxCorridors[i].clearCords();
        }
    }

}
