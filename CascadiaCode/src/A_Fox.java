import java.util.ArrayList;
import java.util.Map;

public class A_Fox {
    Player player;
    private ArrayList<Integer> coordX = new ArrayList<Integer>();
    private ArrayList<Integer> coordY = new ArrayList<Integer>();
    ArrayList<Wildlife> animals = new ArrayList<Wildlife>();
    ArrayList<Integer> score = new ArrayList<Integer>();





    public A_Fox(Player player) {
        this.player = player;

    }

    public void countFoxes(Player player) {
        //function to count foxes on  board
        coordX = new ArrayList<>();
        coordY = new ArrayList<>();
        for (int x = 0; x < player.playerBoard.length; x++) {
            for (int y = 0; y < player.playerBoard.length; y++) {
                if (player.playerBoard[x][y] != null) {
                    if (player.playerBoard[x][y].tokenPlayed) {
                        if (player.playerBoard[x][y].tokenPlayedType.equals(Wildlife.FOX)) {
                            coordX.add(x);
                            coordY.add(y);
                        }
                    }
                }
            }
        }

    }

    public int countScore(Player player) {
        score = new ArrayList<>();
        if (coordX == null || coordX.size() < 1) {
            return 0;
        } else {
            System.out.println("entered else statement");
            //goes through all tiles with a fox token and searches around them for tiles with tokens placed and adds them to an arraylist
            for (int x = 0; x < coordX.size(); x++) {
                animals = new ArrayList<>();
                if (player.playerBoard[coordX.get(x)][coordY.get(x) + 1] != null && player.playerBoard[coordX.get(x)][coordY.get(x) + 1].getTokenPlaced()) {
                    System.out.println("middle bottom");
                    System.out.println(player.playerBoard[coordX.get(x)][coordY.get(x) + 1].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x)][coordY.get(x) + 1].tokenPlayedType);


                }
                if (player.playerBoard[coordX.get(x)][coordY.get(x) - 1] != null && player.playerBoard[coordX.get(x)][coordY.get(x) - 1].getTokenPlaced()) {
                    System.out.println("middle top");
                    System.out.println(player.playerBoard[coordX.get(x)][coordY.get(x) - 1].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x)][coordY.get(x) - 1].tokenPlayedType);


                }
                if (player.playerBoard[coordX.get(x) - 1][coordY.get(x)] != null && player.playerBoard[coordX.get(x) - 1][coordY.get(x)].getTokenPlaced()) {
                    System.out.println("middle left");
                    System.out.println(player.playerBoard[coordX.get(x) - 1][coordY.get(x)].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x) - 1][coordY.get(x)].tokenPlayedType);


                }
                if (player.playerBoard[coordX.get(x) + 1][coordY.get(x)] != null && player.playerBoard[coordX.get(x) + 1][coordY.get(x)].getTokenPlaced()) {
                    System.out.println("middle right");
                    System.out.println(player.playerBoard[coordX.get(x) + 1][coordY.get(x)].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x) + 1][coordY.get(x)].tokenPlayedType);


                }
                if (player.playerBoard[coordX.get(x) + 1][coordY.get(x) + 1] != null && player.playerBoard[coordX.get(x) + 1][coordY.get(x) + 1].getTokenPlaced()) {
                    System.out.println("bottom right");
                    System.out.println(player.playerBoard[coordX.get(x) + 1][coordY.get(x) + 1].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x) + 1][coordY.get(x) + 1].tokenPlayedType);


                }
                if (player.playerBoard[coordX.get(x) + 1][coordY.get(x) - 1] != null && player.playerBoard[coordX.get(x) + 1][coordY.get(x) - 1].getTokenPlaced()) {
                    System.out.println("top right");
                    System.out.println(player.playerBoard[coordX.get(x) + 1][coordY.get(x) - 1].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x) + 1][coordY.get(x) - 1].tokenPlayedType);


                }
                if (player.playerBoard[coordX.get(x) - 1][coordY.get(x) + 1] != null && player.playerBoard[coordX.get(x) - 1][coordY.get(x) + 1].getTokenPlaced()) {
                    System.out.println("bottom left");
                    System.out.println(player.playerBoard[coordX.get(x) - 1][coordY.get(x) + 1].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x) - 1][coordY.get(x) + 1].tokenPlayedType);


                }
                if (player.playerBoard[coordX.get(x) - 1][coordY.get(x) - 1] != null && player.playerBoard[coordX.get(x) - 1][coordY.get(x) - 1].getTokenPlaced()) {
                    System.out.println("top left");
                    System.out.println(player.playerBoard[coordX.get(x) - 1][coordY.get(x) - 1].tokenPlayedType);
                    animals.add(player.playerBoard[coordX.get(x) - 1][coordY.get(x) - 1].tokenPlayedType);


                }
                for (int j = 0; j < animals.size(); j++) {
                    System.out.println("animals before duplicates CALL " + animals.get(j));
                }
                removeDuplicates();

            }

            return getScore();
        }


    }

    public void removeDuplicates() {
        //for each tile with a fox token placed, checks and counts the unique tokens that surround it and adds them up to calculate points
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("animals before duplicates removed " + animals.get(i));
        }
        for (int i = 0; i < animals.size(); i++) {
            for (int j = i + 1; j < animals.size(); j++) {
                if (animals.get(i).equals(animals.get(j))) {
                    animals.remove(j);
                    j--;

                }
            }
        }
        // if  none unique token found  remove one of them from arrayList
        System.out.println("animal size after duplicates removed" + animals.size());
        int points = animals.size();
        System.out.println("points " + points);

        for (int i = 0; i < animals.size(); i++) {
            System.out.println("animals after duplicates removed " + animals.get(i));
        }

             score.add(points);
        //add number of unique tokens found to score arraylist




    }
    public int getScore(){

        int finalScore = 0;
        for(int i=0;i<score.size();i++){
            finalScore+=score.get(i);
        }
        //add up all the points and return
        return finalScore;

    }
    //
}
