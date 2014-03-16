package com.newsweek;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StopWordsFilterIteratorTest extends TestCase {

    @Test
    public void testStopWordsFilterIteration() throws IOException {
        Word[] words = {
                new Word("a"),
                new Word("bunch"),
                new Word("of"),
                new Word("words"),
                new Word("s"),
                new Word("fantastic")};

        StopWordsFilterIterator swfi = new StopWordsFilterIterator(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("stopWordsFilterIteratorTest.txt"),
                Arrays.asList(words).iterator());

        String[] stopWords = swfi.getStopWords();
        assertNotNull(stopWords);
        assertEquals(35, stopWords.length);

        assertTrue(swfi instanceof Iterator);
        assertTrue(swfi.hasNext());
        assertEquals(new Word("a", true), swfi.next());
        assertEquals(new Word("bunch", false), swfi.next());
        assertEquals(new Word("of", true), swfi.next());
        assertEquals(new Word("words", false), swfi.next());
        assertEquals(new Word("s", true), swfi.next());
        assertEquals(new Word("fantastic", false), swfi.next());
        assertEquals(3, swfi.getNumberStopWordsSeen());
        assertFalse(swfi.hasNext());

        NoSuchElementException nsee = null;
        try {
            swfi.next();
        } catch (NoSuchElementException e) {
            nsee = e;
        }
        assertNotNull(nsee);
    }
}
