package ru.zahaand.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * <h1>Паттерн Singleton — Вариант 3: Ленивое создание (Lazy Initialization)</h1>
 *
 * <p>Экземпляр создаётся только при первом обращении к {@link #getInstance()}.
 * Это позволяет откладывать создание объекта до момента его реальной необходимости.
 *
 * <p><strong>ВНИМАНИЕ:</strong> Данная реализация <em>не является потокобезопасной</em>.
 * В многопоточной среде возможно создание нескольких экземпляров одновременно,
 * если два потока одновременно пройдут проверку {@code instance == null}.
 * Используйте только в однопоточных приложениях.
 *
 * <h3>Преимущества:</h3>
 * <ul>
 *     <li><strong>Ленивое создание</strong>: Объект создаётся только при необходимости.</li>
 *     <li><strong>Простота</strong>: Легко понять и реализовать.</li>
 * </ul>
 *
 * <h3>Недостатки:</h3>
 * <ul>
 *     <li><strong>Не потокобезопасен</strong>: Требует внешней синхронизации при использовании в многопоточной среде.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     // ТОЛЬКО для однопоточных приложений
 *     LazySingleton instance = LazySingleton.getInstance();
 *     instance.createContent("My content");
 * }</pre>
 *
 * @see EagerSingleton
 * @see ThreadSafeSingleton
 * @see DoubleCheckedLockingSingleton
 * @see HolderSingleton
 * @see EnumSingleton
 */
@Slf4j
public class LazySingleton {

    /**
     * Приватная статическая переменная — изначально {@code null}.
     */
    private static LazySingleton instance;

    /**
     * Приватный конструктор предотвращает создание экземпляров извне.
     */
    private LazySingleton() {
        log.info("LazySingleton: instance created on first access");
    }

    /**
     * Возвращает единственный экземпляр. Создаёт его при первом вызове.
     * <p><strong>Не потокобезопасен!</strong> В многопоточной среде используйте
     * {@link ThreadSafeSingleton} или {@link DoubleCheckedLockingSingleton}.
     *
     * @return единственный экземпляр {@link LazySingleton}
     */
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public boolean createContent(String content) {
        log.info("[LazySingleton] Content created: {}", content);
        return true;
    }

    public boolean deleteContent(UUID id) {
        log.info("[LazySingleton] Content deleted: {}", id);
        return true;
    }
}
