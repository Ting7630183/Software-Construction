package edu.cs214.hw3;

import edu.cs214.hw3.GUI.Player;
import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.GodCards.GameLogicPan;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class testBoard {
    private Board board;

    @Before
    public void setUp(){
        board = new Board();
    }

    @Test
    public void testAddBlock() {
        board.addBlock(1, 1);
        int height = board.getHeight(1, 1);
        assertEquals(height, 1);
    }

    @Test
    public void testUpdatePlayer(){
        Player player1 = new Player("god", "pan", new GameLogicPan(new DefaultGameLogic()));

        board.updatePlayer(1, 1, player1);
        assertNotNull(board.getPlayer(1, 1));
        assertEquals(board.getPlayer(1, 1), player1);
    }

    @Test
    public void testAddDome(){
        boolean res = board.addDome(1, 1);
        assertEquals(res, false);
    }

    @Test
    public void getPlayerPosition(){
        Player player1 = new Player("Player1", "Pan", new GameLogicPan(new DefaultGameLogic()));
        board.updatePlayer(1, 2, player1);
        board.updatePlayer(3, 4, player1);
        List<List<Integer>> res = board.getPlayerPosition(player1);
        List<Integer> list1 = res.get(0);
        int a = list1.get(0);
        assertEquals(a, 1);
        int b = list1.get(1);
        assertEquals(b, 2);
        List<Integer> list2 = res.get(1);
        int c = list2.get(0);
        assertEquals(c, 3);
        int d = list2.get(1);
        assertEquals(d, 4);
    }

}
