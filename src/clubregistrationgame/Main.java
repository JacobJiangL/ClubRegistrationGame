package clubregistrationgame;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePlay gamePlay = new GamePlay();

        frame.setBounds(0, 0, 1440, 900);
        frame.setTitle("Interactive Registration Form (Made with Java AWT and Swing!)");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
