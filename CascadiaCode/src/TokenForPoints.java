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
    private boolean alreadyPairedUp=false;
    private int numberOfAdjacent=0;
    //this is number of adjacent tokens
    private boolean singleColorTile=false;
    private int length=0;
    private int riverPoints;
    private Wildlife typeOfAnimal;

    public TokenForPoints(int cordX, int cordY){
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public TokenForPoints(int riverPoints, Wildlife typeOfAnimal){
        this.riverPoints=riverPoints;
        this.typeOfAnimal=typeOfAnimal;
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

    public boolean getSingleColorTile() {return singleColorTile; }

    public void setSingleColorTile(boolean singleColorTile) {
        this.singleColorTile = singleColorTile;
    }

    public boolean getAlreadyPairedUp() {return alreadyPairedUp;}

    public void setAlreadyPairedUp(boolean alreadyPairedUp) {this.alreadyPairedUp = alreadyPairedUp;}

    public int getLength() { return length; }

    public void setLength(int length) { this.length = length; }

    public int getRiverPoints() {
        return riverPoints;
    }

    public void setRiverPoints(int riverPoints) {
        this.riverPoints = riverPoints;
    }

    public Wildlife getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(Wildlife typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }
}
