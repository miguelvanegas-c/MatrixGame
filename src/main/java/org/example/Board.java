package org.example;

public class Board {
    private char[][] matrix;
    private boolean timeToShow = false;
    private int columns;
    private int rows;
    private int phoneXPosition;
    private int phoneYPosition;

    public Board(Player[] players, int columns, int rows, int phoneXPosition, int phoneYPosition){
        this.columns = columns;
        this.rows = rows;
        this.phoneYPosition = phoneYPosition;
        this.phoneXPosition = phoneXPosition;
        createBoard();
        addPlayers(players);
        showBoard();
    }

    private void createBoard(){
        matrix = new char[columns][rows];
        for(int column = 0; column<columns;column++ ) {
            for(int row = 0; row<rows;row++ ) {
                matrix[column][row] = '*';
            }
        }

    }

    private void addPlayers(Player[] players){
        matrix[phoneXPosition][phoneYPosition] = 'T';
        for (Player a: players){
            matrix[a.getXPosition()][a.getYPosition()] = a.getRepresentation();
            a.setBoard(this);
        }
    }

    private void showBoard(){
        for(char[] row : matrix){
            System.out.print("|");
            for(char c : row){
                System.out.print(" "+ c);
            }
            System.out.print(" |\n");
        }
    }

    public synchronized void move(Player player){
        char representation = player.getRepresentation();
        int playerXPosition = player.getXPosition();
        int playerYPosition = player.getYPosition();
        switch (representation){
            case 'A': goForNeon(playerXPosition, playerYPosition);
        }
    }

    private void goForNeon(int playerXPosition, int playerYPosition){
        int xDistance =
    }

}
