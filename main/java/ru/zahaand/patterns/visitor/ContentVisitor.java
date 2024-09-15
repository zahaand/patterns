package ru.zahaand.patterns.visitor;

import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.visitor.impl.ArchiverContentVisitor;
import ru.zahaand.patterns.visitor.impl.InfoPrinterContentVisitor;

/**
 * <h1>Паттерн Visitor. Посетитель</h1>
 * Интерфейс {@code ContentVisitor} демонстрирует применение паттерна Visitor.
 * Этот паттерн позволяет определить набор операций, применимых к объектам контента,
 * делая их независимыми от классов объектов, к которым они применяются.
 *
 * <h3>Паттерн Visitor обеспечивает следующие основные преимущества:</h3>
 * <ul>
 *     <li><strong>Инкапсуляция операций</strong>: Определяет набор операций, которые могут быть применены к объектам контента, инкапсулируя их в отдельные классы.</li>
 *     <li><strong>Расширяемость</strong>: Легко добавлять новые операции обработки контента, реализуя новые классы-посетители.</li>
 *     <li><strong>Повторное использование кода</strong>: Повышает степень повторного использования кода за счет инкапсуляции операций в отдельные классы.</li>
 * </ul>
 *
 * <h3>Паттерн Visitor целесообразно применять в следующих ситуациях:</h3>
 * <ul>
 *     <li><strong>Когда требуется множество алгоритмов для обработки объектов</strong>: Когда один объект может быть обработан несколькими способами, и эти способы могут меняться во времени или в зависимости от контекста.</li>
 *     <li><strong>Когда объекты имеют много общего поведения, но также и специфические части</strong>: Позволяет разделять общее поведение от специфического, улучшая модульность и поддерживаемость кода.</li>
 *     <li><strong>Когда необходимо избежать сложной логики условий внутри классов объектов</strong>: Уменьшает количество условных операторов в классах объектов, делая код проще и легче для понимания.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     ContentVisitor infoPrinter = new InfoPrinterContentVisitor();
 *     ContentVisitor archiver = new ArchiverContentVisitor();
 *
 *     Content textContent = new TextContent("Example text", user);
 *     Content imageContent = new ImageContent(ImageFormat.PNG, "EncodedImage", user);
 *
 *     textContent.acceptVisitor(infoPrinter);
 *     imageContent.acceptVisitor(infoPrinter);
 *
 *     textContent.acceptVisitor(archiver);
 *     imageContent.acceptVisitor(archiver);
 * </pre>
 * <p>
 * В этом примере для каждого типа контента ({@link TextContent}, {@link ImageContent}) используются разные посетители ({@link InfoPrinterContentVisitor}, {@link ArchiverContentVisitor}),
 * что позволяет динамически изменять способ обработки контента в зависимости от его типа и нужд обработки.
 */
public interface ContentVisitor {

    /**
     * Метод вызывается для обработки объекта {@link TextContent}.
     * Реализация данного метода в конкретном посетителе определяет, как именно будет обрабатываться текстовый контент.
     *
     * @param textContent объект текстового контента для обработки
     */
    void visit(TextContent textContent);

    /**
     * Метод вызывается для обработки объекта {@link ImageContent}.
     * Реализация данного метода в конкретном посетителе определяет, как именно будет обрабатываться контент изображений.
     *
     * @param imageContent объект контента изображения для обработки
     */
    void visit(ImageContent imageContent);
}
