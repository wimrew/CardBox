package com.example.wimrew.cardbox;

/**
 * Created by Chris on 3/18/2016.
 */
//TO get copy of deck
//PresidentDeck name = new PresidentDeck
//    name.getDeck();
//not the best way of doing it but should work for now
public class SpanishColor {
    Deck spanishColor = new Deck();

    public SpanishColor(){
        Card R = new Card("Red", "Rojo","@drawable/red","@drawable/red");
        Card O = new Card("Orange", "Naranja","@drawable/orange","@drawable/orange");
        Card Y = new Card("Yellow", "Amarillo","@drawable/yellow","@drawable/yellow");
        Card G = new Card("Green", "Verde","@drawable/green","@drawable/green");
        Card B = new Card("Blue", "Azul","@drawable/blue","@drawable/blue");
        Card I = new Card("Indigo", "AĂ±il","@drawable/indigo","@drawable/indigo");
        Card V = new Card("Violet", "Violeta","@drawable/violet","@drawable/violet");

        spanishColor.AddCard(R);
        spanishColor.AddCard(O);
        spanishColor.AddCard(Y);
        spanishColor.AddCard(G);
        spanishColor.AddCard(B);
        spanishColor.AddCard(I);
        spanishColor.AddCard(V);





    }

    public Deck getDeck() {
        return spanishColor;
    }
}
