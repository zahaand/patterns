package ru.zahaand.patterns.command;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <h1>Паттерн Command — Invoker (Вызывающий)</h1>
 *
 * <p>Класс {@code ContentCommandInvoker} является <strong>Invoker</strong>'ом в паттерне Command.
 * Он хранит историю выполненных команд в стеке ({@link Deque}) и предоставляет
 * возможность отменять последние действия (undo).
 *
 * <p>Отличие от {@link ContentEditor}:
 * <ul>
 *     <li>{@link ContentEditor} — простой executor: устанавливает одну команду и выполняет её.</li>
 *     <li>{@link ContentCommandInvoker} — полноценный Invoker: ведёт историю команд,
 *         поддерживает {@link #undo()} для отмены последней команды.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     ContentCommandInvoker invoker = new ContentCommandInvoker();
 *
 *     invoker.executeCommand(new AddContentCommand(textContent));
 *     invoker.executeCommand(new EditContentCommand(id, newContent));
 *
 *     invoker.undo(); // отменяет EditContentCommand
 *     invoker.undo(); // отменяет AddContentCommand
 * }</pre>
 *
 * @see ContentCommand
 * @see ContentEditor
 */
@Slf4j
public class ContentCommandInvoker {

    private final Deque<ContentCommand> commandHistory = new ArrayDeque<>();

    /**
     * Выполняет команду и добавляет её в историю для возможной отмены.
     *
     * @param command команда для выполнения
     */
    public void executeCommand(ContentCommand command) {
        command.execute();
        commandHistory.push(command);
        log.info("ContentCommandInvoker: command executed, history size={}", commandHistory.size());
    }

    /**
     * Отменяет последнюю выполненную команду.
     * Если история пуста — логирует предупреждение.
     */
    public void undo() {
        if (commandHistory.isEmpty()) {
            log.warn("ContentCommandInvoker: nothing to undo — command history is empty");
            return;
        }
        ContentCommand lastCommand = commandHistory.pop();
        lastCommand.undo();
        log.info("ContentCommandInvoker: command undone, history size={}", commandHistory.size());
    }

    /**
     * Возвращает количество команд в истории.
     *
     * @return размер истории команд
     */
    public int getHistorySize() {
        return commandHistory.size();
    }
}
