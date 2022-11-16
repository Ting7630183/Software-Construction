package edu.cs214.hw3;

import edu.cs214.hw3.GUI.Player;
import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.GodCards.GameLogicApollo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testGameLogicApollo {
    GameLogicApollo apolloLogic;
    private Board board;
    private Player player1;
    private Player player2;

    @Before
    public void setUp(){
        apolloLogic = new GameLogicApollo(new DefaultGameLogic());
        board = new Board();
        player1 = new Player("Player1", "Pan", new DefaultGameLogic());
        player2 = new Player("Player2", "Athena", new DefaultGameLogic());
    }

    @Test
    public void testCheckMoveValidity(){
        board.updatePlayer(1, 2, player1);
        board.updatePlayer(3, 2, player1);
        board.updatePlayer(2, 3, player2);
        board.updatePlayer(4, 3, player2);
        int[] dir = new int[]{0, 1};
        boolean res = apolloLogic.checkMoveValidity(1, 2, dir, board, player1, player2);
        assertEquals(res, true);
    }
}
