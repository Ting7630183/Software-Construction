package edu.cs214.hw3.GameLogic.Decorator;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GUI.Player;

import java.util.List;

public interface GameLogic {
    /**
     * Get all the position in the board that the current player's workers can choose next
     * @param Player  The current player
     * @return List<List<Integer>> a list of position that the player's workers can go next
     * @return null if there is no position in the board that the player's workers can move to
     */
    List<List<Integer>> checkNextMovablePosition(Player player, Board board);

    /**
     * Check Whether the position the worker moves to is a valid one
     * @param int x The row of the board
     * @param int y The column of the board
     * @param int[] the direction the worker moves to
     * @return  true if the worker can move to the new position
     */
    boolean checkMoveValidity(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer);

    /**
     * Check Whether the worker can build a block of doom in the direction
     * @param int x The row of the board
     * @param int y The column of the board
     * @param int[] the direction the worker moves to
     * @return  true if the worker can build a block of doom in the direction
     */
    boolean checkBuildValidity(int x, int y, int[] direction, boolean block, Board board, int time);

    /**
     * Move the worker to new position and update the board
     * @param int x The row of the board
     * @param int y The column of the board
     * @param int[] the direction the worker moves to
     * @param Plyaer the player who do the move
     */
    void move(int x, int y, int[] direction, Board board, Player currentPlayer, Player nextPlayer);

    /**
     * Move the build a block or dome in the  new position and update the board
     * @param int x The row of the board
     * @param int y The column of the board
     * @param int[] the direction the worker moves to
     * @param Board the board of the game
     */
    boolean build(int x, int y, int[] direction, boolean block, Board board);

    /**
     * Go over the whole board to see whether is a winner
     */
    boolean checkWinner(int x, int y, int[] direction, Board board);

    /**
     * check whether opponent can move
     */
    boolean opponentCannotMove();


    /**
     * check whether current player's worker move up or not in this turn
     */
    boolean moveUp(int x, int y, int[] direction, Board board);
}


