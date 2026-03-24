package ru.zahaand.patterns.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.command.ContentCommand;
import ru.zahaand.patterns.domain.Content;

@Slf4j
public class AddContentCommand implements ContentCommand {

    private final Content content;

    public AddContentCommand(Content content) {
        this.content = content;
    }

    @Override
    public void execute() {
        log.info("AddContentCommand.execute: adding content id={}", content.getId());
    }

    /**
     * Отменяет добавление контента (условно удаляет добавленный объект).
     */
    @Override
    public void undo() {
        log.info("AddContentCommand.undo: removing content id={}", content.getId());
    }
}
