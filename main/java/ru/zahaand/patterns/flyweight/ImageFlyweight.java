package ru.zahaand.patterns.flyweight;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.Image;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Паттерн Flyweight. Легковес</h1>
 *
 * <p>Кэширует объекты {@link Image} по пути к файлу, предотвращая повторную загрузку
 * одного и того же изображения с диска. Объекты хранятся через {@link SoftReference},
 * что позволяет JVM освобождать их при нехватке памяти — защита от утечек памяти.
 *
 * <p><strong>Внутреннее состояние (intrinsic)</strong> — данные изображения ({@code byte[]}, формат, путь) —
 * хранится в кэше и разделяется между клиентами.<br>
 * <strong>Внешнее состояние (extrinsic)</strong> — контекст использования (например, позиция на экране) —
 * передаётся клиентом при каждом запросе.
 *
 * <h3>Улучшения по сравнению с простым HashMap:</h3>
 * <ul>
 *     <li><strong>SoftReference</strong>: JVM автоматически очищает кэш при нехватке памяти.</li>
 *     <li><strong>Разделение I/O</strong>: Загрузка байт вынесена в отдельный метод {@link #loadBytes(String)}.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     Image img1 = flyweight.getImage("/path/to/image1.jpg");
 *     Image img2 = flyweight.getImage("/path/to/image1.jpg");
 *     // img1 == img2 (если SoftReference ещё не была собрана GC)
 * }</pre>
 */
@Slf4j
@Component
public class ImageFlyweight {

    /**
     * Кэш изображений: ключ — путь к файлу, значение — мягкая ссылка на объект Image.
     * SoftReference позволяет JVM освобождать объекты при нехватке памяти.
     */
    private final Map<String, SoftReference<Image>> imageCache = new HashMap<>();

    /**
     * Возвращает объект {@link Image} для заданного пути.
     * Если объект есть в кэше и ещё не был собран GC — возвращает его.
     * Иначе загружает изображение с диска и помещает в кэш.
     *
     * @param path путь к файлу изображения
     * @return объект {@link Image} с загруженными данными
     * @throws IOException если файл не найден или не может быть прочитан
     */
    public Image getImage(String path) throws IOException {
        SoftReference<Image> ref = imageCache.get(path);
        Image image = (ref != null) ? ref.get() : null;

        if (image == null) {
            log.info("ImageFlyweight: cache miss for '{}', loading from disk", path);
            byte[] content = loadBytes(path);
            image = new Image(ImageFormat.JPEG, content, path);
            imageCache.put(path, new SoftReference<>(image));
        } else {
            log.info("ImageFlyweight: cache hit for '{}'", path);
        }

        return image;
    }

    /**
     * Загружает байты файла с диска. Вынесен в отдельный метод для
     * разделения логики кэширования и ввода-вывода.
     *
     * @param path путь к файлу
     * @return массив байт содержимого файла
     * @throws IOException если файл не найден
     */
    private byte[] loadBytes(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    /**
     * Возвращает текущий размер кэша (включая уже собранные GC ссылки).
     *
     * @return количество записей в кэше
     */
    public int getCacheSize() {
        return imageCache.size();
    }
}
