/*
The MIT License (MIT)

Copyright (c) 2015 Belin Wu (http://wujilin.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.wujilin.doorbell;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;

import static com.wujilin.doorbell.starter.Starters.newStarter;
import static com.wujilin.doorbell.util.Objects.requireNonNull;

/**
 * The class represents the requests to be sent like a doorbell can be rung. <br /><br />
 *
 * @see ActivityDoorbell
 * @see CallbackDoorbell
 */
public class Doorbell {

    /**
     * The default resource ID of the animation resource to use for the incoming activity
     */
    private static int defaultEnter;

    /**
     * The default resource ID of the animation resource to use for the outgoing activity
     */
    private static int defaultExit;

    /**
     * The door of the doorbell
     */
    private Door door;

    /**
     * The door get involved
     */
    private Door involvedDoor;

    /**
     * Constructs a new doorbell.
     *
     * @param builder The builder to build the doorbell
     */
    protected Doorbell(Builder builder) {
        this.door         = builder.door;
        this.involvedDoor = builder.involvedDoor;
    }

    /**
     * Ring the doorbell.
     */
    private void ring() {
        if (!door.test()) {
            callOnBlock();
            return;
        }
        callOnAllow();
        callOnComplete();
    }

    /**
     * Calls the onBlock callbacks.
     */
    private void callOnBlock() {
        if (involvedDoor != null) {
            involvedDoor.onBlock();
        }
        door.onBlock();
    }

    /**
     * Calls the onAllow callbacks.
     */
    private void callOnAllow() {
        if (involvedDoor != null) {
            involvedDoor.onAllow();
        }
        door.onAllow();
        onAllow();
    }

    /**
     * Calls the onComplete callbacks.
     */
    private void callOnComplete() {
        if (involvedDoor != null) {
            involvedDoor.onComplete();
        }
        door.onComplete();
    }

    /**
     * Called when this doorbell is allowed to ring.
     */
    protected void onAllow() {
        // do nothing
    }

    /**
     * Creates a activity doorbell with the given context.
     *
     * @param context The context to start activities
     * @return The builder of the activity doorbell
     */
    public static ActivityDoorbell.Builder with(Context context) {
        return with(newStarter(context));
    }

    /**
     * Creates a new activity doorbell with the given activity.
     *
     * @param activity The activity to start activities
     * @return The builder of the activity doorbell
     */
    public static ActivityDoorbell.Builder with(Activity activity) {
        return with(newStarter(activity));
    }

    /**
     * Creates a new activity doorbell with the given fragment.
     *
     * @param fragment The fragment to start activities
     * @return The builder of the activity doorbell
     */
    public static ActivityDoorbell.Builder with(Fragment fragment) {
        return with(newStarter(fragment));
    }

    /**
     * Creates a new activity doorbell with the given starter.
     *
     * @param starter The starter to start activities
     * @return The builder of the activity doorbell
     */
    public static ActivityDoorbell.Builder with(Starter starter) {
        return new ActivityDoorbell.Builder(starter);
    }

    /**
     * Creates a new callback doorbell.
     *
     * @param condition The condition to test
     * @return The builder of the callback doorbell
     */
    public static CallbackDoorbell.Builder create(boolean condition) {
        return new CallbackDoorbell.Builder(condition);
    }

    /**
     * Creates a new callback doorbell.
     *
     * @param condition The condition to test
     * @return The builder of the callback doorbell
     */
    public static CallbackDoorbell.Builder create(Condition condition) {
        return new CallbackDoorbell.Builder(condition);
    }

    /**
     * Creates a new callback doorbell.
     *
     * @param door The door gets involved
     * @return The builder of the callback doorbell
     */
    public static CallbackDoorbell.Builder create(Door door) {
        return new CallbackDoorbell.Builder(door);
    }

    /**
     * Ring the door.
     *
     * @param door The door to ring
     * @return <code>true</code> if the condition of the given door is passed, otherwise <code>false</code>.
     */
    public static boolean ring(Door door) {
        if (door == null) {
            return false;
        }
        if (door.test()) {
            door.onAllow();
            door.onComplete();
            return true;
        }
        door.onBlock();
        return false;
    }

    /**
     * Return the default transition to use for the incoming activity.
     *
     * @return The resource ID of the animation resource to use for the incoming activity.
     */
    public static int getDefaultEnter() {
        return defaultEnter;
    }

    /**
     * Return the default transition to use for the outgoing activity.
     *
     * @return The resource ID of the animation resource to use for the outgoing activity.
     */
    public static int getDefaultExit() {
        return defaultExit;
    }

    /**
     * Setup the default resource ID of the animation resource to use for the incoming/outgoing activity.
     *
     * @param enter The resource ID of the animation resource to use for the incoming activity.
     * @param exit  The resource ID of the animation resource to use for the outgoing activity.
     */
    public static void setDefaultTransition(@AnimRes final int enter, @AnimRes final int exit) {
        defaultEnter = enter;
        defaultExit  = exit;
    }

    /**
     * The builder class to build the doorbell.
     */
    public abstract static class Builder {

        /**
         * The condition of the ring
         */
        private boolean condition = true;

        /**
         * The door for the doorbell
         */
        private Door door;

        /**
         * The door get involved
         */
        private Door involvedDoor;

        /**
         * Sets the condition of the ringing.
         *
         * @param condition The condition to test
         * @return this
         */
        protected Builder condition(boolean condition) {
            this.condition = condition;
            return this;
        }

        /**
         * Sets the door to get involved.
         *
         * @param door The involved door
         * @return this
         */
        protected Builder door(Door door) {
            this.involvedDoor = door;
            return this;
        }

        /**
         * Ring the doorbell.
         *
         * @param listener The listener to be notified when blocked
         */
        public void ring(final OnBlockListener listener) {
            requireNonNull(listener, "The on block listener must not be null.");
            ring(new RingListener() {
                @Override
                public void onAllow() {
                    // do nothing
                }

                @Override
                public void onBlock() {
                    listener.onBlock();
                }

                @Override
                public void onComplete() {
                    // do nothing
                }
            });
        }

        /**
         * Ring the doorbell.
         *
         * @param listener The listener to be notified when allowed
         */
        public void ring(final OnAllowListener listener) {
            requireNonNull(listener, "The on test listener must not be null.");
            ring(new RingListener() {
                @Override
                public void onAllow() {
                    listener.onAllow();
                }

                @Override
                public void onBlock() {
                    // do nothing
                }

                @Override
                public void onComplete() {
                    // do nothing
                }
            });
        }

        /**
         * Ring the doorbell.
         *
         * @param listener The listener to be notified when ringing
         */
        public final void ring(final RingListener listener) {
            requireNonNull(listener, "The ring listener must not be null.");
            if (involvedDoor != null) {
                condition = involvedDoor.test();
            }
            this.door = new Door() {
                @Override
                public boolean test() {
                    return condition;
                }

                @Override
                public void onAllow() {
                    listener.onAllow();
                }

                @Override
                public void onBlock() {
                    listener.onBlock();
                }

                @Override
                public void onComplete() {
                    listener.onComplete();
                }
            };

            // build the doorbell and ring it
            build().ring();
        }

        /**
         * Builds the doorbell.
         *
         * @return The doorbell
         */
        protected abstract Doorbell build();
    }
}
