package ru.zahaand.patterns.abstract_factory;

import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.model.ContentData;

public abstract class ContentCreator {

    public abstract Content createContent(ContentType contentType, ContentData contentData);
}
