package ru.zahaand.patterns.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.memento.UserContactsMemento;
import ru.zahaand.patterns.observer.Observer;
import ru.zahaand.patterns.state.UserState;

import java.util.UUID;

@Slf4j
@Data
@NoArgsConstructor
public class User implements Observer {

    private UUID id;
    private String mobilePhone;
    private String email;

    private String name;
    private Integer age;

    private String country;
    private String city;
    private String address;

    /**
     * Является частью реализации паттерна State
     */
    private UserState state;

    /**
     * Является частью реализации паттерна Builder.
     * Создает экземпляр класса User с помощью UserBuilder
     *
     * @param builder объект UserBuilder, используемый для создания экземпляра
     */
    private User(UserBuilder builder) {
        this.id = builder.uuid;
        this.mobilePhone = builder.mobilePhone;
        this.email = builder.email;
        this.name = builder.name;
        this.age = builder.age;
        this.country = builder.country;
        this.city = builder.city;
        this.address = builder.address;
    }

    /**
     * Класс UserBuilder представляет собой реализацию паттерна Builder,
     * предназначенную для создания объектов класса {@link User} с заданными параметрами.
     *
     * <p>Паттерн UserBuilder обеспечивает следующие ключевые преимущества:
     * <ul>
     *     <li><strong>Читаемость и гибкость</strong>: Позволяет создавать объекты с большим количеством параметров
     *     в удобной и читаемой форме, не прибегая к использованию конструкторов с большим количеством аргументов.</li>
     *     <li><strong>Гибкость в расширении</strong>: Добавление новых параметров в объект {@link User} не требует
     *     изменения существующих методов билдера, что упрощает расширение функциональности класса.</li>
     *     <li><strong>Упрощение тестирования</strong>: Использование билдера позволяет легко создавать объекты с различными
     *     настройками для тестирования различных сценариев использования.</li>
     * </ul>
     *
     * <p>Методы класса UserBuilder позволяют устанавливать все необходимые поля объекта {@link User}, включая
     * обязательные поля, такие как {@code uuid}, {@code mobilePhone} и {@code email}, а также дополнительные поля,
     * такие как {@code name}, {@code age}, {@code country}, {@code city} и {@code address}. После установки всех
     * необходимых параметров вызывается метод {@link #build()}, который создает и возвращает новый экземпляр класса {@link User}.
     *
     * <p>Пример использования:
     * <pre>
     * User user = new User.UserBuilder(UUID.randomUUID(), "+1 800 1234567", "test@example.ru")
     *         .name("Test Testov")
     *         .age(30)
     *         .country("Russia")
     *         .city("Saint-Petersburg)
     *         .address("123 Main St")
     *         .build();
     * </pre>
     */
    public static class UserBuilder {

        private final UUID uuid;
        private final String mobilePhone;
        private final String email;
        private String name;
        private Integer age;
        private String country;
        private String city;
        private String address;

        public UserBuilder(UUID uuid, String mobilePhone, String email) {
            this.uuid = uuid;
            this.mobilePhone = mobilePhone;
            this.email = email;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public UserBuilder country(String country) {
            this.country = country;
            return this;
        }

        public UserBuilder city(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    /**
     * Внутренний класс-record, реализующий интерфейс {@link UserContactsMemento}.
     * Представляет собой снимок состояния контактной информации пользователя, включающий номер мобильного телефона
     * и адрес электронной почты.
     */
    private record UserContactsMementoImpl(String mobilePhone, String email) implements UserContactsMemento {
    }

    /**
     * Создает снимок текущего состояния контактной информации пользователя.
     * Этот метод используется для реализации паттерна Memento и позволяет сохранять
     * состояние контактных данных пользователя на определенный момент времени.
     *
     * @return Объект типа {@link UserContactsMemento}, содержащий снимок текущего состояния
     * контактной информации пользователя.
     */
    public UserContactsMemento saveContactsState() {
        return new UserContactsMementoImpl(mobilePhone, email);
    }

    /**
     * Восстанавливает состояние контактной информации пользователя из предоставленного снимка.
     * Этот метод используется для реализации паттерна Memento и позволяет восстановить
     * состояние контактных данных пользователя из ранее сохраненного снимка.
     *
     * @param memento Объект типа {@link UserContactsMemento}, содержащий снимок состояния
     *                контактной информации пользователя, который необходимо восстановить.
     */
    public void restoreContactsState(UserContactsMemento memento) {
        this.mobilePhone = memento.mobilePhone();
        this.email = memento.email();
    }

    /**
     * Является частью реализации паттерна Observer.
     * Вызывается при получении пользователем нового сообщения новостной рассылки.
     *
     * @param message Сообщение новостной рассылки, полученное пользователем
     */
    @Override
    public void updateNews(String message) {
        log.info("Observer pattern demo: user {}, subscribed to the news, received a new message: {}", name, message);
    }

    /**
     * Является частью реализации паттерна State.
     * Делегирует выполнение действия состояния текущему состоянию.
     */
    public void performAction() {
        state.executeStateAction();
    }
}
