package edu.cs214.hw3.GameLogic.Decorator;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GUI.Player;

import java.util.ArrayList;
import java.util.List;

public class DefaultGameLogic implements GameLogic {
    public DefaultGameLogic() {
    }

    @Override
    public List<List<Integer>> checkNextMovablePosition(Player player, Board board) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> playerPosition = board.getPlayerPosition(player);
        int[][] dirs = new int[][]{{1, 1}, {-1, -1}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}};

        for (List<Integer> list : playerPosition) {
            int curRow = list.get(0);
            int curCol = list.get(1);
            for (int[] dir : dirs) {
                int newCurRow = curRow + dir[0];
                int newCurCol = curCol + dir[1];
                Player player1 = board.getPlayer(newCurRow, newCurCol);
                int height = board.getHeight(curRow, curCol);
                if (player1 == null && height != 4) {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(curRow);
                    newList.add(curCol);
                    result.add(newList);
                }
            }
        }
        return result;
    }

    @Override
    public boolean checkMoveValidity(int row, int col, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        if (checkWithinBoard(row, col, direction) &&
                checkAdjacency(row, col, direction) &&
                checkHeightDifference(row, col, direction, board) &&
                checkOccupancy(row, col, direction, board)
        ) {
            return true;

        }
        return false;

    }

    private boolean checkHeightDifference(int row, int col, int[] direction, Board board) {
        int heightDifference = getHeightDifference(row, col, direction, board);
        if (heightDifference <= 1) {
            return true;
        }
        return false;
    }

    private int getHeightDifference(int row, int col, int[] direction, Board board) {
        int currentHeight = board.getHeight(row, col);
        int nextHeight = board.getHeight(direction[0], direction[1]);
        return nextHeight - currentHeight;
    }

    private boolean checkWithinBoard(int row, int col, int[] direction) {
        if (direction[0] < 5 && direction[1] < 5) {
            return true;
        }
        return false;
    }

    private boolean checkAdjacency(int row, int col, int[] direction) {
        if ((Math.abs(direction[0] - row) > 1) || (Math.abs(direction[1] - col) > 1)) {
            return false;
        }
        return true;
    }

    private boolean checkOccupancy(int row, int col, int[] direction, Board board) {
        if (board.getPlayer(direction[0], direction[1]) != null || board.getHeight(direction[0], direction[1]) == 4) {
            return false;
        }
        return true;
    }


    @Override
    public boolean checkBuildValidity(int row, int col, int[] direction, boolean block, Board board, int time) {
        if (time == 1) {
            if (checkDome(direction, block, board) && checkWithinBoard(row, col, direction) && checkAdjacency(row, col, direction) && checkOccupancy(row, col, direction, board)) {
                return true;
            }
        }
        return false;
    }



    private boolean checkDome(int[] direction, boolean block, Board board){
        int height = board.getHeight(direction[0], direction[1]);
        if(!block){
            if(height != 3){
                return false;
            }
        }
        return true;
    }

    @Override
    public void move(int row, int col, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        board.updatePlayer(row, col,null);
        board.updatePlayer(direction[0], direction[1], currentPlayer);

    }

    @Override
    public boolean build(int row, int col, int[] direction, boolean block, Board board) {
        if(block){
            return board.addBlock(direction[0], direction[1]);
        }
        return board.addDome(direction[0], direction[1]);
    }

    @Override
    public boolean checkWinner(int row, int col, int[] direction, Board board) {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Player player = board.getPlayer(i, j);
                int height = board.getHeight(i, j);
                if(player != null && height == 3){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean opponentCannotMove() {
        return false;
    }


    @Override
    public boolean moveUp(int x, int y, int[] direction, Board board) {
        int height1 = board.getHeight(direction[0], direction[1]);
        int height2 = board.getHeight(x, y);
        if(height1 - height2 >=1){
            return true;
        }
        return false;
    }
}
