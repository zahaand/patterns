package ru.zahaand.patterns.chain_of_responsibility;

import ru.zahaand.patterns.domain.Content;

/**
 * <h1>Паттерн Chain of Responsibility. Цепочка обязанностей.</h1>
 * Интерфейс ContentHandler определяет методы, необходимые для участия в цепочке обязанностей.
 * Этот паттерн позволяет передавать запросы по цепочке обработчиков. Каждый обработчик решает,
 * может ли он обработать запрос сам, и если нет, передает запрос следующему обработчику в цепочке.
 *
 * <p>Применение паттерна "Цепочка обязанностей" позволяет избежать жесткой привязки отправителя запроса к его получателю,
 * делая систему более гибкой и расширяемой. Это особенно полезно в ситуациях, когда существует множество возможных обработчиков,
 * и заранее неизвестно, какой именно обработчик будет нужен для обработки конкретного запроса.
 *
 * <p>Основные преимущества использования паттерна "Цепочка обязанностей":
 * <ul>
 *     <li><strong>Гибкость</strong>: Легко добавлять новые типы запросов и обработчиков.</li>
 *     <li><strong>Отказоустойчивость</strong>: Можно легко организовать резервные механизмы обработки.</li>
 *     <li><strong>Декуплинг</strong>: Отправитель запроса не зависит от классов обработчиков.</li>
 * </ul>
 *
 * <p>Пример использования паттерна "Цепочка обязанностей":
 * <pre>
 *     ContentHandler textHandler = new TextContentHandler();
 *     ContentHandler imageHandler = new ImageContentHandler();
 *     textHandler.setNext(imageHandler);
 *
 *     Content content = new Content();
 *     content.setContentType(ContentType.TEXT);
 *     textHandler.handle(content);
 * </pre>
 * <p>
 * В этом примере запрос на обработку контента передается от TextContentHandler к ImageContentHandler,
 * если первый не может обработать запрос. Это демонстрирует, как паттерн "Цепочка обязанностей" позволяет организовать
 * гибкую и масштабируемую систему обработки запросов.
 */
public interface ContentHandler {

    void setNext(ContentHandler handler);

    void handle(Content content);
}
