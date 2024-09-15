package ru.zahaand.patterns.abstract_factory.impl;

import ru.zahaand.patterns.abstract_factory.ContentCreator;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.model.ContentData;

public class TextContentCreator extends ContentCreator {

    @Override
    public Content createContent(ContentType contentType, ContentData contentData) {

        return new TextContent(String.valueOf(contentData.getData()), contentData.getUser());
    }
}
