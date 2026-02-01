package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Player[] players = new Player[4];
        players[3] = new NeonRunnable(1, 1);
        players[0] = new AgentRunnable(3,5);
        players[1] = new AgentRunnable(7, 7);
        players[2] = new AgentRunnable(9, 7);
        Board board = new Board(players, 10, 10, 9, 9);

    }
}
