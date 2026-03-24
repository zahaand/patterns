package ru.zahaand.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * <h1>Паттерн Singleton — Вариант 2: Enum-Singleton</h1>
 *
 * <p>Рекомендован Джошуа Блохом в «Effective Java» (Item 3) как наилучший способ реализации Singleton.
 * Java-перечисления ({@code enum}) по определению создаются JVM ровно один раз и защищены
 * от создания дополнительных экземпляров через рефлексию и десериализацию.
 *
 * <p>Обычные классы-Singleton <strong>уязвимы</strong> к атакам через:
 * <ul>
 *     <li>Рефлексию ({@code Constructor.setAccessible(true)})</li>
 *     <li>Десериализацию (каждый {@code readObject()} создаёт новый объект)</li>
 * </ul>
 * Enum автоматически защищён от обоих векторов.
 *
 * <h3>Преимущества:</h3>
 * <ul>
 *     <li><strong>Защита от рефлексии</strong>: JVM не позволяет создавать enum через рефлексию.</li>
 *     <li><strong>Защита от десериализации</strong>: Enum всегда десериализуется в тот же экземпляр.</li>
 *     <li><strong>Потокобезопасность</strong>: Гарантируется JVM.</li>
 *     <li><strong>Простота</strong>: Минимум кода.</li>
 * </ul>
 *
 * <h3>Недостатки:</h3>
 * <ul>
 *     <li><strong>Нет ленивой инициализации</strong>: Экземпляр создаётся при загрузке класса.</li>
 *     <li><strong>Наследование</strong>: Enum не может наследоваться от другого класса.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 *     EnumSingleton instance = EnumSingleton.INSTANCE;
 *     instance.createContent("My content");
 * }</pre>
 *
 * @see EagerSingleton
 * @see LazySingleton
 * @see ThreadSafeSingleton
 * @see DoubleCheckedLockingSingleton
 * @see HolderSingleton
 */
@Slf4j
public enum EnumSingleton {

    /**
     * Единственный экземпляр Singleton.
     */
    INSTANCE;

    /**
     * Выполняет создание контента.
     *
     * @param content строковое представление контента
     * @return {@code true} если контент успешно создан
     */
    public boolean createContent(String content) {
        log.info("[EnumSingleton] Content created: {}", content);
        return true;
    }

    /**
     * Выполняет удаление контента по идентификатору.
     *
     * @param id идентификатор контента
     * @return {@code true} если контент успешно удалён
     */
    public boolean deleteContent(UUID id) {
        log.info("[EnumSingleton] Content deleted: {}", id);
        return true;
    }
}
