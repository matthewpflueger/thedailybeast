package com.newsweek;

import java.util.*;

public class WordReducerIterator extends ReadOnlyIterator<Word> {

    private int stopWordsIgnoredCount;

    private TreeMap<Integer, List<Word>> ordered = new TreeMap<Integer, List<Word>>();
    private Iterator<List<Word>> allWords;
    private Iterator<Word> currentWords;
    private Word next;

    public WordReducerIterator(Iterator<Word> words) {
        Map<String, Word> counter = new HashMap<String, Word>();

        while (words.hasNext()) {
            Word word = words.next();
            if (word.isStopWord()) {
                stopWordsIgnoredCount++;
                continue;
            }

            Word cw = counter.get(word.getWord());
            cw = cw == null ? word : new Word(cw.getWord(), cw.getSeenCount() + 1);
            counter.put(cw.getWord(), cw);
        }

        for (Word word: counter.values()) {
            List<Word> list = ordered.get(word.getSeenCount());
            if (list == null) {
                list = new ArrayList<Word>();
            }
            list.add(word);
            ordered.put(word.getSeenCount(), list);
        }

        allWords = ordered.descendingMap().values().iterator();
    }

    public int getStopWordsIgnoredCount() {
        return stopWordsIgnoredCount;
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

        next = currentWords != null && currentWords.hasNext() ? currentWords.next() : null;
        if (next != null) {
            return true;
        }
        if (allWords == null || !allWords.hasNext()) {
            return false;
        }

        List<Word> tmp = allWords.next();
        Collections.sort(tmp);
        currentWords = tmp.iterator();
        return findNext();
    }
}
