package ru.zahaand.patterns.chain_of_responsibility.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.zahaand.patterns.chain_of_responsibility.ContentHandler;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;

import static ru.zahaand.patterns.enums.ContentType.*;

@Slf4j
@Component
public class TextContentHandler implements ContentHandler {

    private ContentHandler next;

    @Override
    public void setNext(ContentHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Content content) {
        if (TEXT.equals(content.getContentType())) {
            log.info("TEXT content processing...");
            TextContent textContent = (TextContent) content;
            String processedText = textContent.getContent().toUpperCase();
            textContent.setContent(processedText);
        } else if (next != null) {
            next.handle(content);
        }
    }
}
