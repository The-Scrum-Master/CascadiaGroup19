public class Main {
    public static void main(String[] args) {
        Tile test1 = new Tile(0);
        System.out.println(test1);
        for( int i = 0; i < test1.getHabitats().length;i++){
            System.out.println(test1.getHabitat(i));
        }
        for( int i = 0; i <  test1.getSlots().length;i++){
            System.out.println(test1.getSlot(i));
        }
    }
}