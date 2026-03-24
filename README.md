# Design Patterns Examples

> Учебная демонстрация всех 23 классических паттернов проектирования
> из книги «Design Patterns: Elements of Reusable Object-Oriented Software» (Gang of Four) на Java 17+.
>
> Каждый паттерн изолирован в отдельном пакете, снабжён подробным Javadoc
> и демонстрирует реальный сценарий применения в контексте системы управления контентом.

Проект можно использовать как **справочник** при изучении паттернов проектирования
или как **шаблон** для применения паттернов в собственных проектах.

## Технологии

- **Java 17+**
- **Lombok** — `@Data`, `@Slf4j` и др.
- **SLF4J + slf4j-simple** — логирование
- **Spring Context** — DI для отдельных компонентов
- **JUnit 5** — unit-тесты
- **Maven** — сборка и управление зависимостями

## Быстрый старт

```bash
mvn test        # запустить все тесты
mvn compile     # только компиляция
```

## Структура проекта

```
patterns/
├── pom.xml
├── main/java/ru/zahaand/patterns/
│   ├── domain/                  # Доменные классы (User, Content, TextContent, ImageContent)
│   ├── model/                   # Вспомогательные модели данных
│   ├── enums/                   # ContentType, ImageFormat
│   │
│   ├── singleton/               # Singleton — 6 вариантов
│   ├── prototype/               # Prototype
│   ├── factory_method/          # Factory Method + Simple Factory
│   ├── abstract_factory/        # Abstract Factory
│   ├── builder/                 # Builder (вложен в User)
│   │
│   ├── adapter/                 # Adapter
│   ├── bridge/                  # Bridge
│   ├── composite/               # Composite
│   ├── decorator/               # Decorator
│   ├── facade/                  # Facade
│   ├── flyweight/               # Flyweight
│   ├── proxy/                   # Proxy
│   │
│   ├── chain_of_responsibility/ # Chain of Responsibility
│   ├── command/                 # Command
│   ├── iterator/                # Iterator
│   ├── mediator/                # Mediator
│   ├── memento/                 # Memento
│   ├── observer/                # Observer
│   ├── state/                   # State
│   ├── strategy/                # Strategy
│   ├── template_method/         # Template Method
│   └── visitor/                 # Visitor
│
└── test/java/ru/zahaand/patterns/
    ├── singleton/               # SingletonTest — все 6 вариантов + многопоточность
    ├── prototype/               # PrototypeTest
    ├── factory_method/          # FactoryMethodTest
    ├── chain_of_responsibility/ # ChainOfResponsibilityTest
    ├── command/                 # CommandTest
    ├── strategy/                # StrategyTest
    ├── memento/                 # MementoTest
    ├── observer/                # ObserverTest
    ├── state/                   # StateTest
    ├── visitor/                 # VisitorTest
    ├── template_method/         # TemplateMethodTest
    └── builder/                 # BuilderTest
```

---

## Порождающие паттерны (Creational)

### Singleton — 6 вариантов

Гарантирует, что класс имеет только один экземпляр, и предоставляет глобальную точку доступа к нему.

| # | Класс                                                                                                           | Вариант            | Потокобезопасность      | Ленивая инициализация |
|---|-----------------------------------------------------------------------------------------------------------------|--------------------|-------------------------|-----------------------|
| 1 | [`HolderSingleton`](./main/java/ru/zahaand/patterns/singleton/HolderSingleton.java)                             | Bill Pugh Holder   | ✅ (ClassLoader)         | ✅                     |
| 2 | [`EnumSingleton`](./main/java/ru/zahaand/patterns/singleton/EnumSingleton.java)                                 | Enum               | ✅ + защита от рефлексии | ❌                     |
| 3 | [`LazySingleton`](./main/java/ru/zahaand/patterns/singleton/LazySingleton.java)                                 | Ленивое создание   | ❌                       | ✅                     |
| 4 | [`EagerSingleton`](./main/java/ru/zahaand/patterns/singleton/EagerSingleton.java)                               | Раннее создание    | ✅ (ClassLoader)         | ❌                     |
| 5 | [`ThreadSafeSingleton`](./main/java/ru/zahaand/patterns/singleton/ThreadSafeSingleton.java)                     | Synchronized метод | ✅                       | ✅                     |
| 6 | [`DoubleCheckedLockingSingleton`](./main/java/ru/zahaand/patterns/singleton/DoubleCheckedLockingSingleton.java) | DCL + volatile     | ✅                       | ✅                     |

> **Рекомендация:** `HolderSingleton` для большинства случаев, `EnumSingleton` когда нужна защита от рефлексии и
> десериализации.

**Пример (вариант 1 — Holder):**

```java
public class HolderSingleton {
    private HolderSingleton() {
    }

    private static class Holder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

**Пример (вариант 2 — Enum):**

```java
public enum EnumSingleton {
    INSTANCE;

    public boolean createContent(String content) {
        log.info("[EnumSingleton] Content created: {}", content);
        return true;
    }
}

// Использование:
EnumSingleton instance = EnumSingleton.INSTANCE;
instance.createContent("My content");
```

---

### Builder

Позволяет создавать сложные объекты пошагово. Обязательные параметры передаются в конструктор, опциональные — через
fluent-методы.

Реализован как вложенный класс `User.UserBuilder` (Lombok `@Builder`).

```java
User user = User.builder()
        .mobilePhone("+7 999 000-00-00")
        .email("user@mail.ru")
        .name("Иван")
        .age(30)
        .build();
```

---

### Prototype

Создаёт новые объекты путём копирования существующих, избегая дорогостоящей инициализации.

`Content` реализует `Cloneable`. Клон получает **новый UUID**; `ImageContent` делает **глубокое копирование** массива
байт.

```java
TextContent original = new TextContent(user, "Hello");
TextContent clone = (TextContent) original.clone();
// clone.getId() != original.getId() — новый UUID
// clone.getUser() == original.getUser() — тот же пользователь
```

---

### Factory Method

Определяет интерфейс для создания объекта, но позволяет подклассам решать, какой класс инстанцировать.

- `ContentFactory` — абстрактный создатель (Factory Method)
- `TextContentFactory`, `ImageContentFactory` — конкретные фабрики
- `ContentFactoryMethod` — Simple Factory (для сравнения: не является паттерном GoF, но часто используется)

```java
// Factory Method — через подкласс
ContentFactory factory = new TextContentFactory();
Content content = factory.createContent(user);

// Simple Factory — через switch
Content content = ContentFactoryMethod.createContent(ContentType.TEXT, user);
```

> **Разница:** Simple Factory — это просто вспомогательный метод. Factory Method — это паттерн, где выбор фабрики
> вынесен на уровень клиентского кода через полиморфизм.

---

### Abstract Factory

Создаёт семейства связанных объектов без указания их конкретных классов.

`ContentCreatorFactory` создаёт семейства объектов `TextContentCreator` / `ImageContentCreator`.

```java
ContentCreatorFactory factory = new ContentCreatorFactory();

ContentCreator textCreator = factory.createTextContentCreator();
ContentCreator imageCreator = factory.createImageContentCreator();
```

---

## Структурные паттерны (Structural)

### Adapter

Позволяет объектам с несовместимыми интерфейсами работать вместе.

`UserToExternalUserAdapter` адаптирует внутренний `User` к интерфейсу `ExternalUser` внешней системы.

```java
User user = User.builder().name("Иван").email("ivan@mail.ru").build();

ExternalUser externalUser = new UserToExternalUserAdapter(user);
externalSystem.register(externalUser);
```

---

### Bridge

Разделяет абстракцию и реализацию так, чтобы они могли изменяться независимо.

`ContentStorageBridge` — абстракция; `TextContentStorageBridge`, `ImageContentStorageBridge` — конкретные реализации.

```java
ContentStorageBridge bridge = new TextContentStorageBridge(storageImpl);

bridge.save(textContent);
```

---

### Composite

Компонует объекты в древовидные структуры для представления иерархий «часть–целое».

`ContentComposite` позволяет работать с группой контента как с единым объектом.

```java
ContentComposite folder = new ContentComposite();
folder.add(textContent);
folder.add(imageContent);

folder.display(); // отображает все вложенные элементы
```

---

### Decorator

Динамически добавляет объекту новые обязанности, не изменяя его класс.

`EncryptContentDecorator` оборачивает `Content` и добавляет шифрование при отображении.

```java
DisplayableContent encrypted = new EncryptContentDecorator(textContent);

encrypted.display(); // выводит зашифрованный текст
```

---

### Facade

Предоставляет упрощённый интерфейс к сложной подсистеме.

`ServiceFacade` объединяет `ContentService` и `UserService`, скрывая детали взаимодействия.

```java
ServiceFacade facade = new ServiceFacade(contentService, userService);

facade.createAndPublishContent(user, "Hello World", ContentType.TEXT);
```

---

### Flyweight

Эффективно поддерживает большое количество мелких объектов, разделяя общее состояние.

`ImageFlyweight` кэширует загруженные изображения через `SoftReference` — JVM автоматически освобождает кэш при нехватке
памяти.

```java
ImageFlyweight flyweight = ImageFlyweight.getInstance();

byte[] img1 = flyweight.getImage("/path/to/image.png");
byte[] img2 = flyweight.getImage("/path/to/image.png"); // из кэша
```

---

### Proxy

Предоставляет суррогатный объект, контролирующий доступ к другому объекту.

`ResourceProxy` контролирует доступ к `Resource`, добавляя проверку прав и ленивую загрузку.

```java
Resource resource = new ResourceProxy(realResource, currentUser);

resource.load(); // проверит права перед загрузкой
```

---

## Поведенческие паттерны (Behavioral)

### Chain of Responsibility

Передаёт запрос по цепочке обработчиков, каждый из которых решает — обработать или передать дальше.

`AbstractContentHandler` — базовый класс с шаблонным методом `handle()`. Метод `setNext()` возвращает следующий
обработчик для fluent-построения цепочки.

```java
ContentHandler chain = new TextContentHandler();
chain.setNext(new ImageContentHandler());

chain.handle(textContent);  // обработает TextContentHandler
chain.handle(imageContent); // обработает ImageContentHandler
```

---

### Command

Инкапсулирует запрос как объект, позволяя параметризовать клиентов, ставить запросы в очередь и поддерживать отмену
операций.

`ContentCommandInvoker` хранит историю команд (LIFO) и поддерживает `undo()`.

```java
ContentCommandInvoker invoker = new ContentCommandInvoker();
invoker.execute(new AddContentCommand(editor, content));
invoker.execute(new EditContentCommand(editor, content, newContent));

invoker.undo(); // отменяет EditContentCommand
invoker.undo(); // отменяет AddContentCommand
```

---

### Iterator

Предоставляет способ последовательного доступа к элементам коллекции без раскрытия её внутреннего представления.

`ContentListIterator` реализует `ContentIterator` для обхода списка контента.

```java
ContentIterator iterator = new ContentListIterator(contentList);

while(iterator.hasNext()){
    Content content = iterator.next();
    content.display();
}
```

---

### Mediator

Определяет объект, инкапсулирующий взаимодействие множества объектов, уменьшая связанность между ними.

`ChatMediator` координирует обмен сообщениями между пользователями, не давая им ссылаться друг на друга напрямую.

```java
MessageMediator chat = new ChatMediator();
chat.register(user1);
chat.register(user2);

chat.send(user1, "Hello everyone!");
```

---

### Memento

Сохраняет и восстанавливает предыдущее состояние объекта без раскрытия деталей реализации.

`UserContactsManager` управляет снимками состояния контактов `User`. Интерфейс `UserContactsMemento` — маркерный, данные
доступны только внутри `User`.

```java
UserContactsManager manager = new UserContactsManager();
manager.save(user.saveContactsState());       // сохранить состояние
        
user.setMobilePhone("+7 000 000-00-00");      // изменить
user.restoreContactsState(manager.restore()); // восстановить
```

---

### Observer

Определяет зависимость «один ко многим» между объектами: при изменении состояния одного все зависимые уведомляются
автоматически.

`NewsFeedPublisher` реализует `Subject`; подписчики реализуют `Observer`.

```java
NewsFeedPublisher publisher = new NewsFeedPublisher();
publisher.subscribe(observer1);
publisher.subscribe(observer2);

publisher.publish(newContent); // уведомит всех подписчиков
```

---

### State

Позволяет объекту изменять своё поведение при изменении внутреннего состояния.

`User` делегирует поведение текущему `UserState`. `ActiveUserState` и `BlockedUserState` — конкретные состояния.

```java
user.setState(new ActiveUserState());
user.performAction(); // действие активного пользователя

user.setState(new BlockedUserState());
user.performAction(); // действие заблокированного пользователя
```

---

### Strategy

Определяет семейство алгоритмов, инкапсулирует каждый из них и делает их взаимозаменяемыми.

`TextContentProcessingStrategy` и `ImageContentProcessingStrategy` реализуют `ContentProcessingStrategy`. Используют
`instanceof` pattern matching (Java 17+) вместо небезопасного приведения типов.

```java
ContentProcessingStrategy strategy = new TextContentProcessingStrategy();
strategy.process(textContent);  // обработает текст

strategy = new ImageContentProcessingStrategy();
strategy.process(imageContent); // обработает изображение
```

---

### Template Method

Определяет скелет алгоритма в базовом классе, позволяя подклассам переопределять отдельные шаги.

`ContentProcessor` — абстрактный класс с шаблонным методом `process()`. `TextContentProcessor` и
`ImageContentProcessor` — конкретные реализации.

```java
ContentProcessor processor = new TextContentProcessor();

processor.process(textContent); // вызывает: validate() → prepare() → doProcess() → finalize()
```

---

### Visitor

Позволяет добавлять новые операции к объектам без изменения их классов.

`ArchiverContentVisitor` архивирует контент; `InfoPrinterContentVisitor` выводит информацию. Оба реализуют
`ContentVisitor`.

```java
ContentVisitor visitor = new InfoPrinterContentVisitor();
textContent.acceptVisitor(visitor);  // выведет информацию о тексте
imageContent.acceptVisitor(visitor); // выведет информацию об изображении

visitor = new ArchiverContentVisitor();
textContent.acceptVisitor(visitor);  // заархивирует текст
```
