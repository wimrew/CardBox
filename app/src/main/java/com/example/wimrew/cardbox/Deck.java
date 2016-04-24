package com.example.wimrew.cardbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class Deck implements Serializable{
    private ArrayList<Card> Cards = new ArrayList<Card>();
    private String deckName = "";
    private int deckId = -1;
    private DeckLayout layoutElement;
    private Boolean listChecked = false;

    public Deck() {

    }

    public Deck(int id, String name) {
        this.deckId = id;
        deckName = name;
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

    public void setName(String deckName) {
        this.deckName = deckName;
    }

    public ArrayList<Card> getCards() {
        return Cards;
    }

    public String getName() {
        return deckName;
    }

    public int getDeckId() {
        return deckId;
    }

    public Boolean getListChecked() {
        return listChecked;
    }

    public void setListChecked(Boolean listChecked) {
        this.listChecked = listChecked;
    }

    public void setLayoutElement(DeckLayout layoutElement) {
        this.layoutElement = layoutElement;
    }

    public DeckLayout getLayoutElement() {
        return layoutElement;
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
