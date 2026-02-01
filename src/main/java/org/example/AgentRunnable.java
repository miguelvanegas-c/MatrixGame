package org.example;


public class AgentRunnable implements Runnable, Player{

    private char representation;
    private int xPosition;
    private int yPosition;
    private boolean finish = false;
    private Board board;

    public AgentRunnable(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.representation = 'A';
    }

    public void run() {
        board.move(this);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getXPosition(){
        return xPosition;
    }

    public int getYPosition(){
        return yPosition;
    }

    public void makeFinish(){
        finish = true;
    }

    public char getRepresentation() {
        return representation;
    }
}
