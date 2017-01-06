# V0.6.0 (2017-01-06): Add `exit()` function.

- Add `exit()` function.

# V0.5.0 (2016-02-15): Null Checking when Starting

- Add `null` checking of context object into the `ActivityDoorbell#start(...)` methods when constructing the `Intent`.
- Let `Doorbell.ring(Door)` return the condition of the given door.
- Rename `Doorbell.defaultTransition()` to `Doorbell.setDefaultTransition()`.
- Reformat codes with 4 intents.

# V0.4.0 (2015-12-21): Null Starter Feature

- Add `NullStarter` to implements the Null Object Pattern for `Starter` interface.
- Fix typo wrong.

# V0.3.0 (2015-12-19): Support Starting Multiple Activities

- Support starting multiple activities.
- Remove `Transition` interface.
- New `Starter` creating methods with transition animation resource id.
- New abstract `Starter` implementation added.
- Support setting default transition animation.

# V0.2.0 (2015-11-21): Internal Logic Change Release

- Change the called order of callbacks when ringing.
- Add `onComplete()` callback into `RingListener`.
- Add `SimpleRingListener` convenience class.
- Add more `.extra()` for activity doorbell.
- New `transition(Transition)` method for activity doorbell.

# V0.1.0 (2015-11-21): Initial Development Release

Initial development release