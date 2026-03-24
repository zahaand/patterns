package ru.zahaand.patterns.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.User;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Memento.
 * Проверяют сохранение/восстановление состояния и инкапсуляцию данных снимка.
 */
@DisplayName("Паттерн Memento — сохранение и восстановление состояния")
class MementoTest {

    private User user;
    private UserContactsManager manager;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "original@mail.ru")
                .name("Test User")
                .build();
        manager = new UserContactsManager();
    }

    @Test
    @DisplayName("saveUserContactsState: сохраняет текущее состояние контактов")
    void save_storesCurrentState() {
        manager.saveUserContactsState(user);
        // Проверяем косвенно: после изменения и undo — данные восстановились
        user.setEmail("changed@mail.ru");
        manager.undoUserContactsState(user);
        assertEquals("original@mail.ru", user.getEmail());
    }

    @Test
    @DisplayName("undoUserContactsState: восстанавливает email после изменения")
    void undo_restoresEmail() {
        manager.saveUserContactsState(user);
        user.setEmail("new@mail.ru");
        assertEquals("new@mail.ru", user.getEmail());

        manager.undoUserContactsState(user);
        assertEquals("original@mail.ru", user.getEmail(),
                "Email должен вернуться к сохранённому значению");
    }

    @Test
    @DisplayName("undoUserContactsState: восстанавливает mobilePhone после изменения")
    void undo_restoresMobilePhone() {
        manager.saveUserContactsState(user);
        user.setMobilePhone("+7 000 000-00-00");

        manager.undoUserContactsState(user);
        assertEquals("+7 999 000-00-00", user.getMobilePhone(),
                "Номер телефона должен вернуться к сохранённому значению");
    }

    @Test
    @DisplayName("undoUserContactsState: несколько undo восстанавливают состояния в порядке LIFO")
    void undo_multipleSaves_lifoOrder() {
        manager.saveUserContactsState(user);             // снимок 1: original@mail.ru

        user.setEmail("second@mail.ru");
        manager.saveUserContactsState(user);             // снимок 2: second@mail.ru

        user.setEmail("third@mail.ru");
        assertEquals("third@mail.ru", user.getEmail());

        manager.undoUserContactsState(user);             // восстанавливаем снимок 2
        assertEquals("second@mail.ru", user.getEmail());

        manager.undoUserContactsState(user);             // восстанавливаем снимок 1
        assertEquals("original@mail.ru", user.getEmail());
    }

    @Test
    @DisplayName("undoUserContactsState: undo на пустой истории не изменяет состояние")
    void undo_emptyHistory_doesNotChangeState() {
        // История пуста — undo не должен ничего делать
        manager.undoUserContactsState(user);
        assertEquals("original@mail.ru", user.getEmail(),
                "Undo на пустой истории не должен изменять состояние");
    }

    @Test
    @DisplayName("UserContactsMemento: маркерный интерфейс не даёт доступа к данным снимка извне")
    void memento_isMarkerInterface() {
        manager.saveUserContactsState(user);
        UserContactsMemento memento = user.saveContactsState();

        // Убеждаемся, что интерфейс маркерный — нет публичных методов для доступа к данным
        assertEquals(0, UserContactsMemento.class.getMethods().length,
                "UserContactsMemento должен быть маркерным интерфейсом без публичных методов");
    }
}
