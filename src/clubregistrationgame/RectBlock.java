package clubregistrationgame;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class RectBlock {
    private double xPos;
    private double yPos;

    protected int width;
    protected int height;

//    protected int xOffset;
//    protected int yOffset;

    protected Rectangle hitbox;


    public RectBlock(double xPos, double yPos, int width, int height, int xOffset, int yOffset) {
        this.xPos = xPos + xOffset;
        this.yPos = yPos + yOffset;
        this.width = width;
        this.height = height;
        createHitbox();
    }
    public RectBlock(double xPos1, double yPos1, double xPos2, double yPos2, int xOffset, int yOffset) {
        this.xPos = xPos1 + xOffset;
        this.yPos = yPos1 + yOffset;
        this.width = (int)(xPos2 - xPos1);
        this.height = (int)(yPos2 - yPos1);
        createHitbox();
    }

    public void createHitbox() {
        this.hitbox = new Rectangle(intX(), intY(), width, height);
    }

    public void render(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(intX(), intY(), width, height);
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
        return (int)yPos;
    }

    public void setXPos(double newX) {
        xPos = newX;
    }
    public void setXPos(int newX) {
        xPos = newX;
    }
    public void setYPos(double newY) {
        yPos = newY;
    }
    public void setYPos(int newY) {
        yPos = newY;
    }
}
