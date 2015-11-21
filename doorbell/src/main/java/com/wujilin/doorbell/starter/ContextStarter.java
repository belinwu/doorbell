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

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.wujilin.doorbell.Starter;

import java.lang.ref.WeakReference;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

/**
 * The context starter to start activities.
 */
class ContextStarter implements Starter {

  /**
   * The weak reference to the context.
   */
  private WeakReference<Context> contextReference;

  /**
   * Constructs a context starter.
   *
   * @param context The context to start activities
   */
  public ContextStarter(Context context) {
    this.contextReference = new WeakReference<>(context);
  }

  @Override
  @TargetApi(16)
  public void startActivity(Intent intent, Bundle options) {
    Context context = contextReference.get();
    if (context == null) {
      return;
    }
    if (SDK_INT >= JELLY_BEAN) {
      context.startActivity(intent, options);
      return;
    }
    context.startActivity(intent);
  }

  @Override
  public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
    Context context = contextReference.get();
    if (context == null) {
      return;
    }
    if (context instanceof Activity) {
      Activity activity = (Activity) context;
      ActivityCompat.startActivityForResult(activity, intent, requestCode, options);
    }
  }

  @Override
  public Activity getActivity() {
    Context context = contextReference.get();
    if (context == null) {
      return null;
    }
    if (context instanceof Activity) {
      return (Activity) context;
    }
    return null;
  }
}
