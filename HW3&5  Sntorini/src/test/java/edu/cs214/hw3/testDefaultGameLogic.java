package edu.cs214.hw3;
import edu.cs214.hw3.GUI.Player;
import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class testDefaultGameLogic {
    private DefaultGameLogic defaultGameLogic;
    private Board board;
    private Player player1;
    private Player player2;


    @Before
    public void setUp(){
        defaultGameLogic = new DefaultGameLogic();
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
    public void testCheckMovablePosition() {
        List<List<Integer>> res = defaultGameLogic.checkNextMovablePosition(this.player1,this.board);;
        int size = res.size();
        assertEquals(size, 13);
    }


    @Test
    public void testCheckMoveValidity(){
        int[] dir1 = new int[]{1, 0};
        boolean res1 = defaultGameLogic.checkMoveValidity(1, 2, dir1, board, player1, player2);
        assertEquals(res1, true);
        int[] dir2 = new int[]{1, 1};
        boolean res2 = defaultGameLogic.checkMoveValidity(1, 2, dir2, board, player1, player2);
        assertEquals(res2, false);
        int[] dir3 = new int[]{0, 1};
        boolean res3 = defaultGameLogic.checkMoveValidity(3, 2, dir3, board, player1, player2);
        assertEquals(res3, false);
    }

    @Test
    public void testCheckBuildValidity(){
        int[] dir1 = new int[]{1, 0};
        boolean res1 = defaultGameLogic.checkBuildValidity(1, 2, dir1, true, board, 1);
        assertEquals(res1, false);
//        int[] dir2 = new int[]{1, 1};
//        boolean res2 = defaultGameLogic.checkBuildValidity(1, 2, dir2, true, board);
//        assertEquals(res2, false);
//        int[] dir3 = new int[]{0, 1};
//        boolean res3 = defaultGameLogic.checkBuildValidity(3, 2, dir3,true, board);
//        assertEquals(res3, true);
    }

    @Test
    public void testMove(){
        int[] dir1 = new int[]{0, 1};
         defaultGameLogic.move(1, 2, dir1, board, player1, player2);
         Player player = board.getPlayer(1, 3);
         assertEquals(player, player1);
    }

    @Test
    public void testBuild(){
        int[] dir = new int[]{0, 1};
        defaultGameLogic.build(1, 2, dir, true, board);
        int height = board.getHeight(1, 3);
        assertEquals(height, 1);
    }

    @Test
    public void testCheckWinner(){
        board.addBlock(3, 3);
        int[] dir = new int[]{0, 1};
        defaultGameLogic.move(3, 2, dir,board, player1, player2);
        boolean res = defaultGameLogic.checkWinner(3, 2, dir, board);
        assertEquals(res, true);

    }

}
