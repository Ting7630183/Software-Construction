package edu.cmu.cs214.hw1.data;
import static org.junit.Assert.*;

import edu.cmu.cs214.hw1.cards.FlashCard;
import org.junit.*;

import java.util.*;

public class InMemoryCardStoreTest {
    InMemoryCardStore inMemoryCardStore;
    FlashCard flashCard1 = new FlashCard("where is CMU", "Pittsburgh");
    FlashCard flashCard2 = new FlashCard("What's your favorite food", "beef");

    /*Called before each test*/
    @Before
    public void setUp(){
        inMemoryCardStore = new InMemoryCardStore();
    }

    @Test
    public void testInMemoryCardStore() {
        assertEquals(inMemoryCardStore.getAllCards().size(), 0);
    }

    @Test
    public void testAddCards(){
        inMemoryCardStore.addCard(flashCard1);
        inMemoryCardStore.addCard(flashCard2);
        assertEquals(inMemoryCardStore.getAllCards().size(), 2);
        inMemoryCardStore.removeCard(flashCard1);
        assertEquals(inMemoryCardStore.getAllCards().size(), 1);
    }

    @Test
    public void testInvertQuestion() {
        inMemoryCardStore.addCard(flashCard1);
        InMemoryCardStore inverted = inMemoryCardStore.invertQuestions();
        Set<FlashCard> cardsInverted = new HashSet<>(inverted.getAllCards());
        Set<FlashCard> cardsNotInverted = new HashSet<>(inMemoryCardStore.getAllCards());
        FlashCard cardInverted = cardsInverted.iterator().next();
        FlashCard cardNotInverted = cardsNotInverted.iterator().next();
        assertEquals(cardInverted.answer(), cardNotInverted.question());

    }



}
