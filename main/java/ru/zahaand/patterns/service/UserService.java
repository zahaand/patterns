package ru.zahaand.patterns.service;

import ru.zahaand.patterns.domain.User;

import java.util.UUID;

public interface UserService {

    User create(User user);

    User read(UUID id);

    User update(User user);

    boolean delete(UUID id);
}
