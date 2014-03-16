package com.newsweek;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;

public class App {
    public static void main( String[] args ) throws IOException, XMLStreamException {
        new JSONWordOutput(System.out,
                new WordReducerIterator(
                new StopWordsFilterIterator(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream("stopwords.txt"),
                new WordNormalizerIterator(
                new WordIterator(
                new FeedItemDescriptionIterator(
                        new URL("http://www.thedailybeast.com/feed/articles.rss.xml?limit=100").openStream()))))));
    }
}
