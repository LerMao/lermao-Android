package com.lermao.lmbshop.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

/**
 * lermao-Android
 * Created by 小宇宙 on
 * Date:2018-03-31
 * Time:下午10:30
 * Copyright © 2018年 xyz.com All rights reserved.
 */

public abstract class BaseActivity extends AppCompatActivity implements IUIOperation{

    protected View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());
        // 系统的一个根布局，可以查找到activity布局的所有的子控件
        root = findViewById(android.R.id.content);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
