package edu.cs214.hw3;
import edu.cs214.hw3.GUI.Player;
import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.GodCards.GameLogicPan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class testGameLogicPan {
    private GameLogicPan panLogic;
    private Board board;
    private Player player1;
    private Player player2;

    @Before
    public void setUp(){
        panLogic = new GameLogicPan(new DefaultGameLogic());
        board = new Board();
        player1 = new Player("Player1", "Pan", new DefaultGameLogic());
        player2 = new Player("Player2", "Athena", new DefaultGameLogic());
        board.updatePlayer(1, 2, player1);
        board.updatePlayer(3, 2, player1);
        board.updatePlayer(2, 3, player2);
        board.updatePlayer(4, 3, player2);
        board.addBlock(3, 3);
        board.addBlock(3, 3);
    }

    @Test
    public void testCheckWinner() {
        int[] dir = new int[]{0, 1};
        panLogic.move(3, 2, dir, board, player1, player2);
        panLogic.move(3,3, dir, board, player1, player2);
        boolean res = panLogic.checkWinner(3, 3, dir, board);
        assertEquals(res, true);

    }
    @Test
    public void testMove(){
        int[] dir = new int[]{0, 1};
        panLogic.move(1, 2, dir, board, player1, player2);
        Player player = board.getPlayer(1, 3);
        Player player4 = board.getPlayer(1, 2);
        assertEquals(player, player1);
        assertEquals(player4, null);
    }

}
