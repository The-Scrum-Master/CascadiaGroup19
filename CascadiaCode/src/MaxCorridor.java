import java.util.ArrayList;
/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */

public class MaxCorridor {
    private ArrayList<Integer> Xcordinates;
    private ArrayList<Integer> Ycordinates;
    private Habitat habitatType;
    private int index;

    public MaxCorridor(Habitat habitat, int index) {
        this.habitatType = habitat;
        this.index = index;
        Xcordinates = new ArrayList<>();
        Ycordinates = new ArrayList<>();
    }

    public void addCords(int x, int y) {
        Xcordinates.add(x);
        Ycordinates.add(y);
    }

    public ArrayList<Integer> getXcordArrayList() {
        return Xcordinates;
    }

    public void clearCords(){
        Xcordinates.clear();
        Ycordinates.clear();
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        if(Xcordinates.size() == Ycordinates.size()){
            return Xcordinates.size();
        } else{
            throw new IllegalStateException("Error: X and Y cordinates are not the same size");
        }
    }

    public ArrayList<Integer> getYcordArrayList() {
        return Ycordinates;
    }

    public Habitat getHabitatType() {
        return habitatType;
    }
}
