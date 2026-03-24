package ru.zahaand.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * <h1>Паттерн Singleton — Вариант 5: Потокобезопасный с synchronized (Thread-Safe Singleton)</h1>
 *
 * <p>Метод {@link #getInstance()} синхронизирован через ключевое слово {@code synchronized}.
 * Это гарантирует, что в многопоточной среде одновременно только один поток может создать экземпляр.
 *
 * <p><strong>Недостаток:</strong> Синхронизация применяется при <em>каждом</em> вызове {@code getInstance()},
 * что создаёт накладные расходы даже после создания объекта.
 * Для лучшей производительности используйте {@link DoubleCheckedLockingSingleton} или {@link HolderSingleton}.
 *
 * <h3>Преимущества:</h3>
 * <ul>
 *     <li><strong>Потокобезопасность</strong>: Гарантирует единственный экземпляр в многопоточной среде.</li>
 *     <li><strong>Ленивое создание</strong>: Объект создаётся при первом обращении.</li>
 * </ul>
 *
 * <h3>Недостатки:</h3>
 * <ul>
 *     <li><strong>Производительность</strong>: Синхронизация при каждом вызове {@code getInstance()}
 *     может стать узким местом при высокой нагрузке.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     // Потокобезопасно, но с накладными расходами на синхронизацию
 *     ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
 *     instance.createContent("My content");
 * }</pre>
 *
 * @see EagerSingleton
 * @see LazySingleton
 * @see DoubleCheckedLockingSingleton
 * @see HolderSingleton
 * @see EnumSingleton
 */
@Slf4j
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
        log.info("ThreadSafeSingleton: instance created");
    }

    /**
     * Возвращает единственный экземпляр. Метод полностью синхронизирован —
     * гарантирует потокобезопасность, но снижает производительность при частых вызовах.
     *
     * @return единственный экземпляр {@link ThreadSafeSingleton}
     */
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public boolean createContent(String content) {
        log.info("[ThreadSafeSingleton] Content created: {}", content);
        return true;
    }

    public boolean deleteContent(UUID id) {
        log.info("[ThreadSafeSingleton] Content deleted: {}", id);
        return true;
    }
}
