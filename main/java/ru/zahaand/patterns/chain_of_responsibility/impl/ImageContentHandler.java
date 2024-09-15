package ru.zahaand.patterns.chain_of_responsibility.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.zahaand.patterns.chain_of_responsibility.ContentHandler;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.ImageContent;
import ru.zahaand.patterns.enums.ContentType;

@Slf4j
@Component
public class ImageContentHandler implements ContentHandler {

    private ContentHandler next;

    @Override
    public void setNext(ContentHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Content content) {
        if (content.getContentType() == ContentType.IMAGE) {
            log.info("IMAGE content processing...");
            ImageContent imageContent = (ImageContent) content;
            log.info("Resizing image {} to 800x600 pixels...", imageContent.getImage());
        } else if (next!= null) {
            next.handle(content);
        }
    }
}
