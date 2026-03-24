package ru.zahaand.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * <h1>Паттерн Singleton — Обзор всех вариантов реализации</h1>
 *
 * <p>Паттерн Singleton гарантирует, что класс имеет только один экземпляр,
 * и предоставляет глобальную точку доступа к нему.
 *
 * <p>В данном пакете представлены все основные варианты реализации паттерна:
 * <ol>
 *     <li>{@link HolderSingleton} — Holder (Bill Pugh / Initialization-on-Demand):
 *         <strong>рекомендуемый вариант</strong> — ленивый, потокобезопасный, без синхронизации.</li>
 *     <li>{@link EnumSingleton} — Enum-Singleton:
 *         рекомендован Джошуа Блохом. Защищён от рефлексии и десериализации.</li>
 *     <li>{@link LazySingleton} — Ленивое создание (Lazy Initialization):
 *         экземпляр создаётся при первом обращении. <strong>Не потокобезопасен.</strong></li>
 *     <li>{@link EagerSingleton} — Раннее создание (Eager Initialization):
 *         экземпляр создаётся при загрузке класса. Потокобезопасен, прост, но тратит ресурсы.</li>
 *     <li>{@link ThreadSafeSingleton} — Синхронизированный метод (Thread-Safe):
 *         потокобезопасен, но {@code synchronized} при каждом вызове снижает производительность.</li>
 *     <li>{@link DoubleCheckedLockingSingleton} — Двойная проверка блокировки (DCL):
 *         потокобезопасен, синхронизирует только создание. Требует {@code volatile}.</li>
 * </ol>
 *
 * <h2>Вариант 1: Holder (Bill Pugh / Initialization-on-Demand)</h2>
 *
 * <p>Наиболее рекомендуемый подход для большинства случаев в Java.
 * Использует гарантии ClassLoader'а JVM: вложенный статический класс {@link SingletonHolder}
 * загружается только при первом обращении к {@link #getInstance()}, что обеспечивает
 * ленивую инициализацию <em>без какой-либо явной синхронизации</em>.
 *
 * <p>JVM гарантирует, что статическая инициализация класса выполняется ровно один раз
 * и является потокобезопасной по определению (Java Language Specification §12.4).
 *
 * <h3>Преимущества:</h3>
 * <ul>
 *     <li><strong>Потокобезопасность</strong>: Гарантируется загрузчиком классов JVM.</li>
 *     <li><strong>Ленивое создание</strong>: Экземпляр создаётся только при первом обращении.</li>
 *     <li><strong>Производительность</strong>: Нет накладных расходов на синхронизацию.</li>
 *     <li><strong>Простота</strong>: Элегантный и лаконичный код.</li>
 * </ul>
 *
 * <h3>Недостатки:</h3>
 * <ul>
 *     <li><strong>Нет параметров</strong>: Не поддерживает передачу параметров при создании.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     // Рекомендуемый вариант для большинства случаев
 *     HolderSingleton instance = HolderSingleton.getInstance();
 *     instance.createContent("My content");
 * }</pre>
 *
 * @see EnumSingleton
 * @see LazySingleton
 * @see EagerSingleton
 * @see ThreadSafeSingleton
 * @see DoubleCheckedLockingSingleton
 */
@Slf4j
public class HolderSingleton {

    private HolderSingleton() {
        log.info("HolderSingleton: instance created on first access via Holder");
    }

    /**
     * Вложенный статический класс-держатель. Загружается JVM только при первом обращении
     * к {@link HolderSingleton#getInstance()}, обеспечивая ленивую потокобезопасную инициализацию.
     */
    private static final class SingletonHolder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    /**
     * Возвращает единственный экземпляр. Потокобезопасен без синхронизации —
     * гарантируется ClassLoader'ом JVM.
     *
     * @return единственный экземпляр {@link HolderSingleton}
     */
    public static HolderSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean createContent(String content) {
        log.info("[HolderSingleton] Content created: {}", content);
        return true;
    }

    public boolean deleteContent(UUID id) {
        log.info("[HolderSingleton] Content deleted: {}", id);
        return true;
    }
}
