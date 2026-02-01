package org.example;

public class Board {
    private char[][] matrix;
    private int columns;
    private int rows;
    private int phoneXPosition;
    private int phoneYPosition;
    private int neonXPosition;
    private int neonYPosition;
    private boolean finishGame = false;
    private int totalPlayers = 0;
    private int playersFinished = 0;

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
            if(a.getRepresentation() == 'c'){
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

    public synchronized void move(Player player){
        char representation = player.getRepresentation();
        int playerXPosition = player.getXPosition();
        int playerYPosition = player.getYPosition();
        switch (representation){
            case 'A': goForNeon(playerXPosition, playerYPosition, player);
                break;
            case 'N': gorForPhone(playerXPosition, playerYPosition, player);
                break;
        }
    }

    private void gorForPhone(int playerXPosition, int playerYPosition, Player player){
        int xDistance = phoneXPosition - playerXPosition;
        int yDistance = phoneYPosition - playerYPosition;
        calculateMove(xDistance, yDistance, playerXPosition, playerYPosition, 'N', player);
    }

    private void goForNeon(int playerXPosition, int playerYPosition, Player player){
        int xDistance = neonXPosition - playerXPosition;
        int yDistance = neonYPosition - playerYPosition;
        calculateMove(xDistance, yDistance, playerXPosition, playerYPosition, 'A', player);
    }

    private void calculateMove(int xDistance, int yDistance, int playerXPosition, int playerYPosition, char representation, Player player){
        int newYPosition = playerYPosition;
        int newXPosition = playerXPosition;
        if((Math.abs(xDistance) == 1 && yDistance == 0)||(Math.abs(yDistance) == 1 && xDistance == 0)){
            finishGame = true;
            winningMessage(representation);
        }
        if (Math.abs(xDistance) < Math.abs(yDistance)){
            newYPosition = calculateNewPosition(playerYPosition, yDistance);
        } else if (Math.abs(xDistance) >= Math.abs(yDistance)){
            newXPosition = calculateNewPosition(playerXPosition, xDistance);
        }
        updateBoard(playerXPosition, playerYPosition, newXPosition, newYPosition, representation, player);
    }

    private void winningMessage(char representation){
        if (representation == 'A'){
            System.out.println("Agents catch Neon");
            showBoard();
        } else if (representation == 'N') {
            System.out.println("Neon catch Telephone");
            showBoard();
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
        }catch (MatrixException e){

        }
    }

    private void validateMove(int newXPosition, int newYPosition) throws MatrixException{
        if(matrix[newXPosition][newYPosition] != '*'){
            throw new MatrixException(MatrixException.INVALID_MOVE);
        }
    }

    public boolean isFinishGame(){
        return finishGame;
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


}
