package ru.zahaand.patterns.model.impl;

import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.model.ContentData;

public class TextContentData extends ContentData {

    public TextContentData(String data, User user) {
        super(data, user);
    }
}
