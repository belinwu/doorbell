# Doorbell

[![Bintray](https://api.bintray.com/packages/belinwu/maven/doorbell/images/download.svg)](https://bintray.com/belinwu/maven/doorbell/_latestVersion)

A simple library to start activities or call callbacks with condition in your Android apps.

This is an **alpha** library until V1.0.0 is released.

**DO NOT USE THIS LIBRARY IN PRODUCTION.**

# Download

Doorbell is available on jCenter.

Gradle:

```
compile 'com.wujilin.doorbell:doorbell:0.3.0'
```

# How do I use Doorbell?

Checkout the [Developer Document](DOC.md) for more details and APIs.

## Start a Activity

```java
Doorbell.with(activity)
    .start(AnthorActivity.class)
    .condition(boolean) // Optional
    .ring();
```

When the optional condition is passed, `AnotherActivity` will be launched.

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

# Android SDK Version

Doorbell requires a minimum SDK version of 10.

# Dependencies

```
compile 'com.android.support:support-v4:23.1.0'
compile 'com.android.support:support-annotations:23.1.0'
```

# Release History

Checkout the [CHANGELOG](CHANGELOG.md) file.

# License

The MIT Lisense.

Copyright (c) 2015 Belin Wu.
