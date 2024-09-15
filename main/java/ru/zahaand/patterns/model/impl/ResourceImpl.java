package ru.zahaand.patterns.model.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.zahaand.patterns.domain.Content;
import ru.zahaand.patterns.domain.impl.TextContent;
import ru.zahaand.patterns.model.Resource;
import ru.zahaand.patterns.proxy.ResourceProxy;

/**
 * Класс {@code ResourceImpl} реализует интерфейс {@link Resource}, предоставляя конкретную реализацию метода {@link Resource#loadContent()}.
 * Этот класс служит в качестве реализации реального ресурса, к которому может обращаться прокси.
 *
 * <li>Служит для демонстрации паттерна Proxy, обеспечивая безопасный и контролируемый доступ к ресурсам.
 *
 * @see ResourceProxy
 */
@Slf4j
@Component("realResourceImpl")
public class ResourceImpl extends Resource {


    @Override
    public Content loadContent() {
        log.info("Content LOADING...");
        return new TextContent();
    }
}
