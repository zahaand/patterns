package ru.zahaand.patterns.factory_method.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.factory_method.ContentFactory;
import ru.zahaand.patterns.model.ContentData;
import ru.zahaand.patterns.model.Image;
import ru.zahaand.patterns.model.impl.ImageContentData;

/**
 * <h1>Паттерн Factory Method — Конкретный Создатель (Concrete Creator) для изображений</h1>
 *
 * <p>Переопределяет фабричный метод {@link #createContent(ContentData)}, возвращая
 * конкретный тип продукта — {@link ImageContent}. Ожидает, что переданный {@link ContentData}
 * является экземпляром {@link ImageContentData} с форматом и путём к изображению.
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     ContentFactory factory = new ImageContentFactory();
 *     ContentData data = new ImageContentData(imageBytes, user, ImageFormat.JPEG, "/img/photo.jpg");
 *     Content content = factory.createContent(data);
 *     // content instanceof ImageContent == true
 * }</pre>
 *
 * @see ContentFactory
 * @see TextContentFactory
 */
@Slf4j
public class ImageContentFactory extends ContentFactory {

    /**
     * Создаёт объект {@link ImageContent} на основе переданных данных.
     *
     * @param contentData данные контента; должен быть экземпляром {@link ImageContentData}
     * @return новый экземпляр {@link ImageContent}, или {@code null} если переданы некорректные данные
     */
    @Override
    public Content createContent(ContentData contentData) {
        if (contentData instanceof ImageContentData imageData) {
            Image image = new Image(imageData.getFormat(), (byte[]) imageData.getData(), imageData.getPath());
            log.info("ImageContentFactory: creating ImageContent, format={}, path='{}'",
                    imageData.getFormat(), imageData.getPath());
            return new ImageContent(image, imageData.getUser());
        }
        log.warn("ImageContentFactory: expected ImageContentData but got {}", contentData.getClass().getSimpleName());
        return null;
    }
}
