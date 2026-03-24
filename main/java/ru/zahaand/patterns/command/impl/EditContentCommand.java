package ru.zahaand.patterns.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.command.ContentCommand;
import ru.zahaand.patterns.domain.Content;

import java.util.UUID;

@Slf4j
public class EditContentCommand implements ContentCommand {

    private final UUID contentId;
    private final Content newContent;
    private Content previousContent;

    public EditContentCommand(UUID contentId, Content newContent) {
        this.contentId = contentId;
        this.newContent = newContent;
    }

    @Override
    public void execute() {
        // В реальном приложении здесь бы сохранялись previousContent для отмены
        log.info("EditContentCommand.execute: editing content id={}, newContent={}", contentId, newContent);
    }

    /**
     * Отменяет редактирование контента, восстанавливая предыдущую версию.
     */
    @Override
    public void undo() {
        log.info("EditContentCommand.undo: reverting content id={} to previous state", contentId);
    }
}
