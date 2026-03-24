package ru.zahaand.patterns.chain_of_responsibility;

import ru.zahaand.patterns.domain.Content;

/**
 * <h1>Паттерн Chain of Responsibility — Абстрактный Обработчик (Abstract Handler)</h1>
 *
 * <p>Базовый класс для всех обработчиков в цепочке. Реализует логику хранения
 * следующего обработчика ({@code next}) и автоматической передачи запроса по цепочке,
 * если текущий обработчик не может его обработать.
 *
 * <p>Подклассы реализуют метод {@link #canHandle(Content)} для проверки применимости
 * и {@link #doHandle(Content)} для выполнения конкретной обработки.
 * Метод {@link #handle(Content)} является шаблонным — он координирует процесс.
 *
 * <h3>Структура цепочки:</h3>
 * <pre>{@code
 *     ContentHandler chain = new TextContentHandler();
 *     chain.setNext(new ImageContentHandler());
 *
 *     // Передаём запрос в начало цепочки:
 *     chain.handle(content);
 *     // TextContentHandler обработает TEXT, передаст IMAGE следующему,
 *     // ImageContentHandler обработает IMAGE.
 * }</pre>
 *
 * @see ContentHandler
 * @see ru.zahaand.patterns.chain_of_responsibility.impl.TextContentHandler
 * @see ru.zahaand.patterns.chain_of_responsibility.impl.ImageContentHandler
 */
public abstract class AbstractContentHandler implements ContentHandler {

    private ContentHandler next;

    /**
     * {@inheritDoc}
     *
     * <p>Устанавливает следующий обработчик и возвращает его,
     * что позволяет выстраивать цепочку через цепочечный вызов:
     * <pre>{@code
     *     handler1.setNext(handler2).setNext(handler3);
     * }</pre>
     */
    @Override
    public ContentHandler setNext(ContentHandler handler) {
        this.next = handler;
        return handler;
    }

    /**
     * Шаблонный метод обработки запроса.
     * Если текущий обработчик применим ({@link #canHandle} возвращает {@code true}),
     * вызывает {@link #doHandle}. Иначе передаёт запрос следующему обработчику.
     *
     * @param content контент для обработки
     */
    @Override
    public void handle(Content content) {
        if (canHandle(content)) {
            doHandle(content);
        } else if (next != null) {
            next.handle(content);
        }
    }

    /**
     * Проверяет, может ли данный обработчик обработать указанный контент.
     *
     * @param content контент для проверки
     * @return {@code true} если обработчик применим к данному контенту
     */
    protected abstract boolean canHandle(Content content);

    /**
     * Выполняет конкретную обработку контента.
     * Вызывается только если {@link #canHandle} вернул {@code true}.
     *
     * @param content контент для обработки
     */
    protected abstract void doHandle(Content content);
}
