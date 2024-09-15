package ru.zahaand.patterns.template_method.impl;

import lombok.extern.slf4j.Slf4j;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.template_method.ContentProcessor;

@Slf4j
public class ImageContentProcessor extends ContentProcessor {

    @Override
    protected void validateContent(Content content) {
        log.info("Image content validation starts...");

        if (!(content instanceof TextContent)) {
            log.error("Content validation ERROR: Invalid content type. Expected type: Image");
            throw new IllegalArgumentException("Invalid content type");
        }
    }

    @Override
    protected void modifyContent(Content content) {
        log.info("Image content modification starts...");
    }
}
