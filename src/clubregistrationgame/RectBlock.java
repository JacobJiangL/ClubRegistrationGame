package clubregistrationgame;

public class RectBlock {
    private double xPos;
    private double yPos;

    public RectBlock() {

    }
    public double exactX() {
        return xPos;
    }
    public double exactY() {
        return yPos;
    }
    public int intX() {
        return (int)xPos;
    }
    public int intY() {
        return(int)yPos;
    }

    public void setxPos(double newX) {
        xPos = newX;
    }
    public void setxPos(int newX) {
        xPos = newX;
    }
    public void setyPos(double newY) {
        yPos = newY;
    }
    public void setyPos(int newY) {
        yPos = newY;
    }
}
