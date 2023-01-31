import java.util.ArrayList;
import java.util.Scanner;

public class IO {

    String participants;
    int participantsInt;
    ArrayList<String> playerNames;

    public static String takeInput(){
        Scanner in =new Scanner(System.in);
        String input=in.nextLine();
        return makeLowerCase(input);
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

    public static void main(String[] args) {
        IO newGame = new IO();
        newGame.welcomeMessage();
        newGame.numberOfPlayers();
    }
}
