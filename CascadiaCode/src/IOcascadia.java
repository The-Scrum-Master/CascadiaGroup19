
/*Group: 19
*Team Name: Front Row
*Timi Salam- 2139203(Timisalam)
*Patrick Kelly-21204063(Patkelly17)
*Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
*/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IOcascadia {
    static final int numberOfBots=2;
    static private int participantsInt=numberOfBots;
    static public ArrayList<String> playerNames= new ArrayList<String>();
    static public ArrayList<Integer> order= new ArrayList<Integer>();


    public static String takeInput(){
        Scanner in =new Scanner(System.in);
        return in.nextLine().trim();
    }
    public static boolean onlyDigits(String str, int n)
    {
        // Traverse the string from
        // start to end
        for (int i = 0; i < n; i++) {

            // Check if character is
            // not a digit between 0-9
            // then return false
            if (str.charAt(i) < '0'|| str.charAt(i) > '9') {
                return false;
            }
        }
        // If we reach here, that means
        // all characters were digits.
        return true;
    }
    public static int takeIntInput(){
        Scanner in =new Scanner(System.in);
        String input=in.next();
        if(onlyDigits(input, input.length())){
            return Integer.parseInt(input);
        }
        else{
            System.out.println("Wrong input (expected digits), try again :)");
            return takeIntInput();
            }
    }

    public static String makeLowerCase(String str){
        return str.toLowerCase();
    }

    public static void welcomeMessage(){
        System.out.println("Welcome to Cascadia! Let's start the game!");
    }

    public static void numberOfPlayers(){
        System.out.println("How many players are going to play? (between 2 and 4)");
        participantsInt=takeIntInput();
        if(participantsInt<2 || participantsInt>4){
            System.out.println("Sorry, the number of players has to be between 2 and 4, try again");
            numberOfPlayers();
        }
        else{
            System.out.println(participantsInt+ " players, great!");
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

    /*public static int selectScoreCardSalmon(){
        int rand = randomNumberGenerator(1);    //the upper bound should be 3 if we had had time to implement scorecards B and C
        if(rand==0){
            SalmonScoreCard_A.explainCard();
        }
                                   THIS TWO OTHER OPTIONS WOULD BE UNCOMMENTED IF WE HAD HAD TIME TO IMPLEMENT SCORECARDS B AND C
        else if(rand==1){
            SalmonScoreCard_B.explainCard();
        }
        else{
            SalmonScoreCard_C.explainCard();
        }

        return rand;
    }*/

    /*public static int selectScoreCardFox(){
        int rand = randomNumberGenerator(1);    //the upper bound should be 3 if we had had time to implement scorecards B and C
        if(rand==0){
            FoxScoreCard_A.explainCard();
        }
                                   THIS TWO OTHER OPTIONS WOULD BE UNCOMMENTED IF WE HAD HAD TIME TO IMPLEMENT SCORECARDS B AND C
        else if(rand==1){
            FoxScoreCard_B.explainCard();
        }
        else{
            FoxScoreCard_C.explainCard();
        }

        return rand;
    }*/

    /*public static int selectScoreCardBear(){
        int rand = randomNumberGenerator(1);    //the upper bound should be 3 if we had had time to implement scorecards B and C
        if(rand==0){
            BearScoreCard_A.explainCard();
        }
                                   THIS TWO OTHER OPTIONS WOULD BE UNCOMMENTED IF WE HAD HAD TIME TO IMPLEMENT SCORECARDS B AND C
        else if(rand==1){
            BearScoreCard_B.explainCard();
        }
        else{
            BearScoreCard_C.explainCard();
        }

        return rand;
    }*/

    /*public static int selectScoreCardElk(){
        int rand = randomNumberGenerator(1);    //the upper bound should be 3 if we had had time to implement scorecards B and C
        if(rand==0){
            ElkScoreCard_A.explainCard();
        }
                                 THIS TWO OTHER OPTIONS WOULD BE UNCOMMENTED IF WE HAD HAD TIME TO IMPLEMENT SCORECARDS B AND C
        else if(rand==1){
            ElkScoreCard_B.explainCard();
        }
        else{
            ElkScoreCard_C.explainCard();
        }

        return rand;
    }/*

    /*public static int selectScoreCardHawk(){
        int rand = randomNumberGenerator(1);    //the upper bound should be 3 if we had had time to implement scorecards B and C
        if(rand==0){
            HawkScoreCard_A.explainCard();
        }
                                 THIS TWO OTHER OPTIONS WOULD BE UNCOMMENTED IF WE HAD HAD TIME TO IMPLEMENT SCORECARDS B AND C
        else if(rand==1){
            HawkScoreCard_B.explainCard();
        }
        else{
            HawkScoreCard_C.explainCard();
        }

        return rand;
    }*/

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
        System.out.println("Choose a pair tile-token from 1 (left-most) to 4 (right-most) or" +
                " use a nature token to cull again (5) or to choose one tile and one token that are not paired (6)");
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
            System.out.println("You have chosen Not to cull the river.");
            return false;
        }
    }

    public static void botsIntro(){
        System.out.println("How many players are going to play? (between 2 and 4)\n"+numberOfBots);
        System.out.println("Enter player 1 name: Bot_1\nEnter next player's name: Bot_2");
        playerNames.add("Bot_1"); playerNames.add("Bot_2");
    }
}