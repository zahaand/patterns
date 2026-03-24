package ru.zahaand.patterns.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Observer.
 * Проверяют подписку, отписку и уведомление наблюдателей.
 */
@DisplayName("Паттерн Observer — публикация новостей подписчикам")
class ObserverTest {

    private NewsFeedPublisher publisher;
    private List<String> receivedMessages;
    private Observer testObserver;

    @BeforeEach
    void setUp() {
        publisher = new NewsFeedPublisher();
        receivedMessages = new ArrayList<>();
        testObserver = message -> receivedMessages.add(message);
    }

    @Test
    @DisplayName("subscribe(): подписанный наблюдатель получает уведомление")
    void subscribe_observerReceivesNotification() {
        publisher.subscribe(testObserver);
        publisher.publishNews("Breaking news!");

        assertEquals(1, receivedMessages.size());
        assertEquals("Breaking news!", receivedMessages.get(0));
    }

    @Test
    @DisplayName("publishNews(): несколько подписчиков получают одно и то же уведомление")
    void publishNews_allSubscribersNotified() {
        List<String> messages1 = new ArrayList<>();
        List<String> messages2 = new ArrayList<>();

        publisher.subscribe(messages1::add);
        publisher.subscribe(messages2::add);
        publisher.publishNews("Hello observers!");

        assertEquals(List.of("Hello observers!"), messages1);
        assertEquals(List.of("Hello observers!"), messages2);
    }

    @Test
    @DisplayName("unsubscribe(): отписанный наблюдатель не получает уведомлений")
    void unsubscribe_observerNoLongerReceivesNotifications() {
        publisher.subscribe(testObserver);
        publisher.unsubscribe(testObserver);
        publisher.publishNews("You won't see this");

        assertTrue(receivedMessages.isEmpty(),
                "Отписанный наблюдатель не должен получать уведомления");
    }

    @Test
    @DisplayName("publishNews(): без подписчиков не вызывает исключений")
    void publishNews_noSubscribers_doesNotThrow() {
        assertDoesNotThrow(() -> publisher.publishNews("No one listening"),
                "Публикация без подписчиков не должна бросать исключение");
    }

    @Test
    @DisplayName("publishNews(): несколько сообщений доставляются по порядку")
    void publishNews_multipleMessages_deliveredInOrder() {
        publisher.subscribe(testObserver);
        publisher.publishNews("First");
        publisher.publishNews("Second");
        publisher.publishNews("Third");

        assertEquals(List.of("First", "Second", "Third"), receivedMessages);
    }
}
