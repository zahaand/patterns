package ru.zahaand.patterns.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.strategy.ContentProcessingStrategy;

@Slf4j
public class TextContentProcessingStrategy implements ContentProcessingStrategy {

    @Override
    public void processContent(Content content) {
        TextContent textContent = (TextContent) content;
        log.info("Processing text content: {}", textContent);
    }
}
