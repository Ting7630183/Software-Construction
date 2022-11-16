package edu.cs214.hw3.GameLogic.Decorator;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GUI.Player;

import java.util.List;

public class GameLogicDecorator implements GameLogic {
    protected DefaultGameLogic component;
    public GameLogicDecorator(DefaultGameLogic component) {
        this.component = component;
    }

    @Override
    public List<List<Integer>> checkNextMovablePosition(Player player, Board board) {
        return component.checkNextMovablePosition(player, board);
    }

    @Override
    public boolean checkMoveValidity(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        return component.checkMoveValidity(x, y, direction, board, currentPlayer, nextPlayer);
    }

    @Override
    public boolean checkBuildValidity(int x, int y, int[] direction, boolean block, Board board, int time) {
        return component.checkBuildValidity(x, y, direction, block, board, time);
    }

    @Override
    public void move(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer) {
        component.move(x, y, direction, board, currentPlayer, nextPlayer);
    }

    @Override
    public boolean build(int x, int y, int[] direction, boolean block, Board board) {
        return component.build(x, y, direction, block, board);
    }

    @Override
    public boolean checkWinner(int x, int y, int[] direction, Board board) {
        return component.checkWinner(x, y, direction, board);
    }

    @Override
    public boolean opponentCannotMove() {
        return component.opponentCannotMove();
    }


    @Override
    public boolean moveUp(int x, int y, int[] direction, Board board) {
        return component.moveUp(x, y, direction, board);
    }
}
