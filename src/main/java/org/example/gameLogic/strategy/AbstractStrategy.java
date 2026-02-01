package org.example.gameLogic.strategy;

import org.example.exception.MatrixException;
import org.example.gameLogic.Board;
import org.example.gameLogic.Player;

public abstract class AbstractStrategy implements StrategyInterface {
    protected Board board;
    private char[][] matrix;

    public AbstractStrategy(Board board) {
        this.board = board;
        this.matrix = board.getMatrix();
    }

    void calculateMove(int xDistance, int yDistance, int playerXPosition, int playerYPosition, char representation, Player player){
        int newYPosition = playerYPosition;
        int newXPosition = playerXPosition;
        if((Math.abs(xDistance) == 1 && yDistance == 0)||(Math.abs(yDistance) == 1 && xDistance == 0)){
            board.makeFinishGame();
            winningMessage(representation);
        }else if(Math.abs(xDistance) < Math.abs(yDistance)){
            newYPosition = calculateNewPosition(playerYPosition, yDistance);
        } else if (Math.abs(xDistance) >= Math.abs(yDistance)){
            newXPosition = calculateNewPosition(playerXPosition, xDistance);
        }
        updateBoard(playerXPosition, playerYPosition, newXPosition, newYPosition, representation, player);
    }

    private void winningMessage(char representation){
        if (representation == 'A'){
            System.out.println("Agents catch Neon");
        } else if (representation == 'N') {
            System.out.println("Neon catch Telephone");
        }
    }


    private int calculateNewPosition(int actualPosition, int distance){
        int newPosition = actualPosition;
        if(distance > 0){
            newPosition = newPosition + 1;
        } else if (distance < 0) {
            newPosition = newPosition - 1;
        }
        return newPosition;
    }


    private void updateBoard(int playerXPosition, int playerYPosition, int newXPosition, int newYPosition, char representation, Player player){
        try {
            validateMove(newXPosition, newYPosition);
            matrix[newXPosition][newYPosition] = representation;
            matrix[playerXPosition][playerYPosition] = '*';
            player.setXPosition(newXPosition);
            player.setYPosition(newYPosition);
            if(player.getRepresentation() == 'N'){
                board.setNeonXPosition(newXPosition);
                board.setNeonYPosition(newYPosition);
            }
        }catch (MatrixException e){

        }
    }

    private void validateMove(int newXPosition, int newYPosition) throws MatrixException{
        if(matrix[newXPosition][newYPosition] != '*'){
            throw new MatrixException(MatrixException.INVALID_MOVE);
        }
    }

}
