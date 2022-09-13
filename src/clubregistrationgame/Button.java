package clubregistrationgame;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.RandomAccessFile;
import java.io.IOException;

public class Button {

    protected int xPos;
    protected int yPos;

    protected int width;
    protected int height;

    protected Rectangle2D.Double hitbox;

    protected boolean pressed;
    private String label = "";

    public Button(int xPos, int yPos, int width, int height, int xOffset, int yOffset, String label) {
        this.xPos = xPos + xOffset;
        this.yPos = yPos + yOffset;
        this.width = width;
        this.height = height;
        this.label = label;
        createHitbox();
    }

    public boolean checkPressed(double xClick, double yClick) {
        this.pressed = this.hitbox.contains(new Point2D.Double(xClick, yClick));
        return this.pressed;
    }

    public void render(Graphics2D g) {
        if(this.pressed) {
            g.setColor(Color.WHITE);
            g.fillRect(xPos, yPos, width, height);
            g.setColor(new Color(215, 215, 215));
            g.fillRect(xPos+10, yPos+10, width-10, height-10);
        }
        else {
            g.setColor(new Color(215, 215, 215));
            g.fillRect(xPos, yPos, width, height);
            g.setColor(Color.WHITE);
            g.fillRect(xPos+10, yPos+10, width-10, height-10);
        }

        g.setFont(new Font("default", Font.PLAIN, height-6));
        g.fillRect(this.xPos, this.yPos, width, height);

        g.setColor(Color.BLACK);
        g.drawString(label, xPos + width/4, yPos + height/2 + (height-6)/2);
    }
    public void createHitbox() {
        this.hitbox = new Rectangle2D.Double(xPos, yPos, width, height);
    }

    protected void submitInfo(TextInputBox first, TextInputBox last, TextInputBox email) throws IOException {
        if(pressed && !first.getText().equals("") && !last.getText().equals("") && !email.getText().equals("")) {
            String text = "[" + first.getText() + ", " + last.getText() + ", " + email.getText() + "@worcesteracademy.org]\n";
            System.out.println(text);
            first.resetText();
            last.resetText();
            email.resetText();
            StringBuffer currentFileText = new StringBuffer();
            try {
                RandomAccessFile registered = new RandomAccessFile("registered.txt", "r");
                while(registered.getFilePointer() < registered.length()) {
                    currentFileText.append(registered.readLine()+System.lineSeparator());
                }

                registered = new RandomAccessFile("registered.txt", "rw");
                registered.write((currentFileText.toString() + text).getBytes());
                registered.close();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
