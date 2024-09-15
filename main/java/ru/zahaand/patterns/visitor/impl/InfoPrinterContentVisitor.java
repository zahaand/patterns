package ru.zahaand.patterns.visitor.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.visitor.ContentVisitor;

/**
 * Класс {@code InfoPrinterContentVisitor} реализует интерфейс {@link ContentVisitor} для печати информации о контенте.
 * Этот посетитель позволяет получать детальную информацию о контенте в формате, удобном для чтения человеком.
 * Используется в рамках паттерна Visitor для обработки различных типов контента, предоставляя универсальный способ
 * получения информации о контенте без необходимости изменения классов контента.
 *
 * <h3>Основная функциональность:</h3>
 * <ul>
 *     <li><strong>Печать информации о контенте</strong>: Для каждого типа контента выводится его идентификатор и информация о владельце.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     ContentVisitor infoPrinter = new InfoPrinterContentVisitor();
 *     Content textContent = new TextContent("Example text", user);
 *     Content imageContent = new ImageContent(ImageFormat.PNG, "EncodedImage", user);
 *
 *     // Получение информации обо всех типах контента
 *     infoPrinter.visit(textContent);
 *     infoPrinter.visit(imageContent);
 * </pre>
 * <p>
 * В этом примере для каждого типа контента ({@link TextContent}, {@link ImageContent}) используется посетитель {@link InfoPrinterContentVisitor},
 * что позволяет динамически получить информацию о контенте в удобочитаемом виде.
 */
@Slf4j
public class InfoPrinterContentVisitor implements ContentVisitor {

    @Override
    public void visit(TextContent textContent) {
        log.info("Text content info printing...");

        User user = textContent.getUser();
        System.out.printf("""
                        TEXT content id: %s.
                        User id: %s.
                        User contacts: %s %s
                        """,
                textContent.getId(),
                user.getId(),
                user.getMobilePhone(),
                user.getEmail());
    }

    @Override
    public void visit(ImageContent imageContent) {
        log.info("Image content info printing...");

        User user = imageContent.getUser();
        System.out.printf("""
                        IMAGE content id: %s.
                        User id: %s.
                        User contacts: %s %s
                        """,
                imageContent.getId(),
                user.getId(),
                user.getMobilePhone(),
                user.getEmail());
    }
}
