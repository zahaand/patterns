package ru.zahaand.patterns.service;

import ru.zahaand.patterns.domain.Content;

import java.util.UUID;

public interface ContentService {

    Content create(Content content);

    Content read(UUID id);

    Content update(Content content);

    boolean delete(UUID id);
}
