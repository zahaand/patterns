package ru.zahaand.patterns.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.flyweight.ImageFlyweight;

/**
 * Класс {@code Image} представляет собой структуру данных для хранения информации об изображении.
 * Он содержит формат изображения, его содержимое в виде массива байтов и путь к файлу изображения.
 *
 * <li>Служит для демонстрации паттерна Flyweight, позволяя разделить состояние объекта на внутреннее состояние
 * (неизменяемое и уникальное для каждого объекта) и внешнее состояние (может изменяться и быть общим для группы объектов).
 *
 * @see ImageFlyweight
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private ImageFormat format;
    private byte[] content;
    private String path;
}
