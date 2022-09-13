package clubregistrationgame;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JTextField;
import java.util.ArrayList;

public class GameEnvironment {
    private ArrayList<RectBlock> blockList;

    protected Player player;

    protected int shiftedXAmount = 0;
    protected int shiftedYAmount = 0;

    public GameEnvironment(Player player, String environmentName) {
        this.player = player;
        load(environmentName);
    }
    public void load(String environmentName) {
        this.blockList = new ArrayList<>();

        switch(environmentName) {
            case "literally the only one":
                int xOffset = 0;
                int yOffset = 0;
                addBlock(new RectBlock(0d, 0d, 630d, 360d, xOffset, yOffset)); // top left block
                addBlock(new RectBlock(0d, 540d, 630d, 900d, xOffset, yOffset)); // bottom left block
                addBlock(new RectBlock(810d, 0d, 1440d, 360d, xOffset, yOffset)); // top right block
                addBlock(new RectBlock(810d, 540d, 1440d, 900d, xOffset, yOffset)); // bottom right block

                xOffset = 1440;
                yOffset = 0;
                addBlock(new RectBlock(0d, 0d, 1440, 30, xOffset, yOffset)); // ceiling block
                addBlock(new RectBlock(0d, 900d - 30, 1440, 30, xOffset, yOffset)); // floor block
                addBlock(new RectBlock(1440d - 30, 0d, 30, 900, xOffset, yOffset)); // right wall block
                addBlock(new RectBlock(1000d - 30, 0d, 30, 900, xOffset, yOffset)); // right code seperator block
                addBlock(new RectBlock(1000d - 30, 200d, 470, 30, xOffset, yOffset));
                addBlock(new RectBlock(0d, 0d, 30, 360, xOffset, yOffset)); // left top wall block
                addBlock(new RectBlock(0d, 540d, 30, 360, xOffset, yOffset)); // left bottom wall block

                xOffset = 0;
                yOffset = -900;
                addBlock(new RectBlock(0d, 0d, 1440, 30, xOffset, yOffset)); // ceiling block
                addBlock(new RectBlock(0d, 250d, 1440, 30, xOffset, yOffset)); // ceiling code seperator block
                addBlock(new RectBlock(0d, 900d - 30, 630, 30, xOffset, yOffset)); // floor left block
                addBlock(new RectBlock(810d, 900d - 30, 630, 30, xOffset, yOffset)); // floor right block
                addBlock(new RectBlock(1440d - 30, 0d, 30, 900, xOffset, yOffset)); // right wall block
                addBlock(new RectBlock(0d, 0d, 30, 900, xOffset, yOffset)); // left wall block

                xOffset = -1440;
                yOffset = 0;
                addBlock(new RectBlock(0d, 0d, 1440, 30, xOffset, yOffset)); // ceiling block
                addBlock(new RectBlock(0d, 900d - 30, 1440, 30, xOffset, yOffset)); // floor block
                addBlock(new RectBlock(1440d-30, 0d, 30, 360, xOffset, yOffset)); // right top wall block
                addBlock(new RectBlock(1440d-30, 540d, 30, 360, xOffset, yOffset)); // right bottom wall block
                addBlock(new RectBlock(0d, 0d, 30, 900, xOffset, yOffset)); // left wall block
                renderPlayground();
                break;
        }
    }
    public void render(Graphics2D g) {
        renderBackgrounds(g);
        for(RectBlock block : blockList) {
            block.render(g);
        }
        renderText(g);
    }
    public void renderText(Graphics2D g) {
        int xOffset = 0;
        int yOffset = 0;
        g.setFont(new Font("default", Font.BOLD, 55));
        g.setColor(Color.WHITE);
        g.drawString("Programming Club", 70 + xOffset + shiftedXAmount, 150 + yOffset + shiftedYAmount);
        g.setFont(new Font("default", Font.PLAIN, 35));
        g.drawString("Interactive Registration Form", 70 + xOffset + shiftedXAmount, 200 + yOffset + shiftedYAmount);

        g.setColor(Color.GRAY);
        g.setFont(new Font("default", Font.BOLD, 15));
        g.drawString("Controls:", 685 + xOffset + shiftedXAmount, 390 + yOffset + shiftedYAmount);
        g.drawString("WASD or Arrow Keys to Move", 605 + xOffset + shiftedXAmount, 420 + yOffset + shiftedYAmount);

        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 20));
        g.drawString("REGISTRATION", 840 + xOffset + shiftedXAmount, 30 + yOffset + shiftedYAmount);
        g.drawString("DEBUGGING", 1290 + xOffset + shiftedXAmount, 340 + yOffset + shiftedYAmount);
        g.drawString("PLAYGROUND", 30 + xOffset + shiftedXAmount, 340 + yOffset + shiftedYAmount);
        g.drawString("PLAYGROUND", 30 + xOffset + shiftedXAmount, 340 + yOffset + shiftedYAmount);
        g.drawString("LITERALLY", 840 + xOffset + shiftedXAmount, 830 + yOffset + shiftedYAmount);
        g.drawString("NOTHING", 840 + xOffset + shiftedXAmount, 870 + yOffset + shiftedYAmount);

        xOffset = 1440;
        yOffset = 0;
        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.PLAIN, 60));
        g.drawString("Debug Stats:", 1025 + xOffset + shiftedXAmount, 140 + yOffset + shiftedYAmount);

        g.setFont(new Font("default", Font.PLAIN, 15));
        g.setColor(Color.BLACK);
        g.drawString("Player X Pos: " + String.valueOf(player.exactX()),
                1040 + xOffset + shiftedXAmount, 300 + yOffset + shiftedYAmount);
        g.drawString("Player Y Pos: " + String.valueOf(player.exactY()),
                1240 + xOffset + shiftedXAmount, 300 + yOffset + shiftedYAmount);
        g.drawString("Player X Velocity: " + String.valueOf(player.xMoveVel),
                1040 + xOffset + shiftedXAmount, 365 + yOffset + shiftedYAmount);
        g.drawString("Player Y Velocity: " + String.valueOf(player.yMoveVel),
                1240 + xOffset + shiftedXAmount, 365 + yOffset + shiftedYAmount);

        g.drawString("Move Up Key Pressed: " + String.valueOf(player.upMoveToggle),
                1100 + xOffset + shiftedXAmount, 430 + yOffset + shiftedYAmount);
        g.drawString("Move Down Key Pressed: " + String.valueOf(player.downMoveToggle),
                1100 + xOffset + shiftedXAmount, 495 + yOffset + shiftedYAmount);
        g.drawString("Move Left Key Pressed: " + String.valueOf(player.leftMoveToggle),
                1100 + xOffset + shiftedXAmount, 560 + yOffset + shiftedYAmount);
        g.drawString("Move Right Key Pressed: " + String.valueOf(player.rightMoveToggle),
                1100 + xOffset + shiftedXAmount, 625 + yOffset + shiftedYAmount);

        g.drawString("Player Width: " + String.valueOf(player.width),
                1040 + xOffset + shiftedXAmount, 690 + yOffset + shiftedYAmount);
        g.drawString("Player Height: " + String.valueOf(player.height),
                1240 + xOffset + shiftedXAmount, 690 + yOffset + shiftedYAmount);
        g.drawString("Environment X Shift: " + String.valueOf(shiftedXAmount),
                1100 + xOffset + shiftedXAmount, 755 + yOffset + shiftedYAmount);
        g.drawString("Environment Y Shift: " + String.valueOf(shiftedYAmount),
                1100 + xOffset + shiftedXAmount, 820 + yOffset + shiftedYAmount);

        xOffset = 0;
        yOffset = -900;
        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 40));
        g.drawString("Registration:", 600 + xOffset + shiftedXAmount, 100 + yOffset + shiftedYAmount);
        g.setFont(new Font("default", Font.BOLD, 15));
        g.drawString("First Name:", 150 + xOffset + shiftedXAmount, 140 + yOffset + shiftedYAmount);
        g.drawString("Last Name:", 400 + xOffset + shiftedXAmount, 140 + yOffset + shiftedYAmount);
        g.drawString("Email:", 700 + xOffset + shiftedXAmount, 140 + yOffset + shiftedYAmount);
        g.setFont(new Font("default", Font.PLAIN, 26));
        g.drawString("@worcesteracademy.org", 1002 + xOffset + shiftedXAmount, 170 + yOffset + shiftedYAmount);
    }
    public void renderBackgrounds(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        int xOffset = 1440;
        int yOffset = 0;
        g.fillRect(1000 + xOffset + shiftedXAmount, 0 + yOffset + shiftedYAmount, 440, 900);
        xOffset = 0;
        yOffset = -900;
        g.fillRect(0 + xOffset + shiftedXAmount, 0 + yOffset + shiftedYAmount, 1440, 250);
    }

    public void renderPlayground() {
        addBlock(new RectBlock(572, 269, 760d, 307, -1440, 0));addBlock(new RectBlock(573, 361, 751d, 396, -1440, 0));addBlock(new RectBlock(363, 266, 518d, 304, -1440, 0));
        addBlock(new RectBlock(364, 271, 397d, 505, -1440, 0));addBlock(new RectBlock(366, 477, 743d, 512, -1440, 0));addBlock(new RectBlock(436, 354, 484d, 431, -1440, 0));
//        addBlock(new RectBlock(436, 357, 749d, 396, -1440, 0));addBlock(new RectBlock(902, 146, 930d, 173, -1440, 0));addBlock(new RectBlock(966, 149, 984d, 168, -1440, 0));
//        addBlock(new RectBlock(1023, 145, 1053d, 166, -1440, 0));addBlock(new RectBlock(1095, 142, 1129d, 157, -1440, 0));addBlock(new RectBlock(1170, 125, 1199d, 135, -1440, 0));
//        addBlock(new RectBlock(1208, 91, 1249d, 106, -1440, 0));addBlock(new RectBlock(1241, 62, 1277d, 83, -1440, 0));addBlock(new RectBlock(1260, 37, 1290d, 57, -1440, 0));
//        addBlock(new RectBlock(944, 198, 961d, 219, -1440, 0));addBlock(new RectBlock(1006, 197, 1020d, 211, -1440, 0));addBlock(new RectBlock(1070, 188, 1084d, 202, -1440, 0));
//        addBlock(new RectBlock(1144, 167, 1172d, 181, -1440, 0));addBlock(new RectBlock(520, 414, 586d, 417, -1440, 0));addBlock(new RectBlock(627, 414, 695d, 417, -1440, 0));
        addBlock(new RectBlock(381, 663, 420d, 872, -1440, 0));addBlock(new RectBlock(484, 509, 524d, 744, -1440, 0));addBlock(new RectBlock(581, 692, 618d, 883, -1440, 0));
        addBlock(new RectBlock(579, 504, 617d, 629, -1440, 0));addBlock(new RectBlock(688, 507, 732d, 784, -1440, 0));addBlock(new RectBlock(380, 504, 414d, 564, -1440, 0));
        addBlock(new RectBlock(781, 599, 822d, 872, -1440, 0));addBlock(new RectBlock(435, 354, 750d, 398, -1440, 0));addBlock(new RectBlock(568, 23, 599d, 267, -1440, 0));
        addBlock(new RectBlock(569, 265, 596d, 307, -1440, 0));addBlock(new RectBlock(386, 144, 421d, 185, -1440, 0));addBlock(new RectBlock(299, 54, 339d, 111, -1440, 0));
        addBlock(new RectBlock(212, 147, 257d, 192, -1440, 0));addBlock(new RectBlock(122, 66, 169d, 120, -1440, 0));addBlock(new RectBlock(81, 188, 451d, 207, -1440, 0));
        addBlock(new RectBlock(388, 180, 420d, 199, -1440, 0));addBlock(new RectBlock(124, 335, 268d, 545, -1440, 0));addBlock(new RectBlock(23, 714, 112d, 867, -1440, 0));
        addBlock(new RectBlock(89, 758, 185d, 869, -1440, 0));addBlock(new RectBlock(172, 797, 261d, 888, -1440, 0));addBlock(new RectBlock(240, 835, 312d, 888, -1440, 0));
//        addBlock(new RectBlock(948, 498, 960d, 507, -1440, 0));addBlock(new RectBlock(977, 499, 988d, 503, -1440, 0));addBlock(new RectBlock(1002, 501, 1020d, 509, -1440, 0));
//        addBlock(new RectBlock(1031, 502, 1044d, 510, -1440, 0));addBlock(new RectBlock(996, 566, 1011d, 577, -1440, 0));addBlock(new RectBlock(1010, 606, 1020d, 619, -1440, 0));
//        addBlock(new RectBlock(1048, 622, 1057d, 631, -1440, 0));addBlock(new RectBlock(1099, 565, 1110d, 572, -1440, 0));addBlock(new RectBlock(1049, 549, 1061d, 564, -1440, 0));
//        addBlock(new RectBlock(954, 603, 962d, 614, -1440, 0));addBlock(new RectBlock(917, 668, 933d, 680, -1440, 0));addBlock(new RectBlock(915, 549, 926, 561d, -1440, 0));
//        addBlock(new RectBlock(1019, 682, 1032d, 691, -1440, 0));addBlock(new RectBlock(1092, 647, 1096d, 653, -1440, 0));addBlock(new RectBlock(994, 698, 998, 706d, -1440, 0));
//        addBlock(new RectBlock(900, 741, 910d, 748, -1440, 0));addBlock(new RectBlock(948, 715, 965d, 734, -1440, 0));addBlock(new RectBlock(1050, 705, 1070, 717d, -1440, 0));
//        addBlock(new RectBlock(1172, 610, 1181d, 616, -1440, 0));addBlock(new RectBlock(1098, 604, 1108d, 614, -1440, 0));addBlock(new RectBlock(1148, 506, 1164, 531d, -1440, 0));
//        addBlock(new RectBlock(1099, 501, 1111d, 517, -1440, 0));addBlock(new RectBlock(1131, 678, 1135d, 688, -1440, 0));
        addBlock(new RectBlock(648, 137, 769, 178d, -1440, 0));
        addBlock(new RectBlock(1198, 724, 1439d, 764, -1440, 0));addBlock(new RectBlock(1199, 756, 1229d, 806, -1440, 0));addBlock(new RectBlock(1347, 607, 1425, 740d, -1440, 0));
        addBlock(new RectBlock(1303, 638, 1355d, 737, -1440, 0));addBlock(new RectBlock(1257, 671, 1317d, 730, -1440, 0));addBlock(new RectBlock(1219, 695, 1266, 737d, -1440, 0));
        addBlock(new RectBlock(1172, 738, 1210d, 763, -1440, 0));addBlock(new RectBlock(1212, 803, 1217d, 814, -1440, 0));addBlock(new RectBlock(1317, 827, 1374, 867d, -1440, 0));
        addBlock(new RectBlock(1325, 861, 1363d, 887, -1440, 0));addBlock(new RectBlock(1351, 858, 1366d, 884, -1440, 0));addBlock(new RectBlock(1280, 857, 1290, 871d, -1440, 0));
        addBlock(new RectBlock(1272, 852, 1296d, 862, -1440, 0));addBlock(new RectBlock(1328, 625, 1364d, 654, -1440, 0));addBlock(new RectBlock(1279, 655, 1297, 665d, -1440, 0));
        addBlock(new RectBlock(1280, 657, 1331d, 691, -1440, 0));addBlock(new RectBlock(1277, 655, 1378d, 711, -1440, 0));addBlock(new RectBlock(1236, 684, 1304, 708d, -1440, 0));
        addBlock(new RectBlock(1200, 709, 1251d, 733, -1440, 0));addBlock(new RectBlock(1180, 722, 1240d, 753, -1440, 0));addBlock(new RectBlock(1153, 746, 1185, 763d, -1440, 0));
        addBlock(new RectBlock(1372, 592, 1439d, 636, -1440, 0));addBlock(new RectBlock(1395, 577, 1439d, 626, -1440, 0));addBlock(new RectBlock(3, 836, 242, 897d, -1440, 0));
    }

    public ArrayList<RectBlock> getBlockList() {
        return blockList;
    }

    public void addBlock(RectBlock block) {
        blockList.add(block);
    }
}
