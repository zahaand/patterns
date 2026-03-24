package ru.zahaand.patterns.domain.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.model.Image;
import ru.zahaand.patterns.prototype.EntityPrototype;
import ru.zahaand.patterns.visitor.ContentVisitor;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ImageContent extends Content {

    private Image image;

    public ImageContent(Image image, User user) {
        super(ContentType.IMAGE, user);
        this.image = image;
    }

    public ImageContent(Image image) {
        this.image = image;
    }

    @Override
    public void display() {
        log.info("Displaying IMAGE content. User: {}, Image format: {}", user,  image.getFormat());
    }

    @Override
    public void acceptVisitor(ContentVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Глубокое копирование ImageContent.
     * Дополнительно к новому UUID (из {@link Content#clone()}) создаёт
     * новый объект {@link Image} с копией массива байт, чтобы изменение
     * байт в клоне не влияло на оригинал.
     *
     * @return глубокая копия данного {@link ImageContent} с новым UUID и новым объектом Image.
     */
    @Override
    public EntityPrototype clone() {
        ImageContent cloned = (ImageContent) super.clone();
        if (image != null) {
            byte[] originalBytes = image.getContent();
            byte[] copiedBytes = originalBytes != null ? originalBytes.clone() : null;
            cloned.image = new Image(image.getFormat(), copiedBytes, image.getPath());
        }
        return cloned;
    }
}
