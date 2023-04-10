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

    public MaxCorridor(Habitat habitat) {
        this.habitatType = habitat;
        Xcordinates = new ArrayList<>();
        Ycordinates = new ArrayList<>();
    }

    public ArrayList<Integer> getXcordArrayList() {
        return Xcordinates;
    }

    public ArrayList<Integer> getYcordArrayList() {
        return Ycordinates;
    }

    public Habitat getHabitatType() {
        return habitatType;
    }
}
