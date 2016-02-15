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
import android.support.annotation.AnimRes;
import android.support.v4.app.ActivityCompat;

/**
 * The activity starter to start activities.
 */
class ActivityStarter extends AbstractStarter<Activity> {

    /**
     * Constructs a new activity starter.
     *
     * @param activity The activity to start activity
     */
    public ActivityStarter(Activity activity) {
        super(activity);
    }

    /**
     * Constructs a new activity starter.
     *
     * @param activity The activity
     * @param enter    A resource ID of the animation resource to use for the incoming activity
     * @param exit     A resource ID of the animation resource to use for the outgoing activity
     */
    public ActivityStarter(Activity activity, @AnimRes int enter, @AnimRes int exit) {
        super(activity, enter, exit);
    }

    @Override
    public void startActivity(Activity starter, Intent intent, Bundle options) {
        ActivityCompat.startActivity(starter, intent, options);
    }

    @Override
    public void startActivityForResult(Activity starter, Intent intent, int requestCode, Bundle options) {
        ActivityCompat.startActivityForResult(starter, intent, requestCode, options);
    }

    @Override
    public void startActivities(Activity starter, Intent[] intents, Bundle options) {
        ActivityCompat.startActivities(starter, intents, options);
    }

    @Override
    public Activity getActivity(Activity starter) {
        return starter;
    }
}
