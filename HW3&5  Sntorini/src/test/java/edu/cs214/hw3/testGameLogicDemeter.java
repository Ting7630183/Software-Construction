package edu.cs214.hw3;
import edu.cs214.hw3.GUI.Player;
import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.GodCards.GameLogicDemeter;
import org.junit.Before;

public class testGameLogicDemeter {
    GameLogicDemeter demeterLogic;
    private Board board;
    private Player player1;
    private Player player2;

    @Before
    public void setUp(){
        demeterLogic = new GameLogicDemeter(new DefaultGameLogic());
        board = new Board();
        player1 = new Player("Player1", "Pan", new DefaultGameLogic());
        player2 = new Player("Player2", "Athena", new DefaultGameLogic());
        board.updatePlayer(1, 2, player1);
        board.updatePlayer(3, 2, player1);
        board.updatePlayer(1, 3, player2);
        board.updatePlayer(4, 3, player2);
    }


}
