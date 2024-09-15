package ru.zahaand.patterns.domain.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.enums.ContentType;
import ru.zahaand.patterns.visitor.ContentVisitor;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class TextContent extends Content {

    private String content;

    public TextContent(String data, User user) {
        super(ContentType.TEXT, user);
        content = data;
    }

    public TextContent(String content) {
        this.content = content;
    }

    @Override
    public void display() {
        log.info("Displaying TEXT content. User: {}, Text: {}", user, content);
    }

    @Override
    public void acceptVisitor(ContentVisitor visitor) {
        visitor.visit(this);
    }
}
