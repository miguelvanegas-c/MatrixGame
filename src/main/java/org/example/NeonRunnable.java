package org.example;


public class NeonRunnable implements Runnable, Player{
    private int xPosition;
    private int yPosition;
    private Board board;
    private boolean finish = false;
    private char representation;

    public NeonRunnable(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.representation = 'N';
    }
    public void run() {
        board.move(this);
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public int getXPosition(){
        return xPosition;
    }

    public int getYPosition(){
        return yPosition;
    }

    public void makeFinish() {
        finish = true;
    }

    public char getRepresentation() {
        return representation;
    }
}
