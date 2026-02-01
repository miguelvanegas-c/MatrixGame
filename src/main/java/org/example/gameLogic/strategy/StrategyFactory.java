package org.example.gameLogic.strategy;

import org.example.gameLogic.Board;


public class StrategyFactory {

    public static StrategyInterface getStrategy(char representation, Board board) {
        if(representation == 'N'){
            return new NeonStrategy(board);
        }
        if(representation == 'A'){
            return new AgentStrategy(board);
        }
        return null;
    }
}
