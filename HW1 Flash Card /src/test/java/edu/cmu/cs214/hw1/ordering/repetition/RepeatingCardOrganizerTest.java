package edu.cmu.cs214.hw1.ordering.repetition;
import static org.junit.Assert.*;
import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.cards.FlashCard;
import edu.cmu.cs214.hw1.data.InMemoryCardStore;
import org.junit.*;

import java.io.IOException;

public class RepeatingCardOrganizerTest {
    RepeatingCardOrganizer repeatingCardOrganizer;
    CardStatus status1;

    /*Called before each test*/
    @Before
    public void setUp(){
        repeatingCardOrganizer = new RepeatingCardOrganizer(2);
        FlashCard flashCard1 = new FlashCard("where is CMU", "Pittsburgh");
        status1 = new CardStatus(flashCard1);
    }

    @Test
    public void testIsComplete(){
        status1.recordResult(false);
        status1.recordResult(false);
        assertFalse(repeatingCardOrganizer.isComplete(status1));

        status1.recordResult(true);
        status1.recordResult(false);
        assertFalse(repeatingCardOrganizer.isComplete(status1));

        status1.recordResult(true);
        assertTrue(repeatingCardOrganizer.isComplete(status1));
    }

    @Test(expected = AssertionError.class)
    public void whenExceptionThrown_thenExpectationSatisfied(){
        RepeatingCardOrganizer repeatingCardOrganizer1 = new RepeatingCardOrganizer(0);
        repeatingCardOrganizer1.isComplete(status1);

    }
}
