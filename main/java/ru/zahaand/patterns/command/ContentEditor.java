package ru.zahaand.patterns.command;

import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Класс {@code ContentEditor} предназначен для выполнения команды управления контентом.
 * Он инкапсулирует логику выполнения команды, предоставляя простой способ выполнения команды.
 *
 * <li>Служит для демонстрации паттерна Command для инкапсуляции операций над контентом,
 * предоставляя гибкий и расширяемый интерфейс для выполнения команд.
 *
 * @see ContentCommand
 */
@Setter
@Component
public class ContentEditor {

    private ContentCommand command;

    public void executeCommand() {
        command.execute();
    }
}
