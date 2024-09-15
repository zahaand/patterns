package ru.zahaand.patterns.abstract_factory;

import ru.zahaand.patterns.abstract_factory.impl.ImageContentCreator;
import ru.zahaand.patterns.abstract_factory.impl.TextContentCreator;
import ru.zahaand.patterns.enums.ContentType;

/**
 * Класс ContentCreatorFactory представляет собой реализацию паттерна Abstract Factory,
 * предназначенную для создания объектов, отвечающих за создание контента различных типов.
 *
 * <p>Паттерн Abstract Factory обеспечивает следующие ключевые преимущества:
 * <ul>
 *     <li><strong>Абстракция от создания объектов</strong>: Клиенты кода не заботятся о конкретных классах объектов,
 *     которые создаются, а используют абстрактную фабрику для получения объектов, что упрощает код и делает его более гибким.</li>
 *     <li><strong>Гибкость в расширении</strong>: Добавление новых типов контента не требует изменения кода клиента,
 *     так как создание объектов осуществляется через абстрактную фабрику, которая может быть расширена для поддержки новых типов.</li>
 *     <li><strong>Упрощение тестирования</strong>: Использование абстрактной фабрики позволяет легко заменять реализации объектов на моки
 *     или стабы в тестах, что упрощает тестирование различных сценариев использования.</li>
 * </ul>
 *
 * <p>Метод {@link #getContentCreator(ContentType)} класса ContentCreatorFactory принимает параметр,
 * определяющий тип создаваемого контента, и возвращает экземпляр соответствующего класса, отвечающего за создание контента.
 * Это позволяет создавать объекты контента различных типов без необходимости знать о конкретных классах объектов,
 * что соответствует принципам паттерна Abstract Factory.
 */
public class ContentCreatorFactory {

    /**
     * Создает и возвращает ContentCreator на основе предоставленного типа контента.
     *
     * @param contentType тип создаваемого контента
     * @return соответствующий ContentCreator
     */
    public ContentCreator getContentCreator(ContentType contentType) {
        return switch (contentType) {
            case TEXT -> new TextContentCreator();
            case IMAGE -> new ImageContentCreator();
        };
    }
}
