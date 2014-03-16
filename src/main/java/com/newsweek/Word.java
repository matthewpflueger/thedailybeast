package com.newsweek;

public class Word implements Comparable<Word> {

    private String word;
    private boolean isStopWord;
    private int seenCount;

    public Word(String word) {
        this(word, false, 1);
    }

    public Word(String word, int seenCount) {
        this(word, false, seenCount);
    }

    public Word(String word, boolean isStopWord) {
        this(word, isStopWord, 1);
    }

    public Word(String word, boolean isStopWord, int seenCount) {
        this.word = word;
        this.isStopWord = isStopWord;
        this.seenCount = seenCount;
    }

    public String getWord() {
        return word;
    }

    public boolean isStopWord() {
        return isStopWord;
    }

    public int getSeenCount() {
        return seenCount;
    }

    public int hashCode() {
        return this.word.hashCode()
                + Boolean.valueOf(isStopWord).hashCode()
                + Integer.valueOf(seenCount).hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Word)) {
            return false;
        }

        Word that = (Word) o;
        return this.word.equals(that.word)
                && this.isStopWord == that.isStopWord
                && this.seenCount == that.seenCount;
    }

    @Override
    public int compareTo(Word that) {
        return word.compareTo(that.word);
    }
}
