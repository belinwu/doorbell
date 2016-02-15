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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wujilin.doorbell.Starter;

import java.lang.ref.WeakReference;

import static com.wujilin.doorbell.Doorbell.getDefaultEnter;
import static com.wujilin.doorbell.Doorbell.getDefaultExit;

/**
 * The abstract implementation of the {@link Starter} interface.
 */
public abstract class AbstractStarter<T> implements Starter {

    /**
     * A resource ID of the animation resource to use for the incoming activity
     */
    private int enterId;

    /**
     * A resource ID of the animation resource to use for the outgoing activity
     */
    private int exitId;

    /**
     * The weak reference to the starter.
     */
    private WeakReference<T> starterReference;

    /**
     * The default constructor.
     */
    public AbstractStarter(T starter) {
        this(starter, getDefaultEnter(), getDefaultExit());
    }

    /**
     * Constructs a starter with the animation resource of transition.
     *
     * @param starter The starter to start activity
     * @param enterId A resource ID of the animation resource to use for the incoming activity
     * @param exitId  A resource ID of the animation resource to use for the outgoing activity
     */
    public AbstractStarter(T starter, int enterId, int exitId) {
        starterReference = new WeakReference<>(starter);
        this.enterId     = enterId;
        this.exitId      = exitId;
    }

    @Override
    public final void startActivity(Intent intent, Bundle options) {
        T starter = starterReference.get();
        if (starter == null) {
            return;
        }
        startActivity(starter, intent, options);
    }

    @Override
    public final void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        T starter = starterReference.get();
        if (starter == null) {
            return;
        }
        startActivityForResult(starter, intent, requestCode, options);
    }

    @Override
    public final void startActivities(Intent[] intents, Bundle options) {
        T starter = starterReference.get();
        if (starter == null) {
            return;
        }
        startActivities(starter, intents, options);
    }

    @Nullable
    @Override
    public final Activity getActivity() {
        T starter = starterReference.get();
        if (starter == null) {
            return null;
        }
        return getActivity(starter);
    }

    @Override
    public int getEnter() {
        return enterId;
    }

    @Override
    public int getExit() {
        return exitId;
    }

    /**
     * Starts the activity.
     *
     * @param starter The starter to start activity
     * @param intent  The intent to start
     * @param options Additional options for how the Activity should be started
     */
    public abstract void startActivity(T starter, Intent intent, Bundle options);

    /**
     * Starts the activity for result.
     *
     * @param starter     The starter to start activity
     * @param intent      The intent to start
     * @param requestCode The request code for result
     * @param options     Additional options for how the Activity should be started
     */
    public abstract void startActivityForResult(T starter, Intent intent, int requestCode, Bundle options);

    /**
     * Starts the multiple activities.
     *
     * @param starter The starter to start activity
     * @param intents The intents to start
     * @param options Additional options for how the Activity should be started
     */
    public abstract void startActivities(T starter, Intent[] intents, Bundle options);

    /**
     * Returns the current activity.
     *
     * @param starter The starter to start activity
     * @return The current activity
     */
    public abstract Activity getActivity(T starter);
}
