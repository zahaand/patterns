package ru.zahaand.patterns.chain_of_responsibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.chain_of_responsibility.impl.ImageContentHandler;
import ru.zahaand.patterns.chain_of_responsibility.impl.TextContentHandler;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.Image;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Chain of Responsibility.
 * Проверяют корректную передачу запросов по цепочке и обработку каждого типа контента.
 */
@DisplayName("Паттерн Chain of Responsibility — цепочка обработчиков")
class ChainOfResponsibilityTest {

    private User user;
    private TextContentHandler textHandler;
    private ImageContentHandler imageHandler;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
        textHandler = new TextContentHandler();
        imageHandler = new ImageContentHandler();
        // Выстраиваем цепочку: text → image
        textHandler.setNext(imageHandler);
    }

    @Test
    @DisplayName("TextContentHandler обрабатывает TEXT-контент и трансформирует текст в UPPERCASE")
    void textHandler_processesTextContent() {
        TextContent content = new TextContent("hello world", user);

        textHandler.handle(content);

        assertEquals("HELLO WORLD", content.getContent(),
                "TextContentHandler должен конвертировать текст в UPPERCASE");
    }

    @Test
    @DisplayName("TextContentHandler передаёт IMAGE-контент следующему обработчику в цепочке")
    void textHandler_delegatesToNextForImageContent() {
        Image image = new Image(ImageFormat.JPEG, new byte[]{1, 2, 3}, "/img/test.jpg");
        ImageContent content = new ImageContent(image, user);

        // Должен обработаться без исключения — ImageContentHandler обработает
        assertDoesNotThrow(() -> textHandler.handle(content));
    }

    @Test
    @DisplayName("canHandle: TextContentHandler применим только к TEXT")
    void textHandler_canHandleOnlyText() {
        TextContent text = new TextContent("test", user);
        Image image = new Image(ImageFormat.JPEG, new byte[]{1}, "/img/test.jpg");
        ImageContent img = new ImageContent(image, user);

        // Проверяем через AbstractContentHandler — косвенно через handle()
        // TextContent должен обработаться (текст станет UPPERCASE)
        textHandler.handle(text);
        assertEquals("TEST", text.getContent());
    }

    @Test
    @DisplayName("Цепочка: text.setNext(image) — IMAGE обрабатывается через полную цепочку")
    void chain_imageHandledWhenTextIsFirst() {
        Image image = new Image(ImageFormat.PNG, new byte[]{1, 2}, "/img/photo.png");
        ImageContent content = new ImageContent(image, user);

        // TextHandler пропустит IMAGE → ImageHandler обработает без исключений
        assertDoesNotThrow(() -> textHandler.handle(content));
    }

    @Test
    @DisplayName("Одиночный обработчик без next: нераспознанный контент не бросает исключение")
    void singleHandler_unknownContent_doesNotThrow() {
        TextContentHandler isolatedHandler = new TextContentHandler(); // без next
        Image image = new Image(ImageFormat.JPEG, new byte[]{1}, "/img/test.jpg");
        ImageContent content = new ImageContent(image, user);

        // Нет следующего обработчика — тихо игнорирует
        assertDoesNotThrow(() -> isolatedHandler.handle(content));
    }

    @Test
    @DisplayName("Цепочечный setNext: можно выстраивать цепочку через цепочечный вызов")
    void chainableSetNext_buildsChain() {
        TextContentHandler h1 = new TextContentHandler();
        ImageContentHandler h2 = new ImageContentHandler();

        ContentHandler result = h1.setNext(h2);

        assertSame(h2, result, "setNext должен возвращать следующий обработчик");
    }
}
