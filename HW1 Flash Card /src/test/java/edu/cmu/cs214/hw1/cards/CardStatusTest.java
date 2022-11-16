package edu.cmu.cs214.hw1.cards;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;

public class CardStatusTest {
    private CardStatus cardStatus;
    FlashCard flashCard = new FlashCard("where is CMU", "Pittsburgh");

    /*Called before each test*/
    @Before
    public void setUp(){
        cardStatus = new CardStatus(flashCard);
        FlashCard card = cardStatus.getCard();
        assertNotNull(card);
    }

    @Test
    public void testGetCard(){
        FlashCard card = cardStatus.getCard();
        assertEquals(card.answer(), "Pittsburgh");
        assertEquals(card.question(), "where is CMU");
        assertNotEquals(card.answer(), "where is CMU");
        assertNotEquals(card.question(), "Pittsburgh");
    }

    @Test
    public void testGetResult(){
        cardStatus.recordResult(true);
        List<Boolean> list = cardStatus.getResults();
        assertEquals(list.get(0), true);
    }

    @Test
    public void testRecordResult(){
        cardStatus.recordResult(true);
        List<Boolean> list = cardStatus.getResults();
        assertEquals(list.get(0), true);
        cardStatus.clearResults();
        assertTrue(cardStatus.getResults().isEmpty());
    }
}
