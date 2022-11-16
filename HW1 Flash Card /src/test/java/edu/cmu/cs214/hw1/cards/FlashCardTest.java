package edu.cmu.cs214.hw1.cards;
import static org.junit.Assert.*;
import org.junit.*;

public class FlashCardTest {
    private FlashCard flashCard;

    /*Called before each test*/
    @Before
    public void setUp(){
        flashCard = new FlashCard("Where is CMU", "Pittsburgh");
    }

    @Test
    public void testCheckSuccess(){
        assertTrue(flashCard.checkSuccess("pittsburgh"));
        assertFalse(flashCard.checkSuccess("Boston"));
        assertFalse(flashCard.checkSuccess(" "));
        assertEquals(flashCard.question(), "Where is CMU");
        assertEquals(flashCard.answer(),"Pittsburgh");
    }

}
