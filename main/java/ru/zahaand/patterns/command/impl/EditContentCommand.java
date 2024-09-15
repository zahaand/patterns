package ru.zahaand.patterns.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.command.ContentCommand;
import ru.zahaand.patterns.domain.Content;

import java.util.UUID;

@Slf4j
public class EditContentCommand implements ContentCommand {

    private UUID contentId;
    private Content newContent;

    public EditContentCommand(UUID contentId, Content newContent) {
        this.contentId = contentId;
        this.newContent = newContent;
    }

    @Override
    public void execute() {
        log.info("Editing CONTENT with ID {}... . New content: {}", contentId, newContent);
    }
}
