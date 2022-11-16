package edu.cmu.cs214.hw1.ordering.repetition;
import static org.junit.Assert.*;
import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.cards.FlashCard;
import org.junit.*;

public class NonRepeatingCardOrganizerTest {
    CardRepeater nonRepeatingCardOrganizer = new NonRepeatingCardOrganizer();
    CardStatus status1;


    /*Called before each test*/
    @Before
    public void setUp(){
        FlashCard flashCard1 = new FlashCard("where is CMU", "Pittsburgh");
        status1 = new CardStatus(flashCard1);
    }

    @Test
    public void testIsComplete(){
        assertFalse(nonRepeatingCardOrganizer.isComplete(status1));
        status1.recordResult(false);
        assertTrue(nonRepeatingCardOrganizer.isComplete(status1));


    }
}
