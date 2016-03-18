package com.example.wimrew.cardbox;

/**
 * Created by Chris on 2/20/2016.
 */
//TO get copy of deck
//PresidentDeck name = new PresidentDeck
//    name.getDeck();
//not the best way of doing it but should work for now
public class PresidentDeck {
    Deck presDeck = new Deck();

    public PresidentDeck(){
        Card GW1 = new Card("George  Washington", "1st");
        Card JA2 = new Card("John Adams", "2nd");
        Card TJ3 = new Card("Thomas Jefferson", "3rd");
        Card JM4 = new Card("James Madison", "4td");
        Card JM5 = new Card("James Monroe", "5th");
        Card JQA6 = new Card("John Quincy Adams", "6th");
        Card AJ7 = new Card("Andrew Jackson", "7th");
        Card MVB8 = new Card("Martin Van Buren", "8th");
        Card WHH9 = new Card("William Henry Harrison", "9th");
        Card JT10 = new Card("John Tyler", "10th");

        presDeck.AddCard(GW1);
        presDeck.AddCard(JA2);
        presDeck.AddCard(TJ3);
        presDeck.AddCard(JM4);
        presDeck.AddCard(JM5);
        presDeck.AddCard(JQA6);
        presDeck.AddCard(AJ7);
        presDeck.AddCard(MVB8);
        presDeck.AddCard(WHH9);
        presDeck.AddCard(JT10);


    }

    public Deck getDeck() {
        return presDeck;
    }
}
