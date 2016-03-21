package com.example.wimrew.cardbox;

/**
 * Created by Chris on 3/18/2016.
 */
//TO get copy of deck
//PresidentDeck name = new PresidentDeck
//    name.getDeck();
//not the best way of doing it but should work for now
public class SpanishColorDeck {
    Deck spanishColor = new Deck();

    public SpanishColorDeck(){


        Card R = new Card("Red", "Rojo",com.example.wimrew.cardbox.R.drawable.red,com.example.wimrew.cardbox.R.drawable.red);
        Card O = new Card("Orange", "Naranja",com.example.wimrew.cardbox.R.drawable.orange,com.example.wimrew.cardbox.R.drawable.orange);
        Card Y = new Card("Yellow", "Amarillo",com.example.wimrew.cardbox.R.drawable.yellow,com.example.wimrew.cardbox.R.drawable.yellow);
        Card G = new Card("Green", "Verde",com.example.wimrew.cardbox.R.drawable.green,com.example.wimrew.cardbox.R.drawable.green);
        Card B = new Card("Blue", "Azul",com.example.wimrew.cardbox.R.drawable.blue,com.example.wimrew.cardbox.R.drawable.blue);
        Card I = new Card("Indigo", "Indigo",com.example.wimrew.cardbox.R.drawable.indigo,com.example.wimrew.cardbox.R.drawable.indigo);
        Card V = new Card("Violet", "Violeta",com.example.wimrew.cardbox.R.drawable.violet,com.example.wimrew.cardbox.R.drawable.violet);

        spanishColor.addCard(R);
        spanishColor.addCard(O);
        spanishColor.addCard(Y);
        spanishColor.addCard(G);
        spanishColor.addCard(B);
        spanishColor.addCard(I);
        spanishColor.addCard(V);





    }

    public Deck getDeck() {
        return spanishColor;
    }
}
