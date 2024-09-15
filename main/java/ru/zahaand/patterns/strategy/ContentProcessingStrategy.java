package ru.zahaand.patterns.strategy;

import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.strategy.impl.ImageContentProcessingStrategy;
import ru.zahaand.patterns.strategy.impl.TextContentProcessingStrategy;

/**
 * <h1>Паттерн Strategy. Стратегия</h1>
 * Интерфейс {@code ContentProcessingStrategy} является частью реализации паттерна Strategy.
 * Этот паттерн позволяет определить семейство алгоритмов, инкапсулировать каждый из них и сделать их взаимозаменяемыми.
 * Strategy позволяет алгоритму варьироваться независимо от клиентов, которые его используют.
 *
 * <p>Паттерн Strategy используется для изолирования алгоритма от клиента, который его использует, и для предоставления возможности выбора алгоритма на лету.
 * Это особенно полезно, когда имеется несколько вариантов алгоритма, и необходимо дать возможность пользователю или системе выбирать наиболее подходящий.
 *
 * <h3>Паттерн Strategy обеспечивает следующие основные преимущества:</h3>
 * <ul>
 *     <li><strong>Изменение поведения во время выполнения</strong>: Клиенты могут динамически изменять поведение объекта, выбирая различные стратегии.</li>
 *     <li><strong>Упрощение условной логики</strong>: Позволяет избежать больших условных операторов, распределяя поведение по разным классам.</li>
 *     <li><strong>Расширяемость</strong>: Легко добавлять новые стратегии без изменения существующего кода.</li>
 * </ul>
 *
 * <h3>Паттерн Strategy целесообразно применять в следующих ситуациях:</h3>
 * <ul>
 *     <li><strong>Когда существует несколько способов выполнения задачи</strong>: Если у вас есть множество алгоритмов решения одной задачи, и вы хотите предоставить возможность выбора.</li>
 *     <li><strong>Когда необходимо избежать большой иерархии условий</strong>: Если ваш код содержит множество условных операторов, выбирающих поведение, Strategy может помочь упростить структуру.</li>
 *     <li><strong>Когда нужно предоставить возможность выбора алгоритма клиенту</strong>: Если клиент должен иметь возможность выбирать алгоритмы на лету.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     ContentProcessingStrategy textContentStrategy = new TextContentProcessingStrategy();
 *     ContentProcessingStrategy imageContentStrategy = new ImageContentProcessingStrategy();
 *
 *     Content textContent = new TextContent("Example text", user);
 *     Content imageContent = new ImageContent(ImageFormat.PNG, "EncodedImage", user);
 *
 *     textContent.process(textContentStrategy);    // Обработка текстового контента
 *     imageContent.process(imageContentStrategy);  // Обработка изображения
 * </pre>
 * <p>
 * В этом примере для каждого типа контента ({@link TextContent}, {@link ImageContent}) используется своя стратегия обработки ({@link TextContentProcessingStrategy}, {@link ImageContentProcessingStrategy}),
 * что позволяет динамически изменять способ обработки контента в зависимости от его типа.
 */
public interface ContentProcessingStrategy {

    /**
     * Процесс обработки контента согласно выбранной стратегии.
     *
     * @param content Объект контента, который необходимо обработать.
     */
    void processContent(Content content);
}

