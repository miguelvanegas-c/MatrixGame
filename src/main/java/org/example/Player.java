package org.example;

import java.util.concurrent.CyclicBarrier;

public abstract class Player implements Runnable {

    abstract void setBoard(Board board);
    abstract int getXPosition();
    abstract int getYPosition();
    abstract void setXPosition(int xPosition);
    abstract void setYPosition(int yPosition);
    abstract char getRepresentation();
}
