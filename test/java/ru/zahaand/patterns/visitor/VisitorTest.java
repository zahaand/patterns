package ru.zahaand.patterns.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.Image;
import ru.zahaand.patterns.visitor.impl.ArchiverContentVisitor;
import ru.zahaand.patterns.visitor.impl.InfoPrinterContentVisitor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Visitor.
 * Проверяют, что оба посетителя корректно обрабатывают TextContent и ImageContent
 * без изменения классов контента.
 */
@DisplayName("Паттерн Visitor — операции над контентом без изменения его классов")
class VisitorTest {

    private User user;
    private TextContent textContent;
    private ImageContent imageContent;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
        textContent = new TextContent("Hello Visitor", user);
        Image image = new Image(ImageFormat.PNG, new byte[]{1, 2, 3}, "/img/photo.png");
        imageContent = new ImageContent(image, user);
    }

    @Test
    @DisplayName("ArchiverContentVisitor: архивирует TextContent без исключений")
    void archiverVisitor_visitsTextContent_doesNotThrow() {
        ContentVisitor visitor = new ArchiverContentVisitor();
        assertDoesNotThrow(() -> visitor.visit(textContent),
                "ArchiverContentVisitor не должен бросать исключение для TextContent");
    }

    @Test
    @DisplayName("ArchiverContentVisitor: архивирует ImageContent без исключений")
    void archiverVisitor_visitsImageContent_doesNotThrow() {
        ContentVisitor visitor = new ArchiverContentVisitor();
        assertDoesNotThrow(() -> visitor.visit(imageContent),
                "ArchiverContentVisitor не должен бросать исключение для ImageContent");
    }

    @Test
    @DisplayName("InfoPrinterContentVisitor: выводит информацию о TextContent без исключений")
    void infoPrinterVisitor_visitsTextContent_doesNotThrow() {
        ContentVisitor visitor = new InfoPrinterContentVisitor();
        assertDoesNotThrow(() -> visitor.visit(textContent),
                "InfoPrinterContentVisitor не должен бросать исключение для TextContent");
    }

    @Test
    @DisplayName("InfoPrinterContentVisitor: выводит информацию об ImageContent без исключений")
    void infoPrinterVisitor_visitsImageContent_doesNotThrow() {
        ContentVisitor visitor = new InfoPrinterContentVisitor();
        assertDoesNotThrow(() -> visitor.visit(imageContent),
                "InfoPrinterContentVisitor не должен бросать исключение для ImageContent");
    }

    @Test
    @DisplayName("acceptVisitor(): TextContent принимает посетителя через метод acceptVisitor()")
    void textContent_acceptVisitor_doesNotThrow() {
        ContentVisitor visitor = new InfoPrinterContentVisitor();
        assertDoesNotThrow(() -> textContent.acceptVisitor(visitor),
                "textContent.acceptVisitor() не должен бросать исключение");
    }

    @Test
    @DisplayName("acceptVisitor(): ImageContent принимает посетителя через метод acceptVisitor()")
    void imageContent_acceptVisitor_doesNotThrow() {
        ContentVisitor visitor = new ArchiverContentVisitor();
        assertDoesNotThrow(() -> imageContent.acceptVisitor(visitor),
                "imageContent.acceptVisitor() не должен бросать исключение");
    }
}
