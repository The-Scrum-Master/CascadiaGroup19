/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;
import java.util.Objects;
public class GameRunner {
    private static final ArrayList<Player> players = new ArrayList<>();
    private static final ArrayList<A_Hawk> playersHawkScores = new ArrayList<>();
    private static final ArrayList<A_Bear> playersBearScores = new ArrayList<>();
    private static final ArrayList<A_Elk> playersElkScores = new ArrayList<>();
    private static final ArrayList<A_Salmon> playersSalmonScores = new ArrayList<>();
    public static boolean continueGame = true;
    private static int helperIntToPrintMap = -1;

    public static void main(String[] args) throws InterruptedException {
        IOcascadia.welcomeMessage();
        IOcascadia.botsIntro();
        IOcascadia.playerOrder();
        //output message informing of the order the players are going to follow
        TileDeck.createDeck();
        //Creates the deck for this game (85 tiles shuffled in a stack)
        TileDeck.playRiver();
        int numberOfPlayers = IOcascadia.getParticipantsInt();
        //stores number of players into the variable numberOfPlayers
        for (int i = 0; i < numberOfPlayers; i++) {
            //using a loop to add the players in order into the players ArrayList and creating
            //an instance of Player for each
            players.add(new Player(IOcascadia.playerNames.get(IOcascadia.order.get(i)), i));
            playersHawkScores.add(new A_Hawk(players.get(i)));
            playersBearScores.add(new A_Bear(players.get(i)));
            playersElkScores.add(new A_Elk(players.get(i)));
            playersSalmonScores.add(new A_Salmon(players.get(i)));
        }
        int playersTurn = 0;
        //int to rotate around players in order
        int turnTheGameIsAt = 0;

        A_Hawk.explainHawkCard();
        A_Bear.explainBearCard();
        A_Elk.explainCard();
        A_Salmon.explainCard();
        A_Fox.explainFoxCard();
        A_Fox fox = new A_Fox(players.get(playersTurn));
        A_FoxPlacement foxPlacement = new A_FoxPlacement(players.get(playersTurn));

        int strategyChosen = Tile.randomNumberGenerator(3);
        System.out.println("The strategy to be implemented this game is strategy " + (strategyChosen+1));
        if(strategyChosen==0){
            System.out.println("This strategy picks the pair tile-token based on the habitat scoring. Then both the tile and token are placed to maximise points.");
        }
        if(strategyChosen==1){
            System.out.println("This strategy is the random strat. It will pick the tile randomly and then place the token randomly.");
        }
        if(strategyChosen==2){
            System.out.println("This strategy picks the pair tile-token based on the token scoring. Then both the tile and token are placed to maximise points.");}

        Thread.sleep(2000);

        while (turnTheGameIsAt <= 10 && continueGame) {
            //main loop that runs the game until 20 turns pass
            if (playersTurn == numberOfPlayers) {
                playersTurn = 0;
                turnTheGameIsAt++;
                if(turnTheGameIsAt>4){
                    if(turnTheGameIsAt%3==0){
                        helperIntToPrintMap++;
                    }
                } else{
                    helperIntToPrintMap++;
                }
            } else {

                //here, if a bool hasAlreadyStarted==false, then the starter tiles should be displayed
                //here, the 4 pairs habitats-tokens have to be displayed for a player to choose
                //then the player will choose and place his habitat, then his token
                //then the player will terminate his turn or the game (for the sake of terminating the program, in the future
                //the game will end when the players reach 20 turns


                if (!players.get(playersTurn).isFirstTurnPlayed()) {
                    players.get(playersTurn).generateInitialMap();
                    System.out.println(players.get(playersTurn).getName() +" these are your starter tiles!!!");
                    System.out.println();
                    Thread.sleep(1000);
                } else {
                    if(playersTurn==1) {
                        System.out.println(players.get(0).getName() + " map:");
                        players.get(0).map.fillMapWithAllowedTilePlacements();
                        players.get(0).printMap(helperIntToPrintMap);
                    }
                    else if(playersTurn==0){
                        System.out.println(players.get(1).getName() + " map:");
                        players.get(1).map.fillMapWithAllowedTilePlacements();
                        players.get(1).printMap(helperIntToPrintMap);
                    }
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(players.get(playersTurn).getName()+ " map:");
                    players.get(playersTurn).map.fillMapWithAllowedTilePlacements();
                    players.get(playersTurn).printMap(helperIntToPrintMap);
                    System.out.println(players.get(playersTurn).getName() + "'s turn:\n");

                    PairDisplay.showPairs();


                    IOcascadia.instructionsToChoosePair();

                    boolean shouldBotCull=true;
                    boolean alreadyPickedFromRiver=false;
                    if(strategyChosen==0){
                        //CHECKS WHILE NATURE TOKEN > 1 AND SPLIT PICKS BETWEEN THE BEST TILE AND TOKEN
                        while(players.get(playersTurn).getNatureTokenNumber()>1 && shouldBotCull){
                            shouldBotCull=shouldBotCull(playersTurn);
                        }
                        if(!shouldBotCull){
                            int tokenToMaximisePointsIndexInRiver=0;
                            ArrayList<TokenForPoints> pointsForEveryTypeOfToken = createArrayOfBestTokensToPlace(playersTurn);
                            outerloop:
                            for(int i=0 ; i<pointsForEveryTypeOfToken.size() ; i++){
                                for(int j=0 ; j<TileDeck.getRiverTokens().length ; j++){
                                    if(pointsForEveryTypeOfToken.get(i).getTypeOfAnimal()==TileDeck.getRiverTokensIndex(j)){
                                        tokenToMaximisePointsIndexInRiver=j;
                                        break outerloop;
                                    }
                                }
                            }
                            players.get(playersTurn).splitPick(players.get(playersTurn).chooseFromRiver(), tokenToMaximisePointsIndexInRiver);
                            alreadyPickedFromRiver=true;
                        }
                    } else{
                        while(players.get(playersTurn).getNatureTokenNumber()>0 && shouldBotCull){
                            shouldBotCull=shouldBotCull(playersTurn);
                        }
                        //check what the best token to place would be based on points, search through the offered tokens in the river, and if it is not there, cull the river
                    }

                    int instructionsToChoosePairInput = Tile.randomNumberGenerator(4);
                    if (strategyChosen == 0) {
                        if(!alreadyPickedFromRiver){
                            instructionsToChoosePairInput = players.get(playersTurn).chooseFromRiver();
                        }
                    }
                    if (strategyChosen == 2) {
                        int pointsPerRiverToken=0;
                        int maxPoints = -1;
                        int maxPointsIndex=0;
                        for(int i=0;i<TileDeck.getRiverTokens().length;i++){
                            if(TileDeck.getRiverTokensIndex(i) == Wildlife.HAWK){
                                pointsPerRiverToken=playersHawkScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if (TileDeck.getRiverTokensIndex(i) == Wildlife.BEAR){
                                pointsPerRiverToken=playersBearScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if(TileDeck.getRiverTokensIndex(i) == Wildlife.FOX){
                                foxPlacement.countFoxesPlaceHolders(players.get(playersTurn));
                                foxPlacement.countPotentialScore(players.get(playersTurn));
                                foxPlacement.getScore();
                                pointsPerRiverToken= foxPlacement.getMaximumScore();
                            } else if (TileDeck.getRiverTokensIndex(i) == Wildlife.ELK) {
                                pointsPerRiverToken=playersElkScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if (TileDeck.getRiverTokensIndex(i) == Wildlife.SALMON) {
                                pointsPerRiverToken=playersSalmonScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            }

                            if(pointsPerRiverToken>=maxPoints){
                                maxPoints=pointsPerRiverToken;
                                maxPointsIndex=i;
                            }
                        }

                        instructionsToChoosePairInput=maxPointsIndex;
                    }

                    if(!alreadyPickedFromRiver){
                        if (instructionsToChoosePairInput <= 3) {
                            players.get(playersTurn).pickPair(instructionsToChoosePairInput);
                        }
                    }

                    TileGenerator heldTileGenerator = new TileGenerator(players.get(playersTurn).heldTile);
                    System.out.println();
                    heldTileGenerator.printTile();
                    String token = Wildlife.animalSymbol(players.get(playersTurn).heldToken);
                    System.out.println(token + "\n");

                    if(strategyChosen!=0)
                    {
                        players.get(playersTurn).findBestPosition(0,1);
                    }
                    players.get(playersTurn).findBestPosition(0,0);

                    players.get(playersTurn).map.fillMapWithAllowedTilePlacements();
                    players.get(playersTurn).printMap(helperIntToPrintMap);

                    players.get(playersTurn).setHeldTile(null);

                    if (players.get(playersTurn).checkToken()) {
                    } else {
                        if(strategyChosen==0 || strategyChosen==2){
                            if(players.get(playersTurn).heldToken.equals(Wildlife.HAWK)){
                                playersHawkScores.get(playersTurn).strategy1(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if(players.get(playersTurn).heldToken.equals(Wildlife.BEAR)){
                                playersBearScores.get(playersTurn).strategy1(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if(players.get(playersTurn).heldToken.equals(Wildlife.ELK)){
                                playersElkScores.get(playersTurn).strategy1(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if (players.get(playersTurn).heldToken.equals(Wildlife.FOX)){
                                foxPlacement.strategy1(players.get(playersTurn));
                            } else if(players.get(playersTurn).heldToken.equals(Wildlife.SALMON)){
                                playersSalmonScores.get(playersTurn).strategy1(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            }/* else{
                                System.out.println("Do you want to place the token? (yes or no)");
                                wrongInput = true;
                                while (wrongInput) {
                                    String decision = IOcascadia.makeLowerCase(IOcascadia.takeInput());

                                    if (decision.equals("yes")) {

                                        System.out.println("Where would you like to place token " + token);
                                        int coordinate = IOcascadia.takeIntInput();
                                        int coordinate2 = IOcascadia.takeIntInput();
                                        while (coordinate <= 0 || coordinate >= 46 || coordinate2 <= 0 || coordinate2 >= 46) {
                                            System.out.println("Error placed tile out of bounds please try again");
                                            coordinate = IOcascadia.takeIntInput();
                                            coordinate2 = IOcascadia.takeIntInput();
                                        }
                                        players.get(playersTurn).placeToken(coordinate, coordinate2);
                                        players.get(playersTurn).printMap(helperIntToPrintMap);
                                        wrongInput = false;


                                    } else if (decision.equals("no")) {
                                        wrongInput = false;
                                    } else {
                                        System.out.println("expected a yes or no answer, please try again");
                                    }
                                }
                            }
                            */
                        }
                        else{
                            if(players.get(playersTurn).heldToken.equals(Wildlife.HAWK)){
                                playersHawkScores.get(playersTurn).strategy2(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if(players.get(playersTurn).heldToken.equals(Wildlife.BEAR)){
                                playersBearScores.get(playersTurn).strategy2(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if(players.get(playersTurn).heldToken.equals(Wildlife.ELK)){
                                playersElkScores.get(playersTurn).strategy2(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            } else if (players.get(playersTurn).heldToken.equals(Wildlife.FOX)){
                                foxPlacement.strategy2(players.get(playersTurn));
                            } else if(players.get(playersTurn).heldToken.equals(Wildlife.SALMON)){
                                playersSalmonScores.get(playersTurn).strategy2(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                            }
                        }
                    }
                    players.get(playersTurn).setIsFilledToFalse();
                }
                /*1System.out.println("Your turn has ended.\nDo you want to quit and end the game? (Yes/No)");
                boolean wrongInputGameQuit = true;
                while (wrongInputGameQuit) {
                    String quitGameDecision = IOcascadia.makeLowerCase(IOcascadia.takeInput());
                    if (quitGameDecision.equals("yes")) {
                        continueGame = false;
                        wrongInputGameQuit = false;
                    } else if (quitGameDecision.equals("no")) {
                        wrongInputGameQuit = false;

                    } else {
                        System.out.println("Wrong input please try again");
                    }
                }
                 */
                playersHawkScores.get(playersTurn).getIndexesForTokens(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                System.out.println(players.get(playersTurn).getName() +"'s points for hawks so far are: " + playersHawkScores.get(playersTurn).countScore());

                playersBearScores.get(playersTurn).getIndexesForTokens(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                System.out.println(players.get(playersTurn).getName() +"'s points for bear pairs so far are: " + playersBearScores.get(playersTurn).countScore());

                playersElkScores.get(playersTurn).getIndexesForTokens(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                System.out.println(players.get(playersTurn).getName() +"'s points for elk lines so far are: " + playersElkScores.get(playersTurn).countScore());

                playersSalmonScores.get(playersTurn).getIndexesForTokens(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap());
                System.out.println(players.get(playersTurn).getName() +"'s points for salmon runs so far are: " + playersSalmonScores.get(playersTurn).countScore());

                fox.countFoxes(players.get(playersTurn));
                System.out.println(players.get(playersTurn).getName() + "'s number of fox points so far are: " + fox.countScore(players.get(playersTurn)));

                System.out.println("\nSo far, the habitat score is the following:");
                players.get(playersTurn).habitatScore();

                playersTurn++;
                Thread.sleep(1000);
            }
        }
        System.out.println("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                The game has finished!
                """);
        for(int i=0;i<players.size();i++)
        {
            int[] scores = new int[5];
            int count =0;
            playersHawkScores.get(i).getIndexesForTokens(players.get(i).getPlayerBoard(), players.get(i).getMap());
            scores[count] = playersHawkScores.get(i).countScore();
            System.out.println(players.get(i).getName() +"'s points for hawks are: " + scores[count]);
            players.get(i).totalScore.add(scores[count]);
            count++;

            playersBearScores.get(i).getIndexesForTokens(players.get(i).getPlayerBoard(), players.get(i).getMap());
            scores[count] = playersBearScores.get(i).countScore();
            System.out.println(players.get(i).getName() +"'s points for bear pairs are: " + scores[count]);
            players.get(i).totalScore.add(scores[count]);
            count++;

            playersElkScores.get(i).getIndexesForTokens(players.get(i).getPlayerBoard(), players.get(i).getMap());
            scores[count] = playersElkScores.get(i).countScore();
            System.out.println(players.get(i).getName() +"'s points for elk lines are: " + scores[count]);
            players.get(i).totalScore.add(scores[count]);
            count++;

            playersSalmonScores.get(i).getIndexesForTokens(players.get(i).getPlayerBoard(), players.get(i).getMap());
            scores[count] = playersSalmonScores.get(i).countScore();
            System.out.println(players.get(i).getName() +"'s points for salmon runs are: " + scores[count]);
            players.get(i).totalScore.add(scores[count]);
            count++;

            fox.countFoxes(players.get(i));
            scores[count] = fox.countScore(players.get(i));
            System.out.println(players.get(i).getName() +"'s points for fox are: " +scores[count]  + "\n");
            players.get(i).totalScore.add(scores[count]);


        }



        playersTurn = 0;
        System.out.println("largest Habitats");
        String winnerForest="none";
        String winnerWetland="none";
        String winnerPrairie="none";
        String winnerMountain="none";
        String winnerRiver="none";
        // int maxForest = -1;
        // int maxWetland = -1;
        // int maxPrairie = -1;
        // int maxMountain = -1;
        // int maxRiver = -1;

        // while (playersTurn < numberOfPlayers) {
        //     if (players.get(playersTurn).getForestMax() > maxForest) {
        //         maxForest = players.get(playersTurn).getForestMax();
        //         winnerForest = players.get(playersTurn).getName();
        //     }
        //     else if(players.get(playersTurn).getForestMax() == maxForest){
        //         winnerForest = "none";
        //     }
        //     if (players.get(playersTurn).getMountainMax() > maxMountain) {
        //         maxMountain = players.get(playersTurn).getMountainMax();
        //         winnerMountain = players.get(playersTurn).getName();
        //     }
        //     else if(players.get(playersTurn).getMountainMax() == maxMountain){
        //         winnerMountain = "none";
        //     }
        //     if (players.get(playersTurn).getWetlandMax() > maxWetland) {
        //         maxWetland = players.get(playersTurn).getWetlandMax();
        //         winnerWetland = players.get(playersTurn).getName();
        //     }
        //     else if(players.get(playersTurn).getWetlandMax() == maxWetland){
        //         winnerWetland = "none";
        //     }
        //     if (players.get(playersTurn).getPrairieMax() > maxPrairie) {
        //         maxPrairie = players.get(playersTurn).getPrairieMax();
        //         winnerPrairie = players.get(playersTurn).getName();
        //     }
        //     else if(players.get(playersTurn).getPrairieMax() == maxPrairie){
        //         winnerPrairie = "none";
        //     }
        //     if (players.get(playersTurn).getRiverMax() > maxRiver) {
        //         maxRiver = players.get(playersTurn).getRiverMax();
        //         winnerRiver = players.get(playersTurn).getName();
        //     }
        //     else if(players.get(playersTurn).getRiverMax() == maxRiver){
        //         winnerRiver = "none";
        //     }

        //     playersTurn++;
        // }


        playersTurn = 0;
        while (playersTurn < numberOfPlayers) {
            System.out.println(players.get(playersTurn).getName() + "'s points for this game are:");

            //the ints elkRand, bearRand, etc would be used here to utilise the scorecard that was randomly selected
            //at the beggining of the main if we had implemented more than one scorecard. Because we have not had time,
            //the following lines only use scorecards A, as they are the only implemented ones

            /*
            HawkScoreCard_A hawkScore = new HawkScoreCard_A(players.get(playersTurn));
            hawkScore.getIndexes(players.get(playersTurn).getPlayerBoard());
            System.out.println("Points awarded for hawks: " + hawkScore.countScore());

            BearScoreCard_A bearScore = new BearScoreCard_A(players.get(playersTurn));
            bearScore.getIndexes(players.get(playersTurn).getPlayerBoard());
            System.out.println("Points awarded for bears: " + bearScore.countScore());

            ElkScoreCard_A elkScore = new ElkScoreCard_A(players.get(playersTurn));
            elkScore.getIndexes(players.get(playersTurn).getPlayerBoard());
            System.out.println("Points awarded for elks: " + elkScore.countScore());

            SalmonScoreCard_A salmonScore = new SalmonScoreCard_A(players.get(playersTurn));
            salmonScore.getIndexes(players.get(playersTurn).getPlayerBoard());
            System.out.println("Points awarded for salmon: " + salmonScore.countScore());

            FoxScoreCard_A foxScore = new FoxScoreCard_A(players.get(playersTurn));
            foxScore.getIndexes(players.get(playersTurn).getPlayerBoard());
            System.out.println("Points awarded for fox: " + foxScore.countScore());

             */

            int totalHabitatPoints;
            int forestPoints=0;
            if(Objects.equals(winnerForest, players.get(playersTurn).getName())){
                forestPoints=2;
            }
            int prairiePoints=0;
            if(Objects.equals(winnerPrairie, players.get(playersTurn).getName())){
                prairiePoints=2;
            }
            int wetlandPoints=0;
            if(Objects.equals(winnerWetland, players.get(playersTurn).getName())){
                wetlandPoints=2;
            }
            int mountainPoints=0;
            if(Objects.equals(winnerMountain, players.get(playersTurn).getName())){
                mountainPoints=2;
            }
            int riverPoints=0;
            if(Objects.equals(winnerRiver, players.get(playersTurn).getName())){
                riverPoints=2;
            }
            totalHabitatPoints=forestPoints+riverPoints+wetlandPoints+mountainPoints+prairiePoints;
            System.out.println("Points awarded for habitats: " + totalHabitatPoints + "\n");
            players.get(playersTurn).totalScore.add(totalHabitatPoints);


            playersTurn++;
        }
         getTotalScore();
        System.out.println("Thanks for playing!");
    }

    public static void setContinueGame(boolean cont) {
        continueGame = cont;
    }
    public static int getHelperIntToPrintMap(){
        return helperIntToPrintMap;
    }
    public static void getTotalScore(){
        for(int j=0;j<players.size();j++) {
            int sum=0;

            for (int i = 0; i < players.get(j).totalScore.size(); i++) {
                sum += players.get(j).totalScore.get(i);
            }
            sum+= players.get(j).getNatureTokenNumber();
            System.out.println("Points awarded for "+players.get(j).getName()+"'s "+" nature tokens "+players.get(j).getNatureTokenNumber()+"\n");
            System.out.println("Total score for "+players.get(j).getName()+" is "+ sum+"\n");
        }
    }

    public static boolean shouldBotCull(int playersTurn){
        ArrayList<TokenForPoints> pointsForEveryTypeOfToken = createArrayOfBestTokensToPlace(playersTurn);

        boolean shouldBotCull=true;
        for(int i=0 ; i<pointsForEveryTypeOfToken.size() ; i++){
            for(int j=0 ; j<TileDeck.getRiverTokens().length ; j++){
                if(pointsForEveryTypeOfToken.get(i).getTypeOfAnimal()==TileDeck.getRiverTokensIndex(j)){
                    return false;
                    //if we find the wildlife we are looking for, return false
                }
            }
        }

        System.out.println("The river has been culled");
        TileDeck.cullRiver(4);
        players.get(playersTurn).reduceNatureTokenNumberByOne();
        PairDisplay.showPairs();

        return shouldBotCull;
    }

    public static ArrayList<TokenForPoints> createArrayOfBestTokensToPlace(int playersTurn){
        ArrayList<TokenForPoints> pointsForEveryTypeOfToken = new ArrayList<>();
        pointsForEveryTypeOfToken.add(new TokenForPoints(playersHawkScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap()), Wildlife.HAWK));
        pointsForEveryTypeOfToken.add(new TokenForPoints(playersBearScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap()), Wildlife.BEAR));
        pointsForEveryTypeOfToken.add(new TokenForPoints(playersElkScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap()), Wildlife.ELK));
        pointsForEveryTypeOfToken.add(new TokenForPoints(playersSalmonScores.get(playersTurn).strategy3(players.get(playersTurn).getPlayerBoard(), players.get(playersTurn).getMap()), Wildlife.SALMON));
        A_FoxPlacement foxPlacement = new A_FoxPlacement(players.get(playersTurn));
        pointsForEveryTypeOfToken.add(new TokenForPoints(foxPlacement.getPotentialScore(players.get(playersTurn)), Wildlife.FOX));

        A_Elk.insertionSort(pointsForEveryTypeOfToken);

        for(int i=1; i<pointsForEveryTypeOfToken.size();i++){
            if(pointsForEveryTypeOfToken.get(i).getRiverPoints()!=pointsForEveryTypeOfToken.get(0).getRiverPoints()){
                pointsForEveryTypeOfToken.remove(i);
                i--;
            }
        }

        return pointsForEveryTypeOfToken;
    }
}