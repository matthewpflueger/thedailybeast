package com.newsweek;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class FeedItemDescriptionIterator extends ReadOnlyIterator<String> {

    private XMLStreamReader xsr;
    private String next;

    public FeedItemDescriptionIterator(InputStream is) throws XMLStreamException {
        xsr = XMLInputFactory.newInstance().createXMLStreamReader(is);
    }

    @Override
    public boolean hasNext() {
        return findNext();
    }

    @Override
    public String next() {
        if (next == null && !findNext()) {
            throw new NoSuchElementException();
        }
        String tmp = next;
        next = null;
        return tmp;
    }

    private boolean findNext() {
        if (next != null) {
            return true;
        }
        if (xsr == null) {
            return false;
        }

        boolean isItem = false;

        try {
            while (next == null && xsr.hasNext()) {
                if (xsr.isStartElement()) {
                    QName name = xsr.getName();

                    if (isItem && "description".equals(name.getLocalPart()) && "".equals(name.getPrefix())) {
                        next = xsr.getElementText();
                    }
                    if ("item".equals(xsr.getLocalName())) {
                        isItem = true;
                    }
                }
                if (xsr.isEndElement() && xsr.getLocalName() == "item") {
                    isItem = false;
                }
                xsr.next();
            }
        } catch (XMLStreamException se) {
            throw new NoSuchElementException(se.getMessage());
        } finally {
            if (next == null) {
                xsr = null;
            }
        }

        return next != null;
    }
}
