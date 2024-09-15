package ru.zahaand.patterns;

import ru.zahaand.patterns.composite.ContentComposite;
import ru.zahaand.patterns.decorator.ContentDecorator;

/**
 * Интерфейс DisplayableContent определяет контракт для всех элементов контента,
 * которые могут быть отображены в пользовательском интерфейсе или другом месте.
 *
 * <li>Служит для демонстрации паттерна Composite, позволяя обрабатывать как отдельные элементы,
 * так и группы элементов единым способом.
 *
 * <li>Служит для демонстрации паттерна Decorator, позволяя добавлять или изменять поведение элементов контента динамически,
 * оборачивая их в декораторы, которые могут расширять функциональность метода {@link #display()} без изменения исходного кода.
 *
 * @see ContentComposite
 * @see ContentDecorator
 */
public interface DisplayableContent {

    void display();
}
