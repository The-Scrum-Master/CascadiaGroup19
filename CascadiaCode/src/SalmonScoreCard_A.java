
    public class SalmonScoreCard_A extends SalmonScoreCard{
        public int SalmonScore= 0;

        public SalmonScoreCard_A(Player player) {
            super(player);
        }
        @Override
        public int countScore() {
            getIndexes(player.getPlayerBoard());
            Tile[][] playerBoard = player.getPlayerBoard();

        }
        public void getIndexes(Tile[][] playerBoard){

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


