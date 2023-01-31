import java.util.Scanner;

public class ViewTile {
    public void starterTiles()
    {
        topTileForStarterTile();
        //////////
        System.out.print("   * * * * * * * * * * * * * * * *\n");
        System.out.print("   *             * *             *\n");
        System.out.print("   *             * *             *\n");
        System.out.print("   *             * *             *\n");
        //System.out.print(DisplayColour.RED + totalWidthForInitialTile() + DisplayColour.RESET + DisplayColour.BLUE + totalWidthForInitialTile() + DisplayColour.RESET);
        //System.out.print(totalWidthForInitialBottomTile()+totalWidthForInitialBottomTile());
        System.out.print("   * * * * * * * * * * * * * * * *\n");

    }
    public void topTileForStarterTile()
    {
        System.out.println("  "+ DisplayColour.RED + totalWidthForInitialUpperTile() + DisplayColour.RESET);
        System.out.println("           "+totalHeight());
        System.out.println("           "+totalHeight());
        System.out.println("           "+totalHeight());
        System.out.println("  "+ DisplayColour.RED + totalWidthForInitialUpperTile() + DisplayColour.RESET);
    }



    public String totalWidth(){
        return "* * * * * * * *";
    }
    public String totalHeight(){
        return "*             *";
    }
    public String totalWidthForInitialUpperTile(){
        return "         * * * * * * * *";
    }
    public String totalHeightForInitialUpperTile(){
        return "         *           *";
    }
    public String totalWidthForInitialBottomTile(){
        return DisplayColour.RED + "   * * * * * * * *" + DisplayColour.RESET;
        //return "\t* * * * * * * *";
    }

    public String makeSingleTile()
    {
        return totalWidth()+"\n"+totalHeight()+"\n"+totalHeight()+"\n"+totalHeight()+"\n"+totalWidth();
        /*System.out.println(DisplayColour.BLUE + totalWidth());
        System.out.println(totalHeight());
        System.out.println(totalHeight());
        System.out.println(totalHeight());
        System.out.println(totalWidth() + DisplayColour.RESET);*/
    }

    public int whiteSpaces(){
        System.out.println("how many white spaces?");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void finalPrint(){
        int whiteSpaces=whiteSpaces();

        for(int i=0; i<whiteSpaces; i++){
            System.out.print(" ");
        } System.out.println(DisplayColour.BLUE + totalWidth());

        for(int j=0; j<3; j++){
            for(int i=0; i<whiteSpaces; i++){
                System.out.print(" ");
            } System.out.println(totalHeight());
        }
        for(int i=0; i<whiteSpaces; i++){
            System.out.print(" ");
        } System.out.println(totalWidth() + DisplayColour.RESET);
    }
}
