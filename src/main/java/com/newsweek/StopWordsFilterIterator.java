package com.newsweek;

import java.io.*;
import java.util.*;

public class StopWordsFilterIterator extends ReadOnlyIterator<Word> {

    private Set<String> stopWordsSet = new HashSet<String>();
    private Iterator<Word> words;
    private Word next;
    private int numberStopWordsSeen;

    public StopWordsFilterIterator(InputStream stopWords, Iterator<Word> words) throws IOException {
        this.words = words;

        BufferedReader br = new BufferedReader(new InputStreamReader(stopWords));
        String line = null;
        while ((line = br.readLine()) != null) {
            stopWordsSet.add(line);
        }
    }

    public String[] getStopWords() {
        return stopWordsSet.toArray(new String[stopWordsSet.size()]);
    }

    public int getNumberStopWordsSeen() {
        return numberStopWordsSeen;
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
        if (words == null || !words.hasNext()) {
            return false;
        }

        next = words.next();
        if (stopWordsSet.contains(next.getWord())) {
            next = new Word(next.getWord(), true, next.getSeenCount());
            numberStopWordsSeen++;
        }

        return true;
    }
}
