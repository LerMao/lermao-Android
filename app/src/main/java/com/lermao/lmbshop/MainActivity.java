package com.lermao.lmbshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.newrelic.agent.android.NewRelic;
import com.oneapm.agent.android.OneApmAgent;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OneApmAgent.init(this.getApplicationContext()).setToken("4A6ACDCE70D98931FD385E0B6B58708F34").start();
        NewRelic.withApplicationToken(
                "AA0bf8eebbd32ed6764ef75773c45b6518216cd986"
        ).start(this.getApplication());
        MobclickAgent.onEvent(MainActivity.this, "EvenLogin");
    }


}
