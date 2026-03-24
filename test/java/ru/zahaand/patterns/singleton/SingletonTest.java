package ru.zahaand.patterns.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для всех вариантов реализации паттерна Singleton.
 * Демонстрируют ключевое свойство: {@code getInstance()} всегда возвращает один и тот же объект.
 */
@DisplayName("Паттерн Singleton — все варианты реализации")
class SingletonTest {

    // ─── Eager Singleton ───────────────────────────────────────────────────────

    @Test
    @DisplayName("EagerSingleton: два вызова возвращают один и тот же экземпляр")
    void eagerSingleton_sameInstance() {
        EagerSingleton first = EagerSingleton.getInstance();
        EagerSingleton second = EagerSingleton.getInstance();
        assertSame(first, second, "EagerSingleton должен возвращать один и тот же экземпляр");
    }

    @Test
    @DisplayName("EagerSingleton: методы работают корректно")
    void eagerSingleton_methodsWork() {
        assertTrue(EagerSingleton.getInstance().createContent("test"));
    }

    // ─── Lazy Singleton ────────────────────────────────────────────────────────

    @Test
    @DisplayName("LazySingleton: два вызова возвращают один и тот же экземпляр")
    void lazySingleton_sameInstance() {
        LazySingleton first = LazySingleton.getInstance();
        LazySingleton second = LazySingleton.getInstance();
        assertSame(first, second, "LazySingleton должен возвращать один и тот же экземпляр");
    }

    // ─── ThreadSafe Singleton ──────────────────────────────────────────────────

    @Test
    @DisplayName("ThreadSafeSingleton: один экземпляр при конкурентном доступе из 10 потоков")
    void threadSafeSingleton_sameInstanceUnderConcurrency() throws InterruptedException {
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicReference<ThreadSafeSingleton> firstInstance = new AtomicReference<>();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
                firstInstance.compareAndSet(null, instance);
                assertSame(firstInstance.get(), instance);
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }

    // ─── Double-Checked Locking Singleton ─────────────────────────────────────

    @Test
    @DisplayName("DoubleCheckedLockingSingleton: один экземпляр при конкурентном доступе из 10 потоков")
    void doubleCheckedLockingSingleton_sameInstanceUnderConcurrency() throws InterruptedException {
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicReference<DoubleCheckedLockingSingleton> firstInstance = new AtomicReference<>();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
                firstInstance.compareAndSet(null, instance);
                assertSame(firstInstance.get(), instance);
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }

    // ─── Holder Singleton ──────────────────────────────────────────────────────

    @Test
    @DisplayName("HolderSingleton: два вызова возвращают один и тот же экземпляр")
    void holderSingleton_sameInstance() {
        HolderSingleton first = HolderSingleton.getInstance();
        HolderSingleton second = HolderSingleton.getInstance();
        assertSame(first, second, "HolderSingleton должен возвращать один и тот же экземпляр");
    }

    @Test
    @DisplayName("HolderSingleton: один экземпляр при конкурентном доступе из 10 потоков")
    void holderSingleton_sameInstanceUnderConcurrency() throws InterruptedException {
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicReference<HolderSingleton> firstInstance = new AtomicReference<>();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                HolderSingleton instance = HolderSingleton.getInstance();
                firstInstance.compareAndSet(null, instance);
                assertSame(firstInstance.get(), instance);
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }

    // ─── Enum Singleton ────────────────────────────────────────────────────────

    @Test
    @DisplayName("EnumSingleton: единственный экземпляр через INSTANCE")
    void enumSingleton_sameInstance() {
        EnumSingleton first = EnumSingleton.INSTANCE;
        EnumSingleton second = EnumSingleton.INSTANCE;
        assertSame(first, second, "EnumSingleton.INSTANCE всегда один и тот же объект");
    }

    @Test
    @DisplayName("EnumSingleton: методы работают корректно")
    void enumSingleton_methodsWork() {
        assertTrue(EnumSingleton.INSTANCE.createContent("test"));
    }

    @Test
    @DisplayName("EnumSingleton: защита от рефлексии — нельзя создать через конструктор")
    void enumSingleton_reflectionProtection() {
        assertThrows(Exception.class, () -> {
            var constructor = EnumSingleton.class.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            constructor.newInstance("INSTANCE");
        }, "Enum нельзя инстанцировать через рефлексию");
    }
}
