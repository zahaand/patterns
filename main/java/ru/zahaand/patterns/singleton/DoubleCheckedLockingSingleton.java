package ru.zahaand.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * <h1>Паттерн Singleton — Вариант 6: Двойная проверка блокировки (Double-Checked Locking)</h1>
 *
 * <p>Оптимизированный потокобезопасный Singleton. Синхронизация применяется <em>только при создании</em>
 * экземпляра, а не при каждом обращении к {@link #getInstance()}.
 * Ключевое слово {@code volatile} гарантирует видимость изменений переменной {@code instance}
 * во всех потоках и предотвращает частичную инициализацию объекта.
 *
 * <p><strong>Важно:</strong> {@code volatile} обязателен для корректной работы в Java 5+.
 * Без него компилятор или JVM могут переупорядочить инструкции и поток может получить
 * частично инициализированный объект.
 *
 * <h3>Преимущества:</h3>
 * <ul>
 *     <li><strong>Потокобезопасность</strong>: Гарантирует единственный экземпляр в многопоточной среде.</li>
 *     <li><strong>Производительность</strong>: Синхронизация выполняется только один раз — при создании.</li>
 *     <li><strong>Ленивое создание</strong>: Объект создаётся при первом обращении.</li>
 * </ul>
 *
 * <h3>Недостатки:</h3>
 * <ul>
 *     <li><strong>Сложность</strong>: Требует понимания модели памяти Java и корректного
 *     использования {@code volatile}.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
 *     instance.createContent("My content");
 * }</pre>
 *
 * @see EagerSingleton
 * @see LazySingleton
 * @see ThreadSafeSingleton
 * @see HolderSingleton
 * @see EnumSingleton
 */
@Slf4j
public class DoubleCheckedLockingSingleton {

    /**
     * volatile гарантирует видимость изменений между потоками
     * и предотвращает переупорядочивание инструкций JVM.
     */
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
        log.info("DoubleCheckedLockingSingleton: instance created");
    }

    /**
     * Возвращает единственный экземпляр с двойной проверкой блокировки.
     * Синхронизирует только создание, обеспечивая высокую производительность.
     *
     * @return единственный экземпляр {@link DoubleCheckedLockingSingleton}
     */
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    public boolean createContent(String content) {
        log.info("[DoubleCheckedLockingSingleton] Content created: {}", content);
        return true;
    }

    public boolean deleteContent(UUID id) {
        log.info("[DoubleCheckedLockingSingleton] Content deleted: {}", id);
        return true;
    }
}
