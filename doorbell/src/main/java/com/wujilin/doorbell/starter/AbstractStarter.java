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

import com.wujilin.doorbell.Starter;
import com.wujilin.doorbell.Transition;

import static com.wujilin.doorbell.Doorbell.getDefaultEnter;
import static com.wujilin.doorbell.Doorbell.getDefaultExit;

/**
 * The abstract implementation of the Starter interface.
 */
public abstract class AbstractStarter implements Starter {
  private int enterId = getDefaultEnter();
  private int exitId  = getDefaultExit();

  public AbstractStarter() {

  }

  public AbstractStarter(Transition transition) {
    if (transition == null) {
      return;
    }
    this.enterId = transition.getEnter();
    this.exitId  = transition.getExit();
  }

  public AbstractStarter(int enterId, int exitId) {
    this.enterId = enterId;
    this.exitId  = exitId;
  }

    @Override
  public int getEnter() {
    return enterId;
  }

  @Override
  public int getExit() {
    return exitId;
  }
}