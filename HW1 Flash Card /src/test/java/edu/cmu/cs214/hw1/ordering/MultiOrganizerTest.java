package edu.cmu.cs214.hw1.ordering;
import static org.junit.Assert.*;

import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.cards.FlashCard;
import edu.cmu.cs214.hw1.ordering.prioritization.RecentMistakesFirstSorter;
import edu.cmu.cs214.hw1.ordering.repetition.RepeatingCardOrganizer;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class MultiOrganizerTest {
    List<CardOrganizer> cardOrganizers;
    MultiOrganizer multiOrganizer;

    /*Called before each test*/
    @Before
    public void setUp(){
        cardOrganizers = new ArrayList<>();
        cardOrganizers.add(new RepeatingCardOrganizer(2));
        cardOrganizers.add(new RecentMistakesFirstSorter());
        multiOrganizer = new MultiOrganizer(cardOrganizers);
    }

    @Test
    public void testReorganize(){
        FlashCard flashCard1 = new FlashCard("where is CMU", "Pittsburgh");
        FlashCard flashCard2 = new FlashCard("What's your favorite food", "beef");
        FlashCard flashCard3 = new FlashCard("What's your favorite color", "red");
        CardStatus status1 = new CardStatus(flashCard1);
        CardStatus status2 = new CardStatus(flashCard2);
        CardStatus status3 = new CardStatus(flashCard3);
        List<CardStatus> list = new ArrayList<>();
        list.add(status1);
        list.add(status2);
        list.add(status3);

        status1.recordResult(false);
        status2.recordResult(true);
        status3.recordResult(true);

        List<CardStatus> afterReorganized = multiOrganizer.reorganize(list);
        assertEquals(list.get(0), status1);
    }
}
