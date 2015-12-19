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

/**
 * The class represents a doorbell to ring to call the callback.
 */
class CallbackDoorbell extends Doorbell {

  /**
   * Contructs a new callback doorbell.
   *
   * @param builder The builder to build the callback doorbell
   */
  private CallbackDoorbell(Builder builder) {
    super(builder);
  }

  /**
   * The callback doorbell builder.
   */
  public static class Builder extends Doorbell.Builder {

    /**
     * Construct a new builder.
     *
     * @param condition The condition to test
     */
    public Builder(boolean condition) {
      condition(condition);
    }

    /**
     * Constructs a new builder.
     *
     * @param condition The condition to test
     */
    public Builder(Condition condition) {
      boolean test = condition == null || condition.test();
      condition(test);
    }

    /**
     * Contructs a new builder.
     *
     * @param door The door gets involved
     */
    public Builder(Door door) {
      door(door);
    }

    @Override
    protected Doorbell build() {
      return new CallbackDoorbell(this);
    }
  }
}
