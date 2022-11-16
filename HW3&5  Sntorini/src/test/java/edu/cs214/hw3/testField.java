package edu.cs214.hw3;

import edu.cs214.hw3.GUI.Player;
import edu.cs214.hw3.GameLogic.Board.Field;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.GodCards.GameLogicPan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class testField {
    private Field cell;

    @Before
    public void setUp(){
        cell = new Field(0, null);
    }

    @Test
    public void testAddBlock() {
        cell.addBlock();
        int height = cell.getHeight();
        assertEquals(height, 1);
    }
    @Test
    public void testAddDome(){
        boolean res = cell.addDome();
        assertEquals(res, false);
    }

    @Test
    public void testGetHeight(){
        cell.addBlock();
        cell.addBlock();
        int height = cell.getHeight();
        assertEquals(height, 2);
    }

    @Test
    public void testUpdatePlayer(){
        Player player = new Player("Player1", "pan", new GameLogicPan(new DefaultGameLogic()));
        cell.updatePlayer(player);
        assertNotEquals(cell.getPlayer(), null);
    }

}
