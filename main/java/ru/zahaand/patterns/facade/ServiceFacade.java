package ru.zahaand.patterns.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.service.ContentService;
import ru.zahaand.patterns.service.UserService;

/**
 * <h1>Паттерн Facade. Фасадный объект</h1>
 * Класс {@code ServiceFacade} представляет собой реализацию паттерна "Facade".
 * Он предоставляет простой интерфейс для доступа к сложной системе сервисов.
 * Паттерн "Facade" упрощает использование сложной системы, скрывая детали реализации.
 *
 * <p>Класс {@code ServiceFacade} действует как единый точка входа для операций
 * с пользователями и контентом. Он абстрагирирует сложность внутренних сервисов,
 * предоставляя простой API для клиентов приложения.</p>
 *
 * <h3>Основные преимущества использования паттерна "Facade":</h3>
 * <ul>
 *     <li><strong>Упрощение работы с сложными системами</strong>: Предоставляет простой интерфейс для сложной системы.</li>
 *     <li><strong>Снижение зависимости</strong>: Клиенты могут работать с системой без необходимости понимать детали реализации.</li>
 *     <li><strong>Улучшение модульности</strong>: Разделение сложной системы на более мелкие части.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     ServiceFacade facade = new ServiceFacade();
 *
 *     User user = new User();
 *     user.setName("John Doe");
 *     User createdUser = facade.createUser(user);
 *
 *     Content content = new Content();
 *     content.setText("Hello, World!");
 *     Content createdContent = facade.createContent(content);
 * </pre>
 * <p>
 * В этом примере мы используем {@code ServiceFacade} для создания пользователя и контента.
 * Фасадный объект абстрагирирует сложность внутренних сервисов, предоставляя простой API для выполнения задач.</p>
 */
@Slf4j
@Component
public class ServiceFacade {

    private final UserService userService;
    private final ContentService textContentService;
    private final ContentService imageContentService;

    public ServiceFacade(UserService userService,
                         @Qualifier("textContentService") ContentService textContentService,
                         @Qualifier("imageContentService") ContentService imageContentService) {
        this.userService = userService;
        this.textContentService = textContentService;
        this.imageContentService = imageContentService;
    }

    /**
     * Создает нового пользователя с использованием {@link UserService}.
     *
     * @param user пользователь для создания
     * @return созданный пользователь
     */
    User createUser(User user) {
        log.info("Request to CREATE User: {}", user);
        return userService.create(user);
    }

    /**
     * Создает новый объект контента с использованием соответствующего {@link ContentService}
     * на основе типа контента.
     *
     * @param content объект контента для создания
     * @return созданный объект контента
     */
    Content createContent(Content content) {
        log.info("Request to CREATE Content: {}", content);
        return switch (content.getContentType()) {
            case TEXT -> textContentService.create(content);
            case IMAGE -> imageContentService.create(content);
        };
    }
}
