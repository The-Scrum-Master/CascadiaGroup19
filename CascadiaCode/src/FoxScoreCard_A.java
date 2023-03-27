import java.util.ArrayList;

public class FoxScoreCard_A extends FoxScoreCard{
    private int foxScore= 0;
    public FoxScoreCard_A(Player player) {
        super(player);
    }
    @Override
    public int countScore() {
        Tile[][] playerBoard = player.getPlayerBoard();
        for(int i=0;i <cordinateX.size();i++) {
            for(int j=0;j<cordinateY.size();j++){
                try {
                    if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j)].tokenPlayedType==null){

                    }
                    else if(playerBoard[cordinateX.get(i) - 1][cordinateY.get(j)].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) - 1][cordinateY.get(j)].tokenPlayedType);

                    }


                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j)].tokenPlayedType==null){

                    }
                    else if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j)].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) + 1][cordinateY.get(j)].tokenPlayedType);
                                               System.out.println("2");


                    }

                }catch (Exception ignored){

                }
                try {
                     if (playerBoard[cordinateX.get(i)][cordinateY.get(j) - 1].tokenPlayedType==null){

                    }
                   else if (playerBoard[cordinateX.get(i)][cordinateY.get(j) - 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i)][cordinateY.get(j) - 1].tokenPlayedType);

                    }

                }catch (Exception ignored){

                }
                try {
                     if (playerBoard[cordinateX.get(i)][cordinateY.get(j) + 1].tokenPlayedType==null){

                    }
                    else if (playerBoard[cordinateX.get(i)][cordinateY.get(j) + 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i)][cordinateY.get(j) + 1].tokenPlayedType );

                    }

                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) - 1].tokenPlayedType==null){

                    }
                    else if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) - 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) - 1].tokenPlayedType);


                    }


                }catch (Exception ignored){

                }
                try {
                     if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) + 1].tokenPlayedType==null){

                    }
                    else if (playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) + 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) - 1][cordinateY.get(j) + 1].tokenPlayedType);
                    }

                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) - 1].tokenPlayedType==null){

                    }
                    else if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) - 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) - 1].tokenPlayedType);

                    }

                }catch (Exception ignored){

                }
                try {
                    if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) + 1].tokenPlayedType==null){

                    }
                    else if (playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) + 1].tokenPlayedType != Wildlife.FOX) {
                        foxScore++;
                        types.add(playerBoard[cordinateX.get(i) + 1][cordinateY.get(j) + 1].tokenPlayedType);

                    }

                }catch (Exception ignored){

                }
            }
        }
        System.out.println("size of arraylist"+types.size());
        for(int i=0;i<types.size();i++){
            System.out.println(types.get(i));
        }
        for(int i=0;i<types.size();i++){
            for(int j=i+1;j<types.size();j++){
                if(types.get(i).equals(types.get(j))){
                    foxScore--;
                }
            }
        }
        return foxScore;
    }

    public static void explainCard() {
        System.out.println("This is Fox Scorecard A. Points are given depending on the number of unique wildlife types adjacent to it.\n");
    }
}
