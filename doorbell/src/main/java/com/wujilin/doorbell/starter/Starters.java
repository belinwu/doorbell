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
package com.wujilin.doorbell.starter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.wujilin.doorbell.Starter;

/**
 * Helper class for creating the starters.
 */
public final class Starters {

  /**
   * Preventing from constructing.
   */
  private Starters() {
  }

  /**
   * Creates a new context starter.
   *
   * @param context The context to start activities
   * @return The context starter or null if context is null
   */
  public static Starter newStarter(Context context) {
    if (context == null) {
      return null;
    }
    if (context instanceof Starter) {
      return (Starter) context;
    }
    return new ContextStarter(context);
  }

  /**
   * Creates a new activity starter.
   *
   * @param activity The activity to start activities
   * @return The activity starter or null if context is null
   */
  public static Starter newStarter(Activity activity) {
    if (activity == null) {
      return null;
    }
    if (activity instanceof Starter) {
      return (Starter) activity;
    }
    return new ActivityStarter(activity);
  }

  /**
   * Creates a new fragment starter.
   *
   * @param fragment The fragment to start activities
   * @return The fragment starter or null if fragment is null
   */
  public static Starter newStarter(Fragment fragment) {
    if (fragment == null) {
      return null;
    }
    if (fragment instanceof Starter) {
      return (Starter) fragment;
    }
    return new FragmentStarter(fragment);
  }
}
