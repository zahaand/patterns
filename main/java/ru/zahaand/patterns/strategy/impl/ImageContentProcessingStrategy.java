package ru.zahaand.patterns.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.strategy.ContentProcessingStrategy;

/**
 * <h1>Паттерн Strategy — Конкретная стратегия обработки изображений</h1>
 *
 * <p>Использует {@code instanceof} с pattern matching (Java 16+)
 * вместо небезопасного приведения типа, что исключает {@link ClassCastException}.
 */
@Slf4j
public class ImageContentProcessingStrategy implements ContentProcessingStrategy {

    @Override
    public void processContent(Content content) {
        if (content instanceof ImageContent imageContent) {
            log.info("ImageContentProcessingStrategy: processing image content id={}, format={}",
                    imageContent.getId(), imageContent.getImage().getFormat());
        } else {
            log.warn("ImageContentProcessingStrategy: expected ImageContent but got {}",
                    content.getClass().getSimpleName());
        }
    }
}
