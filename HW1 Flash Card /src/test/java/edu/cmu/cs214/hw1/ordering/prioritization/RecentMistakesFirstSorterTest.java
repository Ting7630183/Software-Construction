package edu.cmu.cs214.hw1.ordering.prioritization;

import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.cards.FlashCard;
import edu.cmu.cs214.hw1.ordering.CardOrganizer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecentMistakesFirstSorterTest {
    CardOrganizer RecentMistakesFirstSorter = new RecentMistakesFirstSorter();
    private List<CardStatus> cards;
    CardStatus status1;
    CardStatus status2;
    CardStatus status3;

    /*Called before each test*/
    @Before
    public void setUp(){
        FlashCard flashCard1 = new FlashCard("where is CMU", "Pittsburgh");
        FlashCard flashCard2 = new FlashCard("What's your favorite food", "beef");
        FlashCard flashCard3 = new FlashCard("What's your favorite color", "red");
        status1 = new CardStatus(flashCard1);
        status2 = new CardStatus(flashCard2);
        status3 = new CardStatus(flashCard3);
        cards = new ArrayList<>();
        cards.add(status1);
        cards.add(status2);
        cards.add(status3);
    }

    @Test
    public void testReorganize(){
        status1.recordResult(false);
        status2.recordResult(true);
        status3.recordResult(true);

        List<CardStatus> sorted = RecentMistakesFirstSorter.reorganize(cards);
        assertEquals(sorted.get(0), status1);
    }
}
