    package ru.zahaand.patterns.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.strategy.ContentProcessingStrategy;

@Slf4j
public class ImageContentProcessingStrategy implements ContentProcessingStrategy {
    @Override
    public void processContent(Content content) {
        ImageContent imageContent = (ImageContent) content;
        log.info("Processing image content: {}", imageContent);
    }
}
