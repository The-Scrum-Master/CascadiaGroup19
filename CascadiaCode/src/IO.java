import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IO {

    String participants;
    int participantsInt;
    ArrayList<String> playerNames= new ArrayList<String>();
    ArrayList<Integer> order= new ArrayList<Integer>();
    static Scanner in =new Scanner(System.in);

    public static String takeInput(){
        return in.nextLine();
    }

    public static String makeLowerCase(String str){
        return str.toLowerCase();
    }

    public void welcomeMessage(){
        System.out.println("Welcome to Cascadia! Let's start the game!");
    }

    public void numberOfPlayers(){
        System.out.println("How many players are going to play? (between 2 and 4)");
        participants=takeInput();
        participantsInt=Integer.parseInt(participants);
        if(participantsInt<2 || participantsInt>4){
            System.out.println("Sorry, the number of players has to be between 2 and 4, try again");
            numberOfPlayers();
        }
        else{
            System.out.println(participants+ " players, great!");
        }
    }

    public void playerNames(){
        System.out.println("Enter player names: ");
        playerNames.add(takeInput());
        for(int i=1; i<participantsInt; i++){
            System.out.println("Enter next player's name: ");
            playerNames.add(takeInput());
        }
        printPlayerNames();
    }

    public void playerOrder(){
        System.out.println("The player order will be as follows: ");
        for(int i=0; i<participantsInt; i++){
            boolean reGenerate=false;
            int j=randomNumberGenerator(participantsInt);
            if(order.isEmpty()){
                order.add(j);
            }
            else{
                for(int k=0; k<order.size(); k++){
                    if(j==order.get(k)){
                        reGenerate=true;
                        break;
                    }
                }
                if(reGenerate){
                    i--;
                }
                else{
                    order.add(j);
                }
            }
        }
        printPlayerNamesInOrder();
    }

    public void printPlayerNames(){
        for(int i=0; i<participantsInt; i++){
            System.out.println(playerNames.get(i));
        }
    }

    public void printPlayerNamesInOrder(){
        for(int i=0; i<participantsInt; i++){
            System.out.println(playerNames.get(order.get(i)));
        }
    }

    public int randomNumberGenerator(int upperBound){
        Random rand = new Random ();
        return rand.nextInt(upperBound);
    }

    public static void main(String[] args) {
        IO newGame = new IO();
        newGame.welcomeMessage();
        newGame.numberOfPlayers();
        newGame.playerNames();
        newGame.playerOrder();
    }
}
