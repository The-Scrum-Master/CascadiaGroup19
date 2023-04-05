/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

import java.util.ArrayList;
import java.util.Objects;
public class GameRunner {
    private static final ArrayList<Player> players = new ArrayList<Player>();
    //arrayList of Player class to store players
    public static PairDisplay p = new PairDisplay();
    // to use the functions in the PairDisplay class to display the river
    public static boolean continueGame = true;

    public static void main(String[] args) throws InterruptedException {
        IOcascadia.welcomeMessage();
        //output welcome message
        System.out.println("\n");
        IOcascadia.selectScoreCardElk();
        IOcascadia.selectScoreCardBear();
        IOcascadia.selectScoreCardHawk();
        IOcascadia.selectScoreCardSalmon();
        IOcascadia.selectScoreCardFox();
        System.out.println("\n");
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
        }
        int playersTurn = 0;
        //int to rotate around players in order
        int helperIntToPrintMap = -1;
        int turnTheGameIsAt = 0;

        Thread.sleep(2000);

        while (turnTheGameIsAt <= 20 && continueGame) {
            //main loop that runs the game until 20 turns pass
            if (playersTurn == numberOfPlayers) {
                playersTurn = 0;
                turnTheGameIsAt++;
                helperIntToPrintMap++;
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
                    System.out.println(players.get(0).getName()+" map:");
                    players.get(0).map.fillMapWithAllowedTilePlacements();
                    players.get(0).printMap(helperIntToPrintMap);
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(players.get(1).getName()+ " map:");
                    players.get(1).map.fillMapWithAllowedTilePlacements();
                    players.get(1).printMap(helperIntToPrintMap);
                    System.out.println(players.get(playersTurn).getName() + "'s turn:\n");
                    players.get(playersTurn).printMap(helperIntToPrintMap);

                    PairDisplay.showPairs();

                    IOcascadia.instructionsToChoosePair();
                    int instructionsToChoosePairInput = players.get(playersTurn).chooseFromRiver() ;
                    System.out.println(instructionsToChoosePairInput);


                    if (instructionsToChoosePairInput <= 5 && instructionsToChoosePairInput >= 0) {
                        if (instructionsToChoosePairInput <= 3) {
                            players.get(playersTurn).pickPair(instructionsToChoosePairInput);
                        } else if (instructionsToChoosePairInput == 4) {
                            if (players.get(playersTurn).getNatureTokenNumber() <= 0) {
                                System.out.println("You don't have nature tokens to use, try again");
                            } else {
                                //cull again
                                System.out.println("The river has been culled");
                                TileDeck.cullRiver(4);
                                players.get(playersTurn).reduceNatureTokenNumberByOne();
                                PairDisplay.showPairs();
                            }

                        } else {
                            if (players.get(playersTurn).getNatureTokenNumber() <= 0) {
                                System.out.println("You don't have nature tokens to use, try again");
                            } else {
                                //pick one and one
                                players.get(playersTurn).splitPick();
                            }
                        }
                    }

                    System.out.println(players.get(playersTurn).heldTile);

                    TileGenerator heldTileGenerator = new TileGenerator(players.get(playersTurn).heldTile);
                    System.out.println();
                    heldTileGenerator.printTile();
                    String token = Wildlife.animalSymbol(players.get(playersTurn).heldToken);
                    System.out.println(token + "\n");

                    System.out.println("Would you like to rotate tiles(yes or no)");
                    boolean wrongInput = true;
                    while (wrongInput) {
                        String rotate = IOcascadia.makeLowerCase(IOcascadia.takeInput());
                        if (rotate.equals("yes")) {
                            players.get(playersTurn).heldTile.flipTile(players.get(playersTurn).heldTile);
                            TileGenerator g = new TileGenerator(players.get(playersTurn).heldTile);
                            g.generateFlipTile();
                            g.printTile();

                            players.get(playersTurn).printMap(helperIntToPrintMap);

                            wrongInput = false;
                        } else if (rotate.equals("no")) {
                            wrongInput = false;
                            players.get(playersTurn).printMap(helperIntToPrintMap);

                        } else {
                            System.out.println("Wrong input please try again");
                        }
                    }


                    /*System.out.println("Where would you like to place a tile?");
                    int x = IOcascadia.takeIntInput();
                    int y = IOcascadia.takeIntInput();
                    while (x <= 0 || x >= 46 || y <= 0 || y >= 46) {
                        System.out.println("Error placed tile out of bounds please try again");
                        x = IOcascadia.takeIntInput();
                        y = IOcascadia.takeIntInput();

                    }
                    //System.out.println(players.get(playersTurn).heldTile.getColour());
                    //System.out.println(players.get(playersTurn).heldTile.getColour2());
                    players.get(playersTurn).placeTile(x, y);
                    players.get(playersTurn).map.fillMapWithAllowedTilePlacements();

                     */

                    players.get(playersTurn).findBestHabitat();

                    //players.get(playersTurn).printMap(helperIntToPrintMap);

                    players.get(playersTurn).setHeldTile(null);


                    if (players.get(playersTurn).checkToken()) {
                    } else {
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
                playersTurn++;
            }
        }
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                "The game has finished!\n");


        playersTurn = 0;
        System.out.println("largest Habitats");
        String winnerForest="none";
        String winnerWetland="none";
        String winnerPrairie="none";
        String winnerMountain="none";
        String winnerRiver="none";
        int maxForest = -1;
        int maxWetland = -1;
        int maxPrairie = -1;
        int maxMountain = -1;
        int maxRiver = -1;

        while (playersTurn < numberOfPlayers) {
            if (players.get(playersTurn).getForestMax() > maxForest) {
                maxForest = players.get(playersTurn).getForestMax();
                winnerForest = players.get(playersTurn).getName();
            }
            else if(players.get(playersTurn).getForestMax() == maxForest){
                winnerForest = "none";
            }
            if (players.get(playersTurn).getMountainMax() > maxMountain) {
                maxMountain = players.get(playersTurn).getMountainMax();
                winnerMountain = players.get(playersTurn).getName();
            }
            else if(players.get(playersTurn).getMountainMax() == maxMountain){
                winnerMountain = "none";
            }
            if (players.get(playersTurn).getWetlandMax() > maxWetland) {
                maxWetland = players.get(playersTurn).getWetlandMax();
                winnerWetland = players.get(playersTurn).getName();
            }
            else if(players.get(playersTurn).getWetlandMax() == maxWetland){
                winnerWetland = "none";
            }
            if (players.get(playersTurn).getPrairieMax() > maxPrairie) {
                maxPrairie = players.get(playersTurn).getPrairieMax();
                winnerPrairie = players.get(playersTurn).getName();
            }
            else if(players.get(playersTurn).getPrairieMax() == maxPrairie){
                winnerPrairie = "none";
            }
            if (players.get(playersTurn).getRiverMax() > maxRiver) {
                maxRiver = players.get(playersTurn).getRiverMax();
                winnerRiver = players.get(playersTurn).getName();
            }
            else if(players.get(playersTurn).getRiverMax() == maxRiver){
                winnerRiver = "none";
            }

            playersTurn++;
        }


        playersTurn = 0;
        while (playersTurn < numberOfPlayers) {
            System.out.println(players.get(playersTurn).getName() + "'s points for this game are:");

            //the ints elkRand, bearRand, etc would be used here to utilise the scorecard that was randomly selected
            //at the beggining of the main if we had implemented more than one scorecard. Because we have not had time,
            //the following lines only use scorecards A, as they are the only implemented ones

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

            int totalHabitatPoints=0;
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

            playersTurn++;
        }

        System.out.println("Thanks for playing!");
    }

    public static void setContinueGame(boolean cont) {
        continueGame = cont;
    }
}