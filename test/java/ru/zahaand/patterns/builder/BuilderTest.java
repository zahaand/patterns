package ru.zahaand.patterns.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.domain.User;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Builder (реализован внутри класса User через UserBuilder).
 * Проверяют корректность создания объектов с обязательными и опциональными параметрами.
 */
@DisplayName("Паттерн Builder — создание объекта User через UserBuilder")
class BuilderTest {

    @Test
    @DisplayName("UserBuilder: создаёт объект с обязательными параметрами")
    void builder_createsUserWithRequiredFields() {
        UUID id = UUID.randomUUID();
        User user = new User.UserBuilder(id, "+7 999 000-00-00", "user@mail.ru").build();

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("+7 999 000-00-00", user.getMobilePhone());
        assertEquals("user@mail.ru", user.getEmail());
    }

    @Test
    @DisplayName("UserBuilder: опциональные поля null по умолчанию")
    void builder_optionalFieldsAreNullByDefault() {
        User user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();

        assertNull(user.getName(), "Имя должно быть null если не задано");
        assertNull(user.getAge(), "Возраст должен быть null если не задан");
        assertNull(user.getCity(), "Город должен быть null если не задан");
    }

    @Test
    @DisplayName("UserBuilder: создаёт объект со всеми опциональными параметрами")
    void builder_createsUserWithAllFields() {
        User user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru")
                .name("Иван Иванов")
                .age(30)
                .country("Russia")
                .city("Moscow")
                .address("Lenina 1")
                .build();

        assertEquals("Иван Иванов", user.getName());
        assertEquals(30, user.getAge());
        assertEquals("Russia", user.getCountry());
        assertEquals("Moscow", user.getCity());
        assertEquals("Lenina 1", user.getAddress());
    }

    @Test
    @DisplayName("UserBuilder: каждый вызов build() создаёт новый объект")
    void builder_createsDifferentInstances() {
        User.UserBuilder builder = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru");

        User user1 = builder.build();
        User user2 = builder.build();

        assertNotSame(user1, user2, "Каждый вызов build() должен создавать новый экземпляр");
    }

    @Test
    @DisplayName("UserBuilder: цепочечные вызовы методов возвращают builder")
    void builder_methodChaining_returnsSameBuilder() {
        UUID id = UUID.randomUUID();
        User.UserBuilder builder = new User.UserBuilder(id, "+7 999 000-00-00", "user@mail.ru");

        User.UserBuilder result = builder.name("Test").age(25).city("SPb");
        assertSame(builder, result, "Методы builder должны возвращать тот же объект builder");
    }
}
