package ru.zahaand.patterns.adapter.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.zahaand.patterns.adapter.ExternalUser;
import ru.zahaand.patterns.adapter.ExternalUserSystem;
import ru.zahaand.patterns.domain.User;

/**
 * Класс UserToExternalUserAdapter представляет собой реализацию паттерна Adapter,
 * позволяя преобразовывать объекты класса User в объекты класса ExternalUser, которые могут быть использованы
 * внешней системой, тем самым обеспечивая совместимость между двумя различными интерфейсами.
 *
 * <p>Паттерн Adapter обеспечивает следующие ключевые преимущества:
 * <ul>
 *     <li><strong>Совместимость интерфейсов</strong>: Паттерн Adapter позволяет объектам с
 *     несовместимыми интерфейсами работать вместе, преобразуя интерфейс одного класса в интерфейс
 *     другого.</li>
 *     <li><strong>Переиспользование существующего кода</strong>: Adapter позволяет переиспользовать
 *     существующий код без его изменения, что упрощает интеграцию с новыми или внешними системами.</li>
 *     <li><strong>Гибкость в изменении интерфейсов</strong>: Паттерн Adapter позволяет легко изменять
 *     интерфейсы классов, не влияя на клиентский код, что упрощает поддержку и развитие системы.</li>
 * </ul>
 *
 * <p>Паттерн Adapter целесообразно применять в следующих ситуациях:
 * <ul>
 *     <li><strong>Когда необходимо обеспечить совместимость между интерфейсами, которые не совместимы изначально</strong>:
 *     Паттерн Adapter позволяет объектам с несовместимыми интерфейсами работать вместе, преобразуя интерфейс одного класса
 *     в интерфейс другого. Это особенно полезно, когда необходимо интегрировать сторонние библиотеки или компоненты,
 *     интерфейсы которых отличаются от ожидаемых в вашей системе.</li>
 *     <li><strong>Когда нужно использовать существующий класс, и его интерфейс не соответствует интерфейсу,
 *     который требуется клиенту</strong>: Adapter позволяет "обернуть" существующий класс в адаптер,
 *     который предоставляет нужный интерфейс, не изменяя при этом исходный класс.
 *     Это позволяет переиспользовать существующий код без его изменения.</li>
 *     <li><strong>Когда интерфейс класса должен быть изменен, но изменение этого интерфейса нежелательно или невозможно</strong>:
 *     Паттерн Adapter позволяет создать адаптер, который изменяет интерфейс класса, не затрагивая сам класс.
 *     Это может быть полезно, когда интерфейс класса неудобен для использования или не соответствует стандартам.</li>
 *     <li><strong>Когда необходимо поддерживать несколько версий интерфейса</strong>:
 *     Adapter может быть использован для создания адаптеров к различным версиям интерфейса,
 *     что позволяет клиентам использовать классы с разными версиями интерфейса без необходимости изменения кода клиентов.</li>
 * </ul>
 *
 * <p>Пример использования паттерна Adapter:
 * <pre>
 *     User user = new User();
 *     UserToExternalUserAdapter adapter = new UserToExternalUserAdapter();
 *     ExternalUser externalUser = adapter.adapt(user);
 *     adapter.processUser(externalUser);
 * </pre>
 *
 * <p>В этом примере объект User адаптируется к формату ExternalUser, который затем может быть
 * обработан внешней системой. Это демонстрирует, как паттерн Adapter может быть использован для
 * обеспечения совместимости между различными интерфейсами в системе.
 */
@Slf4j
@Component
public class UserToExternalUserAdapter implements ExternalUserSystem {

    @Override
    public void processUser(ExternalUser externalUser) {
        log.info("Processing external user: {}. Contact Info: {}. Personal Info: {}",
                externalUser.getId(), externalUser.getContactInfo(), externalUser.getPersonalInfo());
    }

    public ExternalUser adapt(User user) {
        String id = user.getId().toString();
        String contactInfo = "Mobile: %s, Email: %s".formatted(user.getMobilePhone(), user.getEmail());
        String personalInfo = "Name: %s, Age: %s, Country: %s".formatted(user.getName(), user.getAge(), user.getCountry());

        return new ExternalUser(id, contactInfo, personalInfo);
    }
}
