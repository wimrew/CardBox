package com.example.wimrew.cardbox;

/**
 * Created by Chris on 3/18/2016.
 */
//TO get copy of deck
//Shapes name = new Shapes
//    name.getDeck();
//not the best way of doing it but should work for now
public class ShapeDeck {
    Deck shapes = new Deck();

    public ShapeDeck(){


        Card Triangle  = new Card("", "Triangle",com.example.wimrew.cardbox.R.drawable.triangle,com.example.wimrew.cardbox.R.drawable.triangle);
        Card Square = new Card("", "Square",com.example.wimrew.cardbox.R.drawable.square,com.example.wimrew.cardbox.R.drawable.square);
        Card Circle = new Card("", "Circle",com.example.wimrew.cardbox.R.drawable.circle,com.example.wimrew.cardbox.R.drawable.circle);
        Card Oval = new Card("", "Oval",com.example.wimrew.cardbox.R.drawable.oval,com.example.wimrew.cardbox.R.drawable.oval);
        Card Heart = new Card("", "Heart",com.example.wimrew.cardbox.R.drawable.heart,com.example.wimrew.cardbox.R.drawable.heart);
        Card Hexagon = new Card("", "Hexagon",com.example.wimrew.cardbox.R.drawable.hexagon,com.example.wimrew.cardbox.R.drawable.hexagon);
        Card Octagon = new Card("", "Octagon",com.example.wimrew.cardbox.R.drawable.octagon,com.example.wimrew.cardbox.R.drawable.octagon);
        Card Parallelgram = new Card("", "Parallelgram",com.example.wimrew.cardbox.R.drawable.parallelogram,com.example.wimrew.cardbox.R.drawable.parallelogram);
        Card Pentagon = new Card("", "Pentagon",com.example.wimrew.cardbox.R.drawable.pentagon,com.example.wimrew.cardbox.R.drawable.pentagon);
        Card Rhombus = new Card("", "Rhombus",com.example.wimrew.cardbox.R.drawable.rhombus,com.example.wimrew.cardbox.R.drawable.rhombus);
        Card Star = new Card("", "Star",com.example.wimrew.cardbox.R.drawable.star,com.example.wimrew.cardbox.R.drawable.star);

        shapes.addCard(Triangle);
        shapes.addCard(Square);
        shapes.addCard(Circle);
        shapes.addCard(Oval);
        shapes.addCard(Heart);
        shapes.addCard(Hexagon);
        shapes.addCard(Octagon);
        shapes.addCard(Parallelgram);
        shapes.addCard(Pentagon);
        shapes.addCard(Rhombus);
        shapes.addCard(Star);






    }

    public Deck getDeck() {return shapes;
    }
}
