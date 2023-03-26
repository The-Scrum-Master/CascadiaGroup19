import java.util.ArrayList;

public class FoxScoreCard_A extends FoxScoreCard{
    private int foxScore= 0;


    public FoxScoreCard_A(Player player) {
        super(player);
    }
    @Override
    public int countScore() {
        System.out.println("entered fox function");
        Tile[][] playerBoard = player.getPlayerBoard();
        System.out.println("size"+cordinateX.size());
        System.out.println("size"+cordinateY.size());
        for(int i=0;i <cordinateX.size();i++) {
            for(int j=0;j<cordinateY.size();j++){
                System.out.println("entered double for loop");
                try {
                    if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j)].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) - 1][cordinateY.get(j)].tokenPlayedType);
                    }

                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j)].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) + 1][cordinateY.get(j)].tokenPlayedType);
                    }
                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i)][cordinateY.get(j) - 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i)][cordinateY.get(j) - 1].tokenPlayedType);
                    }
                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i)][cordinateY.get(j) + 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i)][cordinateY.get(j) + 1].tokenPlayedType );
                    }
                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) - 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) - 1].tokenPlayedType);


                    }
                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) + 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) + 1].tokenPlayedType);

                    }
                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) - 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) - 1].tokenPlayedType);
                    }
                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) + 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) + 1].tokenPlayedType);

                    }
                }catch (Exception ignored){

                }
            }
        }
        System.out.println("size of arraylist"+types.size());
        for(int i=0;i<types.size();i++){
            for(int j=i+1;j<types.size();j++){
                if(types.get(i).equals(types.get(j))){
                    foxScore--;
                }
            }
        }
        return foxScore;
    }

    @Override
    public void explainCard() {
        System.out.println(" You have chosen Fox Scorecard A, Score points shown for each straight line of adjacent fox, depending on length of the line. \n" +
                "Two lines of Fox may be adjacent to one another, however, each Fox may only count for a single line. Lines do not need to be horizontal.");
        System.out.println( "A solo Fox:             2 points\n" +
                "2 Fox line:             5 points\n" +
                "3 Fox line:             9 points\n" +
                "4 Fox line:             13 points");
    }
}
