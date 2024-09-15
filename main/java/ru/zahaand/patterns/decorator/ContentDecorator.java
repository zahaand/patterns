package ru.zahaand.patterns.decorator;

import ru.zahaand.patterns.DisplayableContent;
import ru.zahaand.patterns.decorator.impl.EncryptContentDecorator;
import ru.zahaand.patterns.domain.Content;

/**
 * Абстрактный класс ContentDecorator представляет собой базовый декоратор в паттерне Decorator,
 * который позволяет динамически добавлять новую функциональность к объектам типа Content.
 * Этот класс оборачивает объект Content и предоставляет возможность расширения его функциональности
 * путем переопределения методов, не изменяя при этом поведение объекта, который он декорирует.
 *
 * <p>ContentDecorator является абстрактным классом, который определяет общую структуру для всех конкретных декораторов,
 * предоставляя защищенное поле decoratedContent для хранения ссылки на декорируемый объект.
 * Конкретные декораторы, такие как {@link EncryptContentDecorator} , могут расширять этот класс,
 * чтобы добавить специфическую функциональность.
 */
public abstract class ContentDecorator implements DisplayableContent {

    protected Content decoratedContent;

    protected ContentDecorator(Content decoratedContent) {
        this.decoratedContent = decoratedContent;
    }

    /**
     * Делегирует вызов отображения контента декорируемому объекту.
     * Этот метод может быть переопределен в конкретных декораторах для добавления дополнительной логики
     * перед или после вызова отображения контента.
     */
    @Override
    public void display() {
        decoratedContent.display();
    }
}
