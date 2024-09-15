package ru.zahaand.patterns.abstract_factory.impl;

import ru.zahaand.patterns.abstract_factory.ContentCreator;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.ContentData;
import ru.zahaand.patterns.model.Image;
import ru.zahaand.patterns.model.impl.ImageContentData;

public class ImageContentCreator extends ContentCreator {

    @Override
    public Content createContent(ContentType contentType, ContentData contentData) {
        if (!(contentData instanceof ImageContentData imageData)) {
            return null;
        }

        ImageFormat format = imageData.getFormat();
        byte[] content = (byte[]) imageData.getData();
        String path = imageData.getPath();
        User user = imageData.getUser();

        return new ImageContent(new Image(format, content, path), user);
    }
}
