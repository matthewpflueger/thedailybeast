package com.newsweek;

import java.io.*;
import java.util.Iterator;

public class JSONWordOutput {

    public static final int DEFAULT_MAX_WORDS = 10;

    public JSONWordOutput(OutputStream os, Iterator<Word> words) throws IOException {
        this(os, words, DEFAULT_MAX_WORDS);
    }

    public JSONWordOutput(OutputStream os, Iterator<Word> words, int maxWords) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write("{\"words\":[");

        for (int i = maxWords; i > 0 && words.hasNext(); --i) {
            Word word = words.next();
            bw.write("{\"word\":\"");
            bw.write(word.getWord());
            bw.write("\",\"count\":");
            bw.write(Integer.toString(word.getSeenCount()));
            bw.write("}");

            if (i - 1 > 0 && words.hasNext()) {
                bw.write(",");
            }
        }

        bw.write("],\"stopWordsIgnored\":");
        if (words instanceof WordReducerIterator) {
            bw.write(Integer.toString(((WordReducerIterator) words).getStopWordsIgnoredCount()));
        } else {
            bw.write("0");
        }

        bw.write("}");
        bw.flush();
    }
}
