package ru.zahaand.patterns.iterator.impl;

import org.springframework.stereotype.Component;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.iterator.ContentIterator;

import java.util.Iterator;
import java.util.List;

/**
 * Конкретная реализация интерфейса {@link ContentIterator} для паттерна <b>Итератор (Iterator)</b>.
 *
 * <p>Обеспечивает последовательный доступ к элементам {@link List} объектов {@link Content}
 * без раскрытия внутренней структуры данных клиенту.
 *
 * <p><b>Роли паттерна:</b>
 * <ul>
 *   <li>{@link ru.zahaand.patterns.iterator.ContentIterator} — Iterator (абстрактный итератор)</li>
 *   <li>{@code ContentListIterator} — ConcreteIterator (данный класс)</li>
 * </ul>
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * List<Content> contents = List.of(textContent, imageContent);
 * ContentIterator iterator = new ContentListIterator(contents);
 * while (iterator.hasNext()) {
 *     Content content = iterator.next();
 *     content.display();
 * }
 * }</pre>
 */
@Component
public class ContentListIterator implements ContentIterator {

    private final Iterator<Content> iterator;

    /**
     * Создаёт новый итератор для заданного списка контента.
     *
     * @param contents список объектов {@link Content} для итерации
     */
    public ContentListIterator(List<Content> contents) {
        this.iterator = contents.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Content next() {
        return iterator.next();
    }
}
