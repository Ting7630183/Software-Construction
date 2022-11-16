package edu.cmu.cs214.hw1.Achievement;
import static org.junit.Assert.*;

import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.cards.FlashCard;
import edu.cmu.cs214.hw1.ordering.CardDeck;
import edu.cmu.cs214.hw1.ordering.CardOrganizer;
import edu.cmu.cs214.hw1.ordering.repetition.RepeatingCardOrganizer;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class AchievementsTest {
    Achievements achievements1;
    Achievements achievements2;


    /*Called before each test*/
    @Before
    public void SetUp(){
        FlashCard flashCard1 = new FlashCard("where is CMU", "Pittsburgh");
        FlashCard flashCard2 = new FlashCard("What's your favorite food", "beef");
        FlashCard flashCard3 = new FlashCard("What's your favorite color", "red");
        List<FlashCard> flashCards = new ArrayList<>();
        List<FlashCard> flashCards2 = new ArrayList<>();
        flashCards.add(flashCard1);
        flashCards.add(flashCard2);
        flashCards.add(flashCard3);
        CardOrganizer cardOrganizer = new RepeatingCardOrganizer(7);
        CardDeck cardDeck = new CardDeck(flashCards,cardOrganizer);
        CardDeck cardDeck2 = new CardDeck(flashCards2,cardOrganizer);
        achievements1 = new Achievements(cardDeck);
        achievements2 = new Achievements(cardDeck2);
    }

    @Test
    public void testAllCorrect1(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(CardStatus card: cards){
            card.recordResult(true);
        }
        String string = achievements1.allCorrect();
        assertEquals(string, "Good!! You get all cards correct.");
    }

    @Test
    public void testAllCorrect2(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(CardStatus card: cards){
            card.recordResult(false);
        }
        String string = achievements1.allCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testThreeConsecutiveCorrect1(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                card.recordResult(true);
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, "Good!! You can three cards correct three times consecutively");
    }

    @Test
    public void testThreeConsecutiveCorrect2(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                card.recordResult(false);
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testThreeConsecutiveCorrect3(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 2; i++){
            for(CardStatus card: cards){
                card.recordResult(true);
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testThreeConsecutiveCorrect4(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                if(i == 0) {
                    card.recordResult(false);
                }else if(i == 1){
                    card.recordResult(true);
                }else if(i == 2){
                    card.recordResult(true);
                }
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testThreeConsecutiveCorrect5(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                if(i == 0) {
                    card.recordResult(false);
                }else if(i == 1){
                    card.recordResult(true);
                }else if(i == 2){
                    card.recordResult(false);
                }
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testThreeConsecutiveCorrect6(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                if(i == 0) {
                    card.recordResult(false);
                }else if(i == 1){
                    card.recordResult(false);
                }else if(i == 2){
                    card.recordResult(true);
                }
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testThreeConsecutiveCorrect7(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                if(i == 0) {
                    card.recordResult(true);
                }else if(i == 1){
                    card.recordResult(false);
                }else if(i == 2){
                    card.recordResult(false);
                }
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }
    @Test
    public void testThreeConsecutiveCorrect8(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                if(i == 0) {
                    card.recordResult(true);
                }else if(i == 1){
                    card.recordResult(false);
                }else if(i == 2){
                    card.recordResult(true);
                }
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testThreeConsecutiveCorrect9(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 3; i++){
            for(CardStatus card: cards){
                if(i == 0) {
                    card.recordResult(true);
                }else if(i == 1){
                    card.recordResult(true);
                }else if(i == 2){
                    card.recordResult(false);
                }
            }
        }
        String string = achievements1.threeConsecutiveCorrect();
        assertEquals(string, null);
    }

    @Test
    public void testFiveTimes1(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 5; i++){
            for(CardStatus card: cards){
                card.recordResult(true);
            }
        }
        String string = achievements1.fiveTimes();
        assertEquals(string, "Good!! You have studies the cards five times.");
    }

    @Test
    public void testFiveTimes2(){
        CardDeck deck = achievements1.getCardDeck();
        List<CardStatus> cards = deck.getCards();
        for(int i = 0; i < 4; i++){
            for(CardStatus card: cards){
                card.recordResult(true);
            }
        }
        String string = achievements1.fiveTimes();
        assertEquals(string, null);
    }

    @Test
    public void testFiveTimes3(){
        String string = achievements2.fiveTimes();
        assertEquals(string, null);
    }
}
