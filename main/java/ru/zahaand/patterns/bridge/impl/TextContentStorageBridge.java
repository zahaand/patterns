package ru.zahaand.patterns.bridge.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.zahaand.patterns.bridge.ContentStorageBridge;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.service.ContentService;

public class TextContentStorageBridge extends ContentStorageBridge {

    private final ContentService contentService;

    public TextContentStorageBridge(Content content,
                                    @Qualifier("textContentService") ContentService contentService) {
        super(content);
        this.contentService = contentService;
    }

    @Override
    public void store() {
        contentService.create(content);
    }
}
