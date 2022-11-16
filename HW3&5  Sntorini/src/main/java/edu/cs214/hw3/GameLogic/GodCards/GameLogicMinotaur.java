package edu.cs214.hw3.GameLogic.GodCards;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.Decorator.GameLogicDecorator;
import edu.cs214.hw3.GUI.Player;

public class GameLogicMinotaur extends GameLogicDecorator {
    public GameLogicMinotaur(DefaultGameLogic component) {
        super(component);
    }

    @Override
    public boolean checkMoveValidity(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        return super.checkMoveValidity(x, y, direction, board, currentPlayer, nextPlayer) || canForceOpponentWorker(x, y, direction, board, currentPlayer, nextPlayer);
    }

    /**check whether minotaur can force opponent worker to another place*/
    private boolean canForceOpponentWorker(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        Player player = board.getPlayer(direction[0], direction[1]);
        if(player != null) {
            if (!player.equals(currentPlayer)) {
                int directionX = direction[0]-x;
                int directionY = direction[1]-y;
                int xxx = direction[0] + directionX;
                int yyy = direction[1] + directionY;
                Player player1 = board.getPlayer(xxx, yyy);
                if (checkWithinBoard(xxx, yyy) && player1 == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**check whether within board*/
    private boolean checkWithinBoard(int x, int y){
        return (x >= 0 && x < 5 && y >= 0 && y < 5);
    }

    @Override
    public void move(int x, int y, int[] direction, Board board, Player currentPlayer,  Player nextPlayer) {
        if(canForceOpponentWorker(x, y, direction, board, currentPlayer, nextPlayer)) {
            int directionX = direction[0] - x;
            int directionY = direction[1] - y;
            int xx = direction[0] + directionX;
            int yy = direction[1] + directionY;
            int[] newDir = new int[]{xx, yy};
            super.move(direction[0], direction[1], newDir, board, nextPlayer, currentPlayer);
        }
        super.move(x, y, direction, board, currentPlayer, nextPlayer);
    }

}
