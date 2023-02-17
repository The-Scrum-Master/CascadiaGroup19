/**
 * This class is in charge of running the game. This is the main that will be run and will invoke other classe's functions
 */

import java.util.ArrayList;

public class GameRunner {
    private static final ArrayList<Player> players= new ArrayList<Player>(); //arrayList of Player class to store players
    public static PairDisplay p =new PairDisplay(); // to use the functions in the PairDisplay class to display the river

    public static void main(String[] args) {
        IOcascadia.welcomeMessage(); //output welcome message
        IOcascadia.numberOfPlayers(); //output message asking for number of players and storing that in a variable
        IOcascadia.playerNames(); //output messages asking for player names
        IOcascadia.playerOrder(); //output message informing of the order the players are going to follow
        TileDeck gameDeck = TileDeck.createNewDeck();//Creates the deck for this game (85 tiles shuffled in a stack)
        TileDeck.playRiver(gameDeck);
        int numberOfPlayers = IOcascadia.getParticipantsInt(); //stores number of players into the variable numberOfPlayers
        for(int i = 0; i< numberOfPlayers; i++){ //using a loop to add the players in order into the players ArrayList and creating
                                                 //an instance of Player for each
            players.add(new Player(IOcascadia.playerNames.get(IOcascadia.order.get(i)), i));
        }

        boolean continueGame=true; //boolean to stop the game
        int playersTurn=0; //int to rotate around players in order

        while(continueGame){ //main loop that runs the game until continueGame is changed to false
            if(playersTurn == numberOfPlayers){
                playersTurn=0;
            }
            else{
                System.out.println(players.get(playersTurn).getName()+ "'s turn:");

                //here, if a bool hasAlreadyStarted==false, then the starter tiles should be displayed
                //here, the 4 pairs habitats-tokens have to be displayed for a player to choose
                //then the player will choose and place his habitat, then his token
                //then the player will terminate his turn or the game (for the sake of terminating the program, in the future
                //the game will end when the players reach 20 turns

                boolean wrongCommand=true;
                while(wrongCommand){
                    if(!players.get(playersTurn).isFirstTurnPlayed()) {
                        players.get(playersTurn).generateInitialMap();
                    }
                    else{
                        System.out.println();
                        p.showPairs();
                        System.out.println();
                        IOcascadia.instructionsToChoosePair();
                        System.out.println();
                    }
                    System.out.println("Your turn has ended. What do you want to do, finish your turn (type next) or end the game (type quit)?");
                    String decision=IOcascadia.makeLowerCase(IOcascadia.takeInput());
                    switch(decision) {
                        case "next":
                            wrongCommand=false;
                            break;
                        case "quit":
                            continueGame=false;
                            wrongCommand=false;
                            break;
                        default:
                            System.out.println("You have entered an invalid command, try again");
                    }
                }

                playersTurn++;
            }
        }
        System.out.println("Thanks for playing!");
    }
}
