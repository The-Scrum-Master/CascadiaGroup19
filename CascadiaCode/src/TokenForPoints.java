/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public class TokenForPoints {
    private int cordX;
    private int cordY;
    private boolean valid=false;
    private boolean alreadyAccountedFor=false;
    private int numberOfAdjacent=0;
    //private int pointsForPlaceholder;
    private boolean singleColorTile=false;

    public TokenForPoints(int cordX, int cordY){
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public int getCordX() {
        return cordX;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean getAlreadyAccountedFor() {
        return alreadyAccountedFor;
    }

    public void setAlreadyAccountedFor(boolean alreadyAccountedFor) {
        this.alreadyAccountedFor = alreadyAccountedFor;
    }

    public int getNumberOfAdjacent() {
        return numberOfAdjacent;
    }

    public void setNumberOfAdjacent(int numberOfAdjacent) {
        this.numberOfAdjacent = numberOfAdjacent;
    }

    public boolean getSingleColorTile() {
        return singleColorTile;
    }

    public void setSingleColorTile(boolean singleColorTile) {
        this.singleColorTile = singleColorTile;
    }

    /*public int getPointsForPlaceholder() {
        return pointsForPlaceholder;
    }

    public void setPointsForPlaceholder(int pointsForPlaceholder) {
        this.pointsForPlaceholder = pointsForPlaceholder;
    }

     */
}
