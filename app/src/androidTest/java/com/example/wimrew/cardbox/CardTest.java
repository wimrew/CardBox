package com.example.wimrew.cardbox;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JC Snider on 3/20/2016.
 */
public class CardTest extends TestCase {
    Card testCard;
    @Before
    public void setUp() throws Exception {
        testCard = new Card("FrontText","BackText",2,3);
    }

    @After
    public void tearDown() throws Exception {
        testCard = null;
    }

    @Test
    public void testSetFrontText() throws Exception {
        String testText = "testFrontText";
        testCard.setFrontText(testText);
        assertEquals(testText, testCard.getFrontText());
    }

    @Test
    public void testSetBackText() throws Exception {
        String testText = "testBackText";
        testCard.setBackText(testText);
        assertEquals(testText, testCard.getBackText());
    }

    @Test
    public void testSetFrontImagePath() throws Exception {
        int testImage = 5;
        testCard.setFrontImagePath(testImage);
        assertEquals(testImage, testCard.getFrontImagePath());
    }

    @Test
    public void testSetBackImagePath() throws Exception {
        int testImage = 4;
        testCard.setBackImagePath(testImage);
        assertEquals(testImage, testCard.getBackImagePath());
    }

    @Test
    public void testGetFrontText() throws Exception {
        assertEquals("FrontText", testCard.getFrontText());
    }

    @Test
    public void testGetBackText() throws Exception {
        assertEquals("BackText", testCard.getBackText());
    }

    @Test
    public void testGetFrontImagePath() throws Exception {
        assertEquals(2, testCard.getFrontImagePath());
    }

    @Test
    public void testGetBackImagePath() throws Exception {
        assertEquals(3, testCard.getBackImagePath());
    }
}