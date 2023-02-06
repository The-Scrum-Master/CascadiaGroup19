public class Player {
    private int order;
    private String name;

    public Player(String name, int order){
        this.name = name;
        this.order = order;
        Tile [][] playerBoard= new Tile[20][20];
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

}
