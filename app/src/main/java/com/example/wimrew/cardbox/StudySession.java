package com.example.wimrew.cardbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class StudySession {

    //This is the deck of cards we are studying.
    private Deck _myDeck;
    //This is the card we are currently showing.
    private Card _currentCard = null;
    //Keep track of the last card so we never show the same one twice.
    private Card _lastCard = null;
    //This is a list of cards in the deck, with an integer value signifying how many times this card has been answered correctly.
    private HashMap<Card,Integer> _sessionCards = new HashMap<Card,Integer>();
    private Random _Rand = new Random();

    //This is a temporary variable I am using to say if a card is correct X amount of times, don't repeat it. This will be replaced by the option value when that is created.
    private int CorrectLimit = 1;

    public StudySession(Deck deckToStudy) {
        _myDeck = deckToStudy;

        for (int i = 0; i < _myDeck.count(); i++){
            _sessionCards.put(_myDeck.getCard(i),0); //Each card starts by being answered a total of 0 times.
        }
    }

    public Card getNextCard() {
        //Throw the remaining cards into a list.
        List<Card> cards = new ArrayList<Card>(_sessionCards.keySet());
        if (!isDone()) {
            if (cards.size() == 1) {
                //return the only card!
                _currentCard = cards.get(0);
                return cards.get(0);
            } else {
                //Get a number between 0 and the number of remaining cards.
                int index = _Rand.nextInt(cards.size());
                //If the random card we picked was the previously shown card, try again!
                if (cards.get(index) == _lastCard) {
                    return getNextCard();
                } else {
                    //Return the card at the random index we generated
                    _lastCard = _currentCard;
                    _currentCard = cards.get(index);
                    return _currentCard;
                }
            }
        } else{
        return null;}
    }

    public void markCorrect() {
        if (_currentCard != null) {
            //Increase the score for the current card by 1
            _sessionCards.put(_currentCard,_sessionCards.get(_currentCard) + 1);

            //If the score is equal to or above the limit, we don't need to show it again, remove it from our hashmap.
            if (_sessionCards.get(_currentCard) >= CorrectLimit) {
                _sessionCards.remove(_currentCard);
                _currentCard = null;
            }
        }
    }

    public void markIncorrect() {
        if (_currentCard != null) {
            //No need to take action if the card is incorrect. We might use this later.
        }
    }

    public int getRemainingCards() {
        return _sessionCards.size();
    }

    public boolean isDone() {
        //If we have no more cards to show, return true, else return false.
        if (_sessionCards.size() == 0){
            return true;
        }
        return false;
    }
}
