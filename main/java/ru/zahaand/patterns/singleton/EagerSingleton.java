package ru.zahaand.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * <h1>Паттерн Singleton — Вариант 4: Раннее создание (Eager Initialization)</h1>
 *
 * <p>Экземпляр создаётся в момент загрузки класса JVM — до первого обращения к {@link #getInstance()}.
 * Это гарантирует потокобезопасность без дополнительной синхронизации, так как инициализация
 * статических полей выполняется JVM атомарно.
 *
 * <h3>Преимущества:</h3>
 * <ul>
 *     <li><strong>Простота</strong>: Нет необходимости в синхронизации.</li>
 *     <li><strong>Потокобезопасность</strong>: Гарантируется ClassLoader'ом JVM.</li>
 * </ul>
 *
 * <h3>Недостатки:</h3>
 * <ul>
 *     <li><strong>Расход ресурсов</strong>: Экземпляр создаётся даже если он никогда не будет использован.</li>
 *     <li><strong>Нет обработки исключений</strong>: Ошибки инициализации приведут к {@link ExceptionInInitializerError}.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     EagerSingleton instance = EagerSingleton.getInstance();
 *     instance.createContent("My content");
 * }</pre>
 *
 * @see LazySingleton
 * @see ThreadSafeSingleton
 * @see DoubleCheckedLockingSingleton
 * @see HolderSingleton
 * @see EnumSingleton
 */
@Slf4j
public class EagerSingleton {

    /**
     * Единственный экземпляр класса, создаётся сразу при загрузке класса.
     */
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * Приватный конструктор предотвращает создание экземпляров извне.
     */
    private EagerSingleton() {
        log.info("EagerSingleton: instance created at class load time");
    }

    /**
     * Возвращает единственный экземпляр. Потокобезопасен без синхронизации.
     *
     * @return единственный экземпляр {@link EagerSingleton}
     */
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public boolean createContent(String content) {
        log.info("[EagerSingleton] Content created: {}", content);
        return true;
    }

    public boolean deleteContent(UUID id) {
        log.info("[EagerSingleton] Content deleted: {}", id);
        return true;
    }
}
