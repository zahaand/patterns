package ru.zahaand.patterns.memento;

/**
 * <h1>Паттерн Memento. Снимок — маркерный интерфейс</h1>
 *
 * <p>Маркерный интерфейс снимка контактных данных пользователя.
 * Намеренно не содержит публичных методов — это ключевой принцип паттерна Memento.
 *
 * <h3>Роли паттерна в данной реализации:</h3>
 * <ul>
 *     <li><strong>Originator</strong> ({@link ru.zahaand.patterns.domain.User}) — создаёт снимок
 *         ({@code saveContactsState()}) и восстанавливает из него состояние ({@code restoreContactsState()}).
 *         Знает о приватной реализации {@code UserContactsMementoImpl} и приводит к ней через {@code instanceof}.</li>
 *     <li><strong>Memento</strong> (этот интерфейс + приватный record {@code UserContactsMementoImpl} внутри {@code User}) —
 *         хранит снимок состояния. Данные доступны <em>только</em> Originator'у.</li>
 *     <li><strong>Caretaker</strong> ({@link UserContactsManager}) — хранит историю снимков,
 *         но <em>не может читать их данные</em>, так как интерфейс маркерный.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     User user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
 *     UserContactsManager manager = new UserContactsManager();
 *
 *     manager.saveUserContactsState(user);          // сохраняем снимок
 *     user.setEmail("new@mail.ru");                 // изменяем
 *     manager.undoUserContactsState(user);          // восстанавливаем из снимка
 *     // user.getEmail() == "user@mail.ru"
 * }</pre>
 *
 * @see UserContactsManager
 * @see ru.zahaand.patterns.domain.User
 */
public interface UserContactsMemento {
    // Маркерный интерфейс — методы доступа к данным намеренно скрыты.
    // Реализация UserContactsMementoImpl находится внутри класса User (private record).
}
