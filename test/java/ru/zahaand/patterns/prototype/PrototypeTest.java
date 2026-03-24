package ru.zahaand.patterns.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.Image;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Prototype.
 * Проверяют корректность клонирования: новый UUID, независимость данных.
 */
@DisplayName("Паттерн Prototype — клонирование контента")
class PrototypeTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru")
                .name("Test User")
                .build();
    }

    @Test
    @DisplayName("TextContent clone: клон имеет новый UUID")
    void textContent_clone_hasNewId() {
        TextContent original = new TextContent("Hello World", user);
        TextContent clone = (TextContent) original.clone();

        assertNotNull(clone);
        assertNotEquals(original.getId(), clone.getId(),
                "Клон должен иметь новый UUID, отличный от оригинала");
    }

    @Test
    @DisplayName("TextContent clone: клон содержит те же данные")
    void textContent_clone_hasSameData() {
        TextContent original = new TextContent("Hello World", user);
        TextContent clone = (TextContent) original.clone();

        assertEquals(original.getContent(), clone.getContent(),
                "Клон должен содержать те же текстовые данные");
        assertEquals(original.getUser(), clone.getUser(),
                "Клон должен ссылаться на того же пользователя");
    }

    @Test
    @DisplayName("TextContent clone: изменение клона не влияет на оригинал")
    void textContent_clone_isIndependent() {
        TextContent original = new TextContent("Original text", user);
        TextContent clone = (TextContent) original.clone();

        clone.setContent("Modified text");

        assertEquals("Original text", original.getContent(),
                "Изменение клона не должно влиять на оригинал");
        assertEquals("Modified text", clone.getContent());
    }

    @Test
    @DisplayName("ImageContent clone: клон имеет новый UUID")
    void imageContent_clone_hasNewId() {
        byte[] bytes = {1, 2, 3, 4};
        Image image = new Image(ImageFormat.JPEG, bytes, "/img/test.jpg");
        ImageContent original = new ImageContent(image, user);
        ImageContent clone = (ImageContent) original.clone();

        assertNotNull(clone);
        assertNotEquals(original.getId(), clone.getId(),
                "Клон ImageContent должен иметь новый UUID");
    }

    @Test
    @DisplayName("ImageContent clone: глубокое копирование — изменение байт клона не влияет на оригинал")
    void imageContent_clone_deepCopiesBytes() {
        byte[] originalBytes = {1, 2, 3, 4};
        Image image = new Image(ImageFormat.JPEG, originalBytes.clone(), "/img/test.jpg");
        ImageContent original = new ImageContent(image, user);
        ImageContent clone = (ImageContent) original.clone();

        // Изменяем байты в клоне
        clone.getImage().getContent()[0] = 99;

        assertEquals(1, original.getImage().getContent()[0],
                "Изменение байт в клоне не должно влиять на оригинал (глубокое копирование)");
    }
}
