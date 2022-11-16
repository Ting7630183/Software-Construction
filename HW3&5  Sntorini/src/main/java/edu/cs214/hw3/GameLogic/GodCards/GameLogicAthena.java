package edu.cs214.hw3.GameLogic.GodCards;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.Decorator.GameLogicDecorator;
import edu.cs214.hw3.GUI.Player;

public class GameLogicAthena extends GameLogicDecorator {
    boolean lastUp;

    public GameLogicAthena(DefaultGameLogic component) {
        super(component);
    }

    @Override
    public void move(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        int heightDifference = getHeightDifference(x, y, direction, board);
        if(heightDifference == 1){
            lastUp = true;
        }else{
            lastUp = false;
        }
        super.move(x, y, direction, board, currentPlayer, nextPlayer);
    }

    /**check height difference*/
    private int getHeightDifference(int x, int y, int[] direction, Board board){
        int currentHeight = board.getHeight(x, y);
        int nextHeight = board.getHeight(direction[0], direction[1]);
        return nextHeight-currentHeight;
    }



    @Override
    public boolean opponentCannotMove() {
        return this.lastUp;
    }
}
