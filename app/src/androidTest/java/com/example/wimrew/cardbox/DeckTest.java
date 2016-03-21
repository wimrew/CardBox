package com.example.wimrew.cardbox;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JC Snider on 3/20/2016.
 */
public class DeckTest extends TestCase {
    Deck testDeck;
    @Before
    public void setUp() throws Exception {
        Card testCard = new Card("FrontText","BackText",0,0);
        testDeck = new Deck();
        testDeck.addCard(testCard); //Try to give the deck a card
    }

    @After
    public void tearDown() throws Exception {
        testDeck = null;
    }

    @Test
    public void testAddCard() throws Exception {
        Card testCard = new Card("testAddCardFront","testAddCardBack",0,0);
        testDeck.addCard(testCard); //Try to give the deck a card
        assertEquals(2, testDeck.count());
        assertEquals(testCard, testDeck.getCard(testDeck.count() - 1));
        testDeck.removeCard(testCard);
    }

    @Test
    public void testGetCard() throws Exception {
        if (testDeck.count() > 0) {
            assertEquals("FrontText", testDeck.getCard(0).getFrontText()); //Make sure the card is the card we gave it
        }
    }

    @Test
    public void testCount() throws Exception {
        assertEquals(1, testDeck.count()); //Make sure the deck has a card
    }

    @Test
    public void testRemoveCard() throws Exception {
        int deckCount = testDeck.count();
        if (deckCount > 0) {
            testDeck.removeCard(testDeck.getCard(0));
            assertEquals(deckCount-1, testDeck.count()); //Make sure the deck has no cards
        }
    }

    @Test
    public void testLoad() throws Exception {
        //Not Implemented
    }

    @Test
    public void testSave() throws Exception {
        //Not Implemented
    }
}