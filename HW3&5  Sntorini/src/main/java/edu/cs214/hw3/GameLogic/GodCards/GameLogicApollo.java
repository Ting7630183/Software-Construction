package edu.cs214.hw3.GameLogic.GodCards;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.Decorator.GameLogicDecorator;
import edu.cs214.hw3.GUI.Player;

import java.util.List;

public class GameLogicApollo extends GameLogicDecorator {

    public GameLogicApollo(DefaultGameLogic component) {
        super(component);

    }

    @Override
    public boolean checkMoveValidity(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        return super.checkMoveValidity(x, y, direction, board, currentPlayer, nextPlayer) || cenMoveToOpponentPlace(x, y, direction, board, nextPlayer);
    }

    /**check whether current player's worker can move to opponent's place*/
    private boolean cenMoveToOpponentPlace(int x, int y, int[] direction, Board board, Player nextPlayer){
        List<List<Integer>> opponentPlayerPlace = board.getPlayerPosition(nextPlayer);
        List<Integer> list1 = opponentPlayerPlace.get(0);
        List<Integer> list2 = opponentPlayerPlace.get(1);

        int xx = x + direction[0];
        int yy = y + direction[1];
        if((xx == list1.get(0) && yy == list1.get(1)) || (xx == list2.get(0) && yy == list2.get(1))){
            return true;
        }
        return false;
    }

    @Override
    /**move both current player and next player at the same time*/
    public void move(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        int[] dir = new int[]{x, y};
        super.move(direction[0], direction[1], dir,board, nextPlayer, currentPlayer);
        super.move(x, y, direction,board, currentPlayer, nextPlayer);
    }
}
