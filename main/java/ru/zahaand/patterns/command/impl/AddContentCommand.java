package ru.zahaand.patterns.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.command.ContentCommand;
import ru.zahaand.patterns.domain.Content;

@Slf4j
public class AddContentCommand implements ContentCommand {

    private Content content;

    public AddContentCommand(Content content) {
        this.content = content;
    }

    @Override
    public void execute() {
        log.info("Adding CONTENT {}...", content);
    }
}
