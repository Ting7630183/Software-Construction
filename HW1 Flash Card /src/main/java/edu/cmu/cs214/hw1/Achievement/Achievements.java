package edu.cmu.cs214.hw1.Achievement;

import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.ordering.CardDeck;

import java.util.List;

public class Achievements {
    private CardDeck cardDeck;
    public Achievements(CardDeck cardDeck){
        this.cardDeck = cardDeck;
    }

    public CardDeck getCardDeck(){
        return this.cardDeck;
    }

    /**
     * Checks whether all cards are answered correctly in the last round
     *
     * @param CardDeck
     * @return {@code true} if all cards are answered correctly in the last round.
     */
    public String allCorrect(){
        List<CardStatus> cards = this.cardDeck.getCards();
        for(CardStatus card: cards){
            boolean resultOfLastRound = getLastResult(card);
            if(!resultOfLastRound){
                return null;
            }
        }
        return "Good!! You get all cards correct.";
    }
    /**get the last element in the list
     * @param  CardStatus
     * @return the boolean value of the last element in the list
     * */
    private boolean getLastResult(CardStatus card){
        List<Boolean> result = card.getResults();
        return result.get(result.size()-1);
    }

    /**
     * Checks whether cards have been answered for at least 5 times
     *
     * @param CardDeck
     * @return {@code true} if cards have been answered for at least 5 rounds.
     */
    public String fiveTimes(){
        List<CardStatus> cards = this.cardDeck.getCards();
        for(CardStatus card: cards){
            List<Boolean> success = card.getResults();
            if(success.size() >= 5){
                return "Good!! You have studies the cards five times.";
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * Checks whether there is at least one card that has been answered correctly for three consecutive times.
     *
     * @param CardDeck
     * @return {@code true} if there is at least one card that has been answered correctly for three consecutive rounds.
     */
    public String threeConsecutiveCorrect(){
        List<CardStatus> cards = this.cardDeck.getCards();
        for(CardStatus card: cards) {
            List<Boolean> success = card.getResults();
            if (success.size() < 3) {
                return null;
            } else {
                if (getLastResult(card)) {
                    if (getSecondLastResult(card)) {
                        if (getThirdLastResult(card)) {
                            return "Good!! You can three cards correct three times consecutively";
                        }
                    }
                }
            }
        }
        return null;
    }

    /**get the second last element in the list
     * @param  CardStatus
     * @return the boolean value of the second last element in the list
     * */
    private boolean getSecondLastResult(CardStatus card){
        List<Boolean> result = card.getResults();
        return result.get(result.size()-2);
    }

    /**get the third last element in the list
     * @param  CardStatus
     * @return the boolean value of the third last element in the list
     * */
    private boolean getThirdLastResult(CardStatus card){
        List<Boolean> result = card.getResults();
        return result.get(result.size()-3);
    }
}
