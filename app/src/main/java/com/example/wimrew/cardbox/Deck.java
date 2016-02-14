package com.example.wimrew.cardbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class Deck {
    private List<Card> Cards = new ArrayList<Card>();

    public Deck() {

    }

    public void AddCard(Card toAdd) {
        if (!Cards.contains(toAdd)) {
            Cards.add(toAdd);
        }
    }

    public void RemoveCard(Card toRemove){
        if (Cards.contains(toRemove)) {
            Cards.remove(toRemove);
        }
    }

    public Card GetCard(int index) {
        if (index > -1 && index < Cards.size()){
            return Cards.get(index);
        }
        return null;
    }

    public int Count() {
        return Cards.size();
    }

    //TODO: Loading and Saving of Decks including their Cards
    public void Load() {
        //We need to decide what format we will use, and how to save/retrieve them.
    }

    public void Save() {
        //We need to decide what format we will use, and how to save/retrieve them.
    }
}
