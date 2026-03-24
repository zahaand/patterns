package ru.zahaand.patterns.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.state.impl.ActiveUserState;
import ru.zahaand.patterns.state.impl.BlockedUserState;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна State.
 * Проверяют делегирование поведения текущему состоянию и переключение состояний.
 */
@DisplayName("Паттерн State — изменение поведения пользователя через состояния")
class StateTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
    }

    @Test
    @DisplayName("ActiveUserState: performAction() не бросает исключение")
    void activeState_performAction_doesNotThrow() {
        user.setState(new ActiveUserState());
        assertDoesNotThrow(() -> user.performAction(),
                "performAction() с ActiveUserState не должен бросать исключение");
    }

    @Test
    @DisplayName("BlockedUserState: performAction() не бросает исключение")
    void blockedState_performAction_doesNotThrow() {
        user.setState(new BlockedUserState());
        assertDoesNotThrow(() -> user.performAction(),
                "performAction() с BlockedUserState не должен бросать исключение");
    }

    @Test
    @DisplayName("setState(): переключение состояния — Active → Blocked → Active")
    void setState_canSwitchStateMultipleTimes() {
        user.setState(new ActiveUserState());
        assertDoesNotThrow(() -> user.performAction());

        user.setState(new BlockedUserState());
        assertDoesNotThrow(() -> user.performAction());

        user.setState(new ActiveUserState());
        assertDoesNotThrow(() -> user.performAction());
    }

    @Test
    @DisplayName("ActiveUserState: является экземпляром UserState")
    void activeState_implementsUserState() {
        UserState state = new ActiveUserState();
        assertInstanceOf(UserState.class, state);
    }

    @Test
    @DisplayName("BlockedUserState: является экземпляром UserState")
    void blockedState_implementsUserState() {
        UserState state = new BlockedUserState();
        assertInstanceOf(UserState.class, state);
    }
}
