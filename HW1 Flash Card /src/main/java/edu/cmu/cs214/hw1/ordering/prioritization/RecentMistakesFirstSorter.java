package edu.cmu.cs214.hw1.ordering.prioritization;

import edu.cmu.cs214.hw1.cards.CardStatus;
import edu.cmu.cs214.hw1.ordering.CardOrganizer;

import java.util.ArrayList;
import java.util.List;


public class RecentMistakesFirstSorter implements CardOrganizer{

    /**
     * Orders the cards so that the ones that were answered incorrectly in the las round appear first.
     *
     * @param cards The {@link CardStatus} objects to order.
     * @return The ordered cards.
     */

    @Override
    public List<CardStatus> reorganize(List<CardStatus> cards) {
        if(cards == null){
            return null;
        }

        List<CardStatus> newOrder = new ArrayList<>();
        for(CardStatus card: cards){
            List<Boolean> results = card.getResults();
            if(!getLastResult(results)){
                newOrder.add(card);
            }
        }
        
        for(CardStatus card: cards){
            List<Boolean> results = card.getResults();
            if(getLastResult(results)){
                newOrder.add(card);
            }
        }
        return newOrder;
    }

    /**get the last element in the list
     * Orders the cards so that the ones that were answered incorrectly in the las round appear first.
     *
     * @param list the result list from cardsGetResult
     * @return the boolean value of the last element in the list
     * */
    private boolean getLastResult(List<Boolean> list){
        int size = list.size();
        return list.get(size-1);
    }

}

