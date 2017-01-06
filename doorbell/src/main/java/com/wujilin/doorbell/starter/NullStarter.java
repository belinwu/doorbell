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

import com.wujilin.doorbell.Starter;

/**
 * The class implements Null Object Pattern for the {@link Starter} interface.
 */
class NullStarter extends AbstractStarter<Object> {

    /**
     * Construct a new null starter.
     *
     * @param starter The starter
     */
    public NullStarter(Object starter) {
        super(starter);
    }

    @Override
    public void startActivity(Object starter, Intent intent, Bundle options) {
        // do nothing
    }

    @Override
    public void startActivityForResult(Object starter, Intent intent, int requestCode, Bundle options) {
        // do nothing
    }

    @Override
    public void startActivities(Object starter, Intent[] intents, Bundle options) {
        // do nothing
    }

    @Override
    public Activity getActivity(Object starter) {
        return null;
    }

    @Override
    protected void exit(Object starter) {
        // do nothing
    }
}
