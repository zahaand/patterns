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
 * <h1>Простая Фабрика (Simple Factory) — для сравнения с паттерном Factory Method</h1>
 *
 * <p><strong>Важно:</strong> Этот класс реализует <em>Simple Factory</em> (Простую Фабрику),
 * а не классический паттерн Factory Method. Разница принципиальная:
 *
 * <ul>
 *     <li><strong>Simple Factory (этот класс)</strong> — конкретный класс с оператором {@code switch}.
 *         Добавление нового типа контента требует изменения этого класса — нарушение
 *         принципа Open/Closed.</li>
 *     <li><strong>Factory Method ({@link ContentFactory})</strong> — абстрактный создатель +
 *         конкретные подклассы {@link ru.zahaand.patterns.factory_method.impl.TextContentFactory} и
 *         {@link ru.zahaand.patterns.factory_method.impl.ImageContentFactory}.
 *         Добавление нового типа = новый подкласс, без изменения существующего кода.</li>
 * </ul>
 *
 * <p>Simple Factory удобна для небольших проектов, где количество типов стабильно.
 * Factory Method предпочтительнее, когда набор типов может расширяться.
 *
 * @see ContentFactory
 * @see ru.zahaand.patterns.factory_method.impl.TextContentFactory
 * @see ru.zahaand.patterns.factory_method.impl.ImageContentFactory
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
