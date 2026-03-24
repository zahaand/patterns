package ru.zahaand.patterns.factory_method.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.factory_method.ContentFactory;
import ru.zahaand.patterns.model.ContentData;

/**
 * <h1>Паттерн Factory Method — Конкретный Создатель (Concrete Creator) для текстового контента</h1>
 *
 * <p>Переопределяет фабричный метод {@link #createContent(ContentData)}, возвращая
 * конкретный тип продукта — {@link TextContent}. Клиентский код работает с абстракцией
 * {@link ContentFactory}, не зная о конкретном типе создаваемого объекта.
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     ContentFactory factory = new TextContentFactory();
 *     Content content = factory.createContent(new TextContentData("Hello world", user));
 *     // content instanceof TextContent == true
 * }</pre>
 *
 * @see ContentFactory
 * @see ImageContentFactory
 */
@Slf4j
public class TextContentFactory extends ContentFactory {

    /**
     * Создаёт объект {@link TextContent} на основе переданных данных.
     *
     * @param contentData данные контента; ожидается, что {@code getData()} вернёт {@link String}
     * @return новый экземпляр {@link TextContent}
     */
    @Override
    public Content createContent(ContentData contentData) {
        String text = String.valueOf(contentData.getData());
        log.info("TextContentFactory: creating TextContent with data: '{}'", text);
        return new TextContent(text, contentData.getUser());
    }
}
