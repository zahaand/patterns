# Design Patterns Examples

Этот репозиторий содержит примеры реализации различных паттернов проектирования на языке Java. Проект демонстрирует использование как порождающих, так и структурных и поведенческих паттернов в контексте современной разработки (с использованием элементов Spring Framework и Lombok).

## Содержание

- [Порождающие паттерны](#порождающие-паттерны)
- [Структурные паттерны](#структурные-паттерны)
- [Поведенческие паттерны](#поведенческие-паттерны)
- [Технологический стек](#технологический-стек)

---

## Порождающие паттерны

Эти паттерны отвечают за механизмы создания объектов, позволяя создавать их наиболее подходящим для конкретной ситуации способом.

- **[Abstract Factory](./main/java/ru/zahaand/patterns/abstract_factory)** — Предоставляет интерфейс для создания семейств взаимосвязанных объектов (ContentCreator, ContentCreatorFactory).
- **[Factory Method](./main/java/ru/zahaand/patterns/factory_method)** — Определяет интерфейс создания объекта, позволяя подклассам решать, какой класс инстанцировать.
- **[Prototype](./main/java/ru/zahaand/patterns/prototype)** — Позволяет копировать объекты, не вдаваясь в подробности их реализации.
- **[Singleton](./main/java/ru/zahaand/patterns/singleton)** — Гарантирует, что у класса есть только один экземпляр, и предоставляет к нему глобальную точку доступа (SystemDataManagement).

## Структурные паттерны

Эти паттерны отвечают за построение удобных в поддержке иерархий классов и объектов.

- **[Adapter](./main/java/ru/zahaand/patterns/adapter)** — Позволяет объектам с несовместимыми интерфейсами работать вместе (UserToExternalUserAdapter).
- **[Bridge](./main/java/ru/zahaand/patterns/bridge)** — Разделяет абстракцию и реализацию так, чтобы они могли изменяться независимо (ContentStorageBridge).
- **[Composite](./main/java/ru/zahaand/patterns/composite)** — Позволяет сгруппировать объекты в древовидную структуру и работать с ними как с единым целым.
- **[Decorator](./main/java/ru/zahaand/patterns/decorator)** — Позволяет динамически добавлять объектам новые обязанности (EncryptContentDecorator).
- **[Facade](./main/java/ru/zahaand/patterns/facade)** — Предоставляет простой интерфейс к сложной системе классов (ServiceFacade).
- **[Flyweight](./main/java/ru/zahaand/patterns/flyweight)** — Позволяет экономить память, разделяя общее состояние объектов между собой.
- **[Proxy](./main/java/ru/zahaand/patterns/proxy)** — Позволяет подставлять вместо реальных объектов специальные объекты-заменители (ResourceProxy).

## Поведенческие паттерны

Эти паттерны решают задачи эффективной коммуникации между объектами.

- **[Chain of Responsibility](./main/java/ru/zahaand/patterns/chain_of_responsibility)** — Позволяет передавать запросы последовательно по цепочке обработчиков.
- **[Command](./main/java/ru/zahaand/patterns/command)** — Превращает запросы в объекты, позволяя передавать их как аргументы при вызове методов (AddContentCommand).
- **[Iterator](./main/java/ru/zahaand/patterns/iterator)** — Дает возможность последовательно обходить элементы составных объектов, не раскрывая их внутреннего представления.
- **[Mediator](./main/java/ru/zahaand/patterns/mediator)** — Позволяет уменьшить связанность множества классов между собой, благодаря перемещению этих связей в один класс-посредник (ChatMediator).
- **[Memento](./main/java/ru/zahaand/patterns/memento)** — Позволяет сохранять и восстанавливать прошлые состояния объектов (UserContactsMemento).
- **[Observer](./main/java/ru/zahaand/patterns/observer)** — Создает механизм подписки, позволяющий одним объектам следить и реагировать на события в других объектах (NewsFeedPublisher).
- **[State](./main/java/ru/zahaand/patterns/state)** — Позволяет объектам менять поведение в зависимости от своего состояния (ActiveUserState, BlockedUserState).
- **[Strategy](./main/java/ru/zahaand/patterns/strategy)** — Определяет семейство схожих алгоритмов и помещает каждый из них в собственный класс (ContentProcessingStrategy).
- **[Template Method](./main/java/ru/zahaand/patterns/template_method)** — Определяет скелет алгоритма, перекладывая ответственность за некоторые шаги на подклассы.
- **[Visitor](./main/java/ru/zahaand/patterns/visitor)** — Позволяет добавлять в программу новые операции, не изменяя классы объектов, над которыми эти операции совершаются.

---

## Технологический стек

- **Java** (версия 17+)
- **Spring Framework** (Context, Beans)
- **Lombok** (для уменьшения бойлерплейта)
- **SLF4J** (логирование)

---
*Проект подготовлен в учебных целях.*
