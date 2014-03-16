package com.newsweek;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WordReducerIteratorTest extends TestCase {

    @Test
    public void testWordCountIteration() {
        Word[] words = {
                new Word("these"),
                new Word("are", true),
                new Word("words"),
                new Word("and"),
                new Word("so", true),
                new Word("are", true),
                new Word("these"),
                new Word("but"),
                new Word("are", true),
                new Word("better"),
                new Word("words"),
                new Word("and"),
                new Word("much"),
                new Word("more"),
                new Word("popular"),
                new Word("words"),
                new Word("to", true)
        };

        WordReducerIterator wri = new WordReducerIterator(Arrays.asList(words).iterator());
        assertTrue(wri.hasNext());
        assertEquals(new Word("words", 3), wri.next());
        assertEquals(new Word("and", 2), wri.next());
        assertEquals(new Word("these", 2), wri.next());
        assertEquals(new Word("better", 1), wri.next());
        assertEquals(new Word("but", 1), wri.next());
        assertEquals(new Word("more", 1), wri.next());
        assertEquals(new Word("much", 1), wri.next());
        assertEquals(new Word("popular", 1), wri.next());
        assertFalse(wri.hasNext());
        assertEquals(5, wri.getStopWordsIgnoredCount());

        NoSuchElementException nsee = null;
        try {
            wri.next();
        } catch (NoSuchElementException e) {
            nsee = e;
        }
        assertNotNull(nsee);
    }
}
