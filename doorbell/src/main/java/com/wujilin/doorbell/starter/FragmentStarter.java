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
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

/**
 * The fragment starter to start activities.
 */
class FragmentStarter extends AbstractStarter<Fragment> {

    /**
     * Constructs a new fragment starter.
     *
     * @param fragment The fragment to start activity
     */
    public FragmentStarter(Fragment fragment) {
        super(fragment);
    }

    /**
     * Constructs a new fragment starter.
     *
     * @param fragment The fragment to start activity
     * @param enter    A resource ID of the animation resource to use for the incoming activity
     * @param exit     A resource ID of the animation resource to use for the outgoing activity
     */
    public FragmentStarter(Fragment fragment, @AnimRes int enter, @AnimRes int exit) {
        super(fragment, enter, exit);
    }

    @Override
    public void startActivity(Fragment starter, Intent intent, Bundle options) {
        starter.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Fragment starter, Intent intent, int requestCode, Bundle options) {
        starter.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivities(Fragment starter, Intent[] intents, Bundle options) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        ActivityCompat.startActivities(activity, intents, options);
    }

    @Override
    public Activity getActivity(Fragment starter) {
        return starter.getActivity();
    }

    @Override
    protected void exit(Fragment starter) {
        if (starter instanceof DialogFragment) {
            DialogFragment fragment = (DialogFragment) starter;
            if (fragment.getShowsDialog()) {
                fragment.dismiss();
                return;
            }
        }
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
