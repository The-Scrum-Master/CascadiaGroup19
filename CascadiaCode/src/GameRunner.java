import java.util.ArrayList;

public class GameRunner {
    private static ArrayList<Player> players= new ArrayList<Player>();
    private static int numberOfPlayers;
    public static void main(String[] args) {
        GameRunner game = new GameRunner();
        IOcascadia.welcomeMessage();
        IOcascadia.numberOfPlayers();
        IOcascadia.playerNames();
        IOcascadia.playerOrder();

        numberOfPlayers=IOcascadia.getParticipantsInt();
        for(int i=0;i<numberOfPlayers;i++){
            players.add(new Player(IOcascadia.playerNames.get(IOcascadia.order.get(i)), i));
        }

        boolean continueGame=true;
        int playersTurn=0;

        while(continueGame){
            if(playersTurn==numberOfPlayers){
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
    }
}
