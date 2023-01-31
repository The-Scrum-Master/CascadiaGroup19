import java.util.Scanner;

public class IO {

    public static String takeInput(){
        Scanner in =new Scanner(System.in);
        String input=in.nextLine();
        return makeLowerCase(input);
    }

    public static String makeLowerCase(String str){
        return str.toLowerCase();
    }

    public static void welcomeMessage(){
        System.out.println("Welcome to Cascadia! Let's start the game!");
    }

    public static void numberOfPlayers(){
        System.out.println("How many players are going to play? (between 2 and 4)");
        String participants=takeInput();
        int participantsInt=Integer.parseInt(participants);
        if(participantsInt<2 || participantsInt>4){
            System.out.println("Sorry, the number of players has to be between 2 and 4, try again");
            numberOfPlayers();
        }
        else{
            System.out.println(participants+ " players, great!");
        }
    }

    public static void main(String[] args) {
        numberOfPlayers();
    }
}
