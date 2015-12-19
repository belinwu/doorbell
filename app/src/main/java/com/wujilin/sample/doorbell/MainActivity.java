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
package com.wujilin.sample.doorbell;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wujilin.doorbell.Condition;
import com.wujilin.doorbell.Door;
import com.wujilin.doorbell.Doorbell;
import com.wujilin.doorbell.OnAllowListener;
import com.wujilin.doorbell.OnBlockListener;
import com.wujilin.doorbell.RingListener;

public class MainActivity extends Activity implements View.OnClickListener {

  private TextView status;
  private Button login;
  private Button logout;
  private Button callback1;
  private Button callback2;
  private Button callback3;
  private Button callback4;
  private Button callback5;
  private Button callback6;
  private Button activity1;
  private Button activity2;
  private Button activity3;
  private Button activity4;
  private Button activity5;
  private Button activity6;
  private Button activity7;
  private Button activity8;
  private Button activity9;
  private Button activity10;
  private boolean logined;

  private LoginDoor loginDoor = new LoginDoor();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.app_activity_main);
    onFindView();
    onInitView();
  }

  public void onFindView() {
    callback1 = (Button) findViewById(R.id.callback1);
    callback2 = (Button) findViewById(R.id.callback2);
    callback3 = (Button) findViewById(R.id.callback3);
    callback4 = (Button) findViewById(R.id.callback4);
    callback5 = (Button) findViewById(R.id.callback5);
    callback6 = (Button) findViewById(R.id.callback6);
    activity1 = (Button) findViewById(R.id.activity1);
    activity2 = (Button) findViewById(R.id.activity2);
    activity3 = (Button) findViewById(R.id.activity3);
    activity4 = (Button) findViewById(R.id.activity4);
    activity5 = (Button) findViewById(R.id.activity5);
    activity6 = (Button) findViewById(R.id.activity6);
    activity7 = (Button) findViewById(R.id.activity7);
    activity8 = (Button) findViewById(R.id.activity8);
    activity9 = (Button) findViewById(R.id.activity9);
    activity10 = (Button) findViewById(R.id.activity10);
    login = (Button) findViewById(R.id.login);
    logout = (Button) findViewById(R.id.logout);
    status = (TextView) findViewById(R.id.status);
  }

  public void onInitView() {
    callback1.setOnClickListener(this);
    callback2.setOnClickListener(this);
    callback3.setOnClickListener(this);
    callback4.setOnClickListener(this);
    callback5.setOnClickListener(this);
    callback6.setOnClickListener(this);
    activity1.setOnClickListener(this);
    activity2.setOnClickListener(this);
    activity3.setOnClickListener(this);
    activity4.setOnClickListener(this);
    activity5.setOnClickListener(this);
    activity6.setOnClickListener(this);
    activity7.setOnClickListener(this);
    activity8.setOnClickListener(this);
    activity9.setOnClickListener(this);
    activity10.setOnClickListener(this);
    login.setOnClickListener(this);
    logout.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.login:
        logined = true;
        status.setText("Log in");
        break;
      case R.id.logout:
        logined = false;
        status.setText("Log out");
        break;
      case R.id.callback1:
        Doorbell.create(new Condition() {
          @Override
          public boolean test() {
            return logined;
          }
        }).ring(new RingListener() {
          @Override
          public void onAllow() {
            toask("RingListener.onAllow() get called");
          }

          @Override
          public void onBlock() {
            toask("RingListener.onBlock() get called");
          }

          @Override
          public void onComplete() {
            toask("RingListener.onComplete() get called");
          }
        });
        break;
      case R.id.callback2:
        Doorbell.create(new Condition() {
          @Override
          public boolean test() {
            return logined;
          }
        }).ring(new OnAllowListener() {
          @Override
          public void onAllow() {
            toask("OnAllowListener.onAllow() get called");
          }
        });
        break;
      case R.id.callback3:
        Doorbell.create(new Condition() {
          @Override
          public boolean test() {
            return logined;
          }
        }).ring(new OnBlockListener() {
          @Override
          public void onBlock() {
            toask("OnBlockListener.onBlock() get called");
          }
        });
        break;
      case R.id.callback4:
        Doorbell.create(loginDoor).ring(new RingListener() {
          @Override
          public void onAllow() {
            toask("RingListener.onAllow() get called");
          }

          @Override
          public void onBlock() {
            toask("RingListener.onBlock() get called");
          }

          @Override
          public void onComplete() {
            toask("RingListener.onComplete() get called");
          }
        });
        break;
      case R.id.callback5:
        Doorbell.create(loginDoor).ring(new OnAllowListener() {
          @Override
          public void onAllow() {
            toask("OnAllowListener.onAllow() get called");
          }
        });
        break;
      case R.id.callback6:
        Doorbell.create(loginDoor).ring(new OnBlockListener() {
          @Override
          public void onBlock() {
            toask("OnBlockListener.onBlock() get called");
          }
        });
        break;
      case R.id.activity1:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .ring();
        break;
      case R.id.activity2:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .condition(new Condition() {
              @Override
              public boolean test() {
                return logined;
              }
            })
            .ring();
        break;
      case R.id.activity3:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .door(loginDoor)
            .ring();
        break;
      case R.id.activity4:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .transition(R.anim.app_push_left_in, R.anim.app_push_left_out)
            .condition(new Condition() {
              @Override
              public boolean test() {
                return logined;
              }
            })
            .ring(new RingListener() {
              @Override
              public void onAllow() {
                toask("RingListener.onAllow() get called");
              }

              @Override
              public void onBlock() {
                toask("RingListener.onBlock() get called");
              }

              @Override
              public void onComplete() {
                toask("RingListener.onComplete() get called");
              }
            });
        break;
      case R.id.activity5:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .condition(new Condition() {
              @Override
              public boolean test() {
                return logined;
              }
            })
            .ring(new OnAllowListener() {
              @Override
              public void onAllow() {
                toask("OnAllowListener.onAllow() get called");
              }
            });
        break;
      case R.id.activity6:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .condition(new Condition() {
              @Override
              public boolean test() {
                return logined;
              }
            })
            .ring(new OnBlockListener() {
              @Override
              public void onBlock() {
                toask("OnBlockListener.onBlock() get called");
              }
            });
        break;
      case R.id.activity7:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .door(loginDoor)
            .enter(R.anim.app_navigtor_pop_right_in)
            .exit(R.anim.app_navigtor_pop_right_out)
            .ring(new RingListener() {
              @Override
              public void onAllow() {
                toask("RingListener.onAllow() get called");
              }

              @Override
              public void onBlock() {
                toask("RingListener.onBlock() get called");
              }

              @Override
              public void onComplete() {
                toask("RingListener.onComplete() get called");
              }
            });
        break;
      case R.id.activity8:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .door(loginDoor)
            .ring(new OnAllowListener() {
              @Override
              public void onAllow() {
                toask("OnAllowListener.onAllow() get called");
              }
            });
        break;
      case R.id.activity9:
        Doorbell.with(this).start(SendActivity.class)
            .extra("name", "Doorbell")
            .door(loginDoor)
            .ring(new OnBlockListener() {
              @Override
              public void onBlock() {
                toask("OnBlockListener.onBlock() get called");
              }
            });
        break;
      case R.id.activity10:
        Doorbell.defaultTransition(R.anim.app_push_left_in, R.anim.app_navigtor_pop_right_out);
        Doorbell.with(this)
            .start(SendActivity.class, ThirdActivity.class)
            .extra("name", "Doorbell")
            .ring();
        break;
    }
  }

  public void toask(String text) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
  }

  /**
   * A door to test the user login or not.
   */
  private class LoginDoor implements Door {
    @Override
    public boolean test() {
      return logined;
    }

    @Override
    public void onAllow() {
      toask("LoginDoor.onAllow() get called");
    }

    @Override
    public void onBlock() {
      toask("LoginDoor.onBlock() get called");
    }

    @Override
    public void onComplete() {
      toask("LoginDoor.onComplete() get called");
    }
  }
}
