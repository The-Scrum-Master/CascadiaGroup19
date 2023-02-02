import java.util.Arrays;

public class TileGenerator {

    public String [][] tile= new String[4][4];

    public TileGenerator (){ //put the habitats and the placeholders as inputs into the constructor

    }

    public void generateBlankTile(){
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                tile[i][j] = "   ";
                System.out.print(tile[i][j]);
            }
            System.out.println(DisplayColour.RESET);
        }
    }

    public void tileUniqueColor(String color, char letter){
        for(int i=0;i< tile.length;i++) {
            for (int j = 0; j < tile.length; j++) {
                if(i==1 || i==2){
                    if(j==0 || j==3){
                        tile[i][j] = color + "   "+DisplayColour.RESET;
                    }
                    else{
                        tile[i][j] = " "+String.valueOf(letter)+" ";
                    }
                }
                else{
                    tile[i][j] = color + "   "+DisplayColour.RESET;
                }
                System.out.print(tile[i][j]);
            }
            System.out.println(DisplayColour.RESET);
        }
    }

    public void generateTile(String color, char letter){
        tileUniqueColor(color, letter);
    }


    public static void main(String[] args) {
        TileGenerator a =new TileGenerator();
        a.generateBlankTile();
        a.generateBlankTile();
        a.generateTile("\033[42m", 'F');
    }
}
