package ru.zahaand.patterns.factory_method;

import org.springframework.stereotype.Component;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.ContentData;
import ru.zahaand.patterns.model.Image;
import ru.zahaand.patterns.model.impl.ImageContentData;

/**
 * Класс ContentFactoryMethod представляет собой реализацию паттерна Фабричный Метод,
 * предназначенную для создания объектов контента различных типов.
 *
 * <p>Паттерн Фабричный Метод обеспечивает следующие ключевые преимущества:
 * <ul>
 *     <li><strong>Абстракция от создания объектов</strong>: Клиенты кода не заботятся о конкретных классах объектов,
 *     которые создаются, а используют фабрику для получения объектов, что упрощает код и делает его более гибким.</li>
 *     <li><strong>Гибкость в расширении</strong>: Добавление новых типов контента не требует изменения кода клиента,
 *     так как создание объектов осуществляется через фабричный метод, который может быть расширен для поддержки новых типов.</li>
 *     <li><strong>Упрощение тестирования</strong>: Использование фабрики позволяет легко заменять реализации объектов на моки
 *     или стабы в тестах, что упрощает тестирование различных сценариев использования.</li>
 * </ul>
 *
 * <p>Метод {@link #createContent(ContentType, ContentData)} класса ContentFactoryMethod принимает параметры, определяющие тип создаваемого
 * контента и данные, и возвращает экземпляр соответствующего класса контента. Это позволяет создавать объекты контента различных типов
 * без необходимости знать о конкретных классах объектов, что соответствует принципам паттерна Фабричный Метод.
 */
@Component
public class ContentFactoryMethod {

    /**
     * Создает контент на основе заданного типа контента.
     *
     * @param contentType тип создаваемого контента
     * @param contentData данные контента
     * @return созданный контент
     */
    public Content createContent(ContentType contentType, ContentData contentData) {
        Object data = contentData.getData();
        User user = contentData.getUser();

        return switch (contentType) {
            case TEXT -> new TextContent(String.valueOf(data), user);
            case IMAGE -> {
                if (contentData instanceof ImageContentData imageContentData) {
                    ImageFormat format = imageContentData.getFormat();
                    byte[] content = (byte[]) data;
                    String path = imageContentData.getPath();

                    Image image = new Image(format, content, path);
                    yield new ImageContent(image, user);

                } else {
                    yield null;
                }
            }
        };
    }
}
