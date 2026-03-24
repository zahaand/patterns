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
public abstract class Content implements EntityPrototype, DisplayableContent, Cloneable {

    private UUID id;
    private ContentType contentType;
    protected User user;

    protected Content(ContentType contentType, User user) {
        id = UUID.randomUUID();
        this.contentType = contentType;
        this.user = user;
    }

    /**
     * Реализация метода clone интерфейса {@link EntityPrototype} (паттерн Prototype).
     * Создаёт глубокую копию объекта: клон получает <strong>новый уникальный идентификатор</strong>
     * ({@link UUID}), но сохраняет все остальные поля оригинала.
     *
     * <p>Метод работает корректно благодаря реализации интерфейса {@link Cloneable}.
     * Без {@code implements Cloneable} вызов {@code super.clone()} привёл бы к
     * {@link CloneNotSupportedException}.
     *
     * <p>Пример использования:
     * <pre>{@code
     *     TextContent original = new TextContent("Hello", user);
     *     TextContent clone = (TextContent) original.clone();
     *     // clone.getId() != original.getId() — у клона новый UUID
     *     // clone.getContent().equals(original.getContent()) — данные скопированы
     * }</pre>
     *
     * @return новый экземпляр {@link EntityPrototype} — глубокая копия с новым идентификатором.
     */
    @Override
    public EntityPrototype clone() {
        try {
            Content cloned = (Content) super.clone();
            // Генерируем новый UUID для клона, чтобы он был независимым объектом
            cloned.id = UUID.randomUUID();
            return cloned;
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
