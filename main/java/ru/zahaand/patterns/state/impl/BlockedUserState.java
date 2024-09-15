package ru.zahaand.patterns.state.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.state.UserState;

/**
 * <h1>Класс BlockedUserState</h1>
 * Класс {@code BlockedUserState} реализует интерфейс {@link UserState} и представляет состояние заблокированного пользователя.
 * В этом состоянии доступ пользователя к функциям системы ограничен.
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     User user = new User(new ActiveUserState());
 *     user.setState(new BlockedUserState());
 *     user.executeStateAction();    // Пользователь заблокирован. Доступ ограничен.
 * </pre>
 */
@Slf4j
public class BlockedUserState implements UserState {

    @Override
    public void executeStateAction() {
        log.info("The user is blocked. Access is limited.");
    }
}
