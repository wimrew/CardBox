package com.example.wimrew.cardbox;

import junit.framework.TestCase;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class StudySessionTest extends TestCase {

    private StudySession sessionTest;
    public void setUp() throws Exception {
        super.setUp();

        //Start by defining a deck
        Deck testDeck = new Deck();

        //Then add cards to the deck
        testDeck.AddCard(new Card("1","I",null,null));
        testDeck.AddCard(new Card("2","II",null,null));
        testDeck.AddCard(new Card("3","II",null,null));
        testDeck.AddCard(new Card("4","IV",null,null));
        testDeck.AddCard(new Card("5","V",null,null));

        //Define a new study session, referencing the deck we are testing.
        sessionTest = new StudySession(testDeck);
    }

    public void tearDown() throws Exception {

    }

    public void testGetNextCard() throws Exception {

        //Loop through the study session until .isDone() becomes true.
        //Alternate between calling .getNextCard() and .markCorrect() OR .markIncorrect()
        while (!sessionTest.isDone()) {
            Card displayCard = sessionTest.getNextCard();
            System.out.println("Card Drawn. Front: " + displayCard.getFrontText() + "      Back: " + displayCard.getBackText());
            sessionTest.markCorrect();
        }
    }
}