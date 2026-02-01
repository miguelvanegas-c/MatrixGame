package org.example.gameLogic.strategy;

import org.example.gameLogic.Board;
import org.example.gameLogic.Player;

public class AgentStrategy extends AbstractStrategy {

    public AgentStrategy(Board board) {
        super(board);
    }

    @Override
    public void makeStrategy(Player player) {
        int playerXPosition = player.getXPosition();
        int playerYPosition = player.getYPosition();
        int xDistance = board.getNeonXPosition()- playerXPosition;
        int yDistance = board.getNeonYPosition() - playerYPosition;
        calculateMove(xDistance, yDistance, playerXPosition, playerYPosition, 'A', player);
    }
}
