package ru.zahaand.patterns.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.strategy.ContentProcessingStrategy;

/**
 * <h1>Паттерн Strategy — Конкретная стратегия обработки текстового контента</h1>
 *
 * <p>Использует {@code instanceof} с pattern matching (Java 16+)
 * вместо небезопасного приведения типа, что исключает {@link ClassCastException}.
 */
@Slf4j
public class TextContentProcessingStrategy implements ContentProcessingStrategy {

    @Override
    public void processContent(Content content) {
        if (content instanceof TextContent textContent) {
            log.info("TextContentProcessingStrategy: processing text content id={}, text='{}'",
                    textContent.getId(), textContent.getContent());
        } else {
            log.warn("TextContentProcessingStrategy: expected TextContent but got {}",
                    content.getClass().getSimpleName());
        }
    }
}
