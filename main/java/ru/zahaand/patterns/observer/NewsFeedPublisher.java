package ru.zahaand.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Класс NewsFeedPublisher</h1>
 * Класс {@code NewsFeedPublisher} реализует интерфейс {@link Subject} и служит публикацией новостей пользователям.
 * Используется для демонстрации паттерна OBSERVER, позволяя пользователям подписываться на новости и получать уведомления о них.
 * <p>
 * Каждый пользователь, подписанный на новостной канал, будет получать уведомления о новостях через метод {@link Observer#updateNews(String)}.
 *
 * <h3>Основные возможности:</h3>
 * <ul>
 *     <li><strong>Подписка наблюдателей</strong>: Метод {@link #subscribe(Observer)} позволяет добавлять новых наблюдателей (пользователей).</li>
 *     <li><strong>Отмена подписки</strong>: Метод {@link #unsubscribe(Observer)} позволяет удалять наблюдателей.</li>
 *     <li><strong>Публикация новостей</strong>: Метод {@link #publishNews(String)} позволяет публиковать новости, уведомляя всех подписанных наблюдателей.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     User user = new User(...);
 *     NewsFeedPublisher publisher = new NewsFeedPublisher();
 *
 *     publisher.subscribe(user); // Подписываем пользователя на новости
 *     publisher.publishNews("Breaking news!"); // Публикуем новость
 *
 * </pre>
 * <p>
 * В этом примере пользователь подписывается на новостной канал и получает уведомление о новости.
 *
 * @see Observer
 * @see Subject
 */
public class NewsFeedPublisher implements Subject {

    private final List<Observer> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void publishNews(String message) {
        for (Observer subscriber : subscribers) {
            subscriber.updateNews(message);
        }
    }
}

