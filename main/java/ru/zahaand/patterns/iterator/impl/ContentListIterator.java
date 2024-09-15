package ru.zahaand.patterns.iterator.impl;

import org.springframework.stereotype.Component;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.iterator.ContentIterator;

import java.util.Iterator;
import java.util.List;

@Component
public class ContentListIterator implements ContentIterator {

    private final Iterator<Content> iterator;

    public ContentListIterator(List<Content> contents) {
        this.iterator = contents.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Content next() {
        return iterator.next();
    }
}
