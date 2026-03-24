package ru.zahaand.patterns.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.Image;
import ru.zahaand.patterns.strategy.impl.ImageContentProcessingStrategy;
import ru.zahaand.patterns.strategy.impl.TextContentProcessingStrategy;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Strategy.
 * Проверяют безопасную типизацию через instanceof и корректную обработку контента.
 */
@DisplayName("Паттерн Strategy — безопасная обработка контента")
class StrategyTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
    }

    @Test
    @DisplayName("TextContentProcessingStrategy: обрабатывает TextContent без исключений")
    void textStrategy_processesTextContent() {
        ContentProcessingStrategy strategy = new TextContentProcessingStrategy();
        TextContent content = new TextContent("Hello", user);

        assertDoesNotThrow(() -> strategy.processContent(content),
                "TextContentProcessingStrategy не должна бросать исключение для TextContent");
    }

    @Test
    @DisplayName("TextContentProcessingStrategy: не бросает исключение для ImageContent (instanceof защита)")
    void textStrategy_doesNotThrowForImageContent() {
        ContentProcessingStrategy strategy = new TextContentProcessingStrategy();
        Image image = new Image(ImageFormat.JPEG, new byte[]{1, 2}, "/img/test.jpg");
        ImageContent content = new ImageContent(image, user);

        // Без instanceof защиты это привело бы к ClassCastException
        assertDoesNotThrow(() -> strategy.processContent(content),
                "instanceof защита должна предотвратить ClassCastException");
    }

    @Test
    @DisplayName("ImageContentProcessingStrategy: обрабатывает ImageContent без исключений")
    void imageStrategy_processesImageContent() {
        ContentProcessingStrategy strategy = new ImageContentProcessingStrategy();
        Image image = new Image(ImageFormat.PNG, new byte[]{1, 2, 3}, "/img/photo.png");
        ImageContent content = new ImageContent(image, user);

        assertDoesNotThrow(() -> strategy.processContent(content),
                "ImageContentProcessingStrategy не должна бросать исключение для ImageContent");
    }

    @Test
    @DisplayName("ImageContentProcessingStrategy: не бросает исключение для TextContent (instanceof защита)")
    void imageStrategy_doesNotThrowForTextContent() {
        ContentProcessingStrategy strategy = new ImageContentProcessingStrategy();
        TextContent content = new TextContent("Hello", user);

        // Без instanceof защиты это привело бы к ClassCastException
        assertDoesNotThrow(() -> strategy.processContent(content),
                "instanceof защита должна предотвратить ClassCastException");
    }

    @Test
    @DisplayName("Content.process(): стратегия применяется через метод контента")
    void content_process_appliesStrategy() {
        TextContent content = new TextContent("Hello", user);
        ContentProcessingStrategy strategy = new TextContentProcessingStrategy();

        // Паттерн Strategy через метод process() на самом Content
        assertDoesNotThrow(() -> content.process(strategy));
    }
}
