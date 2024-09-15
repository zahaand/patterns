package ru.zahaand.patterns.domain.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.model.Image;
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
}
