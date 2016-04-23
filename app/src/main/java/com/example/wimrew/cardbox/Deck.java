package com.example.wimrew.cardbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class Deck implements Serializable{
    private List<Card> Cards = new ArrayList<Card>();

    public Deck() {

    }

    public void addCard(Card toAdd) {
        if (!Cards.contains(toAdd)) {
            Cards.add(toAdd);
        }
    }

    public void removeCard(Card toRemove){
        if (Cards.contains(toRemove)) {
            Cards.remove(toRemove);
        }
    }

    public Card getCard(int index) {
        if (index > -1 && index < Cards.size()){
            return Cards.get(index);
        }
        return null;
    }

    public int count() {
        return Cards.size();
    }

    //TODO: Loading and Saving of Decks including their Cards
    public void load() {
        //We need to decide what format we will use, and how to save/retrieve them.
    }

    public void save() {
        //We need to decide what format we will use, and how to save/retrieve them.
    }
}
