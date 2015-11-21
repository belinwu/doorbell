# Doorbell

A simple library to start activities or call callbacks with condition in your Android apps.

# Download

Gradle:

```
compile 'com.wujilin.doorbell:doorbell:0.1.0' // Coming soon
```

# How do I use Doorbell?

Checkout the [GitHub Wiki](https://github.com/belinwu/doorbell/wiki) for more details.

## Start a Activity

```java
Doorbell.with(activity)
    .start(AnthorActivity.class)
    .condition(boolean) // Optional
    .ring();
```

When the condition is passed, `AnotherActivity` will be launched.

## Call a Callback

```java
Doorbell.create(condition).ring(OnAllowListener);
```

When the condition is passed, `OnAllowListener.onAllow()` will be called.

## Ring a Door

```java
Doorbell.ring(Door);
```

When the condition of the door is passed, `Door.onAllow()` will be called, otherwise `Door.onBlock()` called.

# License

The MIT Lisense.

Copyright (c) 2015 Belin Wu.
