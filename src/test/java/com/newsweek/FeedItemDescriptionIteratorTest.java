package com.newsweek;

import junit.framework.TestCase;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FeedItemDescriptionIteratorTest extends TestCase {
    private static final String testXml = "feedItemDescriptionIteratorTest.xml";

    @Test
    public void testIteration() throws XMLStreamException {
        Iterator<String> fidi = new FeedItemDescriptionIterator(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(testXml));

        assertNotNull(fidi);
        assertTrue(fidi.hasNext());
        assertEquals("If law enforcement can track people with impunity, why not fight fire with fire and record the police?", fidi.next());
        for (int i=0; i < 8; i++) {
            fidi.next();
        }
        assertTrue(fidi.hasNext());
        assertEquals("Parents taking their children to see the real-life Hannah Montana were greeted by simulated oral sex and more. Should they have known better?", fidi.next());
        assertFalse(fidi.hasNext());

        NoSuchElementException nsee = null;
        try {
            fidi.next();
        } catch (NoSuchElementException e) {
            nsee = e;
        }
        assertNotNull(nsee);
    }
}
