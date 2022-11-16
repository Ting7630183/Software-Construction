package edu.cmu.cs214.hw1.ordering;
import static org.junit.Assert.*;

import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.cards.FlashCard;
import edu.cmu.cs214.hw1.ordering.prioritization.RecentMistakesFirstSorter;
import edu.cmu.cs214.hw1.ordering.repetition.NonRepeatingCardOrganizer;
import edu.cmu.cs214.hw1.ordering.repetition.RepeatingCardOrganizer;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class CardDeckTest {
    CardOrganizer organizer;
    CardDeck cardDeck;

    @Before
    public void SetUp(){
        FlashCard flashCard1 = new FlashCard("where is CMU", "Pittsburgh");
        FlashCard flashCard2 = new FlashCard("What's your favorite food", "beef");
        FlashCard flashCard3 = new FlashCard("What's your favorite color", "red");
        List<FlashCard> flashCards = new ArrayList<>();
        flashCards.add(flashCard1);
        flashCards.add(flashCard2);
        flashCards.add(flashCard3);
        /*List<CardOrganizer> list = new ArrayList<>();
        CardOrganizer repeatingCardOrganizer = new RepeatingCardOrganizer(2);
        CardOrganizer recentMistakeFirstSorter = new RecentMistakesFirstSorter();
        list.add(repeatingCardOrganizer);
        list.add(recentMistakeFirstSorter);
        multiOrganizer = new MultiOrganizer(list);*/
        organizer = new RecentMistakesFirstSorter();
        cardDeck = new CardDeck(flashCards, organizer);
    }

    @Test
    public void testGetCardSorter(){
        CardOrganizer cardOrganizer = cardDeck.getCardSorter();
        assertEquals(cardOrganizer, organizer);
    }

    @Test
    public void testCountCard(){
        assertEquals(cardDeck.countCards(), 3);
    }

    @Test
    public void testIsComplete(){
        assertFalse(cardDeck.isComplete());
    }

    @Test
    public void testGetCard(){
       List<CardStatus> cards = cardDeck.getCards();
       assertEquals(cards.size(), 3);
    }

    @Test
    public void testReorganize(){
        List<CardStatus> cards = cardDeck.getCards();
        for(int i = 0; i < cards.size(); i++){
            if(i == 1){
                cards.get(i).recordResult(false);
            }else{
                cards.get(i).recordResult(true);
            }
        }
        List<CardStatus> cardsOrganized = cardDeck.reorganize();
        List<Boolean> successes = cardsOrganized.get(0).getResults();
        boolean result =  successes.get(successes.size()-1);
        assertEquals(result, false);

    }

}
