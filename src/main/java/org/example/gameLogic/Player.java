
package org.example.gameLogic;


import lombok.Getter;
import lombok.Setter;
import org.example.exception.MatrixException;

public class Player implements Runnable{
    @Getter
    private char representation;
    @Setter
    @Getter
    private int xPosition;
    @Setter
    @Getter
    private int yPosition;
    @Setter
    private Board board;


    public Player(int xPosition, int yPosition, char representation) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.representation = representation;
    }

    @Override
    public void run() {
        try {
            while (!board.isFinishGame()) {
                board.move(this);
                board.waitForAllThePlayers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
