public class TokenForPoints {
    private int cordX;
    private int cordY;
    private boolean valid=false;

    private boolean alreadyAccountedFor=false;

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
}
