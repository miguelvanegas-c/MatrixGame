package org.example.gameLogic;

import lombok.Getter;
import lombok.Setter;
import org.example.exception.MatrixException;
import org.example.gameLogic.strategy.StrategyFactory;
import org.example.gameLogic.strategy.StrategyInterface;

public class Board {
    @Getter
    private char[][] matrix;
    private final int columns;
    private final int rows;
    @Getter
    private int phoneXPosition;
    @Getter
    private int phoneYPosition;
    @Getter
    @Setter
    private int neonXPosition;
    @Getter
    @Setter
    private int neonYPosition;
    @Getter
    private boolean finishGame = false;
    @Getter
    private int totalPlayers;
    @Getter
    private int playersFinished;

    public Board(Player[] players, int columns, int rows, int phoneXPosition, int phoneYPosition) {
        this.columns = columns;
        this.rows = rows;
        this.phoneYPosition = phoneYPosition;
        this.phoneXPosition = phoneXPosition;
        totalPlayers = players.length;
        createBoard();
        addPlayers(players);
        showBoard();
        System.out.println("Juego creado con exito");
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
            if(a.getRepresentation() == 'N'){
                neonXPosition = a.getXPosition();
                neonYPosition = a.getYPosition();
            }
        }
    }

    public void showBoard(){
        for(char[] row : matrix){
            System.out.print("|");
            for(char c : row){
                System.out.print(" "+ c);
            }
            System.out.print(" |\n");
        }
    }

    public synchronized void move(Player player) throws MatrixException{
        char representation = player.getRepresentation();
        StrategyInterface strategy = StrategyFactory.getStrategy(representation, this);
        if(strategy == null){
            throw new MatrixException(MatrixException.UNEXPECTED_ERROR);
        }
        strategy.makeStrategy(player);
    }


    public synchronized void waitForAllThePlayers() throws InterruptedException {
        playersFinished++;
        if(playersFinished < totalPlayers){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else {
            playersFinished = 0;
            showBoard();
            System.out.println("All players finished");
            Thread.sleep(5000);
            notifyAll();
        }
    }

    public void makeFinishGame(){
        finishGame = true;
    }


}
