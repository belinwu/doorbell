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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;

/**
 * The interface represents the objects that have some methods to start activities.
 *
 * @see com.wujilin.doorbell.starter.ContextStarter
 * @see com.wujilin.doorbell.starter.ActivityStarter
 * @see com.wujilin.doorbell.starter.FragmentStarter
 */
public interface Starter {

    /**
     * Starts the activity.
     *
     * @param intent  The description of the activity to start
     * @param options Additional options for how the Activity should be started
     */
    void startActivity(Intent intent, Bundle options);

    /**
     * Starts the activity for the result.
     *
     * @param intent      The description of the activity to start
     * @param requestCode The code that will be returned with onActivityResult() identifying this
     *                    request
     * @param options     Additional options for how the Activity should be started
     */
    void startActivityForResult(Intent intent, int requestCode, Bundle options);

    /**
     * Starts multiple activities.
     *
     * @param intents The intents to start.
     * @param options Additional options for how the activities should be started.
     */
    void startActivities(Intent[] intents, Bundle options);

    /**
     * Returns a resource ID of the animation resource to use for the incoming activity.
     *
     * @return A resource ID of the animation resource to use for the incoming activity
     */
    @AnimRes
    int getEnter();

    /**
     * Returns a resource ID of the animation resource to use for the outgoing activity.
     *
     * @return A resource ID of the animation resource to use for the outgoing activity
     */
    @AnimRes
    int getExit();

    /**
     * Return the current activity.
     *
     * @return The current activity
     */
    @Nullable
    Activity getActivity();
}
