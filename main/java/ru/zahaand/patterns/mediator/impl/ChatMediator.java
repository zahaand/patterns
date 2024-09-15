package ru.zahaand.patterns.mediator.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.mediator.MessageMediator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class ChatMediator implements MessageMediator {

    private final Map<UUID, User> registeredUsers = new HashMap<>();

    @Override
    public void registerUser(User user) {
        registeredUsers.put(user.getId(), user);
        log.info("Пользователь {} зарегистрирован в чате.", user.getName());
    }

    @Override
    public void sendMessage(User sender, User receiver, String message) {

        if (registeredUsers.containsKey(sender.getId())
                && registeredUsers.containsKey(receiver.getId())) {
            log.info("{} отправил сообщение {}: {}", sender.getName(), receiver.getName(), message);

        } else {
            log.error("Один из пользователей не зарегистрирован в чате.");
        }
    }
}
