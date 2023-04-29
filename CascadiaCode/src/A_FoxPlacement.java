import java.util.ArrayList;


    public class A_FoxPlacement {
        Player player;
        private ArrayList<Integer> coordX = new ArrayList<Integer>();
        private ArrayList<Integer> coordY = new ArrayList<Integer>();

        ArrayList<Wildlife> animals = new ArrayList<Wildlife>();
        ArrayList<Integer> score = new ArrayList<Integer>();

        public int getXcoord(int x){
            return coordX.get(x);
        }
        public int getYcoord(int y){
            return coordY.get(y);
        }



        public A_FoxPlacement(Player player) {
            this.player = player;

        }

        public void countFoxesPlaceHolders(Player player) {
            //function to count fox placeholders on  board
            coordX = new ArrayList<>();
            coordY = new ArrayList<>();
            for (int x = 0; x < player.playerBoard.length; x++) {
                for (int y = 0; y < player.playerBoard.length; y++) {
                    if (player.playerBoard[x][y] != null) {
                        if(!player.playerBoard[x][y].tokenPlayed) {
                            for (int i = 0; i < player.playerBoard[x][y].getSlots().length; i++) {
                                if (player.playerBoard[x][y].getSlot(i).equals(Wildlife.FOX)) {
                                    coordX.add(x);
                                    coordY.add(y);
                                }
                            }
                        }

                    }
                }
            }

        }

        public void countPotentialScore(Player player) {
            score = new ArrayList<>();
            if (coordX == null || coordX.size() < 1) {

            } else {
                //goes through all tiles with a fox placeholder and searches around them for tiles with tokens placed and adds them to an arraylist
                for (int x = 0; x < coordX.size(); x++) {
                    animals = new ArrayList<>();
                    if (player.playerBoard[coordX.get(x)][coordY.get(x) + 1] != null && player.playerBoard[coordX.get(x)][coordY.get(x) + 1].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x)][coordY.get(x) + 1].tokenPlayedType);


                    }
                    if (player.playerBoard[coordX.get(x)][coordY.get(x) - 1] != null && player.playerBoard[coordX.get(x)][coordY.get(x) - 1].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x)][coordY.get(x) - 1].tokenPlayedType);


                    }
                    if (player.playerBoard[coordX.get(x) - 1][coordY.get(x)] != null && player.playerBoard[coordX.get(x) - 1][coordY.get(x)].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x) - 1][coordY.get(x)].tokenPlayedType);


                    }
                    if (player.playerBoard[coordX.get(x) + 1][coordY.get(x)] != null && player.playerBoard[coordX.get(x) + 1][coordY.get(x)].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x) + 1][coordY.get(x)].tokenPlayedType);


                    }
                    if (player.playerBoard[coordX.get(x) + 1][coordY.get(x) + 1] != null && player.playerBoard[coordX.get(x) + 1][coordY.get(x) + 1].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x) + 1][coordY.get(x) + 1].tokenPlayedType);


                    }
                    if (player.playerBoard[coordX.get(x) + 1][coordY.get(x) - 1] != null && player.playerBoard[coordX.get(x) + 1][coordY.get(x) - 1].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x) + 1][coordY.get(x) - 1].tokenPlayedType);


                    }
                    if (player.playerBoard[coordX.get(x) - 1][coordY.get(x) + 1] != null && player.playerBoard[coordX.get(x) - 1][coordY.get(x) + 1].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x) - 1][coordY.get(x) + 1].tokenPlayedType);


                    }
                    if (player.playerBoard[coordX.get(x) - 1][coordY.get(x) - 1] != null && player.playerBoard[coordX.get(x) - 1][coordY.get(x) - 1].getTokenPlaced()) {
                        animals.add(player.playerBoard[coordX.get(x) - 1][coordY.get(x) - 1].tokenPlayedType);


                    }

                    removeDuplicates();

                }

             placeToken(player,getScore());
            }


        }

        public void removeDuplicates() {
            //for each tile with a fox token placed, checks and counts the unique tokens that surround it and adds them up to calculate points

            for (int i = 0; i < animals.size(); i++) {
                for (int j = i + 1; j < animals.size(); j++) {
                    if (animals.get(i).equals(animals.get(j))) {
                        animals.remove(j);
                        j--;

                    }
                }
            }

            score.add(animals.size());
        }

        public int getScore(){
            //returns index of tile with fox placeholder that yields the highest score
            int max=0;
            int j=0;
            for(int i=0;i<score.size();i++){
                if(max<score.get(i)){
                    max = score.get(i);
                    j= i;
                }
            }
            return j;
        }

        public void placeToken(Player player,int coords){
            System.out.println("Token placed at "+ getXcoord(coords) +","+getYcoord(coords) );
            player.placeToken(getXcoord(coords),getYcoord(coords));
        }



    }


