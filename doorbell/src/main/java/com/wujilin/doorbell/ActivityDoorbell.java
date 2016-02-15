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
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;

import java.io.Serializable;

import static com.wujilin.doorbell.starter.Starters.STARTER_NULL;

/**
 * The class represents the doorbell to ring to start activities.
 */
class ActivityDoorbell extends Doorbell {

    /**
     * The value represents no result
     */
    public static final int NO_RESULT = -1;

    /**
     * The starter to start activity
     */
    private Starter starter;

    /**
     * The request code
     */
    private int requestCode;

    /**
     * The intents for starting activities
     */
    private Intent[] intents;

    /**
     * Additional options for how the Activity should be started
     */
    private Bundle options;

    /**
     * The animation resource to use for the incoming activity
     */
    @AnimRes
    private int enter;

    /**
     * The animation resource to use for the outgoing activity
     */
    @AnimRes
    private int exit;

    /**
     * Constructs a new activity doorbell.
     *
     * @param builder The builder to build the activity doorbell
     */
    private ActivityDoorbell(Builder builder) {
        super(builder);
        this.starter     = builder.starter;
        this.requestCode = builder.requestCode;
        this.intents     = builder.intents;
        this.options     = builder.options;
        this.enter       = builder.enter;
        this.exit        = builder.exit;
    }

    /**
     * Test if the transition is given.
     *
     * @return True if the transition is given, otherwise false.
     */
    private boolean hasTransition() {
        return enter != 0 || exit != 0;
    }

    @Override
    protected void onAllow() {
        if (intents == null || intents.length == 0) {
            return;
        }
        start();
        overridePendingTransition();
    }

    /**
     * Starts the activities.
     */
    private void start() {
        if (intents.length > 1) {
            starter.startActivities(intents, options);
            return;
        }
        Intent intent = intents[0];
        switch (requestCode) {
            case NO_RESULT:
                starter.startActivity(intent, options);
                break;
            default:
                starter.startActivityForResult(intent, requestCode, options);
                break;
        }
    }

    /**
     * Overrides the pending transition.
     */
    private void overridePendingTransition() {
        Activity activity = starter.getActivity();
        if (activity == null) {
            return;
        }

        // get the transition of the starter
        int enterId = starter.getEnter();
        int exitId  = starter.getExit();

        // if the transition of the doorbell is given
        if (hasTransition()) {
            enterId = this.enter;
            exitId  = this.exit;
        }

        // override the pending transition
        activity.overridePendingTransition(enterId, exitId);
    }

    /**
     * The builder to build the activity doorbell.
     */
    public static class Builder extends Doorbell.Builder {

        /**
         * The starter to start activities
         */
        private Starter starter;

        /**
         * The request code
         */
        private int requestCode = NO_RESULT;

        /**
         * The array of intents for starting activities.
         */
        private Intent[] intents = new Intent[0];

        /**
         * Additional options for how the Activity should be started
         */
        private Bundle options;

        /**
         * The animation resource to use for the incoming activity
         */
        private int enter;

        /**
         * The animation resource to use for the outgoing activity
         */
        private int exit;

        /**
         * Construct a new builder object with the given starter.
         *
         * @param starter The starter to start activities
         */
        public Builder(Starter starter) {
            this.starter = starter == null ? STARTER_NULL : starter;
        }

        /**
         * Sets the activity class to be launched.
         *
         * @param activityClass The activity class to be launched
         * @return this
         */
        public Builder start(Class<? extends Activity> activityClass) {
            Context context = this.starter.getActivity();
            if (context == null) {
                return this;
            }
            Intent intent = new Intent(context, activityClass);
            return start(intent);
        }

        /**
         * Sets the activity classes to be launched.
         *
         * @param activityClasses The activity classes to be launched
         * @return this
         */
        public Builder start(Class<? extends Activity>... activityClasses) {
            if (activityClasses == null || activityClasses.length == 0) {
                return this;
            }
            Context context = this.starter.getActivity();
            if (context == null) {
                return this;
            }
            Intent[] intents = new Intent[activityClasses.length];
            for (int i = 0; i < activityClasses.length; i++) {
                Intent intent = new Intent(context, activityClasses[i]);
                intents[i]    = intent;
            }
            return start(intents);
        }

        /**
         * Sets the intent for starting activity.
         *
         * @param intent The intent
         * @return this
         */
        public Builder start(Intent intent) {
            if (intent == null) {
                return this;
            }
            this.intents = new Intent[]{ intent };
            return this;
        }

        /**
         * Sets the intents for starting activities.
         *
         * @param intents The intents for starting activities.
         * @return this
         */
        public Builder start(Intent... intents) {
            if (intents == null || intents.length == 0) {
                return this;
            }
            this.intents = intents;
            return this;
        }

        /**
         * Puts a boolean extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The boolean data value
         * @return this
         */
        public Builder extra(String name, boolean value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a int extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The int data value
         * @return this
         */
        public Builder extra(String name, int value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a long extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The long data value
         * @return this
         */
        public Builder extra(String name, long value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a float extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The float data value
         * @return this
         */
        public Builder extra(String name, float value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a double extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The double data value
         * @return this
         */
        public Builder extra(String name, double value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a string extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The string data value
         * @return this
         */
        public Builder extra(String name, String value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a byte array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The byte array data value
         * @return this
         */
        public Builder extra(String name, byte[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a int array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The int array data value
         * @return this
         */
        public Builder extra(String name, int[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a long array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The long array data value
         * @return this
         */
        public Builder extra(String name, long[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a boolean array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The boolean array data value
         * @return this
         */
        public Builder extra(String name, boolean[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a float array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The float array data value
         * @return this
         */
        public Builder extra(String name, float[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a double array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The double array data value
         * @return this
         */
        public Builder extra(String name, double[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a char sequence array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The CharSequence array data value
         * @return this
         */
        public Builder extra(String name, CharSequence[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a string array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The string array data value
         * @return this
         */
        public Builder extra(String name, String[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a parcelable extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The Parcelable data value
         * @return this
         */
        public Builder extra(String name, Parcelable value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a parcelable array extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The Parcelable array data value
         * @return this
         */
        public Builder extra(String name, Parcelable[] value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * PUts a serializable extra.
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The
         * @return this
         */
        public Builder extra(String name, Serializable value) {
            for (Intent intent : intents) {
                intent.putExtra(name, value);
            }
            return this;
        }

        /**
         * Puts a bundle of extras.
         *
         * @param extras The extras
         * @return this
         */
        public Builder extras(Bundle extras) {
            if (extras == null) {
                return this;
            }
            for (Intent intent : intents) {
                intent.putExtras(extras);
            }
            return this;
        }

        /**
         * Sets additional options for how the Activity should be started.
         *
         * @param options Additional options for how the Activity should be started
         * @return this
         */
        public Builder options(Bundle options) {
            this.options = options;
            return this;
        }

        /**
         * Sets the request code.
         *
         * @param requestCode The request code
         * @return this
         */
        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        /**
         * Sets the animation resource to use for the incoming activity.
         *
         * @param enter The animation resource to use for the incoming activity
         * @return this
         */
        public Builder enter(@AnimRes int enter) {
            return transition(enter, getDefaultExit());
        }

        /**
         * Sets the animation resource to use for the outgoing activity.
         *
         * @param exit The animation resource to use for the outgoing activity
         * @return this
         */
        public Builder exit(@AnimRes int exit) {
            return transition(getDefaultEnter(), exit);
        }

        /**
         * Sets the transition animation to perform next.
         *
         * @param enter The animation resource to use for the incoming activity
         * @param exit  The animation resource to use for the outgoing activity
         * @return this
         */
        public Builder transition(@AnimRes final int enter, @AnimRes final int exit) {
            this.enter = enter;
            this.exit  = exit;
            return this;
        }

        /**
         * Sets the condition of the ringing.
         *
         * @param condition The condition to test
         * @return this
         */
        public Builder condition(boolean condition) {
            super.condition(condition);
            return this;
        }

        /**
         * Sets the condition of the ringing.
         *
         * @param condition The condition to test
         * @return this
         */
        public Builder condition(Condition condition) {
            boolean test = condition == null || condition.test();
            super.condition(test);
            return this;
        }

        @Override
        public Builder door(Door door) {
            super.door(door);
            return this;
        }

        /**
         * Ring the doorbell.
         */
        public void ring() {
            ring(new RingListener() {
                @Override
                public void onAllow() {
                    // do nothing
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

        @Override
        protected Doorbell build() {
            return new ActivityDoorbell(this);
        }
    }
}
