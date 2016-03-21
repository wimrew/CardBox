package com.example.wimrew.cardbox;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JC Snider on 3/20/2016.
 */
public class StudySessionTest extends TestCase {
    private StudySession sessionTest;

    @Before
    public void setUp() throws Exception {
        //Start by defining a deck
        Deck testDeck = new Deck();

        //Then add cards to the deck
        testDeck.addCard(new Card("1","I",0,0));
        testDeck.addCard(new Card("2","II",0,0));
        testDeck.addCard(new Card("3","II",0,0));
        testDeck.addCard(new Card("4","IV",0,0));
        testDeck.addCard(new Card("5","V",0,0));

        //Define a new study session, referencing the deck we are testing.
        sessionTest = new StudySession(testDeck);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetNextCard() throws Exception {
        if (sessionTest.getRemainingCards() > 0) {
            Card displayCard = sessionTest.getNextCard();
            assertNotNull(displayCard);
        }
    }

    @Test
    public void testMarkCorrect() throws Exception {
        int cardCount = sessionTest.getRemainingCards();
        if (cardCount > 0) {
            sessionTest.getNextCard();
            sessionTest.markCorrect();
            assertEquals(cardCount - 1, sessionTest.getRemainingCards());
        }
    }

    @Test
    public void testMarkIncorrect() throws Exception {
        int cardCount = sessionTest.getRemainingCards();
        if (cardCount > 0) {
            sessionTest.getNextCard();
            sessionTest.markIncorrect();
            assertEquals(cardCount,sessionTest.getRemainingCards());
        }
    }

    @Test
    public void testRemainingCards() throws Exception {
        assertEquals(5, sessionTest.getRemainingCards());
    }

    @Test
    public void testIsDone() throws Exception {
        assertEquals(false, sessionTest.isDone());
    }
}