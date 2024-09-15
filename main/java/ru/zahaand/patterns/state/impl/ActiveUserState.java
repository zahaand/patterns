package ru.zahaand.patterns.state.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.state.UserState;

/**
 * <h1>Класс ActiveUserState</h1>
 * Класс {@code ActiveUserState} реализует интерфейс {@link UserState} и представляет состояние активного пользователя.
 * В этом состоянии пользователю доступны все функции системы.
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     User user = new User(new ActiveUserState());
 *     user.executeStateAction();    // Пользователь активен. Доступны все функции.
 * </pre>
 */
@Slf4j
public class ActiveUserState implements UserState {

    @Override
    public void executeStateAction() {
        log.info("The user is active. All functions are available.");
    }
}
