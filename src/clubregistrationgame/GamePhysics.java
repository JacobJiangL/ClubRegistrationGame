package clubregistrationgame;

public class GamePhysics {

    private GamePlay gamePlay;

    private Player player;
    private GameEnvironment gameEnvironment;

    public GamePhysics(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
        this.player = gamePlay.player;
        this.gameEnvironment = gamePlay.gameEnvironment;
    }

    public void checkShiftEnvironment() {
        if(player.exactX2() < 0) { // right of player off-screen left side
            shiftEnvironment(1440, 0, true);
        } else if(player.exactX() > 1440) { // left of player off-screen right side
            shiftEnvironment(-1440, 0, true);
        }
        if(player.exactY2() < 0) { // bottom of player off-screen top
            shiftEnvironment(0, 900, true);
        } else if(player.exactY() > 900) { // top of player off-screen bottom
            shiftEnvironment(0, -900, true);
        }
    }

    public void shiftEnvironment(int xShift, int yShift, boolean playerShift) {
        for(RectBlock block : gameEnvironment.getBlockList()) {
            block.setXPos(block.exactX() + xShift);
            block.setYPos(block.exactY() + yShift);
            block.createHitbox();
        }
        if(playerShift) {
            player.setXPos(player.exactX() + xShift);
            player.setX2Pos(player.exactX2() + xShift);
            player.setYPos(player.exactY() + yShift);
            player.setY2Pos(player.exactY2() + yShift);
        }

        gamePlay.fNameInputBox.xPos += xShift;
        gamePlay.fNameInputBox.yPos += yShift;
        gamePlay.lNameInputBox.xPos += xShift;
        gamePlay.lNameInputBox.yPos += yShift;
        gamePlay.emailFrontInputBox.xPos += xShift;
        gamePlay.emailFrontInputBox.yPos += yShift;
//        gamePlay.emailBackInputBox.xPos += xShift;
//        gamePlay.emailBackInputBox.yPos += yShift;
        gamePlay.fNameInputBox.createHitbox();
        gamePlay.lNameInputBox.createHitbox();
        gamePlay.emailFrontInputBox.createHitbox();
//        gamePlay.emailBackInputBox.createHitbox();
        gamePlay.submitButton.xPos += xShift;
        gamePlay.submitButton.yPos += yShift;
        gamePlay.submitButton.createHitbox();

        gameEnvironment.shiftedXAmount += xShift;
        gameEnvironment.shiftedYAmount += yShift;
    }

}
