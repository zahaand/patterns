package ru.zahaand.patterns.model.impl;

import lombok.Getter;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.enums.ImageFormat;
import ru.zahaand.patterns.model.ContentData;

@Getter
public class ImageContentData extends ContentData {

    private final ImageFormat format;
    private final String path;

    public ImageContentData(String data, User user, ImageFormat format, String path) {
        super(data, user);
        this.format = format;
        this.path = path;
    }
}
