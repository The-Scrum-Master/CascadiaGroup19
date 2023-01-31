public class Main {
    public static void main(String[] args) {
        Tile test1 = new Tile(Tile.tileType.NORMAL);
        test1.populateTile();
        System.out.println(test1);
        for( int i = 0; i < test1.getHabitats().size();i++){
            System.out.println(test1.getHabitats().get(i));
        }
    }
}