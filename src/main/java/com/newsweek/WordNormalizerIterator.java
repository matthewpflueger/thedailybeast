package com.newsweek;

import java.util.Iterator;

public class WordNormalizerIterator extends ReadOnlyIterator<Word> {

    private Iterator<Word> words;

    public WordNormalizerIterator(Iterator<Word> words) {
        this.words = words;
    }

    @Override
    public boolean hasNext() {
        return words != null && words.hasNext();
    }

    @Override
    public Word next() {
        Word next = words.next();
        return new Word(next.getWord().toLowerCase(), next.isStopWord(), next.getSeenCount());
    }
}
