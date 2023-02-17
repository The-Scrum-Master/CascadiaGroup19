import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IOcascadia {

    static private String participants;
    static private int participantsInt;
    static public ArrayList<String> playerNames= new ArrayList<String>();
    static public ArrayList<Integer> order= new ArrayList<Integer>();
    static Scanner in =new Scanner(System.in);

    public static String takeInput(){
        return in.nextLine();
    }
    public static int takeIntInput(){
        return in.nextInt();
    }

    public static String makeLowerCase(String str){
        return str.toLowerCase();
    }

    public static void welcomeMessage(){
        System.out.println("Welcome to Cascadia! Let's start the game!");
    }

    public static void numberOfPlayers(){
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

    public static void playerNames(){
        System.out.println("Enter player names: ");
        playerNames.add(takeInput());
        for(int i=1; i<participantsInt; i++){
            System.out.println("Enter next player's name: ");
            playerNames.add(takeInput());
        }
        System.out.print("Hello ");
        int i=0;
        while(i<participantsInt-1){
            System.out.print(playerNames.get(i));
            System.out.print(", ");
            i++;
        }
        System.out.print(playerNames.get(i));
        System.out.println("!\n");
    }

    public static void playerOrder(){
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

    public static void printPlayerNames(){
        for(int i=0; i<participantsInt; i++){
            System.out.print(playerNames.get(i));
            System.out.print(", ");
        }
    }

    public static void printPlayerNamesInOrder(){
        for(int i=0; i<participantsInt; i++){
            System.out.print(i+1 + ") ");
            System.out.println(playerNames.get(order.get(i)));
        }
    }

    public static int randomNumberGenerator(int upperBound){
        Random rand = new Random ();
        return rand.nextInt(upperBound);
    }

    public static ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    public static ArrayList<Integer> getOrder() {
        return order;
    }

    public static int getParticipantsInt() {
        return participantsInt;
    }

    public static void instructionsToChoosePair(){
        System.out.println("Choose a pair tile-token from 1 (left-most) to 4 (right-most), ask for a cull (5) or" +
                " use a nature token to cull again (6) or to choose one tile and one token that are not paired (7)");
    }
    public static boolean cullOption(){
        System.out.println("There is 3 of the same animal tokens in the river. \n " +
                "Would you like to cull? Enter yes and no.");
        String choice = takeInput();
        choice = choice.toLowerCase();
        while(!"yes".equals(choice) && !"no".equals(choice)){
            System.out.println("Your input was invalid, try again.");
            choice = takeInput();
            choice = choice.toLowerCase();
        }
        if(choice.equals("yes")){
            System.out.println("You have chosen to cull the river.");
            return true;
        }else{
            System.out.println("You have Not chosen to cull the river.");
            return false;
        }
    }
}
