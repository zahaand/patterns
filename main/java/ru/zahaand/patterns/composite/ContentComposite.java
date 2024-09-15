package ru.zahaand.patterns.composite;

import org.springframework.stereotype.Component;
import ru.zahaand.patterns.DisplayableContent;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Паттерн Composite. Компоновщик</h1>
 * Класс ContentComposite представляет собой реализацию паттерна Composite,
 * позволяя группировать элементы контента в иерархическую структуру,
 * где каждый элемент может быть как отдельным элементом контента, так и группой элементов.
 * Это обеспечивает гибкость в управлении и отображении контента,
 * позволяя обрабатывать как отдельные элементы, так и группы элементов единым способом.
 *
 * <p>Паттерн Composite обеспечивает следующие ключевые преимущества:
 * <ul>
 *     <li><strong>Гибкость в управлении контентом</strong>: Composite позволяет легко добавлять,
 *     удалять и изменять элементы контента, создавая сложные иерархические структуры.</li>
 *     <li><strong>Упрощение обработки контента</strong>: Composite позволяет обрабатывать группы
 *     элементов контента как единое целое, упрощая логику обработки и отображения контента.</li>
 *     <li><strong>Переиспользование кода</strong>: Composite позволяет переиспользовать код для обработки
 *     отдельных элементов и групп элементов, уменьшая дублирование кода.</li>
 * </ul>
 *
 * <p>Паттерн Composite целесообразно применять в следующих ситуациях:
 * <ul>
 *     <li><strong>Когда необходимо управлять иерархическими структурами контента</strong>:
 *     Composite идеально подходит для ситуаций, когда контент должен быть организован в иерархии,
 *     например, разделы и подразделы в документации или категории и подкатегории в интернет-магазине.</li>
 *     <li><strong>Когда требуется единая логика обработки для различных типов контента</strong>:
 *     Composite позволяет определить общий интерфейс для обработки как отдельных элементов,
 *     так и групп элементов, упрощая логику обработки и отображения контента.</li>
 *     <li><strong>Когда необходимо поддерживать сложные структуры контента</strong>:
 *     Composite позволяет легко добавлять, удалять и изменять элементы контента, поддерживая сложные иерархии.</li>
 * </ul>
 *
 * <p>Пример использования паттерна Composite:
 * <pre>
 *     Content imageContent = new ImageContent(ImageFormat.JPEG, "encodedImage", user);
 *     Content textContent = new TextContent("Sample text", user);
 *
 *     ContentComposite compositeContent = new ContentComposite();
 *     compositeContent.addComponent(imageContent);
 *     compositeContent.addComponent(textContent);
 *     compositeContent.display();
 * </pre>
 *
 * В этом примере создается группа контента, включающая изображение и текст,
 * и затем отображается как единое целое. Это демонстрирует, как паттерн Composite может быть использован для
 * организации и обработки иерархических структур контента в приложении.
 */
@Component
public class ContentComposite implements DisplayableContent {

    private final List<DisplayableContent> components;

    public ContentComposite() {
        components = new ArrayList<>();
    }

    public void addComponent(DisplayableContent component) {
        components.add(component);
    }

    public void removeComponent(DisplayableContent component) {
        components.remove(component);
    }

    @Override
    public void display() {
        for (DisplayableContent component : components) {
            component.display();
        }
    }
}
