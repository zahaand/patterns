package ru.zahaand.patterns.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.command.ContentCommand;

import java.util.UUID;

@Slf4j
public class DeleteContentCommand implements ContentCommand {

    private UUID contentId;

    public DeleteContentCommand(UUID contentId) {
        this.contentId = contentId;
    }

    @Override
    public void execute() {
        log.info("Deleting CONTENT with ID {}...", contentId);
    }
}
