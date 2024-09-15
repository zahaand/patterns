package ru.zahaand.patterns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.zahaand.patterns.domain.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ContentData {

    private Object data;
    private User user;
}
