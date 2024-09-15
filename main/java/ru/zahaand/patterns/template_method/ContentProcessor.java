package ru.zahaand.patterns.template_method;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.template_method.impl.ImageContentProcessor;
import ru.zahaand.patterns.template_method.impl.TextContentProcessor;

/**
 * <h1>Паттерн Template Method. Шаблонный метод</h1>
 * Абстрактный класс {@code ContentProcessor} демонстрирует применение паттерна Template Method.
 * Этот паттерн позволяет определить скелет алгоритма в базовом классе, но оставляет возможность подклассам переопределять некоторые шаги алгоритма без изменения его структуры.
 *
 * <h3>Паттерн Template Method обеспечивает следующие основные преимущества:</h3>
 * <ul>
 *     <li><strong>Инкапсуляция алгоритмов</strong>: Позволяет инкапсулировать алгоритмы, делая их независимыми от контекста.</li>
 *     <li><strong>Расширяемость</strong>: Подклассы могут переопределять некоторые шаги алгоритма, не изменяя общую структуру.</li>
 *     <li><strong>Повторное использование кода</strong>: Общий код алгоритма находится в базовом классе и может быть повторно использован подклассами.</li>
 * </ul>
 *
 * <h3>Паттерн Template Method целесообразно применять в следующих ситуациях:</h3>
 * <ul>
 *     <li><strong>Когда существует несколько способов выполнения задачи</strong>: Если у вас есть множество алгоритмов решения одной задачи, и вы хотите предоставить возможность выбора.</li>
 *     <li><strong>Когда необходимо избежать большой иерархии условий</strong>: Если ваш код содержит множество условных операторов, выбирающих поведение, Template Method может помочь упростить структуру.</li>
 *     <li><strong>Когда нужно предоставить возможность выбора алгоритма клиенту</strong>: Если клиент должен иметь возможность выбирать алгоритмы на лету.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     ContentProcessor textContentProcessor = new TextContentProcessor();
 *     ContentProcessor imageContentProcessor = new ImageContentProcessor();
 *
 *     Content textContent = new TextContent("Example text", user);
 *     Content imageContent = new ImageContent(ImageFormat.PNG, "EncodedImage", user);
 *
 *     textContentProcessor.processTemplateMethod(textContent);
 *     imageContentProcessor.processTemplateMethod(imageContent);
 * </pre>
 * <p>
 * В этом примере для каждого типа контента ({@link TextContent}, {@link ImageContent}) используется свой процессор ({@link TextContentProcessor}, {@link ImageContentProcessor}),
 * что позволяет динамически изменять способ обработки контента в зависимости от его типа, сохраняя при этом общую структуру алгоритма обработки.
 */
@Slf4j
public abstract class ContentProcessor {

    /**
     * Шаблонный метод для обработки контента.
     * Этот метод определяет последовательность вызова методов для обработки контента,
     * включая его валидацию, модификацию и сохранение.
     *
     * @param content объект контента для обработки
     */
    public final void processTemplateMethod(Content content) {
        validateContent(content);
        modifyContent(content);
        saveContent(content);
    }

    /**
     * Метод для валидации контента. Должен быть переопределен в подклассах.
     * Позволяет проверить контент на соответствие определенным критериям перед его обработкой.
     *
     * @param content объект контента для валидации
     */
    protected abstract void validateContent(Content content);

    /**
     * Метод для модификации контента. Должен быть переопределен в подклассах.
     * Позволяет изменять или адаптировать контент в соответствии с требованиями конкретной реализации.
     *
     * @param content объект контента для модификации
     */
    protected abstract void modifyContent(Content content);

    /**
     * Метод для сохранения контента. Предоставляет реализацию по умолчанию для сохранения контента.
     *
     * @param content объект контента для сохранения
     */
    private void saveContent(Content content) {
        log.info("Saving Content with ID: {}", content.getId());
    }
}
