package ru.zahaand.patterns.visitor.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.visitor.ContentVisitor;

/**
 * Класс {@code ArchiverContentVisitor} реализует интерфейс {@link ContentVisitor} для архивации контента.
 * Этот посетитель позволяет автоматически архивировать различные типы контента, сохраняя их в архивном формате.
 * Используется в рамках паттерна Visitor для обработки различных типов контента, предоставляя универсальный способ
 * архивации контента без необходимости изменения классов контента.
 *
 * <h3>Основная функциональность:</h3>
 * <ul>
 *     <li><strong>Архивация контента</strong>: Для каждого типа контента создается архивный файл, содержащий исходный контент.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     ContentVisitor archiver = new ArchiverContentVisitor();
 *     Content textContent = new TextContent("Example text", user);
 *     Content imageContent = new ImageContent(ImageFormat.PNG, "EncodedImage", user);
 *
 *     // Архивация всех типов контента
 *     archiver.visit(textContent);
 *     archiver.visit(imageContent);
 * </pre>
 * <p>
 * В этом примере для каждого типа контента ({@link TextContent}, {@link ImageContent}) используется посетитель {@link ArchiverContentVisitor},
 * что позволяет динамически архивировать контент, сохраняя его в архивном формате.
 */
@Slf4j
public class ArchiverContentVisitor implements ContentVisitor {

    @Override
    public void visit(TextContent textContent) {
        log.info("Text content archiving...");
    }

    @Override
    public void visit(ImageContent imageContent) {
        log.info("Image content archiving...");
    }
}
