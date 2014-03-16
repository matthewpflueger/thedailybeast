package com.newsweek;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class JSONWordOutputTest extends TestCase {

    @Test
    public void testBasicJSONWordOutput() throws IOException {
        Word[] words = {
                new Word("these"),
                new Word("are", true),
                new Word("words")
        };
        String out = new StringBuilder()
                .append("{")
                .append("\"words\":[")
                .append("{\"word\":\"these\",\"count\":1},")
                .append("{\"word\":\"are\",\"count\":1},")
                .append("{\"word\":\"words\",\"count\":1}")
                .append("],")
                .append("\"stopWordsIgnored\":0")
                .append("}")
                .toString();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new JSONWordOutput(baos, Arrays.asList(words).iterator());
        assertEquals(baos.toString(), out);
    }

    @Test
    public void testReducedJSONWordOutput() throws IOException {
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

        String out = new StringBuilder()
                .append("{")
                .append("\"words\":[")
                .append("{\"word\":\"words\",\"count\":3},")
                .append("{\"word\":\"and\",\"count\":2},")
                .append("{\"word\":\"these\",\"count\":2}")
                .append("],")
                .append("\"stopWordsIgnored\":5")
                .append("}")
                .toString();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new JSONWordOutput(baos, new WordReducerIterator(Arrays.asList(words).iterator()), 3);
        assertEquals(baos.toString(), out);
    }
}
