
# Doorbell Document

As you know in the [README](README.md) file, there are 3 kind of doorbells to ring.

- Activity Doorbell
- Callback Doorbell
- Door Doorbell

## Activity Doorbell

The Activity Doorbells are used to ring to start activities.

### Creating

There are 4 static methods defined in the `Doorbell` class to create the activity doorbells.

- `Doorbell.with(Context)`
- `Doorbell.with(Activity)`
- `Doorbell.with(Fragment)`
- `Doorbell.with(Starter)`

They all return a instance of `ActivityDoorbell.Builder` class to build activity doorbell with some
methods listed below in a chain.

### Given the Activity class to start

```java
.start(Activity.class) // or
.start(Intent) // or
.start(Activity1.class, Activity2.class, ...) // or
.start(Intent, Intent, ...)
```

### Sets the request code

```java
.requestCode(int)
```

When the request code is given and valid, then the `.startActivityForResult()` method of starter 
will be called.

### Using the condition

```java
.condition(boolean) // or
.condition(Condition)
```

If the `.condition()` not called or `Condition` is null, then the condition of doorbell will always 
be passed.

### Ring Listeners

There are 4 kind of listeners to be notified.

- `.ring()`
- `.ring(OnAllowListener)` 
- `.ring(OnBlockListener)` 
- `.ring(RingListener)` 

When the optional condition is passed, the called order of callbacks is:

1. The involved door's `onAllow()` called if given 
2. The ring listener's `onAllow()` callback if given
3. The activity started
4. The involved door's `onComplete()` called if given
5. The ring listener's `onComplete()` called if given

Otherwise, the involved door's `onBlock()` called, and then `onBlock()` callback of listener called if
given.

#### Simple Ring Listener

There is a convenience class called `SimpleRingListener` to extend when you only want to listen for 
a subset of ringing callbacks: `onAllow()`, `onComplete()` or `onBlock()`.

### Setup a Door to get involved

```java
.door(Door)
```

Once you call the `.door()` method and the given door param isn't `null`, then the condition will be
ignored.

### Puts some extras

```java
.extra(String, int)
.extra(String, boolean)
.extra(String, long)
.extra(String, float)
.extra(String, double)
.extra(String, String)
// ...
.extras(Bundle)
```

**Notice: If you start multiple activties and these methods chained, then all intents affected.**

### Sets additional options

This feature requires Android SDK API >= 16.

```java
.options(Bundle)
```

### Incoming or Outgoing Transition

```java
.enter(int) // or
.exit(int) // or
.transition(int, int)
```

### Define your Starter

Maybe your apps using the MVP and you want to start activities in the `Presenter` classes. So let 
your Presenter class implement the `Starter` interface.

```java
public class Presenter implements Starter {
  private Starter starter = Starters.newStarter(fragment);

  @Override
  public void startActivity(Intent intent, Bundle options) {
    // Take care of NPE
    starter.startActivity(intent, options);
  }

  @Override
  public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
    // Take care of NPE
    starter.startActivityForResult(intent, requestCode, options);
  }

  @Override
  public void startActivities(Intent[] intents, Bundle options) {
    starter.startActivities(intents, options);
  }

  @Nullable
  @Override
  public Activity getActivity() {
    // Take care of NPE
    return starter.getActivity();
  }

  @Override
  public int getEnter() {
    return starter.getEnter();
  }

  @Override
  public int getExit() {
    return starter.getEnter();
  }
}
```

And then create a doorbell with presenter by: `Doorbell.with(presenter);`

#### Null Starter

Doorbell provides a `NullStarter` to implements the Null Object Pattern for `Starter` interface.

The null starter do nothing when starting activities, just quietly.

You can use `Starters.STATER_NULL` to get it.

## Callback Doorbell

The Callback Doorbells are used to ring to call callbacks with a required condition.

### Creating

There are 3 static methods defined in the `Doorbell` class to create the callback doorbells.

- `Doorbell.create(boolean)`
- `Doorbell.create(Condition)`
- `Doorbell.create(Door)` // Setup the door to get involved and also as condition of ringing

They all return a instance of `CallbackDoorbell.Builder` class to build callback.

### Ring Listeners

There ara 3 kind of listeners to be notified.

- `.ring(OnAllowListener)` 
- `.ring(OnBlockListener)` 
- `.ring(RingListener)` 

When the condition is passed, the called order of callbacks is:

1. The involved door's `onAllow()` called if given 
2. The ring listener's `onAllow()` callback if given
3. The involved door's `onComplete()` called if given
4. The ring listener's `onComplete()` called if given

Otherwise, the involved door's `onBlock()` called if given, and then `onBlock()` callback of listener 
called if given.


## Door Doorbell

The Door Doorbell are used to ring the doors.

### Define your Door

We define a login door that do nothing when the condition is passed or toast a message when blocked.

```java
  /**
   * A door to confirm the user login or not.
   */
  public class LoginDoor implements Door {
    @Override
    public boolean test() {
      return condition;
    }

    @Override
    public void onAllow() {
    }
    
    @Override
    public void onComplete() {
    }

    @Override
    public void onBlock() {
      toast("You must login first!");
    }
  }
```

### Ring the Door

```java
Doorbell.ring(new LoginDoor());
```

When the condition of the login door is passed, `onAllow()` will be called, otherwise 
`onBlock()` called.

### Simple Door

There is a convenience class called `SimpleDoor` to extend when you only want to listen for a subset 
of callbacks.

Here the login door doesn't need the `onAllow()` and `onComplete()` callbacks because they do nothing. 
So we can define the login door `extends SimpleDoor` instead.

```java
  /**
   * A door to confirm the user login or not.
   */
  public class LoginDoor extends SimpleDoor {
    @Override
    public boolean test() {
      return condition;
    }

    @Override
    public void onBlock() {
      toast("You must login first!");
    }
  }
```

## Others

### Setup default transition

Call `Doorbell.setDefaultTransition()` to setup default transition.