package clubregistrationgame;

import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

import static java.lang.Math.abs;
//import java.awt.

public class Player {
    private double xPos;
    private double yPos;
    private double xPos2;
    private double yPos2;
    protected int width;
    protected int height;

    protected boolean upMoveToggle, downMoveToggle, leftMoveToggle, rightMoveToggle = false;

    protected double defaultMoveSpeed = 4.33;
    protected double xMoveVel = 0;
    protected double yMoveVel = 0;


    public Player(double xPos, double yPos, int width, int height) {
        this.xPos = xPos; // x of the top left corner (represents left side)
        this.yPos = yPos; // y of the top left corner (represents top)
        this.xPos2 = xPos + width; // x of the bottom right corner (represents right side)
        this.yPos2 = yPos + height; // y of the bottom right corner (represents bottom)
        this.width = width;
        this.height = height;
    }
    public void render(Graphics2D g) {
        g.setColor(new Color(152,4,52)); // WA color
        g.fillRect(intX(), intY(), width, height);

    }
    public void executeMovementAndCollision(GamePhysics gamePhysics, GameEnvironment gameEnvironment) {
        checkMovement();
        double[] dissectedMoveVectorAndCount = dissectMovement();
//        System.out.println(Arrays.toString(dissectedMoveVectorAndCount));
//        System.out.println(yMoveVel);
        double moveCount = dissectedMoveVectorAndCount[0];
        double moveXAmount = dissectedMoveVectorAndCount[1];
        double moveYAmount = dissectedMoveVectorAndCount[2];

        double nextXPos;
        double nextYPos;
        double nextXPos2;
        double nextYPos2;

        while(moveCount > 0) {
            if(moveCount < 1) {
                moveXAmount *= moveCount;
                moveYAmount *= moveCount;
            }
            nextXPos = (exactX() + moveXAmount);
            nextXPos2 = (exactX2() + moveXAmount);
            nextYPos = (exactY() + moveYAmount);
            nextYPos2 = (exactY2() + moveYAmount);

            boolean[] collided = checkCollision(gameEnvironment, nextXPos, nextXPos2, nextYPos, nextYPos2);
            if(collided[0]) {
                setXPos(intX());
                setX2Pos(intX2());
                xMoveVel = 0;
            } else {
                setXPos(nextXPos);
                setX2Pos(nextXPos2);
            }
            if(collided[1]) {
                setYPos(intY());
                setY2Pos(intY2());
                yMoveVel = 0;
            } else {
                setYPos(nextYPos);
                setY2Pos(nextYPos2);
            }

            moveCount -= 1;
        }
        gamePhysics.checkShiftEnvironment();
    }

    public void checkMovement() {
        // first reset values
        xMoveVel = 0;
        yMoveVel = 0;
        if(upMoveToggle) { // check if up key is pressed -> set velocity up
            yMoveVel += -defaultMoveSpeed;
        }
        if(downMoveToggle) { // check if down key is pressed -> set velocity down
            yMoveVel += defaultMoveSpeed;
        }
        if(leftMoveToggle) { // check if left key is pressed -> set velocity left
            xMoveVel += -defaultMoveSpeed;
        }
        if(rightMoveToggle) { // check if right key is pressed -> set velocity right
            xMoveVel += defaultMoveSpeed;
        }
    }

    public double[] dissectMovement() {
        double[] dissected = new double[3];
        if(abs(xMoveVel) >= abs(yMoveVel)) {
            dissected[0] = abs(xMoveVel);
            dissected[1] = xMoveVel/abs(xMoveVel);
            dissected[2] = yMoveVel/abs(xMoveVel);
        } else {
            dissected[0] = abs(yMoveVel);
            dissected[1] = xMoveVel/abs(yMoveVel);
            dissected[2] = yMoveVel/abs(yMoveVel);
        }


        return dissected;
    }

    public boolean[] checkCollision(GameEnvironment gameEnvironment, double nextX, double nextX2, double nextY, double nextY2) {
        boolean[] collisionDetected = new boolean[2];
        for(RectBlock block : gameEnvironment.getBlockList()) {
            if(block.hitbox.intersects(new Rectangle2D.Double(nextX, intY(), width, height))) {
//                setXPos(intX());
//                setX2Pos(intX2());
                collisionDetected[0] = true;
            }
            if(block.hitbox.intersects(new Rectangle2D.Double(intX(), nextY, width, height))) {
//                setYPos(intY());
//                setY2Pos(intY());
                collisionDetected[1] = true;
            }


        }
        return collisionDetected;
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

    public double exactX2() {
        return xPos2;
    }
    public double exactY2() {
        return yPos2;
    }
    public int intX2() {
        return (int)xPos2;
    }
    public int intY2() {
        return (int)yPos2;
    }

    public void setX2Pos(double newX2) {
        xPos2 = newX2;
    }
    public void setX2Pos(int newX2) {
        xPos2 = newX2;
    }
    public void setY2Pos(double newY2) {
        yPos2 = newY2;
    }
    public void setY2Pos(int newY2) {
        yPos2 = newY2;
    }
}
