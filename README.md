# GitHub Jobs

Этот репозиторий содержит тестовое задание для компании [Ventra](https://ventra.ru).
Реализован простой пример [GitHub Jobs](https://jobs.github.com/api).

##  Задание

```
Реализовать приложение Android на Kotlin, которое будет обращаться к API из списка https://github.com/public-apis/public-apis

Приложение должно состоять из нескольких экранов:

1. Экран списка элементов с простой разметкой.

2. Экран единичного объекта.

3. Экран поиска по объектам. Плюсом будет реализация поиска как с помощью API, так и с локально.

4. Избранные объекты. Реализовать добавление/удаление в локальное избранное.
```

Реализован локальный поиск с помощью обычных LIKE-запросов.

## Возможные проблемы

Если сетевые запросы не будут работать, то нужно отключить использование `socketFactory` в `networkModule`:

```kotlin
single<OkHttpClient> {
    OkHttpClient.Builder()
//        .socketFactory(get<SocketFactory>())
//        ... other methods
        .build()
}
```

Причина кроется в использовании `StrictMode.enableDefaults()` в классе `App`.
[Исследование проблемы](https://github.com/square/okhttp/issues/3537)
