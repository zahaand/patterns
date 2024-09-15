package ru.zahaand.patterns.flyweight;

import org.springframework.stereotype.Component;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Паттерн Flyweight. Легковес</h1>
 * Класс {@code ImageFlyweight} реализует паттерн Flyweight для эффективного управления и повторного использования объектов изображений.
 * Это позволяет минимизировать использование памяти за счет хранения уникальных экземпляров изображений в кэше.
 *
 * <p>Паттерн Flyweight применяется для снижения количества объектов, создаваемых во время выполнения программы,
 * путем повторного использования уже существующих объектов, когда это возможно. Это достигается за счет разделения состояния объекта
 * на внутреннее состояние (неизменяемое и уникальное для каждого объекта) и внешнее состояние (может изменяться и быть общим для группы объектов).
 *
 * <h3>Паттерн Легковес обеспечивает следующие основные преимущества:</h3>
 * <ul>
 *     <li><strong>Экономия памяти</strong>: Повторное использование объектов снижает потребление памяти.</li>
 *     <li><strong>Улучшенная производительность</strong>: Благодаря меньшему количеству создаваемых объектов, приложение работает быстрее.</li>
 *     <li><strong>Централизованное управление</strong>: Упрощает обслуживание и обновление объектов.</li>
 * </ul>
 *
 * <h3>Паттерн Легковес целесообразно применять в следующих ситуациях:</h3>
 * <ul>
 *     <li>Работа с большим количеством мелких объектов, имеющих общие характеристики.</li>
 *     <li>Частые создания и удаления объектов, что дорогостоящее с точки зрения ресурсов.</li>
 *     <li>Ограниченность ресурсов, например, на мобильных устройствах.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>
 *     ImageContent image1 = imageFlyweight.getImage("/path/to/image1.jpg");
 *     ImageContent image2 = imageFlyweight.getImage("/path/to/image1.jpg");
 * </pre>
 * <p>
 * При попытке получить доступ к одному и тому же изображению по пути "/path/to/image1.jpg",
 * возвращается один и тот же объект из кэша, что демонстрирует принцип повторного использования объектов в паттерне Flyweight.
 */
@Component
public class ImageFlyweight {
    private final Map<String, Image> imagesByPath = new HashMap<>();

    public Image getImage(String path) throws IOException {

        if (!imagesByPath.containsKey(path)) {
            byte[] content = Files.readAllBytes(Paths.get(path));
            Image image = new Image(ImageFormat.JPEG, content, path);
            imagesByPath.put(path, image);
        }

        return imagesByPath.get(path);
    }
}
