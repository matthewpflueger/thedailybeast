package com.newsweek;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WordIteratorTest extends TestCase {

    @Test
    public void testWordIteration() {
        String[] sentences = {
            "'four' w0rd test sentences",
            "that are really dumb",
            " but will ",
            "",
            "hope_fully ",
            "now-\"end\"."
        };

        Iterator<Word> wi = new WordIterator(Arrays.asList(sentences).iterator());
        assertTrue(wi.hasNext());
        assertEquals(new Word("four"), wi.next());
        assertEquals(new Word("w0rd"), wi.next());
        for (int i = 0; i < 8; i++) {
            assertNotNull(wi.next());
        }
        assertTrue(wi.hasNext());
        assertEquals(new Word("hope_fully"), wi.next());
        assertEquals(new Word("now"), wi.next());
        assertEquals(new Word("end"), wi.next());

        assertFalse(wi.hasNext());

        NoSuchElementException nsee = null;
        try {
            wi.next();
        } catch (NoSuchElementException e) {
            nsee = e;
        }
        assertNotNull(nsee);
    }
}
