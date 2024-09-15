package ru.zahaand.patterns.adapter;

import ru.zahaand.patterns.adapter.impl.UserToExternalUserAdapter;

/**
 * Интерфейс ExternalUserSystem определяет контракт для обработки объектов ExternalUser.
 * Служит для демонстрации паттерна Adapter, позволяя адаптировать объекты класса User к формату ExternalUser,
 * который может быть использован внешней системой.
 *
 * @see UserToExternalUserAdapter
 */
public interface ExternalUserSystem {

    void processUser(ExternalUser user);
}
