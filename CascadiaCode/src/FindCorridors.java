
import java.util.ArrayList;

public class FindCorridors {
    private Tile[][] playerBoard;
    private static ArrayList<Tile> countedTiles = new ArrayList<>();
    private MaxCorridor[] maxCorridors = { new MaxCorridor(Habitat.RIVER), new MaxCorridor(Habitat.FOREST),
            new MaxCorridor(Habitat.PRAIRIE), new MaxCorridor(Habitat.MOUNTAIN), new MaxCorridor(Habitat.WETLANDS) };
    public static int RiverCorridor_index = 0;
    public static int ForrestCorridor_index = 1;
    public static int PrairieCorridor_index = 2;
    public static int MountainCorridor_index = 3;
    public static int WetlandCorridor_index = 4;

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
        // iterates through the playerboard that is currently playable.
        for (int i = 0 + 21 - GameRunner.getHelperIntToPrintMap(); i < 46 - 21
                + GameRunner.getHelperIntToPrintMap(); i++) {
            for (int j = 0 + 21 - GameRunner.getHelperIntToPrintMap(); j < 46 - 21
                    + GameRunner.getHelperIntToPrintMap(); j++) {
                Tile cur = board[i][j];
                if (cur != null) {
                    // System.out.println("checking tile " + j + " " + i);
                    firstHabitatCheck(cur);
                    if (tileMatch(Direction.RIGHT, cur)) {
                        Habitat corridorHabitat;
                        if (cur.getSelect() == 1) {
                            corridorHabitat = cur.getHabitat(0);
                        } else {
                            corridorHabitat = cur.getHabitat(1);
                        }
                        cur.getHabitat(0);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.RIGHT, cur), Direction.RIGHT, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                    if (tileMatch(Direction.DOWN, cur)) {
                        Habitat corridorHabitat;
                        if (cur.getSelect() == 1) {
                            corridorHabitat = cur.getHabitat(0);
                        } else {
                            corridorHabitat = cur.getHabitat(1);
                        }
                        cur.getHabitat(0);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.DOWN, cur), Direction.DOWN, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                    if (tileMatch(Direction.LEFT, cur)) {
                        Habitat corridorHabitat;
                        corridorHabitat = cur.getHabitat(0);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.LEFT, cur), Direction.LEFT, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                    if (tileMatch(Direction.UP, cur)) {
                        Habitat corridorHabitat;
                        corridorHabitat = cur.getHabitat(0);
                        countedTiles.add(cur);
                        recursiveCheck(getAdjTile(Direction.UP, cur), Direction.UP, corridorHabitat);
                        largestCorridorTracker(corridorHabitat);
                        countedTiles.clear();
                    }
                    // System.out.println("Iterated to the next tile in the playerboard");
                }
            }
        }
        reorderMaxCorridors(maxCorridors);
        printMaxCorridors();
        return maxCorridors;
    }

    private void recursiveCheck(Tile curTile, Direction direction, Habitat corridorHabitat) {
        if (curTile != null && !countedTiles.contains(curTile)) {
            // System.out.println("recursiveCheck added tile " + curTile.getBoardXIndex() + " " + curTile.getBoardYIndex() + "to corridor type " + corridorHabitat);
            countedTiles.add(curTile);
            if (tileMatch(Direction.RIGHT, curTile)) {
                // System.out.println("recursive check moves Right");
                recursiveCheck(getAdjTile(Direction.RIGHT, curTile), Direction.RIGHT, corridorHabitat);
            }
            if (tileMatch(Direction.DOWN, curTile)) {
                // System.out.println("recursive check moves Down");
                recursiveCheck(getAdjTile(Direction.DOWN, curTile), Direction.DOWN, corridorHabitat);

            }
            if (tileMatch(Direction.LEFT, curTile)) {
                // System.out.println("recursive check moves left");
                recursiveCheck(getAdjTile(Direction.LEFT, curTile), Direction.LEFT, corridorHabitat);
            }
            if (tileMatch(Direction.UP, curTile)) {
                // System.out.println("recursive check moves up");
                recursiveCheck(getAdjTile(Direction.UP, curTile), Direction.UP, corridorHabitat);
            }
            largestCorridorTracker(corridorHabitat);
            countedTiles.clear();
        }
    }

    private Boolean tileMatch(Direction direction, Tile current) {
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

    public void largestCorridorTracker(Habitat habitat) {
        // if the counter is greater than the max, set the max to the counter
        switch (habitat) {
            case RIVER:
                if (countedTiles.size() > maxCorridors[RiverCorridor_index].getSize()) {
                    maxCorridors[RiverCorridor_index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[RiverCorridor_index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case FOREST:
                if (countedTiles.size() > maxCorridors[ForrestCorridor_index].getSize()) {
                    maxCorridors[ForrestCorridor_index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[ForrestCorridor_index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case PRAIRIE:
                if (countedTiles.size() > maxCorridors[PrairieCorridor_index].getSize()) {
                    maxCorridors[PrairieCorridor_index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[PrairieCorridor_index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case MOUNTAIN:
                if (countedTiles.size() > maxCorridors[MountainCorridor_index].getSize()) {
                    maxCorridors[MountainCorridor_index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[MountainCorridor_index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            case WETLANDS:
                if (countedTiles.size() > maxCorridors[WetlandCorridor_index].getSize()) {
                    maxCorridors[WetlandCorridor_index].clearCords();
                    for (int i = 0; i < countedTiles.size(); i++) {
                        maxCorridors[WetlandCorridor_index].addCords(countedTiles.get(i).getBoardXIndex(),
                                countedTiles.get(i).getBoardYIndex());
                    }
                }
                break;
            default:
                throw new IllegalStateException("The Habitat is not a valid state in LargetCorridorTracker");
        }
    }

    public void initializeCorridors() {
        Tile startTileSolo = playerBoard[22][22];
        Tile startTile1 = playerBoard[23][22];
        Tile startTile2 = playerBoard[23][23];
        if (startTileSolo == null || startTile1 == null || startTile2 == null) {
            throw new IllegalStateException("starting tiles are null");
        }
        for (int i = 0; i < maxCorridors.length; i++) {
            if (maxCorridors[i].getHabitatType() == startTileSolo.getHabitat(0)) {
                maxCorridors[i].addCords(startTileSolo.getBoardXIndex(), startTileSolo.getBoardYIndex());
            } else if (maxCorridors[i].getHabitatType() == startTile1.getHabitat(0)) {
                maxCorridors[i].addCords(startTile1.getBoardXIndex(), startTile1.getBoardYIndex());
            } else if (maxCorridors[i].getHabitatType() == startTile1.getHabitat(1)) {
                maxCorridors[i].addCords(startTile1.getBoardXIndex(), startTile1.getBoardYIndex());
            } else if (maxCorridors[i].getHabitatType() == startTile2.getHabitat(0)) {
                maxCorridors[i].addCords(startTile2.getBoardXIndex(), startTile2.getBoardYIndex());
            } else if (maxCorridors[i].getHabitatType() == startTile2.getHabitat(1)) {
                maxCorridors[i].addCords(startTile2.getBoardXIndex(), startTile2.getBoardYIndex());
            }
        }
    }

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

    private void reorderMaxCorridors(MaxCorridor[] maxCorridors){
        for(int i = 0; i < maxCorridors.length; i++){
            for(int j = i; j < maxCorridors.length; j++){
                if(maxCorridors[i].getSize() < maxCorridors[j].getSize()){
                    MaxCorridor temp = maxCorridors[i];
                    switchIndex(i, j);
                    maxCorridors[i] = maxCorridors[j];
                    switchIndex(j, i);
                    maxCorridors[j] = temp;
                }
            }
        }
    }
    
    private void switchIndex(int origianl, int newIndex){
        maxCorridors[origianl].getHabitatType();
        switch(maxCorridors[origianl].getHabitatType()){
            case RIVER:
                RiverCorridor_index = newIndex;
                break;
            case FOREST:
                ForrestCorridor_index = newIndex;
                break;
            case PRAIRIE:
                PrairieCorridor_index = newIndex;
                break;
            case MOUNTAIN:
                MountainCorridor_index = newIndex;
                break;
            case WETLANDS:
                WetlandCorridor_index = newIndex;
                break;
            default:    
                throw new IllegalStateException("The Habitat is not a valid state in switchIndex");
        };
    }


    private  void printMaxCorridors(){ 
        System.out.println("Finished iterating through the playerboard for, the longest corridors are: ");
        for(int i = 0; i < maxCorridors.length; i++){
            System.out.println("Corridor " + i + " is of type: " + maxCorridors[i].getHabitatType() + " and has a size of: " + maxCorridors[i].getSize());
        }
    }

}
