package ru.zahaand.patterns.bridge.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.zahaand.patterns.bridge.ContentStorageBridge;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.service.ContentService;

public class ImageContentStorageBridge extends ContentStorageBridge {

    private final ContentService contentService;

    public ImageContentStorageBridge(Content content,
                                    @Qualifier("imageContentService") ContentService contentService) {
        super(content);
        this.contentService = contentService;
    }

    @Override
    public void store() {
        contentService.create(content);
    }
}
