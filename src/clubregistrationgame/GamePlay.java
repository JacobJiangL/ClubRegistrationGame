package clubregistrationgame;

import org.w3c.dom.Text;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePlay extends JPanel implements ActionListener, KeyListener, MouseListener {
    int mouseX;
    int mouseY;

    protected boolean processKeyEvents = true;

    protected GamePhysics gamePhysics;
    protected GameEnvironment gameEnvironment;
    protected Player player;

    protected TextInputBox fNameInputBox = new TextInputBox(150, 150, 200, 30, 0, -900);
    protected TextInputBox lNameInputBox = new TextInputBox(400, 150, 200, 30, 0, -900);
    protected TextInputBox emailFrontInputBox = new TextInputBox(700, 150, 300, 30, 0, -900);
//    protected TextInputBox emailBackInputBox  = new TextInputBox(1050, 150, 200, 30, 0, -900);;

    protected Button submitButton = new Button(650, 200, 170, 30, 0, -900, "Submit");

    protected final Timer timer;
    protected final int delay = 8;

    public GamePlay() {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        int spawnXOffset = 0;
        int spawnYOffset = 0;

        this.timer = new Timer(delay, this);
        this.player = new Player(705 + spawnXOffset, 435 + spawnYOffset, 30, 30);
        this.gameEnvironment = new GameEnvironment(player, "literally the only one");
        this.gamePhysics = new GamePhysics(this);

        timer.start();
    }

    public void paint(Graphics g) {
        gameEnvironment.render((Graphics2D)g);
        player.render((Graphics2D)g);

        fNameInputBox.render((Graphics2D)g);
        lNameInputBox.render((Graphics2D)g);
        emailFrontInputBox.render((Graphics2D)g);
//        emailBackInputBox.render((Graphics2D)g);
        submitButton.render((Graphics2D) g);

//        g.setColor(Color.RED);
//        g.drawString(String.valueOf(player.xMoveVel), 10, 10);
//        g.drawString(String.valueOf(player.yMoveVel), 10, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.executeMovementAndCollision(gamePhysics, gameEnvironment);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(!processKeyEvents) {
            if(fNameInputBox.focused) {
                fNameInputBox.type(e);
            } else if (lNameInputBox.focused) {
                lNameInputBox.type(e);
            } else if (emailFrontInputBox.focused) {
                emailFrontInputBox.type(e);
//            } else if (emailBackInputBox.focused) {
//                emailBackInputBox.type(e);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(processKeyEvents) {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                player.upMoveToggle = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                player.downMoveToggle = true;
            } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.leftMoveToggle = true;
            } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.rightMoveToggle = true;
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                gamePhysics.shiftEnvironment(-gameEnvironment.shiftedXAmount, -gameEnvironment.shiftedYAmount, false);
                player.setXPos(705);
                player.setX2Pos(705 + 30);
                player.setYPos(435);
                player.setY2Pos(435 + 30);

                fNameInputBox.resetText();
                lNameInputBox.resetText();
                emailFrontInputBox.resetText();
            }
        } else {
            player.upMoveToggle = player.downMoveToggle = player.leftMoveToggle = player.rightMoveToggle = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(processKeyEvents) {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                player.upMoveToggle = false;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                player.downMoveToggle = false;
            } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.leftMoveToggle = false;
            } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.rightMoveToggle = false;
            }
        } else {
            player.upMoveToggle = player.downMoveToggle = player.leftMoveToggle = player.rightMoveToggle = false;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        boolean focused;

        fNameInputBox.checkFocus(e.getX(), e.getY());
        lNameInputBox.checkFocus(e.getX(), e.getY());
        emailFrontInputBox.checkFocus(e.getX(), e.getY());
//        emailBackInputBox.checkFocus(e.getX(), e.getY());
        if(submitButton.checkPressed(e.getX(), e.getY())) {
            try {
                submitButton.submitInfo(fNameInputBox, lNameInputBox, emailFrontInputBox);
            } catch (IOException ex) {

            }
        }


        focused = (fNameInputBox.checkFocus(e.getX(), e.getY()) || lNameInputBox.checkFocus(e.getX(), e.getY()) ||
                emailFrontInputBox.checkFocus(e.getX(), e.getY())); //|| emailBackInputBox.checkFocus(e.getX(), e.getY()));

        processKeyEvents = !focused;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        submitButton.pressed = false;
//        gameEnvironment.addBlock(new RectBlock((double)mouseX, (double)mouseY, (double)e.getX(), (double)e.getY(), 0, 0));
//        System.out.println(mouseX + ", " + mouseY + ", " + e.getX() + ", " + e.getY() + ", " + gameEnvironment.shiftedXAmount + ", " + gameEnvironment.shiftedYAmount);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
