package ru.zahaand.patterns.template_method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.Image;
import ru.zahaand.patterns.template_method.impl.ImageContentProcessor;
import ru.zahaand.patterns.template_method.impl.TextContentProcessor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Template Method.
 * Проверяют, что скелет алгоритма фиксирован в базовом классе,
 * а подклассы корректно реализуют отдельные шаги.
 */
@DisplayName("Паттерн Template Method — фиксированный алгоритм с переопределяемыми шагами")
class TemplateMethodTest {

    private User user;
    private TextContent textContent;
    private ImageContent imageContent;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
        textContent = new TextContent("Hello Template", user);
        Image image = new Image(ImageFormat.JPEG, new byte[]{1, 2, 3}, "/img/photo.jpg");
        imageContent = new ImageContent(image, user);
    }

    @Test
    @DisplayName("TextContentProcessor: обрабатывает TextContent без исключений")
    void textProcessor_processesTextContent_doesNotThrow() {
        ContentProcessor processor = new TextContentProcessor();
        assertDoesNotThrow(() -> processor.processTemplateMethod(textContent),
                "TextContentProcessor не должен бросать исключение для TextContent");
    }

    @Test
    @DisplayName("TextContentProcessor: бросает исключение для ImageContent (неверный тип)")
    void textProcessor_throwsForImageContent() {
        ContentProcessor processor = new TextContentProcessor();
        assertThrows(IllegalArgumentException.class,
                () -> processor.processTemplateMethod(imageContent),
                "TextContentProcessor должен бросить IllegalArgumentException для ImageContent");
    }

    @Test
    @DisplayName("ImageContentProcessor: обрабатывает ImageContent без исключений")
    void imageProcessor_processesImageContent_doesNotThrow() {
        ContentProcessor processor = new ImageContentProcessor();
        assertDoesNotThrow(() -> processor.processTemplateMethod(imageContent),
                "ImageContentProcessor не должен бросать исключение для ImageContent");
    }

    @Test
    @DisplayName("ImageContentProcessor: бросает исключение для TextContent (неверный тип)")
    void imageProcessor_throwsForTextContent() {
        ContentProcessor processor = new ImageContentProcessor();
        assertThrows(IllegalArgumentException.class,
                () -> processor.processTemplateMethod(textContent),
                "ImageContentProcessor должен бросить IllegalArgumentException для TextContent");
    }

    @Test
    @DisplayName("processTemplateMethod(): метод финален — нельзя переопределить скелет алгоритма")
    void processTemplateMethod_isFinal() throws NoSuchMethodException {
        java.lang.reflect.Method method = ContentProcessor.class
                .getMethod("processTemplateMethod", ru.zahaand.patterns.domain.Content.class);
        assertTrue(java.lang.reflect.Modifier.isFinal(method.getModifiers()),
                "processTemplateMethod() должен быть final для защиты скелета алгоритма");
    }
}
