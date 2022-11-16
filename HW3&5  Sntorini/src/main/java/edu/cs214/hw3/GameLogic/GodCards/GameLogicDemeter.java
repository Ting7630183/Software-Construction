package edu.cs214.hw3.GameLogic.GodCards;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.Decorator.GameLogicDecorator;

public class GameLogicDemeter extends GameLogicDecorator {
    int[] lastBuildPlace = new int[2];
    public GameLogicDemeter(DefaultGameLogic component) {
        super(component);
    }


    @Override
    public boolean checkBuildValidity(int x, int y, int[] direction, boolean block, Board board, int time) {
        if(time == 1){
            lastBuildPlace = direction;
            return super.checkBuildValidity(x, y, direction, block, board, time);
        }else{
            return notSameDirection(direction) && checkDome(direction, block, board) && checkAdjacency(x, y, direction) && checkOccupancy(direction, board);
        }
    }

    /**check whether player can build a dome*/
    private boolean checkDome(int[] direction, boolean block, Board board){
        int height = board.getHeight(direction[0], direction[1]);
        if(!block){
            if(height != 3){
                return false;
            }
        }
        return true;
    }

    /**check adjacency*/
    private boolean checkAdjacency(int row, int col, int[] direction) {
        if ((Math.abs(direction[0] - row) > 1) || (Math.abs(direction[1] - col) > 1)) {
            return false;
        }
        return true;
    }

    /**check occupancy*/
    private boolean checkOccupancy(int[] direction, Board board) {
        if (board.getPlayer(direction[0], direction[1]) != null || board.getHeight(direction[0], direction[1]) == 4) {
            return false;
        }
        return true;
    }

    /**check whether direction is the same with last moving direction*/
    private boolean notSameDirection(int [] direction){
        if(direction[0] != lastBuildPlace[0] || direction[1] != lastBuildPlace[1]){
            return true;
        }
        return false;
    }


}
