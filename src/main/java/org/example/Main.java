package org.example;

import org.example.gameLogic.Board;
import org.example.gameLogic.Player;

import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Player[] players = new Player[4];
        players[3] = new Player(0, 8, 'N');
        players[0] = new Player(3,5, 'A');
        players[1] = new Player(7, 7, 'A');
        players[2] = new Player(9, 7, 'A');
        Board board = new Board(players, 10, 10, 9, 9);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for(Player player : players) {
            executor.submit(player);
        }
        executor.shutdown();
    }
}
