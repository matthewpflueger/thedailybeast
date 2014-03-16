package com.newsweek;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordIterator extends ReadOnlyIterator<Word> {

    private static final Pattern regex = Pattern.compile("\\b\\w+\\b");

    private Iterator<String> strings;
    private Word next;
    private Matcher matcher;


    public WordIterator(Iterator<String> strings) {
        this.strings = strings;
    }

    @Override
    public boolean hasNext() {
        return findNext();
    }

    @Override
    public Word next() {
        if (next == null && !findNext()) {
            throw new NoSuchElementException();
        }
        Word tmp = next;
        next = null;
        return tmp;
    }

    private boolean findNext() {
        if (next != null) {
            return true;
        }
        if (matcher != null && matcher.find()) {
            next = new Word(matcher.group());
            return true;
        }
        if (strings == null || !strings.hasNext()) {
            return false;
        }

        matcher = regex.matcher(strings.next());
        return findNext();
    }
}
