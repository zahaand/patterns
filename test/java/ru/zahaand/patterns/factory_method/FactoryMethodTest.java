package ru.zahaand.patterns.factory_method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.factory_method.impl.ImageContentFactory;
import ru.zahaand.patterns.factory_method.impl.TextContentFactory;
import ru.zahaand.patterns.model.impl.ImageContentData;
import ru.zahaand.patterns.model.impl.TextContentData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Factory Method.
 * Проверяют корректность создания объектов через абстрактные фабрики.
 */
@DisplayName("Паттерн Factory Method — создание контента через фабрики")
class FactoryMethodTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
    }

    @Test
    @DisplayName("TextContentFactory: создаёт объект типа TextContent")
    void textContentFactory_createsTextContent() {
        ContentFactory factory = new TextContentFactory();
        Content content = factory.createContent(new TextContentData("Hello", user));

        assertNotNull(content);
        assertInstanceOf(TextContent.class, content);
    }

    @Test
    @DisplayName("TextContentFactory: созданный объект содержит переданный текст")
    void textContentFactory_contentHasCorrectData() {
        ContentFactory factory = new TextContentFactory();
        TextContent content = (TextContent) factory.createContent(new TextContentData("Hello World", user));

        assertEquals("Hello World", content.getContent());
        assertEquals(user, content.getUser());
        assertEquals(ContentType.TEXT, content.getContentType());
    }

    @Test
    @DisplayName("ImageContentFactory: создаёт объект типа ImageContent")
    void imageContentFactory_createsImageContent() {
        ContentFactory factory = new ImageContentFactory();
        byte[] bytes = {1, 2, 3};
        Content content = factory.createContent(
                new ImageContentData(bytes, user, ImageFormat.JPEG, "/img/photo.jpg"));

        assertNotNull(content);
        assertInstanceOf(ImageContent.class, content);
    }

    @Test
    @DisplayName("ImageContentFactory: созданный объект содержит корректные данные изображения")
    void imageContentFactory_contentHasCorrectData() {
        ContentFactory factory = new ImageContentFactory();
        byte[] bytes = {1, 2, 3};
        ImageContent content = (ImageContent) factory.createContent(
                new ImageContentData(bytes, user, ImageFormat.PNG, "/img/photo.png"));

        assertEquals(ImageFormat.PNG, content.getImage().getFormat());
        assertEquals("/img/photo.png", content.getImage().getPath());
        assertEquals(ContentType.IMAGE, content.getContentType());
    }

    @Test
    @DisplayName("ImageContentFactory: возвращает null при неверном типе данных")
    void imageContentFactory_returnsNullForWrongDataType() {
        ContentFactory factory = new ImageContentFactory();
        Content content = factory.createContent(new TextContentData("wrong data", user));

        assertNull(content, "ImageContentFactory должна вернуть null для TextContentData");
    }

    @Test
    @DisplayName("Клиентский код работает с абстракцией ContentFactory")
    void clientCode_worksWithAbstractFactory() {
        // Демонстрация: клиент не зависит от конкретного типа фабрики
        ContentFactory textFactory = new TextContentFactory();
        ContentFactory imageFactory = new ImageContentFactory();

        Content text = textFactory.createContent(new TextContentData("text", user));
        Content image = imageFactory.createContent(
                new ImageContentData(new byte[]{1}, user, ImageFormat.JPEG, "/img/x.jpg"));

        assertInstanceOf(TextContent.class, text);
        assertInstanceOf(ImageContent.class, image);
    }
}
