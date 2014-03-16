package com.newsweek;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WordNormalizerIteratorTest extends TestCase {

    @Test
    public void testWordNormalizerIteration() {
        Word[] words = {
                new Word("Test"),
                new Word("W0rDs"),
                new Word("Are"),
                new Word("cool")};

        Iterator<Word> wni = new WordNormalizerIterator(Arrays.asList(words).iterator());
        assertTrue(wni.hasNext());
        assertEquals(new Word("test"), wni.next());
        assertEquals(new Word("w0rds"), wni.next());
        assertEquals(new Word("are"), wni.next());
        assertEquals(new Word("cool"), wni.next());
        assertFalse(wni.hasNext());

        NoSuchElementException nsee = null;
        try {
            wni.next();
        } catch (NoSuchElementException e) {
            nsee = e;
        }
        assertNotNull(nsee);
    }
}
