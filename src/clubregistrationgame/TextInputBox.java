package clubregistrationgame;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.KeyEvent;

public class TextInputBox {

    protected int xPos;
    protected int yPos;

    protected int width;
    protected int height;

    protected Rectangle2D.Double hitbox;

    protected boolean focused;
    private String text = "";

    public TextInputBox(int xPos, int yPos, int width, int height, int xOffset, int yOffset) {
        this.xPos = xPos + xOffset;
        this.yPos = yPos + yOffset;
        this.width = width;
        this.height = height;
        createHitbox();
    }

    public boolean checkFocus(double xClick, double yClick) {
        this.focused = this.hitbox.contains(new Point2D.Double(xClick, yClick));
        return this.focused;
    }

    public void type(KeyEvent e) {
        text = text + e.getKeyChar();
        if(e.getKeyCode() == KeyEvent.VK_DELETE || e.getExtendedKeyCode() == 8) {
            if(text.length() <= 1) {
                text = "";
            } else {
                text = text.substring(0, text.length() - 2);
            }
        }
    }

    public void render(Graphics2D g) {
        if(!this.focused) {g.setColor(Color.WHITE); }
        else {g.setColor(new Color(230, 230, 230)); }

        g.setFont(new Font("default", Font.PLAIN, height-6));
        g.fillRect(this.xPos, this.yPos, width, height);

        g.setColor(Color.BLACK);
        g.drawString(text, xPos + 2, yPos + height - 3);
    }
    public String getText() {
        return text;
    }
    public void resetText() {
        this.text = "";
    }
    public void createHitbox() {
        this.hitbox = new Rectangle2D.Double(xPos, yPos, width, height);
    }
}
