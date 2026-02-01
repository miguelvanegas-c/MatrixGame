package org.example;

public interface Player {

    void setBoard(Board board);
    int getXPosition();
    int getYPosition();
    void makeFinish();
    void run();
    char getRepresentation();
}
