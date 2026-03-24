package ru.zahaand.patterns.chain_of_responsibility.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.chain_of_responsibility.AbstractContentHandler;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;

import static ru.zahaand.patterns.enums.ContentType.TEXT;

/**
 * <h1>Паттерн Chain of Responsibility — Конкретный обработчик текстового контента</h1>
 *
 * <p>Обрабатывает запросы типа {@link ru.zahaand.patterns.enums.ContentType#TEXT}.
 * Наследует от {@link AbstractContentHandler} логику хранения следующего обработчика
 * и автоматической передачи по цепочке.
 *
 * <p>Использует {@code instanceof} с pattern matching (Java 16+)
 * вместо небезопасного приведения типов, что исключает {@link ClassCastException}.
 *
 * @see AbstractContentHandler
 * @see ImageContentHandler
 */
@Slf4j
public class TextContentHandler extends AbstractContentHandler {

    @Override
    protected boolean canHandle(Content content) {
        return TEXT.equals(content.getContentType());
    }

    @Override
    protected void doHandle(Content content) {
        if (content instanceof TextContent textContent) {
            log.info("TextContentHandler: processing TEXT content id={}", textContent.getId());
            String processedText = textContent.getContent().toUpperCase();
            textContent.setContent(processedText);
            log.info("TextContentHandler: text transformed to uppercase: '{}'", processedText);
        }
    }
}
