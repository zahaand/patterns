package ru.zahaand.patterns.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.command.ContentCommand;

import java.util.UUID;

@Slf4j
public class DeleteContentCommand implements ContentCommand {

    private final UUID contentId;

    public DeleteContentCommand(UUID contentId) {
        this.contentId = contentId;
    }

    @Override
    public void execute() {
        log.info("DeleteContentCommand.execute: deleting content id={}", contentId);
    }

    /**
     * Отменяет удаление контента (условно восстанавливает удалённый объект).
     */
    @Override
    public void undo() {
        log.info("DeleteContentCommand.undo: restoring content id={}", contentId);
    }
}
