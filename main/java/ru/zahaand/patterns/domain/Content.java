package ru.zahaand.patterns.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.DisplayableContent;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.prototype.EntityPrototype;
import ru.zahaand.patterns.strategy.ContentProcessingStrategy;
import ru.zahaand.patterns.visitor.ContentVisitor;

import java.util.UUID;

@Slf4j
@Data
@NoArgsConstructor
public abstract class Content implements EntityPrototype, DisplayableContent {

    private UUID id;
    private ContentType contentType;
    protected User user;

    protected Content(ContentType contentType, User user) {
        id = UUID.randomUUID();
        this.contentType = contentType;
        this.user = user;
    }

    /**
     * Реализация метода clone интерфейса {@link EntityPrototype}, который является реализацией паттерна Prototype.
     * Метод предназначен для создания копии объекта класса Content, что позволяет создавать
     * независимые экземпляры объектов с сохранением их состояния, но без прямого влияния на оригинальные объекты.
     * <p> Пример использования:
     * <pre>
     *     Content originalContent = new Content(ContentType.TEXT, user);
     *     EntityPrototype clonedContent = originalContent.clone();
     * </pre>
     *
     * @return новый экземпляр объекта EntityPrototype, который является копией оригинального объекта.
     * @throws CloneNotSupportedException в случае, если клонирование объекта не может быть выполнено.
     */
    @Override
    public EntityPrototype clone() {
        try {
            return (EntityPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("Content clone failed", e);
            throw new RuntimeException("Ошибка клонирования контента: " + e.getMessage(), e);
        }
    }

    /**
     * Реализация метода обработки контента с использованием паттерна Strategy.
     * Этот метод позволяет динамически изменять способ обработки контента,
     * применяя различные стратегии в зависимости от типа контента или других условий.
     * Паттерн Strategy предоставляет возможность инкапсуляции алгоритма и его параметров в отдельный объект,
     * что упрощает добавление новых способов обработки без изменения существующего кода.
     * <p>
     * Пример использования:
     * <pre>
     *     ContentProcessingStrategy textContentStrategy = new TextContentProcessingStrategy();
     *     Content content = new TextContent("Example text", user);
     *
     *     // Применение стратегии обработки текстового контента
     *     content.process(textContentStrategy);
     * </pre>
     *
     * @param strategy Объект стратегии, который определяет конкретный способ обработки контента.
     */
    public void process(ContentProcessingStrategy strategy) {
        strategy.processContent(this);
    }

    /**
     * Реализация метода принятия посетителя с использованием паттерна Visitor.
     * Этот метод позволяет объекту контента принимать посетителя и обрабатывать его запросы.
     * Паттерн Visitor обеспечивает гибкую систему для добавления новых операций над объектами контента,
     * не изменяя их классов напрямую. Вместо этого, новые операции реализуются через посетителей,
     * что позволяет легко расширять возможности обработки контента.
     * <p>
     * Пример использования:
     * <pre>
     *     ContentVisitor infoPrinterVisitor = new InfoPrinterVisitor();
     *
     *     Content content = new TextContent("Example text", user);
     *
     *     content.accept(infoPrinterVisitor);
     * </pre>
     *
     * @param visitor Объект посетитель, который определяет конкретный способ обработки контента.
     */
    public abstract void acceptVisitor(ContentVisitor visitor);

}
