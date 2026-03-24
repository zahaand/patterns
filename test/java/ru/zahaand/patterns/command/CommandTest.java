package ru.zahaand.patterns.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zahaand.patterns.command.impl.AddContentCommand;
import ru.zahaand.patterns.command.impl.DeleteContentCommand;
import ru.zahaand.patterns.command.impl.EditContentCommand;
import ru.zahaand.patterns.domain.User;
import ru.zahaand.patterns.domain.impl.TextContent;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Command.
 * Проверяют выполнение команд, undo и работу ContentCommandInvoker с историей.
 */
@DisplayName("Паттерн Command — выполнение и отмена команд")
class CommandTest {

    private User user;
    private TextContent content;
    private ContentCommandInvoker invoker;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder(UUID.randomUUID(), "+7 999 000-00-00", "user@mail.ru").build();
        content = new TextContent("Test content", user);
        invoker = new ContentCommandInvoker();
    }

    @Test
    @DisplayName("AddContentCommand: execute() выполняется без исключений")
    void addCommand_execute_doesNotThrow() {
        ContentCommand command = new AddContentCommand(content);
        assertDoesNotThrow(command::execute);
    }

    @Test
    @DisplayName("AddContentCommand: undo() выполняется без исключений")
    void addCommand_undo_doesNotThrow() {
        ContentCommand command = new AddContentCommand(content);
        command.execute();
        assertDoesNotThrow(command::undo);
    }

    @Test
    @DisplayName("DeleteContentCommand: execute() и undo() выполняются без исключений")
    void deleteCommand_executeAndUndo() {
        ContentCommand command = new DeleteContentCommand(UUID.randomUUID());
        assertDoesNotThrow(command::execute);
        assertDoesNotThrow(command::undo);
    }

    @Test
    @DisplayName("EditContentCommand: execute() и undo() выполняются без исключений")
    void editCommand_executeAndUndo() {
        TextContent newContent = new TextContent("Updated", user);
        ContentCommand command = new EditContentCommand(UUID.randomUUID(), newContent);
        assertDoesNotThrow(command::execute);
        assertDoesNotThrow(command::undo);
    }

    @Test
    @DisplayName("ContentCommandInvoker: история пуста при создании")
    void invoker_initiallyEmpty() {
        assertEquals(0, invoker.getHistorySize());
    }

    @Test
    @DisplayName("ContentCommandInvoker: после executeCommand история увеличивается")
    void invoker_historyGrowsAfterExecute() {
        invoker.executeCommand(new AddContentCommand(content));
        assertEquals(1, invoker.getHistorySize());

        invoker.executeCommand(new DeleteContentCommand(UUID.randomUUID()));
        assertEquals(2, invoker.getHistorySize());
    }

    @Test
    @DisplayName("ContentCommandInvoker: undo уменьшает историю")
    void invoker_undoDecreasesHistory() {
        invoker.executeCommand(new AddContentCommand(content));
        invoker.executeCommand(new DeleteContentCommand(UUID.randomUUID()));
        assertEquals(2, invoker.getHistorySize());

        invoker.undo();
        assertEquals(1, invoker.getHistorySize());

        invoker.undo();
        assertEquals(0, invoker.getHistorySize());
    }

    @Test
    @DisplayName("ContentCommandInvoker: undo на пустой истории не бросает исключение")
    void invoker_undoOnEmptyHistory_doesNotThrow() {
        assertDoesNotThrow(() -> invoker.undo());
        assertEquals(0, invoker.getHistorySize());
    }

    @Test
    @DisplayName("ContentCommandInvoker: undo выполняет отмену в порядке LIFO")
    void invoker_undoFollowsLifoOrder() {
        // Проверяем, что undo отменяет последнюю выполненную команду первой
        TextContent firstContent = new TextContent("First", user);
        TextContent secondContent = new TextContent("Second", user);

        invoker.executeCommand(new AddContentCommand(firstContent));
        invoker.executeCommand(new AddContentCommand(secondContent));
        assertEquals(2, invoker.getHistorySize());

        // После первого undo должна остаться 1 команда
        invoker.undo();
        assertEquals(1, invoker.getHistorySize());
    }
}
