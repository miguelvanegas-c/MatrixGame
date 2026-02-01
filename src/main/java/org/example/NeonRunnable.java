
package org.example;


import lombok.Getter;
import lombok.Setter;

public class NeonRunnable extends Player{
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


    public NeonRunnable(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.representation = 'N';
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