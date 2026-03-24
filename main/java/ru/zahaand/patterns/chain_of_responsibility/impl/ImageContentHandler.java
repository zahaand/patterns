package ru.zahaand.patterns.chain_of_responsibility.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.chain_of_responsibility.AbstractContentHandler;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.enums.ContentType;

/**
 * <h1>Паттерн Chain of Responsibility — Конкретный обработчик изображений</h1>
 *
 * <p>Обрабатывает запросы типа {@link ContentType#IMAGE}.
 * Наследует от {@link AbstractContentHandler} логику хранения следующего обработчика
 * и автоматической передачи по цепочке.
 *
 * <p>Использует {@code instanceof} с pattern matching (Java 16+)
 * вместо небезопасного приведения типов, что исключает {@link ClassCastException}.
 *
 * @see AbstractContentHandler
 * @see TextContentHandler
 */
@Slf4j
public class ImageContentHandler extends AbstractContentHandler {

    @Override
    protected boolean canHandle(Content content) {
        return ContentType.IMAGE.equals(content.getContentType());
    }

    @Override
    protected void doHandle(Content content) {
        if (content instanceof ImageContent imageContent) {
            log.info("ImageContentHandler: processing IMAGE content id={}", imageContent.getId());
            log.info("ImageContentHandler: resizing image format={} to 800x600 pixels",
                    imageContent.getImage().getFormat());
        }
    }
}
