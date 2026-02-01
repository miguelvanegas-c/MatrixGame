package org.example.gameLogic.strategy;

import org.example.gameLogic.Board;
import org.example.gameLogic.Player;

public class NeonStrategy extends AbstractStrategy {

    public NeonStrategy(Board board) {
        super(board);
    }

    @Override
    public void makeStrategy(Player player) {
        int playerXPosition = player.getXPosition();
        int playerYPosition = player.getYPosition();
        int xDistance = board.getPhoneXPosition()- playerXPosition;
        int yDistance = board.getPhoneYPosition() - playerYPosition;
        calculateMove(xDistance, yDistance, playerXPosition, playerYPosition, 'N', player);
    }


}
