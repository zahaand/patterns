package ru.zahaand.patterns.factory_method;

import ru.zahaand.patterns.domain.Content;

/**
 * <h1>Паттерн Factory Method. Фабричный Метод — Абстрактный Создатель (Creator)</h1>
 *
 * <p>Определяет интерфейс для создания объекта, но оставляет конкретным подклассам
 * решение о том, какой класс инстанцировать. Factory Method позволяет классу делегировать
 * создание объекта подклассам.
 *
 * <p><strong>Отличие от Simple Factory</strong> ({@link ContentFactoryMethod}):
 * <ul>
 *     <li><strong>Simple Factory</strong> — конкретный класс с методом-переключателем ({@code switch/if}).
 *         Нарушает Open/Closed Principle: добавление нового типа требует изменения существующего кода.</li>
 *     <li><strong>Factory Method</strong> — абстрактный создатель + конкретные подклассы.
 *         Добавление нового типа = создание нового подкласса без изменения существующего кода.</li>
 * </ul>
 *
 * <h3>Структура паттерна в данном пакете:</h3>
 * <ul>
 *     <li>{@link ContentFactory} — абстрактный создатель (этот класс)</li>
 *     <li>{@link ru.zahaand.patterns.factory_method.impl.TextContentFactory} — конкретный создатель для текста</li>
 *     <li>{@link ru.zahaand.patterns.factory_method.impl.ImageContentFactory} — конкретный создатель для изображений</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     ContentFactory factory = new TextContentFactory();
 *     Content content = factory.createContent(new TextContentData("Hello world", user));
 *
 *     // Смена типа контента — просто меняем фабрику, не меняем клиентский код:
 *     factory = new ImageContentFactory();
 *     content = factory.createContent(new ImageContentData(image, user));
 * }</pre>
 *
 * @see ru.zahaand.patterns.factory_method.impl.TextContentFactory
 * @see ru.zahaand.patterns.factory_method.impl.ImageContentFactory
 * @see ContentFactoryMethod
 */
public abstract class ContentFactory {

    /**
     * Фабричный метод — определяет интерфейс создания объекта.
     * Конкретные подклассы переопределяют этот метод, возвращая
     * нужный тип {@link Content}.
     *
     * @param contentData данные, необходимые для создания контента
     * @return созданный объект {@link Content}
     */
    public abstract Content createContent(ru.zahaand.patterns.model.ContentData contentData);
}
