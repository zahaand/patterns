package ru.zahaand.patterns.memento;

import ru.zahaand.patterns.domain.User;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Класс {@code UserContactsManager} реализует логику управления историей состояний
 * контактной информации пользователя в рамках паттерна Memento. Этот класс позволяет сохранять
 * различные состояния контактной информации пользователя и при необходимости восстанавливать их,
 * что может быть полезно для реализации функционала отмены изменений или возврата к предыдущему состоянию.
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     User user = new User(...); // Создание объекта пользователя с начальной контактной информацией
 *     UserContactsManager userContactsManager = new UserContactsManager();
 *
 *     // Сохранение начального состояния контактной информации
 *     userContactsManager.saveUserContactsState(user);
 *
 *     // Изменение контактной информации пользователя
 *     user.setEmail("new-email@example.com");
 *     user.setMobilePhone("+1234567890");
 *
 *     // Сохранение измененного состояния контактной информации
 *     userContactsManager.saveUserContactsState(user);
 *
 *     // Восстановление предыдущего состояния контактной информации пользователя
 *     userContactsManager.undoUserContactsState(user);
 * </pre>
 * <p>
 * В этом примере демонстрируется создание объекта {@code User}, сохранение его начального состояния контактной информации,
 * изменение этой информации, повторное сохранение измененного состояния и последующее восстановление предыдущего состояния
 * с помощью класса {@code UserContactsManager}. Это показывает, как можно использовать паттерн Memento для управления историей состояний
 * в контексте изменения данных пользователя.
 */
public class UserContactsManager {

    private final Deque<UserContactsMemento> userContactsHistory = new ArrayDeque<>();

    public void saveUserContactsState(User user) {
        userContactsHistory.push(user.saveContactsState());
    }

    public void undoUserContactsState(User user) {
        if (!userContactsHistory.isEmpty()) {
            user.restoreContactsState(userContactsHistory.pop());
        }
    }
}
