package edu.cs214.hw3.GameLogic.GodCards;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.Decorator.GameLogicDecorator;

public class GameLogicPan extends GameLogicDecorator {

    public GameLogicPan(DefaultGameLogic component) {
        super(component);
    }

    @Override
    public boolean checkWinner(int x, int y, int[] direction, Board board) {
        if(MoveDownTwoOrMore(x, y, direction, board)){
            return true;
        }else {
            return super.checkWinner(x, y, direction, board);
        }
    }

     /**check whether current player move down two floor or not*/
    private boolean MoveDownTwoOrMore(int x, int y, int[] direction, Board board){
        int currentHeight = board.getHeight(x, y);
        int nextHeight = board.getHeight(direction[0], direction[1]);
        if(currentHeight-nextHeight >=2){
            return true;
        }
        return false;
    }
}
