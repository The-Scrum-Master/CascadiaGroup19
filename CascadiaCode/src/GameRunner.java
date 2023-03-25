/**
 * This class is in charge of running the game. This is the main that will be run and will invoke other classe's functions
 */

import java.util.ArrayList;

public class GameRunner {
    private static final ArrayList<Player> players= new ArrayList<Player>(); //arrayList of Player class to store players
    public static PairDisplay p =new PairDisplay(); // to use the functions in the PairDisplay class to display the river
    public static boolean continueGame=true;
    public static void main(String[] args) {
        IOcascadia.welcomeMessage(); //output welcome message
        IOcascadia.numberOfPlayers(); //output message asking for number of players and storing that in a variable
        IOcascadia.playerNames(); //output messages asking for player names
        IOcascadia.playerOrder(); //output message informing of the order the players are going to follow
        TileDeck.createDeck();//Creates the deck for this game (85 tiles shuffled in a stack)
        TileDeck.playRiver();
        int numberOfPlayers = IOcascadia.getParticipantsInt(); //stores number of players into the variable numberOfPlayers
        for(int i = 0; i< numberOfPlayers; i++){ //using a loop to add the players in order into the players ArrayList and creating
            //an instance of Player for each
            players.add(new Player(IOcascadia.playerNames.get(IOcascadia.order.get(i)), i));
        }
        int playersTurn=0; //int to rotate around players in order
        int helperIntToPrintMap=-1;
        int turnTheGameIsAt=0;

        while(turnTheGameIsAt<=1 && continueGame){ //main loop that runs the game until 20 turns pass
            if(playersTurn == numberOfPlayers){
                playersTurn=0;
                turnTheGameIsAt++;
                helperIntToPrintMap++;
            }
            else {
                System.out.println(players.get(playersTurn).getName() + "'s turn:");

                //here, if a bool hasAlreadyStarted==false, then the starter tiles should be displayed
                //here, the 4 pairs habitats-tokens have to be displayed for a player to choose
                //then the player will choose and place his habitat, then his token
                //then the player will terminate his turn or the game (for the sake of terminating the program, in the future
                //the game will end when the players reach 20 turns


                if (!players.get(playersTurn).isFirstTurnPlayed()) {
                    players.get(playersTurn).generateInitialMap();
                    System.out.println("These are your starter tiles!!!");
                } else {
                    players.get(playersTurn).map.fillMapWithAllowedTilePlacements();
                    players.get(playersTurn).printMap(helperIntToPrintMap);

                    PairDisplay.showPairs();
                    boolean continueAskingForInput = true;
                    while (continueAskingForInput) {
                        IOcascadia.instructionsToChoosePair();
                        int instructionsToChoosePairInput = IOcascadia.takeIntInput() - 1;
                        if (instructionsToChoosePairInput <= 5 && instructionsToChoosePairInput >= 0) {
                            if (instructionsToChoosePairInput <= 3) {
                                players.get(playersTurn).pickPair(instructionsToChoosePairInput);
                                continueAskingForInput = false;
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
                                    continueAskingForInput = false;
                                }
                            }
                        } else {
                            System.out.println("Sorry, the input should be between 1 and 6, try again");
                        }
                    }


                    // System.out.println("pre-flipped"+players.get(playersTurn).heldTile.getColour()+ players.get(playersTurn).heldTile.getColour2() );
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
                            //System.out.println("post-flipped"+players.get(playersTurn).heldTile.getColour()+ players.get(playersTurn).heldTile.getColour2() );

                            players.get(playersTurn).printMap(helperIntToPrintMap);

                            wrongInput = false;
                        } else if (rotate.equals("no")) {
                            wrongInput = false;
                            players.get(playersTurn).printMap(helperIntToPrintMap);

                        } else {
                            System.out.println("Wrong input please try again");
                        }
                    }


                    System.out.println("Where would you like to place a tile?");
                    int x = IOcascadia.takeIntInput();
                    int y = IOcascadia.takeIntInput();
                    //System.out.println(players.get(playersTurn).heldTile.getColour());
                    //System.out.println(players.get(playersTurn).heldTile.getColour2());
                    players.get(playersTurn).placeTile(x, y);
                    players.get(playersTurn).map.fillMapWithAllowedTilePlacements();

                    players.get(playersTurn).printMap(helperIntToPrintMap);


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
                System.out.println("Your turn has ended.");
                playersTurn++;
            }
        }

        playersTurn=0;
        while(playersTurn<numberOfPlayers){
            System.out.println(players.get(playersTurn).getName() + "'s points for this game are:");

            HawkScoreCard_A hawkScore=new HawkScoreCard_A(players.get(playersTurn));
            hawkScore.getIndexes(players.get(playersTurn).getPlayerBoard());
            System.out.println("Points awarded for hawks: " + hawkScore.countScore());

            BearScoreCard_A bearScore=new BearScoreCard_A(players.get(playersTurn));
            bearScore.getIndexes(players.get(playersTurn).getPlayerBoard());
            System.out.println("Points awarded for bears: " + bearScore.countScore());



            playersTurn++;
        }

        System.out.println("Thanks for playing!");
    }

    public static void setContinueGame(boolean cont) {
        continueGame = cont;
    }
}