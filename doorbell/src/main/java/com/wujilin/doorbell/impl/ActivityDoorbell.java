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
package com.wujilin.doorbell.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;

import com.wujilin.doorbell.Condition;
import com.wujilin.doorbell.Door;
import com.wujilin.doorbell.Doorbell;
import com.wujilin.doorbell.RingListener;
import com.wujilin.doorbell.Starter;
import com.wujilin.doorbell.Transition;

import java.io.Serializable;

import static com.wujilin.doorbell.starter.Starters.newStarter;
import static com.wujilin.doorbell.starter.Starters.newStarter;
import static com.wujilin.doorbell.starter.Starters.newStarter;
import static com.wujilin.doorbell.util.Objects.requireNonNull;

/**
 * The class represents the doorbell to ring to start activities.
 */
public class ActivityDoorbell extends Doorbell {

  /**
   * The value represents no result
   */
  public static final int NO_RESULT = -1;

  /**
   * The starter to start activities
   */
  private Starter starter;

  /**
   * The request code
   */
  private int requestCode;

  /**
   * The intent for starting activities
   */
  private Intent intent;

  /**
   * Additional options for how the Activity should be started
   */
  private Bundle options;

  /**
   * The transition animation to perform next
   */
  private Transition transition;

  /**
   * Constructs a new activity doorbell.
   *
   * @param builder The builder to build the activity doorbell
   */
  private ActivityDoorbell(final Builder builder) {
    super(builder);
    this.starter     = builder.starter;
    this.requestCode = builder.requestCode;
    this.intent      = builder.intent;
    this.options     = builder.options;
    this.transition  = new Transition() {
      @Override
      public int getEnter() {
        return builder.enter;
      }

      @Override
      public int getExit() {
        return builder.exit;
      }
    };
  }

  @Override
  protected void onAllow() {
    requireNonNull(intent, "The intent must not be null.");

    switch (requestCode) {
      case NO_RESULT:
        starter.startActivity(intent, options);
        break;
      default:
        starter.startActivityForResult(intent, requestCode, options);
        break;
    }

    Activity activity = starter.getActivity();
    if (activity == null) {
      return;
    }

    // get the transition of the starter
    int enterId = starter.getEnter();
    int exitId  = starter.getExit();

    // if the transition of the doorbell is given
    if (transition != null) {
      enterId = transition.getEnter();
      exitId  = transition.getExit();
    }

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
     * The intent for strating activities
     */
    private Intent intent;

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
     * Constructs a new builder object with the given context.
     *
     * @param context The context to start activities
     */
    public Builder(Context context) {
      this(newStarter(context));
    }

    /**
     * Constructs a new builder object with the given activity.
     *
     * @param activity The activity to start activities
     */
    public Builder(Activity activity) {
      this(newStarter(activity));
    }

    /**
     * Constructs a new builder object with the given fragment.
     *
     * @param fragment The fragment to start acitvities
     */
    public Builder(Fragment fragment) {
      this(newStarter(fragment));
    }

    /**
     * Construct a new builder object with the given starter.
     *
     * @param starter The starter to start acitvities
     */
    public Builder(Starter starter) {
      requireNonNull(starter, "The starter must not be null.");
      this.starter = starter;
    }

    /**
     * Sets the activity class to be launched.
     *
     * @param activityClass The activity class to be launched
     * @return this
     */
    public Builder start(Class<? extends Activity> activityClass) {
      Context context = this.starter.getActivity();
      Intent intent   = new Intent(context, activityClass);
      return start(intent);
    }

    /**
     * Sets the intent for strating activities.
     *
     * @param intent The intent
     * @return this
     */
    public Builder start(Intent intent) {
      requireNonNull(starter, "The intent must not be null.");
      this.intent = intent;
      return this;
    }

    /**
     * Puts a boolean extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The boolean data value
     * @return this
     */
    public Builder extra(String name, boolean value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a int extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The int data value
     * @return this
     */
    public Builder extra(String name, int value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a long extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The long data value
     * @return this
     */
    public Builder extra(String name, long value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a float extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The float data value
     * @return this
     */
    public Builder extra(String name, float value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a double extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The double data value
     * @return this
     */
    public Builder extra(String name, double value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a string extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The string data value
     * @return this
     */
    public Builder extra(String name, String value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a byte array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The byte array data value
     * @return this
     */
    public Builder extra(String name, byte[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a int array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The int array data value
     * @return this
     */
    public Builder extra(String name, int[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a long array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The long array data value
     * @return this
     */
    public Builder extra(String name, long[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a boolean array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The boolean array data value
     * @return this
     */
    public Builder extra(String name, boolean[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a float array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The float array data value
     * @return this
     */
    public Builder extra(String name, float[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a double array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The double array data value
     * @return this
     */
    public Builder extra(String name, double[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a char sequence array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The CharSequence array data value
     * @return this
     */
    public Builder extra(String name, CharSequence[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a string array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The string array data value
     * @return this
     */
    public Builder extra(String name, String[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a parcelable extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The Parcelable data value
     * @return this
     */
    public Builder extra(String name, Parcelable value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * Puts a parcelable array extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The Parcelable array data value
     * @return this
     */
    public Builder extra(String name, Parcelable[] value) {
      requireIntent();
      this.intent.putExtra(name, value);
      return this;
    }

    /**
     * PUts a serializable extra.
     *
     * @param name The name of the extra data, with package prefix.
     * @param value The
     * @return
     */
    public Builder extra(String name, Serializable value) {
      requireIntent();
      this.intent.putExtra(name, value);
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
      requireIntent();
      this.intent.putExtras(extras);
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
      return transition(enter, 0);
    }

    /**
     * Sets the animation resource to use for the outgoing activity.
     *
     * @param exit The animation resource to use for the outgoing activity
     * @return this
     */
    public Builder exit(@AnimRes int exit) {
      return transition(0, exit);
    }

    /**
     * Sets the transition animation to perform next.
     *
     * @param enter The animation resource to use for the incoming activity
     * @param exit The animation resource to use for the outgoing activity
     * @return this
     */
    public Builder transition(@AnimRes final int enter, @AnimRes final int exit) {
      this.enter = enter;
      this.exit  = exit;
      return this;
    }

    /**
     * Sets the transition animation to perform next.
     *
     * @param transition The transition animation
     * @return this
     */
    public Builder transition(Transition transition) {
      if (transition == null) {
        return this;
      }
      this.enter = transition.getEnter();
      this.exit  = transition.getExit();
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

    /**
     * Requires a intent.
     */
    private void requireIntent() {
      requireNonNull(this.intent, "The intent must not be null.");
    }
  }
}
